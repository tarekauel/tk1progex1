package controller;

import model.Account;
import model.Partner;
import model.StateRecord;
import model.packets.Message;
import model.packets.Snapshot;
import model.packets.StateRecordMessage;
import model.packets.Transfer;
import view.AccountView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class AccountHolder implements ActionListener {

    private DatagramSocket channelIn;
    private Map<Partner, Queue<Message>> channelsOut =
        Collections.synchronizedMap(new HashMap<Partner, Queue<Message>>());
    private List<Partner> partners = new ArrayList<>();

    private Partner me;
    private Account account;
    private AccountManager am;

    private HashMap<Integer, StateRecord> stateRecord = new HashMap<>();
    private HashMap<Integer, StateRecord> finishedRecords = new HashMap<>();
    private HashMap<Integer, List<StateRecordMessage>> stateRecordMessages = new HashMap<>();

    private String name;

    private TransferCreator transferCreator;

    public AccountHolder(AccountManager am, String name, int startBalance) {
        this.name = name;
        AccountView av = new AccountView(name);
        am.addAccount(av);
        av.setSnapshotButtonListener(this);
        BalanceUpdater bu = new BalanceUpdater(av);
        try {
            account = new Account(startBalance, bu);
            me = new Partner(am.getPort(name));
            channelIn = new DatagramSocket(me.getPort());
            this.am = am;
            (new Thread(new MessageReceiver())).start();
            transferCreator = new TransferCreator();
            new Thread(transferCreator).start();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public Partner get() {
        return me;
    }

    public synchronized boolean addPartner(Partner p) {
        Queue<Message> queue = new LinkedList<>();
        channelsOut.put(p, queue);
        (new Thread(new MessageSender(queue))).start();
        return partners.add(p);
    }

    public synchronized void sendMoney(Transfer t) {
        account.transfer(t.getAmount());
        channelsOut.get(t.getReceiver()).add(t);
        am.publishMessage(
            String.format("%s sends %d € to %s", am.getName(me.getPort()), t.getAmount(),
                am.getName(t.getReceiver().getPort())));
    }

    public synchronized void receiveMessage(Message m) {
        if (m instanceof Snapshot) {
            Snapshot s = (Snapshot) m;
            if (!finishedRecords.containsKey(s.getSnapshotId())) {
                if (s.getReceiver() == null) {
                    // this account initialized the snapshot
                    // record own state and turn on recording (stateRecord object does exist)
                    stateRecord.put(s.getSnapshotId(), new StateRecord(account.getBalance()));

                    // flood marker message to on all channels
                    for (Partner p : partners) {
                        channelsOut.get(p).add(new Snapshot(s.getInitiator(), this.get(), p, s.getSnapshotId()));
                    }
                } else {
                    StateRecord sr;
                    if (stateRecord.containsKey(s.getSnapshotId())) {
                        sr = stateRecord.get(s.getSnapshotId());
                        for (Partner p : partners) {
                            if (!sr.checkReceivedMarker(p)) {
                                channelsOut.get(p).add(
                                    new Snapshot(s.getInitiator(), this.get(), p, s.getSnapshotId()));
                                sr.receivedMarker(p);
                            }
                        }
                    } else {
                        sr = new StateRecord(account.getBalance());
                        sr.receivedMarker(s.getSender());
                        stateRecord.put(s.getSnapshotId(), sr);
                        for (Partner p : partners) {
                            // send marker to all partners
                            channelsOut.get(p).add(new Snapshot(s.getInitiator(), this.get(), p, s.getSnapshotId()));
                        }
                    }
                    if (sr.receivedMarkerCount() == partners.size()) {
                        stateRecord.remove(s.getSnapshotId());
                        finishedRecords.put(s.getSnapshotId(), sr);

                        StateRecordMessage srm = new StateRecordMessage(
                            s.getSnapshotId(), this.get(), s.getInitiator(), sr);

                        // send result to initiator
                        if(s.getInitiator().getPort() == this.get().getPort()) {
                            receiveMessage(srm);
                        } else {
                            if (channelsOut.get(s.getInitiator()) == null) {
                                System.err.println(s.getInitiator().getPort() + " " + get().getPort());
                            }
                            channelsOut.get(s.getInitiator()).add(srm);
                        }
                    }
                }
            }
        } else if (m instanceof Transfer) {
            Transfer t = (Transfer) m;
            account.receive(t.getAmount());
            am.publishMessage(
                String.format("%s received %d €",
                    am.getName(t.getReceiver().getPort()), t.getAmount()));

            for(StateRecord sr : stateRecord.values()) {
                if (!sr.checkReceivedMarker(t.getSender())) {
                    // marker was not received, yet
                    // record message
                    sr.addMessage(t);
                }
            }

        } else if (m instanceof StateRecordMessage) {
            StateRecordMessage srm = (StateRecordMessage) m;
            List<StateRecordMessage> list = stateRecordMessages.get(srm.getSnapshotId());
            list = (list == null) ? new ArrayList<StateRecordMessage>() : list;
            stateRecordMessages.put(srm.getSnapshotId(), list);
            list.add(srm);
            if(list.size() == partners.size() + 1) {
                String message = "\n-------------------\n";
                message += String.format("Snapshot Id %d, by %s\n", srm.getSnapshotId(), this.name);
                for(StateRecordMessage s : list) {
                    int pending = 0;
                    String pendingMessages = "";
                    for(Transfer t : s.getPending()) {
                        pending += t.getAmount();
                        pendingMessages +=
                            String.format("%s --> %s: %d€\n",
                                am.getName(t.getSender().getPort()),
                                am.getName(t.getReceiver().getPort()),
                                t.getAmount());
                    }
                    message +=
                        String.format("%s: balance %d €, pending %d €\n%s",
                            am.getName(s.getSender().getPort()),
                            s.getBalance(),
                            pending,
                            pendingMessages);
                }
                message += "-------------------\n";
                am.publishMessage(message);
            }
        }
    }

    public synchronized void start() {
        transferCreator.setStatus(true);
    }

    public synchronized void stop() {
        transferCreator.setStatus(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        receiveMessage(new Snapshot(this.get(), null, null));
    }

    private class TransferCreator implements Runnable {

        private boolean status = false;

        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 1750));
                    if (status) {
                            Transfer t = new Transfer(
                                ThreadLocalRandom.current().nextInt(0, Math.min(20, account.getBalance())),
                                get(),
                                partners.get(ThreadLocalRandom.current().nextInt(0, partners.size())));
                            sendMoney(t);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void setStatus(boolean status) {
            this.status = status;
        }
    }

    private class MessageReceiver implements Runnable {

        @Override
        public void run() {
            new Thread(new MessageReceiverTcp()).start();
            while(true) {
                try {
                    byte[] data = new byte[256];
                    DatagramPacket p = new DatagramPacket(data, 0, data.length);
                    channelIn.receive(p);
                    ByteArrayInputStream is = new ByteArrayInputStream(p.getData());
                    ObjectInputStream ois = new ObjectInputStream(is);
                    Message m = (Message) ois.readObject();
                    receiveMessage(m);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        private class MessageReceiverTcp implements Runnable {

            @Override
            public void run() {
                try {
                    ServerSocket ss = new ServerSocket(get().getPort() + 100);
                    while(true) {
                        Socket s = ss.accept();
                        ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                        StateRecordMessage srm = (StateRecordMessage) ois.readObject();
                        receiveMessage(srm);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class MessageSender implements Runnable {

        private Queue<Message> channelOut;

        public MessageSender(Queue<Message> channelOut) {
            this.channelOut = channelOut;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    if (!channelOut.isEmpty()) {
                        Message m = channelOut.poll();
                        if (m instanceof StateRecordMessage) {
                            Socket s = new Socket(InetAddress.getLocalHost(), m.getReceiver().getPort() + 100);
                            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                            oos.writeObject(m);
                            s.close();
                        } else {
                            ByteArrayOutputStream os = new ByteArrayOutputStream();
                            ObjectOutputStream oos = new ObjectOutputStream(os);
                            oos.writeObject(m);
                            byte[] data = os.toByteArray();
                            (new DatagramSocket()).send(new DatagramPacket(data, data.length,
                                InetAddress.getLoopbackAddress(), m.getReceiver().getPort()));
                        }
                    }
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 1750));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

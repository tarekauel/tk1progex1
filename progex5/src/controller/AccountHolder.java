package controller;

import model.Account;
import model.Partner;
import model.StateRecord;
import model.packets.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class AccountHolder {

    private DatagramSocket channelIn;
    private Map<Partner, Queue<Message>> channelsOut =
        Collections.synchronizedMap(new HashMap<Partner, Queue<Message>>());
    private List<Partner> partners = new ArrayList<>();

    private Partner me;
    private Account account;

    private HashMap<Integer, StateRecord> stateRecord = new HashMap<>();
    private HashMap<Integer, StateRecord> finishedRecords = new HashMap<>();
    private HashMap<Integer, List<StateRecordMessage>> stateRecordMessages = new HashMap<>();

    private String name;

    private TransferCreator transferCreator;

    public static void main(String[] args) {
        int nr = ThreadLocalRandom.current().nextInt(0, 100);
        new AccountHolder("Account " + args[0], ThreadLocalRandom.current().nextInt(100, 200));
    }

    public AccountHolder(String name, int startBalance) {
        this.name = name;
        account = new Account(startBalance);

        try {
            me = new Partner(getPortFromGui(name));
            new BalanceUpdater(me.getPort(), account);
            channelIn = new DatagramSocket(me.getPort());
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
        sendMessageToGui(
            String.format("%s sends %d € to %s", getNameFromGui(me.getPort()), t.getAmount(),
                getNameFromGui(t.getReceiver().getPort())));
    }

    public synchronized void receive(Object o) {
        if (o instanceof Message) {
            receiveMessage((Message) o);
        } else if (o instanceof Partner) {
            addPartner((Partner) o);
        } else if (o instanceof StartStopSignal) {
            transferCreator.setStatus(((StartStopSignal) o).isRunning());
        } else {
            throw new NotImplementedException();
        }
    }

    public synchronized void receiveMessage(Message m) {
        if (m instanceof Snapshot) {
            Snapshot s = (Snapshot) m;
            if (!finishedRecords.containsKey(s.getSnapshotId())) {
                if (s.getReceiver() == null) {
                    sendMessageToGui(String.format("%s initiates snapshot record and broadcasts it (Snapshot ID: %d) ",
                            getNameFromGui(me.getPort()), s.getSnapshotId()));

                    // this account initialized the snapshot
                    // record own state and turn on recording (stateRecord object does exist)
                    stateRecord.put(s.getSnapshotId(), new StateRecord(account.getBalance()));

                    // flood marker message to on all channels
                    for (Partner p : partners) {
                        channelsOut.get(p).add(new Snapshot(this.get(), this.get(), p, s.getSnapshotId()));
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
            sendMessageToGui(
                String.format("%s received %d €",
                    getNameFromGui(t.getReceiver().getPort()), t.getAmount()));

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
                                getNameFromGui(t.getSender().getPort()),
                                getNameFromGui(t.getReceiver().getPort()),
                                t.getAmount());
                    }
                    message +=
                        String.format("%s: balance %d €, pending %d €\n%s",
                            getNameFromGui(s.getSender().getPort()),
                            s.getBalance(),
                            pending,
                            pendingMessages);
                }
                message += "-------------------\n";
                sendMessageToGui(message);
            }
        } else if (m instanceof Terminate) {
            System.exit(0);
        }
    }

    private void sendMessageToGui(String message) {
        sendToGui(message);
    }

    private String getNameFromGui(int port) {
        return ((NameRequest) sendToGui(new NameRequest(port))).getName();
    }

    private int getPortFromGui(String name) {
        PortRequest p = ((PortRequest) sendToGui(new PortRequest(name, account.getBalance())));
        for(Partner partner : p.getPartners()) {
            addPartner(partner);
        }
        return p.getPort();
    }

    public static Object sendToGui(Object o) {
        try {
            Socket s = new Socket(InetAddress.getLoopbackAddress(), 7777);
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            oos.writeObject(o);
            Object res = ois.readObject();
            s.close();
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class TransferCreator implements Runnable {

        private boolean status = false;

        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 1750));
                    if (status) {
                        if (account.getBalance() > 1) {
                            Transfer t = new Transfer(
                                ThreadLocalRandom.current().nextInt(Math.min(1, account.getBalance()), Math.min(20, account.getBalance())),
                                get(),
                                partners.get(ThreadLocalRandom.current().nextInt(0, partners.size())));
                            sendMoney(t);
                        }
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
            while(true) {
                try {
                    byte[] data = new byte[8190];
                    DatagramPacket p = new DatagramPacket(data, 0, data.length);
                    channelIn.receive(p);
                    ByteArrayInputStream is = new ByteArrayInputStream(p.getData());
                    ObjectInputStream ois = new ObjectInputStream(is);
                    Object o = ois.readObject();
                    receive(o);
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
                        ByteArrayOutputStream os = new ByteArrayOutputStream();
                        ObjectOutputStream oos = new ObjectOutputStream(os);
                        oos.writeObject(m);
                        byte[] data = os.toByteArray();
                        (new DatagramSocket()).send(new DatagramPacket(data, data.length,
                            InetAddress.getLoopbackAddress(), m.getReceiver().getPort()));

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

package controller;

import model.Partner;
import model.StateRecord;
import model.packets.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import view.AccountManagerLayout;
import view.AccountView;
import view.CirculationBalanceView;

import javax.sound.sampled.Port;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class AccountManager implements ActionListener {

  public static void main(String[] args) {
    AccountManager am = new AccountManager();
  }

  private boolean running = false;

  private AccountManagerLayout aml;
  private CirculationBalanceUpdater cbu;

  private int port = 2100;

  private HashMap<Integer, String> names = new HashMap<>();
  private HashMap<Integer, AccountView> accountViews = new HashMap<>();

  public AccountManager() {

    new Thread(new GuiReceiver()).start();

    aml = new AccountManagerLayout(this);
    cbu = new CirculationBalanceUpdater();
    addPanel(cbu.getView());

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    StartStopSignal sss;
    if(!running) {
      running = true;
      sss = new StartStopSignal(running);

    } else {
      running = false;
      sss = new StartStopSignal(running);
    }
    for(int port : names.keySet()) {
      sendToClient(port, sss);
    }
  }

  private void addPanel(JPanel p) {
    aml.addPanel(p);
  }

  private void publishMessage(String m) {
    aml.addMessage(m);
  }

  private synchronized int getPort(String name) {
    final int port = this.port++;

    names.put(port, name);
    AccountView av = new AccountView(name);
    addPanel(av);
    accountViews.put(port, av);
    av.setSnapshotButtonListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        sendToClient(port, new Snapshot(null, null, null));
      }
    });

    for(int i : names.keySet()) {
      if (i != port) {
        // notify others about new partner
        sendToClient(i, new Partner(port));
      }
    }
    return port;
  }

  private void sendToClient(int port, Object o) {
    try {
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      ObjectOutputStream oos = new ObjectOutputStream(os);
      oos.writeObject(o);
      byte[] data = os.toByteArray();
      (new DatagramSocket()).send(new DatagramPacket(data, data.length,
          InetAddress.getLoopbackAddress(), port));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String getName(int port) {
    return names.get(port);
  }

  private class GuiReceiver implements Runnable {

    @Override
    public void run() {
      try {
        ServerSocket ss = new ServerSocket(7777);
        while(true) {
          Socket s = ss.accept();
          ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
          ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
          oos.writeObject(handle(ois.readObject()));
          s.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }

    private Object handle(Object o) {
      if(o instanceof String) {
        publishMessage((String) o);
        return null;
      } else if (o instanceof NameRequest) {
        NameRequest n = (NameRequest) o;
        n.setName(getName(n.getPort()));
        return n;
      } else if (o instanceof PortRequest) {
        PortRequest p = (PortRequest) o;
        p.setPort(getPort(p.getName()));
        cbu.addAccount(p.getPort(), p.getStartBalance());
        ArrayList<Partner> partners = new ArrayList<>();
        for(int port : names.keySet()) {
          if (port != p.getPort()) {
            partners.add(new Partner(port));
          }
        }
        p.setPartners(partners);
        return p;
      } else if (o instanceof BalanceUpdate) {
        BalanceUpdate b = (BalanceUpdate) o;
        accountViews.get(b.getPort()).setBalance(b.getBalance());
        cbu.update(b.getPort(), b.getBalance());
        return null;
      }
      throw new NotImplementedException();
    }
  }
}

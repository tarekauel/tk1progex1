package controller;

import view.AccountManagerLayout;
import view.AccountView;
import view.CirculationBalanceView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
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
  private AccountHolder ah1;
  private AccountHolder ah2;
  private AccountHolder ah3;

  public AccountManager() {
    aml = new AccountManagerLayout(this);
    cbu = new CirculationBalanceUpdater();
    addPanel(cbu.getView());

    ah1 = new AccountHolder(this, "Account 1", ThreadLocalRandom.current().nextInt(100, 200));
    ah2 = new AccountHolder(this, "Account 2", ThreadLocalRandom.current().nextInt(100, 200));
    ah3 = new AccountHolder(this, "Account 3", ThreadLocalRandom.current().nextInt(100, 200));

    // Make them know each other
    ah1.addPartner(ah2.get());
    ah1.addPartner(ah3.get());
    ah2.addPartner(ah1.get());
    ah2.addPartner(ah3.get());
    ah3.addPartner(ah1.get());
    ah3.addPartner(ah2.get());
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(!running) {
      running = true;
      ah1.start();
      ah2.start();
      ah3.start();
    } else {
      running = false;
      ah1.stop();
      ah2.stop();
      ah3.stop();
    }
  }

  public void addPanel(JPanel p) {
    aml.addPanel(p);
  }

  public void publishMessage(String m) {
    //System.out.println(m);
    aml.addMessage(m);
  }

  public void publishState(String m) {
    System.out.println(m);
    aml.addMessage(m);
  }

  public synchronized int getPort(String name) {
    int port = this.port++;
    names.put(port, name);
    return port;
  }

  public String getName(int port) {
    return names.get(port);
  }

  public CirculationBalanceUpdater getCirculationBalanceUpdater() {
    return cbu;
  }
}

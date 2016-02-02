package controller;

import model.Account;
import model.packets.BalanceUpdate;

import java.util.Observable;
import java.util.Observer;

public class BalanceUpdater implements Observer {

  private int port;

  public BalanceUpdater(int port, Account observedAccount) {
    this.port = port;
    observedAccount.addObserver(this);
    update(observedAccount, null);
  }

  @Override
  public void update(Observable o, Object arg) {
    Account account = (Account) o;
    AccountHolder.sendToGui(new BalanceUpdate(port, account.getBalance()));
  }
}

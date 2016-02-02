package controller;

import model.Account;
import view.AccountView;

import java.util.Observable;
import java.util.Observer;

public class BalanceUpdater implements Observer {

  private AccountView accountView;

  public BalanceUpdater(AccountView accountView, Account observedAccount) {
    this.accountView = accountView;

    observedAccount.addObserver(this);
    update(observedAccount, null);
  }

  @Override
  public void update(Observable o, Object arg) {
    Account account = (Account) o;
    accountView.setBalance(account.getBalance());
  }
}

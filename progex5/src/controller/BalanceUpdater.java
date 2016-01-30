package controller;

import view.AccountView;

public class BalanceUpdater {

  private AccountView accountView;

  public BalanceUpdater(AccountView accountView) {
    this.accountView = accountView;
  }

  public void setBalance(int balance) {
    accountView.setBalance(balance);
  }

}

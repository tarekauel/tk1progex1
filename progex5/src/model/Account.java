package model;

import controller.BalanceUpdater;

import java.io.Serializable;

public class Account implements Serializable {

  private int balance;
  private BalanceUpdater bu;

  public Account(int balance, BalanceUpdater bu) {
    this.balance = balance;
    this.bu = bu;
    bu.setBalance(balance);
  }

  public synchronized void transfer(int amount) {
    balance -= amount;
    bu.setBalance(balance);
  }

  public synchronized void receive(int amount) {
    balance += amount;
    bu.setBalance(balance);
  }

  public synchronized int getBalance() {
    return balance;
  }
}

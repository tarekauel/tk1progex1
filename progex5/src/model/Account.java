package model;

import controller.BalanceUpdater;

import java.io.Serializable;
import java.util.Observable;

public class Account extends Observable implements Serializable {

  private int balance;
  private BalanceUpdater bu;

  public Account(int balance, BalanceUpdater bu) {
    this.balance = balance;
    this.bu = bu;
    bu.setBalance(balance);
  }

  public synchronized void transfer(int amount) {
    balance -= amount;
    this.setChanged();
    this.notifyObservers();
    bu.setBalance(balance);
  }

  public synchronized void receive(int amount) {
    balance += amount;
    this.setChanged();
    this.notifyObservers();
    bu.setBalance(balance);
  }

  public synchronized int getBalance() {
    return balance;
  }
}

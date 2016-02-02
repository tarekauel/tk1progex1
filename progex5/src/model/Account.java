package model;


import java.io.Serializable;
import java.util.Observable;

public class Account extends Observable implements Serializable {

  private int balance;

  public Account(int balance) {
    this.balance = balance;
  }

  public synchronized void transfer(int amount) {
    balance -= amount;
    this.setChanged();
    this.notifyObservers();
  }

  public synchronized void receive(int amount) {
    balance += amount;
    this.setChanged();
    this.notifyObservers();
  }

  public synchronized int getBalance() {
    return balance;
  }
}

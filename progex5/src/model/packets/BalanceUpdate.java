package model.packets;

import java.io.Serializable;

/**
 * Created by tarek on 02/02/16.
 */
public class BalanceUpdate implements Serializable {

  private final int port;
  private final int balance;

  public BalanceUpdate(int port, int balance) {
    this.port = port;
    this.balance = balance;
  }

  public int getPort() {
    return port;
  }

  public int getBalance() {
    return balance;
  }
}

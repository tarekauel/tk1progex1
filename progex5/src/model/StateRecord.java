package model;

import packets.Transfer;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class StateRecord implements Serializable {

  private int balance;
  private Queue<Transfer> messages = new LinkedList<>();
  private Set<Partner> receivedMarker = new HashSet<>();

  public StateRecord(int balance) {
    this.balance = balance;
  }

  public void addMessage(Transfer t) {
    messages.add(t);
  }

  public void receivedMarker(Partner p) {
    receivedMarker.add(p);
  }

  public boolean checkReceivedMarker(Partner p) {
    return receivedMarker.contains(p);
  }

  public int receivedMarkerCount() {
    return receivedMarker.size();
  }

  public Queue<Transfer> getMessages() {
    return messages;
  }

  public int getBalance() {
    return balance;
  }
}

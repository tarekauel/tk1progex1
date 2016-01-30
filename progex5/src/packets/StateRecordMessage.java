package packets;

import model.Partner;
import model.StateRecord;

import java.io.Serializable;
import java.util.Queue;

public class StateRecordMessage implements Message, Serializable {

  private int snapshotId;
  private Partner receiver;
  private Partner sender;
  private int balance;
  private Queue<Transfer> pending;

  public StateRecordMessage(int snapshotId, Partner sender, Partner receiver, StateRecord stateRecord) {
    this.snapshotId = snapshotId;
    this.receiver = receiver;
    this.sender = sender;
    this.balance = stateRecord.getBalance();
    this.pending = stateRecord.getMessages();
  }

  @Override
  public Partner getReceiver() {
    return receiver;
  }

  @Override
  public Partner getSender() {
    return sender;
  }

  public int getBalance() {
    return balance;
  }

  public Queue<Transfer> getPending() {
    return pending;
  }

  public int getSnapshotId() {
    return snapshotId;
  }
}

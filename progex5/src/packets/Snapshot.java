package packets;

import model.Partner;

import java.io.Serializable;

public class Snapshot implements Message, Serializable {

    private static int nextSnapshotId = 0;

    private final Partner receiver;
    private final Partner sender;
    private final Partner initiator;
    private final int snapshotId;

    public Snapshot(Partner initiator, Partner sender, Partner receiver) {
        this.sender = sender;
        this.receiver = receiver;
        this.initiator = initiator;
        snapshotId = nextSnapshotId++;
    }

    public Snapshot(Partner initiator, Partner sender, Partner receiver, int snapshotId) {
        this.sender = sender;
        this.receiver = receiver;
        this.initiator = initiator;
        this.snapshotId = snapshotId;
    }

    @Override
    public Partner getReceiver() {
        return receiver;
    }

    public int getSnapshotId() {
        return snapshotId;
    }

    public Partner getInitiator() {
        return initiator;
    }

    @Override
    public Partner getSender() {
        return sender;
    }
}

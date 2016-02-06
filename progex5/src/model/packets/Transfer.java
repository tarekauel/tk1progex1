package model.packets;

import model.Partner;

import java.io.Serializable;

public class Transfer implements Message, Serializable {

    private int amount;
    private Partner receiver;
    private Partner sender;

    public Transfer(int amount, Partner sender, Partner receiver) {
        this.sender = sender;
        this.amount = amount;
        this.receiver = receiver;
    }

    @Override
    public Partner getReceiver() {
        return receiver;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public Partner getSender() {
        return sender;
    }
}

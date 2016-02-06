package model.packets;

import model.Partner;

import java.io.Serializable;

public class Terminate implements Message, Serializable {
    @Override
    public Partner getReceiver() {
        return null;
    }

    @Override
    public Partner getSender() {
        return null;
    }
}

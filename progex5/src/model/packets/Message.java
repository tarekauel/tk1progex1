package model.packets;

import model.Partner;

import java.io.Serializable;

public interface Message extends Serializable {

    Partner getReceiver();

    Partner getSender();

}

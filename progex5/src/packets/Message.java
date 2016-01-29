package packets;

import java.io.Serializable;

abstract public class Message implements Serializable {
    public enum MessageType {
        SNAPSHOT,
        TRANSFER
    }

    public MessageType $msgType;

    public Message(MessageType $msgType) {
        this.$msgType = $msgType;
    }
}

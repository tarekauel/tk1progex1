package packets;

public class Transfer extends Message {
    public double amount;

    public Transfer(double transferAmount) {
        super(MessageType.TRANSFER);
        amount = transferAmount;
    }
}

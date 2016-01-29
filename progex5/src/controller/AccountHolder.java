package controller;

import packets.Transfer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashSet;
import java.util.Set;

public class AccountHolder implements Runnable {
    private static int port = 2000;

    private double balance;
    private DatagramSocket channelIn;
    private Set<AccountHolder> partners;

    public AccountHolder(double accountBalance) {
        try {
            balance = accountBalance;
            channelIn = new DatagramSocket(port++);
            partners = new HashSet<>();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public boolean addPartner(AccountHolder ah) {
        return partners.add(ah);
    }

    private int getPort() {
        return channelIn.getLocalPort();
    }

    public void sendMoney(AccountHolder p, double amount) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(new Transfer(5.0));

            byte[] data = os.toByteArray();
            (new DatagramSocket()).send(new DatagramPacket(data, data.length, InetAddress.getLocalHost(), p.getPort()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
    }
}

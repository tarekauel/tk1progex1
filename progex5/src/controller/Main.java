package controller;

import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        // Create the three account holders with a account balance between 100 and 200.
        AccountHolder ah1 = new AccountHolder(ThreadLocalRandom.current().nextDouble(100.0, 200.0));
        AccountHolder ah2 = new AccountHolder(ThreadLocalRandom.current().nextDouble(100.0, 200.0));
        AccountHolder ah3 = new AccountHolder(ThreadLocalRandom.current().nextDouble(100.0, 200.0));

        // Make them know each other
        ah1.addPartner(ah2);
        ah1.addPartner(ah3);
        ah2.addPartner(ah1);
        ah2.addPartner(ah3);
        ah3.addPartner(ah1);
        ah3.addPartner(ah2);

        // Start trading activity
        (new Thread(ah1)).start();
        (new Thread(ah2)).start();
        (new Thread(ah3)).start();
    }
}

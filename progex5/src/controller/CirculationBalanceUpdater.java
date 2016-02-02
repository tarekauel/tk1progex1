package controller;

import model.Account;
import view.AccountManagerLayout;
import view.CirculationBalanceView;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class CirculationBalanceUpdater {
    private Map<Integer, Integer> balances;
    private CirculationBalanceView view;

    public CirculationBalanceUpdater() {
        this.balances = new HashMap<>();
        this.view = new CirculationBalanceView();
    }

    public void addAccount(int port, int balance) {
        if (!balances.containsKey(port)) {
            balances.put(port, balance);
        }

        view.setUniverseBalance(getTotalAccountBalance());
    }

    private int getTotalAccountBalance() {
        int totalAccountBalance = 0;

        for (int i : balances.keySet()) {
            totalAccountBalance += balances.get(i);
        }

        return totalAccountBalance;
    }

    public void update(int port, int balance) {
        balances.put(port, balance);
        view.setAccountBalance(getTotalAccountBalance());
    }

    public CirculationBalanceView getView() {
        return view;
    }
}

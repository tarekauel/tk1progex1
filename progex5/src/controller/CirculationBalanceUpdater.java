package controller;

import model.Account;
import view.AccountManagerLayout;
import view.CirculationBalanceView;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class CirculationBalanceUpdater implements Observer {
    private Map<Account, Integer> balances;
    private CirculationBalanceView view;

    public CirculationBalanceUpdater() {
        this.balances = new HashMap<>();
        this.view = new CirculationBalanceView();
    }

    public void addAccount(Account account) {
        if (!balances.containsKey(account)) {
            account.addObserver(this);
            balances.put(account, account.getBalance());
        }

        view.setUniverseBalance(getTotalAccountBalance());
    }

    private int getTotalAccountBalance() {
        int totalAccountBalance = 0;

        for (Account a : balances.keySet()) {
            totalAccountBalance += balances.get(a);
        }

        return totalAccountBalance;
    }

    @Override
    public void update(Observable o, Object arg) {
        Account account = (Account) o;
        balances.put(account, account.getBalance());

        view.setAccountBalance(getTotalAccountBalance());
    }

    public CirculationBalanceView getView() {
        return view;
    }
}

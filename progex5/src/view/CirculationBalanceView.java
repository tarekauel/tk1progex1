package view;

import javax.swing.*;

public class CirculationBalanceView extends JPanel {
    private int universeBalance = 0;

    private JLabel universeLabel = new JLabel("0.0 €");
    private JLabel accountLabel = new JLabel("0.0 €");
    private JLabel transitLabel = new JLabel("0.0 €");

    public CirculationBalanceView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel universeMoneyRow = row();
        universeMoneyRow.add(new JLabel("Money in universe: "));
        universeMoneyRow.add(universeLabel);
        this.add(universeMoneyRow);

        JPanel accountMoneyRow = row();
        accountMoneyRow.add(new JLabel("Money on accounts: "));
        accountMoneyRow.add(accountLabel);
        this.add(accountMoneyRow);

        JPanel transitMoneyRow = row();
        transitMoneyRow.add(new JLabel("Money in transit: "));
        transitMoneyRow.add(transitLabel);
        this.add(transitMoneyRow);

        JPanel spaceRow = row();
        spaceRow.add(new JLabel(" "));
        this.add(spaceRow);
    }

    private JPanel row() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        return panel;
    }

    public void setUniverseBalance(int balance) {
        universeBalance = balance;
        universeLabel.setText(String.format("%d.00 €", balance));
    }

    public void setAccountBalance(int balance) {
        accountLabel.setText(String.format("%d.00 €", balance));
        transitLabel.setText(String.format("%d.00 €", universeBalance - balance));
    }
}

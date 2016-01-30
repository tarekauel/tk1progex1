package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AccountView extends JPanel {

  private JLabel balanceLabel = new JLabel("0.0");
  private JButton snapshotButton;

  public AccountView(String title) {

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    JPanel panel = new JPanel();
    this.add(panel);
    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
    panel.add(new JLabel(title + "\t\t\t\t\t"));
    panel.add(balanceLabel);
    snapshotButton = new JButton("Snapshot");

    JPanel southPanel = new JPanel();
    southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
    this.add(southPanel);
    southPanel.add(snapshotButton);
    southPanel.add(new JLabel(" "));
  }

  public void setBalance(int balance) {
    balanceLabel.setText(String.format("%d.00 €", balance));
  }

  public void setSnapshotButtonListener(ActionListener listener) {
    snapshotButton.addActionListener(listener);
  }
}

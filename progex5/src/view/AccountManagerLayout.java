package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class AccountManagerLayout extends JFrame {

  private JTextArea messageBox = new JTextArea();

  private JPanel right = new JPanel();

  public AccountManagerLayout(ActionListener listener) {

    this.setLayout(new BorderLayout());

    messageBox.setColumns(40);
    JScrollPane scrollPane = new JScrollPane(messageBox);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    this.add(scrollPane, BorderLayout.WEST);

    right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
    this.add(right, BorderLayout.EAST);

    JButton start = new JButton("Start / Stop");
    start.addActionListener(listener);
    this.add(start, BorderLayout.SOUTH);

    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setTitle("AccountManager");
    this.setSize(800, 600);

    this.setVisible(true);
  }

  public void addPanel(JPanel av) {
    right.add(av);
  }

  public void addMessage(String message) {
    messageBox.append(message + "\n");
  }
}

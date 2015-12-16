package controllers;

import views.ClientView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendButtonActionListener implements ActionListener {

  private ClientView cw;

  public SendButtonActionListener(ClientView cw) {
    this.cw = cw;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (cw.getMessageTags().noPendingTag()) {
      Client ctl = cw.getCtrl();
      ctl.onMessageSend(
          cw.getMessage().getText(),
          cw.getMessageTags().getTags());
      cw.getMessage().setText("");
      cw.getMessageTags().removeAllTags();
    } else {
      JOptionPane.showMessageDialog(cw, "Either add the tag or clear the input");
    }
  }
}

package views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import components.TagSelect;
import controllers.ClientControllerInterface;

public class ClientView extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private ClientControllerInterface ctrl;
	private JTextArea message;
	private JTextArea receiveLog;
	private TagSelect messageTags;
	private TagSelect subscribedTags;
	private JButton sendMsgBtn;
	
	
	public ClientView(ClientControllerInterface ctrl) {
		this.ctrl = ctrl;
		this.message = new JTextArea(2, 10);
		this.receiveLog = new JTextArea(2, 10);
		this.receiveLog.setEditable(false);
		this.receiveLog.setBackground(Color.LIGHT_GRAY);
		this.messageTags = new TagSelect(false);
		this.subscribedTags = new TagSelect(true);
		this.sendMsgBtn = new JButton("Publish Message");
		this.sendMsgBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClientView.this.ctrl.onMessageSend(
						ClientView.this.message.getText(),
						ClientView.this.messageTags.getTags());
				ClientView.this.message.setText("");
				ClientView.this.messageTags.removeAllTags();
			}
		});
		
		this.initGui();
		//this.pack();
		this.setVisible(true);
	}
	
	private void initGui() {
		JPanel publishPanel = this.initPublishPanel();
		JPanel subscribePanel = this.initSubscribePanel();
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(3, 3, 3, 3);
		
		this.setLayout(new GridBagLayout());
		this.setSize(900, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = .5;
		this.add(publishPanel, c);
		
		c.gridy = 1;
		this.add(subscribePanel, c);
	}
	
	private JPanel initPublishPanel() {
		JPanel publishPanel = new JPanel();
		publishPanel.setLayout(new GridBagLayout());
		publishPanel.setBorder(BorderFactory.createTitledBorder("Publish"));
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(3, 3, 3, 3);
		
		// Left column
		JLabel messageLabel = new JLabel("Message");
		messageLabel.setVerticalAlignment(SwingConstants.TOP);
		c.gridx = 0;
		c.gridy = 0;
		publishPanel.add(messageLabel, c);
		
		JLabel tags = new JLabel("Tags");
		tags.setVerticalAlignment(SwingConstants.TOP);
		c.gridy = 1;
		c.insets.top = 13;
		publishPanel.add(tags, c);
		
		// Right column
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.insets.top = 3;
		publishPanel.add(this.message, c);
		
		c.gridy = 1;
		c.weighty = 0.0;
		publishPanel.add(this.messageTags, c);
		
		c.gridy = 2;
		c.weighty = .0;
		c.anchor = GridBagConstraints.LINE_END;
		publishPanel.add(this.sendMsgBtn, c);
		
		return publishPanel;
	}
	
	private JPanel initSubscribePanel() {
		JPanel subscribePanel = new JPanel();
		subscribePanel.setLayout(new GridBagLayout());
		subscribePanel.setBorder(BorderFactory.createTitledBorder("Subscribe"));
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(3, 3, 3, 3);
		
		// Left column
		JLabel messagesLabel = new JLabel("Messages");
		messagesLabel.setVerticalAlignment(SwingConstants.TOP);
		c.gridx = 0;
		c.gridy = 0;
		subscribePanel.add(messagesLabel, c);
		
		JLabel subscriptionTagsLabel = new JLabel("Subscription Tags");
		subscriptionTagsLabel.setVerticalAlignment(SwingConstants.TOP);
		c.gridy = 1;
		c.insets.top = 13;
		subscribePanel.add(subscriptionTagsLabel, c);
		
		// Right column
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.insets.top = 3;
		subscribePanel.add(this.receiveLog, c);
		
		c.gridy = 1;
		c.weightx = .0;
		c.weighty = .0;
		subscribePanel.add(this.subscribedTags, c);
		
		c.gridy = 2;
		subscribePanel.add(new JLabel("Prepend '@' to a tag to subscribe to users! (Case sensitive!)"), c);
		
		return subscribePanel;
	}
	
	public String chooseNameDialog() {
		String name = JOptionPane.showInputDialog(
				this,
				"Your Name", "Please provide a Name",
				JOptionPane.PLAIN_MESSAGE);
		
		this.setTitle("Microblog - Logged in as " + name);
		return name;
	}
	
	public TagSelect getSubscribedTags() {
		return this.subscribedTags;
	}
	
	public void appendToMessageLog(String text) {
		this.receiveLog.append(text);
	}
}

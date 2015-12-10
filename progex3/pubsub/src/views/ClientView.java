package views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import components.MultiSelect;
import controllers.ClientControllerInterface;

public class ClientView extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private ClientControllerInterface ctrl;
	private JButton sendMsgBtn;
	
	
	public ClientView(ClientControllerInterface ctrl) {
		this.ctrl = ctrl;
		this.sendMsgBtn = new JButton("Publish Message");
		
		this.initGui();
		//this.pack();
		this.setVisible(true);
		//this.chooseNameDialog();
	}
	
	private void initGui() {
		JPanel publishPanel = this.initPublishPanel();
		JPanel subscribePanel = this.initSubscribePanel();
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(3, 3, 3, 3);
		
		this.setLayout(new GridBagLayout());
		this.setSize(500, 600);
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
		publishPanel.add(new JTextArea(2, 10), c);
		
		MultiSelect messageTags = new MultiSelect();
		c.gridy = 1;
		c.weighty = 0.0;
		publishPanel.add(messageTags, c);
		
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
		JTextArea messages = new JTextArea(1, 10);
		messages.setEditable(false);
		messages.setBackground(Color.LIGHT_GRAY);
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.insets.top = 3;
		subscribePanel.add(messages, c);
		
		MultiSelect subscriptionTags = new MultiSelect();
		c.gridy = 1;
		c.weightx = .0;
		c.weighty = .0;
		subscribePanel.add(subscriptionTags, c);
		
		return subscribePanel;
	}
	
	private void chooseNameDialog() {
		String name = JOptionPane.showInputDialog(
				this,
				"Your Name", "Please provide a Name",
				JOptionPane.PLAIN_MESSAGE);
		
		this.setTitle("Microblog - Logged in as " + name);
		this.ctrl.setName(name);
	}
}

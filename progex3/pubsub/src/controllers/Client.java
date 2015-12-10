package controllers;

import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import views.ClientView;

public class Client implements ClientControllerInterface {
	public static void main(String[] args) throws JMSException, NamingException {
		new Client();
	}
	
	private Connection connection;
	private Session session;
	private ClientView view;
	private String name;
	private Map<String, MessageConsumer> subscriptions;
	
	public Client() throws JMSException, NamingException {
		InitialContext ctx = new InitialContext();
		ConnectionFactory cf = (ConnectionFactory) ctx.lookup("ConnectionFactory");
		
		this.connection = cf.createConnection();
		this.connection.start();
		this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		this.view = new ClientView(this);
		this.name = this.view.chooseNameDialog();
		this.subscriptions = new HashMap<String, MessageConsumer>();
		
		this.registerOnSubscriptionChangeCallback();
		
		// Java has no (explicit) destructors? Dont know when to call these.
		//session.close();
		//connection.close();
	}
	
	private void registerOnSubscriptionChangeCallback() {
		this.view.getSubscribedTags().addContainerListener(new ContainerListener() {
			@Override
			public void componentRemoved(ContainerEvent e) {
				Client.this.removeSubscription(e.getChild().toString());
			}
			
			@Override
			public void componentAdded(ContainerEvent e) {
				Client.this.addSubscription(e.getChild().toString());
			}
		});
	}
	
	private void addSubscription(final String tag) {
		if (!this.subscriptions.containsKey(tag)) {
			try {
				Destination d = session.createTopic(tag);
				MessageConsumer consumer = session.createConsumer(d);
				
				consumer.setMessageListener(new MessageListener() {
					@Override
					public void onMessage(Message message) {
						try {
					        if (message instanceof TextMessage) {
					            String text = ((TextMessage) message).getText();
								
					            
								Client.this.view.appendToMessageLog(String.format("[%s] %s",
										tag, text));
								Client.this.view.appendToMessageLog("\n");
					        } else {
					            System.out.println("Received: " + message);
					        }
						} catch (JMSException e) {
							e.printStackTrace();
						}
					}
				});
				
				this.subscriptions.put(tag, consumer);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void removeSubscription(String tag) {
		if (this.subscriptions.containsKey(tag)) {
			try {
				MessageConsumer consumer = this.subscriptions.get(tag);
				consumer.close();
				this.subscriptions.remove(tag);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onMessageSend(String text, String[] tags) {
		this.publish(text, "@" + this.name);
		
		for (String tag : tags) {
			this.publish(text, tag);
		}
	}
	
	private void publish(String text, String topic) {
		try {
			
			Destination d = this.session.createTopic(topic);
			MessageProducer producer = this.session.createProducer(d);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			TextMessage message = session.createTextMessage(text);
			
			producer.send(message);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}

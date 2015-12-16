package controllers;

import views.ClientView;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.util.HashMap;
import java.util.Map;

public class ClientImpl implements Client {

  private Connection connection;
  private Session session;
  private ClientView view;
  private String name;
  private Map<String, MessageConsumer> subscriptions;

  public ClientImpl() throws JMSException, NamingException {
    InitialContext ctx = new InitialContext();
    ConnectionFactory cf = (ConnectionFactory) ctx.lookup("ConnectionFactory");

    this.connection = cf.createConnection();
    this.connection.start();
    this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    this.view = new ClientView(this);
    this.name = this.view.chooseNameDialog();
    this.subscriptions = new HashMap<>();

    this.registerOnSubscriptionChangeCallback();
  }

  public static void main(String[] args) throws JMSException, NamingException {
    new ClientImpl();
  }

  private void registerOnSubscriptionChangeCallback() {
    this.view.getSubscribedTags().addContainerListener(new ContainerListener() {
      @Override
      public void componentRemoved(ContainerEvent e) {
        ClientImpl.this.removeSubscription(e.getChild().toString());
      }

      @Override
      public void componentAdded(ContainerEvent e) {
        ClientImpl.this.addSubscription(e.getChild().toString());
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
                String json = ((TextMessage) message).getText();
                model.Message m = model.Message.fromJson(json);
                ClientImpl.this.view.appendToMessageLog(String.format("[%s] %s",
                    tag, m.getMessage()));
                ClientImpl.this.view.appendToMessageLog("\n");
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
    model.Message m = new model.Message(text, tags, this.name);
    this.publish(m, "@" + this.name);

    for (String tag : tags) {
      this.publish(m, tag);
    }
  }

  private void publish(model.Message m, String topic) {
    try {

      Destination d = this.session.createTopic(topic);
      MessageProducer producer = this.session.createProducer(d);
      producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
      TextMessage message = session.createTextMessage(m.getJson());

      producer.send(message);
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }
}

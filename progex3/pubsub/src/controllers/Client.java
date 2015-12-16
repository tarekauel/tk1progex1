package controllers;

public interface Client {

  void onMessageSend(String message, String[] tags);

}

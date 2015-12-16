package model;

import com.google.gson.Gson;

public class Message {

  private static final Gson gson = new Gson();
  private final String message;
  private final String[] tags;
  private final String user;
  public Message(String message, String[] tags, String user) {
    this.message = message;
    this.tags = tags;
    this.user = user;
  }

  public static Message fromJson(String json) {
    return gson.fromJson(json, Message.class);
  }

  public String getMessage() {
    return message;
  }

  public String[] getTags() {
    return tags;
  }

  public String getUser() {
    return user;
  }

  public String getJson() {
    return gson.toJson(this);
  }
}

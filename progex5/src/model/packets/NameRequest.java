package model.packets;

import java.io.Serializable;

public class NameRequest implements Serializable {

  private final int port;
  private String name;

  public NameRequest(int port) {
    this.port = port;
  }

  public int getPort() {
    return port;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

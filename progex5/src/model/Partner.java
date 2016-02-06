package model;

import java.io.Serializable;

public class Partner implements Serializable {

  private final int port;

  public Partner(int port) {
    this.port = port;
  }

  public int getPort() {
    return port;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Partner partner = (Partner) o;

    return port == partner.port;

  }

  @Override
  public int hashCode() {
    return port;
  }
}

package model.packets;

import model.Partner;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by tarek on 02/02/16.
 */
public class PortRequest implements Serializable {

  private int port;
  private final String name;
  private final int startBalance;
  private ArrayList<Partner> partners;

  public PortRequest(String name, int startBalance) {
    this.name = name;
    this.startBalance = startBalance;
  }

  public ArrayList<Partner> getPartners() {
    return partners;
  }

  public void setPartners(ArrayList<Partner> partners) {
    this.partners = partners;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getName() {
    return name;
  }

  public int getStartBalance() {
    return startBalance;
  }
}

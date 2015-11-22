package services.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Bill {

  public final static Bill NOT_AVAILABLE = new Bill();

  @XmlElement
  private boolean success = false;

  @XmlElement
  private ShoppingCart sc = null;

  public Bill() {}

  public Bill(ShoppingCart sc) {
    this.sc = sc;
    success = true;
  }

  @Override
  public String toString() {
    if (!success) {
      return "Checkout was not successful. Some items may not be available.";
    } else {
      return String.format("Purchase successful:\n %s", sc.toString());
    }
  }
}

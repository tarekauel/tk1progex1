package services.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

@XmlRootElement
public class ShoppingCart {

  private static final Map<String, ShoppingCart> scs = new HashMap<>();

  public static ShoppingCart get(String uuid) {
    return scs.containsKey(uuid) ? scs.get(uuid) : new ShoppingCart(uuid);
  }

  @XmlElement private String uuid;

  @XmlElement private final Map<Product, Integer> items = new HashMap<>();

  public ShoppingCart() {};

  private ShoppingCart(String uuid) {
    this.uuid = uuid;
    scs.put(uuid, this);
  }

  public ShoppingCart set(Product p, int quantity) {
    if (quantity == 0) {
      items.remove(p);
    } else {
      items.put(p, quantity);
    }
    return this;
  }

  public Map<Product, Integer> get() {
    return items;
  }

  @Override
  public String toString() {
    String out = String.format("Shopping cart:\n%10s %8s\n", "Product", "Quantity");
    for (Map.Entry<Product, Integer> e : items.entrySet()) {
      out += String.format("%10s %8d\n", e.getKey().getName(), e.getValue());
    }
    return out;
  }
}

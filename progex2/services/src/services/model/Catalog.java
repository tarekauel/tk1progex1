package services.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

@XmlRootElement
public class Catalog {

  private static Catalog ref;

  public static void create(Item[] items) {
    ref = new Catalog(items);
  }

  public static Catalog get() {
    return ref;
  }

  @XmlElement private Map<Product, Item> items = new HashMap<>();

  public Catalog() {
  }

  private Catalog(Item[] i) {
    for (Item item : i) {
      items.put(item.getProduct(), item);
    }
  }

  public Item get(String name) {
    return items.get(Product.getProduct(name));
  }

  public synchronized boolean purchase(ShoppingCart sc) {
    for (Map.Entry<Product, Integer> e : sc.get().entrySet()) {
      if (ref.get(e.getKey().getName()).getQuantity() < e.getValue()) {
        return false;
      }
    }

    for (Map.Entry<Product, Integer> e : sc.get().entrySet()) {
      ref.get(e.getKey().getName()).reduce(e.getValue());
    }
    return true;
  }

  @Override
  public String toString() {
    String out = "Catalog:\n";
    for(Item i : items.values()) {
      out += String.format("Product:\t%s\n", i.getProduct().getName());
    }
    return out += "\n";
  }
}

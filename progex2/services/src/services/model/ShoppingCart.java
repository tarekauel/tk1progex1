package services.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement
public class ShoppingCart {

  private static final Map<String, ShoppingCart> scs = new HashMap<>();
  @XmlElement
  private final List<ShoppingCartItem> items = new ArrayList<>();
  @XmlElement
  private String uuid;
  private ShoppingCart() {
  }

  private ShoppingCart(String uuid) {
    this.uuid = uuid;
    scs.put(uuid, this);
  }

  public static ShoppingCart get(String uuid) {
    return scs.containsKey(uuid) ? scs.get(uuid) : new ShoppingCart(uuid);
  }

  public ShoppingCart set(Product p, int quantity) {
    if (quantity == 0) {
      items.remove(new ShoppingCartItem(p, 0));
    } else {
      ShoppingCartItem sci = new ShoppingCartItem(p, quantity);
      if (items.contains(sci)) {
        int qOld = items.get(items.indexOf(sci)).getQuantity();
        if (qOld + quantity <= 0) {
          items.remove(items.indexOf(sci));
        } else {
          items.set(items.indexOf(sci), new ShoppingCartItem(p, qOld + quantity));
        }
      } else {
        if(quantity>0){
        	items.add(sci);
        }
      }
    }

    return this;
  }

  public List<ShoppingCartItem> get() {
    return items;
  }

  public synchronized String checkout() {
    Stock stock = Stock.get();

    for (ShoppingCartItem e : items) {
      int instock = stock.get(e.getProduct().getId()).getQuantity();

      if (instock == 0) {
        return String.format("1" + e.getProduct().getName());
      } else if (instock < e.getQuantity()) {
    	  int count = String.valueOf(instock).length();
    	  return String.format("2" + count + instock + e.getProduct().getName());
      }
    }

    for (ShoppingCartItem e : items) {
      stock.get(e.getProduct().getId()).reduce(e.getQuantity());
    }

    items.clear();
    return "0";
  }
}

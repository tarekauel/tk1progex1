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

  private ShoppingCart() {}
  private ShoppingCart(String uuid) {
    this.uuid = uuid;
    scs.put(uuid, this);
  }

  public ShoppingCart set(Product p, int quantity) {
    if (quantity == 0) {
      items.remove(p);
    } else {
    	int q = items.containsKey(p) ? items.get(p) : 0;
    	items.put(p, q + quantity);
    }
    
    return this;
  }

  public Map<Product, Integer> get() {
    return items;
  }
  
  public synchronized String checkout() {
	  Stock stock = Stock.get();
	  
	  for (Map.Entry<Product, Integer> e : items.entrySet()) {
		  int instock = stock.get(e.getKey().getId()).getQuantity();
		  
		  if (instock == 0) {
			  return String.format("Out of stock: %s", e.getKey().getName());
		  } else if (instock < e.getValue()) {
			  return String.format("You cannot purchase more than %d of %s", instock, e.getKey().getName());
		  }
	  }
	  
	  for (Map.Entry<Product, Integer> e : items.entrySet()) {
		  stock.get(e.getKey().getId()).reduce(e.getValue());
	  }
	  
	  items.clear();
	  return "";
  }
}

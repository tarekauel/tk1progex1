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

  public synchronized CheckoutResponse checkout() {
    Stock stock = Stock.get();
    CheckoutResponse response = new CheckoutResponse();

    for (ShoppingCartItem e : items) {
      int instock = stock.get(e.getProduct().getId()).getQuantity();

      if (instock == 0) {
    	response.code = CheckoutResponse.FAILURE;
    	response.message =	String.format("We are sorry! %s is out of stock!", e.getProduct().getName());
    	return response;
      } else if (instock < e.getQuantity()) {
    	response.code = CheckoutResponse.FAILURE;
    	response.message = String.format("Too bad, we are missing %d items of %s. (In Stock: %d, Ordered: %d)",
    			e.getQuantity() - instock,
    			e.getProduct().getName(),
    			instock,
    			e.getQuantity());
    	return response;
      }
    }
    
    double checkoutValue = .0;
    for (ShoppingCartItem e : items) {
      checkoutValue += e.getProduct().getPrice() * e.getQuantity();
      stock.get(e.getProduct().getId()).reduce(e.getQuantity());
    }

    items.clear();
    
    response.code = CheckoutResponse.SUCCESS;
    response.message = String.format("Checkout successful! Your credit card is charged with %.2f EURO", checkoutValue);
    return response;
  }
  
  public static class CheckoutResponse {
	public static final int SUCCESS = 200;
	public static final int FAILURE = 300;
	  
	public int code;
	public String message = "";
  }
}

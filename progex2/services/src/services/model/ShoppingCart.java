package services.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

@XmlRootElement
public class ShoppingCart {

  private static final Map<String, ShoppingCart> scs = new HashMap<>();

  public static ShoppingCart get(String uuid) {
    return scs.containsKey(uuid) ? scs.get(uuid) : new ShoppingCart(uuid);
  }

  private final String uuid;

  private ShoppingCart(String uuid) {
    this.uuid = uuid;
    scs.put(uuid, this);
  }
}

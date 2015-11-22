package services.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

@XmlRootElement
public class Product {

  private static Map<String, Product> list = new HashMap<>();

  public static Product getProduct(String name) {
    return (list.containsKey(name)) ? list.get(name) : new Product(name);
  }

  @XmlElement
  private String name;

  public Product() {};

  private Product(String name) {
    this.name = name;
    list.put(name, this);
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Product " + name;
  }
}

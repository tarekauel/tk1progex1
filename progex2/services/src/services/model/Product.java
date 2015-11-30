package services.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product {
  private static Product[] list = {
		  new Product(0, "Lampe", 19.99),
		  new Product(1, "Notebook", 199.99),
		  new Product(2, "Auto", 20000.00)
  };

  public static Product getProduct(int id) {
	  return id < list.length ? list[id] : null;
  }
  
  @XmlElement
  private int id;
  @XmlElement
  private String name;
  @XmlElement
  private double price;
  
  private Product() {}
  private Product(int id, String name, double price) {
	this.id = id;
    this.name = name;
    this.price = price;
  }
  
  public int getId() {
	  return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return String.format("%s (%.2f )", name, price);
  }
}

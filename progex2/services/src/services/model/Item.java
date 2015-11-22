package services.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Item {

  @XmlElement private Product product;
  @XmlElement private double price;
  @XmlElement private int quantity;

  public Item() {}

  public Item(Product product, double price, int quantity) {
    this.product = product;
    this.price = price;
    this.quantity = quantity;
  }

  public Product getProduct() {
    return product;
  }

  public double getPrice() {
    return price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void reduce(int q) {
    quantity -= q;
  }

  @Override
  public String toString() {
    return
        String.format("%5s %10s %10s\n", "Product", "Price", "Qty.") +
        String.format("%5s %4f %10d", product.getName(), getPrice(), getQuantity());
  }
}
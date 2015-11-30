package services.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StockItem {

  @XmlElement private Product product;
  @XmlElement private int quantity;

  public StockItem() {}

  public StockItem(Product product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  public Product getProduct() {
    return product;
  }

  public int getQuantity() {
    return quantity;
  }

  public void reduce(int q) {
    quantity -= q;
  }
}
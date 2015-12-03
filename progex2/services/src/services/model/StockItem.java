package services.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StockItem {

  @XmlElement
  private int productId;

  @XmlElement
  private int quantity;

  public StockItem() {
  }

  public StockItem(Product product, int quantity) {
    this.productId = product.getId();
    this.quantity = quantity;
  }

  public Product getProduct() {
    return Product.getProduct(productId);
  }

  public int getQuantity() {
    return quantity;
  }

  public void reduce(int q) {
    quantity -= q;
  }
}
package services.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ShoppingCartItem {

  @XmlElement
  private Product product;
  @XmlElement
  private int quantity;

  private ShoppingCartItem() {
  }

  public ShoppingCartItem(Product product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  public Product getProduct() {
    return product;
  }

  public int getQuantity() {
    return quantity;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof ShoppingCartItem && ((ShoppingCartItem) obj).product == product;
  }
}

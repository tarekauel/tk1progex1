package services.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ShoppingCartItem {

  @XmlElement
  private int productId;
  @XmlElement
  private int quantity;

  private ShoppingCartItem() {
  }

  public ShoppingCartItem(Product product, int quantity) {
    this.productId = product.getId();
    this.quantity = quantity;
  }

  public Product getProduct() {
    return Product.getProduct(productId);
  }

  public int getQuantity() {
    return quantity;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof ShoppingCartItem && ((ShoppingCartItem) obj).productId == productId;
  }
}

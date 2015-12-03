package model;

public class CartItem {

  private int productId;
  private String productDescription;
  private double price;
  private int quantity;

  public CartItem(int productId, String productDesciription, double price, int quantity) {
    this.productId = productId;
    this.productDescription = productDesciription;
    this.price = price;
    this.quantity = quantity;
  }

  public int getProductId() {
    return productId;
  }

  public String getProduct() {
    return productDescription;
  }

  public double getPrice() {
    return price;
  }

  public int getQuantity() {
    return quantity;
  }
}
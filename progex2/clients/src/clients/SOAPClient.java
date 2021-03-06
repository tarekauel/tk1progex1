package clients;

import model.CartItem;
import soap.stub.*;

import java.util.ArrayList;
import java.util.List;

public class SOAPClient extends Client<StockItem> {
  private static WebShopService wsr = new WebShopService_Service().getWebShopServicePort();

  public SOAPClient() {
    super("SOAP Client");
  }

  public static void main(String[] args) {
    new SOAPClient();
  }

  @Override
  public void checkoutImpl() {
	CheckoutResponse rsp = wsr.checkout(getUuid());
    getLayout().setInfoLabel(rsp.getMessage());
    ShoppingCart shoppingCart = wsr.getShoppingCart(getUuid());
    updateShoppingCart(shoppingCart);
  }

  @Override
  protected List<ComboBoxObject<StockItem>> getLatestProductList() {
    List<ComboBoxObject<StockItem>> list = new ArrayList<>();
    for (StockItem s : wsr.getAllStockItems().getItem()) {
      list.add(new StubComboBoxObject(s));
    }
    return list;
  }

  @Override
  protected List<CartItem> addProduct(StockItem i, int quantity) {
    Product p = wsr.getProduct(i.getProductId());
    ShoppingCart sc = wsr.putProduct(getUuid(), p.getId(), quantity);
    return updateShoppingCart(sc);
  }

  private List<CartItem> updateShoppingCart(ShoppingCart sc) {
    List<CartItem> cartItems = new ArrayList<>();
    for (ShoppingCartItem sci : sc.getItems()) {
      Product p = wsr.getProduct(sci.getProductId());
      cartItems.add(new CartItem(p.getId(), p.getName(), p.getPrice(), sci.getQuantity()));
    }

    return cartItems;
  }

  @Override
  protected List<CartItem> refreshShoppingCart() {
    return updateShoppingCart(wsr.getShoppingCart(getUuid()));
  }

  static class StubComboBoxObject implements ComboBoxObject<StockItem> {

    private final StockItem item;

    public StubComboBoxObject(StockItem item) {
      this.item = item;
    }

    @Override
    public StockItem getObject() {
      return item;
    }

    @Override
    public String toString() {
      Product p = wsr.getProduct(item.getProductId());
      return String.format("%s (Price: %.2f Euro, In stock: %d)",
          p.getName(),
          p.getPrice(),
          item.getQuantity());
    }
  }
}

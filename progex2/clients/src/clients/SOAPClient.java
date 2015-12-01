package clients;

import model.CartItem;
import soap.stub.*;
import views.ComboBoxObject;
import utility.StringFormatter;

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
    getLayout().setInfoLabel(StringFormatter.formatCheckoutResult(wsr.checkout(getUuid())));
    ShoppingCart shoppingCart = wsr.getShoppingCart(getUuid());
    updateShoppingCart(shoppingCart);
  }

  @Override
  protected List<ComboBoxObject<StockItem>> getLatestProductList() {
    List<ComboBoxObject<StockItem>> list = new ArrayList<>();
    for (StockItem s : wsr.getStock().getItems()) {
      list.add(new StubComboBoxObject(s));
    }
    return list;
  }

  @Override
  protected List<CartItem> addProduct(StockItem i, int quantity) {
    Product p = i.getProduct();
    ShoppingCart sc = wsr.putProduct(getUuid(), p.getId(), quantity);
    return updateShoppingCart(sc);
  }

  private List<CartItem> updateShoppingCart(ShoppingCart sc) {
    List<CartItem> cartItems = new ArrayList<>();
    for (ShoppingCartItem sci : sc.getItems()) {
      cartItems.add(new CartItem(sci.getProduct().getId(), sci.getProduct().getName(),
          sci.getProduct().getPrice(), sci.getQuantity()));
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
      return String.format("%s (Price: %.2f Euro, In stock: %d)",
          item.getProduct().getName(),
          item.getProduct().getPrice(),
          item.getQuantity());
    }
  }
}

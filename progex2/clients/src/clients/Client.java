package clients;

import model.CartItem;
import model.CartTableModel;
import views.ClientLayout;

import java.util.List;
import java.util.UUID;

abstract public class Client<T> {

  private final String uuid = UUID.randomUUID().toString();
  private final ClientLayout layout;

  public Client(String name) {
    this.layout = new ClientLayout(this, name);

    refreshProductList();
    layout.setVisible(true);
  }

  public final String getUuid() {
    return uuid;
  }

  public final void onAddProduct(T o, int quantity) {
    if (quantity == 0) {
      System.err.println("Quantity was zero");
      layout.setInfoLabel("Quantity has to be not equal to 0;");
      return;
    }

    refreshCartView(addProduct(o, quantity));
  }

  public ClientLayout getLayout() {
    return layout;
  }

  public void refreshProductList() {
    layout.removeAllProducts();
    for (ComboBoxObject<T> obj : getLatestProductList()) {
      layout.addProduct(obj);
    }
  }

  public final void onCheckout() {
    checkoutImpl();
    refreshProductList();
    refreshCartView(refreshShoppingCart());
  }

  private void refreshCartView(List<CartItem> items) {
    layout.setCartModel(new CartTableModel(items.toArray(new CartItem[items.size()])));
  }

  abstract protected void checkoutImpl();

  abstract protected List<ComboBoxObject<T>> getLatestProductList();

  abstract protected List<CartItem> addProduct(T o, int quantity);

  abstract protected List<CartItem> refreshShoppingCart();
}

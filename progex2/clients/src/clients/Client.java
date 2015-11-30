package clients;

import views.ClientLayout;
import views.ComboBoxObject;

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

    addProduct(o, quantity);
  }

  public ClientLayout getLayout() {
    return layout;
  }

  private void refreshProductList() {
    layout.removeAllProducts();
    for(ComboBoxObject obj : getLatestProductList()) {
      layout.addProduct(obj);
    }
  }

  abstract public void onCheckout();

  abstract protected List<ComboBoxObject<T>> getLatestProductList();

  abstract protected void addProduct(T o, int quantity);
}

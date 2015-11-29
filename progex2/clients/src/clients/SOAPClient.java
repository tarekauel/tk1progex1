package clients;

import soap.stub.Product;
import soap.stub.ShoppingCart;
import soap.stub.Stock;
import soap.stub.StockItem;
import soap.stub.WebShopService;
import soap.stub.WebShopService_Service;

import views.ClientLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.CartItem;
import models.CartTableModel;

public class SOAPClient implements ClientController {
  private static WebShopService wsr = new WebShopService_Service().getWebShopServicePort();
  
  private String uuid;
  private ClientLayout window;
  
  public static void main(String[] args) {
	  new SOAPClient();
  }
  
  public SOAPClient() {
	this.uuid = UUID.randomUUID().toString();
	this.window = new ClientLayout(this, "SOAP Client");
	
	refreshProductList();
    window.setVisible(true);
  }
  
  private void refreshProductList() {
	  Stock stock = wsr.getStock();
	  
	  window.removeAllProducts();
	  for (Stock.Items.Entry stockEntry : stock.getItems().getEntry()) {
		  window.addProduct(new ComboBoxStockItem(stockEntry.getValue()));
	  }
  }
  
  private class ComboBoxStockItem implements ComboBoxObject {
	  StockItem i;
	  
	  public ComboBoxStockItem(StockItem i) {
		this.i = i;
	}
	  
	  @Override
	public Object getObject() {
		return i;
	}
	  
	  @Override
	public String toString() {
		return String.format("%s (Price: %.2f Euro, In stock: %d)",
				  i.getProduct().getName(),
				  i.getProduct().getPrice(),
				  i.getQuantity());
	}
  }
  
  private void updateCartItems(ShoppingCart shoppingCart) {
	  List<CartItem> cartItems = new ArrayList<CartItem>();
	  for (ShoppingCart.Items.Entry e : shoppingCart.getItems().getEntry()) {
		  cartItems.add(new CartItem(e.getKey().getId(), e.getKey().getName(), e.getKey().getPrice(), e.getValue()));
	  }
	  
	  CartTableModel ctm = new CartTableModel(cartItems.toArray(new CartItem[0]));
	  window.setCartModel(ctm);
  }
  
  @Override
  public void onAddProduct(Object o, int quantity) {
	  if (quantity <= 0) {
		  window.setInfoLabel("The quantity must not be greater than 0!");
		  return;
	  }
	  
	  StockItem i = (StockItem) o;
	  Product p = i.getProduct();
	  ShoppingCart shoppingCart = wsr.putProduct(uuid, p.getId(), quantity);
	  
	  this.updateCartItems(shoppingCart);
	  window.setInfoLabel(String.format("Added product to cart: %s (x%d)", p.getName(), quantity));
  }
  
  @Override
  public void onCheckout() {
	  window.setInfoLabel(wsr.checkout(uuid));
	  ShoppingCart shoppingCart = wsr.getShoppingCart(uuid);
	  updateCartItems(shoppingCart);
	  refreshProductList();
  }

  @Override
  public String getUUID() {
	return uuid;
  }
}

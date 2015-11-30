package services.soap;

import services.model.Product;
import services.model.ShoppingCart;
import services.model.Stock;
import services.model.StockItem;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "WebShopService")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class WebShop {
  @WebMethod
  public Stock getStock() {
    return Stock.get();
  }

  @WebMethod
  public StockItem getStockItem(int productId) {
    return Stock.get().get(productId);
  }

  @WebMethod
  public String checkout(String uuid) {
    String errorMsg = ShoppingCart.get(uuid).checkout();

    if (errorMsg.isEmpty()) {
      return "Checkout successful!";
    } else {
      return errorMsg;
    }
  }

  @WebMethod
  public Product getProduct(int productId) {
    return Product.getProduct(productId);
  }

  @WebMethod
  public ShoppingCart getShoppingCart(String uuid) {
    return ShoppingCart.get(uuid);
  }

  @WebMethod
  public ShoppingCart putProduct(String uuid, int productId, int qty) {
    ShoppingCart sc = ShoppingCart.get(uuid);
    Product p = Product.getProduct(productId);

    return sc.set(p, qty);
  }

  @WebMethod
  public ShoppingCart deleteProduct(String uuid, int productId) {
    return putProduct(uuid, productId, 0);
  }

}

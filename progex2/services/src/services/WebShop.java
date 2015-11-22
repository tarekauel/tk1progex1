package services;

import services.model.Bill;
import services.model.Catalog;
import services.model.Product;
import services.model.ShoppingCart;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name="WebShopService")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class WebShop {

  @WebMethod
  public String getCatalog() {
    return Catalog.get().toString();
  }

  @WebMethod
  public String getCatalogItem(String productName) {
    return Catalog.get().get(productName).toString();
  }

  @WebMethod
  public String checkout(String uuid) {
    if (Catalog.get().purchase(ShoppingCart.get(uuid))) {
      return new Bill(ShoppingCart.get(uuid)).toString();
    } else {
      return Bill.NOT_AVAILABLE.toString();
    }
  }

  @WebMethod
  public String getShoppingCart(String uuid) {
    return ShoppingCart.get(uuid).toString();
  }

  @WebMethod
  public String putProduct(String uuid, String product, int qty) {
    return ShoppingCart.get(uuid).set(Product.getProduct(product), qty).toString();
  }

  @WebMethod
  public String deleteProduct(String uuid, String product) {
    return putProduct(uuid, product, 0);
  }

}

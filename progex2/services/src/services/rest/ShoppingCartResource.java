package services.rest;

import services.model.Bill;
import services.model.Catalog;
import services.model.Product;
import services.model.ShoppingCart;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("cart")
public class ShoppingCartResource {

  @POST
  @Path("checkout/{uuid}")
  @Produces(MediaType.TEXT_PLAIN)
  public String checkout(@PathParam("uuid") String uuid) {
    if (Catalog.get().purchase(ShoppingCart.get(uuid))) {
      return new Bill(ShoppingCart.get(uuid)).toString();
    } else {
      return Bill.NOT_AVAILABLE.toString();
    }
  }

  @GET
  @Path("{uuid}")
  @Produces(MediaType.TEXT_PLAIN)
  public String getShoppingCart(@PathParam("uuid") String uuid) {
    return ShoppingCart.get(uuid).toString();
  }

  @PUT
  @Path("{uuid}/{product}")
  @Produces(MediaType.TEXT_PLAIN)
  public String putProduct(@PathParam("uuid") String uuid,
                           @PathParam("product") String product,
                           @DefaultValue("1") @QueryParam("qty") String qty     ) {
    return ShoppingCart.get(uuid).set(Product.getProduct(product), Integer.parseInt(qty)).toString();
  }

  @DELETE
  @Path("{uuid}/{product}")
  @Produces(MediaType.TEXT_PLAIN)
  public String removeProduct(@PathParam("uuid") String uuid,
                                 @PathParam("product") String product) {
    return ShoppingCart.get(uuid).set(Product.getProduct(product), 0).toString();
  }
}

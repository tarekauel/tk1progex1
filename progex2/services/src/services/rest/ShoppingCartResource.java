package services.rest;

import services.model.Product;
import services.model.ShoppingCart;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("cart")
public class ShoppingCartResource {

  @POST
  @Path("checkout/{uuid}")
  @Produces(MediaType.APPLICATION_JSON)
  public String checkout(@PathParam("uuid") String uuid) {
	  return ShoppingCart.get(uuid).checkout();
  }

  @GET
  @Path("{uuid}")
  @Produces(MediaType.APPLICATION_JSON)
  public ShoppingCart getShoppingCart(@PathParam("uuid") String uuid) {
    return ShoppingCart.get(uuid);
  }

  @PUT
  @Path("{uuid}/{productId}")
  @Produces(MediaType.APPLICATION_JSON)
  public ShoppingCart putProduct(@PathParam("uuid") String uuid,
                           @PathParam("productId") int productId,
                           @DefaultValue("1") @QueryParam("qty") String qty) {
    return ShoppingCart.get(uuid).set(Product.getProduct(productId), Integer.parseInt(qty));
  }

  @DELETE
  @Path("{uuid}/{productId}")
  @Produces(MediaType.APPLICATION_JSON)
  public ShoppingCart removeProduct(@PathParam("uuid") String uuid,
                                 @PathParam("productId") int productId) {
    return ShoppingCart.get(uuid).set(Product.getProduct(productId), 0);
  }
}

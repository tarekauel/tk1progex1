package services.rest;

import com.google.gson.Gson;
import services.model.Product;
import services.model.ShoppingCart;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("cart")
public class ShoppingCartResource {

  private static final Gson gson = new Gson();

  @POST
  @Path("checkout/{uuid}")
  @Produces(MediaType.APPLICATION_JSON)
  public String checkout(@PathParam("uuid") String uuid) {
    return gson.toJson(ShoppingCart.get(uuid).checkout());
  }

  @GET
  @Path("{uuid}")
  @Produces(MediaType.APPLICATION_JSON)
  public String getShoppingCart(@PathParam("uuid") String uuid) {
    return gson.toJson(ShoppingCart.get(uuid));
  }

  @PUT
  @Path("{uuid}/{productId}")
  @Produces(MediaType.APPLICATION_JSON)
  public String putProduct(@PathParam("uuid") String uuid,
                           @PathParam("productId") int productId,
                           @DefaultValue("1") @QueryParam("qty") String qty) {
    return gson.toJson(ShoppingCart.get(uuid).set(Product.getProduct(productId), Integer.parseInt(qty)));
  }

  @DELETE
  @Path("{uuid}/{productId}")
  @Produces(MediaType.APPLICATION_JSON)
  public String removeProduct(@PathParam("uuid") String uuid,
                              @PathParam("productId") int productId) {
    return gson.toJson(ShoppingCart.get(uuid).set(Product.getProduct(productId), 0));
  }
}

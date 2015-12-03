package services.rest;

import com.google.gson.Gson;
import services.model.Product;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("product")
public class ProductResource {

  private static final Gson gson = new Gson();

  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public String getShoppingCart(@PathParam("id") String id) {
    return gson.toJson(Product.getProduct(Integer.parseInt(id)));
  }

}

package services.rest;

import com.google.gson.Gson;
import services.model.Stock;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("stock")
public class StockResource {

  private static final Gson gson = new Gson();

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String get() {
    return gson.toJson(Stock.get());
  }
}

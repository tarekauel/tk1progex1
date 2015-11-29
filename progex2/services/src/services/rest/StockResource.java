package services.rest;

import services.model.Stock;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("stock")
public class StockResource {
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Stock get() {
    return Stock.get();
  }
}

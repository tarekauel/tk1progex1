package services.rest;

import services.model.Catalog;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("catalog")
public class CatalogResource {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String get() {
    return Catalog.get().toString();
  }

  @GET
  @Path("{name}")
  @Produces(MediaType.TEXT_PLAIN)
  public String get(@PathParam("name") String name) {
    return Catalog.get().get(name).toString();
  }

}

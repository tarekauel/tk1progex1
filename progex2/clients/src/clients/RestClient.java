package clients;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import views.ComboBoxObject;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class RestClient extends clients.Client<JsonObject> {

  private static final JsonParser jsonParser = new JsonParser;

  private static WebResource wr = Client.create().resource( "http://localhost:8080" );

  private static String uuid = UUID.randomUUID().toString();

  public RestClient() {
    super("REST-Client");
  }

  @Override
  public void onCheckout() {

  }

  @Override
  protected List<ComboBoxObject<JsonObject>> getLatestProductList() {
    JsonArray array = jsonParser
        .parse(wr
            .path("stock")
            .accept(MediaType.APPLICATION_JSON)
            .get(String.class))
        .getAsJsonObject().get("items").getAsJsonArray();

    List<ComboBoxObject<JsonObject>> result = new ArrayList<>();

    for(JsonElement e : array) {
      result.add(new JsonComboBoxItem(e.getAsJsonObject()));
    }

    return result;
  }

  @Override
  protected void addProduct(JsonObject o, int quantity) {

  }

  static class JsonComboBoxItem implements ComboBoxObject<JsonObject> {

    private final JsonObject obj;

    public JsonComboBoxItem(JsonObject obj) {
      this.obj = obj;
    }

    @Override
    public JsonObject getObject() {
      return obj;
    }

    @Override
    public String toString() {
      return String.format("%s (Price: %.2f Euro, In stock: %d)",
          obj.getAsJsonObject("product").get("name").getAsString(),
          obj.getAsJsonObject("product").get("price").getAsDouble(),
          obj.get("quantity").getAsInt());
    }
  }
}

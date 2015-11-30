package clients;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import model.CartItem;
import views.ComboBoxObject;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

public class RestClient extends clients.Client<JsonObject> {

  private static final JsonParser jsonParser = new JsonParser();

  private static WebResource wr = Client.create().resource("http://localhost:8080");

  public RestClient() {
    super("REST-Client");
  }

  public static void main(String[] args) {
    new RestClient();
  }

  @Override
  public void checkoutImpl() {
    String response = jsonParser
        .parse(
            wr.path("cart").path("checkout").path(getUuid())
                .accept(MediaType.APPLICATION_JSON).post(String.class))
        .getAsString();

    getLayout().setInfoLabel(response);
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

    for (JsonElement e : array) {
      result.add(new JsonComboBoxItem(e.getAsJsonObject()));
    }

    return result;
  }

  @Override
  protected List<CartItem> addProduct(JsonObject o, int quantity) {
    JsonArray array = jsonParser
        .parse(wr
            .path("cart").path(getUuid())
            .path(o.getAsJsonObject("product").get("id").getAsString())
            .queryParam("qty", quantity + "")
            .accept(MediaType.APPLICATION_JSON)
            .put(String.class))
        .getAsJsonObject()
        .getAsJsonArray("items");

    List<CartItem> result = new ArrayList<>();
    for (JsonElement e : array) {
      JsonObject product = e.getAsJsonObject().getAsJsonObject("product");
      result.add(new CartItem(product.get("id").getAsInt(), product.get("name").getAsString(),
          product.get("price").getAsDouble(), e.getAsJsonObject().get("quantity").getAsInt()));
    }
    return result;
  }

  @Override
  protected List<CartItem> refreshShoppingCart() {
    JsonArray array = jsonParser
        .parse(wr
            .path("cart").path(getUuid()).accept(MediaType.APPLICATION_JSON)
            .get(String.class))
        .getAsJsonObject()
        .getAsJsonArray("items");

    List<CartItem> result = new ArrayList<>();
    for (JsonElement e : array) {
      JsonObject product = e.getAsJsonObject().getAsJsonObject("product");
      result.add(new CartItem(product.get("id").getAsInt(), product.get("name").getAsString(),
          product.get("price").getAsDouble(), e.getAsJsonObject().get("quantity").getAsInt()));
    }
    return result;
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

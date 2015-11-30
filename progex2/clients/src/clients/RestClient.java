package clients;

import com.google.gson.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import models.CartItem;
import models.CartTableModel;
import soap.stub.ShoppingCart;
import views.ClientLayout;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RestClient implements ClientController {
  private static WebResource wr = Client.create().resource("http://localhost:8080");

  private String uuid;
  private ClientLayout window;
  private static final JsonParser json = new JsonParser();

  public RestClient() {
    this.uuid = UUID.randomUUID().toString();
    this.window = new ClientLayout(this, "REST Client");

    refreshProductList();
    window.setVisible(true);
  }

  public static void main(String[] args) {
    new RestClient();
  }

  private void refreshProductList() {
    JsonObject stock = json.parse(wr.path("stock")
        .accept(MediaType.APPLICATION_JSON)
        .get(String.class)).getAsJsonObject();

    window.removeAllProducts();
    for (JsonElement e : stock.get("items").getAsJsonArray()) {
      window.addProduct(new ComboBoxStockItem(e.getAsJsonObject()));
    }
  }

  @Override
  public String getUUID() {
    return uuid;
  }

  @Override
  public void onAddProduct(Object o, int quantity) {
    if (quantity == 0) {
      window.setInfoLabel("The quantity must not be not equals 0!");
      return;
    }

    JsonObject stockEntry = (JsonObject) o;
    JsonObject product = stockEntry.get("product").getAsJsonObject();
    String productId = product.get("id").getAsString();

    String jsonResponse = wr.path("cart")
        .path(uuid)
        .path(productId)
        .queryParam("qty", quantity + "")
        .accept(MediaType.APPLICATION_JSON)
        .put(String.class);

    JsonArray cartItems = json.parse(jsonResponse).getAsJsonObject()
        .get("items").getAsJsonArray();

    List<CartItem> cart = new ArrayList<>();

    for (JsonElement elem : cartItems) {
      JsonObject obj = elem.getAsJsonObject();
      cart.add(new CartItem(obj.get("")))
    }

    CartTableModel ctm = new CartTableModel()



    System.out.println(jsonResponse);
  }

  @Override
  public void onCheckout() {
    return;
  }

  private class ComboBoxStockItem implements ComboBoxObject {
    JsonObject stockEntry;

    public ComboBoxStockItem(JsonObject stockEntry) {
      this.stockEntry = stockEntry;
    }

    @Override
    public Object getObject() {
      return stockEntry;
    }

    @Override
    public String toString() {
      JsonObject product = stockEntry.get("product").getAsJsonObject();

      return String.format("%s (Price: %.2f Euro, In stock: %d)",
          product.get("name").getAsString(),
          product.get("price").getAsDouble(),
          stockEntry.get("quantity").getAsInt());
    }
  }
}

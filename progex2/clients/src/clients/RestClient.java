package clients;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import views.ClientLayout;

import java.util.UUID;

import javax.ws.rs.core.MediaType;

public class RestClient implements ClientController {
  private static WebResource wr = Client.create().resource( "http://localhost:8080" );
  
  private String uuid;
  private ClientLayout window;
  private JsonParser json;
  
  public static void main(String[] args) {
	  new RestClient();
  }
  
  public RestClient() {
	this.uuid = UUID.randomUUID().toString();
	this.window = new ClientLayout(this, "REST Client");
	this.json = new JsonParser();

	refreshProductList();
    window.setVisible(true);
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
  
  private void refreshProductList() {
	  String plainResponse = wr.path("stock").accept(MediaType.APPLICATION_JSON).get(String.class);

	  JsonObject response = json.parse(plainResponse).getAsJsonObject();
	  JsonArray entries = response.get("items").getAsJsonObject().get("entry").getAsJsonArray();
	  
	  window.removeAllProducts();
	  for (JsonElement e : entries) {
		  window.addProduct(new ComboBoxStockItem(e.getAsJsonObject().get("value").getAsJsonObject()));
	  }
  }
  
  @Override
  public String getUUID() {
	return uuid;
  }
  
  @Override
  public void onAddProduct(Object o, int quantity) {
	  JsonObject stockEntry = (JsonObject) o;
	  JsonObject product = stockEntry.get("product").getAsJsonObject();
	  String productId = product.get("id").getAsString();
	  
	  String jsonResponse = wr.path("cart")
			  .path(uuid)
			  .path(productId)
			  .queryParam("qty", quantity + "")
			  .accept(MediaType.APPLICATION_JSON)
			  .put(String.class);
	  
	  // TODO: JSON Response is not reliable. Empty array is encoded as null, one element is encoded as Object and multiple elements are encoded as objects.
	  // http://stackoverflow.com/questions/2199453/how-can-i-customize-serialization-of-a-list-of-jaxb-objects-to-json/3143214#3143214
	  JsonObject cartItems = json.parse(jsonResponse).getAsJsonObject()
			  .get("items").getAsJsonObject();
	  
			  
	System.out.println(jsonResponse);
  }

  @Override
  public void onCheckout() {
	return;
  }
}

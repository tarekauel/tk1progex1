package services;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import services.model.Catalog;
import services.model.Item;
import services.model.Product;

import javax.xml.ws.Endpoint;
import java.io.IOException;

public class WebShopEndpoint {

  public static void main(String[] args) throws IOException {
    Catalog.create(new Item[] {
        new Item(Product.getProduct("PA"), 1.0, 1),
        new Item(Product.getProduct("PB"), 2.0, 1),
        new Item(Product.getProduct("PC"), 3.0, 1),
    });

    (new Thread() {
      @Override
      public void run() {
        try {
          HttpServerFactory.create("http://localhost:8080/");
          System.out.println("Started REST server");
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(-1);
        }
      }
    }).run();

    (new Thread() {
      @Override
      public void run() {
        Endpoint.publish("http://localhost:8090/", new WebShop());
        System.out.println("Started SOAP server");
      }
    }).run();
  }

}
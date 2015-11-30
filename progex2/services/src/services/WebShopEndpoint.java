package services;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import services.soap.WebShop;

import javax.xml.ws.Endpoint;
import java.io.IOException;

public class WebShopEndpoint {

  public static void main(String[] args) throws IOException {
    (new Thread() {
      @Override
      public void run() {
        try {
          HttpServerFactory.create("http://localhost:8080/").start();
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
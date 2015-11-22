package clients;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

public class RestClient {

  private static WebResource wr = Client.create().resource( "http://localhost:8080" );

  private static String uuid = UUID.randomUUID().toString();

  public static void main(String[] args) throws IOException {
    System.out.printf("REST-Client");
    System.out.printf("Welcome, your UUID is %s\n", uuid);

    int input;
    while(true) {
      printOption();
      do {
        input = Integer.parseInt(getStringInput());
      } while (input < 0 || input > 9);
      perform(input);
    }
  }

  private static void perform(int choice) throws IOException {
    switch (choice) {
      case 0: {
        System.out.println(
            wr.path("catalog").accept(MediaType.TEXT_PLAIN).get(String.class));
        break;
      }
      case 1: {
        System.out.printf("product name?\n");
        String name = getStringInput();
        System.out.println(
            wr.path("catalog").path(name).accept(MediaType.TEXT_PLAIN).get(String.class));
        break;
      }
      case 2: {
        System.out.printf("product name?\n");
        String name = getStringInput();
        System.out.printf("Quantity?\n");
        int qty = Integer.parseInt(getStringInput());
        System.out.println(
            wr.path("cart").path(uuid).path(name).queryParam("qty", qty + "").accept(MediaType.TEXT_PLAIN).put(String.class));
        break;
      }
      case 3: {
        System.out.printf("product name?\n");
        String name = getStringInput();
        System.out.println(
            wr.path("cart").path(uuid).path(name).accept(MediaType.TEXT_PLAIN).delete(String.class));
        break;
      }
      case 4: {
        System.out.println(
            wr.path("cart").path(uuid).accept(MediaType.TEXT_PLAIN).get(String.class)
        );
        break;
      }
      case 5: {
        System.out.println(
            wr.path("cart").path("checkout").path(uuid).accept(MediaType.TEXT_PLAIN).post(String.class)
        );
        break;
      }

      default:
        System.err.println("No action for: " + choice);
    }
  }

  private static String getStringInput() {
    Scanner sc = new Scanner(System.in);
    return sc.nextLine();
  }

  private static void printOption() {
    System.out.printf(
        "Please choose an option:\n" +
            "0: Show catalog\n" +
            "1: Show item of catalog\n" +
            "2: Put item into shopping cart\n" +
            "3: Remove item from shopping cart\n" +
            "4: Show shopping cart\n" +
            "5: Checkout:\n");

  }

}

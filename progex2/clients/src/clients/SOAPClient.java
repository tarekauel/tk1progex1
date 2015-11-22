package clients;

import gen.WebShopService;
import gen.WebShopService_Service;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

public class SOAPClient {

  private static WebShopService wsr = new WebShopService_Service().getWebShopServicePort();

  private static String uuid = UUID.randomUUID().toString();

  public static void main(String[] args) throws IOException {
    System.out.printf("SOAP-Client\n");
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
        System.out.println(wsr.getCatalog());
        break;
      }
      case 1: {
        System.out.printf("product name?\n");
        String name = getStringInput();
        System.out.println(wsr.getCatalogItem(name));
        break;
      }
      case 2: {
        System.out.printf("product name?\n");
        String name = getStringInput();
        System.out.printf("Quantity?\n");
        int qty = Integer.parseInt(getStringInput());
        System.out.println(wsr.putProduct(uuid, name, qty));
        break;
      }
      case 3: {
        System.out.printf("product name?\n");
        String name = getStringInput();
        System.out.println(wsr.deleteProduct(uuid, name));
        break;
      }
      case 4: {
        System.out.println(wsr.getShoppingCart(uuid));
        break;
      }
      case 5: {
        System.out.println(wsr.checkout(uuid));
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

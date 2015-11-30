package clients;

public interface ClientController {

	String getUUID();
	void onAddProduct(Object o, int quantity);
	void onCheckout();
}

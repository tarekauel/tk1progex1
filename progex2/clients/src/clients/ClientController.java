package clients;

public interface ClientController {
	public String getUUID();
	public void onAddProduct(Object o, int quantity);
	public void onCheckout();
}

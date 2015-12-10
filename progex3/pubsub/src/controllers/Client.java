package controllers;

import views.ClientView;

public class Client implements ClientControllerInterface {
	public static void main(String[] args) {
		new Client();
	}
	
	private String name;
	private ClientView view;
	
	public Client() {
		this.view = new ClientView(this);
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
}

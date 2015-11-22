The server has to provide the following functionality:

- The server has to manage the shopping cart for every client. Each shopping cart is stored using a
  unique ID for each client. No complex session management is needed for the clients.
- The server has to provide information about at least three different products (“Name”, “Price”,
  “Available Amount”)
- The server has to manage the available amounts for each product

Each client has to provide the following functionality:

- The client has to provide a user interface, which shows the available products with prices and the
  available amount for each product
- The user is able to buy products. The user can send his order using his shopping cart.
- The user gets a return message, if a product is not available, because another order might be
  received beforehand.
Task
====
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

File Hierachy
=============
- clients/      Contains the REST and SOAP client source code
  - lib/        Required 3rd party libraries
  - src/
    - clients/  Contains the client controllers. REST and SOAP client share an abstract parent class.
    - model/    Models that are used within the view to represent dropdown entries and the shopping cart table
    - views/    The GUI used for both clients
  - build.xml   ANT script. Responsible to build SOAP stubs and start the REST and SOAP client

- services/     Contains the REST and SOAP server source code
  - lib/        Required 3rd party libraries (Same as in clients/lib)
  - src/
    - services/ 
      - model/  The models that represent a Product, the Stock, ShoppingCart, etc.
      - rest/   REST server related code
      - soap/   SOAP server related cod
  - build.xml   ANT script. Responsible to start up the REST and SOAP services on the localhost interface

Notes
=====
- Please first run the ANT script within services/ before running the clients/ ANT script!
- To remove items from the cart, use a negative value in the spinner UI control

package services;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name="services.WebShop")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class WebShop {

  @WebMethod
  public String hello(String name) {
    return String.format("Hallo %s", name);
  }

}

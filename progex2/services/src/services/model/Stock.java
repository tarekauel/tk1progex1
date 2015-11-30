package services.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collections;

@XmlRootElement
public class Stock {

  private static Stock stock = new Stock(new StockItem[]{
      new StockItem(Product.getProduct(0), 20),
      new StockItem(Product.getProduct(1), 5),
      new StockItem(Product.getProduct(2), 1)
  });
  @XmlElement
  private ArrayList<StockItem> items = new ArrayList<>();

  private Stock() {
  }

  private Stock(StockItem[] i) {
    Collections.addAll(items, i);
  }

  public static Stock get() {
    return stock;
  }

  public StockItem get(int productId) {
    for (StockItem i : items) {
      if (i.getProduct().getId() == productId) {
        return i;
      }
    }
    return null;
  }
}

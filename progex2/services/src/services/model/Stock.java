package services.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

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
    for(StockItem si : i) {
      items.add(si);
    }
  }

  public static Stock get() {
    return stock;
  }

  public ArrayList<StockItem> getItems() {
    return items;
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

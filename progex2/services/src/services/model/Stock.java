package services.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

@XmlRootElement
public class Stock {
  private static Stock stock = new Stock(new StockItem[]{
		  new StockItem(Product.getProduct(0), 20),
		  new StockItem(Product.getProduct(1), 5),
		  new StockItem(Product.getProduct(2), 1)
  });

  public static Stock get() {
    return stock;
  }

  @XmlElement
  private Map<Integer, StockItem> items = new HashMap<>();
  
  private Stock() {}
  private Stock(StockItem[] i) {
    for (StockItem item : i) {
      items.put(item.getProduct().getId(), item);
    }
  }

  public StockItem get(int productId) {
    return items.get(productId);
  }
}

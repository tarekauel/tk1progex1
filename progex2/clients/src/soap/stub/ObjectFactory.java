
package soap.stub;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soap.stub package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _StockItem_QNAME = new QName("http://soap.services/", "stockItem");
    private final static QName _ShoppingCart_QNAME = new QName("http://soap.services/", "shoppingCart");
    private final static QName _Stock_QNAME = new QName("http://soap.services/", "stock");
    private final static QName _Product_QNAME = new QName("http://soap.services/", "product");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soap.stub
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Stock }
     * 
     */
    public Stock createStock() {
        return new Stock();
    }

    /**
     * Create an instance of {@link Stock.Items }
     * 
     */
    public Stock.Items createStockItems() {
        return new Stock.Items();
    }

    /**
     * Create an instance of {@link ShoppingCart }
     * 
     */
    public ShoppingCart createShoppingCart() {
        return new ShoppingCart();
    }

    /**
     * Create an instance of {@link ShoppingCart.Items }
     * 
     */
    public ShoppingCart.Items createShoppingCartItems() {
        return new ShoppingCart.Items();
    }

    /**
     * Create an instance of {@link Product }
     * 
     */
    public Product createProduct() {
        return new Product();
    }

    /**
     * Create an instance of {@link StockItem }
     * 
     */
    public StockItem createStockItem() {
        return new StockItem();
    }

    /**
     * Create an instance of {@link Stock.Items.Entry }
     * 
     */
    public Stock.Items.Entry createStockItemsEntry() {
        return new Stock.Items.Entry();
    }

    /**
     * Create an instance of {@link ShoppingCart.Items.Entry }
     * 
     */
    public ShoppingCart.Items.Entry createShoppingCartItemsEntry() {
        return new ShoppingCart.Items.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StockItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services/", name = "stockItem")
    public JAXBElement<StockItem> createStockItem(StockItem value) {
        return new JAXBElement<StockItem>(_StockItem_QNAME, StockItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShoppingCart }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services/", name = "shoppingCart")
    public JAXBElement<ShoppingCart> createShoppingCart(ShoppingCart value) {
        return new JAXBElement<ShoppingCart>(_ShoppingCart_QNAME, ShoppingCart.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Stock }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services/", name = "stock")
    public JAXBElement<Stock> createStock(Stock value) {
        return new JAXBElement<Stock>(_Stock_QNAME, Stock.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Product }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services/", name = "product")
    public JAXBElement<Product> createProduct(Product value) {
        return new JAXBElement<Product>(_Product_QNAME, Product.class, null, value);
    }

}

package models;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class CartTableModel implements TableModel {
	public static enum Columns {
		IDX(0),
		PRODUCT_ID(1),
		PRODUCT_NAME(2),
		QUANTITY(3),
		PRICE(4),
		TOTAL(5);
		
	    private final int value;
	    private Columns(int value) {
	        this.value = value;
	    }

	    public int toInt() {
	        return value;
	    }
	};
	private static String[] header = {"#", "Product ID", "Product Name", "Quantity", "Price", "Total"};
	private CartItem[] items;
	
	public CartTableModel(CartItem[] items) {
		super();
		
		this.items = items;
	}

	@Override
	public int getRowCount() {
		return this.items.length;
	}

	@Override
	public int getColumnCount() {
		return header.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return header[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
		case 1:
		case 3:
			return Integer.class;
		case 4:
		case 5:
			return Double.class;
		}
		
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		CartItem i = items[rowIndex];
		
		switch (columnIndex) {
		case 0: return rowIndex + 1;
		case 1: return i.getProductId();
		case 2: return i.getProduct();
		case 3: return i.getQuantity();
		case 4: return i.getPrice();
		case 5: return i.getPrice() * i.getQuantity();
		}
		
		return null;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}
}
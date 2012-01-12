package ro.cuzma.db;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ro.cuzma.db.DBException;
import ro.cuzma.db.TableCell;
import ro.cuzma.db.TableCellString;
import ro.cuzma.db.TableColumn;

public class BookType extends ValueDisplay {
	//private TableCell		value;
	//private TableCell		display;
	private TableCell		openWidth;
	public final static int	COLUMN_OPEN_WIDTH	= 3;

	public BookType(String xmlTag) throws DBException {
		super(xmlTag);
	}

	public BookType(String xmlTag, int rowID, String value, String display, String openWidth) throws DBException {
		super(xmlTag);
		this.rowID = rowID;
		this.value.setValue(value);
		this.display.setValue(display);
		this.openWidth.setValue(openWidth);
		this.setFieldOrder(ValueDisplay.COLUMN_DISPLAY);
	}

	
	protected void init() throws DBException {
		super.init();
		this.openWidth = new TableCellString(new TableColumn(BookType.COLUMN_OPEN_WIDTH, String.class, true, "openWidth"));
		this.cells.put(BookType.COLUMN_OPEN_WIDTH, this.openWidth);
	}

	public static BookType processNode(Node langN, String xmlTag) {
		NodeList children = langN.getChildNodes();
		String value = null;
		String display = null;
		String openWidth = null;
		BookType result = null;
		int id = -1;
		if (children != null) {
			int len = children.getLength();
			// System.out.println("childs len: " + len);
			Node tmp;
			for (int i = 0; i < len; i++) {
				tmp = children.item(i);
				if (tmp.getNodeType() == Node.ELEMENT_NODE) {
					if (tmp.getLocalName().equalsIgnoreCase("value")) {
						value = tmp.getChildNodes().item(0).getNodeValue();
					} else if (tmp.getLocalName().equalsIgnoreCase("id")) {
						id = new Integer(tmp.getChildNodes().item(0).getNodeValue()).intValue();
					} else if (tmp.getLocalName().equalsIgnoreCase("display")) {
						display = tmp.getChildNodes().item(0).getNodeValue();
					} else if (tmp.getLocalName().equalsIgnoreCase("openWith")) {
						openWidth = tmp.getChildNodes().item(0).getNodeValue();
					}
				}
			}
			try {
				//Logger.getLogger("com.cuzma.books.BookType").debug("Value: " + value);
				result = new BookType(xmlTag, id, value, display,openWidth);
				
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public String toString() {
		return this.display.getStringValue();
	}

	public TableCell getOpenWidth() {
		return openWidth;
	}
}

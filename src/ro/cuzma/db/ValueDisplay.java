package ro.cuzma.db;


	import org.apache.log4j.Logger;
	import org.w3c.dom.Node;
	import org.w3c.dom.NodeList;

import ro.cuzma.db.DBException;
import ro.cuzma.db.TableCell;
import ro.cuzma.db.TableCellString;
import ro.cuzma.db.TableColumn;
import ro.cuzma.db.TableRow;

	public class ValueDisplay extends TableRow {
		
		protected TableCell value;
		protected TableCell display;
		public  final static int COLUMN_VALUE = 1;
		public  final static int COLUMN_DISPLAY = 2;
		public ValueDisplay(String xmlTag) throws DBException{
			super(xmlTag);
		}
		public ValueDisplay(String xmlTag,int rowID,String value,String display) throws DBException{
			super(xmlTag);
			this.rowID = rowID;
			this.value.setValue(value);
			this.display.setValue(display);
			this.setFieldOrder(ValueDisplay.COLUMN_DISPLAY);
		}

		@Override
		public String getStringRow() {
			// TODO Auto-generated method stub
			return display.getStringValue();
		}

		@Override
		protected void init() throws DBException {
			this.value = new TableCellString(new TableColumn(ValueDisplay.COLUMN_VALUE, String.class, false, "value"));
			this.cells.put(ValueDisplay.COLUMN_VALUE, this.value);
			this.display = new TableCellString(new TableColumn(ValueDisplay.COLUMN_DISPLAY, String.class, false, "display"));
			this.cells.put(ValueDisplay.COLUMN_DISPLAY, this.display);
		}
		public boolean isEqual(ValueDisplay cmp) {
			return this.value.equals(cmp.value);
		}
		public static ValueDisplay processNode(Node langN, String xmlTag) {
			NodeList children = langN.getChildNodes();
			String value = null;
			String display = null;
			ValueDisplay result = null;
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
						} 
					}
				}
				try {
					//Logger.getLogger("ro.cuzma.books.ValueDisplay").debug("Value: " + value);
					result = new ValueDisplay(xmlTag,id, value,display);
				} catch (DBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return result;
		}
		public String toString(){
			return this.display.getStringValue();
		}
		

	}



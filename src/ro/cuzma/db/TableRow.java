package ro.cuzma.db;

import java.util.Enumeration;
import java.util.Hashtable;
import org.apache.log4j.Logger;


public abstract class TableRow implements Comparable {
	public final static int					COLUMN_ID		= 0;
	protected Integer						rowID;
	protected String						xmlTag;
	protected Hashtable	cells			= new Hashtable();
	public final static int					COMPARE_DESC	= -1;
	public final static int					COMPARE_ASC		= 1;
	public static int						fieldOrder		= TableRow.COLUMN_ID;
	public static int						ascDesc			= TableRow.COMPARE_ASC;
	protected String CLASS_NAME = this.getClass().getName();

	protected abstract void init() throws DBException;

	public abstract String getStringRow();

	protected Object getVal(int columnID) {
		return getCell(columnID).getValue();
	}

	public TableRow(String xmlTag) throws DBException {
		this.xmlTag = xmlTag;
		init();
	}
	public void setValue(int columnID, Object obj) throws DBException {
		((TableCell) cells.get(columnID)).setValue(obj);
	}
	public void setStringValue(int columnID, String obj) throws DBException {
		((TableCell) cells.get(columnID)).setStringValue(obj);
	}

	public TableRow getID() {
		return this;
	}

	public Object getDisplayValue(int columnID) {
		if (columnID == TableRow.COLUMN_ID) {
			return this;
		} else {
			return ((TableCell) cells.get(columnID)).getStringValue();
		}
	}

	public Object getValue(int columnID) {
		if (columnID == TableRow.COLUMN_ID) {
			return this;
		} else {
			return getVal(columnID);
		}
	}

	public Integer getRowID() {
		return rowID;
	}

	public void setRowID(int bookID) {
		this.rowID = bookID;
	}

	public TableCell getCell(int columnID) {
		return ((TableCell) cells.get(columnID));
	}

	public String toXml(String indent) {
		String rez = "";
		rez = rez + indent + "<" + this.xmlTag + ">\r\n";
		rez = rez + indent + "\t<id>" + this.rowID + "</id>\r\n";
		Enumeration en = cells.keys();
		while (en.hasMoreElements()) {
			rez = rez + ((TableCell) cells.get(en.nextElement())).toXml(indent + "\t");
		}
		rez = rez + indent + "</" + this.xmlTag + ">\r\n";
		return rez;
	}

	public int compareTo(Object arg) {
		//Logger.getLogger(CLASS_NAME).debug(this.getDisplayValue(TableRow.getFieldOrder()));
		TableRow tmp = (TableRow) arg;
		int rez = 0;
		if (TableRow.getFieldOrder() == TableRow.COLUMN_ID) {
			rez = this.getRowID() - tmp.getRowID();
		} else {
			rez = this.getCell(TableRow.getFieldOrder()).compareTo(tmp.getCell(TableRow.getFieldOrder()));
			if (rez == 0) {
				rez = this.getRowID() - tmp.getRowID();
			}
		}
		return rez;
	}

	public String toString() {
		return "" + rowID;
	}

	public static int getFieldOrder() {
		return fieldOrder;
	}

	public static void setFieldOrder(int fieldOrder) {
		TableRow.fieldOrder = fieldOrder;
	}

	public static int getAscDesc() {
		return ascDesc;
	}

	public static void setAscDesc(int ascDesc) {
		TableRow.ascDesc = ascDesc;
	}
}

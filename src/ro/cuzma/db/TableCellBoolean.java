package ro.cuzma.db;

import org.apache.log4j.Logger;

public class TableCellBoolean extends TableCell {
	public TableCellBoolean(TableColumn column) {
		super(column);
		// TODO Auto-generated constructor stub
	}

	public String getStringValue() {
		if (value == null) {
			return "";
		} else {
			if (((Boolean) value).booleanValue()) {
				return "1";
			} else {
				return "0";
			}
		}
	}

	public void setValue(Object obj) throws DBException {
		if (obj == null) {
			
				throw new DBException("Column: " + column.getColumnID() + " is not nullable!");
		} else {
			if (obj instanceof Boolean) {
				value = (Boolean) obj;
			} else {
				throw new DBException("Not Integer for TableCellBoolean for column: " + column.getColumnID() + " value: <" + obj.toString() + ">");
			}
		}
		// TODO Auto-generated method stub
	}

	public void setStringValue(String obj) throws DBException {
		
		if (obj == null || obj.equals("")) {
			
				throw new DBException("Column: " + column.getColumnID() + " is not nullable!");
			
		} else {
			if (obj.equals("1")) {
				value = new Boolean(true);
			}if (obj.equals("0")) {
				value = new Boolean(false);
			}else{
				throw new DBException("Not Boolean for TableCellBoolean for column: " + column.getColumnID() + " value: <" + obj.toString() + ">");
			} 
		}
		// TODO Auto-generated method stub
	}

	public int compareTo(Object arg0) {
		//Logger.getLogger(this.getClass().getName()).debug("Onject is Instance of: " + arg0.getClass().getName());
		return ((Boolean)value).compareTo((Boolean)((TableCellBoolean)arg0).getValue());
	}
}

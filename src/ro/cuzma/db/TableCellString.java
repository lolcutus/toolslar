package ro.cuzma.db;

import org.apache.log4j.Logger;

public class TableCellString extends TableCell {
	private String CLASS_NAME = this.getClass().getName();
	public TableCellString(TableColumn column) {
		super(column);
		// TODO Auto-generated constructor stub
	}

	public String getStringValue() {
		if (value == null){
			return "";
		}else{
			return (String)value;
		}
	}

	public void setValue(Object obj) throws DBException {
		Logger.getLogger(CLASS_NAME).debug("Value: " + obj);
		if (obj == null) {
			Logger.getLogger(CLASS_NAME).debug("1");
			if (column.isNullable()){
				value = null;
			}else{
				throw new DBException("Column: " + column.getColumnID() + " is not nullable!");
			}
		} else {
			Logger.getLogger(CLASS_NAME).debug("2");
			if (obj instanceof String) {
				String tmp = (String) obj;
				if(!column.isNullable() && tmp.equals("")){
					throw new DBException("Column: " + column.getColumnID() + " is not nullable!");
				}else{
					value = tmp;
				}
			} else {
				throw new DBException("Not String for TableCellString for column " + column.getColumnID());
			}
		}
		
		// TODO Auto-generated method stub
	}
	public void setStringValue(String obj) throws DBException {
		
		if (obj == null || obj.equals("")) {
			if (column.isNullable()){
				value = null;
			}else{
				throw new DBException("Column: " + column.getColumnID() + " is not nullable!");
			}
		} else {
			value = obj;
		}
		
		// TODO Auto-generated method stub
	}

	public int compareTo(Object arg0) {
		return getStringValue().compareTo(((TableCellString)arg0).getStringValue());
	}
}

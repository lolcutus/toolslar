package ro.cuzma.db;


	import java.util.Vector;
import org.apache.log4j.Logger;

	public class TableCellValueDisplay extends TableCell {
		
		public Object getValue() {
			return value;
		}

		public TableCellValueDisplay(TableColumn column) {
			super(column);
		}

		public String getStringValue() {
			String rez = "";
			if (value != null) {
				rez = (String)((ValueDisplay)value).getValue(ValueDisplay.COLUMN_DISPLAY);
				//Logger.getLogger(CLASS_NAME).debug("rez: " + rez);
				}
			
			return rez;
		}

		public void setStringValue(String obj) throws DBException {
			throw new DBException("TableCellValueDisplay can not use this method!!!");
		}

		public void setValue(Object obj) throws DBException {
			
			if (obj == null) {
				if (column.isNullable()) {
					value = null;
				} else {
					throw new DBException("Column: " + column.getColumnID() + " is not nullable!");
				}
			} else {
				//
				if (obj instanceof ValueDisplay) {
					//Logger.getLogger(CLASS_NAME).debug("Intru");	
					value = obj;
				} else {
					throw new DBException("Not ValueDisplay for TableCellValueDisplay for column: " + column.getColumnID() + " value: <" + obj.toString() + ">");
				}
			}
		}

		
		public int compareTo(Object o) {
			return getStringValue().compareTo(((TableCellValueDisplay) o).getStringValue());
		}

	}


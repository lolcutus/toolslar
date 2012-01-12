package ro.cuzma.db;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.apache.log4j.Logger;
//import com.cuzma.util.Log4JUtil;
import ro.cuzma.tools.CalendarTools;;

public class TableCellCalendar extends TableCell {
	private String CLASS_NAME = this.getClass().getName();
	public TableCellCalendar(TableColumn column) {
		super(column);
		// TODO Auto-generated constructor stub
	}

	public String getStringValue() {
		if (value == null) {
			return "";
		} else {
			//System.out.println(value);
			return CalendarTools.calendarToStringShort((Calendar) value, ".");
		}
	}
	/*public Object getValue() {
		return getStringValue();
	}*/
	public String toString() {
		if (value == null) {
			return "";
		} else {
			//System.out.println(value);
			return CalendarTools.calendarToStringShort((Calendar) value, ".");
		}
	}

	public void setValue(Object obj) throws DBException {
		if (obj == null) {
			if (column.isNullable()) {
				value = null;
			} else {
				throw new DBException("Column: " + column.getColumnID() + " is not nullable!");
			}
		} else {
			if (obj instanceof Calendar) {
				Calendar tmp = (Calendar) obj;
				value = tmp;
			} else {
				throw new DBException("Not Calendar for TableCellCalendar");
			}
		}
		// TODO Auto-generated method stub
	}

	public int compareTo(Object arg0) {
		Calendar a = (Calendar) this.value;
		Calendar b = (Calendar) ((TableCellCalendar) arg0).getValue();
		if (a == null)
			a = new GregorianCalendar();
		if (b == null)
			b = new GregorianCalendar();
		// TODO Auto-generated method stub
		return a.compareTo(b);
	}

	public void setStringValue(String obj) throws DBException {
		Logger.getLogger(CLASS_NAME).debug("Value: " + obj);
		if (obj == null || obj.equals("")) {
			if (column.isNullable()) {
				value = null;
			} else {
				throw new DBException("Column: " + column.getColumnID() + " is not nullable!");
			}
		} else {
			GregorianCalendar gr = new GregorianCalendar();
			try {
				String year = obj.substring(6, 10);
				String month = obj.substring(3, 5);
				String day = obj.substring(0, 2);
				// System.out.println(year + "-" + month + "-" + day);
				gr.set(new Integer(year).intValue(), new Integer(month).intValue() - 1, new Integer(day).intValue());
				value = gr;
			} catch (RuntimeException ex) {
				throw new DBException("Wrong format date: " + obj + ". Format must be like: dd.mm.yyyy");
			}
		}
	}
	
}

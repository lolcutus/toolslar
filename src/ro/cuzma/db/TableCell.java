package ro.cuzma.db;

import java.util.GregorianCalendar;
import java.util.Vector;
import org.apache.log4j.Logger;
import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;
//import larry.tools.Tools;
//import com.cuzma.ui.Column;

public abstract class TableCell implements Comparable {
	protected String		CLASS_NAME	= this.getClass().getName();
	protected TableColumn	column;
	//private Vector<TableCell> valueV;
	protected Object		value;

	public TableCell(TableColumn column) {
		this.column = column;
	}

	public abstract void setValue(Object obj) throws DBException;

	public abstract void setStringValue(String obj) throws DBException;

	public Object getValue() {
		return value;
	}

	public TableColumn getColumn() {
		return column;
	}

	public abstract String getStringValue();/*{
	 String rez = "";
	 if (value == null){
	 rez = "";
	 }
	 if (column.getClass() == GregorianCalendar.class){
	 rez = Tools.calendarToStringShort((GregorianCalendar)value, ".");
	 }else{
	 rez = value.toString();
	 }
	 return rez;
	 }*/

	public String toXml(String indent) {
		String rez = "";
		if (value != null) {
			if (value instanceof Boolean) {
				Boolean tmp = (Boolean) value;
				if (tmp.booleanValue()) {
					rez = rez + indent + "<" + column.getXmlTag() + "/>\r\n";
				}
			} else if (value instanceof Vector) {
				//Logger.getLogger(CLASS_NAME).debug("Instance of vector");
				value = getValue();
				TableCellVector tcv = (TableCellVector) this;
				int size = ((Vector) tcv.getValue()).size();
				if (size > 0) {
					if (tcv.getParentTag() != null) {
						rez = rez + indent + "<" + tcv.getParentTag() + ">\r\n";
					}
					for (int i = 0; i < size; i++) {
						rez = rez + indent + "\t<" + tcv.column.getXmlTag() + ">" + ((TableRow) ((Vector) tcv.getValue()).get(i)).getID() + "</" + tcv.column.getXmlTag() + ">\r\n";
					}
					if (tcv.getParentTag() != null) {
						rez = rez + indent + "</" + tcv.getParentTag() + ">\r\n";
					}
				}
				return rez;
			} else if (value instanceof ValueDisplay) {
				//Logger.getLogger(CLASS_NAME).debug("Instance of vector");
				rez = rez + indent + "<" + column.getXmlTag() + ">" + ((ValueDisplay)value).rowID + "</" + column.getXmlTag() + ">\r\n";
				return rez;
			} else {
				rez = rez + indent + "<" + column.getXmlTag() + ">" + getStringValue() + "</" + column.getXmlTag() + ">\r\n";
			}
		}
		//System.out.println("-"+rez+"-");
		return rez;
	}
}

package ro.cuzma.db;

import java.util.Vector;

public class TableCellVector extends TableCell {
	Vector<TableRow>	objs;
	String				parentTag;

	public Object getValue() {
		value = objs;
		return value;
	}

	public TableCellVector(TableColumn column, String parentTag) {
		super(column);
		this.parentTag = parentTag;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getStringValue() {
		String rez = "";
		if (objs != null) {
			for (int i = 0; i < objs.size(); i++) {
				if (rez.equals("")) {
					rez = objs.get(i).getStringRow();
				} else {
					rez = rez + "; " + objs.get(i).getStringRow();
				}
			}
		}
		return rez;
	}

	@Override
	public void setStringValue(String obj) throws DBException {
		throw new DBException("Vector can not use this method!!!");
	}

	@Override
	public void setValue(Object obj) throws DBException {
		if (obj == null) {
			if (column.isNullable()) {
				objs = null;
			} else {
				throw new DBException("Column: " + column.getColumnID() + " is not nullable!");
			}
		} else {
			if (obj instanceof Vector) {
				Vector tmp = (Vector) obj;
				objs = tmp;
			} else {
				throw new DBException("Not Long for TableCellVector for column: " + column.getColumnID() + " value: <" + obj.toString() + ">");
			}
		}
		value = objs;
	}

	public void add(TableRow tr) {
		objs.add(tr);
	}

	public int compareTo(Object o) {
		return getStringValue().compareTo(((TableCellVector) o).getStringValue());
	}

	public String getParentTag() {
		return parentTag;
	}
}

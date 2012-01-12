package ro.cuzma.ui;



import java.util.TreeSet;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

import ro.cuzma.db.TableRow;
import ro.cuzma.ui.Column;


public class TableWithColumnModel extends AbstractTableModel {
	/**
	 * @uml.property  name="data" multiplicity="(0 -1)" dimension="2"
	 */
	private Object[][]	data;
	/**
	 * @uml.property  name="editColumn"
	 */
	private boolean		editColumn	= false;
	/**
	 * @uml.property  name="table"
	 * @uml.associationEnd  multiplicity="(1 1)" inverse="modelT:ro.cuzma.ui.TableWithColumn"
	 */
	private TableWithColumn	table;

	public TableWithColumnModel(TableWithColumn table) {
		this.table = table;
		data = new Object[0][table.getDisplayColumns().length];
	}

	public int getColumnCount() {
		int len = table.getDisplayColumns().length;
		return len;
	}

	public int getRowCount() {
		return data.length;
	}

	public String getColumnName(int col) {
		//System.out.println("-" + table.getDisplayColumns()[col]);
		return table.getDisplayColumns()[col];
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	public void setSort(int col) {
		Column tmp;
		for (int i = 0; i < getColumnCount(); i++) {
			tmp = (Column) table.getTableColumns().get(i);
			if (col == i) {
				tmp.setSort();
			} else {
				tmp.resetSort();
			}
		}
	}

	/*
	 * JTable uses this method to determine the default renderer/ editor for
	 * each cell. If we didn't implement this method, then the last column would
	 * contain text ("true"/"false"), rather than BookTableHeaderListener check
	 * box.
	 */
	public Class getColumnClass(int c) {
		// return String.class;
		return ((Column) table.getTableColumns().get(c)).getColumnClass();
	}

	/*
	 * Don' t need to implement this method unless your table's editable.
	 */
	public boolean isCellEditable(int row, int col) {
		if (editColumn) {
			return  table.getTableColumns().get(col).isEditable();
		} else {
			return false;
		}
	}

	/*
	 * Don't need to implement this method unless your table's data can change.
	 */
	public void setValueAt(Object value, int row, int col) {
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}

	public void newData(Vector<TableRow> rows) {
		data = new Object[rows.size()][getColumnCount()];
		// System.out.println(books.size());
		TreeSet<TableRow> ts = new TreeSet<TableRow>(rows);
		java.util.Iterator it = ts.iterator();
		int i = 0;
		TableRow tmp;
		while (it.hasNext()) {
			tmp = (TableRow) it.next();
			for (int j = 0; j < getColumnCount(); j++) {
				data[i][j] = table.getValueToDisplay(tmp, j);
			}
			i++;
		}
	}

	public boolean setUnsetEdit() {
		this.editColumn = !this.editColumn;
		return this.editColumn;
	}

	public boolean getEdit() {
		return this.editColumn;
	}
}


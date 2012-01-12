package ro.cuzma.ui;

import javax.swing.table.TableCellRenderer;

import ro.cuzma.db.TableRow;

public class Column implements Comparable {
	private String				header;
	private Class				columnClass;
	private int					columnPos;
	private int					columnID;
	private static final int	ORDER_NO	= 0;
	private int					sort		= Column.ORDER_NO;
	private boolean				show		= true;
	private int					minimWidth	= 30;
	private boolean				editable		= false;
	private TableCellRenderer   cellRender =  null;

	public Column(String header, Class columnClass, int columnPos, int columnID, int minimWidth) {
		this.header = header;
		this.columnClass = columnClass;
		this.columnPos = columnPos;
		this.columnID = columnID;
		this.minimWidth = minimWidth;
		this.editable = false;
	}

	public Column(String header, Class columnClass, int columnPos, int columnID, int minimWidth,  boolean show) {
		this.header = header;
		this.columnClass = columnClass;
		this.columnPos = columnPos;
		this.columnID = columnID;
		this.show = show;
		this.minimWidth = minimWidth;
		this.editable = false;
	}

	public int compareTo(Object obj) {
		int rez = this.columnPos - ((Column) obj).getColumnPos();
		/*
		 * if (rez == 0){ throw new BooksException(this.getHeader() + " and " +
		 * ((Column)obj).getHeader() + " have the same position!!!"); }
		 */
		if (rez == 0) {
			this.columnPos++;
			return 1;
		}
		return rez;
	}

	public Class getColumnClass() {
		return columnClass;
	}

	public void setColumnClass(Class columnClass) {
		this.columnClass = columnClass;
	}

	public int getColumnPos() {
		return columnPos;
	}

	public void setColumnPos(int columnPos) {
		this.columnPos = columnPos;
	}

	public String getHeader() {
		/*if (sort == Book.COMPARE_ASC) {
			return "ASC " + header;
		} else if (sort == Book.COMPARE_DESC) {
			return "DESC " + header;
		}*/
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public void resetSort() {
		sort = ORDER_NO;
	}

	public void setSort() {
		if (sort == Column.ORDER_NO) {
			sort = TableRow.COMPARE_ASC;
		} else if (sort == TableRow.COMPARE_DESC) {
			sort = TableRow.COMPARE_ASC;
		} else {
			if (sort == TableRow.COMPARE_ASC) {
				sort = TableRow.COMPARE_DESC;
			}
		}
		//Book.setFieldOrder(this.getColumnID());
		//Book.setAscDesc(sort);
	}

	public Column copy() {
		Column copyC = new Column(header, columnClass, columnPos, columnID, minimWidth, show);
		copyC.setEditable(editable);
		copyC.setCellRender(cellRender);
		return copyC;
	}

	public int getColumnID() {
		return columnID;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public int getMinimWidth() {
		return minimWidth;
	}

	public void setMinimWidth(int minimWidth) {
		this.minimWidth = minimWidth;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public TableCellRenderer getCellRender() {
		return this.cellRender;
	}

	public void setCellRender(TableCellRenderer cellRender) {
		this.cellRender = cellRender;

	}
}

package ro.cuzma.ui;

//import com.cuzma.books.Book;
import java.util.TreeSet;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ToolTipManager;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import org.apache.log4j.Logger;

import ro.cuzma.db.DBException;
import ro.cuzma.db.TableCell;
import ro.cuzma.db.TableCellCalendar;
import ro.cuzma.db.TableCellValueDisplay;
import ro.cuzma.db.TableCellVector;
import ro.cuzma.db.TableData;
import ro.cuzma.db.TableRow;
import ro.cuzma.ui.Column;
import ro.cuzma.util.Log4JUtil;

public abstract class TableWithColumn extends JTable {
	private TableWithColumnModel	modelT;
	private JTableHeader			header;
	private TableData					tableData;
	private JTextArea				messageText;
	private Vector<Column>			tableColumnsAll	= new Vector<Column>();
	private Vector<Column>			tableColumns	= new Vector<Column>();
	protected abstract void createAllColumns();
	boolean exc = false;
	private String CLASS_NAME = this.getClass().getName();

	public Vector<TableRow> reloadData() {
		// System.out.println("Reload: " +
		// tableData.getSortedFilteredData().size());
		return tableData.getSortedFilteredData();
	}

	public TableWithColumn(TableData tableData, JTextArea messageText) {
		// super();
		createAllColumns();
		generateColumns();
		this.setRowSelectionAllowed (true);
	     this.setColumnSelectionAllowed (true);

		ToolTipManager.sharedInstance().unregisterComponent(this);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.tableData = tableData;
		this.messageText = messageText;
		//this.cellRender = cellRender;
		init();
		// System.out.println(this.tableData.getSortedFilteredData().size());
	}

	public void init() {
		modelT = new TableWithColumnModel(this);
		this.setModel(modelT);
		header = this.getTableHeader();
		header.addMouseListener(new TableHeaderListener(this));
		this.setNewData(tableData.getRows());
		//
		for (int i = 0; i < this.getColumnModel().getColumnCount(); i++) {
			// System.out.println("col:"+1);
			if (tableColumns.get(i).getCellRender() != null) {
				//System.out.println("add renderer");
				this.getColumnModel().getColumn(i).setCellRenderer(tableColumns.get(i).getCellRender() );
			}
			this.getColumnModel().getColumn(i).setMinWidth(tableColumns.get(i).getMinimWidth());
			this.getColumnModel().getColumn(i).setPreferredWidth(tableColumns.get(i).getMinimWidth());
		}
	}

	public void setNewData(Vector newData) {
		modelT.newData(newData);
		if (messageText != null) {
			messageText.setText("Show: " + newData.size());
		}
	}

	/*
	 * private void jTableBooksKeyReleased(KeyEvent evt) { int column =
	 * this.getSelectedColumn(); int row = this.getSelectedRow(); //
	 * System.out.print("im"); processEdit(row, column); }
	 * 
	 * private void jTableBooksFocusLost(FocusEvent evt) { processEdit(-1, -1); }
	 * 
	 * private void jTableBooksMouseClicked(MouseEvent evt) { processEdit(-1,
	 * -1); }
	 */
	public String[] getDisplayColumns() {
		Column tmp;
		TreeSet<Column> ts = new TreeSet<Column>(getTableColumns());
		String[] displayColumns = new String[ts.size()];
		java.util.Iterator it = ts.iterator();
		int i = 0;
		while (it.hasNext()) {
			tmp = (Column) it.next();
			// System.out.println(tmp.getHeader());
			displayColumns[i] = tmp.getHeader();
			i++;
		}
		return displayColumns;
	}

	public Vector<Column> getTableColumns() {
		// System.out.println("TableColumns:"+tableColumns.size());
		return tableColumns;
	}

	public Object getValueToDisplay(TableRow rowObj, int columnPos) {
		Column tmp;
		for (int i = 0; i < getTableColumns().size(); i++) {
			tmp = (Column) getTableColumns().get(i);
			if (columnPos == tmp.getColumnPos()) {
				// System.out.println(rowObj.getValue(tmp.getColumnID()));
				//if (columnPos == Book.)
				if (rowObj.getCell(tmp.getColumnID()) instanceof TableCellCalendar){
					return rowObj.getDisplayValue(tmp.getColumnID());
				}else if (rowObj.getCell(tmp.getColumnID()) instanceof TableCellVector){
					return ((TableCellVector)rowObj.getCell(tmp.getColumnID())).getStringValue();
				}else if (rowObj.getCell(tmp.getColumnID()) instanceof TableCellValueDisplay){
					return ((TableCellValueDisplay)rowObj.getCell(tmp.getColumnID())).getStringValue();
				}
				else{
					return rowObj.getValue(tmp.getColumnID());
				}
				
			}
		}
		return "ERROR";
	}

	private void generateColumns() {
		Column tmp;
		Column newS;
		TreeSet<Column> ts = new TreeSet<Column>(tableColumnsAll);
		java.util.Iterator it = ts.iterator();
		int i = 0;
		while (it.hasNext()) {
			tmp = (Column) it.next();
			if (tmp.isShow()) {
				newS = tmp.copy();
				newS.setColumnPos(i);
				tableColumns.add(newS);
				i++;
			}
		}
	}

	public void addColumn(Column toAdd) {
		this.tableColumnsAll.add(toAdd);
	}

	/**
	 * @return the messageText
	 * @uml.property name="messageText"
	 */
	public JTextArea getMessageText() {
		return messageText;
	}

	/**
	 * @param messageText
	 *            the messageText to set
	 * @uml.property name="messageText"
	 */
	public void setMessageText(JTextArea messageText) {
		this.messageText = messageText;
	}

	/**
	 * @return the tableData
	 * @uml.property name="tableData"
	 */
	public TableData getTableData() {
		return tableData;
	}

	/**
	 * @param tableData
	 *            the tableData to set
	 * @uml.property name="tableData"
	 */
	public void setTableData(TableData tableData) {
		this.tableData = tableData;
	}
	public void tableChanged(TableModelEvent e) {
		super.tableChanged(e);
		if (!exc){
		int row = e.getFirstRow();
		int column = e.getColumn();
		String oldValue = "";
		try {
			if (row != -1 && column != -1) {
				TableRow bk = (TableRow) this.getValueAt(row, 0);
				int col = getTableColumns().get(column).getColumnID();
				oldValue = (String)bk.getDisplayValue(col);
				TableCell tc = bk.getCell(col);
				//Logger.getLogger(CLASS_NAME).info("tc: " + tc.getClass().getName() + "-" + tc.getColumn().getXmlTag());
				if (tc instanceof TableCellCalendar){
					Logger.getLogger(CLASS_NAME).info("col: " + col);
				}else{
					tc.setValue(this.getValueAt(row, column));
				}
			}
		} catch (java.lang.RuntimeException ex) {
			this.setValueAt(oldValue, row, column);
			getMessageText().setText(ex.getMessage());
			exc = true;
		} catch (DBException ex2) {
			exc = true;
			// TODO Auto-generated catch block
			this.setValueAt(oldValue, row, column);
			getMessageText().setText(ex2.getMessage());
		}}else{
			exc = false;
		}
	}
}

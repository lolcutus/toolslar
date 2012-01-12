package ro.cuzma.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.TableColumn;

import ro.cuzma.ui.TableWithColumn;

public class TableHeaderListener extends MouseAdapter {
	/**
	 * @uml.property  name="table"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	TableWithColumn	table;

	public TableHeaderListener(TableWithColumn table) {
		this.table = table;
	}

	public void mouseClicked(MouseEvent evt) {
		if (evt.getButton() == MouseEvent.BUTTON1) {
			int mColIndex = table.columnAtPoint(evt.getPoint());
			((TableWithColumnModel) table.getModel()).setSort(mColIndex);
			TableColumn column;
			for (int i = 0; i < table.getModel().getColumnCount(); i++) {
				column = table.getColumnModel().getColumn(i);
				column.setHeaderValue(table.getModel().getColumnName(i));
			}
			table.getTableHeader().repaint();
			table.setNewData(table.reloadData());
			table.revalidate();
			table.repaint();
		}
	}
}

package ro.cuzma.ui;

import java.util.TreeSet;
import javax.swing.JComboBox;

import ro.cuzma.db.TableData;
import ro.cuzma.db.TableRow;
import ro.cuzma.db.ValueDisplay;

public class TableComboBox extends JComboBox {
	protected boolean empty;
		public TableComboBox(TableData tbl,boolean empty){
			super();
			this.empty =empty;
			if (empty){
				this.addItem("");
			}
			TreeSet<TableRow> ts = new TreeSet<TableRow>(tbl.getRows());
			java.util.Iterator it = ts.iterator();
			int i = 0;
			TableRow tmp;
			while (it.hasNext()) {
				tmp = (TableRow) it.next();
				this.addItem(tmp);
			}
		}
		public Integer getSelectedID(){
			if (empty && this.getSelectedIndex() == 0){
				return null;
			}
			return ((ValueDisplay)getSelectedItem()).getRowID();
		}
	
}

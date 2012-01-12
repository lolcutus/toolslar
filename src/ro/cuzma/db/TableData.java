package ro.cuzma.db;

//import com.cuzma.books.Book;
import java.util.Vector;

/**
 * @author  cuzma
 */
public abstract class TableData {
	
	protected Vector rows = new Vector();
	protected String xmlTag;
	
	public abstract Vector getSortedFilteredData();
	public TableData(Vector rows,String xmlTag){
		this.rows = rows;
		this.xmlTag = xmlTag;
	}
	public void deleteRow(Object row) {
		rows.remove(row);
	}

	/**
	 * @return  the rows
	 * @uml.property  name="rows"
	 */
	public Vector getRows() {
		return rows;
	}

	/**
	 * @param rows  the rows to set
	 * @uml.property  name="rows"
	 */
	public void setRows(Vector rows) {
		this.rows = rows;
	}
	
	public int size(){
		return rows.size();
	}
	
	public void add(Object row){
		rows.add(row);
	}
	public Object get(int i){
		return rows.get(i);
	}
	public void remove(Object row){
		rows.remove(row);
	}
	public int getNewID() {
		int rez = 0;
		int tmpId;
		// System.out.println(books.size());
		for (int i = 0; i < rows.size(); i++) {
			// System.out.println("i: "+i);
			tmpId = ((TableRow) rows.get(i)).getRowID();
			if (rez < tmpId) {
				rez = tmpId;
			}
			;
		}
		return rez + 1;
	}
	public String toXml(String indent){
		String rez = indent + "<" + xmlTag + ">\r\n";
		for (int i = 0; i < this.rows.size(); i++) {
			rez = rez + ((TableRow) this.rows.get(i)).toXml(indent + "\t");
		}
		rez = rez + indent + "</" + xmlTag + ">\r\n";
		return rez;
	}
}

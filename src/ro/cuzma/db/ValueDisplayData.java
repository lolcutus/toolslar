package ro.cuzma.db;

import java.util.Vector;

import ro.cuzma.db.TableData;

public class ValueDisplayData extends TableData {
	public ValueDisplayData(Vector rows,String xmlParent) {
		super(rows,xmlParent);
	}
	public Vector getSortedFilteredData() {
		// TODO Auto-generated method stub
		return rows;
	}
	public ValueDisplay addValue(ValueDisplay value) {
		ValueDisplay tmp;
		for (int i = 0; i < this.size(); i++) {
			tmp = (ValueDisplay) this.get(i);
			if (tmp.isEqual(value)) {
				return tmp;
			}
		}
		this.add(value);
		return value;
	}
	
}
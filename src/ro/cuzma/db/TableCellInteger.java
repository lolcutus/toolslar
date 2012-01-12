
	package ro.cuzma.db;

	public class TableCellInteger extends TableCell {
		public TableCellInteger(TableColumn column) {
			super(column);
			// TODO Auto-generated constructor stub
		}

		public String getStringValue() {
			if (value == null){
				return "";
			}else{
				//System.out.println(value);
				return ((Integer)value).toString();
			}
		}

		public void setValue(Object obj) throws DBException {
			if (obj == null) {
				if (column.isNullable()){
					value = null;
				}else{
					throw new DBException("Column: " + column.getColumnID() + " is not nullable!");
				}
			} else {
				if (obj instanceof Integer) {
					Integer tmp = (Integer) obj;
					
						value = tmp;
				} else {
					throw new DBException("Not Integer for TableCellInteger for column: " + column.getColumnID() + " value: <" + obj.toString() + ">");
				}
			}
			
			// TODO Auto-generated method stub
		}
		public void setStringValue(String obj) throws DBException {
			if (obj == null || obj.equals("")) {
				if (column.isNullable()){
					value = null;
				}else{
					throw new DBException("Column: " + column.getColumnID() + " is not nullable!");
				}
			} else {
				try{
					value = new Integer(obj);
				}catch (java.lang.RuntimeException ex){
					throw new DBException("Not a number: <" + obj + ">!!");
				}
			}
			
			// TODO Auto-generated method stub
		}

		public int compareTo(Object arg0) {
			Integer a = (Integer)this.value;
			Integer b = (Integer)((TableCellInteger)arg0).getValue();
			if (a == null ) a = new Integer(0);
			if (b == null ) b = new Integer(0);
			// TODO Auto-generated method stub
			return a.compareTo(b);
		}
	}



	package ro.cuzma.db;

	public class TableCellFloat extends TableCell {
		public TableCellFloat(TableColumn column) {
			super(column);
			// TODO Auto-generated constructor stub
		}

		public String getStringValue() {
			if (value == null){
				return "";
			}else{
				//System.out.println(value);
				return ((Float)value).toString();
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
				if (obj instanceof Float) {
					Float tmp = (Float) obj;
					
						value = tmp;
				} else {
					throw new DBException("Not Float for TableCellLong for column: " + column.getColumnID() + " value: <" + obj.toString() + ">");
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
					value = new Float(obj);
				}catch (java.lang.RuntimeException ex){
					throw new DBException("Not a number: <" + obj + ">!!");
				}
			}
			
			// TODO Auto-generated method stub
		}

		public int compareTo(Object arg0) {
			Float a = (Float)this.value;
			Float b = (Float)((TableCellFloat)arg0).getValue();
			if (a == null ) a = new Float(0.00);
			if (b == null ) b = new Float(0.00);
			// TODO Auto-generated method stub
			return a.compareTo(b);
		}
	}



	package ro.cuzma.db;

	public class TableCellLong extends TableCell {
		public TableCellLong(TableColumn column) {
			super(column);
			// TODO Auto-generated constructor stub
		}

		public String getStringValue() {
			if (value == null){
				return "";
			}else{
				//System.out.println(value);
				return ((Long)value).toString();
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
				if (obj instanceof Long) {
					Long tmp = (Long) obj;
					
						value = tmp;
				} else {
					throw new DBException("Not Long for TableCellLong for column: " + column.getColumnID() + " value: <" + obj.toString() + ">");
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
					value = new Long(obj);
				}catch (java.lang.RuntimeException ex){
					throw new DBException("Not a number: <" + obj + ">!!");
				}
			}
			
			// TODO Auto-generated method stub
		}

		public int compareTo(Object arg0) {
			Long a = (Long)this.value;
			Long b = (Long)((TableCellLong)arg0).getValue();
			if (a == null ) a = new Long(0);
			if (b == null ) b = new Long(0);
			// TODO Auto-generated method stub
			return a.compareTo(b);
		}
	}


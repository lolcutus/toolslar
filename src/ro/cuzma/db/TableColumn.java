package ro.cuzma.db;

public class TableColumn {
	protected int columnID;
	protected Class classType = String.class;
	private boolean nullable = true;
	private String xmlTag;
	
	public Class getClassType() {
		return classType;
	}

	public void setClassType(Class classType) {
		this.classType = classType;
	}

	public int getColumnID() {
		return columnID;
	}

	public void setColumnID(int columnID) {
		this.columnID = columnID;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public TableColumn(int columnID,Class classType,boolean nullable,String xmlTag) throws DBException{
		this.columnID = columnID;
		this.classType = classType;
		this.nullable = nullable;
		if (xmlTag == null || xmlTag.equals("")){
			throw new DBException("xmlTag can not be null or empty!!!");
		}
		this.xmlTag = xmlTag;
	}

	public String getXmlTag() {
		return xmlTag;
	}
}

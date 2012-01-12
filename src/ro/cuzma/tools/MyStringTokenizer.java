package ro.cuzma.tools;;

public class MyStringTokenizer {
	private String toParse;
	private String delimitator;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyStringTokenizer st = new MyStringTokenizer("\"Personale\",\"\",0xd852328b,3,\"\\\\cuzma\\f$\",\"NTFS\",\"Multimedia\",1095841431,1138463805,280755,-2147483648,32,32,0,0,3,24,0,32,32,6,880,\"(ebook - PDF) How to Make Love All Night (and Drive BookTableHeaderListener Woman Wild).pdf\",\"\",\";\"",",");
		//String[] data = new String[27];
        //int i = 0;
		while(st.hasMoreTokens()){
			//data[i++] = st.nextToken();
			System.out.println("<" + st.nextToken()+">");
		}

	}
	
	public MyStringTokenizer(String toParse, String delimitator){
		this.toParse = toParse;
		this.delimitator = delimitator;
	}
	
	public String nextToken(){
		//System.out.println("remain: -" + this.toParse + "-");
		if (toParse != null){
			if (toParse.substring(0,1).equals("\"")){
				toParse = toParse.substring(1);
				int next = toParse.indexOf("\"");
				String ret = toParse.substring(0,next);
				toParse = toParse.substring(next + 1);
				if (this.toParse != null && !this.toParse.equals("")){
					if (toParse.substring(0,this.delimitator.length()).equals(this.delimitator)){
						toParse = toParse.substring(this.delimitator.length());
					}
				}
				return ret;
			}else{
				int next = toParse.indexOf(this.delimitator);
				if (next != -1){
					String ret = toParse.substring(0,next);
					toParse = toParse.substring(next + this.delimitator.length());
					return ret;
				}else{
					String ret = toParse;
					toParse = null;
					return ret;
				}
			}
		}
		return null;
	}
	public boolean hasMoreTokens(){
		if (this.toParse != null && !this.toParse.equals("")){
			return true;
		}
		return false;
	}

}

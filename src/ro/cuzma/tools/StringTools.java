package ro.cuzma.tools;

public class StringTools {
	public static String leftPad(String toPad,int newLenght,char withChar){
		String tmp="";
		for (int i=0;i< newLenght - toPad.length();i++){
			tmp += withChar;
		}
		return tmp+=toPad;
		
	}
	public static String rightPad(String toPad,int newLenght,char withChar){
		String tmp=toPad;
		for (int i=0;i< newLenght - toPad.length();i++){
			tmp += withChar;
		}
		return tmp;
		
	}
}

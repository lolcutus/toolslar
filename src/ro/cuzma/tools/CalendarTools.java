package ro.cuzma.tools;
import java.util.Calendar;



/**
 * @author Laurian Cuzma
 *
 * 04.10.2005
 */
public class CalendarTools {
	public  static String calendarToTime( java.util.Date cl,String separator){
		
		int tmp = cl.getHours();
		String hour = "";
		if (tmp < 10) {
			hour = "0" + tmp+separator;
		}else{
			hour = tmp+separator;
		}
		tmp = cl.getMinutes();
		String min = "";
		if (tmp < 10) {
			min = "0" + tmp+separator;
		}else{
			min = tmp+separator;
		}
		tmp = cl.getSeconds();
		String sec = "";
		if (tmp < 10) {
			sec = "0" + tmp+"";
		}else{
			sec = tmp+"";
		}
		int offset = java.lang.Math.round(cl.getTimezoneOffset()/60);
		int rest = java.lang.Math.round(((cl.getTimezoneOffset()%60)/(float)60)*100);
		String offsetS = "";
		if (offset > 0){
			offsetS = "-" + StringTools.leftPad(""+offset, 2, '0')+StringTools.rightPad(""+rest, 2, '0');
		}else{
			offset = -1 * offset;
			offsetS = "+" + StringTools.leftPad(""+offset, 2, '0')+StringTools.rightPad(""+rest, 2, '0');
		}
		return hour+min+sec+offsetS;
	}
	public  static String calendarToString( java.util.Calendar cl,String separator){
		String year = cl.get(Calendar.YEAR) + separator;
		int tmp = cl.get(Calendar.MONTH) + 1;
		String month = "";
		if (tmp < 10) {
			month = "0" + tmp+separator;
		}else{
			month = tmp+separator;
		}
		tmp = cl.get(Calendar.DAY_OF_MONTH);
		String day = "";
		if (tmp < 10) {
			day = "0" + tmp+" ";
		}else{
			day = tmp+" ";
		}
		tmp = cl.get(Calendar.HOUR_OF_DAY);
		String hour = "";
		if (tmp < 10) {
			hour = "0" + tmp+":";
		}else{
			hour = tmp+":";
		}
		tmp = cl.get(Calendar.MINUTE);
		String min = "";
		if (tmp < 10) {
			min = "0" + tmp+":";
		}else{
			min = tmp+":";
		}
		tmp = cl.get(Calendar.SECOND);
		String sec = "";
		if (tmp < 10) {
			sec = "0" + tmp+"";
		}else{
			sec = tmp+"";
		}
		return year+month+day+hour+min+sec;
	}
	
	public  static String calendarToStringShort( java.util.Calendar cl, String separator){
		if (cl == null) return "";
		String year = cl.get(Calendar.YEAR) + "";
		int tmp = cl.get(Calendar.MONTH) + 1;
		String month = "";
		if (tmp < 10) {
			month = "0" + tmp;
		}else{
			month = tmp+"";
		}
		tmp = cl.get(Calendar.DAY_OF_MONTH);
		String day = "";
		if (tmp < 10) {
			day = "0" + tmp;
		}else{
			day = tmp + "";
		}
				return day+separator+month+separator+year;
	}
	public  static String calendarToStringShort( java.util.Date cl, String separator){
		String year = cl.getYear() + 1900 +"";
		int tmp = cl.getMonth() + 1;
		String month = "";
		if (tmp < 10) {
			month = "0" + tmp;
		}else{
			month = tmp+"";
		}
		tmp = cl.getDate();
		String day = "";
		if (tmp < 10) {
			day = "0" + tmp;
		}else{
			day = tmp + "";
		}
				return year+separator+month+separator+day;
	}
	

}


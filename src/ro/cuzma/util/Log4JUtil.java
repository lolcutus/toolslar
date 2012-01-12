package ro.cuzma.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * <p>Copyright (c) 2003 SSI Schaefer SRL and SSI Schaefer Noell GmbH</p>
 * <p>Description: </p>
 * @author <A href="mailto:Cuzma.Laurian@ssi-schaefer.ro">Laurian Cuzma, lcu</A>
 * @version $Revision: 1.20 $
 */
public class Log4JUtil {

	private final String CLASS_NAME = "ro.cuzma.util.Log4JUtil";
	private static String logFile;

	/**
	 * Initialisation of log system.
	 * @param appRoot where properties file is located
	 * @param file properties file
	 * @return true is log is initialized corectly, false otherwise
	 */
	public static boolean init( String file){
		logFile = file;
		try{
			if(file != null){
				org.apache.log4j.LogManager.resetConfiguration();
				PropertyConfigurator.configure(file + ".properties");
				return true;
			} else{
				return false;
			}

		} catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Return time in format yyyymmdd_hhMMss
	 * @return time in format yyyymmdd_hhMMss
	 */
	public static String getTimeStamp(){
		Calendar cal = new GregorianCalendar();

		String sDate = "" + zeroize(cal.get(Calendar.YEAR)) + zeroize(getMonthIndex(cal.get(Calendar.MONTH)))
			+ zeroize(cal.get(Calendar.DATE)) + "_" + zeroize(cal.get(Calendar.HOUR_OF_DAY))
			+ zeroize(cal.get(Calendar.MINUTE)) + zeroize(cal.get(Calendar.SECOND));

		return sDate;
	}

	/**
	 * return number which is less then 10 with 0 in front. For example 5 will bebome 05 and 15 will remain 15.
	 * @param n nuber to change
	 * @return String
	 */
	public static String zeroize(int n){
		if(n < 10){
			return "0" + n;
		} else{
			return "" + n;
		}
	}

	/**
	 * Get month from 1-12.
	 * @param m month to check
	 * @return month from 1-12
	 */
	public static int getMonthIndex(int m){
		if(m == Calendar.JANUARY){
			return 1;
		} else if(m == Calendar.FEBRUARY){
			return 2;
		} else if(m == Calendar.MARCH){
			return 3;
		} else if(m == Calendar.APRIL){
			return 4;
		} else if(m == Calendar.MAY){
			return 5;
		} else if(m == Calendar.JUNE){
			return 6;
		} else if(m == Calendar.JULY){
			return 7;
		} else if(m == Calendar.AUGUST){
			return 8;
		} else if(m == Calendar.SEPTEMBER){
			return 9;
		} else if(m == Calendar.OCTOBER){
			return 10;
		} else if(m == Calendar.NOVEMBER){
			return 11;
		} else if(m == Calendar.DECEMBER){
			return 12;
		} else{
			return -1;
		}
	}

	/**
	 * Reinitialisation of log system. Is use to change log runtime.
	 */
	public void reInit(){
		if(logFile != null){
			init(logFile);
		}
	}

}


/*
 * Created on 02.03.2006
 * by Laurian Cuzma
 */
package ro.cuzma.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartitionData {
	
	private String volumeLabel;
	private String volumeSerial;
	private String volumeLetter;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	// TODO Auto-generated method stub

	}
	
	public PartitionData(String volumeLetter){
		this.volumeLetter = volumeLetter;
		this.volumeSerial = getVolumeSerial();
		this.volumeLabel = getVolumeLabel();
	}

	/**
	 * @return Returns the volumeLetter.
	 */
	public String getVolumeLabel() {
		if (this.volumeLabel == null){
			this.volumeLabel =  this.getVolumeLabel(this.volumeLetter);
		}
		return this.volumeLabel;
	}

	/**
	 * @return Returns the volumeSerial.
	 */
	public String getVolumeSerial() {
		if (this.volumeSerial == null){
			this.volumeSerial =  this.getVolumeSerial(this.volumeLetter);
		}
		return this.volumeSerial;
	}
	private  String getVolumeSerialXP(String volume){
		Pattern pattern = Pattern.compile("^\\s*Volume Serial Number is\\s*(.*)$");
        
        //String osName = System.getProperty("os.name" );
        //System.out.println(osName);
        String[] cmd = {"cmd.exe", "/C", "vol " + volume + ":",};
        final Process process;
		try {
			process = Runtime.getRuntime().exec(cmd);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "unknown";
		}
       /* new Thread(){
            public void run(){
                try {
                    InputStream errorStream = process.getErrorStream();
                    BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
                    for (String line = null; (line = errorReader.readLine()) != null;){
                        System.err.println("STDERR : " + line);
                    }
                }catch (Exception e){
                    System.err.println("Problem 1, exception = " + e);
                }
            }
        }.start();*/
        
        String volumeID = null;
        //if (process != null){
	        InputStream stoutStream = process.getInputStream();
	        BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stoutStream));
	        try {
				for (String line = null; (line = stdoutReader.readLine()) != null;)
				{
				    
					//System.out.println("LINE : " + line);
				    if (volumeID == null)
				    {
				        Matcher matcher = pattern.matcher(line);
				        if (matcher.matches()){
				            volumeID = matcher.group(1);
				        	break;
				        }
				    }
				    
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				volumeID = "unknown";
			}
		//}else{
		//	volumeID = "unknown";
		//}
        //System.out.println("Volume ID = '" + volumeID + "'");

		return volumeID;
	}
	
	private  String getVolumeLabelXP(String volume){
		Pattern pattern = Pattern.compile("^\\s*Volume in drive " + volume.toUpperCase()+ " is\\s*(.*)$");
        
        //String osName = System.getProperty("os.name" );
        //System.out.println(osName);
        String[] cmd = {"cmd.exe", "/C", "vol " + volume + ":",};
        final Process process;
		try {
			process = Runtime.getRuntime().exec(cmd);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "unknown";
		}
       /* new Thread(){
            public void run(){
                try {
                    InputStream errorStream = process.getErrorStream();
                    BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
                    for (String line = null; (line = errorReader.readLine()) != null;){
                        System.err.println("STDERR : " + line);
                    }
                }catch (Exception e){
                    System.err.println("Problem 1, exception = " + e);
                }
            }
        }.start();*/
        
        String volumeID = null;
        //if (process != null){
	        InputStream stoutStream = process.getInputStream();
	        BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stoutStream));
	        try {
				for (String line = null; (line = stdoutReader.readLine()) != null;)
				{
				    
					//System.out.println("LINE : " + line);
				    if (volumeID == null)
				    {
				        Matcher matcher = pattern.matcher(line);
				        if (matcher.matches()){
				            volumeID = matcher.group(1);
				        	break;
				        }
				    }
				    
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				volumeID = "unknown";
			}
		//}else{
		//	volumeID = "unknown";
		//}
        //System.out.println("Volume ID = '" + volumeID + "'");

		return volumeID;
	}
	
	private  String getVolumeSerial(String volume){
		String osName = System.getProperty("os.name" );
		if (osName.equals("Windows XP") || osName.equals("Windows 7")){
			return getVolumeSerialXP(volume);
		}else{
			return null;
		}
	}
	private  String getVolumeLabel(String volume){
		String osName = System.getProperty("os.name" );
		if (osName.equals("Windows XP")){
			return getVolumeLabelXP(volume);
		}else{
			return null;
		}
	}

	/**
	 * @return Returns the volumeLetter.
	 */
	public String getVolumeLetter() {
		return this.volumeLetter;
	}
}

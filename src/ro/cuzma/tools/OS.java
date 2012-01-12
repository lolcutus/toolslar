/*
 * Created on 28.02.2006
 * by Laurian Cuzma
 */
package ro.cuzma.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OS {
	
	private static Vector volumes = new Vector();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	// TODO Auto-generated method stub
		System.out.println(OS.getVolumeSerial("e"));
		System.out.println(OS.getVolumeLabel("E"));

	}
	
	public static String getVolumeSerial(String volume){
		PartitionData tmp;
		for(int i = 0;i < volumes.size(); i++){
			System.out.println(i);
			tmp = (PartitionData)volumes.get(i);
			if (tmp.getVolumeLetter().equalsIgnoreCase(volume)){
				return tmp.getVolumeSerial(); 
			}
		}
		tmp = new PartitionData(volume);
		volumes.add(tmp);
		return tmp.getVolumeSerial();
	}
	public static String getVolumeLabel(String volume){
		PartitionData tmp;
		for(int i = 0;i < volumes.size(); i++){
			tmp = (PartitionData)volumes.get(i);
			if (tmp.getVolumeLetter().equalsIgnoreCase(volume)){
				return tmp.getVolumeLabel(); 
			}
		}
		tmp = new PartitionData(volume);
		volumes.add(tmp);
		return tmp.getVolumeLabel();
	}
}

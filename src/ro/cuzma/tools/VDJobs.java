package ro.cuzma.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Vector;

public class VDJobs {
	private String			filesPath;
	private String			wavPath  = "Wav path not set";
	private final String	outDir			= "out";
	private final String	jobsFile		= "VirtualDub.jobs";
	private final String	jobsFileSetting	= "VirtualDub.settings";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length == 1) {
			VDJobs vd = new VDJobs(args[0]);
			vd.run();
		}else if (args.length == 2){
			VDJobs vd = new VDJobs(args[0],args[1]);
			vd.run();
		}		else {
			System.out.println("Please provide the path to avi");
		}
	}

	public VDJobs(String filesPath) {
		this.filesPath = filesPath;
	}
	public VDJobs(String filesPath, String wavPath) {
		this.filesPath = filesPath;
		this.wavPath = wavPath;
	}

	private void run() {
		Vector files = FileTools.getFilesInDir(filesPath, "avi");
		try {
			File jobFile = new File(filesPath + "\\" + jobsFile);
			File settings = new File(filesPath + "\\" + jobsFileSetting);
			System.out.println(settings.getCanonicalPath());
			RandomAccessFile rin = new RandomAccessFile(settings, "r");
			
			String toWrite = "";
			
			String c = rin.readLine();
			while (c != null) {
				toWrite += c  + "\r\n";
				c = rin.readLine();
			}
			rin.close();
			File oDir = new File(filesPath + "\\" + outDir);
			if (!oDir.isDirectory()) {
				oDir.mkdir();
			}
			jobFile.delete();
			jobFile.createNewFile();
			RandomAccessFile ra = new RandomAccessFile(jobFile, "rw");
			ra.writeBytes("//	VirtualDub job list (Sylia script format)\r\n");
			ra
					.writeBytes("//	This is BookTableHeaderListener program generated file -- edit at your own risk.\r\n");
			ra.writeBytes("//\r\n");
			ra.writeBytes("//	$numjobs " + files.size() + "\r\n");
			ra.writeBytes("//\r\n");
			File tmp;
			int job = 0;
			String srcFile = null;
			String destFile = null;
			String wavFile = null;
			String jobSetting = null;
			for (int i = 0; i < files.size(); i++) {
				tmp = (File) files.get(i);
				job = i + 1;
				srcFile = tmp.getAbsolutePath();
				wavFile = wavPath + "\\" + tmp.getName().substring(0,tmp.getName().lastIndexOf(".")).replace('.','_') + ".wav";
				destFile = tmp.getParent() + "\\" + outDir 		+ "\\" + tmp.getName();
				jobSetting = toWrite.replace("#jobNr#", job+"");
				jobSetting = jobSetting.replace("#srcFile#", srcFile);
				jobSetting = jobSetting.replace("#destFile#", destFile);
				jobSetting = jobSetting.replace("#wavFileE#", duplSlash(wavFile));
				jobSetting = jobSetting.replace("#srcFileE#", duplSlash(srcFile));
				jobSetting = jobSetting.replace("#destFileE#", duplSlash(destFile));
				ra.writeBytes(jobSetting);
				}
			ra.close();
			System.out.println("Done: " + jobFile.getAbsolutePath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String duplSlash(String text) {
		String res = "";
		int pos = text.indexOf("\\");
		while (pos != -1) {
			res = res + text.substring(0, pos + 1) + "\\";
			text = text.substring(pos + 1);
			pos = text.indexOf("\\");
		}
		return res + text;
	}
}

package ro.cuzma.tools;

//import com.drew.imaging.jpeg.JpegMetadataReader;
//import com.drew.imaging.jpeg.JpegProcessingException;
//import com.drew.metadata.*;


//import com.drew.metadata.exif.ExifDirectory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.io.IOException;

import java.util.*;




/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Laurian Cuzma
 * @version 1.0
 */
public class FileTools {
    public int p1;
	public int p2;
	public int p3;
	public int p4;
	public int p5;
	public int p6;
	public int p7;

  public FileTools() {
  }
  public static void main(String[] args) {
    /*FileTools fileTools1 = new FileTools();
	String option = args[0];
	String dir = "";
	if (args.length > 1){
	   dir = args[1];
	}

	String modifica = "";
	if (option.equals("-f")){
	  if (args.length > 7){
	    fileTools1.p1 = (new Integer(args[1])).intValue();
	    fileTools1.p2 = (new Integer(args[2])).intValue();
	    fileTools1.p3 = (new Integer(args[3])).intValue();
	    fileTools1.p4 = (new Integer(args[4])).intValue();
	    fileTools1.p5 = (new Integer(args[5])).intValue();
	    fileTools1.p6 = (new Integer(args[6])).intValue();
	    fileTools1.p7 = (new Integer(args[7])).intValue();
	  }
	  if (args.length > 8){
	 	   modifica = args[8];
	  }
	  File dirF = new File(dir);
	  File[] vec = dirF.listFiles();
	  try{
	    File aaa = null;
		for (int i = 0; i < vec.length; i++){

		  if (vec[i].isDirectory() != true){
			  aaa = new File(dir + "\\" + fileTools1.rename(vec[i].getName()));
			  if (modifica != null && modifica.equals("DA"))
				vec[i].renameTo(aaa);
			  System.out.println(fileTools1.rename(vec[i].getName()));
		  }else{
			System.out.println(vec[i].getName());
		  }

		}
	  }catch(Exception e){}
	}else if (option.equals("-c")){
	  String deSchimbat = args[2];
	   if (args.length > 3){
	 	   modifica = args[3];
	  }
	  File dirF = new File(dir);
	  File[] vec = dirF.listFiles();
	  try{
	    File aaa = null;
		for (int i = 0; i < vec.length; i++){

		  if (vec[i].isDirectory() != true){
			  String rez = fileTools1.change(vec[i].getName(),deSchimbat);
			  System.out.println(rez);
			  if (modifica != null && modifica.equals("DA")){
				aaa = new File(dir + "\\" + rez);
				vec[i].renameTo(aaa);
			  }

		  }else{
			System.out.println(vec[i].getName());
		  }

		}
	  }catch(Exception e){}
	}else if (option.equals("-BookTableHeaderListener")){
	  String numar = args[2];
	  String grup = args[3];

	   if (args.length > 4){
	 	   modifica = args[4];
	  }
	  File dirF = new File(dir);
	  File[] vec = dirF.listFiles();
	  try{
	    File aaa = null;
		for (int i = 0; i < vec.length; i++){

		  if (vec[i].isDirectory() != true){
			  String rez = fileTools1.format(vec[i].getName(),numar,grup);
			  System.out.println(rez);
			  if (modifica != null && modifica.equals("DA")){
				aaa = new File(dir + "\\" + rez);
				vec[i].renameTo(aaa);
			  }

		  }else{
			System.out.println(vec[i].getName());
		  }

		}
	  }catch(Exception e){}
	}else if (option.equals("-?")){
	  System.out.println("-c pentru BookTableHeaderListener schimba numarul de ordine: -c \"dir from where to take files\" 0010111 DA");
	  System.out.println("-f pentru BookTableHeaderListener formata numele: -f \"dir from where to take files\" 0 6 7 16 17 20 21 DA");
	  System.out.println("-BookTableHeaderListener pentru BookTableHeaderListener creea sirul: -BookTableHeaderListener \"dir from where to take files\" nrDeOrdine StringGrup");
	  System.out.println("-?help");
	}*/
  	File jpegFile = new File("F:\\tmp1\\aaa.jpg");
  	/*try {
		Metadata metadata = JpegMetadataReader.readMetadata(jpegFile);
		Directory exifDirectory = metadata.getDirectory(ExifDirectory.class);
		String cameraMake = exifDirectory.getString(ExifDirectory.TAG_MAKE);
		String cameraModel = exifDirectory.getString(ExifDirectory.TAG_MODEL);
		String dateTime = exifDirectory.getString(ExifDirectory.TAG_DATETIME);
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.set((new Integer(dateTime.substring(0,4))).intValue(),
				(new Integer(dateTime.substring(5,7))).intValue(),
				(new Integer(dateTime.substring(8,10))).intValue(),
				(new Integer(dateTime.substring(11,13))).intValue(),
				(new Integer(dateTime.substring(14,16))).intValue(),
				(new Integer(dateTime.substring(17,19))).intValue());
		System.out.println(cameraMake + ":" + dateTime + "->" + larry.tools.Tools.calendarToString(gc,"."));
			FileTools.replaceInFile(jpegFile,dateTime,"qqqq===qqqq",true);


	} catch (JpegProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
  }

  private String rename(String deSchimbat){

	String s = "[";
	String e = "]";

	String deIntors = s + deSchimbat.substring(p1,p2) + e + s + deSchimbat.substring(p3,p4) + e + s + deSchimbat.substring(p5,p6)+ e + s +  deSchimbat.substring(p7,deSchimbat.lastIndexOf(".")) + e + deSchimbat.substring(deSchimbat.lastIndexOf("."));
	return deIntors;
  }

   private String change(String deSchimbat,String newNumber){


	String deIntors = deSchimbat.substring(0,1) + newNumber + deSchimbat.substring(7);
	return deIntors;
  }
     private String format(String deSchimbat,String numar,String grup){


	String deIntors = "[" + numar + "][" + grup + "][" + deSchimbat.substring(0,3) + "][" + deSchimbat.substring(4,deSchimbat.lastIndexOf(".")) + "]" + deSchimbat.substring(deSchimbat.lastIndexOf("."));
	return deIntors;
  }
     
    public static Vector getFilesInDir(String dir, String extension){
    	File curDir = new File(dir);
		String fileName = "";
		Vector files = new Vector();
		if(curDir.isDirectory()){
			File[] allFielsInDir = curDir.listFiles();
			for(int i = 0; i < allFielsInDir.length; i++){
				if(!allFielsInDir[i].getAbsoluteFile().isDirectory()){
					fileName = allFielsInDir[i].getName();
					if(fileName.toLowerCase().endsWith("."+extension)){
						files.add(allFielsInDir[i]);
					}
				}
			}
		}
		return files;
    }
    public static void replaceInFile(File f, String toFound, String toReplace, boolean overwrite){
    	try {
    		int whatRead[] = new int[toFound.length()];
    		int curInt;
			FileInputStream fr = new FileInputStream(f);
			try {
				File rezFileDir = new File(f.getParent() + File.separator + "result_replace");
				
				rezFileDir.mkdir();
				//rezFileDir.close();
				FileOutputStream rez = new FileOutputStream(rezFileDir.getAbsolutePath() + File.separator + f.getName());
				System.out.print(rezFileDir.getAbsolutePath() + File.separator + f.getName());
				System.out.print("replace: -" + toFound + "- with: -" + toReplace + "-");
				curInt = fr.read();
				boolean init = true;
				int contor = 0;
				while (curInt != -1){
					if (init){
						whatRead[contor] = curInt;
						/*for (int i=0; i <=contor;i++){
							System.out.print((char)whatRead[i]);

						}
						System.out.println("-");*/
						contor++;
						if (contor ==  toFound.length()){
							init = false;
						}else{
							curInt = fr.read();
						}
					}else{
						boolean match = true;
						//String test = "";
						for (int i=0; i <toFound.length();i++){
							char tmp = toFound.charAt(i);
							//System.out.print((char)whatRead[i]);
							//test += (char)whatRead[i];
							if (whatRead[i] != (int)tmp){
								match = false;
							}
						}
						//System.out.println("");
						//if (test.endsWith("2005:07:30 13:42:54")){
						//	System.out.println("URA-"+toFound);
						//}
						if (match){
							for (int i=0; i <toReplace.length();i++){
								//System.out.println("W-"+(char)toReplace.charAt(i));
								rez.write(toReplace.charAt(i));
							}
							contor = 0;
							init = true;
							curInt = fr.read();
						}else{
							//System.out.println("W-"+(char)whatRead[0]);
							rez.write(whatRead[0]);
							for(int i = 1;i <toFound.length();i++){
								whatRead[i-1]=whatRead[i];
							}
							curInt = fr.read();
							if (curInt != -1){
								whatRead[contor-1] = curInt;
							}
						}
					}
					
				}
				for (int i = 0; i <contor;i++){
					//System.out.println("W-"+(char)curInt);
					rez.write(whatRead[i]);
				}
				rez.close();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
	public static Vector parseDir(String dir, String extension) throws Exception{
		Vector files =new Vector();
		int test = 0;
		File curDir = new File(dir);
		String fileName = "";
		if(curDir.isDirectory()){
			File[] allFielsInDir = curDir.listFiles();
			for(int i = 0; i < allFielsInDir.length; i++){
				if(!allFielsInDir[i].getAbsoluteFile().isDirectory()){
					test++;
					fileName = allFielsInDir[i].getName();
					if(fileName.toLowerCase().endsWith("." + extension.toLowerCase())){
						files.add(allFielsInDir[i]);
					}
				}
			}
			
		}
		return files;
	}
	public static void copyfile(String srFile, String dtFile){
	    try{
	      File f1 = new File(srFile);
	      File f2 = new File(dtFile);
	      InputStream in = new FileInputStream(f1);
	      
	      //For Append the file.
//	      OutputStream out = new FileOutputStream(f2,true);

	      //For Overwrite the file.
	      OutputStream out = new FileOutputStream(f2);

	      byte[] buf = new byte[1024];
	      int len;
	      while ((len = in.read(buf)) > 0){
	        out.write(buf, 0, len);
	      }
	      in.close();
	      out.close();
	      System.out.println("File copied.");
	    }
	    catch(FileNotFoundException ex){
	      System.out.println(ex.getMessage() + " in the specified directory.");
	      System.exit(0);
	    }
	    catch(IOException e){
	      System.out.println(e.getMessage());      
	    }
	  }
}
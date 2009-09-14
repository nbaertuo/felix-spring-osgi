import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author Administrator
 * 
 */
public class FileUtils {

	public static List<File> getFilesByEndName(File dir, String endWithName) {
		if (dir.isFile()){
			return null;
		}
		List<File> fileRs=new ArrayList<File>();
		getFiles(dir, endWithName, fileRs);
		
		return fileRs;
	}
	
	private static void getFiles(File dir, String endWithName,List<File> fileRs){
		if (dir.isFile()){
			return;
		}
		File[] files = dir.listFiles();
		if (files != null) {
			for (File file : files) {
                if(file.isFile()&&file.getName().endsWith(endWithName)){
                	fileRs.add(file);
                }
                if(file.isDirectory()){
                	getFilesByEndName(file, endWithName);
                }
                
			}
		}
	}

}

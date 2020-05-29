package utilities;

import java.io.File;

public class RemoveLogFiles {
	
	static int   count=0;
	
	public static void removeLogFile() {
		
		File f=new File(System.getProperty("user.dir")+"\\src\\test\\resources\\logs");
		
		//System.out.println("filepath exists "+f.exists());
		//System.out.println("is directory "+f.isDirectory());
		File[] fileList=f.listFiles();
		if(fileList.length > 0)
			
		{
		for(File file:fileList)
		{
			//count++;
			//System.out.println(count+"----"+file);
			if(file.isFile())
			{
				file.delete();
			}
	
		}
		}
		
		
	}

}

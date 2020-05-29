package utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.Status;





public class TestUtil {
	
	public static String screenshotPathExtent;
	public static String screenshotPathSurefire;
	public static int errorCount=0;
	public static String screenshotName;
	
	public static void captureScreenshot(WebDriver driver,String methodName)
	{
		
		File sourceFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//screenshotName = d.toString().replace(":", "_").replace(" ", "_")+"_error.jpg";
		errorCount++;
		screenshotName="error"+errorCount+".jpg";
		screenshotPathExtent=System.getProperty("user.dir")+"\\src\\test\\resources\\screenshots\\"+screenshotName;
		screenshotPathSurefire=".\\target\\surefire-reports\\html\\"+screenshotName;
		
			try {
				FileUtils.copyFile(sourceFile, new File(screenshotPathExtent));
				FileUtils.copyFile(sourceFile, new File(screenshotPathSurefire));
				Log.info("Screenshots taken successfully for method --"+methodName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.error("Screenshot not copied");
				Reporter.log("Screenshot not copied");
				//DriverManager.testReport.get().log(Status.FAIL, "Screenshot not taken from TestUtil class");
			}
	}
	
	
	@DataProvider(name="dp")
	public Object[][]  getExcelHashData(Method m) 
	{
		String sheetName=m.getName();
		Object[][]  data=null;
	    int rowcount=DriverManager.excelRead.getRowCount(sheetName);
        int colCount=DriverManager.excelRead.getColCount(sheetName);
        HashMap<String,String> table=null;
		data=new Object[rowcount][1]; //data has 1 col and number of rows present in excel except the header
		for(int i=1;i<=rowcount;i++)// excel data starts from 1,0 is header
		{
			table=new HashMap<String,String>(); //create new table for each data row
			for(int j=0;j<colCount;j++) //iterate over col
			{
				
				table.put(DriverManager.excelRead.getDesiredData(sheetName,0, j),DriverManager.excelRead.getDesiredData(sheetName, i, j));
			    data[i-1][0]=table; //assign each table to data object (3)
			    //System.out.println(ep.getDesiredData(sheetName,0, j)+".."+ep.getDesiredData("testdata", i, j));
			}
			
		}
		return data;
	}
	
	@DataProvider(name="dp2")
	public Object[][] getMaxData(Method m){
		String sheetName=m.getName();
		Object[][]  data=null;
	    int rowcount=DriverManager.excelRead.getRowCount(sheetName);
        int colCount=DriverManager.excelRead.getColCount(sheetName);
        HashMap<String,String> table=null;
		data=new Object[rowcount][1]; //data has 1 col and number of rows present in excel except the header
		for(int i=1;i<=rowcount;i++)// excel data starts from 1,0 is header
		{
			table=new HashMap<String,String>(); //create new table for each data row
			for(int j=0;j<colCount;j++) //iterate over col
			{
				
				table.put(DriverManager.excelRead.getDesiredData(sheetName,0, j),DriverManager.excelRead.getDesiredData(sheetName, i, j));
			    data[i-1][0]=table; //assign each table to data object (3)
			    //System.out.println(ep.getDesiredData(sheetName,0, j)+".."+ep.getDesiredData("testdata", i, j));
			}
			
		}
		return data;
}
	
	@DataProvider(name="dp1")
	public Object[][] getData(Method m){
		
		
		
		String sheetName=m.getName();
	
		 int rowCount = DriverManager.excelRead.getRowCount(sheetName); 
		 int colCount = DriverManager.excelRead.getColCount(sheetName);
		 
		Object[][] data = new Object[rowCount -1][colCount]; 
		 for (int i = 2; i <= rowCount; i++) { 
			 for (int j = 0; j <colCount; j++) {
		 
		 data[i-2][j] = DriverManager.excelRead.getDesiredData(sheetName, i, j); }
		 
		 }
		 Log.debug("Data provided successfully");
		 Reporter.log("DataProvided Executed successfully");
		 //CustomListener.test.log(Status.INFO, "Screenshot is taken from TestUtil");
		return data;

}
	
	public static boolean isTestRunnable(String testName,ExcelReading excelRead)
	{
		String sheetName="test_suite";
		int rows=excelRead.getRowCount(sheetName);
		for(int rowNum=2;rowNum<=rows;rowNum++)
		{
		if(testName.equalsIgnoreCase(excelRead.getDesiredData(sheetName, rowNum, "TCID")))
		{
			if(excelRead.getDesiredData(sheetName, rowNum, "RunMode").equalsIgnoreCase("Y"))
			{
				return true;
			}
			else
				return false;
		}
		 
		}
		return false;
	}
}

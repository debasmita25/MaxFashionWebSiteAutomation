package utilities;

import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;


public class DriverManager {
	
	private WebDriver driver;
	private Properties p=new Properties();
	private FileInputStream fis;
	public static ExcelReading excelRead = new ExcelReading(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static ExtentReports extent=ExtentManager.createInstance();
	public static ThreadLocal<ExtentTest> testReport=new ThreadLocal<ExtentTest>();
	public static WebDriverWait wait;
    static Date d=new Date();
	static String value=d.toString().replace(":", " ");
	public WebDriver setUp()
	{
		RemoveLogFiles.removeLogFile();
		System.setProperty("current.date", value);
		PropertyConfigurator.configure(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\log4j.properties");	
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
	try {
	  fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
	  p.load(fis);
	}
	catch(Exception e)
	{
		System.out.println("IO Exception -- "+e.getMessage());
	}
	  String browserName=p.getProperty("browser");
	  if(browserName.equalsIgnoreCase("chrome"))
	  {
		  //WebDriverManager.chromedriver().setup();
		  System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executablefiles\\chromedriver.exe");
		  ChromeOptions option=new ChromeOptions();
		  option.addArguments("--disable-notifications");
		  driver=new ChromeDriver(option);
		  
		  Log.info("Launching Chrome browser");
	  }
	  else if (browserName.equalsIgnoreCase("firefox"))
	  {
		  
	  }
	  else if (browserName.equalsIgnoreCase("ie"))
	  {
		  
	  }
	  Log.info("Assigning implicit timeout to browser");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
	  wait=(WebDriverWait)new WebDriverWait(driver,30).ignoring(StaleElementReferenceException.class);
	  Log.info("maximizing the browser");
	  driver.manage().window().maximize();
	  return driver;
	}

	
	public void tearDown()
	{
		if(driver!=null)
		{
			driver.quit();
			Log.info("quitting browser");
		}
	}


}

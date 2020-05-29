package testcases;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import pages.actions.MaxHomePage;
import utilities.DriverManager;
import utilities.Log;


public class MaxMainPageTest  {

	DriverManager dm ;
	public WebDriver driver;
				
    @BeforeTest(groups="main")
	public void runFirst()
	{
		dm = new DriverManager();
		driver = dm.setUp();
		driver.get("https://www.maxfashion.in");
		Log.info("Launching URL");
		
	}
	@Test(groups="main")
	public void verifyMaxMainTotalList()
	{
		
		MaxHomePage mhp = new MaxHomePage(driver);
		Log.debug("Verifying the total number of categories in the main page");
		Assert.assertEquals(mhp.getCategoryList().size(), 4);
		Log.info("Total number of categories are matched successfully");
		DriverManager.testReport.get().log(Status.PASS, "Total number of categories are matched successfully");
		
		
	}
	
	static int index=0;
	
	@Test(dataProviderClass = utilities.TestUtil.class,dataProvider="dp",groups="main")
	public void verifyMaxMainCategoryList(Map<String,String> data) {
		
		MaxHomePage mhp = new MaxHomePage(driver);
		Log.debug("Verifying actual list of categories is same as expected ");
        String expected=data.get("categories");
        List<WebElement> listActual=mhp.getCategoryList();
        String actual=listActual.get(index).getText();
        index ++;
		/*
		 * List<String> expectedList = new ArrayList(); expectedList.add("SHOP POMEN");
		 * expectedList.add("SHOP MEN"); expectedList.add("SHOP GIRLS");
		 * expectedList.add("SHOP BOYS"); List<String> actualList = new ArrayList(); for
		 * (WebElement element : mhp.getCategoryList()) {
		 * actualList.add(element.getText()); }
		 * DriverManager.log.info("Actual list of categories is successfully validated");
		   DriverManager.testReport.get().log(Status.PASS, "Actual list of categories is successfully validated");
		
		 */
		Assert.assertEquals(actual,expected);
        
		Log.info(actual+"- category is successfully validated");
		DriverManager.testReport.get().log(Status.PASS, actual+"- category is successfully validated");
		
		
		
	}
	

	
@AfterTest(groups="main")
public void quitBrowser()
{
	driver.quit();
	dm.tearDown();
    dm=null;
	
}
	
}

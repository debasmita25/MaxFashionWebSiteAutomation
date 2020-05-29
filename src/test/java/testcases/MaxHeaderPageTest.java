package testcases;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import pages.actions.MaxBasketPage;
import pages.actions.MaxHeaderPage;
import pages.actions.MaxPreBasketPage;
import pages.actions.MaxSearchedProductPage;
import pages.actions.MaxSelectedProductPage;
import utilities.DriverManager;
import utilities.Log;

public class MaxHeaderPageTest {

	DriverManager dm;
	public WebDriver driver;

	MaxHeaderPage mhp;
	MaxPreBasketPage mpbp;
	MaxBasketPage mbp ;
	static int totalProductAdded=0;

	@BeforeTest
	public void runFirst() {
		dm = new DriverManager();
		driver = dm.setUp();
		driver.get("https://www.maxfashion.in");
		Log.info("Launching URL");
	}

	@Test(groups = "header")
	public void verifyMaxLogoInHeader() {
		mhp = new MaxHeaderPage(driver);

		Log.debug("verify Max Logo is present in Header Page");
		Assert.assertTrue(mhp.isMaxLogoPresent().isDisplayed(), "MAX logo NOT Present");
		Log.info("Max Logo is present in Header Page -successfully validate");
		DriverManager.testReport.get().log(Status.PASS, "Max Logo is present in Header Page -successfully validate");

	}

	@Test(dataProviderClass = utilities.TestUtil.class, dataProvider = "dp2", groups = "header")
	public void verifyWomenProductSelection(Map<String, String> data) {
		totalProductAdded++;
		mhp.isMaxLogoPresent().click();
		mhp = new MaxHeaderPage(driver);
		System.out.println(data.get("TCDATA1"));

		mhp.moveToCategory(data.get("TCDATA1"));
		System.out.println(data.get("TCDATA2"));
		mhp.moveToSubcategory(data.get("TCDATA2"));
		mhp.clickOnProduct(data.get("TCDATA3"));
		MaxSearchedProductPage mspp = new MaxSearchedProductPage(driver);
		mspp.selectSize(data.get("TCDATA5"));
		System.out.println(data.get("TCDATA5"));

		System.out.println(mspp.getTotalProducts());
		Log.debug("Verifying the total products is displayed");

		Assert.assertTrue(mspp.getTotalProducts().isDisplayed());
		Log.info("Total products are displayed successfully");
		DriverManager.testReport.get().log(Status.PASS, "Total products are displayed successfully");

		mspp.selectByProductName(data.get("TCDATA4"));
		MaxSelectedProductPage mspp1 = new MaxSelectedProductPage(driver);
		Log.debug("Verifying the retrieved product is same searched");

		Assert.assertEquals(mspp1.getProductName(), data.get("TCDATA4"));

		Log.info("Verified the retrieved product is same searched successfully");
		DriverManager.testReport.get().log(Status.PASS, "Verified the retrieved product is same searched successfully");

		mspp1.clickSize(data.get("TCDATA5"));
		mspp1.addToBasket();
		mpbp = new MaxPreBasketPage(driver);
		System.out.println("PRODUCT ADDED TO THE BASKET--------" + mpbp.getSuccessMsg());
		mpbp.clickCheckoutNow();

		
		/*
		 * if(!(mbp.getTotalListItem()>1)) {
		 * System.out.println("----------------ITEM DESCRIPTION ----------------");
		 * System.out.println(mbp.getItemDescription() + "-----------" +
		 * mbp.getProductName()); listKeys = mbp.getItemKeys(); listValues =
		 * mbp.getItemValues(); for (int i = 0; i < mbp.getItemKeys().size(); i++) {
		 * System.out.println(listKeys.get(i).getText() + "---------" +
		 * listValues.get(i).getText()); }
		 * 
		 * System.out.println(mbp.getPriceTitle() + "----------" + mbp.getPrice());
		 * System.out.println(mbp.getQtyTitle() + "------------" + mbp.getQty());
		 * System.out.println(mbp.getPriceTotalTitle() + "---------" +
		 * mbp.getTotalPrice()); }
		 */
		
		
	}
   @Test(dependsOnMethods="verifyWomenProductSelection")
	public void verifyBasketInformation()
	{
		mbp = new MaxBasketPage(driver);
		System.out.println("-----------------TOTAL ITEM IN BASKET--------------------");

		System.out.print(mbp.getItemTotal());

		System.out.println();
		System.out.println("-------------------ITEM PRICE BREAKUP-----------------------");
		List<WebElement> listKeys = mbp.getItemPriceBreakupsKeys();
		List<WebElement> listValues = mbp.getItemPriceBreakupsValues();
		for (int i = 0; i < mbp.getItemPriceBreakupsKeys().size(); i++) {
			System.out.println(listKeys.get(i).getText() + "---------" + listValues.get(i).getText());
		}

		System.out.println(mbp.getTotalBreakupKey() + "----------" + mbp.getTotalBreakupPrice());
		
		for(int i=0;i<mbp.getTotalListItem();i++)
		{
			List<WebElement> listPnames=mbp.getProductNames();
			//List<WebElement> listCTitle=mbp.getColorKeys();
			List<WebElement> listCValues=mbp.getColorValues();
			//List<WebElement> listSizeTitle=mbp.getSizeKeys();
			List<WebElement> listSizeValues=mbp.getSizeValues();
			List<WebElement> listQuantities=mbp.getQtys();
			List<WebElement> listTotalPrices=mbp.getTotalPrices();
			
			System.out.println("----------------ITEM DESCRIPTION ----------------");
			System.out.println("ProductName : "+listPnames.get(i).getText()+" Color : "+listCValues.get(i).getText()+" Size : "+listSizeValues.get(i).getText());
			System.out.println("Quantity : "+listQuantities.get(i).getText());
			System.out.println("TotalPrice : "+listTotalPrices.get(i).getText());
			
			
		}
		
		Assert.assertEquals(mbp.getTotalListItem(), totalProductAdded);
	}
	
	@AfterTest
	public void quitBrowser()

	{
		//driver.quit();
		//dm.tearDown();
		//dm = null;
	}

}

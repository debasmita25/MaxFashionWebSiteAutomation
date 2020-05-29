package rough;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import pages.actions.MaxBasketPage;
import pages.actions.MaxHeaderPage;
import pages.actions.MaxPreBasketPage;
import pages.actions.MaxSearchedProductPage;
import pages.actions.MaxSelectedProductPage;
import utilities.DriverManager;

public class RoughWork {
	static WebDriver  driver;
	public static void main(String[] args) {
		
		DriverManager dm=new DriverManager();
		driver=dm.setUp();
		
		driver.get("https://www.maxfashion.in");
		
		//List<WebElement> categoryList=driver.findElements(By.cssSelector(".MuiGrid-root.jss634.MuiGrid-item.MuiGrid-grid-xs-true button"));
		
		/*
		 * for(WebElement getList:categoryList) { System.out.println(getList.getText());
		 * }
		 */
		
		MaxHeaderPage mhp=new MaxHeaderPage(driver);
		mhp.moveToCategory("Women");
		mhp.moveToSubcategory("Essentials");
		
		mhp.clickOnProduct("Tops & Tees");
	    MaxSearchedProductPage mspp=new MaxSearchedProductPage(driver);
	    mspp.selectSize("L");
	    mspp.selectByProductName("MAX Striped Round Neck T-shirt");
	    MaxSelectedProductPage mspp1=new MaxSelectedProductPage(driver);
	    Assert.assertEquals(mspp1.getProductName(), "MAX Striped Round Neck T-shirt");
	    mspp1.clickSize("L");
		mspp1.addToBasket();
		 MaxPreBasketPage mpbp=new MaxPreBasketPage(driver);
		 System.out.println("PRODUCT ADDED TO THE BASKET--------"+mpbp.getSuccessMsg());
		 mpbp.clickCheckoutNow();
		 
		 MaxBasketPage mbp=new MaxBasketPage(driver);
		 System.out.println("-----------------TOTAL ITEM IN BASKET--------------------");
		
		 System.out.print(mbp.getItemTotal());
		 
		 System.out.println();
		 System.out.println("-------------------ITEM PRICE BREAKUP-----------------------");
		 List<WebElement>  listKeys=mbp.getItemPriceBreakupsKeys();
		 List<WebElement>  listValues=mbp.getItemPriceBreakupsValues();
		 for(int i=0;i< mbp.getItemPriceBreakupsKeys().size();i++)
		 {
		 System.out.println(listKeys.get(i).getText()+"---------"+listValues.get(i).getText());
		 }
		 
		 System.out.println(mbp.getTotalBreakupKey()+"----------"+mbp.getTotalBreakupPrice());
		 System.out.println("----------------ITEM DESCRIPTION ----------------");
		 System.out.println(mbp.getItemDescription()+"-----------"+mbp.getProductName());
		 listKeys=mbp.getItemKeys();
		 listValues=mbp.getItemValues();
		 for(int i=0;i< mbp.getItemKeys().size();i++)
		 {
		 System.out.println(listKeys.get(i).getText()+"---------"+listValues.get(i).getText());
		 }
		 
		 System.out.println(mbp.getPriceTitle()+"----------"+mbp.getPrice());
		 System.out.println(mbp.getQtyTitle()+"------------"+mbp.getQty());
		 System.out.println(mbp.getPriceTotalTitle()+"---------"+mbp.getTotalPrice());
		
		 dm.tearDown();
	}

}

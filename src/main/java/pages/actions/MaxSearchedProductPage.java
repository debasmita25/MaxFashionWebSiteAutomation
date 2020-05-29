package pages.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.locators.MaxSearchedProductPageLocators;
import utilities.DriverManager;

public class MaxSearchedProductPage {

	MaxSearchedProductPageLocators maxSearchedProductPageLocators;

	public MaxSearchedProductPage(WebDriver driver) {
		this.maxSearchedProductPageLocators = new MaxSearchedProductPageLocators();
		PageFactory.initElements(driver, this.maxSearchedProductPageLocators);
	}

	public void selectSize(String size) {

	 DriverManager.wait.until(ExpectedConditions.visibilityOf(maxSearchedProductPageLocators.size));
		maxSearchedProductPageLocators.size.click();
		//DriverManager.wait.until(ExpectedConditions.visibilityOfAllElements(maxSearchedProductPageLocators.sizeList));
		for (WebElement element : maxSearchedProductPageLocators.sizeList) {
		    try {
		  
			if (element.getAttribute("value").equalsIgnoreCase(size)) {
              element.click();
              DriverManager.wait.until(ExpectedConditions.visibilityOf(maxSearchedProductPageLocators.Ldisplay));
  			
			}
		    }catch(Exception e)
		    {
		    	//System.out.println("HELLLO");
		    }
		}
	}
	
	public WebElement getTotalProducts()
	{
		//System.out.println("inside action method "+maxSearchedProductPageLocators.productsNo.getText());
		return maxSearchedProductPageLocators.productsNo;
	}
	
	public void selectByProductName(String productName)
	{
		
		//DriverManager.wait.until(ExpectedConditions.visibilityOf(maxSearchedProductPageLocators.firstItem));
		for(WebElement ele:maxSearchedProductPageLocators.productNameWomenList)
		{
			try{if(ele.getText().equalsIgnoreCase(productName))
			{
				ele.click();
			}
			}catch(Exception e)
			{
				//System.out.println("bye");
			}
		}
	}

}

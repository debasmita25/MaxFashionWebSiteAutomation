package pages.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.locators.MaxHeaderPageLocators;
import utilities.DriverManager;

public class MaxHeaderPage {
	
	MaxHeaderPageLocators maxHeaderPageLocators;
	Actions action;
	public MaxHeaderPage(WebDriver driver)
	{
		action=new Actions(driver);
		this.maxHeaderPageLocators=new MaxHeaderPageLocators();
		PageFactory.initElements(driver,maxHeaderPageLocators);
	}
	
	public WebElement isMaxLogoPresent()
	{
		return maxHeaderPageLocators.maxHeaderLogo;
	}
	
	public void moveToCategory(String category)
	{
		for(WebElement e:maxHeaderPageLocators.listInMain)
		{
			if(e.getText().equalsIgnoreCase(category))
			{
				//action.moveToElement(e).build().perform();
				e.click();
			}
		}
	}
	public void moveToSubcategory(String subcategory)
	{
		for(WebElement e:maxHeaderPageLocators.listInWomen)
		{
			if(e.getText().equalsIgnoreCase(subcategory))
			{
				action.moveToElement(e).build().perform();
			}
		}
	}
	
	public void clickOnProduct(String product)
	{
		
		if(product.equalsIgnoreCase("Lingerie"))
		{
			
			DriverManager.wait.until(ExpectedConditions.visibilityOf(maxHeaderPageLocators.lingerieHeader));
		
			
		}else if(product.equalsIgnoreCase("Essentials"))
		{DriverManager.wait.until(ExpectedConditions.visibilityOf(maxHeaderPageLocators.essentialsHeader));
		}
		
		for(WebElement e:maxHeaderPageLocators.listOfProducts)
		{
		try {	if(e.getText().equalsIgnoreCase(product))
			{
				//System.out.println(e.getText());
				e.click();
			}
		}
		
		catch(Exception exp)
		{
			//System.out.println(exp.getMessage());
		}
	}

}
}
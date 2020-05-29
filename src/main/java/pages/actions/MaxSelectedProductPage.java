package pages.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.locators.MaxSelectedProductPageLocators;
import utilities.DriverManager;

public class MaxSelectedProductPage {
	
	
	MaxSelectedProductPageLocators maxSelectedProductPageLocators;
	
	public MaxSelectedProductPage(WebDriver driver) {
		this.maxSelectedProductPageLocators=new MaxSelectedProductPageLocators();
		PageFactory.initElements(driver, this.maxSelectedProductPageLocators);
	}
	
	public String getProductName()
	{
		//DriverManager.wait.until(ExpectedConditions.visibilityOf(maxSelectedProductPageLocators.favClose));
		//maxSelectedProductPageLocators.favClose.click();
		System.out.println(maxSelectedProductPageLocators.productName.getText());
		return maxSelectedProductPageLocators.productName.getText();
	}
	
	public void clickSize(String size)
	{
		if(size.equalsIgnoreCase("m"))
		maxSelectedProductPageLocators.selectSizeM.click();
		else if(size.equalsIgnoreCase("l"))
			maxSelectedProductPageLocators.selectSizeL.click();
	}
	
	public void addToBasket()
	{
		maxSelectedProductPageLocators.addToBasket.click();
	}

}

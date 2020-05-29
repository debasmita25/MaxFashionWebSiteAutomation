package pages.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pages.locators.MaxPreBasketPageLocators;

public class MaxPreBasketPage {
	
	MaxPreBasketPageLocators maxPreBasketPageLocators;
	
	public MaxPreBasketPage(WebDriver driver) {
		
		this.maxPreBasketPageLocators=new MaxPreBasketPageLocators();
		PageFactory.initElements(driver, this.maxPreBasketPageLocators);
		
	}
	
	public String getSuccessMsg()
	{
		return maxPreBasketPageLocators.getSuccessNotification.getText();
	}
	
	public void clickCheckoutNow()
	{
		maxPreBasketPageLocators.checkoutNow.click();
	}
}

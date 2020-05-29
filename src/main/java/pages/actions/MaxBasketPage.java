package pages.actions;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.locators.MaxBasketPageLocators;
import utilities.DriverManager;

public class MaxBasketPage {
	
	MaxBasketPageLocators maxBasketPageLocators;
	
	public MaxBasketPage(WebDriver driver) {
		
		this.maxBasketPageLocators=new MaxBasketPageLocators();
		PageFactory.initElements(driver, this.maxBasketPageLocators);
		
	}
	
	public String getItemTotal()

	{
		DriverManager.wait.until(ExpectedConditions.visibilityOf(maxBasketPageLocators.basketTitle));
		return maxBasketPageLocators.itemsTotal.getText();
	}
	
	public List<WebElement> getItemPriceBreakupsKeys()
	{
		return maxBasketPageLocators.priceBreakupTitle;
	}
	
	public List<WebElement> getItemPriceBreakupsValues()
	{
		return maxBasketPageLocators.priceBreakupValue;
	}
	
	public String getTotalBreakupKey()
	{
		return maxBasketPageLocators.totalPriceBreakupskey.getText();
	}
	
	public String getTotalBreakupPrice()
	{
		return maxBasketPageLocators.totalPriceBreakupsValue.getText();
	}
	
	public String getItemDescription()
	{
		return maxBasketPageLocators.descrTitle.getText();
	}
	
	public String getProductName()
	{
		return maxBasketPageLocators.productName.getText();
	}
	
	public String getQtyTitle()
	{
		return maxBasketPageLocators.qtyTitle.getText();
	}
	
	
	public String getQty()
	{
		return maxBasketPageLocators.qty.getText();
	}
	
	public String getPriceTitle()
	{
		return maxBasketPageLocators.priceTitle.getText();
	}
	
	public String getPrice()
	{
		return maxBasketPageLocators.price.getText();
	}
	
	public String getPriceTotalTitle()
	{
		return maxBasketPageLocators.totalTitle.getText();
	}
	
	public String getTotalPrice()
	{
		return maxBasketPageLocators.totalPrice.getText();
	}
	
	public List<WebElement> getItemKeys()
	{
		return maxBasketPageLocators.productKeys;
	}
	
	public List<WebElement> getItemValues()
	{
		return maxBasketPageLocators.productValues;
	}
	
	public int getTotalListItem()
	{
		return maxBasketPageLocators.totalItemList.size();
	}
	
	public List<WebElement> getQtys()
	{
	  return maxBasketPageLocators.qtyValues;	
	}
	
	public List<WebElement> getSizeValues()
	{
		return maxBasketPageLocators.sizeValues;
	}
	
	public List<WebElement> getSizeKeys()
	{
		return maxBasketPageLocators.sizeTitles;
	}
	
	public List<WebElement> getColorValues()
	{
		return maxBasketPageLocators.colourValues;
	}
	
	public List<WebElement> getColorKeys()
	{
		return maxBasketPageLocators.colourTitles;
	}
	
	public List<WebElement> getProductNames()
	{
		return maxBasketPageLocators.productNames;
	}
	
	public List<WebElement> getTotalPrices()
	{
		return maxBasketPageLocators.totalPriceValues;
	}

}

package pages.actions;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;
import pages.locators.MaxHomePageLocators;

public class MaxHomePage  extends BasePage{
	
	MaxHomePageLocators maxHomePageLocators ;
	
	public MaxHomePage(WebDriver driver)
	{
		this.maxHomePageLocators=new MaxHomePageLocators();
		PageFactory.initElements(driver, maxHomePageLocators);	
	}
	
    public List<WebElement> getCategoryList()
    {
    	return maxHomePageLocators.categoryList;
    	
    }
	
	

}

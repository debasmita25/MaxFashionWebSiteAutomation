package pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MaxPreBasketPageLocators {
	
	@FindBy(css=".color--success")
	public WebElement getSuccessNotification;
	
	@FindBy(xpath="//a[text()=' Checkout Now']")
    public WebElement checkoutNow;
}

package pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MaxSelectedProductPageLocators {
	
	@FindBy(css="#product-details-description h1")
	public WebElement productName;
	
	@FindBy(css="i>.svg-heart")
	public WebElement favClose;
	
	@FindBy(id="filter-form-label-size-3")
	public WebElement selectSizeL;
	
	@FindBy(id="filter-form-label-size-2")
	public WebElement selectSizeM;
	
	@FindBy(css=".show.old-button-set #product-actions-btn-add")
	public WebElement addToBasket;

}

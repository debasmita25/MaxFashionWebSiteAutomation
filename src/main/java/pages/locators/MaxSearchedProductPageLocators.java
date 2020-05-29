package pages.locators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MaxSearchedProductPageLocators {
	
	@FindBy(xpath="//div[text()='Size']")
	public WebElement size;
	
	@FindBy(css=".MuiCollapse-container.MuiCollapse-entered span.MuiIconButton-label>input[value]")
	public List<WebElement> sizeList;
	
	@FindBy(xpath="//a[contains(@href,'/in/en/Women') and text()]")
	public List<WebElement> productNameWomenList;
	
	@FindBy(css="span.MuiChip-label")
	public WebElement Ldisplay;
	
	@FindBy(css="div.MuiBox-root.jss1695")
	public WebElement firstItem;
	
	@FindBy(xpath="//div[@id='main-part']/div/div//div[contains(text(),'Products')]")
	public WebElement productsNo;

}

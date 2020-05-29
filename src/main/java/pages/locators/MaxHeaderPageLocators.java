package pages.locators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MaxHeaderPageLocators {
	
	
	@FindBy(css="img[src$='logo-max.svg']")
	public WebElement maxHeaderLogo;
	
	@FindBy(xpath="//div[text()='Shop in Lingerie']")
	public WebElement lingerieHeader;
	
	@FindBy(xpath="//div[text()='Shop inLingerie']")
	public WebElement lingerieHeaderTwo;
	
	@FindBy(xpath="//div[text()='Shop in Essentials']")
	public WebElement essentialsHeader;
	
	@FindBy(xpath="//*[@class='MuiButton-label' and text()='Women']")
	public WebElement womenCat;
	
	@FindBy(xpath="//*[@class='MuiButton-label' and text()='Men']")
	public WebElement menCat;
	
	@FindBy(xpath="//*[@class='MuiButton-label' and text()='Girls']")
	public WebElement girlsCat;

	@FindBy(css=".MuiButtonBase-root.MuiButton-root.MuiButton-text.dept-btn.jss82")
	public List<WebElement> listInMain;
	
	@FindBy(xpath="//div[@id='category-menu-0']//span[@class='MuiButton-label' and text()]")
	public List<WebElement> listInWomen;
	
	@FindBy(xpath="//div[starts-with(@class,'MuiBox-root jss')]/a[@class='jss105' and text()]")
	public List<WebElement> listOfProducts;
	
	@FindBy(xpath="//div[starts-with(@class,'MuiBox-root jss300')]/a[@class='jss105' and text()]")
	public List<WebElement> listInEssentials;
}

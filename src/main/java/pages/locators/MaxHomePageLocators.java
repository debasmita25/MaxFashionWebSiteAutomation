package pages.locators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MaxHomePageLocators{

	@FindBy(how=How.CSS,using =".MuiGrid-root.jss634.MuiGrid-item.MuiGrid-grid-xs-true button")
	public List<WebElement> categoryList;
	
	
	
}

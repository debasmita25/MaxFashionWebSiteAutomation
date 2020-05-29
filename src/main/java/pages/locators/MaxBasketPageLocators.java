package pages.locators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MaxBasketPageLocators {
	
	@FindBy(css="#section-heading-holder-01 h2#section-heading-title-01")
	public WebElement basketTitle;
	
	@FindBy(css="#section-heading-holder-01 .item-count-heading.ABShow")
	public WebElement itemsTotal;
	
	@FindBy(css="div.miniCart__rightContent .shipping-breakdown-miniCart-ab-testing li>span")
	public List<WebElement> itemsPriceBeakups;
	
	@FindBy(css="ul#shopping-basket li")
	public List<WebElement>  itemDescriptions;
	
	@FindBy(css="#shopping-basket-col-descr-title")
	public WebElement descrTitle;
	
	@FindBy(css="#shopping-basket-col-price")
	public WebElement priceTitle;
	
	@FindBy(css="#shopping-basket-product-price-00")
	public WebElement price;
	
	
	@FindBy(id="shopping-basket-col-qty-title")
	public WebElement qtyTitle;
	
	
	@FindBy(css=".jcf-select-text>span")
	public WebElement qty;
	
	@FindBy(css="#shopping-basket-col-total-title")
	public WebElement totalTitle;
	
	@FindBy(css="#products-price-current00")
	public WebElement totalPrice;
	
	@FindBy(css="#RemoveProduct_00")
	public WebElement removeProduct;
	
	@FindBy(xpath="//a[@data-heart='true' and @data-toggle='modal']")
    public WebElement saveForLater;
	
	@FindBy(css=".miniCart__rightContent .key")
	public List<WebElement> priceBreakupTitle;
	
	@FindBy(css=".miniCart__rightContent .value")
	public List<WebElement> priceBreakupValue;
	
	@FindBy(css=".total-text")
	public WebElement totalPriceBreakupskey;
	
	@FindBy(css="span.total-price-text.miniCart__price")
	public WebElement totalPriceBreakupsValue;
	
	@FindBy(css="#shopping-basket-product-name-00")
	public WebElement productName;
	
	@FindBy(css="#shopping-basket-product-options-list-00 dt")
	public List<WebElement> productKeys;
	
	@FindBy(css="#shopping-basket-product-options-list-00 dd")
	public List<WebElement> productValues;
	
	@FindBy(css="li.shopping-basket-product.cartItem.clearfix")
	public List<WebElement> totalItemList;
	
	@FindBy(xpath="//li[@class=' shopping-basket-product cartItem clearfix']//a[@class='product-link' and text()]")
	public List<WebElement> productNames;
	
	@FindBy(xpath="//dd[contains(@id,'shopping-basket-product-color')]")
    public List<WebElement> colourValues;
	
	@FindBy(xpath="//dt[contains(@id,'shopping-basket-product-color-title')]")
	public List<WebElement> colourTitles;
	
	@FindBy(xpath="//dt[contains(@id,'shopping-basket-product-size-title')]")
	public List<WebElement> sizeTitles;
	
	@FindBy(xpath="//dd[contains(@id,'shopping-basket-product-size')]")
    public List<WebElement> sizeValues;
	
	@FindBy(css=".jcf-select-text>span")
	public List<WebElement>  qtyValues;
	
	@FindBy(css="[id^='products-price-current']")
	public List<WebElement>  totalPriceValues;
}

package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class flipkart {
	
	WebDriver driver;
	
	@FindBy(name = "q")
	WebElement txt_Searchbox;
	
	@FindBy(xpath = "//div[normalize-space()='Apple iPhone 16 Pro Max (Desert Titanium, 256 GB)']")
	WebElement lnk_FirstItem;
	
	@FindBy  (id="twotabsearchtextbox")
	WebElement txt_AmazonSearchBox;
	
	@FindBy(xpath = "//span[normalize-space()='Apple iPhone 16 Pro Max (256 GB) - Desert Titanium']")
	WebElement lnk_FirstItemAmazon;
	
	//Constructor
	public flipkart(WebDriver driver)
	{
		this.driver = driver;
		 PageFactory.initElements(driver, this);
	}
	
	
	public void searchProduct(String productName)
	{
		txt_Searchbox.sendKeys(productName);
		txt_Searchbox.sendKeys(Keys.ENTER);
	}
	
	public void clickFirstItem() {
		lnk_FirstItem.click();
	}
	
	public void searchAmazonProduct(String prodName)
	{
		txt_AmazonSearchBox.sendKeys(prodName);
		txt_AmazonSearchBox.sendKeys(Keys.ENTER);
	}
	
	public void clickFirstItemAmazon() throws Exception {
		lnk_FirstItemAmazon.click();
		Thread.sleep(3000);
	}
	
	

}

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
	
	@FindBy(xpath = "//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[2]/div/div/div/a/div[2]/div[1]/div[1]")
	WebElement lnk_FirstItem;

	
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
	
	
	
	

}

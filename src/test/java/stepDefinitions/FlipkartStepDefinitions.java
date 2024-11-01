package stepDefinitions;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.flipkart;



public class FlipkartStepDefinitions   {
	WebDriver driver;
	flipkart fp ;
	
	@Given("I am on the Flipkart homepage")
	public void navigateToFlipkartHomePage() {
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.flipkart.com/");		
		fp = new flipkart(driver);
	}

	
	@When("I search for product")
	public void searchForProduct() {
	   fp.searchProduct("iphone 16 pro max 512 gb");	
	}

	
	@And("I select the first product")
	public void clickOnFirstProduct() {
		fp.clickFirstItem();		
	}

	
	@Then("I should see the model name and price")
	public void getModelNameAndPrice() throws Exception {
		String parentWH = driver.getWindowHandle();
		System.out.println("parent window handle is:"+ parentWH);
		Set<String> allWH = driver.getWindowHandles();
		
		for(String handle:allWH)
		{
			if(!handle.equals(parentWH))
			{
				driver.switchTo().window(handle);
				System.out.println("Child window handle is:"+ handle);
			}
		}
		
		String modelName = "";
		String flipkartPrice = "";
		try {
			Thread.sleep(3000);
			 modelName = driver.findElement(By.xpath("//h1[@class='_6EBuvT']//span")).getText();
			 Thread.sleep(3000);
			 flipkartPrice = driver.findElement(By.xpath("//div[@class='Nx9bqj CxhGGd']")).getText();
		}
		
		catch (Exception e) {	
			Thread.sleep(3000);
			modelName = driver.findElement(By.xpath("//h1[@class='_6EBuvT']//span")).getText();	
			Thread.sleep(3000);
			flipkartPrice = driver.findElement(By.xpath("//div[@class='hl05eU TuwWn4']//child::div")).getText();
	
			 
		}
		
		
		
		
		
		
		System.out.println(modelName);
		System.out.println(flipkartPrice);
		
		
	}

}

package stepDefinitions;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.gherkin.model.ScenarioOutline;

import extentReports.ReportsUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.flipkart;



public class FlipkartStepDefinitions   {
	WebDriver driver;
	flipkart fp ;
	
	String modelNameAmazon = "";
	String PriceAmazon = "";
	String modelName = "";
	String flipkartPrice = "";
	
	 ExtentTest test;

	
	@Given("I am on the Flipkart homepage")
	public void navigateToFlipkartHomePage() {
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.flipkart.com/");		
		fp = new flipkart(driver);
		
        ReportsUtils.pass("Navigated to Flipkart homepage");
        ReportsUtils.attachScreenshot(driver);
	}

	
	@When("I search for product")
	public void searchForProduct() {
	   fp.searchProduct("iphone 16 pro max 256 gb");
	   ReportsUtils.pass("Searched for iPhone 16 Pro Max");
       ReportsUtils.attachScreenshot(driver);
	  
	}

	
	@And("I select the first product")
	public void clickOnFirstProduct() {
		fp.clickFirstItem();	
		ReportsUtils.pass("Selected the first product");
        ReportsUtils.attachScreenshot(driver);
	}

	
	@Then("I should see the model name and price from flipkart")
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
				break;
			}
		}
		
		
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
			
		System.out.println("Flipkart Model Name:" + modelName);
		System.out.println("Flipkart Price :" + flipkartPrice);
		
			
	}
	
	//Amazon code
	@And("Navigate to amazon homepage")
	public void navigateToAmazonHomePage() {
		driver.navigate().to("https://www.amazon.in/");
		ReportsUtils.pass("Navigated to Amazon homepage");
	}
	
	@And("Search for the same product")
	public void searchForSameProduct() {
		fp.searchAmazonProduct("iphone 16 pro max 256 gb");
		ReportsUtils.pass("Searching for the same product");
		
	}
	
	@And("Select the product")
	public void selectProduct() throws Exception {
		fp.clickFirstItemAmazon();
		ReportsUtils.pass("Clicked on the first product");
	}
	
	@Then("I should see the model name and price from amazon")
	public void getModelNameAndPriceAmazon() throws Exception {
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
		
		
		try {
			Thread.sleep(3000);
			 modelNameAmazon = driver.findElement(By.xpath("//span[@id='productTitle']")).getText();
			 Thread.sleep(3000);
			 PriceAmazon = driver.findElement(By.xpath("//span[normalize-space()='1,44,900']")).getText();
			 System.out.println(modelNameAmazon);
			System.out.println(PriceAmazon);
			
		}
		
		catch (Exception e) {	
            throw new RuntimeException("Failed to get model name and price from Amazon: " + e.getMessage());

			 
		}
			
		System.out.println("Amazon Model :" +modelNameAmazon);
		System.out.println("Amazon Price :"+PriceAmazon);
		
		driver.close();
			
	}
	
	@And("Compare both model and prices and add to cart based on low price")
	public void compareModelAndPrice() {
		ReportsUtils.pass("Comparing price for each product");
		 if (modelName.equals(modelNameAmazon)) {
	            System.out.println("Both models are the same: " + modelName);
	        } else {
	            System.out.println("Models are different. Flipkart: " + modelName + ", Amazon: " + modelNameAmazon);
	        }

	        // Compare prices (assuming prices are in a comparable format)
	        double flipkartPriceValue = Double.parseDouble(flipkartPrice.replace(",", "").replace("₹", "").trim());
	        double amazonPriceValue = Double.parseDouble(PriceAmazon.replace(",", "").replace("₹", "").trim());

	        if (flipkartPriceValue < amazonPriceValue) {
	            System.out.println("Flipkart has the lower price: " + flipkartPrice);
	            ReportsUtils.pass("Flipkart product has less price");
	            
	        } else if (flipkartPriceValue > amazonPriceValue) {
	            System.out.println("Amazon has the lower price: " + PriceAmazon);
	            ReportsUtils.pass("Amazon product has less price");
	           
	        } else {
	            System.out.println("Both prices are the same: " + flipkartPrice);
	            ReportsUtils.pass("Both prices are same");
	            
	        }
	 
		
	}
	
	
	@After
    public void tearDown() {
        driver.quit();
    }

	
	

}

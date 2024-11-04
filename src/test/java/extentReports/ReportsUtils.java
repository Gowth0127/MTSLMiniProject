package extentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportsUtils {
    private static ExtentReports extent;
    private static ExtentTest test;
    private static String reportPath;
    
    static {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd  HH-mm").format(new Date());
        reportPath = "automationreports/report_" + timestamp + ".html"; // Unique report filename
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    public static void startTest(String testName) {
        test = extent.createTest(testName);

    }

    public static void pass(String message) {
        test.pass(message);
        
    }

    public static void fail(String message, WebDriver driver) {
        test.fail(message);
        attachScreenshot(driver);
    }

    public static void endReport() {
        extent.flush();
    }

    public static void attachScreenshot(WebDriver driver) {
        if (driver == null) {
            System.out.println("WebDriver instance is null. Cannot take screenshot.");
            return; // Exit if driver is not initialized
        }
        try {
            // Take the screenshot
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // Define the screenshot path
            String screenshotPath = "automationreports/screenshots/" + System.currentTimeMillis() + ".png";
            File destFile = new File(screenshotPath);
            
            // Copy the screenshot to the specified location
            Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            // Add screenshot to the report
            test.addScreenCaptureFromPath(screenshotPath);
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An error occurred while taking the screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }

}

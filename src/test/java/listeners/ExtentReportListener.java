package listeners;

import extentReports.ReportsUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;

public class ExtentReportListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        ReportsUtils.startTest(context.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ReportsUtils.pass("Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
        ReportsUtils.fail("Test failed: " + result.getMethod().getMethodName(), driver);
    }

    @Override
    public void onFinish(ITestContext context) {
        ReportsUtils.endReport();
    }
}

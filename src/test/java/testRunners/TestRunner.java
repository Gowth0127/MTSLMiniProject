package testRunners;

import org.testng.annotations.Listeners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import listeners.ExtentReportListener;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "stepDefinitions",
    plugin = {
        "pretty",
        "html:target/cucumber-reports.html",
        "json:target/cucumber.json"
    },
    tags = "@smoke"
)
@Listeners({ExtentReportListener.class}) // Register the listener
public class TestRunner extends AbstractTestNGCucumberTests {
}

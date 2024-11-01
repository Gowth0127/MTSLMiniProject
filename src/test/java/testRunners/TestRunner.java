package testRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue="stepDefinitions",

plugin = {
        "pretty",
        "html:target/cucumber-reports.html",  // HTML report
        "json:target/cucumber.json"             // JSON report
},

tags = "@smoke"
		
		)
public class TestRunner extends AbstractTestNGCucumberTests  {
	
	
}
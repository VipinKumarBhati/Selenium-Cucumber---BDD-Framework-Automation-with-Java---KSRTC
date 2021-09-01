package runners;


import org.junit.runner.RunWith;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions (
		features = {"src/test/java/features/"},
		glue = {"stepDefinitions"},
		monochrome = true,
		tags = {"@validLoginTest,@invalidLoginTest,@searchBus"},
		plugin = {"pretty","html:target/cucumber","json:target/cucumber.json","com.cucumber.listener.ExtentCucumberFormatter:target/report.html","com.cucumber.listener.ExtentCucumberFormatter:target/report.html"}
		)
public class MainRunner extends AbstractTestNGCucumberTests{
}

package stepDefinitions;

import java.util.List;
import java.util.Map;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.BasePage;
import utils.DriverFactory;
import utils.ExcelReader;

public class Login_Invalid_KSRTC_Steps extends DriverFactory{
	@When("^User enters invalid username and password from sheetname \"([^\"]*)\" and row number (\\d+)$")
	public void user_enters_invalid_username_and_password_from_sheetname_and_row_number(String sheetName, int rowNumber) throws Throwable {
		ExcelReader reader = new ExcelReader();
		List<Map<String,String>> testData = 
				reader.getData(System.getProperty("user.dir") + BasePage.pathSeparator() + "src"+ BasePage.pathSeparator() + "main" + BasePage.pathSeparator() + "java" + BasePage.pathSeparator() + "testdata" + BasePage.pathSeparator() + "controller.xlsx", sheetName);
		
		String username = testData.get(rowNumber).get("username");
		String password = testData.get(rowNumber).get("password");
		loginPage.enterUserName(username);
		loginPage.enterPassword(password);
	}

	@Then("^User should not be taken to the successful login page$")
	public void user_should_not_be_taken_to_the_successful_login_page() throws Throwable {
		loginPage.confirmLoginWasUnsuccessful();
	}


}

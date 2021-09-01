package stepDefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;

import cucumber.api.java.en.And;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utils.DriverFactory;
import utils.ExcelReader;

public class Login_Valid_KSRTC_Steps extends DriverFactory{
	@Given("^User navigates to KSRTC website$")
	public void user_navigates_to_KSRTC_website() throws Throwable {
		loginPage.getKRSTC_Page();
	}

	@Given("^user clicks on Sign in Link$")
	public void user_clicks_on_Sign_in_Link() throws Throwable {
		loginPage.clickOnSignInLink();
	}

	@When("^User enters valid username and password from sheetname \"([^\"]*)\" and row number (\\d+)$")
	public void user_enters_valid_username_and_password_from_sheetname_and_row_number(String sheetName, int rowNumber) throws Throwable {
		ExcelReader reader = new ExcelReader();
		List<Map<String,String>> testData = 
				reader.getData(System.getProperty("user.dir") + "\\src\\main\\java\\testdata\\controller.xlsx", sheetName);
		
		String username = testData.get(rowNumber).get("username");
		String password = testData.get(rowNumber).get("password");
		loginPage.enterUserName(username);
		loginPage.enterPassword(password);
	}

	@When("^User clicks on the login button$")
	public void user_clicks_on_the_login_button() throws Throwable {
		loginPage.clickOnLoginButton();
	}

	@Then("^User should be taken to the successful login page$")
	public void user_should_be_taken_to_the_successful_login_page() throws Throwable {
		loginPage.confirmLoginWasSuccessful();
	}


}

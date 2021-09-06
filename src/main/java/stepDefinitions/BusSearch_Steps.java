package stepDefinitions;

import java.util.List;
import java.util.Map;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.BasePage;
import utils.DriverFactory;
import utils.ExcelReader;

public class BusSearch_Steps extends DriverFactory{
	@When("^User enters the source and destination from sheet \"([^\"]*)\" and row number (\\d+)$")
	public void user_enters_the_source_and_destination_from_sheet_and_row_number(String sheetName, int rowNumber) throws Throwable {
		ExcelReader reader = new ExcelReader();
		List<Map<String,String>> testData = 
				reader.getData(System.getProperty("user.dir") + BasePage.pathSeparator() + "src"+ BasePage.pathSeparator() + "main" + BasePage.pathSeparator() + "java"
								+ BasePage.pathSeparator() + "testdata" + BasePage.pathSeparator() + "controller.xlsx", sheetName);
		
		String source = testData.get(rowNumber).get("source");
		String destination = testData.get(rowNumber).get("destination");
		busSearchPage.enterSource(source);
		busSearchPage.enterDestination(destination);
	}

	@When("^User enters a journey start date$")
	public void user_enters_a_journey_start_date() throws Throwable {
		busSearchPage.enterJourneyDate();
	}

	@When("^User enters a return date$")
	public void user_enters_a_return_date() throws Throwable {
		busSearchPage.enterReturnDate();
	}

	@When("^User clicks on the search bus button$")
	public void user_clicks_on_the_search_bus_button() throws Throwable {
		busSearchPage.clickOnSearchButton();
	}

	@Then("^User successfully navigates to the next page with list of buses$")
	public void user_successfully_navigates_to_the_next_page_with_list_of_buses() throws Throwable {
		busSearchPage.confirmSearchWasSuccessful();
	}
}

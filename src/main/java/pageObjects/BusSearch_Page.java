package pageObjects;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class BusSearch_Page extends BasePage {
	public @FindBy(xpath="//input[@name='fromPlaceName']") WebElement from;
	public @FindBy(xpath="//ul[@id='ui-id-1']//li[1]") WebElement fromList;
	public @FindBy(xpath="//input[@name='toPlaceName']") WebElement to;
	public @FindBy(xpath="//a[@class='ui-corner-all']") List<WebElement> toList;
	public @FindBy(xpath="//input[@name='txtJourneyDate']") WebElement journeyDate;
	public @FindBy(className="ui-datepicker-calendar") WebElement datePickerCalendar;
	public @FindBy(xpath="//input[@name='txtReturnJourneyDate']") WebElement returnJourneyDate;
	public @FindBy(xpath="//div[@class='ui-datepicker-title']//span[@class='ui-datepicker-month']") WebElement month;
	public @FindBy(xpath="//a[@title='Next']") WebElement return_Next;
	public @FindBy(xpath="//button[contains(text(),'Search for Bus')]") WebElement searchButton;
	public @FindBy(xpath="//div[contains(text(),'Note: Seat availability is displayed based on minimum distance criteria in advance booking.')]") WebElement searchValidation;

	public BusSearch_Page() throws IOException{
		super();
		PageFactory.initElements(driver, this);
	}
	public BusSearch_Page enterSource(String src) throws IOException, Exception{
		sendKeysToWebElement(from, src);
		waitAndClickElement(fromList);
		return new BusSearch_Page();
	}
	public BusSearch_Page enterDestination(String dest) throws Exception{
		waitAndClickElement(to);
		for(int i = 0; i< toList.size()-1; i++){
			if(toList.get(i).getText().equalsIgnoreCase(dest)){
				actionMoveAndClick(toList.get(i));
				break;
			}
		}
		return new BusSearch_Page();
	}

	public BusSearch_Page enterJourneyDate() throws Exception{
		waitAndClickElement(journeyDate);
		WebElement dateWidgetFrom = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("ui-datepicker-calendar"))).get(0);
		//This are the columns of the from date picker table
		List<WebElement> columns = dateWidgetFrom.findElements(By.tagName("td"));
		clickGivenDay(columns, getCurrentDay());
		return new BusSearch_Page();
	}
	
	public BusSearch_Page enterReturnDate() throws Exception{
		waitAndClickElement(returnJourneyDate);
		String date = getCurrentDayPlus();
		String splitters[] = date.split("-");
		String retMonth = splitters[1];
		String day = splitters[0]; 
		if(!month.getText().equalsIgnoreCase(retMonth)){
			waitAndClickElement(return_Next);
		}
		WebElement dateWidgetFrom = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("ui-datepicker-calendar"))).get(0);
		List<WebElement> columns = dateWidgetFrom.findElements(By.tagName("td"));
		clickGivenDay(columns, day);
		return new BusSearch_Page();
	}
	public BusSearch_Page clickOnSearchButton() throws Exception{
		waitAndClickElement(searchButton);
		return new BusSearch_Page();
	}
	public BusSearch_Page confirmSearchWasSuccessful() throws Exception{
		WaitUntilWebElementIsVisible(searchValidation);
		Assert.assertEquals(searchValidation.getText(),"Note: Seat availability is displayed based on minimum distance criteria in advance booking.");
		return new BusSearch_Page();		
	}
}

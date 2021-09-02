package pageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Login_Page extends BasePage{
	public @FindBy(xpath="//a[contains(text(),'Sign In')]") WebElement signIn;
	public @FindBy(xpath="//input[@name='userName']") WebElement userName;
	public @FindBy(xpath="//input[@name='password']") WebElement password;
	public @FindBy(xpath="//input[@id='submitBtn']") WebElement logIn;
	public @FindBy(xpath="//div[@class='dropdown' and contains(text(),'Welcome')]") WebElement validLogin_Welcome;
	public @FindBy(xpath="//a[contains(text(),'Logout')]") WebElement validLogin_Verify;
	public @FindBy(xpath="//body/main[1]/iframe[1]") WebElement iFrame;
	public @FindBy(xpath="//div[@id='errorMsg']//strong[contains(text(),'Login incorrect. Please try again')]") WebElement inValidLogin_Verify;
	
	public Login_Page() throws IOException{
		super();
		PageFactory.initElements(driver, this);
	}
	
	
	public Login_Page getKRSTC_Page() throws IOException{
		Properties p = new Properties();
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + pathSeparator() + "src"+ pathSeparator() +"main"+ pathSeparator() +"java"+ pathSeparator() +"config"+ pathSeparator() +"config.properties");
		p.load(fi);
		String url = p.getProperty("url");
		//System.out.println(browserName);
		getDriver().get(url);;
		return new Login_Page();
	}
	
	public Login_Page clickOnSignInLink() throws IOException, Exception{
		waitAndClickElement(signIn);
		return new Login_Page();
	}
	
	public Login_Page enterUserName(String username) throws Exception{
		//waitAndClickElement(userName);
		sendKeysToWebElement(userName, username);
		return new Login_Page();
	}
	
	public Login_Page enterPassword(String passWord) throws Exception{
		//waitAndClickElement(password);
		sendKeysToWebElement(password, passWord);
		return new Login_Page();
	}
	
	public Login_Page clickOnLoginButton() throws Exception{
		waitAndClickElement(logIn);
		return new Login_Page();
	}
	
	public Login_Page confirmLoginWasSuccessful() throws Exception{
		WaitUntilWebElementIsVisible(validLogin_Welcome);
		waitAndClickElement(validLogin_Welcome);
		Assert.assertEquals(validLogin_Verify.getText().toLowerCase().replaceAll("[ ()0-9]",""),"logout");
		return new Login_Page();		
	}
	
	public Login_Page confirmLoginWasUnsuccessful() throws Exception{
		WaitUntilWebElementIsVisible(inValidLogin_Verify);
		Assert.assertEquals(inValidLogin_Verify.getText().toLowerCase().replaceAll("[ ()0-9]",""),"loginincorrect.pleasetryagain");
		return new Login_Page();		
	}
}

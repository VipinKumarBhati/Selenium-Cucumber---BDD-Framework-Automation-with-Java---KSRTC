package utils;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import pageObjects.BasePage;
import pageObjects.BusSearch_Page;
import pageObjects.Login_Page;
import utils.Constants;

public class DriverFactory {
	public static WebDriver driver;
	public static Login_Page loginPage;
	public static BusSearch_Page busSearchPage;
	public static BasePage basePage;
	
	@SuppressWarnings("deprecation")
	public static WebDriver getDriver(){
		try{
			//Read Config
			//commented because to use jenkins or batch file it will be difficult or it will fail to use readconfig file
			//So we are accessing the properties file directly instead of accessing the ReadConfigFile
			
			//ReadConfigFile file =  new ReadConfigFile();
			//String browserName = file.getBrowser();
			
			Properties p = new Properties();
			FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\config\\config.properties");
			p.load(fi);
			String browserName = p.getProperty("browser");
			switch(browserName){

			case "firefox":
				if (null == driver){
					System.setProperty("webdriver.gecko.driver", Constants.GECKO_DRIVER_DIRECTORY);
					DesiredCapabilities capabilities = DesiredCapabilities.firefox();
					capabilities.setCapability("marionette", true);
					driver = new FirefoxDriver();
				}
				break;
			case "chrome":
				if (null == driver){
					System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_DIRECTORY);
					driver = new ChromeDriver();
					driver.manage().window().maximize();
				}
				break;
			case "ie":
				if (null == driver){
					DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
					System.setProperty("webdriver.ie.driver", Constants.IE_DRIVER_DIRECTORY);
					capabilities.setCapability("ignoreZoomSetting", true);
					driver = new InternetExplorerDriver(capabilities);
					driver.manage().window().maximize();
				}
				break;
			}
		}
		catch(Exception e){
			System.out.println("Unable to load browser : " + e.getMessage());
		}
		finally{
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			loginPage = PageFactory.initElements(driver, Login_Page.class);
			busSearchPage = PageFactory.initElements(driver, BusSearch_Page.class);
			basePage = PageFactory.initElements(driver, BasePage.class);
		}
		return driver;
	}
}

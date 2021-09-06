package utils;

import pageObjects.BasePage;

public class Constants {
	/** Config Properties File **/
	
	public final static String CONFIG_PROPERTIES_DIRECTORY = "properties" + BasePage.pathSeparator() + "config.properties";
	
	public final static String GECKO_DRIVER_DIRECTORY = System.getProperty("user.dir") + BasePage.pathSeparator() + "src" + BasePage.pathSeparator() + "test" + BasePage.pathSeparator() + "java" 
														+ BasePage.pathSeparator() + "resources" + BasePage.pathSeparator() + "geckodriver.exe";
	
	public final static String CHROME_DRIVER_DIRECTORY = System.getProperty("user.dir") + BasePage.pathSeparator() + "src" + BasePage.pathSeparator() + "test" + BasePage.pathSeparator() + "java" 
														+ BasePage.pathSeparator() + "resources" + BasePage.pathSeparator() + "chromedriver.exe";
	
	public final static String CHROME_DRIVER_DIRECTORY_MAC = System.getProperty("user.dir") + BasePage.pathSeparator() + "src" + BasePage.pathSeparator() + "test" + BasePage.pathSeparator() + "java" 
														+ BasePage.pathSeparator() + "resources" + BasePage.pathSeparator() + "chromedriver_mac";
	
	public final static String IE_DRIVER_DIRECTORY = System.getProperty("user.dir") + BasePage.pathSeparator() + "src" + BasePage.pathSeparator() + "test" + BasePage.pathSeparator() + "java" 
														+ BasePage.pathSeparator() + "resources" + BasePage.pathSeparator() + "IEDriverServer.exe";
	
	
}

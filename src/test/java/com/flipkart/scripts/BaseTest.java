package com.flipkart.scripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
	public static final Logger log = Logger.getLogger(BaseTest.class.getName());
	
	public WebDriver driver;	
	public Properties prop =new Properties();
	
	public void loadData() throws IOException {
		File configPath = new File(System.getProperty("user.dir") + "/src/test/java/com/flipkart/config/config.properties");
		FileInputStream file = new FileInputStream(configPath);
		prop.load(file);
		
	}
	
	public void init() throws IOException {
			loadData();
		
		// Logger configuration
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		// Calling the browser
		selectBrowser(prop.getProperty("browser"));
		// Getting the URL
		getUrl(prop.getProperty("url")); 
	}
	
	public void selectBrowser(String browser) {
		if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
			log.info("Creating the object of--->>>" +browser);
			driver = new FirefoxDriver();
		}
		
		else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
			log.info("Creating the object of--->>>" +browser);
			driver = new ChromeDriver();
		}
	}
	
	public void getUrl(String url) {
		
		driver.get(url);
		driver.manage().window().maximize();
		
		log.info("Navigating to--->> " +url);
		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	public WebElement waitForElement(WebDriver driver, WebElement element, long timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		return element;
	}
	
	

}


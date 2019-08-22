package com.flipkart.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.scripts.BaseTest;

public class LoginPage extends BaseTest {
	public static final Logger log = Logger.getLogger(LoginPage.class.getName());

	public WebDriver driver;

	@FindBy(xpath = "//input[@class='_2zrpKA _1dBPDZ']")
	private WebElement emailAddress;

	@FindBy(xpath = "//input[@class='_2zrpKA _3v41xv _1dBPDZ']")
	private WebElement password;

	@FindBy(xpath = "(//span[contains(text(),'Login')])[2]")
	private WebElement login;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void loginToApplication(String email, String passwordText) throws Exception {
		emailAddress.sendKeys(email);
		log.info(" Entering the phone number or username: " + email);
		password.sendKeys(passwordText);
		log.info(" Entering the password: " + passwordText);
		login.click();
		Thread.sleep(4000);
		log.info(" Clicking on the Login button ");
	}

}

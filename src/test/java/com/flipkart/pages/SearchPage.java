package com.flipkart.pages;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.flipkart.scripts.BaseTest;

public class SearchPage extends BaseTest {
	public static final Logger log = Logger.getLogger(SearchPage.class.getName());
	public WebDriver driver;
	JavascriptExecutor js;

	@FindBy(xpath = "//input[@class='LM6RPg']")
	private WebElement searchTextBox;

	@FindBy(xpath = "//button[@class='vh79eN']")
	private WebElement searchButton;

	@FindBy(xpath = "//button[text()='ADD TO CART']")
	private WebElement addToCart;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// This function enter the Product name to be searched
	public void searchProduct(String productName) throws Exception {
		Thread.sleep(8000);
		searchTextBox.sendKeys(productName, Keys.ENTER);
		log.info(" Entering the Product Name: " + productName);
		Thread.sleep(20000);
		/*
		 * searchButton.click(); clickUsingJavaScript();
		 */
		log.info(" Clicking on the Search button ");
	}

	// This function clicks on the search button
	public void clickUsingJavaScript() {
		js = (JavascriptExecutor) driver;
		WebElement button = driver.findElement(By.xpath("//button[@class='vh79eN']"));
		js.executeScript("arguments[0].click();", button);
	}

	// This function fetches the product and price , compares product price with the
	// cart price
	public void fetchProductName() throws Exception {
		String xpathRows = "//div[@class='_1HmYoV _35HD7C'][2]/div";
		List<WebElement> totalRows = driver.findElements(By.xpath(xpathRows));
		int actualRows = (totalRows.size() - 3);

		for (int i = 2; i <= actualRows; i++) {
			String xpathProductName = "//div[@class='_1HmYoV _35HD7C'][2]/div[" + i + "]//a//div[@class='_3wU53n']";
			WebElement prodName = driver.findElement(By.xpath(xpathProductName));

			if (prodName.getText().equalsIgnoreCase("Sony CyberShot DSC-W810/BC IN5")) {
				// Click on Product
				prodName.click();
				Thread.sleep(10000);
				log.info("Clicking on the Product Name: " + prodName.getText().toString());

				// Switch to new Tab
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));				

				String xpathProductPrice = "(//span[text()='Special price']/parent::div/following-sibling::div//div)[3]";
				WebElement prodPrice = driver.findElement(By.xpath(xpathProductPrice));

				// Wait for the Product Price to load
				waitForElement(driver, 10, prodPrice);

				String productPrice = prodPrice.getText().toString().trim();
				log.info("Product Price is : " + productPrice);

				// Click on Add to Cart
				scrollToElement();
				Thread.sleep(20000);
				log.info(" Clicking on the Add to Cart button ");
				/*
				 * String cartXpath = "//button[text()='ADD TO CART']"; WebElement addToCart =
				 * driver.findElement(By.xpath(cartXpath)); addToCart.click();
				 */

				// Get the Cart Price for verification
				String cartPriceXpath = "(//span[text()='Price details']/parent::div/div/div)[1]/span";
				WebElement ele = driver.findElement(By.xpath(cartPriceXpath));
				waitForClickElement(driver, 20, ele);
				String cartPrice = ele.getText().toString();
				System.out.println("Cart Price of the Product:  " + cartPrice);
				Assert.assertEquals(productPrice, cartPrice);
				log.info(" ####### Product Price and Cart Price are same ####### ");

				driver.close();
				driver.switchTo().window(tabs.get(0));
				break;
			}

		}
	}

	public void waitForElement(WebDriver driver, int timeOutInSeconds, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void scrollToElement() {
		WebElement element = driver.findElement(By.xpath("//button[@class='_2AkmmA _2Npkh4 _2MWPVK']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		element.click();
	}

	public void waitForClickElement(WebDriver driver, int timeOutInSeconds, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

}

package com.flipkart.scripts;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.flipkart.pages.SearchPage;

public class SearchProductTest extends BaseTest {
	public static final Logger log = Logger.getLogger(SearchProductTest.class.getName());
	public WebDriver driver;

	SearchPage sPage;

	@Test
	public void testsearchProduct() throws Exception {
		sPage = new SearchPage(driver);
		sPage.searchProduct("camera");
		sPage.fetchProductName();
	}

}

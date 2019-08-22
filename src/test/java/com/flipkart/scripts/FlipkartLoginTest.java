package com.flipkart.scripts;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.flipkart.pages.LoginPage;
import com.flipkart.pages.SearchPage;

public class FlipkartLoginTest extends BaseTest {

	public static final Logger log = Logger.getLogger(FlipkartLoginTest.class.getName());
	LoginPage lPage;
	SearchPage sPage;

	@BeforeClass
	public void setUp() throws IOException {
		init();
	}

	@Test
	public void testLoginwithValidCredentials() throws Exception {
		log.info(" ####### Started the Login Test ####### ");
		lPage = new LoginPage(driver);
		lPage.loginToApplication("7019375360", "Abc123");
		// Assert.assertEquals(lPage.inValidLoginText(), "Authentication Passed");
		log.info(" ####### Finished the Login Test ####### ");

	}

	@Test
	public void testsearchProduct() throws Exception {
		log.info(" ####### Starting Product search and adding it to the cart ####### ");
		sPage = new SearchPage(driver);
		sPage.searchProduct("camera");
		sPage.fetchProductName();
		log.info(" #######  Product search completed and Added to Cart ####### ");
	}

	@AfterClass
	public void closeBrowser() {
		driver.close();
	}

}

package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.qa.opencart.Factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResPage;

//@Listeners(TestAllureListener.class)  getting error cannot be resolved as a type
public class BaseTest {

	DriverFactory df;
	public WebDriver driver;
	public Properties prop;
	public LoginPage loginpage;
	public AccountsPage accPage;
	public SearchResPage searchResPage;
	public ProductInfoPage productInfoPage;
	public RegistrationPage registrationPage;
    
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(String browserName) {
		df = new DriverFactory();
		prop = df.init_prop();
		prop.setProperty("browser",browserName);
		driver = df.init_driver(prop);
		loginpage = new LoginPage(driver);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}

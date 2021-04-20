package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

public class ProductInfoTest extends BaseTest {

	SoftAssert softAssert = new SoftAssert();

	@BeforeClass
	public void productInfoSetup() {
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}
	
	@DataProvider
	public Object[] [] searchData(){
		return new Object[] [] {{"Macbook"},{"iMac"},{"iPhone"}};
	}
	

	@Test(dataProvider = "searchData")
	public void searchProductCountTest(String productName) {
		searchResPage = accPage.doSearch(productName);
		Assert.assertTrue(searchResPage.getProductResultCount() > 0);
	}

	@Test
	public void productInfoHeaderTest() {
		searchResPage = accPage.doSearch("iMac");
		productInfoPage = searchResPage.selectProductFromResults("iMac");
		Assert.assertEquals(productInfoPage.getProductHeaderText(), "iMac");

	}

	@Test
	public void productImageTest() {
		searchResPage = accPage.doSearch("Macbook");
		productInfoPage = searchResPage.selectProductFromResults("MacBook Pro");
		Assert.assertTrue(productInfoPage.getProductImagesCount() == 4);
	}

	@Test
	public void getProductInfoTest() {

		searchResPage = accPage.doSearch("Macbook");
		productInfoPage = searchResPage.selectProductFromResults("MacBook Pro");
		Map<String, String> actProductMetaData = productInfoPage.getProductInformation();
		actProductMetaData.forEach((k, v) -> System.out.println(k + " : " + v));
		softAssert.assertEquals(actProductMetaData.get("name"), "MacBook Pro");
		softAssert.assertEquals(actProductMetaData.get("Brand"), "Apple");
		softAssert.assertEquals(actProductMetaData.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(actProductMetaData.get("price"), "$2,000.00");

		softAssert.assertAll();
	}
	
	@Test
	
	public void AddtoCart()
	{
		searchResPage = accPage.doSearch("Macbook");
		productInfoPage = searchResPage.selectProductFromResults("MacBook Pro");
		                  productInfoPage.selectQuantity("1");
		                  productInfoPage.addtoCart();
       String Successmessage=productInfoPage.getSuccessMessage();
       
       System.out.println(Successmessage);
       Assert.assertEquals(Successmessage, "Success: You have added MacBook Pro to your shopping cart!\r\n" + 
       		"×");
		                 
	}

}

package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	ElementUtil elementUtil;

	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails li img");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private By itemstab = By.xpath("//button[@class='btn btn-inverse btn-block btn-lg dropdown-toggle']");
	private By Viewcart = By.xpath("//strong[text()=' View Cart']");

	private By successMessg = By.cssSelector("div.alert.alert-success.alert-dismissible");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getProductHeaderText() {
		return elementUtil.doGetText(productHeader);
	}

	public int getProductImagesCount() {
		return elementUtil.getElements(productImages).size();
	}

	/**
	 * This method will collect the product meta data and pricing data information
	 * in the form of HashMap. This method will return productInforMap.
	 */
	public Map<String, String> getProductInformation() {
		Map<String, String> productInfoMap = new HashMap<String, String>();
		productInfoMap.put("name", getProductHeaderText());
		List<WebElement> metaDataList = elementUtil.getElements(productMetaData);
		System.out.println("total product meta data: " + metaDataList.size());

		// meta data
		for (WebElement e : metaDataList) {
			String meta[] = e.getText().split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}
		// price:
		List<WebElement> priceList = elementUtil.getElements(productPriceData);
		String price = priceList.get(0).getText().trim();
		String Exprice = priceList.get(1).getText().trim();

		productInfoMap.put("price", price);
		productInfoMap.put("ExTaxPrice", Exprice);

		return productInfoMap;

	}

	public void selectQuantity(String qty) {

		elementUtil.doSendKeys(quantity, qty);
	}

	public void addtoCart() {

		elementUtil.doClick(addToCartBtn);
	}

	public String getSuccessMessage() {
		elementUtil.waitForElementVisible(successMessg, 5);
		return elementUtil.doGetText(successMessg);
	}

	public void Clickonitems() {
		elementUtil.doClick(itemstab);
	}
 
	
	
}

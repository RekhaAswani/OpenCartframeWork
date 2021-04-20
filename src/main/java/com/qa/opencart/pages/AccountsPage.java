package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	private ElementUtil elementUtil;
	private WebDriver driver;

	private By accSections = By.cssSelector("div#content h2");
	private By header = By.cssSelector("div#logo a");
	private By LogoutLink = By.linkText("Logout");
	private By MyAccountElements = By.xpath("(//ul[@class='list-unstyled'])[8]/li");
	private By MyOrdersElements = By.xpath("(//ul[@class='list-unstyled'])[9]/li");
	private By SearchField = By.name("search");
	private By searchButton = By.cssSelector("div#search button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getAccPageTitle() {
		return elementUtil.waitForTitle(5, Constants.ACCOUNTS_PAGE_TITLE);
	}

	public String getAccPageUrl() {
		return elementUtil.getPageUrl();
	}

	public String getAccPageHeader() {
		return elementUtil.doGetText(header);
	}

	public List<String> getAccountSectionsList() {
		List<String> accSecValList = new ArrayList<String>();
		List<WebElement> accSecList = elementUtil.waitForVisibilityOfElements(accSections, 5);
		for (WebElement e : accSecList) {
			accSecValList.add(e.getText());

		}
		Collections.sort(accSecValList);
		return accSecValList;
	}

	public Boolean isLogoutExist() {
		return elementUtil.doIsDisplayed(LogoutLink);
	}

	public List<String> getMyAccountElements() {
		List<String> MyAccEleValList = new ArrayList<String>();
		List<WebElement> MyAccEleList = elementUtil.waitForVisibilityOfElements(MyAccountElements, 5);
		for (WebElement e : MyAccEleList) {
			MyAccEleValList.add(e.getText());
		}
		Collections.sort(MyAccEleValList);
		return MyAccEleValList;
	}

	public List<String> getMyOrdersElements() {
		List<String> MyOrdersEleValList = new ArrayList<String>();
		List<WebElement> MyOrdEleList = elementUtil.waitForVisibilityOfElements(MyOrdersElements, 5);
		for (WebElement e : MyOrdEleList) {
			MyOrdersEleValList.add(e.getText());
		}
		Collections.sort(MyOrdersEleValList);
		return MyOrdersEleValList;
	}

	// Search Feature
	public SearchResPage doSearch(String productName) {
		System.out.println("Searching the product: " + productName);
		elementUtil.doSendKeys(SearchField, productName);
		elementUtil.doClick(searchButton);
		return new SearchResPage(driver);
	}

}

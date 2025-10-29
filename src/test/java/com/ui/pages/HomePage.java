package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ui.constants.Browser;
import static com.ui.constants.Env.*;
import com.utility.BrowserUtility;
import com.utility.JSONUtility;
import com.utility.LoggerUtility;

import static com.utility.PropertiesUtil.*;

import java.sql.Driver;

public final class HomePage extends BrowserUtility {
	Logger logger = LoggerUtility.getLogger(getClass());

	private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),\"Sign\")]");

	public HomePage(Browser browserName, boolean isHeadless) {
		super(browserName, isHeadless);// To call parent class constructor from child constructor.
		// goTOWebsite(readProperty(QA, "URL"));
		goTOWebsite(JSONUtility.readJson(QA));
	}

	public HomePage(WebDriver driver) {
		super(driver);
		goTOWebsite(JSONUtility.readJson(QA));
	}

	public LoginPage goToLoginPage() {
		logger.info("Trying to perform click to go to sign in page");
		clickon(SIGN_IN_LINK_LOCATOR);
		LoginPage loginPage = new LoginPage(getDriver());
		return loginPage;

	}

	public void quit() {
		System.out.println("reached here");

	}
}

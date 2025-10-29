package com.ui.test;

import static com.ui.constants.Browser.*;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.ui.constants.Browser;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LamdaTestUtility;
import com.utility.LoggerUtility;

public class TestBase {
	
	protected HomePage homePage;
	Logger logger = LoggerUtility.getLogger(getClass());
	private boolean isLamdaTest;
	//private boolean isHeadless = true;
	
	@Parameters({"browser","isLamdaTest","isHeadless"})
	@BeforeMethod(description = "Load the homepage of the webSite")
	public void setup(
			@Optional("Chrome") String browser,
			@Optional("false") boolean isLamdaTest,
			@Optional("true")boolean  isHeadLess, ITestResult result){
			 this.isLamdaTest =isLamdaTest;
		WebDriver lamdaDriver;
		if(isLamdaTest) {	
			lamdaDriver=LamdaTestUtility.initializeLamdaTestSession(browser, result.getMethod().getMethodName());
			homePage = new HomePage(lamdaDriver);
			
		}else {
			logger.info("Load the homepage of the webSite");
			homePage = new HomePage(Browser.valueOf(browser.toUpperCase()),isHeadLess);
			logger.info("Driver from HomePage: " + homePage.getDriver());
		}
		
	}
	
	public BrowserUtility getInstance() {
		return homePage;
	}
	@AfterMethod(description = "tear down the browser")
	public void tearDown() {
		if (isLamdaTest) {
		System.out.println("Inside tear down");
			LamdaTestUtility.quitSession();
		}else {
		//homePage.quit();
	}


}}

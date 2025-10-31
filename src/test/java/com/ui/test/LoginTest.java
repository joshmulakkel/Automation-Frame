package com.ui.test;

import org.testng.annotations.Test;

import static com.ui.constants.Browser.*;

import static org.testng.Assert.*;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.HomePage;
import com.ui.pojo.User;
import com.utility.LoggerUtility;
@Listeners({com.ui.listeners.TestListener.class})

public class LoginTest extends TestBase {
	//HomePage homePage;
	Logger logger = LoggerUtility.getLogger(getClass());


	@Test(description = " verifies user is able to login into  the application", groups = { "Sanity",
			"e2e" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestDataProvider")
	public void loginTest(User user) throws InterruptedException {
		Assert.assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
				"Test abcd");

	}


	@Test(description = " verifies user is able to login into  the application", groups = { "Sanity",
			"e2e" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestCSVtDataProvider")
	public void loginCSVTest(User user) throws InterruptedException {
		Assert.assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
				"Test abcd");

	}

	@Test(description = " verifies user is able to login into  the application", groups = { "Sanity",
	"e2e" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestExcelDataProvider",
	 retryAnalyzer = com.ui.listeners.MyRetryAnalyser.class)
	
	public void loginExcelTest(User user) throws InterruptedException {
		
		
		Assert.assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
		"Test abcd1");
			
	
}}

package com.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.ReadOnlyFileSystemException;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.ui.constants.Browser;

public abstract class BrowserUtility {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	Logger logger = LoggerUtility.getLogger(getClass());

	public WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);
	}


	public BrowserUtility(Browser browserName, boolean isHeadless) {
		logger.info("Launching browser" + browserName);
		if (browserName == Browser.CHROME) {
			if (isHeadless) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");
				driver.set(new ChromeDriver(options));
			}

			else {
				driver.set(new ChromeDriver());
			}
		} else if (browserName == Browser.EDGE) {
			if (isHeadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=new");
				options.addArguments("--disable-gpu");
				driver.set(new EdgeDriver(options));
			} else {
				driver.set(new EdgeDriver());
			}

		} else if (browserName == Browser.FIREFOX) {
			if (isHeadless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless");
				driver.set(new FirefoxDriver(options));
			} else {

				driver.set(new FirefoxDriver());
			}

		}

	}

	public void goTOWebsite(String url) {
		logger.info("visiting the website " + url);
		driver.get().get(url);
	}

	public void maximizeWindow() {
		logger.info("Maximising browser window ");
		driver.get().manage().window().maximize();
	}

	public void clickon(By locator) {
		logger.info("Finding element with the locator " + locator);
		WebElement element = driver.get().findElement(locator);

		element.click();

	}

	public void enterText(By locator, String textToEnter) {
		logger.info("Finding element with the locator " + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element Found and trying to enterText " + textToEnter);

		element.sendKeys(textToEnter);
	}

	public String getVisibleText(By locator) {
		logger.info("Finding element with the locator " + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element Found and returning visiblet" + element.getText());
		return element.getText();
	}

	public String takeScreenShot(String name) {
		TakesScreenshot screenShot = (TakesScreenshot) driver.get();
		File screenShotData = screenShot.getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = format.format(date);
		String path = System.getProperty("user.dir") + "/screenshots/" + name + "_" + timeStamp + ".png";

		File screenShotFile = new File(path);
		try {
			FileUtils.copyFile(screenShotData, screenShotFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return path;

	}
}

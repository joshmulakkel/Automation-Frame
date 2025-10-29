package com.utility;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.NonReadableChannelException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.compress.harmony.pack200.NewAttribute;
import org.checkerframework.common.value.qual.StaticallyExecutable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.opentelemetry.api.trace.StatusCode;

public class LamdaTestUtility {

	public static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";
	private static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<WebDriver>();
	private static ThreadLocal<DesiredCapabilities> capabilitiesLocal = new ThreadLocal<DesiredCapabilities>();

	public static WebDriver initializeLamdaTestSession(String browser, String testName) {
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("browserName", browser);
		capabilities.setCapability("browserVersion", "127");
		Map<String, Object> ltOptions = new HashMap<>();
		ltOptions.put("user", "joshmv2003");
		ltOptions.put("accessKey", "LT_5ZH2wUvYCiwAWx6PiJwACNa7Mmxcu7umMATtg1O5kjHl6xX");
		ltOptions.put("build", "Selenium 4");
		ltOptions.put("name", testName);
		ltOptions.put("platformName", "Windows 10");
		ltOptions.put("seCdp", true);
		ltOptions.put("selenium_version", "4.23.0");
		capabilities.setCapability("LT:Options", ltOptions);
		capabilitiesLocal.set(capabilities);
		WebDriver driver = null;
		try {
			driver = new RemoteWebDriver(new URL(HUB_URL), capabilitiesLocal.get());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driverLocal.set(driver);
		return driverLocal.get();
	}

	public static void quitSession() {
		if (driverLocal.get() != null) {
			driverLocal.get().quit();
		}
	}

}

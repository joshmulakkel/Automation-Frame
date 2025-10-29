package com.ui.listeners;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.test.TestBase;
import com.utility.BrowserUtility;
import com.utility.ExtentReporterUtility;
import com.utility.LoggerUtility;

public class TestListener implements ITestListener {
	Logger logger = LoggerUtility.getLogger(this.getClass());

	ExtentSparkReporter extentSparkReporter;
	ExtentReports extentReports;
	ExtentTest extentTest;

	public void onTestStart(ITestResult result) {
		logger.info(result.getMethod().getMethodName());
		logger.info(result.getMethod().getDescription());
		logger.info(Arrays.toString(result.getMethod().getGroups()));
		// extentTest = extentReports.createTest(result.getMethod().getMethodName());
		ExtentReporterUtility.createExtenttest(result.getMethod().getMethodName());

	}

	public void onTestSuccess(ITestResult result) {
		logger.info(result.getMethod().getMethodName() + " " + "PASSED");
		// extentTest.log(Status.PASS, result.getMethod().getMethodName() + " " +
		// "PASSED");
		ExtentReporterUtility.getTest().log(Status.PASS, result.getMethod().getMethodName() + " " + "PASSED");
	}

	public void onTestFailure(ITestResult result) {
		logger.error(result.getMethod().getMethodName() + " " + "FAILED");
		logger.error(result.getThrowable().getMessage());
		// extentTest.log(Status.FAIL, result.getMethod().getMethodName() + " " +
		// "FAILED");
		ExtentReporterUtility.getTest().log(Status.FAIL, result.getMethod().getMethodName() + " " + "FAILED");
		ExtentReporterUtility.getTest().log(Status.FAIL, result.getThrowable().getMessage());
		Object testObject = result.getInstance();
		BrowserUtility browserUtility = ((TestBase) testObject).getInstance();
		logger.info("Attaching the screenShot for the failed test");
		String screenShotPath = browserUtility.takeScreenShot(result.getMethod().getMethodName());
		logger.info("Attaching the screenShot to the HTML file");
		ExtentReporterUtility.getTest().addScreenCaptureFromPath(screenShotPath);
	}

	public void onTestSkipped(ITestResult result) {
		logger.warn(result.getMethod().getMethodName() + " " + "SKIPPED");
		// extentTest.log(Status.SKIP, result.getMethod().getMethodName() + " " +
		// "SKIPPED");
		ExtentReporterUtility.getTest().log(Status.SKIP, result.getMethod().getMethodName() + " " + "SKIPPED");
	}

	public void onStart(ITestContext context) {
		logger.info("TEST SUITE STARTED");
		ExtentReporterUtility.setUpSparkReporter("report.html");

	}

	public void onFinish(ITestContext context) {
		logger.info("TEST SUITE COMPLETED");
		ExtentReporterUtility.flushReport();
	}

}

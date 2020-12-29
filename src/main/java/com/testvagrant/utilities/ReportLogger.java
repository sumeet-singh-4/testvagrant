package com.testvagrant.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.testvagrant.helper.Helper;

public class ReportLogger {
	public static Logger logger;
	public static ExtentReports report;
	public static ExtentTest reportLogger;

	public static void setupReportLogger() {
				
		logger = Logger.getLogger("ReportLogger");

		PropertyConfigurator.configure(System.getProperty("user.dir")
				+ "\\src\\test\\java\\com\\testvagrant\\resources\\log4j.properties");

		ExtentHtmlReporter extent = new ExtentHtmlReporter(
				new File(System.getProperty("user.dir") + "/reports/report" + Helper.getTimestamp() + ".html"));

		report = new ExtentReports();

		report.attachReporter(extent);
				
	}

	public static void startTest(String method) {
		
		
		try {
			reportLogger = report.createTest(method);
			
			reportLogger.info("Starting a new test : " + method);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

	public static void error(String message) {

		reportLogger.error(message);

		logger.error(message);
	}


	public static void pass(ITestResult result) {
		
		reportLogger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
		
	}

	public static void fail(WebDriver driver, ITestResult result) {
		
		Helper.captureScreenshot(driver);
		
		try {
			
			reportLogger.fail("Testcase failure : " + result.getName(),
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
			
			reportLogger.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Failed ", ExtentColor.RED));
			
			logger.error("Testcase failure : " + result.getName());
		
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
	}

	public static void info(String message) {

		reportLogger.info(message);

		logger.info(message);
	}


	public static void debug(String message) {

		reportLogger.debug(message);

		logger.debug(message);
	}
	
	public static void skip(ITestResult result) {
		
		reportLogger.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.YELLOW));
		
	}
	
	public static void printReport() {
		
		report.flush();
	}

}

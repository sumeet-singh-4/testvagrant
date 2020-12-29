package com.testvagrant.testCaseBase;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.testvagrant.utilities.PropertyReader;
import com.testvagrant.utilities.ReportLogger;

public class TestCaseBase {

	public WebDriver driver = null;

	public Properties properties;

	public static String configFilePath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\com\\testvagrant\\resources\\configuration.properties";

	@BeforeSuite(alwaysRun = true)
	public void setupSuite() {

		ReportLogger.setupReportLogger();

	}

	@SuppressWarnings("deprecation")
	@BeforeMethod(alwaysRun = true)
	public void initialize(Method method) throws InterruptedException, IOException {

		loadPropertyFile();

		ReportLogger.startTest(method.getName());

		String browserName = properties.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {

			ReportLogger.info("Opening browser " + browserName + "...");

			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\testvagrant\\drivers\\chromedriver.exe");

			ChromeOptions options = new ChromeOptions();

			options.addArguments("--no-sandbox");
			
			options.addArguments("--disable-dev-shm-usage");
			
			options.addArguments("--aggressive-cache-discard");
			
			options.addArguments("--disable-cache");
			
			options.addArguments("--disable-application-cache");
			
			options.addArguments("--disable-offline-load-stale-cache");
			
			options.addArguments("--disk-cache-size=0"); 
			
			options.addArguments("--disable-extensions");

			options.addArguments("--disable-gpu");

			options.addArguments("--disable-popup-blocking");

			options.addArguments("--dns-prefetch-disable");

			DesiredCapabilities capabilities = new DesiredCapabilities();

			capabilities.setCapability(ChromeOptions.CAPABILITY, options);

			driver = new ChromeDriver(capabilities);

		}

		driver.manage().window().maximize();

		String ServiceUrl = properties.getProperty("url");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

		driver.get(ServiceUrl);
	}

	public Properties loadPropertyFile() {

		try {

			properties = PropertyReader.readPropertiesFile(configFilePath);

		} catch (IOException e) {

			ReportLogger.error(e.getMessage());

			e.printStackTrace();
		}

		return properties;

	}

	@AfterMethod(alwaysRun = true)
	public void getResult(ITestResult result) {

		try {

			if (result.getStatus() == ITestResult.FAILURE) {

				ReportLogger.fail(driver, result);
			}

			else if (result.getStatus() == ITestResult.SUCCESS) {

				ReportLogger.pass(result);
			}

			else {

				ReportLogger.skip(result);
			}
		}

		catch (Exception e) {

			ReportLogger.error("Error while logging results in report : " + result.getTestName());

			ReportLogger.error((e.getMessage()));
		}

		driver.quit();

	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() {

		ReportLogger.printReport();
	}

}

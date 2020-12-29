package com.testvagrant.genericLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ReportAggregates;
import com.testvagrant.utilities.ReportLogger;

public class GenericMethods {

	public static void handleDropDownUsingVisibleText(WebElement element, String text) {

		try {

			Select dropdown = new Select(element);

			dropdown.selectByVisibleText(text);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static void click(WebElement element) {

		try {

			if (element.isEnabled()) {

				element.click();
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	public static void denyAlert() {

		try {

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static String getText(WebElement element) {

		String text = null;

		try {

			text = element.getText();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return text;
	}
	
	public static void moveToElement(WebDriver driver, WebElement element) {

		Actions act = new Actions(driver);

		try {

			act.moveToElement(element).build().perform();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static void waitTillVisiblity(WebDriver driver, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 10);

		try {

			wait.until(ExpectedConditions.visibilityOf(element));

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static void waitTillElementIsClickable(WebDriver driver, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 10);

		try {

			wait.until(ExpectedConditions.elementToBeClickable(element));

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static void enterText(WebElement element, String text) {

		try {

			ReportLogger.info("Entering "+text+"...");
			element.sendKeys(text);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static WebElement waitTillVisibilityOfElement(WebDriver driver, int timeout, String xpath) {

		WebElement element = null;

		try {

			WebDriverWait wait = new WebDriverWait(driver, timeout);

			element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

		} catch (Exception e) {

			e.printStackTrace();
		}

		return element;
	}

}

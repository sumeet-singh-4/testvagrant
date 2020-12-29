package com.testvagrant.pageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testvagrant.genericLibrary.GenericMethods;
import com.testvagrant.utilities.ReportLogger;

public class NdtvHomePage {

	public NdtvHomePage(WebDriver driver) {

		PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath = "//a[@class='notnow']")
	WebElement declineNotification;
	
	@FindBy(xpath = "//a[@id='h_search_menu']")
	WebElement searchButton;
	
	@FindBy(xpath = "//input[@id='s']")
	WebElement searchField;

	@FindBy(xpath = "//input[@value='GO']")
	WebElement goButton;

	@FindBy(xpath = "//div[@id='topnav_section']")
	WebElement sections;
	
	@FindBy(xpath = "//a[contains(text(),'Weather')]")
	WebElement weatherSections;

	@FindBy(xpath = "//div[@class='message']//label//input")
	List<WebElement> cityCheckbox;
	
	@FindBy(xpath = "//input[@id='searchBox']")
	WebElement pinYourCitySearchbox;
	
	@FindBy(xpath = "//input[@id='Bengaluru']")
	WebElement bengaluruCityCheckbox;
	
	@FindBy(xpath = "//div[@title='Bengaluru']//img")
	WebElement cityWeatherDetailsOption;
	
	@FindBy(xpath = "//b[contains(text(),'Temp in Fahrenheit')]")
	WebElement tempInFahrenheit;

	public void goToWeatherSection(String sectionName) {

		ReportLogger.info("Declining the pop up...");
		GenericMethods.click(declineNotification);
		ReportLogger.info("Clicking on the search button...");
		GenericMethods.click(searchButton);
		ReportLogger.info("Entering the text : "+sectionName+" in the textfield...");
		GenericMethods.enterText(searchField, sectionName);
		ReportLogger.info("Clicking on the go button");
		GenericMethods.click(goButton);
		ReportLogger.info("Clicking on sections...");
		GenericMethods.click(sections);
		ReportLogger.info("Selecting the weather section...");
		GenericMethods.click(weatherSections);

	}

	public void selectCity(String cityName) throws InterruptedException {
		ReportLogger.info("Unchecking all the selected cities...");
		for (WebElement ele : cityCheckbox) {
			
			if (ele.isSelected()) {
				GenericMethods.click(ele);
			}
		}
		
		ReportLogger.info("Searching the required city...");
		GenericMethods.enterText(pinYourCitySearchbox, cityName);
		
		ReportLogger.info("Clicking on the checkbox...");
		GenericMethods.click(bengaluruCityCheckbox);
		
		
	}
	
	public Float fetchCityTemperature() {
		
		ReportLogger.info("Clicking the city weather details...");
		GenericMethods.click(cityWeatherDetailsOption);
		
		ReportLogger.info("Fetching the city temperature in Fahrenheit...");
		return Float.parseFloat(GenericMethods.getText(tempInFahrenheit).split(": ")[1]);
		
	}

}

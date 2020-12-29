package com.testvagrant.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.testvagrant.exceptions.TemperatureMaxOut;
import com.testvagrant.helper.TestDataHelper;
import com.testvagrant.pageObject.NdtvHomePage;
import com.testvagrant.pageObject.controller.verifyMoviesNames.VerifyCityTemperature_Manager;
import com.testvagrant.testCaseBase.TestCaseBase;
import com.testvagrant.utilities.ReportLogger;

public class NdtvTest extends TestCaseBase {

	NdtvHomePage homePage;

	@Test(dataProvider = "ExcelDataProvider", dataProviderClass = TestDataHelper.class)
	void verifyCityTemperature(String sectionName, String cityName, String api, String unit) throws InterruptedException {

		homePage.goToWeatherSection(sectionName);

		homePage.selectCity(cityName);

		Float uiResponse = homePage.fetchCityTemperature();
		
		ReportLogger.info("Temperature value from the UI : " + uiResponse);

		Float apiResponse = new VerifyCityTemperature_Manager().validateTestCase(properties.getProperty(api), cityName,
				unit);

		ReportLogger.info("Temperature value from the API : " + apiResponse);
		
		try{
			if (uiResponse - apiResponse > 1) {
				throw new TemperatureMaxOut("Temperature ranged exceeded");
			} else {
				ReportLogger.info("Temperature within specified range.");
			}
		} catch (Exception e) {
			ReportLogger.error("Exception occured : "+e);
			Assert.fail();
		}
	}

	@BeforeMethod
	void beforeTest() {
		homePage = new NdtvHomePage(driver);
	}

}

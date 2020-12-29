
package com.testvagrant.helper;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.testvagrant.utilities.ExcelReader;




public class TestDataHelper {

	@DataProvider(name = "ExcelDataProvider")
	public static Object[][] getDataFromDataProvider(Method method) {
		String pathName = System.getProperty("user.dir")
				+ "\\src\\test\\java\\com\\testvagrant\\resources\\testData\\" + method.getName() + ".xlsx";

		System.out.println(pathName);

		Object[][] testDataObject = ExcelReader.getDataFromExcel(pathName);

		return testDataObject;
	}
}

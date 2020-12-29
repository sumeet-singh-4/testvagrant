package com.testvagrant.helper;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Helper {

	public static String getTimestamp() {
		DateFormat dateFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate = new Date();
		return dateFormat.format(currentDate);
	}

	public static String captureScreenshot(WebDriver driver) {
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String folderPath = System.getProperty("user.dir")+"/Screenshots";
		if(!(new File(folderPath).exists())) 
    		new File(folderPath).mkdir();
		String screenshotPath = System.getProperty("user.dir")+"/Screenshots/screenshot_" + getTimestamp() + ".png";
		System.out.println("Screenshot captured");
		try {
			FileHandler.copy(src, new File(screenshotPath));
		} catch (IOException e) {
			System.out.println("Unable to capture screenshot : "+e.getMessage());
		}
		
		return screenshotPath;
	}
	
}

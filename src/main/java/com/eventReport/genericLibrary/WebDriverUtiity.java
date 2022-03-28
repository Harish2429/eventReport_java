package com.eventReport.genericLibrary;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class WebDriverUtiity {

	public static String takeScreenshot(WebDriver staticDriver, String methodName) {
		
		String dateTime = new SimpleDateFormat("dd_MM_yyyy hh-mm-ss").format(new Date());
		
		EventFiringWebDriver eDriver=new EventFiringWebDriver(BaseClass.staticDriver);
		File srcFile=eDriver.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/Screenshots/" + methodName+" "+dateTime+".png";
		String path1 = ("./Screenshots/" + methodName+" "+dateTime+".png");
		System.out.println(path);
		System.out.println(path1);
		
		File destFile = new File(path);
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}

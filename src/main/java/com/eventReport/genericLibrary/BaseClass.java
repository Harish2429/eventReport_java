package com.eventReport.genericLibrary;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public WebDriver driver;
	public static WebDriver staticDriver;
	public static ExtentReports report;
	public static ExtentTest test;
	String dateTime = new SimpleDateFormat("dd_MM_yyyy hh-mm-ss").format(new Date());

	@BeforeSuite
	public void configBS() {
		// DB connection
		
		System.out.println(dateTime);
		ExtentHtmlReporter htmlReport = new ExtentHtmlReporter(
				new File(".\\ExtentReports\\report" + dateTime + ".html"));
		htmlReport.config().setDocumentTitle("Extent Report");
		htmlReport.config().setTheme(Theme.DARK);
		htmlReport.config().setReportName("Functional Test");
		report = new ExtentReports();
		report.attachReporter(htmlReport);
		report.setSystemInfo("TestURL", "https://example.com");
		report.setSystemInfo("Platform", "Windows 10");
		report.setSystemInfo("Reporter Name", "Surajkumar");
	}

	@BeforeTest
	public void configBT() {
		// parallel execution
	}

	@BeforeClass
	public void configBC() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://localhost:8888/");
		staticDriver = driver;
	}

	@BeforeMethod
	public void setUp() {
//		driver.findElement(By.name("user_name")).sendKeys("admin");
//		driver.findElement(By.name("user_password")).sendKeys("admin", Keys.ENTER);
//		
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getMethod().getMethodName() + " is failed");
			test.log(Status.FAIL, result.getThrowable());
			WebDriverUtiity.takeScreenshot(staticDriver, result.getMethod().getMethodName());
			/*
			 * String methodName = result.getMethod().getMethodName(); EventFiringWebDriver
			 * eDriver=new EventFiringWebDriver(BaseClass.staticDriver); File
			 * srcFile=eDriver.getScreenshotAs(OutputType.FILE); String path =
			 * System.getProperty("user.dir") + "/Screenshots/" +
			 * methodName+" "+dateTime+".png"; String path1 = ("./Screenshots/" +
			 * methodName+" "+dateTime+".png"); System.out.println(path);
			 * System.out.println(path1);
			 * 
			 * File destFile = new File(path); try { FileUtils.copyFile(srcFile, destFile);
			 * } catch (IOException e) { e.printStackTrace(); }
			 */
			String path = System.getProperty("user.dir") + "/Screenshots/" + result.getMethod().getMethodName()+ ".png";

			test.addScreenCaptureFromPath(path, result.getMethod().getMethodName());

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getMethod().getMethodName() + " is passed");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, result.getMethod().getMethodName() + " is skipped");
			test.log(Status.SKIP, result.getThrowable());
		}

		driver.close();
	}

	@AfterClass
	public void configAC() {
		// close the browser
	}

	@AfterTest
	public void configAT() {
		// close browser launched in parallel
	}

	@AfterSuite
	public void configAS() {
		report.flush();
		// close db
	}

}

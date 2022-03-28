package com.eventReport.testScript;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.eventReport.genericLibrary.BaseClass;

public class Testcase2 extends BaseClass {
	@Test
	public void testcase2() {
		test = report.createTest("testcase2");
		test.log(Status.INFO, "entering the username");
		driver.findElement(By.name("user_name")).sendKeys("Admin");
		test.log(Status.INFO, "entering the password");
		driver.findElement(By.name("user_password")).sendKeys("manager");
		Assert.assertTrue(false);

	}

}

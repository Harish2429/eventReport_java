package com.eventReport.testScript;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.eventReport.genericLibrary.BaseClass;

public class TestCase3 extends BaseClass {
	@Test
	public void testcase3() {
		test = report.createTest("testcase3");
		test.log(Status.INFO, "testcase 3 executed");
		System.out.println("testcase3 is executed");
	}

}

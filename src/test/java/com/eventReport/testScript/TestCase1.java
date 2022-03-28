package com.eventReport.testScript;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.eventReport.genericLibrary.BaseClass;

public class TestCase1 extends BaseClass {
	@Test
	public void testcase1() {

		test=report.createTest("testcase1");
		test.log(Status.INFO, "testcase 1 executed");

		System.out.println("Testcase1 is executed");
	}

}

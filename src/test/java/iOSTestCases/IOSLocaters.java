package iOSTestCases;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import genaralUtility.IOSBaseClass;
import pageObjectModel.IOS.AlertViewPOM;

public class IOSLocaters extends IOSBaseClass {

	@Test
	public void IOSBasics() {
		AlertViewPOM alertView = homePage.selectAlertView();
		alertView.fillTextLabel("hellow");
		String actualMessage = alertView.getConfirmMessage();
		assertEquals(actualMessage, "A message should be a short, complete sentence.");
	}

}

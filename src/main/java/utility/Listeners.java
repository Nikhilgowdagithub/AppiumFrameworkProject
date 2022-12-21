package utility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;

public class Listeners extends AppiumUtility implements ITestListener {
	ExtentTest test;
	AppiumDriver driver;

	ThreadLocal<ExtentTest> extendTest = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extendTest.set(test);

	}

	public void onTestSuccess(ITestResult result) {
		extendTest.get().pass("Test Case passed");

	}

	public void onTestFailure(ITestResult result) {
		extendTest.get().fail(result.getThrowable());

		try {
			driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {

			e.printStackTrace();
		}

		try {
			extendTest.get().addScreenCaptureFromPath(getScreenShot(driver, result.getMethod().getMethodName()),
					result.getMethod().getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onFinish(ITestContext context) {
		extent.flush();

	}

}

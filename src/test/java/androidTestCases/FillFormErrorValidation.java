package androidTestCases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import genaralUtility.AndroidBaseClass;
import io.appium.java_client.AppiumBy;

public class FillFormErrorValidation extends AndroidBaseClass {

	@BeforeMethod
	public void preSetUp() {
		fillForm.setActivityHome();

	}

	@Test
	public void FillForm() throws InterruptedException {

		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Rahul Shetty");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size() < 1);

	}

	@Test
	public void FillFormErrrorValidation() throws InterruptedException {

		// driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		Assert.assertEquals(toastMessage, "Please enter your name");

	}
}

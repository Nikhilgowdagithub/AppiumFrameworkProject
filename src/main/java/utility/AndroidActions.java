package utility;

import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class AndroidActions extends AppiumUtility {
	AndroidDriver driver;

	public AndroidActions(AndroidDriver driver) {
		this.driver = driver;
	}

	public void LongPress(WebElement ele, int durationinInMillisec) {

		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId()), "duration", durationinInMillisec);
	}

	public void DragGesture(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "endX", 100, "endY", 100));

	}

	public WebElement ScrollTOText(String text) {
		return driver.findElement(AppiumBy
				.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"));
	}

	public void ScrollWithoutLocation(String direction) {
		boolean canScrollMore;

		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", direction, "percent", 3.0));
		} while (canScrollMore);
	}

	public void Swipe(WebElement ele, String direction) {

		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) ele).getId(), "direction", direction, "percent", 0.75));

	}

	public void DragAndDrop(WebElement ele, int endX, int endY) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "endX", endX, "endY", endY));
	}

	public void LandScapeMode() {

		DeviceRotation landScape = new DeviceRotation(0, 0, 90);
		driver.rotate(landScape);
		// now devise will roate 90 degree

	}

	public void KeybordActivity(AndroidKey action) {

		driver.pressKey(new KeyEvent(action));

	}

}

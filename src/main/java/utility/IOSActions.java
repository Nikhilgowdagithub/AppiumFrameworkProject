package utility;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import io.appium.java_client.ios.IOSDriver;

public class IOSActions extends AppiumUtility {

	IOSDriver driver;

	public IOSActions(IOSDriver driver) {

		this.driver = driver;

	}

	// https://github.com/clarabez/appium-1/blob/master/docs/en/writing-running-appium/ios/ios-xctest-mobile-gestures.md
	public void LongPress(WebElement ele) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("element", ((RemoteWebElement) ele).getId());
		params.put("duration", 5);

		driver.executeScript("mobile:touchAndHold", params);

	}

	public void Scroll(WebElement ele, String direction) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("direction", direction);
		params.put("element", ((RemoteWebElement) ele).getId());
		driver.executeScript("mobile:scroll", params);

	}

	public void SwipeWithDirection(String direction) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("direction", direction);
		driver.executeScript("mobile:swipe", params);

	}

	public void SwipeWithElementAndDirection(WebElement ele, String direction) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("direction", direction);
		params.put("element", ((RemoteWebElement) ele).getId());

		driver.executeScript("mobile:swipe", params);

	}

}

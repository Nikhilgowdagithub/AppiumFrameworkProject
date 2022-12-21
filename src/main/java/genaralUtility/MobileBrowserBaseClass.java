package genaralUtility;

import java.io.IOException;
import java.time.Duration;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import utility.AppiumUtility;

public class MobileBrowserBaseClass extends AppiumUtility {
	public AndroidDriver driver;
	public AppiumDriverLocalService server;

	@BeforeTest
	public void configerAppium() throws IOException {

		server = startAppiumServer();

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(praperty.getProperty("deviceName"));
		options.setChromedriverExecutable(chromeDriverPath);
		options.setCapability("browserName", praperty.getProperty("browser"));// **********************

		driver = new AndroidDriver(server.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	public double ConvertStringTOdouble(String stringPrice) {
		double price = Double.parseDouble(stringPrice.substring(1));
		return price;

	}

	@AfterTest
	public void tearDown() {

		driver.quit();
		service.stop();
		// stop() will Stop the server service.stop();

	}

}

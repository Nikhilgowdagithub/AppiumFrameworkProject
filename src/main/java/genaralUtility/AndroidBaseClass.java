package genaralUtility;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import pageObjectModel.Android.FormPagePOM;
import utility.AppiumUtility;

public class AndroidBaseClass extends AppiumUtility {

	public AndroidDriver driver;
	public AppiumDriverLocalService server;
	public FormPagePOM fillForm;

	@BeforeTest(alwaysRun = true)
	public void configerAppium() throws IOException {

		startAppiumServer();

		UiAutomator2Options options = new UiAutomator2Options();

		options.setDeviceName(praperty.getProperty("deviceName"));
		options.setApp(apkResoursePath);
		options.setChromedriverExecutable(chromeDriverPath);

		driver = new AndroidDriver(service.getUrl(), options);
		// new URL("http://127.0.0.1:4723") insted use getUrl
		fillForm = new FormPagePOM(driver);

	}

	@AfterTest(alwaysRun = true)
	public void tearDown() {

		driver.quit();
		service.stop();

	}

}

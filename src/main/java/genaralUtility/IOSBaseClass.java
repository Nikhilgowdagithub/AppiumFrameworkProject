package genaralUtility;

import java.io.IOException;
import java.time.Duration;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import pageObjectModel.IOS.HomePagePOM;
import utility.AppiumUtility;

public class IOSBaseClass extends AppiumUtility {
	// xcode is used insted of android studio to perform Automation on IOS
	public IOSDriver driver;
	public AppiumDriverLocalService server;
	public HomePagePOM homePage;

	@BeforeTest(alwaysRun = true)
	public void configerAppium() throws IOException {

		server = startAppiumServer();

		XCUITestOptions options = new XCUITestOptions();
		options.setDeviceName(praperty.getProperty("deviceName"));
		options.setApp(iosAppPath);
		options.setPlatformVersion(praperty.getProperty("IOSPlotformVersion"));
		options.setWdaLaunchTimeout(Duration.ofSeconds(20));

		driver = new IOSDriver(server.getUrl(), options);

		homePage = new HomePagePOM(driver);
	}

	@AfterTest(alwaysRun = true)
	public void tearDown() {

		driver.quit();
		service.stop();
		// stop() will Stop the server service.stop();

	}

}

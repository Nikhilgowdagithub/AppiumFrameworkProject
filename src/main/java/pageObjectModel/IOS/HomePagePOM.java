package pageObjectModel.IOS;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import utility.IOSActions;

public class HomePagePOM extends IOSActions {
	IOSDriver driver;

	public HomePagePOM(IOSDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	@iOSXCUITFindBy(accessibility = "Alert Views")
	private WebElement alertView;

	public AlertViewPOM selectAlertView() {

		alertView.click();
		return new AlertViewPOM(driver);

	}
}

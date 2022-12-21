package pageObjectModel.IOS;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import utility.IOSActions;

public class AlertViewPOM extends IOSActions {
	IOSDriver driver;

	public AlertViewPOM(IOSDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label=='Text Entry'`]")
	private WebElement textEntryMenu;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell")
	private WebElement textBox;

	@iOSXCUITFindBy(accessibility = "OK")
	private WebElement acceptPopup;

	@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'Confirm'")
	private WebElement confirmMenueItem;

	@iOSXCUITFindBy(iOSNsPredicate = "name BEGINSWITH[c] 'A message'")
	private WebElement confirmMessage;

	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Confirm'")
	private WebElement submit;

	public void fillTextLabel(String name) {
		textEntryMenu.click();
		textBox.sendKeys(name);
		acceptPopup.click();
	}

	public String getConfirmMessage() {
		confirmMenueItem.click();
		String text = confirmMessage.getText();
		submit.click();
		return text;

	}
}

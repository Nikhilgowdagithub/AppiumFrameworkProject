package pageObjectModel.Android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utility.AndroidActions;

public class FormPagePOM extends AndroidActions {
	AndroidDriver driver;

	public FormPagePOM(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
//we use AppiumFieldDecorator to construct webelements in background this ia a type of decorate for Appium
//this is used to specify which object need to be constructed by giving this it will refare to the current object from this page

	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement nameField;

	@AndroidBy(xpath = "//android.widget.RadioButton[@text='Male']")
	private WebElement maleGender;

	@AndroidBy(xpath = "//android.widget.RadioButton[@text='Female']")
	private WebElement femalegender;

	@AndroidBy(id = "android:id/text1")
	private WebElement selectCountryDropDown;

	@AndroidBy()
	private WebElement shopButton;

	public void enterName(String name) {

		nameField.sendKeys(name);
		driver.hideKeyboard();
	}

	public void selectGender(String gender) {

		if (gender.contains("male"))
			maleGender.click();
		else
			femalegender.click();

	}

	public void selectCountry(String Country) {

		selectCountryDropDown.click();
		ScrollTOText(Country).click();

	}

	public PraductCathalogPOM submitForm() {

		shopButton.click();
		return new PraductCathalogPOM(driver);
	}

	public void setActivityHome() {
		Activity activity = new Activity("com.androidsample.generalstore",
				"com.androidsample.generalstore.MainActivity");
		driver.startActivity(activity);
	}

}

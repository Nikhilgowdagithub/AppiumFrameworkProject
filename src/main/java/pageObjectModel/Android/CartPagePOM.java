package pageObjectModel.Android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utility.AndroidActions;

public class CartPagePOM extends AndroidActions {
	AndroidDriver driver;

	public CartPagePOM(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	@AndroidBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productPrices;

	@AndroidBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement displayedSum;

	@AndroidBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement tcHoldele;

	@AndroidBy(id = "android:id/button1")
	private WebElement tcPopClose;

	@AndroidBy(className = "android.widget.CheckBox")
	private WebElement checkbox;

	@AndroidBy(id = "com.androidsample.generalstore:id/btnProceed")
	private WebElement proceed;


	public double totalProductValue() {

		int count = productPrices.size();
		double totalSum = 0;
		for (int i = 0; i < count; i++) {
			String amountString = productPrices.get(i).getText();
			double price = ConvertStringTOdouble(amountString);
			totalSum = totalSum + price;

		}
		return totalSum;

	}

	public double totalDisplayedSum() {
		return ConvertStringTOdouble(displayedSum.getText());
	}

	public void acceptTermsAndCondition() {
		LongPress(tcHoldele, 2000);
		tcPopClose.click();

	}

	public void submitorder() {

		checkbox.click();
		proceed.click();

	}

}

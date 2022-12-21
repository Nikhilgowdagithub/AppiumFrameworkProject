package pageObjectModel.Android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utility.AndroidActions;

public class PraductCathalogPOM extends AndroidActions {
	public AndroidDriver driver;

	public PraductCathalogPOM(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidBy(id = "com.androidsample.generalstore:id/productName")
	private List<WebElement> searchProduct;

	@AndroidBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement submitCart;

	@AndroidBy(id = "com.androidsample.generalstore:id/toolbar_title")
	private WebElement waitElement;

	@AndroidBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
	private List<WebElement> productAddToCart;

	public void searchProduct(String productName) {
		ScrollTOText(productName);

		int productCount = searchProduct.size();
		for (int i = 0; i < productCount; i++) {

			String productName1 = searchProduct.get(i).getText();

			if (productName1.equalsIgnoreCase(productName)) {

				searchProduct.get(i).click();
			}
		}

	}

	public String cartPage() {
		return searchProduct.get(0).getText();

	}

	public void addItemToCartByIndex(int index) {

		productAddToCart.get(index).click();

	}

	public CartPagePOM goToCheckoutPage() {
		submitCart.click();
		WaitattributeContains(driver, waitElement, "text", "Cart");
		return new CartPagePOM(driver);
	}

}

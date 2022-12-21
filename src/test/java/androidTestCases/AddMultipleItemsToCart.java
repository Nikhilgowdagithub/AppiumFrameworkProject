package androidTestCases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import genaralUtility.AndroidBaseClass;
import pageObjectModel.Android.CartPagePOM;
import pageObjectModel.Android.PraductCathalogPOM;

public class AddMultipleItemsToCart extends AndroidBaseClass {
	@Test(dataProvider = "data")
	public void MultipleItems(HashMap<String, String> input) throws InterruptedException {
		fillForm.enterName(input.get("name"));
		fillForm.selectGender(input.get("gender"));
		fillForm.selectCountry(input.get("country"));
		PraductCathalogPOM product = fillForm.submitForm();
		product.addItemToCartByIndex(0);
		product.addItemToCartByIndex(0);
		CartPagePOM checkout = product.goToCheckoutPage();
		assertEquals(checkout.totalProductValue(), checkout.totalDisplayedSum());
		checkout.acceptTermsAndCondition();
		checkout.submitorder();

	}

	@DataProvider
	public Object[][] data() throws IOException {

		List<HashMap<String, String>> data = getJsonData(jsonFilePath);

		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}

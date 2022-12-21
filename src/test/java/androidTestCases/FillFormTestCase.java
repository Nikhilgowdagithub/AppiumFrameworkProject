package androidTestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import genaralUtility.AndroidBaseClass;
import pageObjectModel.Android.PraductCathalogPOM;

public class FillFormTestCase extends AndroidBaseClass {
	@Test(dataProvider = "data", groups = "smoke")
	public void FillForm(HashMap<String, String> input) {

		fillForm.enterName(input.get("name"));
		fillForm.selectGender(input.get("gender"));
		fillForm.selectCountry(input.get("country"));
		PraductCathalogPOM product = fillForm.submitForm();
		product.searchProduct(input.get("productName"));
		product.goToCheckoutPage();
		String lastPageProduct = product.cartPage();
		Assert.assertEquals(lastPageProduct, input.get("productName"));
	}

	@BeforeMethod(alwaysRun = true)
	public void preSetup() {
		fillForm.setActivityHome();
		/*
		 * it will navigate back to homepage to start over the Tc from start or else
		 * weget Error due to driver will be in last page ofpreview TC
		 */
	}

	@DataProvider
	public Object[][] data() throws IOException {

		List<HashMap<String, String>> data = getJsonData(jsonFilePath);
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

	/*
	 * public Object[][] data() {
	 * 
	 * return new Object[][] { { "nikhil", "male", "Argentina", "Jordan 6 Rings" },
	 * { "nikhil1", "female", "Angola", "Jordan 6 Rings" } };
	 * 
	 * }
	 */

}

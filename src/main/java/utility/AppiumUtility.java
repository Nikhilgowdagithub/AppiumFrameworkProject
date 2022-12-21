package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import genaralUtility.pathUtilitys;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumUtility implements pathUtilitys {
	public AppiumDriverLocalService service;
	public Properties praperty;
	public ExtentReports extent;

	public AppiumDriverLocalService startAppiumServer() throws IOException {

		String ipAddress = System.getProperty("ipaddress") != null ? System.getProperty("ipaddress")
				: praperty.getProperty("IPaddress");
		service = new AppiumServiceBuilder().withAppiumJS(new File(appiumJSNodePath)).withIPAddress(ipAddress)
				.usingPort(Integer.parseInt(praperty.getProperty("port"))).build();
		// bcz all the data we get from property will be in String so conveting string
		// inti integer
		service.start();

		return service;
	}

	public double ConvertStringTOdouble(String stringPrice) {
		double price = Double.parseDouble(stringPrice.substring(1));
		return price;
	}

	public void WaitattributeContains(AppiumDriver driver, WebElement ele, String attributeKey, String attributeValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(ele, attributeKey, attributeValue));

	}

	public List<HashMap<String, String>> getJsonData(String pathofFile) throws IOException {

		String jsonContent = FileUtils.readFileToString(new File(pathofFile));
		// converting json object in to json string

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;

	}

	public Properties getDataFromProperty() throws IOException {

		praperty = new Properties();
		FileInputStream fis = new FileInputStream(propertyFilePath);
		praperty.load(fis);

		return praperty;

	}

	public String getScreenShot(AppiumDriver driver, String testCaseName) throws IOException {

		File source = driver.getScreenshotAs(OutputType.FILE);
		String destination = ".//AppiumFrameworkProject//reports" + testCaseName + "png";
		FileUtils.copyFile(source, new File(destination));
		return destination;

	}

	public ExtentReports extentReports(String testCaseName) {

		ExtentSparkReporter reporter = new ExtentSparkReporter(extentReportFilePath);
		reporter.config().setReportName("Appium Automation");
		reporter.config().setDocumentTitle("Test Results");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Nikhil");
		return extent;

	}

}

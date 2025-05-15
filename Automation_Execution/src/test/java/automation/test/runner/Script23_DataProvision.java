package automation.test.runner;

import org.testng.Reporter;
import org.testng.annotations.Test;

import automation.main.webdriver.util.DriverUtility;
import automation.main.webdriver.util.WebDriverUtility;
import automation.test.configuration.Configuration;

public class Script23_DataProvision {

	@Test(dataProviderClass = Configuration.class, dataProvider = "dataSet1")
	public void trail(String id, String title, String lastName, String firstName, String Login, String sys,
			String gender, String employee, String zip, String city, String country, String mob, String job) {

		System.out.println(id + " " + title + " " + lastName + " " + firstName + " " + Login + " " + sys + " " + gender
				+ " " + employee + " " + zip + " " + city + " " + country + " " + mob + " " + job);
	}

	@Test(dataProviderClass = Configuration.class, dataProvider = "dataSet2")
	public void flipKart(String search, String Product) {
		try {
			DriverUtility.browser();
			WebDriverUtility webdriver = new WebDriverUtility(DriverUtility.getDriver());
			webdriver.launchApplication("https://www.flipkart.com/search");
			webdriver.implicitlyWait();
			webdriver.elementEnter("xpath", "//input[@placeholder='Search for products, brands and more']", search);
			webdriver.elementClick("xpath", "//button[@type='submit']");
			String price = webdriver.elementText("xpath", "//div[text()='" + Product + "']/../../div[2]/div");
			Reporter.log(search+"\t"+Product+"\t"+price, true);
		} catch (Exception e) {
			e.getMessage();
		} finally {
			DriverUtility.quit();
		}
	}

}

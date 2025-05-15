package automation.test.base;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import automation.main.pageobjects.Dashboard_NinzaHRM;
import automation.main.pageobjects.LoginPage_NinzaHRM;

public class BaseScript_Ninza_Hrm {
	JSON_Reader read;
	public WebDriver driver;
	LoginPage_NinzaHRM page;
	Dashboard_NinzaHRM dash;
	@BeforeClass
	public void launchBrowser() throws FileNotFoundException, IOException, ParseException {
		read = new JSON_Reader();
		switch (read.browser().toLowerCase()) {
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			driver = new ChromeDriver();
			break;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((long) read.timeOut()));
		page = new LoginPage_NinzaHRM(driver);
		dash = new Dashboard_NinzaHRM(driver);
		driver.get(read.testURL());
	}
	@BeforeMethod
	public void launchApplication() throws AWTException {
		page.enterUserName().sendKeys(read.userName());
		page.enterPassword().sendKeys(read.password());
		page.LoginButton().click();		
	}
	@AfterMethod
	public void windDown() {
		dash.LogoutButton().click();
		
	}
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}

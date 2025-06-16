package automation.test.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import automation.main.webdriver.util.CloudPropertyReader;

public class CloudBaseClass {
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected JavascriptExecutor java;
	@BeforeSuite
	public void browserSetup() {
		String browser = CloudPropertyReader.getProperty("browser");
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			throw new IllegalArgumentException("Invalid Browser");
		}
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		java = (JavascriptExecutor) driver;
	}
	
	@BeforeMethod
	public void applicationSetup() {
		driver.get(CloudPropertyReader.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(CloudPropertyReader.getProperty("timeout"))));
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(CloudPropertyReader.getProperty("username"));
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(CloudPropertyReader.getProperty("password"));
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
	}
	
	@AfterMethod
	public void applicationTearDown() {
		driver.findElement(By.xpath("//img[@src='/assets/media/user.png']")).click();
		driver.findElement(By.linkText("Sign Out")).click();
	}
	
	@AfterSuite(alwaysRun = true)
	public void browserTearDown() {
		driver.quit();
	}
}

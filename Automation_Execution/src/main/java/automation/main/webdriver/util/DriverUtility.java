package automation.main.webdriver.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverUtility {

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static WebDriver browser() {
		if (driver.get() == null) {
			String browser = ConfigurationUtil.getProperty("browser");
			switch (browser.toLowerCase()) {
			case "chrome":
				driver.set(new ChromeDriver());
				break;
			case "firefox":
				driver.set(new FirefoxDriver());
				break;
			case "edge":
				driver.set(new EdgeDriver());
				break;

			default:
				throw new RuntimeException("Failed to Load : " + browser + " is Not Supported");
			}
		}
		return driver.get();
	}
	
	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void quit() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}

}

package automation.test.runner;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Script38_Parallel {
	
	private WebDriver driver;

	@BeforeMethod
	@Parameters("Browser")
	public void setUp(String browser) {
		switch (browser) {
			case "chrome" -> driver = new ChromeDriver( );
			case "firefox" -> driver = new FirefoxDriver( );
			
			default ->
			throw new IllegalArgumentException("Unexpected value: " + browser);
		}
		driver.manage( ).timeouts( ).implicitlyWait( Duration.ofSeconds( 10 ) );
	}
	
	@Test
	public void test() {
		driver.get( "https://www.google.com" );
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit( );
	}
}

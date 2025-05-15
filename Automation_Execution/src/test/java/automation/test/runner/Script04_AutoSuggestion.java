package automation.test.runner;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Script04_AutoSuggestion {
	WebDriver driver;
	@BeforeMethod
	public void openBrowser() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		driver = new ChromeDriver(options);		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	//Auto Suggestion
	@Test(priority = 1)
	public void AutoSuggestion() {
		driver.get("https://www.amazon.in");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("ip");
		driver.findElement(By.xpath("//span[contains(text(),'ad 11th generation')]")).click();
		driver.quit();
	}
	//disappearing Auto Suggestion
	@Test(priority = 2)
	public void AutoSuggestionDisappear() {
		driver.get("https://www.flipkart.com");
		driver.findElement(By.name("q")).sendKeys("ip");
		driver.findElement(By.xpath("//div[contains(text(),'hone 16e')]")).click();
	}
	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		driver.quit();		
	}

}

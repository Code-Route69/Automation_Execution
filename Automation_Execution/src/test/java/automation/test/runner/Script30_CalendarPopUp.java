package automation.test.runner;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Script30_CalendarPopUp {
	
	WebDriver driver;
	
	@BeforeMethod
	public void browserInitializing() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}
	
	@Test
	public void redBus() {
		driver.get("https://www.redbus.in/");
		driver.findElement(By.xpath("//div[.='From']")).click();
		driver.findElement(By.xpath("//input[@id='srcDest']")).sendKeys( "Bangalore" );
		driver.findElement(By.xpath("//div[text()='Bangalore' and @class='listHeader___90a8b7' ]")).click();
//		driver.findElement(By.xpath("//div[text()='To']")).click();
//		driver.findElement(By.xpath("//div[@class='listHeader___90a8b7']")).click();
		
	}
	
	@Test
	public void KSRTC() {
		driver.get("https://ksrtc.in/");
		driver.findElement(By.xpath("//div[@role='button' and @class='srcDestWrapper___e67e69']")).click();
		driver.findElement(By.xpath("//div[text()='Bangalore' and @class='listHeader___90a8b7' ]")).click();
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
	

}

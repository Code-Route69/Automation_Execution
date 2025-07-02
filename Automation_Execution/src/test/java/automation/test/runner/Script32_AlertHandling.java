package automation.test.runner;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Script32_AlertHandling {

	@Test
	public void javaScriptAlertHandlingAccept() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://demoapps.qspiders.com/ui/alert?sublist=0");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		TimeUnit.SECONDS.sleep(5);
		//Deleting SAMSUNG Galaxy 
		driver.findElement(By.xpath("//td[text()='SAMSUNG Galaxy']/..//input[@type='checkbox']")).click();
		driver.findElement(By.id("deleteButton")).click();
		TimeUnit.SECONDS.sleep(5);
		driver.switchTo().alert().accept();
		driver.quit();
	}

	@Test
	public void javaScriptAlertHandlingPrompt() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://demoapps.qspiders.com/ui/alert?sublist=0");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		TimeUnit.SECONDS.sleep(5);
		// Deleting SAMSUNG Galaxy
		driver.findElement(By.xpath("//a[text()='Prompt']")).click();
		TimeUnit.SECONDS.sleep(5);
		driver.findElement(By.xpath("//td[text()='SAMSUNG Galaxy']/..//input[@type='checkbox']")).click();
		driver.findElement(By.id("deleteButton")).click();
		TimeUnit.SECONDS.sleep(5);
		driver.switchTo().alert().sendKeys("yes");
		driver.switchTo().alert().accept();
		TimeUnit.SECONDS.sleep(5);
		driver.quit();
	}
	

}

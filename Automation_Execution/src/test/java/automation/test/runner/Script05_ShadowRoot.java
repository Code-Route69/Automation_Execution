package automation.test.runner;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Script05_ShadowRoot {
	WebDriver driver;
	JavascriptExecutor java;
	@BeforeMethod
	public void openBrowser() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		java = (JavascriptExecutor) driver;
	}
	//Open Shadow Root
	@Test(priority = 1)
	public void ShadowRoot() throws InterruptedException {
		driver.get("https://demoapps.qspiders.com/ui?scenario=1");
		driver.findElement(By.xpath("//section[normalize-space()='Shadow Root Elements']")).click();
		driver.findElement(By.xpath("//section[@class='poppins text-[14px]']")).click();
		Thread.sleep(2000);
		SearchContext shadowdiv1 = driver.findElement(By.xpath("//form/div[1]")).getShadowRoot();
		shadowdiv1.findElement(By.cssSelector("input[type='text']")).sendKeys("Ravi Shankar");
		SearchContext shadowdiv2 = driver.findElement(By.xpath("//form/div[2]")).getShadowRoot();
		shadowdiv2.findElement(By.cssSelector("input[type='text']")).sendKeys("ravi1234");
	}
	//Closed Shadow Root
	@Test(priority = 2)
	public void shadowRootClose() {
		driver.get("https://demoapps.qspiders.com/ui?scenario=1");
		driver.findElement(By.xpath("//section[normalize-space()='Shadow Root Elements']")).click();
		driver.findElement(By.xpath("//section[@class='poppins text-[14px]']")).click();
		driver.findElement(By.xpath("//a[text()='Close Shadow Root']")).click();
		Actions act = new Actions(driver);
		driver.findElement(By.xpath("//h1"));
		act.sendKeys(Keys.TAB).perform();
		act.sendKeys("Ravi Shankar").perform();
		act.sendKeys(Keys.TAB).perform();
		act.sendKeys("ravi1234").perform();
	}
	//Nested Shadow Root
	@Test(priority = 3)
	public void shadowRootNested() throws InterruptedException {
		driver.get("https://demoapps.qspiders.com/ui?scenario=1");
		driver.findElement(By.xpath("//section[normalize-space()='Shadow Root Elements']")).click();
		driver.findElement(By.xpath("//section[@class='poppins text-[14px]']")).click();
		driver.findElement(By.xpath("//a[text()='Nested Shadow Root']")).click();
		Actions act = new Actions(driver);
		driver.findElement(By.xpath("//h1"));
		act.sendKeys(Keys.TAB).perform();
		act.sendKeys("Ravi Shankar").perform();
		act.sendKeys(Keys.TAB).perform();
		act.sendKeys("ravi1234").perform();

	}
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}

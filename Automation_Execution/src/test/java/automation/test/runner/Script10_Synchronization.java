package automation.test.runner;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Script10_Synchronization {
	WebDriver driver;
	WebDriverWait wait;
	TakesScreenshot take;
	JavascriptExecutor java;
	File temp;
	File perm;

	@BeforeMethod
	public void launchBrowser() {
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--headless");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://demoapps.qspiders.com/ui?scenario=1");
		Reporter.log("Browser Launched", true);
		take = (TakesScreenshot) driver;
		java = (JavascriptExecutor) driver;
	}

	@Test(priority = 1)
	public void defaultsync() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[normalize-space()='Synchronization']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[normalize-space()='Progress Bar']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Start']"))).click();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//button[normalize-space()='Start']"),
				"Start"));
		driver.findElement(By.xpath("//button[normalize-space()='Reset']")).click();
		Reporter.log("Default Synchroniztion Pass", true);
	}

	@Test(priority = 2)
	public void withTimer() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[normalize-space()='Synchronization']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[normalize-space()='Progress Bar']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[.='With Timer']"))).click();
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Enter time in seconds']")))
				.sendKeys("5");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Start']"))).click();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//button[normalize-space()='Start']"),
				"Start"));
		driver.findElement(By.xpath("//button[normalize-space()='Reset']")).click();
		Reporter.log("With Timer Synchroniztion Pass", true);
	}

	@Test(priority = 3)
	public void withElement() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[normalize-space()='Synchronization']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[normalize-space()='Progress Bar']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[.='With Element']"))).click();
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Enter time in seconds']")))
				.sendKeys("5");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Start']"))).click();
		Select drop = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//select)[2]"))));
		drop.selectByContainsVisibleText("Yes");
		Reporter.log("With Element Synchroniztion Pass", true);
	}

	@Test(priority = 4)
	public void withElementDisappear() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[normalize-space()='Synchronization']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[normalize-space()='Progress Bar']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[.='With Element Disappear']"))).click();
		java.executeScript("arguments[0].removeAttribute('disabled');",
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//select)[2]"))));
		Select drop = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//select)[2]"))));
		drop.selectByContainsVisibleText("Yes");
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Enter time in seconds']")))
				.sendKeys("5");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Start']"))).click();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//button[normalize-space()='Start']"),
				"Start"));
		driver.findElement(By.xpath("//button[normalize-space()='Reset']")).click();
		Reporter.log("With Element Disappear Synchroniztion Pass", true);
	}

	@Test(priority = 5)
	public void disabledElement() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[normalize-space()='Synchronization']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[normalize-space()='Progress Bar']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[.='Disabled Element']"))).click();
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Enter time in seconds']")))
				.sendKeys("5");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Start']"))).click();
		java.executeScript("arguments[0].removeAttribute('disabled');",
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//select)[2]"))));
		Select drop = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//select)[2]"))));
		drop.selectByContainsVisibleText("Yes");
		Reporter.log("Disabled Element Synchroniztion Pass", true);
	}

	@Test(priority = 6)
	public void hiddenElement() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[normalize-space()='Synchronization']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[normalize-space()='Progress Bar']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[.='Hidden Element']"))).click();
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Enter time in seconds']")))
				.sendKeys("5");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Start']"))).click();
		java.executeScript("arguments[0].style.display='block';", wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//section[@class='py-3 border-t-[1px]']"))));
		java.executeScript("arguments[0].removeAttribute('disabled');",
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//select)[2]"))));
		Select drop = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//select)[2]"))));
		drop.selectByContainsVisibleText("Yes");
		Reporter.log("Hidden Element Synchroniztion Pass", true);
	}

	@Test(priority = 7)
	public void pageLoad() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[normalize-space()='Synchronization']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[normalize-space()='Page Loading']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[.='Open In New Tab']"))).click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.switchTo().window((String) (driver.getWindowHandles().toArray()[1]));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys("I am Ravi");
		Reporter.log("Page Load Synchroniztion Pass", true);
		Thread.sleep(1000);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
		Reporter.log("Browser Exit", true);
	}

}

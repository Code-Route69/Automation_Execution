package automation.test.runner;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Script07_iFrames {
	WebDriver driver;
	WebDriverWait wait;
	TakesScreenshot take;
	File temp;
	File perm;
	@BeforeMethod
	public void openBrowser() {
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://demoapps.qspiders.com/ui?scenario=1");
		Reporter.log("Browser Launched", true);
		take = (TakesScreenshot) driver;
	}

	@Test(priority = 1)
	public void defaultFrame() throws IOException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[normalize-space()='Frames']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@class='poppins text-[14px]']"))).click();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		driver.findElement(By.id("username")).sendKeys("Ravi Shankar");
		driver.findElement(By.id("password")).sendKeys("Ravi Shankar");
		driver.findElement(By.id("submitButton")).click();
		temp = take.getScreenshotAs(OutputType.FILE);
		perm = new File("./src/test/resources/ScreenShots/defaultFrame.png");
		FileHandler.copy(temp, perm);
		Reporter.log("Default Frame Handling Execution Completed", true);
	}

	@Test(priority = 2)
	public void nested() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[normalize-space()='Frames']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@class='poppins text-[14px]']"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Nested iframe']"))).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		Thread.sleep(2000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));		
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='email']"))).sendKeys("Admin@gmail.com");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='password']"))).sendKeys("Admin@1234");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='confirm-password']"))).sendKeys("Admin@1234");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='submitButton']"))).click();
		Thread.sleep(2000);
		temp = take.getScreenshotAs(OutputType.FILE);
		perm = new File("./src/test/resources/ScreenShots/nestedFrame.png");
		FileHandler.copy(temp, perm);
		Reporter.log("Nested Frame Handling Execution Completed", true);
	}
	@Test(priority = 3)
	public void multiple() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[normalize-space()='Frames']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@class='poppins text-[14px]']"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Multiple iframe']"))).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='email']"))).sendKeys("Admin@gmail.com");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='password']"))).sendKeys("Admin@1234");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='confirm-password']"))).sendKeys("Admin@1234");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='submitButton']"))).click();
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		Thread.sleep(2000);		
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='username']"))).sendKeys("Admin@gmail.com");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='password']"))).sendKeys("Admin@1234");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='submitButton']"))).click();
		Thread.sleep(2000);
		temp = take.getScreenshotAs(OutputType.FILE);
		perm = new File("./src/test/resources/ScreenShots/multipleFrame.png");
		FileHandler.copy(temp, perm);
		Reporter.log("Multiple Frame Handling Execution Completed", true);
	}
	@Test(priority = 4)
	public void nestedMultiple() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[normalize-space()='Frames']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@class='poppins text-[14px]']"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Nested with Multiple iframe']"))).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));	
		Thread.sleep(2000);	
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));	
		Thread.sleep(2000);
		Actions act = new Actions(driver);
		String[] xpath = {"//input[@id='email']","//input[@id='password']","//input[@id='confirm']"};
		String[] input = {"Admin@gmail.com","Admin@1234","Admin@1234"};
		for (int i = 0; i < xpath.length; i++) {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(i));	
			Thread.sleep(2000);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath[i]))).sendKeys(input[i]);
			Thread.sleep(2000);
			act.sendKeys(Keys.TAB).perform();
			driver.switchTo().parentFrame();
			Thread.sleep(2000);
		}
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(3));	
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='submitButton']"))).click();
		Thread.sleep(2000);
		temp = take.getScreenshotAs(OutputType.FILE);
		perm = new File("./src/test/resources/ScreenShots/nestedMultipleFrame.png");
		FileHandler.copy(temp, perm);
		Reporter.log("Multiple + Nested Frame Handling Execution Completed", true);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
		Reporter.log("Browser Exit", true);
	}

}

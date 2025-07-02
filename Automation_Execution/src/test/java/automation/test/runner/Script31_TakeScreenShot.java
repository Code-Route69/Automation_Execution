package automation.test.runner;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import automation.main.webdriver.util.FolderPaths;

public class Script31_TakeScreenShot {
	
	public static String webelementScreenShot(String url, String xpath) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		File screenShot = driver.findElement(By.xpath(xpath)).getScreenshotAs(OutputType.FILE);
		String elementName = driver.findElement(By.xpath(xpath)).getText();
		File storage = new File(FolderPaths.SCREENSHOT_PATH+elementName+".png");
		try {
			FileHandler.copy(screenShot, storage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver.quit();
		return "Screen Shot Taken of "+elementName;
	}

	public static String fullScreenShot(String url) {
		WebDriver driver = new ChromeDriver();
		TakesScreenshot take = (TakesScreenshot) driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		File screenShot = take.getScreenshotAs(OutputType.FILE);
		String screenName = "ScreenShot";
		File storage = new File(FolderPaths.SCREENSHOT_PATH + screenName + ".png");
		try {
			FileHandler.copy(screenShot, storage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver.quit();
		return "Screen Shot Taken of " + screenName;
	}	
	
	public static void main(String[] args) {
		System.out.println(webelementScreenShot("https://www.amazon.in", "//a[@id='nav-logo-sprites']"));		
		System.out.println(fullScreenShot("https://www.amazon.in"));		
	}
}

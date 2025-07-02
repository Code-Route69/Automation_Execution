package automation.test.runner;

import java.net.HttpURLConnection;
import java.net.URI;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Script29_BrokenImageTest {
	WebDriver driver;
	@BeforeMethod
	public void launchBrowser() {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		driver = new ChromeDriver(option);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@Test
	public void sbiBankOnline() {
		driver.get("https://www.onlinesbi.sbi/");
		List<WebElement> images = driver.findElements(By.xpath("//img"));
		for (WebElement image : images) {
			String src = image.getAttribute("src");
			if (src == null || src.isEmpty()) {
				System.out.println("Image src is Empty");
				continue;
			}
			
			try {
				HttpURLConnection connect = (HttpURLConnection) (URI.create(src).toURL().openConnection());
				connect.setRequestMethod("GET");
				connect.connect();
				if (connect.getResponseCode()>=400) {
					System.out.println(src + "is a Broken Image, Code : "+connect.getResponseCode());
				} else {
					System.out.println(src + "is a Valid Image, Code : "+connect.getResponseCode());
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} 
		}
	}

	@Test
	public void EPFOOnline() {
		driver.get("https://www.epfindia.gov.in/site_en/index.php");
		List<WebElement> images = driver.findElements(By.xpath("//img"));
		for (WebElement image : images) {
			String src = image.getAttribute("src");
			if (src == null || src.isEmpty()) {
				System.out.println("Image Src is Empty");
				continue;
			}

			try {
				HttpURLConnection connect = (HttpURLConnection) (URI.create(src).toURL().openConnection());
				connect.setRequestMethod("GET");
				connect.connect();
				if (connect.getResponseCode() >= 400) {
					System.out.println(src + "is a Broken Image, Code : " + connect.getResponseCode());
				} else {
					System.out.println(src + "is a Valid Image, Code : " + connect.getResponseCode());
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Test
	public void IRCTCOnline() {
		driver.get("https://www.irctc.co.in/nget/train-search");
		List<WebElement> images = driver.findElements(By.xpath("//img"));
		for (WebElement image : images) {
			String src = image.getAttribute("src");
			if (src == null || src.isEmpty()) {
				System.out.println("Image src is Empty");
				continue;
			}

			try {
				HttpURLConnection connect = (HttpURLConnection) (URI.create(src).toURL().openConnection());
				connect.setRequestMethod("GET");
				connect.connect();
				if (connect.getResponseCode() >= 400) {
					System.out.println(src + "is a Broken Image, Code : " + connect.getResponseCode());
				} else {
					System.out.println(src + "is a Valid Image, Code : " + connect.getResponseCode());
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}

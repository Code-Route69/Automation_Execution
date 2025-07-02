package automation.test.runner;

import java.net.HttpURLConnection;
import java.net.URI;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Script28_BrokenLinkTest {
	WebDriver driver;
	@BeforeMethod
	public void launchBrowser() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}
	
	@Test
	public void sbiBankOnline() {
		driver.get("https://www.onlinesbi.sbi/");
		List<WebElement> links = driver.findElements(By.xpath("//a"));
		for (WebElement link : links) {
			String url = link.getAttribute("href");
			if (url == null || url.isEmpty()) {
				System.out.println("URL is Empty");
				continue;
			}
			
			try {
				HttpURLConnection connect = (HttpURLConnection) (URI.create(url).toURL().openConnection());
				connect.setRequestMethod("HEAD");
				connect.connect();
				if (connect.getResponseCode()>=400) {
					System.out.println(url + "is a Broken link, Code : "+connect.getResponseCode());
				} else {
					System.out.println(url + "is a Valid link, Code : "+connect.getResponseCode());
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} 
		}
	}

	@Test
	public void EPFOBankOnline() {
		driver.get("https://www.epfindia.gov.in/site_en/index.php");
		List<WebElement> links = driver.findElements(By.xpath("//a"));
		for (WebElement link : links) {
			String url = link.getAttribute("href");
			if (url == null || url.isEmpty()) {
				System.out.println("URL is Empty");
				continue;
			}

			try {
				HttpURLConnection connect = (HttpURLConnection) (URI.create(url).toURL().openConnection());
				connect.setRequestMethod("HEAD");
				connect.connect();
				if (connect.getResponseCode() >= 400) {
					System.out.println(url + "is a Broken link, Code : " + connect.getResponseCode());
				} else {
					System.out.println(url + "is a Valid link, Code : " + connect.getResponseCode());
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Test
	public void IRCTCBankOnline() {
		driver.get("https://www.irctc.co.in/nget/train-search");
		List<WebElement> links = driver.findElements(By.xpath("//a"));
		for (WebElement link : links) {
			String url = link.getAttribute("href");
			if (url == null || url.isEmpty()) {
				System.out.println("URL is Empty");
				continue;
			}

			try {
				HttpURLConnection connect = (HttpURLConnection) (URI.create(url).toURL().openConnection());
				connect.setRequestMethod("HEAD");
				connect.connect();
				if (connect.getResponseCode() >= 400) {
					System.out.println(url + "is a Broken link, Code : " + connect.getResponseCode());
				} else {
					System.out.println(url + "is a Valid link, Code : " + connect.getResponseCode());
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

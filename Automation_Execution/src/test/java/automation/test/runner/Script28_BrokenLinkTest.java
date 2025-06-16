package automation.test.runner;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Script28_BrokenLinkTest {
	
	@Test
	public void sbiBankOnline() {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.onlinesbi.sbi/");
		List<WebElement> links = driver.findElements(By.xpath("//a"));
		for (WebElement link : links) {
			String url = link.getAttribute("href");
			if (url == null || url.isEmpty()) {
				System.out.println("URL is Empty");
				continue;
			}
			
			try {
				HttpURLConnection connect = (HttpURLConnection) (new URL(url).openConnection());
				connect.setRequestMethod("HEAD");
				connect.connect();
				if (connect.getResponseCode()>=400) {
					System.out.println(url + "is a Broken link, Code : "+connect.getResponseCode());
				} else {
					System.out.println(url + "is a Valid link, Code : "+connect.getResponseCode());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		driver.quit();
	}

	@Test
	public void EPFOBankOnline() {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.epfindia.gov.in/site_en/index.php");
		List<WebElement> links = driver.findElements(By.xpath("//a"));
		for (WebElement link : links) {
			String url = link.getAttribute("href");
			if (url == null || url.isEmpty()) {
				System.out.println("URL is Empty");
				continue;
			}

			try {
				HttpURLConnection connect = (HttpURLConnection) (new URL(url).openConnection());
				connect.setRequestMethod("HEAD");
				connect.connect();
				if (connect.getResponseCode() >= 400) {
					System.out.println(url + "is a Broken link, Code : " + connect.getResponseCode());
				} else {
					System.out.println(url + "is a Valid link, Code : " + connect.getResponseCode());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		driver.quit();
	}

	@Test
	public void IRCTCBankOnline() {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.irctc.co.in/nget/train-search");
		List<WebElement> links = driver.findElements(By.xpath("//a"));
		for (WebElement link : links) {
			String url = link.getAttribute("href");
			if (url == null || url.isEmpty()) {
				System.out.println("URL is Empty");
				continue;
			}

			try {
				HttpURLConnection connect = (HttpURLConnection) (new URL(url).openConnection());
				connect.setRequestMethod("HEAD");
				connect.connect();
				if (connect.getResponseCode() >= 400) {
					System.out.println(url + "is a Broken link, Code : " + connect.getResponseCode());
				} else {
					System.out.println(url + "is a Valid link, Code : " + connect.getResponseCode());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		driver.quit();
	}

}

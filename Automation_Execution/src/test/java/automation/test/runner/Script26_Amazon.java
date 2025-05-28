package automation.test.runner;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class Script26_Amazon {
	WebDriver driver;
	@Test
	public void TestEndToEnd() {
		driver = new FirefoxDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String input = "The Prince";
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(input+Keys.ENTER);
		driver.findElement(By.xpath("//a[@class='a-link-normal s-line-clamp-2 s-link-style a-text-normal']")).click();
		Set<String> handles = driver.getWindowHandles();
		for (String string : handles) {
			if (driver.switchTo().window(string).getTitle().contains("Shopping Cart")) {
				break;
			}
		}
		driver.findElement(By.id("add-to-cart-button")).click();
		driver.findElement(By.xpath("//span[@class='nav-cart-icon nav-sprite']")).click();
		String text = driver.findElement(By.xpath("//span[@class='a-truncate-cut']")).getText();
		System.out.println(text);
		Assert.assertTrue(text.trim().contains(input), "Product added to cart");
	}
	
	@AfterMethod(alwaysRun = true)
	public void close() {
		driver.quit();
	}

}

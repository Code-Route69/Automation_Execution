package automation.test.runner;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Script02_FlipKartDropDown {
	@Test
	public void fetchDropDown() throws InterruptedException {
		
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--headless");
		WebDriver driver = new ChromeDriver(opt);
		driver.get("https://www.flipkart.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions act = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//span[.='Fashion']"));
		act.moveToElement(element).perform();
		int num = 11;
		List<String> categories = new ArrayList<String>();

		for (int i = 1; i <= num; i++) {
			String xp = "(//object/a)["+i+"]";
			WebElement option = driver.findElement(By.xpath(xp));
			act.moveToElement(option).perform();
			categories.add(option.getText());
			List<WebElement> sub = driver.findElements(By.xpath("(//object)[2]/a"));
			for (WebElement a : sub) {
				categories.add("\t"+a.getText());
			}
		}
		for (String string : categories) {
			Reporter.log(string, true);
		}
		
		driver.quit();
	}

}

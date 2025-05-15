package automation.test.runner;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Script06_MultipleWindow {
	@Test
	public void handleWindow() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://demowebshop.tricentis.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions act = new Actions(driver);
		act.scrollToElement(driver.findElement(By.xpath("//a[.='Facebook']")));
		driver.findElement(By.xpath("//a[.='Facebook']")).click();
		Set<String> browers = driver.getWindowHandles();
		List<String> switchs = new ArrayList<String>();
		for (String i : browers) {
			switchs.add(i);
		}
		driver.switchTo().window(switchs.get(1));
		driver.switchTo();
		Thread.sleep(5000);
		driver.findElement(By.name("email")).sendKeys("Ravi Shankar");
		driver.findElement(By.name("pass")).sendKeys("ravi1234");
		driver.close();
		Thread.sleep(5000);
		driver.switchTo().window(switchs.get(0));
		act.scrollToElement(driver.findElement(By.xpath("//input[@value='Search store']")));
		driver.findElement(By.xpath("//input[@value='Search store']")).sendKeys("Mobiles");
		driver.quit();
	}
}

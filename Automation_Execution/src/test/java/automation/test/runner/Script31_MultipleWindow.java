package automation.test.runner;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Script31_MultipleWindow {
	
	@Test
	public void multiWindow() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in");
		String parentTitle = driver.getTitle();
		driver.switchTo().newWindow(WindowType.TAB).get("https://www.flipkart.com");
		driver.switchTo().newWindow(WindowType.TAB).get("https://www.instagram.com");
		Set<String> tabs = driver.getWindowHandles();
		for (String tab : tabs) {
			if (!driver.switchTo().window(tab).getTitle().contains(parentTitle)) {
				TimeUnit.SECONDS.sleep(2);
				driver.close();
			}
		}
		TimeUnit.SECONDS.sleep(2);
		driver.quit();
	}

}

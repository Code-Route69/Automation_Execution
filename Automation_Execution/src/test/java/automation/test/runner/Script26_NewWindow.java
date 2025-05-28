package automation.test.runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Script26_NewWindow {
	
	@Test
	public void newBrowser() {
		WebDriver driver = new ChromeDriver();
		driver.switchTo().newWindow(WindowType.TAB);
		driver.quit();
	}

}

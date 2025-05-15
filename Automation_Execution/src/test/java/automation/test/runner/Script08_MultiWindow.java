package automation.test.runner;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;

public class Script08_MultiWindow {
	@Test
	public void multi() throws AWTException {
		WebDriver driver = new ChromeDriver();
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);

		driver.get("https://www.selenium.dev");

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[.='Downloads']"))).click();
		JavascriptExecutor java = (JavascriptExecutor) driver;
		java.executeScript("window.scrollBy(0,500)");
		Actions mouse = new Actions(driver);
		Robot robo = new Robot();
		WebElement net = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[.='Changelog'])[1]/../..//a[1]")));
		WebElement ruby = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[.='Changelog'])[2]/../..//a[1]")));
		WebElement python = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[.='Changelog'])[4]/../..//a[1]")));
		WebElement javascript = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[.='Changelog'])[5]/../..//a[1]")));
		WebElement[] names = { net, ruby, python, javascript };
		for (int i = 0; i < names.length; i++) {

			mouse.contextClick(names[i]).perform();
			robo.delay(500);
			robo.keyPress(KeyEvent.VK_DOWN);
			robo.keyRelease(KeyEvent.VK_DOWN);
			robo.delay(500);
			robo.keyPress(KeyEvent.VK_ENTER);
			robo.keyRelease(KeyEvent.VK_ENTER);
		}

		Set<String> windows = driver.getWindowHandles();
		for (String string : windows) {
			System.out.println(driver.switchTo().window(string).getTitle());
		}
		List<String> win = new ArrayList<String>();
		for (String string : windows) {
			win.add(string);
		}
		String pythonaddress = win.get(3);
		for (int i = 0; i < win.size(); i++) {
			if (pythonaddress.equals(win.get(i))) {
				continue;
			} else {

				driver.switchTo().window(win.get(i)).close();
			}
		}

		driver.quit();

	}
}

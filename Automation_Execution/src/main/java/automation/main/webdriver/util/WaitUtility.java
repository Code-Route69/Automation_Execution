package automation.main.webdriver.util;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility {
	WebDriver driver;
	WebDriverWait wait;
	FluentWait<WebDriver> fluent;

	public WaitUtility(WebDriver driver, long seconds) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		fluent = new FluentWait<WebDriver>(driver);
	}

	public void setWait(long seconds) {
		fluent.withTimeout(Duration.ofSeconds(seconds));
	}

	public void setPolling(long millisec) {
		fluent.pollingEvery(Duration.ofMillis(millisec));
	}

	public void ignoreException(String Exception) {
		switch (Exception.toLowerCase()) {
		case "nosuchelement":
			fluent.ignoring(NoSuchElementException.class);
			break;
		case "staleelementreference":
			fluent.ignoring(NoSuchElementException.class);
			break;
		default:
			throw new RuntimeException("No Such Exception please check!!!");
		}
	}

	public void implicitlyWait(long seconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}

	public void pageLoadWait(long seconds) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(seconds));
	}

	public void waitUtilVisibility(WebElement element) {
		wait.until(ExpectedConditions.visibilityOfElementLocated((By) element));
	}

	public void waitUtilPresence(WebElement element) {
		wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
	}

	public void waitUtilClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void switchToFrame(WebElement element) {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	}

	public void switchToFrame(int index) {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}

	public void switchToFrame(String name) {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(name));
	}

	public void waitUtilAlertPresent() {
		wait.until(ExpectedConditions.alertIsPresent());
	}

}

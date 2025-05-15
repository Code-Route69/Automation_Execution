package automation.main.webdriver.util;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtilityPage {
	WebDriver driver;
	JavascriptExecutor java;
	String mainWindow;

	public WebDriverUtilityPage(WebDriver driver) {
		this.driver = driver;
		mainWindow = driver.getWindowHandle();
		java = (JavascriptExecutor) driver;
	}

	public void maximize() {
		driver.manage().window().maximize();
	}

	public void minimize() {
		driver.manage().window().minimize();
	}

	public void launchApplication(String url) {
		driver.get(url);
	}
	public String getTitle() {
		return driver.getTitle();
	}

	public void pageLoadWait() {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
	}

	public void implicitlyWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	public void implicitlyWait(long sec) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
	}

	public void elementPresent(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
	}

	public void elementVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf((WebElement) element));
	}

	public void alertPresence() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void switchToTab(String title) {
		Set<String> address = driver.getWindowHandles();
		Iterator<String> it = address.iterator();
		while (it.hasNext()) {
			String type = it.next();
			String window = driver.switchTo().window(type).getTitle();
			if (window.contains(title)) {
				driver.switchTo().window(title);
			}
		}
	}

	public void elementClick(WebElement element) {
		element.click();
	}

	public void elementEnter(WebElement element, String value) {
		element.sendKeys(value);
	}

	public void elementClear(WebElement element) {
		element.clear();
	}

	public String elementText(WebElement element) {
	
		return 	element.getText();

	}

	public void parentWindow() {
		driver.switchTo().window(mainWindow);
	}

	public void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}

	public void switchToFrame(String nameid) {
		driver.switchTo().frame(nameid);
	}

	public void switchToFrame(WebElement webelement) {
		driver.switchTo().frame(webelement);
	}

	public void parentFrame() {
		driver.switchTo().parentFrame();
	}

	public void mainPage() {
		driver.switchTo().defaultContent();
	}

	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}

	public void dismissAlert() {
		driver.switchTo().alert().dismiss();
	}

	public void dropDown(WebElement element, String option) {
		Select select = new Select(element);
		select.selectByContainsVisibleText(option);
	}

	public void dropDown(WebElement element, int option) {
		Select select = new Select(element);
		select.selectByIndex(option);
	}

	public void mouseHover(WebElement element) {
		Actions mouse = new Actions(driver);
		mouse.moveToElement(element).perform();
	}

	public void doubleClick(WebElement element) {
		Actions mouse = new Actions(driver);
		mouse.doubleClick(element).perform();
	}

	public void javaScriptRefresh() {
		java.executeScript("location.reload();");
	}

	public void javaScriptEnable(WebElement element) {
		java.executeScript("arguments[0].disabled = false;", element);
	}

	public void javaScriptdisable(WebElement element) {
		java.executeScript("arguments[0].disabled = true;", element);
	}

	public void javaScriptScroll(WebElement element) {
		java.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void javaScriptScroll(int x, int y) {
		java.executeScript("window.scrollBy(" + x + "," + y + ");");
	}

	public void javaScriptScrollToBottom() {
		java.executeScript("window.scrollBy(0,document.body.scrollHeight);");
	}

	public void javaScriptScrollToTop() {
		java.executeScript("window.scrollBy(0,-document.body.scrollHeight);");
	}

	public void javaScriptEnter(WebElement element, String value) {
		java.executeScript("arguments[0].value='" + value + "';", element);
	}

	public void javaScriptClick(WebElement element, String value) {
		java.executeScript("arguments[0].click();", element);
	}

	public void javaScriptRemoveAtt(WebElement element, String value) {
		java.executeScript("arguments[0].removeAttribute('" + value + "');", element);
	}

	public String javaScriptGetText(WebElement element, String value) {
		return (String) java.executeScript("return arguments[0].innerText;", element);
	}

}

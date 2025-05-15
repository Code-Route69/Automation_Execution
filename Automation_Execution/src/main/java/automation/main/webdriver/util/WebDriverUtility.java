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

public class WebDriverUtility {
	WebDriver driver;
	JavascriptExecutor java;
	String mainWindow;

	public WebDriverUtility(WebDriver driver) {
		this.driver = driver;
		mainWindow = driver.getWindowHandle();
		java = (JavascriptExecutor) driver;
	}

    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param  message the detail message (which is saved for later retrieval
     *         by the {@link #getMessage()} method).
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A {@code null} value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     * @since  1.4
     */
	public void maximize() {
		driver.manage().window().maximize();
	}

	public void minimize() {
		driver.manage().window().minimize();
	}

	public void launchApplication(String url) {
		driver.get(url);
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

	public WebElement elementFetch(String locator, String address) {
		WebElement element = null;
		switch (locator.toLowerCase()) {
		case "id":
			element = driver.findElement(By.id(address));
			break;
		case "name":
			element = driver.findElement(By.name(address));
			break;
		case "classname":
			element = driver.findElement(By.className(address));
			break;
		case "tagname":
			element = driver.findElement(By.tagName(address));
			break;
		case "linktext":
			element = driver.findElement(By.linkText(address));
			break;
		case "partiallinktext":
			element = driver.findElement(By.partialLinkText(address));
			break;
		case "cssselector":
			element = driver.findElement(By.cssSelector(address));
			break;
		case "xpath":
			element = driver.findElement(By.xpath(address));
			break;

		default:
			throw new RuntimeException("Please Check locator and address");
		}
		return element;
	}

	public void elementClick(String locator, String address) {
		switch (locator.toLowerCase()) {
		case "id":
			driver.findElement(By.id(address)).click();
			break;
		case "name":
			driver.findElement(By.name(address)).click();
			break;
		case "classname":
			driver.findElement(By.className(address)).click();
			break;
		case "tagname":
			driver.findElement(By.tagName(address)).click();
			break;
		case "linktext":
			driver.findElement(By.linkText(address)).click();
			break;
		case "partiallinktext":
			driver.findElement(By.partialLinkText(address)).click();
			break;
		case "cssselector":
			driver.findElement(By.cssSelector(address)).click();
			break;
		case "xpath":
			driver.findElement(By.xpath(address)).click();
			break;

		default:
			throw new RuntimeException("Please Check locator and address");
		}
	}

	public void elementEnter(String locator, String address, String value) {
		switch (locator.toLowerCase()) {
		case "id":
			driver.findElement(By.id(address)).sendKeys(value);
			break;
		case "name":
			driver.findElement(By.name(address)).sendKeys(value);
			break;
		case "classname":
			driver.findElement(By.className(address)).sendKeys(value);
			break;
		case "tagname":
			driver.findElement(By.tagName(address)).sendKeys(value);
			break;
		case "linktext":
			driver.findElement(By.linkText(address)).sendKeys(value);
			break;
		case "partiallinktext":
			driver.findElement(By.partialLinkText(address)).sendKeys(value);
			break;
		case "cssselector":
			driver.findElement(By.cssSelector(address)).sendKeys(value);
			break;
		case "xpath":
			driver.findElement(By.xpath(address)).sendKeys(value);
			break;

		default:
			throw new RuntimeException("Please Check locator and address");
		}
	}

	public void elementClear(String locator, String address) {
		switch (locator.toLowerCase()) {
		case "id":
			driver.findElement(By.id(address)).clear();
			break;
		case "name":
			driver.findElement(By.name(address)).clear();
			break;
		case "classname":
			driver.findElement(By.className(address)).clear();
			break;
		case "tagname":
			driver.findElement(By.tagName(address)).clear();
			break;
		case "linktext":
			driver.findElement(By.linkText(address)).clear();
			break;
		case "partiallinktext":
			driver.findElement(By.partialLinkText(address)).clear();
			break;
		case "cssselector":
			driver.findElement(By.cssSelector(address)).clear();
			break;
		case "xpath":
			driver.findElement(By.xpath(address)).clear();
			break;

		default:
			throw new RuntimeException("Please Check locator and address");
		}
	}

	public String elementText(String locator, String address) {
		String text = null;
		switch (locator.toLowerCase()) {
		case "id":
			text = driver.findElement(By.id(address)).getText();
			break;
		case "name":
			text = driver.findElement(By.name(address)).getText();
			break;
		case "classname":
			text = driver.findElement(By.className(address)).getText();
			break;
		case "tagname":
			text = driver.findElement(By.tagName(address)).getText();
			break;
		case "linktext":
			text = driver.findElement(By.linkText(address)).getText();
			break;
		case "partiallinktext":
			text = driver.findElement(By.partialLinkText(address)).getText();
			break;
		case "cssselector":
			text = driver.findElement(By.cssSelector(address)).getText();
			break;
		case "xpath":
			text = driver.findElement(By.xpath(address)).getText();
			break;

		default:
			throw new RuntimeException("Please Check locator and address");
		}
		return text;
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
	public void rightClick(WebElement element) {
		Actions mouse = new Actions(driver);
		mouse.contextClick(element).perform();
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

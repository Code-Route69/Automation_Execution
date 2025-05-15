package automation.main.webdriver.util;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionHandler {

	Actions actions;

	public ActionHandler(WebDriver driver) {
		actions = new Actions(driver);
	}
	
	public void click(WebElement element) {
		actions.moveToElement(element).click();
	}
	public void tab() {
		actions.sendKeys(Keys.TAB);
	}
	public void backSpace() {
		actions.sendKeys(Keys.BACK_SPACE);
	}
	public void delete() {
		actions.sendKeys(Keys.DELETE);
	}
	
	

}

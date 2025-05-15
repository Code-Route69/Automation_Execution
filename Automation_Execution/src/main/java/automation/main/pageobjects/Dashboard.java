package automation.main.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Dashboard {
	@FindBy(xpath = "//button[normalize-space()='Upgrade']") private WebElement upgradeButton;
	@FindBy(xpath = "//span[@class='oxd-userdropdown-tab']") private WebElement userDropDown;
	@FindBy(xpath = "//a[normalize-space()='Logout']") private WebElement logoutButton;

	public Dashboard(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public WebElement upgradeButton() {
		return upgradeButton;
	}
	public WebElement userDropDown() {
		return userDropDown;
	}
	public WebElement LogoutButton() {
		return logoutButton;
	}

}

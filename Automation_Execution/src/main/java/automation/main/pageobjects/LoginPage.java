package automation.main.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	@FindBy(xpath = "//input[@placeholder='Username']") private WebElement usernameField;
	@FindBy(xpath = "//input[@placeholder='Password']") private WebElement passwordField;
	@FindBy(xpath = "//button[normalize-space()='Login']") private WebElement loginButton;

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public WebElement enterUserName() {
		return usernameField;
	}
	public WebElement enterPassword() {
		return passwordField;
	}
	public WebElement LoginButton() {
		return loginButton;
	}

}

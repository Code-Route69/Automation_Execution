package automation.main.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage_NinzaHRM {
	@FindBy(id = "username") private WebElement usernameField;
	@FindBy(id = "inputPassword") private WebElement passwordField;
	@FindBy(xpath = "//button[normalize-space()='Sign in']") private WebElement loginButton;

	public LoginPage_NinzaHRM(WebDriver driver) {
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

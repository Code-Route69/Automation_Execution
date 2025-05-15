package automation.main.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Dashboard_NinzaHRM {
	@FindBy(xpath = "//a[normalize-space()='Projects']") private WebElement projects;
	@FindBy(xpath = "//span[normalize-space()='Create Project']") private WebElement create;
	@FindBy(xpath = "//div[@title='Logout']//*[name()='svg']") private WebElement logoutButton;

	public Dashboard_NinzaHRM(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public WebElement projectsLink() {
		return projects;
	}
	public WebElement createLink() {
		return create;
	}
	public WebElement LogoutButton() {
		return logoutButton;
	}

}

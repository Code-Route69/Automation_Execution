package automation.main.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateProject_NinzaHrm {
	@FindBy(xpath = "//input[@name='projectName']") private WebElement projectsName;
	@FindBy(xpath = "//input[@name='teamSize']") private WebElement teamSize;
	@FindBy(xpath = "//input[@name='createdBy']") private WebElement projectMgr;
	@FindBy(xpath = "//select[@name='status']") private WebElement statusDropDown;
	@FindBy(xpath = "//input[@type='submit']") private WebElement createButton;

	public CreateProject_NinzaHrm(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public WebElement projectNameField() {
		return projectsName;
	}
	public WebElement teamSizeField() {
		return teamSize;
	}
	public WebElement createdByField() {
		return projectMgr;
	}
	public void status(String status) {
		Select select = new Select(statusDropDown);
		select.selectByVisibleText(status);
	}
	public WebElement createButton() {
		return createButton;
	}

}

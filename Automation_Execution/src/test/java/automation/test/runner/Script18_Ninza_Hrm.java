package automation.test.runner;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import automation.main.pageobjects.CreateProject_NinzaHrm;
import automation.main.pageobjects.Dashboard_NinzaHRM;
import automation.test.base.BaseScript_Ninza_Hrm;

public class Script18_Ninza_Hrm extends BaseScript_Ninza_Hrm{
	Dashboard_NinzaHRM dash;
	CreateProject_NinzaHrm create;
	JavascriptExecutor java;
	@Test
	public void createProject() {
		dash = new Dashboard_NinzaHRM(driver);
		create = new CreateProject_NinzaHrm(driver);
		dash.projectsLink().click();
		dash.createLink().click();
		create.projectNameField().sendKeys("RS_Stratos_");
		java = (JavascriptExecutor) driver;
		java.executeScript("arguments[0].value='3'", create.teamSizeField());
		create.createdByField().sendKeys("Ravi Shankar");
		create.status("On Going");
		create.createButton().click();
	}

}

package automation.main.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Dashboard {
	@FindBy(xpath = "//button[normalize-space()='Upgrade']")
	private WebElement upgradeButton;
	@FindBy(xpath = "//span[@class='oxd-userdropdown-tab']")
	private WebElement userDropDown;
	@FindBy(xpath = "//a[normalize-space()='Logout']")
	private WebElement logoutButton;

	private Dashboard() {
		/*
		 * The issue you're referring to is a common concern in Java when using this in
		 * a constructor. In your Dashboard class, the constructor calls
		 * PageFactory.initElements(driver, this);, which passes the this reference (the
		 * current object) to the PageFactory before the constructor has fully
		 * initialized the object.
		 * 
		 * Why is this a potential problem? When you pass this from a constructor, the
		 * object is not fully constructed yet. If the PageFactory or any other code
		 * tries to use the object before all its fields are initialized, it can lead to
		 * unexpected behavior or bugs. This is known as "leaking this."
		 * 
		 * Is it a problem here? In this specific case, it might not cause an issue
		 * because PageFactory.initElements is designed to initialize the @FindBy
		 * annotated fields. However, if PageFactory or any other code tries to use the
		 * object in a way that depends on the fields being fully initialized, it could
		 * lead to problems. 
		 * 
		 * Explanation of the changes: 
		 * 
		 * Factory Method: Added a static
		 * create method to handle the initialization of the PageFactory. This ensures
		 * that the object is fully constructed before being passed to
		 * PageFactory.initElements. 
		 * 
		 * Private Constructor: Made the constructor private
		 * to enforce the use of the factory method. This approach avoids leaking this
		 * and ensures that the object is fully initialized before being used.
		 */
	}

	public static Dashboard create(WebDriver driver) {
		Dashboard dash = new Dashboard();
		PageFactory.initElements(driver, dash);
		return dash;
	}

	/*
	 * public Dashboard(WebDriver driver) { PageFactory.initElements(driver, this);
	 * }
	 */

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

package automation.test.runner;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import net.datafaker.Faker;

public class Script33_AutomationExercise {
	WebDriver driver;
	Faker fake;

	@BeforeMethod
	public void config() {
		WebDriver driver = new ChromeDriver();
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://www.automationexercise.com");
		driver.manage().window().maximize();
		Faker fake = new Faker();
		this.fake = fake;
	}

	@Test
	public void testCase1() {
		assertTrue(driver.findElement(By.xpath("//img[@src='/static/images/home/logo.png']")).isDisplayed(),
				"Home Page Not Visible");
		driver.findElement(By.xpath("//a[text()=' Signup / Login']")).click();
		assertTrue(driver.findElement(By.xpath("//h2[text()='New User Signup!']")).isDisplayed(),
				"SignUp Page Not Visible");
		String name = "ravi" + System.currentTimeMillis();
		String email = name + "@gmail.com";
		WebElement nameInput = driver.findElement(By.name("name"));
		nameInput.sendKeys(name);
		WebElement emailInput = driver.findElement(with(By.name("email")).below(nameInput));
		emailInput.sendKeys(email);
		driver.findElement(with(By.xpath("//button[@type='submit']")).below(emailInput)).click();
		assertEquals(driver.findElement(By.id("name")).getAttribute("value"), name, "Name Does Not Match");
		assertEquals(driver.findElement(By.id("email")).getAttribute("value"), email, "Email Does Not Match");
		driver.findElement(By.id("password")).sendKeys("ravi123");
		driver.findElement(By.name("title")).click();
		WebElement days = driver.findElement(By.xpath("//select[@id='days']"));
		WebElement months = driver.findElement(By.xpath("//select[@id='months']"));
		WebElement years = driver.findElement(By.xpath("//select[@id='years']"));
		WebElement[] dates = { days, months, years };
		for (int i = 0; i < dates.length; i++) {
			Select select = new Select(dates[i]);
			List<WebElement> options = select.getOptions();
			select.selectByIndex((int) Math.floor(Math.random() * options.size()));
		}
		driver.findElement(By.id("newsletter")).click();
		String firstName = fake.name().firstName();
		String lastName = fake.name().lastName();
		String company = fake.company().name();
		String adress1 = fake.address().fullAddress();
		String adress2 = fake.address().fullAddress();
		
		
		
		driver.findElement(By.id("first_name")).sendKeys("");
		driver.findElement(By.id("last_name")).sendKeys("");
		driver.findElement(By.id("company")).sendKeys("");
		driver.findElement(By.id("address1")).sendKeys("");
		driver.findElement(By.id("address2")).sendKeys("");
		driver.findElement(By.id("country")).sendKeys("");
		driver.findElement(By.id("state")).sendKeys("");
		driver.findElement(By.id("city")).sendKeys("");
		driver.findElement(By.id("zipcode")).sendKeys("");
		driver.findElement(By.id("mobile_number")).sendKeys("");
		

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}

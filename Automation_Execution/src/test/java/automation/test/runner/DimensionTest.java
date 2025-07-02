package automation.test.runner;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class DimensionTest {
	
	@Test
	public void setDimension() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		Dimension dimension = new Dimension(500, 500);
		driver.manage().window().setSize(dimension);
		Dimension dim = driver.manage().window().getSize();
		Dimension dimension2 = new Dimension(1000, 1000);
		driver.manage().window().setSize(dimension2);
		System.out.println(dim);
		driver.quit();
	}

}

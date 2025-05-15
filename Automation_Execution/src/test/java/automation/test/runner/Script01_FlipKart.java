package automation.test.runner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Script01_FlipKart {
	@Test
	public void fetchAndWrite() throws IOException {
		
//		ChromeOptions opt = new ChromeOptions();
//		opt.addArguments("--headless");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.flipkart.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//input[@placeholder='Search for Products, Brands and More']")).sendKeys("Water Bottle"+Keys.ENTER);
		List<WebElement> iphone = driver.findElements(By.xpath("//div[@class='KzDlHZ']"));
		List<WebElement> price = driver.findElements(By.xpath("//div[@class='Nx9bqj _4b5DiR']"));
		Map<String, String> product = new HashMap<String, String>();
		for (int i = 0; i < iphone.size(); i++) {
			product.put(iphone.get(i).getText(), price.get(i).getText());
		}
		for (Entry<String, String> i : product.entrySet()) {
			System.out.println("Product Name : "+i.getKey()+"Product price : "+i.getValue());
			Reporter.log(i.getKey()+" "+i.getValue());
		}
		int n = 0;
		XSSFWorkbook book = new XSSFWorkbook();
		XSSFSheet sheet = book.createSheet("FlipKart Product");
		for (Entry<String, String> i : product.entrySet()) {
			XSSFRow row = sheet.createRow(n);
			row.createCell(0).setCellValue(i.getKey());
			row.createCell(1).setCellValue(i.getValue());
			n++;
		}
		FileOutputStream file = new FileOutputStream("./src/test/resources/FlipKart.xlsx");
		book.write(file);
		book.close();
		file.close();
		driver.quit();
	}

}

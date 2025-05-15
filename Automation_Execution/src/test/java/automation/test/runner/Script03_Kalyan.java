package automation.test.runner;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class Script03_Kalyan {
	@Test
	public void fetchText() throws Exception {

	
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--headless");
		WebDriver driver = new ChromeDriver(opt);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.flipkart.com");
		WebElement ele = driver.findElement(By.xpath("//input[@type='text']"));
		ele.sendKeys("iPhone15" + Keys.ENTER);

		List<WebElement> iphone15 = driver.findElements(By.xpath("//div[@class='KzDlHZ']"));

		for (WebElement iphone : iphone15) {
			String count = iphone.getText();
			System.out.println(count);

		}
		List<WebElement> pricetags = driver.findElements(By.xpath("//div[@class='Nx9bqj _4b5DiR']"));
		for (WebElement price : pricetags) {
			String prices = price.getText();
			System.out.println(prices);

		}
		XSSFWorkbook book = new XSSFWorkbook();
		XSSFSheet sheet = book.createSheet("Sheet1");
		int size = iphone15.size();
		for (int i = 0; i < size; i++) {
//			WebElement link = iphone15.get(i);
//			String text = link.getText();
//			WebElement link1 = pricetags.get(i);
//			String text1 = link1.getText();
			XSSFRow row = sheet.createRow(i);
			XSSFCell cel = row.createCell(0);
			XSSFCell cel1 = row.createCell(1);
			cel.setCellValue(iphone15.get(i).getText());
			cel1.setCellValue(pricetags.get(i).getText());

		}
		FileOutputStream fos = new FileOutputStream("./src/test/resources/FlipKart.xlsx");
		book.write(fos);
		book.close();
		fos.close();
		driver.quit();

	}

}

package automation.test.runner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automation.test.base.CloudBaseClass;

public class Script27_ExcelUserCreation extends CloudBaseClass {
	FileInputStream input;
	Workbook book;
	Sheet sheet;

	@DataProvider
	public Object[][] ExcelData() {
		try {
			input = new FileInputStream("./src/test/resources/testdata.excel/CloudTestData.xlsx");
			book = WorkbookFactory.create(input);
			sheet = book.getSheet("Sheet1");
		} catch (IOException | EncryptedDocumentException e) {
			throw new IllegalArgumentException("Path Incorrect");
		}
		Object[][] users = new Object[2][4];
		users[0][0] = sheet.getRow(1).getCell(0).getStringCellValue();
		users[0][1] = sheet.getRow(1).getCell(1).getStringCellValue();
		users[0][2] = String.valueOf(sheet.getRow(1).getCell(2).getNumericCellValue());
		users[0][3] = sheet.getRow(1).getCell(3).getStringCellValue();
		users[1][0] = sheet.getRow(2).getCell(0).getStringCellValue();
		users[1][1] = sheet.getRow(2).getCell(1).getStringCellValue();
		users[1][2] = String.valueOf(sheet.getRow(2).getCell(2).getNumericCellValue());
		users[1][3] = sheet.getRow(2).getCell(3).getStringCellValue();
		return users;
	}

	@Test(dataProvider = "ExcelData")
	public void userCreationTest(String username, String password, String number, String email) {
		int i = 1;
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='addButton']"))).click();
		driver.findElement(By.cssSelector("#addButton")).click();
//		java.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#addButton")));
		driver.findElement(By.xpath("//input[@placeholder='Enter User Name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@placeholder='Enter Mobile Numbr']")).sendKeys(number);
		driver.findElement(By.xpath("//input[@placeholder='Enter email...']")).sendKeys(email);
		driver.findElement(By.xpath("//span[text()='Submit']")).click();
		driver.findElement(By.xpath("//button[text()='Ok, got it!' or text()='OK']")).click();
//		wait.until(ExpectedConditions.alertIsPresent()).dismiss();
		if (wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'" + username + "')]")))
				.isDisplayed()) {
			Assert.assertTrue(true, "Test Pass");
			sheet.getRow(i++).createCell(4).setCellValue("Pass");
		} else {
			Assert.assertTrue(false, "Test fail");
			sheet.getRow(i++).createCell(4).setCellValue("Fail");
		}

		try {
			FileOutputStream out = new FileOutputStream("./src/test/resources/testdata.excel/CloudTestData.xlsx");
			book.write(out);
			book.close();
			out.close();
			input.close();
		} catch (IOException e) {
			throw new IllegalArgumentException("Wrong Path");
		}

		driver.switchTo().defaultContent();
	}

}

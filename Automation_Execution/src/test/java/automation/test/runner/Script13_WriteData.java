package automation.test.runner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class Script13_WriteData {
	//Multiple Data
	@Test
	public void writeMultiple() throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream("./src/test/resources/testData.xlsx");
		Workbook book = WorkbookFactory.create(file);
		Sheet sheet = book.getSheet("Multiple");
		int countRow = 10; int countCel = 10;
		for (int i = 0; i < countRow; i++) {
			Row row = sheet.createRow(i);
			for (int j = 0; j < countCel; j++) {
				Cell cell = row.createCell(j);
				cell.setCellValue("Write Multiple");
			}
		}
		FileOutputStream out = new FileOutputStream("./src/test/resources/testData.xlsx");
		book.write(out);
		book.close();
		file.close();
	}
	//Single Data
	@Test
	public void writeSingle() throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream("./src/test/resources/testData.xlsx");
		Workbook book = WorkbookFactory.create(file);
		book.getSheet("Single").createRow(0).createCell(0).setCellValue("Write Single");
		book.getSheet("Single").createRow(1).createCell(0).setCellValue("Write Single");
		book.getSheet("Single").createRow(2).createCell(0).setCellValue("Write Single");
		book.getSheet("Single").createRow(3).createCell(0).setCellValue("Write Single");
		book.getSheet("Single").createRow(4).createCell(0).setCellValue("Write Single");
		book.getSheet("Single").createRow(5).createCell(0).setCellValue("Write Single");
		book.getSheet("Single").createRow(6).createCell(0).setCellValue("Write Single");
		book.getSheet("Single").createRow(7).createCell(0).setCellValue("Write Single");
		book.getSheet("Single").createRow(8).createCell(0).setCellValue("Write Single");
		book.getSheet("Single").createRow(9).createCell(0).setCellValue("Write Single");


		FileOutputStream out = new FileOutputStream("./src/test/resources/testData.xlsx");
		book.write(out);
		book.close();
		file.close();
	}

}

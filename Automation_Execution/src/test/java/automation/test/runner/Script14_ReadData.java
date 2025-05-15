package automation.test.runner;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class Script14_ReadData {
		// Multiple Data
		@Test
		public void readMultiple() throws EncryptedDocumentException, IOException {
			FileInputStream file = new FileInputStream("./src/test/resources/testData.xlsx");
			Workbook book = WorkbookFactory.create(file);
			Sheet sheet = book.getSheet("Multiple");
			int countRow = sheet.getLastRowNum();
			System.out.println("Reading Multiple Data from Excel");
			System.out.println("-------------------------------------------------------");
			for (int i = 0; i < countRow; i++) {
				Row row = sheet.getRow(i);
				int countCel = row.getLastCellNum();
				for (int j = 0; j < countCel; j++) {
					String cell = row.getCell(j).toString();
					System.out.print(cell+" ");
				}
				System.out.println();
			}
			System.out.println("-------------------------------------------------------");
			book.close();
			file.close();
		}

		// Single Data
		@Test
		public void readSingle() throws EncryptedDocumentException, IOException {
			FileInputStream file = new FileInputStream("./src/test/resources/testData.xlsx");
			Workbook book = WorkbookFactory.create(file);
			System.out.println("Reading Single Data from Excel");
			System.out.println("-------------------------------------------------------");
			System.out.println(book.getSheet("Single").getRow(0).getCell(0).toString());
			System.out.println(book.getSheet("Single").getRow(1).getCell(0).toString());
			System.out.println(book.getSheet("Single").getRow(2).getCell(0).toString());
			System.out.println(book.getSheet("Single").getRow(3).getCell(0).toString());
			System.out.println(book.getSheet("Single").getRow(4).getCell(0).toString());
			System.out.println(book.getSheet("Single").getRow(5).getCell(0).toString());
			System.out.println(book.getSheet("Single").getRow(6).getCell(0).toString());
			System.out.println(book.getSheet("Single").getRow(7).getCell(0).toString());
			System.out.println(book.getSheet("Single").getRow(8).getCell(0).toString());
			System.out.println(book.getSheet("Single").getRow(9).getCell(0).toString());
			System.out.println("-------------------------------------------------------");


			book.close();
			file.close();
		}
	}

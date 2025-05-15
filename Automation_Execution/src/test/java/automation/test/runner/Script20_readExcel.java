package automation.test.runner;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Script20_readExcel {

	public static void readExcel(String Url, String sheetName) throws EncryptedDocumentException, IOException {
		String[][] values;
		FileInputStream file = new FileInputStream(Url);
		Workbook book = WorkbookFactory.create(file);
		Sheet sheet = book.getSheet(sheetName);
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				values = new String[sheet.getLastRowNum()][row.getLastCellNum()];
				Cell cell = row.getCell(j);
//				try {
				if (cell.getCellType() != null & cell.getCellType() == CellType.STRING) {
					String value = cell.getStringCellValue();
					values[i][j] = value;
				} else if (cell.getCellType() != null & cell.getCellType() == CellType.NUMERIC) {
					long value = (long) cell.getNumericCellValue();
					values[i][j] = String.valueOf(value);
				} else {
					values[i][j] = "NA";
				}
//				} catch (Exception e) {
//					e.printStackTrace();					
//				}
				System.out.print(values[i][j] + "\t");
			}
			System.out.println();
		}
		book.close();
		file.close();
	}

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		readExcel("./src/test/resources/TestScriptData/data.xlsx", "Details");

	}
}

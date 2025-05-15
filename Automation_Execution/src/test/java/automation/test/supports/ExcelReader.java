package automation.test.supports;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {
	Sheet sheet;
	String[][] values;
	public ExcelReader(String url, String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream(url);
		Workbook book = WorkbookFactory.create(file);
		sheet = book.getSheet(sheetName);
	}
	
	public void read() {
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				Cell cell = row.getCell(j);
				values = new String[sheet.getLastRowNum()][row.getLastCellNum()];
				if (cell.getCellType()!=null&cell.getCellType()==CellType.STRING) {
					String value = cell.getStringCellValue();							
					values[i][j] = value;
				} else if (cell.getCellType()!=null&cell.getCellType()==CellType.NUMERIC) {
					long value = (long) cell.getNumericCellValue();							
					values[i][j] = String.valueOf(value);
				}else {
					values[i][j] = "NA";
				}
			}
		}
	}
	
	public String[][] getValue() {
		
		return values;
		
	}

}

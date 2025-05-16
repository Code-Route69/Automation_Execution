package automation.main.file.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {
	FileInputStream input;
	Workbook book;
	Sheet sheet;
	String path;
	FileOutputStream output;

	public ExcelFileUtility(String path, String sheetName) {
		this.path = path;
		try {
			input = new FileInputStream(path);
			book = WorkbookFactory.create(input);
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to initialize ExcelFileUtility for path: " + path, e);
		}
		sheet = book.getSheet(sheetName);
	}

	public String getValue(int rownum, int colnum) {
		Cell cell = sheet.getRow(rownum).getCell(colnum);
		if (cell.getCellType() != null && cell.getCellType() == CellType.STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellType() != null && cell.getCellType() == CellType.FORMULA
				&& cell.getCachedFormulaResultType() == CellType.STRING) {
			return sheet.getRow(rownum).getCell(colnum).getStringCellValue();
		} else if (cell.getCellType() != null && cell.getCellType() == CellType.FORMULA
				&& cell.getCachedFormulaResultType() == CellType.NUMERIC) {
			return String.valueOf((long) sheet.getRow(rownum).getCell(colnum).getNumericCellValue());
		} else if (cell.getCellType() != null && cell.getCellType() == CellType.NUMERIC) {
			return String.valueOf((long) sheet.getRow(rownum).getCell(colnum).getNumericCellValue());
		} else {
			return "NA";
		}
	}

	public int getRowCount() {
		return sheet.getLastRowNum();
	}

	public int getColCount() {
		return sheet.getRow(0).getLastCellNum();
	}

	public void setCellData(String sheetName, int R, int C, String data) throws IOException {
		sheet.getRow(R).createCell(C).setCellValue(data);
		FileOutputStream output = new FileOutputStream(path);
		book.write(output);
	}

	public void close() throws IOException {
		input.close();
		book.close();
	}

	public void outClose() throws IOException {
		output.close();
	}

}

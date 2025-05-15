package automation.test.configuration;

import org.testng.annotations.DataProvider;

import automation.main.file.util.ExcelFileUtility;

public class Configuration {

	@DataProvider
	public Object[][] dataSet1() {
		ExcelFileUtility excel = new ExcelFileUtility("./src/test/resources/testdata.excel/Automation.xlsx", "Sheet1");
		Object[][] data = new Object[excel.getRowCount() + 1][excel.getColCount()];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				data[i][j] = excel.getValue(i, j);
			}
		}
		return data;
	}

	@DataProvider
	public Object[][] dataSet2() {
		ExcelFileUtility excel = new ExcelFileUtility("./src/test/resources/testdata.excel/Automation.xlsx", "Sheet2");
		Object[][] data = new Object[excel.getRowCount()+1][excel.getColCount()];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				data[i][j] = excel.getValue(i, j);
			}
		}
		return data;

	}

}

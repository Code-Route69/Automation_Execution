package automation.test.runner;

import java.sql.SQLException;

import org.testng.annotations.Test;

import automation.test.supports.Create_Table;

public class Script22_OJDBC {
	@Test
	public void ojdbc() throws SQLException {
		Create_Table create = new Create_Table("jdbc:oracle:thin:@//localhost:1521", "ravi", "ravi1234");
	}

}

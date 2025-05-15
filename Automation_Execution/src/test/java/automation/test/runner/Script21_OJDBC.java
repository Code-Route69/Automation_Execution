package automation.test.runner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

public class Script21_OJDBC {
	@Test
	public void ojdbcConnection() throws SQLException {
		try {
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521", "ravi", "ravi1234");
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("select * from tab");
		while (result.next()) {
			System.out.println(result.getString(1));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

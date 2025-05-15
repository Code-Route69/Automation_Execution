package automation.test.supports;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Create_Table {
	Connection connection;
	Statement statement;
	String name;

	public Create_Table(String url, String username, String password) throws SQLException {
		try {
			connection = DriverManager.getConnection(url, username, password);
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int createTable(String name) throws SQLException {
		this.name = name;
		return statement.executeUpdate("create table " + name + "(dummy int)");
	}

	public void createColumn(String columnname, String datatype,int size, String constraint) throws SQLException {
		ResultSet result = statement.executeQuery("show columns from " + name);
		while (result.next()) {
			if (result.getString(1).equals("dummy")) {
				statement.executeUpdate("alter table"+name+" drop column dummy");
			} else {
				break;
			}
		}
		if (datatype.toLowerCase().equals("date")) {
			statement.executeUpdate("alter table add "+columnname+" "+datatype+" "+constraint);			
		}
		statement.executeUpdate("alter table add "+columnname+" "+datatype+"("+size+") "+constraint);
	}
	public void close() throws SQLException {
		connection.close();
	}
}

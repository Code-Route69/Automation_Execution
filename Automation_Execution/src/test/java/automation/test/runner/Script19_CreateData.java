package automation.test.runner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class Script19_CreateData {
	Connection connection;
	Statement statement;
	@BeforeMethod
	public void setUp() throws SQLException {
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			connection = DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/StratosERP","root","root");
			statement = connection.createStatement();	
		} catch (Exception e) {
			e.printStackTrace();
			connection.close();
		}
	}
	@Test
	public void createtable() throws SQLException {
		try { 
			int result = statement.executeUpdate("create table Product_Games (product_id varchar(50) Primary key,\r\n"
					+ "product_name varchar(50) not null,\r\n"
					+ "product_status varchar(20) not null,\r\n"
					+ "deployment_date date not null,\r\n"
					+ "versions numeric not null)");
			if (result!=0) {
				Reporter.log("Table Created", true);
			} else {				
				Reporter.log("Table Not Created", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			connection.close();
		}
	}
	@Test
	public void inserttable() throws SQLException {
		try {
		
				int result = statement.executeUpdate("insert into Product_Games () values()");				
				if (result!=0) {
					Reporter.log("Row inserted", true);
				} else {				
					Reporter.log("Row Not inserted", true);
				}

		} catch (Exception e) {
			e.printStackTrace();
			connection.close();
		}
	}
	@AfterMethod
	public void tearDown() throws SQLException {
		connection.close();
	}

}

package automation.test.runner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class Script17_JDBC {

	@Test(priority = 1)
	public void selectdb() throws SQLException {
		Connection connection = null;
		try {
//			Driver driver = new Driver();
//			DriverManager.registerDriver(driver);
			connection = DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/ninza_hrm", "root", "root");
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from project");
			while (result.next()) {
				System.out.println(result.getString(1) + "\t" + result.getString(2) + "\t" + result.getString(3) + "\t"
						+ result.getString(4) + "\t" + result.getString(5) + "\t" + result.getInt(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	@Test(priority = 2)
	public void updatedb() throws SQLException {
		Connection connection = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			connection = DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/ninza_hrm", "root", "root");
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate(
					"insert into project values('TY_Stratos_2025','Ravi Shankar','17-04-2025','StratosERP','On Going','3')");
			if (result != 0) {
				Reporter.log("*********Insertion Completed*********", true);
			} else {
				Reporter.log("*********Insertion Incomplete*********", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	@Test(priority = 3)
	public void verifyProject() throws SQLException {
		Connection connection = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			connection = DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/ninza_hrm", "root", "root");
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from project");
			boolean flag = false;
			String expectedProject = "StratosERP";
			while (result.next()) {
				String project = result.getString(4);
				if (project.equals(expectedProject)) {
					flag = true;
					Reporter.log(project + " Available", true);
				}
			}
			if (flag == false) {
				Reporter.log(expectedProject + " Not Available", true);
				Assert.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	@Test(priority = 4)
	public void selectSpecific() throws SQLException {
		Connection connection = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			connection = DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/ninza_hrm", "root", "root");
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from project where project_id='TY_Stratos_2025'");
			while (result.next()) {
				System.out.println(result.getString(1) + "\t" + result.getString(2) + "\t" + result.getString(3) + "\t"
						+ result.getString(4) + "\t" + result.getString(5) + "\t" + result.getInt(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

}

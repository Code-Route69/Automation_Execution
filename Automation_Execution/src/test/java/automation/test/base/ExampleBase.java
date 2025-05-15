package automation.test.base;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class ExampleBase {
	
	@BeforeSuite
	public void beforeSuiteMethod() {
		System.out.println("BaseClass : Before Suite");
	}
	@BeforeTest
	public void beforeTestMethod() {
		System.out.println("BaseClass : Before Test");
	}
	@BeforeClass
	public void beforeClassMethod() {
		System.out.println("BaseClass : Before Class");
	}
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("BaseClass : Before Method");
	}
	@AfterMethod
	public void afterMethod() {
		System.out.println("BaseClass : After Method");
	}
	@AfterClass
	public void afterClassMethod() {
		System.out.println("BaseClass : After Class");
	}
	@AfterTest
	public void afterTestMethod() {
		System.out.println("BaseClass : After Test");
	}
	@AfterSuite
	public void afteSuiteMethod() {
		System.out.println("BaseClass : After Suite");
	}

}

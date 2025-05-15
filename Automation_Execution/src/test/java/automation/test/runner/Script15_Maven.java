package automation.test.runner;

import org.testng.annotations.Test;

public class Script15_Maven {
	/*
	 * After Writing the script go to cmd and change directory to project
	 * cd C:\Users\ravis\OneDrive\Documents\Coding\Automation_Project
	 * then given maven command 
	 * mvn -Dtest=testscriptname test -DUrl=https://example.com -DUserName=admin -Dpassword=admin123
	 * in test script recieve the data by System.getProperty("Key");
	 * mvn -Dtest=testscriptname#testMaven1+testMaven2 test -DUrl=https://example.com -DUserName=admin -Dpassword=admin123
	 */
	@Test
	public void testMaven1() {
		System.out.println(System.getProperty("URL"));
	}
	@Test
	public void testMaven2() {
		System.out.println(System.getProperty("USN"));
	}

}

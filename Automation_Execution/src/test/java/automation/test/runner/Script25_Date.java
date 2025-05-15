package automation.test.runner;

import java.time.LocalDate;

public class Script25_Date {
	
	public static void main(String[] args) {
		LocalDate date = LocalDate.now();
		System.out.println(date);
		System.out.println(date.plusDays(10));
		System.out.println(date.plusDays(10));
		
	}
	
}

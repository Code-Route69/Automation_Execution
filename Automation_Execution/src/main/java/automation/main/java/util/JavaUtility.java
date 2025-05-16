package automation.main.java.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
/**
 * This class is used to get the current system date and time
 * 
 * @author Ravi Shankar
 *
 */	
public class JavaUtility {
	LocalDate date;
	LocalTime time;
	Random random;
	/**
	 * This method is used to get the current system date and time
	 */
	public JavaUtility() {
		date = LocalDate.now();
		time = LocalTime.now();
	}
	/**
	 * This method is used to get the current system date
	 * 
	 * @return
	 */

	public int getRandomNumber(int lower, int upper) {
		random = new Random();
		return random.nextInt(lower, upper);
	}
	/**
	 * This method is used to get the current system date
	 * 
	 * @return
	 */

	public long getRandomNumber(int digit) {
		long lower = 0;
		long upper = 0;
		random = new Random();
		switch (digit) {
		case 1 -> {	lower = 0;upper = 9;}
		case 2 -> {	lower = 10;upper = 99;}
		case 3 -> {	lower = 100;upper = 999;}
		case 4 -> {	lower = 1000;upper = 9999;}
		case 5 -> {	lower = 10000;upper = 99999;}
		case 6 -> {	lower = 100000;upper = 999999;}
		case 7 -> {	lower = 1000000;upper = 9999999;}
		case 8 -> {	lower = 10000000;upper = 99999999;}
		case 9 -> {	lower = 100000000;upper = 999999999;}
		case 10 -> {lower = 1000000000;upper = 9999999999l;}
		default -> throw new IllegalArgumentException("Please Enter Valid Digit bwteen 1 and 10");
		}
		return lower + random.nextInt((int)(upper - lower + 1));
	}

//	public int autoIncrement(int num) {
//		
//		return num++;
//	}
	/**
	 * This method is used to get the current system date
	 * 
	 * @return
	 */

	public String sysDate() {
		return String.valueOf(date);
	}
	/**
	 * This method is used to get the current system time
	 * 
	 * @return
	 */
	public String sysTime() {
		return String.valueOf(date);
	}
	/**
	 * This method is used to get the current system date
	 * 
	 * @return
	 */
	public String sysDate(String Format) {
		return DateTimeFormatter.ofPattern("dd-MM-YYYY").format(date);
	}
	/**
	 * This method is used to get the current system time
	 * 
	 * @return
	 */
	public String modDate(int day, String format) {
		DateTimeFormatter.ofPattern(format).format(date);
		return date.plusDays(day).toString();
	}

}

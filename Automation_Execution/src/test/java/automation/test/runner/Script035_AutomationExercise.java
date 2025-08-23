package automation.test.runner;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import static org.openqa.selenium.support.locators.RelativeLocator.with;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import automation.main.file.util.ExcelFileUtility;
import automation.main.java.util.JavaUtility;
import automation.main.webdriver.util.Locator;
import automation.main.webdriver.util.WebElementUtil;
import graphql.Assert;

/**
 * 
 * @author Ravisankar
 * @see 
 * 1. CricBuzz - Get the Country and Rating of a Player
 * 2. Olympics - Get the Player Names for a particular Event
 * 3. WorldoMeters - Search for a Country in the Population Table
 * 4. CricBuzz - Get the Player Name and Country for a given Rating
 * 5. WorldoMeters - Get the Undernourished Population for a Country
 * 6. BBC Olympics - Get the Winning Countries for a particular Game
 * 7. BBC Cricket - Get the Rating of a Country in Test Format
 * 8. BBC Cricket - Get the Rating of a Country in T20 Format
 * 9. Olympics 2020 - Get the Country of a Player in a particular Event
 * 10. RedBus - Get the Available Seats and Price for a particular Bus and Store it in Excel
 * 11. Kushals - Print all the Texts in the Page
 * 12. Manapuram - Gold Loan Calculator with DataProvider
 * 13. Manyavar - Social Media Links
 * 14. JosAlukkas - Social Media Links
 * @since 23-07-2025
 * @category Automation Exercise
 * @discription This class contains multiple test scripts that automate various web interactions using Selenium WebDriver.
 *
 */
public class Script035_AutomationExercise {
	WebDriver      driver;
	SoftAssert     soft;
	Actions        action;
	WebDriverWait  wait;
	WebElementUtil util;
	Scanner        scanner;
	Select         select;

	/**
	 * This method sets up the WebDriver and initializes various utilities before the tests are run.
	 * @see ChromeOptions
	 * @see WebElementUtil
	 * @see Actions
	 * @see WebDriverWait
	 * @see SoftAssert
	 * @see Scanner
	 */
	@BeforeTest
	public void setUp( ) {
		driver = new ChromeDriver( new ChromeOptions( )
		            .setExperimentalOption( "prefs",
		                        Map.of( "credentials_enable_service", false, "profile.password_manager_enabled", false, "profile.password_manager_leak_detection",
		                                    false ) )
		            .addArguments( List.of( "--disable-blink-features=AutomationControlled", "--disable-notifications", "--disable-infobars",
		                        "--disable-popup-blocking", "--disable-features=CookiesWithoutSameSiteMustBeSecure", "--disable-cookie-encryption",
		                        "--disable-notifications", "--disable-popup-blocking" ) ) );
		driver.manage( ).timeouts( ).implicitlyWait( Duration.ofSeconds( 10 ) );
		soft    = new SoftAssert( );
		action  = new Actions( driver );
		wait    = new WebDriverWait( driver, Duration.ofSeconds( 10 ) );
		util    = WebElementUtil.init( driver );
		scanner = new Scanner( System.in );
	}

	/**
	* This test navigates to the CricBuzz website, accesses the rankings page, and retrieves the country and rating of a specified player.
	 */
	@Test
	public void cricBuzz1( ) {
		driver.get( "https://www.cricbuzz.com/" );
		util.find( Locator.ID, "rankingDropDown" ).click( );
		soft.assertTrue( driver.getCurrentUrl( ).contains( "icc-rankings" ), "Ranking Page Not Shown" );
		util.find( Locator.XPATH, "//a[text()='ODI']" ).click( );
		String name    = "Rohit Sharma";
		String country = util.find( Locator.XPATH, "//a[text()='" + name + "']/following-sibling::div" ).getText( );
		String rating  = util.find( Locator.XPATH, "//a[text()='" + name + "']/../../following-sibling::div" ).getText( );
		System.out.println( "Name : " + name + " Country : " + country + " Rating : " + rating );

	}

	/**
	 * This test navigates to the Olympics website, accesses the results page, and retrieves player names for a specified event.
	 */
	@Test
	public void olympics( ) {
		driver.get( "https://www.olympics.com/en/olympic-games/tokyo-2020" );
		util.find( Locator.XPATH, "//a[@class='primary' and text()='Results']" ).click( );
		util.find( Locator.XPATH, "//button[text()='Yes, I am happy']" ).click( );
		action.scrollByAmount( 0, 1000 ).perform( );
		util.find( Locator.XPATH, "//p[text()='Boxing']" ).click( );
		util.find( Locator.XPATH, "//span[text()='Event']" ).click( );
		util.finds( Locator.XPATH, "//button[@data-cy='event-button']/p" ).stream( ).map( WebElement::getText ).forEach( System.out::println );
		System.out.println( "Please Enter the Event : " );
		String event = scanner.nextLine( );
		util.find( Locator.XPATH, "//button[@class='sc-6b7599d3-1 kKLdri']/p[contains(text(),'" + event + "')]" ).click( );
		util.find( Locator.XPATH, "//span[text()='Go']" ).click( );
		util.finds( Locator.XPATH, "//h3[@data-cy='athlete-name']" ).stream( ).map( WebElement::getText ).forEach( System.out::println );
		System.out.println( "Please Select the Player Name : " );
		String name = scanner.nextLine( );
		System.out.println( "Player Name : " + name + " Country : "
		            + util.find( Locator.XPATH, "//h3[@data-cy='athlete-name' and text()='" + name + "']/preceding::div[@data-cy='flag-with-label']" ).getText( ) );
	}

	/**
	 * This test navigates to the WorldoMeters website and searches for a specified country in the population table, handling pagination if necessary.
	 */
	@Test
	public void worldoMeters1( ) {
		driver.get( "https://www.worldometers.info/world-population/" );
		action.scrollToElement( driver
		            .findElement( with( By.xpath( "//div[@class='datatable-container']" ) ).below( By.xpath( "//h2[text()='World Population by Country']" ) ) ) )
		            .perform( );
		int     page  = 1;
		boolean found = false;
		System.out.print( "Please Enter The Country Name : " );
		String country = scanner.nextLine( );
		while ( !found && page <= 24 ) {
			List< WebElement > countries = util.finds( Locator.XPATH,
			            "(//table[@class='datatable w-full border border-zinc-200 datatable-table'])[4]/tbody/tr/td[2]" );

			for ( WebElement i : countries ) {
				if ( i.getText( ).equalsIgnoreCase( country ) ) {
					Reporter.log( country + " Found on Page " + page, true );
					found = true;
					break;
				}
			}

			if ( !found ) {
				util.find( Locator.XPATH, "//button[text()='›']" ).click( );
				page++;
			}
		}
		if ( !found ) {
			throw new RuntimeException( "Country Not Found" );
		}
	}

	/**
	 * This test navigates to the CricBuzz website, accesses the rankings page, and retrieves the player name and country for a specified rating.
	 */
	@Test
	public void cricBuzz2( ) {
		driver.get( "https://www.cricbuzz.com/" );
		driver.findElement( By.id( "rankingDropDown" ) ).click( );
		soft.assertTrue( driver.getCurrentUrl( ).contains( "icc-rankings" ), "Ranking Page Not Shown" );
		driver.findElement( By.xpath( "//a[text()='ODI']" ) ).click( );
		String rating  = "756";
		String name    = driver.findElement( By.xpath( "//div[text()='" + rating + "']/../child::div/div/following-sibling::div/a" ) ).getText( );
		String country = driver.findElement( By.xpath( "//div[text()='" + rating + "']/../child::div/div/following-sibling::div/div" ) ).getText( );
		System.out.println( "Name : " + name + " Country : " + country + " Rating : " + rating );

	}

	/**
	 * This test navigates to the WorldoMeters website, selects an option from a dropdown, chooses a country, and retrieves the undernourished population for that country.
	 * It also interacts with a chart to display additional data points.
	 */
	@Test
	public void worldoMeter2( ) throws InterruptedException {
		driver.get( "https://www.worldometers.info/geography/countries-of-the-world/" );
		wait.until( ExpectedConditions.elementToBeClickable( util.find( Locator.ID, "menu-button-5" ) ) ).click( );
		util.finds( Locator.XPATH, "//el-menu[@anchor='bottom end']/ul/li/a" ).stream( ).map( WebElement::getText ).forEach( System.out::println );
		System.out.println( "Please Select the Options : " );
		String option = scanner.nextLine( );
		util.find( Locator.XPATH, "//el-menu[@anchor='bottom end']/ul/li/a[text()='" + option + "']" ).click( );
		util.finds( Locator.XPATH, "//a[@data-country]" ).stream( ).map( WebElement::getText ).forEach( System.out::println );
		System.out.println( "Please Select the Country : " );
		String country = scanner.nextLine( );
		util.find( Locator.XPATH, "//a[@data-country='" + country + "']" ).click( );
		String undernourished = util.find( Locator.XPATH, "//span[@class='text-2xl font-bold']" ).getText( );
		System.out.println( "Undernourished in " + country + " " + undernourished + " people" );
		action.scrollByAmount( 0, 500 );
		util.finds( Locator.CSSSELECTOR, "g[class='highcharts-markers highcharts-series-0 highcharts-spline-series highcharts-tracker']>path[fill='#FF3300']" )
		            .stream( ).forEach( location ->
				{
			            action.moveToElement( location ).click( location ).perform( );
			            JavaUtility.wait( 500 );
			            // CSS
			            System.out.println( wait
			                        .until( ExpectedConditions.visibilityOfElementLocated(
			                                    By.cssSelector( ".highcharts-label.highcharts-tooltip.highcharts-color-undefined > text > tspan" ) ) )
			                        .getText( ) );
			            System.out.println( wait.until( ExpectedConditions
			                        .visibilityOfElementLocated( By.cssSelector( ".highcharts-label.highcharts-tooltip.highcharts-color-undefined > text" ) ) )
			                        .getText( ) );
			            // Xpath
//			            System.out.println( wait.until( ExpectedConditions.visibilityOfElementLocated( By.xpath(
//			                        "//*[name()='svg' and @aria-label='Number of Undernourished in India']/*[name()='g' and @class='highcharts-label highcharts-tooltip highcharts-color-undefined']/*[name()='text']/*[name()='tspan']" ) ) )
//			                        .getText( ) );
//			            System.out.println( wait.until( ExpectedConditions.visibilityOfElementLocated( By.xpath(
//			                        "//*[name()='svg' and @aria-label='Number of Undernourished in India']/*[name()='g' and @class='highcharts-label highcharts-tooltip highcharts-color-undefined']/*[name()='text']" ) ) )
//			                        .getText( ) );
		            } );
	}

	/**
	 * This test navigates to the BBC Olympics website, selects options from dropdowns, and retrieves the winning countries for a specified game
	 */
	@Test
	public void bbcOlympics( ) {
		driver.get( "https://www.bbc.com/sport/olympics/paris-2024/medals" );
		util.find( Locator.XPATH, "//span[text()='Medal results']" ).click( );
		List< WebElement > elements = util.finds( Locator.XPATH, "//select" );
		select = new Select( elements.get( 0 ) );
		List< WebElement > games = select.getOptions( );
		games.stream( ).map( WebElement::getText ).forEach( System.out::println );
		System.out.println( "Please Select the Option from above : " );
		String game = scanner.nextLine( );
		for ( WebElement i : games ) {
			if ( i.getText( ).trim( ).contains( game ) ) {
				select.selectByContainsVisibleText( game );
				break;
			}
		}
		select = new Select( elements.get( 1 ) );
		List< WebElement > gender = select.getOptions( );
		gender.stream( ).map( WebElement::getText ).forEach( System.out::println );
		System.out.println( "Please Select the Option from above : " );
		String gen = scanner.nextLine( );
		for ( WebElement i : games ) {
			if ( i.getText( ).equalsIgnoreCase( gen ) ) {
				select.selectByContainsVisibleText( gen );
				break;
			}
		}
		System.out.println( "Winning Countries : " );
		util.finds( Locator.XPATH, "//div[@class='ssrcss-fy4lnm-PrimaryName e1dg50ic1']" ).stream( ).map( WebElement::getText ).forEach( System.out::println );
	}

	/**
	 * This test navigates to the BBC Cricket website and retrieves the rating of a specified country in Test format.
	 */
	@Test
	public void bbcCricketTest( ) {

		driver.get( "https://www.bcci.tv/international/men/rankings/test" );
		util.finds( Locator.XPATH, "//h6" ).stream( ).map( WebElement::getText ).forEach( System.out::println );
		System.out.println( "Please Select a Country : " );
		String country = scanner.nextLine( );
		System.out.println( "Rating of " + country + " : " + util.find( Locator.XPATH, "//h6[text()='" + country + "']/../../td[6]/p" ).getText( ) );

	}

	/**
	 * This test navigates to the BBC Cricket website and retrieves the rating of a specified country in T20 format.
	 */
	@Test
	public void bbcCricketT20( ) {

		driver.get( "https://www.bcci.tv/international/men/rankings" );
		util.find( Locator.XPATH, "//button[text()='T20I']" ).click( );
		util.finds( Locator.XPATH, "//h6" ).stream( ).map( WebElement::getText ).forEach( System.out::println );
		System.out.println( "Please Select a Country : " );
		String country = scanner.nextLine( );
		System.out.println( "Rating of " + country + " : " + util.find( Locator.XPATH, "//h6[text()='" + country + "']/../../td[6]/p" ).getText( ) );

	}

	/**
	 * This test navigates to the Olympics 2020 website, accesses the results page, and retrieves player names for a specified event.
	 */
	@Test
	public void olympics2020( ) {
		driver.get( "https://www.olympics.com/en/olympic-games/tokyo-2020" );
		util.find( Locator.XPATH, "//a[@class='primary' and text()='Results']" ).click( );
		util.find( Locator.XPATH, "//button[text()='Yes, I am happy']" ).click( );
		action.scrollByAmount( 0, 1000 ).perform( );
		util.find( Locator.XPATH, "//p[text()='Boxing']" ).click( );
		util.find( Locator.XPATH, "//button[@data-cy='game-select']" ).click( );
		util.finds( Locator.XPATH, "//button[@class='sc-6b7599d3-1 kKLdri link-item']/p" ).stream( ).map( WebElement::getText ).forEach( System.out::println );
		System.out.println( "Please Enter the game : " );
		String game = scanner.nextLine( );
		util.find( Locator.XPATH, "//button[@class='sc-6b7599d3-1 kKLdri link-item']/p[text()='" + game + "']" );
		util.find( Locator.XPATH, "//span[text()='Event']" ).click( );
		util.finds( Locator.XPATH, "//button[@data-cy='event-button']/p" ).stream( ).map( WebElement::getText ).forEach( System.out::println );
		System.out.println( "Please Enter the Event : " );
		String event = scanner.nextLine( );
		util.find( Locator.XPATH, "//button[@class='sc-6b7599d3-1 kKLdri']/p[contains(text(),'" + event + "')]" ).click( );
		util.find( Locator.XPATH, "//span[text()='Go']" ).click( );
		util.finds( Locator.XPATH, "//h3[@data-cy='athlete-name']" ).stream( ).map( WebElement::getText ).forEach( System.out::println );
		System.out.println( "Please Select the Player Name : " );
		String name = scanner.nextLine( );
		System.out.println( "Player Name : " + name + " Country : "
		            + util.find( Locator.XPATH, "//h3[@data-cy='athlete-name' and text()='" + name + "']/preceding::div[@data-cy='flag-with-label']" ).getText( ) );
	}

	/**
	 * This test navigates to the RedBus website, searches for buses between two cities on a specified date, retrieves available seats and prices for a selected bus, and stores the information in an Excel file.
	 */
	@Test
	public void redBus( ) {
		driver.get( "https://www.redbus.in/" );
		util.find( Locator.XPATH, "//div[@class='placeHolderContainer___da35ad']/div/div[text()='From']" ).click( );
		String origin = "Bangalore";
		util.find( Locator.XPATH, "//label[text()='From']/preceding-sibling::input" ).sendKeys( origin );
		util.find( Locator.XPATH, "//div[@class='listHeader___19289f' and text()='" + origin + "']" ).click( );
		JavaUtility.wait( 500 );
		String destination = "Chennai";
		util.find( Locator.XPATH, "//label[text()='To']/preceding-sibling::input" ).sendKeys( destination );
		util.find( Locator.XPATH, "//div[@class='listHeader___19289f'and text()='" + destination + "']" ).click( );
		util.find( Locator.XPATH, "//span[@class='doj___db963b']" ).click( );
		String date = "22";
		util.find( Locator.XPATH, "//span[normalize-space()='" + date + "']" ).click( );
		util.find( Locator.XPATH, "//button[normalize-space()='Search buses']" ).click( );
		util.finds( Locator.XPATH, "//div[@class='travelsName___854b5a']" ).stream( ).map( WebElement::getText ).forEach( System.out::println );
		System.out.println( "Please select the Travels : " );
		String travels = scanner.nextLine( );
		util.find( Locator.XPATH, "//div[@class='travelsName___854b5a'][normalize-space()='" + travels + "']" ).click( );
		List< String > price = new ArrayList<>( );
		util.finds( Locator.XPATH, "//span[@class='sleeper__ind-seat-styles-module-scss-Z8-su ']/span" ).stream( ).map( WebElement::getText ).forEach( p -> {
			if ( !p.isEmpty( ) ) {
				price.add( p );
			}
		} );
		List< String > seats = new ArrayList<>( );
		util.finds( Locator.XPATH, "//span[@class='sleeper__ind-seat-styles-module-scss-Z8-su ']" ).stream( ).forEach( s -> seats.add( s.getAttribute( "id" ) ) );
		Map< String, String > avail = IntStream.range( 0, Math.min( seats.size( ), price.size( ) ) ).boxed( ).collect( Collectors.toMap( seats::get, price::get ) );
		avail.entrySet( ).forEach( System.out::println );
		ExcelFileUtility excel = new ExcelFileUtility( "C:\\Users\\ravis\\OneDrive\\Documents\\TekPyramid\\Automation Exercise.xlsx", "Red Bus Seat" );
		int              r     = 1;
		for ( Entry< String, String > i : avail.entrySet( ) ) {
			excel.setCellData( r, 1, i.getKey( ) );
			excel.setCellData( r, 2, i.getValue( ) );
			r++;
		}
		excel.close( );
		excel.outClose( );
	}

	/**
	 * This test navigates to the Kushals website and prints all non-empty text elements on the page.
	 */
	@Test( enabled = false )
	public void kushalsApp( ) {
		driver.get( "https://www.kushals.com/" );
		util.finds( Locator.XPATH, "//*" ).stream( ).map( WebElement::getText ).filter( s -> !s.isEmpty( ) ).forEach( System.out::println );
	}

	/**
	 * This DataProvider reads data from an Excel file and provides it to the manapuram test method.
	 * Each row in the Excel file represents a set of input parameters for the test.	
	 * @return A 2D array of Strings containing the test data.
	 */
	@DataProvider
	public String[ ][ ] manapuramClient( ) {
		ExcelFileUtility excel = new ExcelFileUtility( "C:\\Users\\ravis\\OneDrive\\Documents\\TekPyramid\\Automation Exercise.xlsx", "Manapuram Client" );
		String[ ][ ]     data  = new String[ excel.getRowCount( ) + 1 ][ excel.getColCount( ) ];
		for ( int i = 0; i <= excel.getRowCount( ); i++ ) {
			for ( int j = 0; j < excel.getColCount( ); j++ ) {
				data[ i ][ j ] = excel.getValue( i, j );
			}
		}
		return data;
	}

	/**
	 * This test navigates to the Manapuram website, fills out the Gold Loan Calculator form with data provided by the manapuramClient DataProvider,
	 * and verifies the eligibility for a loan amount based on the input data.
	 * 
	 * @param type     The type of test case (positive/negative).
	 * @param name     The name of the applicant.
	 * @param contact  The contact number of the applicant.
	 * @param email    The email address of the applicant.
	 * @param state    The state where the applicant resides.
	 * @param goldtype The type of gold being used for the loan.
	 * @param city     The city where the applicant resides.
	 * @param gold     The weight of the gold in grams.
	 * @param amount   The amount required for the loan.
	 */
	@Test( dataProvider = "manapuramClient" )
	public void manapuram( String type, String name, String contact, String email, String state, String goldtype, String city, String gold, String amount ) {
		driver.get( "https://www.manappuram.com/gold-loan" );
		action.scrollToElement( util.find( Locator.XPATH, "//a[text()='Gold Loan Calculator']" ) )
		            .click( util.find( Locator.XPATH, "//a[text()='Gold Loan Calculator']" ) ).perform( );
		action.scrollToElement( util.find( Locator.XPATH, "//fieldset" ) ).perform( );
		util.find( Locator.ID, "edit-username" ).sendKeys( name );
		util.find( Locator.ID, "edit-phone-number" ).sendKeys( contact );
		util.find( Locator.ID, "edit-email" ).sendKeys( email );
		util.find( Locator.ID, "edit-city" ).sendKeys( city );
		util.find( Locator.ID, "edit-gold-weight" ).sendKeys( gold );
		util.find( Locator.ID, "edit-amount-required" ).sendKeys( amount );
		util.find( Locator.XPATH, "//span[text()='State']" ).click( );
		util.finds( Locator.XPATH, "//li[@role='option']" ).stream( ).filter( w -> w.getText( ).equalsIgnoreCase( state ) ).findFirst( )
		            .ifPresent( WebElement::click );
		util.find( Locator.XPATH, "//span[text()='Gold Type']" ).click( );
		util.finds( Locator.XPATH, "//li[@role='option']" ).stream( ).filter( w -> w.getText( ).equalsIgnoreCase( goldtype ) ).findFirst( )
		            .ifPresent( WebElement::click );
		if ( type.equals( "positive" ) ) {
			util.find( Locator.XPATH, "//button[text()='Calculate']" ).click( );
			JavaUtility.wait( 500 );
			Assert.assertTrue( util.find( Locator.XPATH, "//div[@role='status']/em" ).isDisplayed( ) );
		} else {
			util.find( Locator.XPATH, "//button[text()='Calculate']" ).click( );
			JavaUtility.wait( 500 );
			Assert.assertFalse( util.find( Locator.XPATH, "//div[@role='status']/em" ).isDisplayed( ) );
		}
		JavaUtility.wait( 1000 );
		System.out.println( "You are eligible for loan amount of Rs: " + util.find( Locator.XPATH, "//div[@role='status']/em" ).getText( ) );
	}

//	@Test
//	public void manyavar( ) {
////		driver.get( "https://www.manyavar.com/" );
////		util.find( Locator.XPATH, "//button[text()='Agree & Close']" ).click( );
////		String main = driver.getWindowHandle( );
////		List.of( util.find( Locator.XPATH, "//span[@class='icon-facebook gl-m-auto gl-fs-30 gl-xs-fs-20']" ),
////		            util.find( Locator.XPATH, "//span[@class='icon-twitter gl-m-auto gl-fs-30 gl-xs-fs-20']" ),
////		            util.find( Locator.XPATH, "//span[@class='icon-instagram gl-m-auto gl-fs-30 gl-xs-fs-20']" ) ).stream( ).map( w ->
////		{
////			            w.click( );
////			            for ( String handle : driver.getWindowHandles( ) ) {
////				            if ( !handle.equals( main ) ) {
////					            driver.switchTo( ).window( handle );
////					            break;
////				            }
////			            }
////			            String currentUrl = driver.getCurrentUrl( );
////			            if ( currentUrl.contains( "facebook" ) ) {
////				            System.out.println( "Facebook validation passed: " + currentUrl );
////			            } else if ( currentUrl.contains( "twitter" ) ) {
////				            System.out.println( "Twitter validation passed: " + currentUrl );
////			            } else if ( currentUrl.contains( "instagram" ) ) {
////				            System.out.println( "Instagram validation passed: " + currentUrl );
////			            } else {
////				            System.out.println( "URL validation failed: " + currentUrl );
////			            }
////			            driver.close( );
////			            driver.switchTo( ).window( main );
////			            return w;
////		            } ).toList( );
//
//	}

	/**
	 * This test navigates to the JosAlukkas website and verifies the functionality of social media links (Facebook, Twitter, Instagram).
	 * It clicks on each social media icon, switches to the new window, validates the URL, and then closes the window.
	 */
	@Test
	public void josaluka( ) {
		driver.get( "https://www.josalukkaseasybuy.com" );
		String main = driver.getWindowHandle( );
		List.of( util.find( Locator.XPATH, "//span[@class='icon-facebook gl-m-auto gl-fs-30 gl-xs-fs-20']" ),
		            util.find( Locator.XPATH, "//span[@class='icon-twitter gl-m-auto gl-fs-30 gl-xs-fs-20']" ),
		            util.find( Locator.XPATH, "//span[@class='icon-instagram gl-m-auto gl-fs-30 gl-xs-fs-20']" ) ).stream( ).map( w ->
		{
			            w.click( );
			            for ( String handle : driver.getWindowHandles( ) ) {
				            if ( !handle.equals( main ) ) {
					            driver.switchTo( ).window( handle );
					            break;
				            }
			            }
			            String currentUrl = driver.getCurrentUrl( );
			            if ( currentUrl.contains( "facebook" ) ) {
				            System.out.println( "Facebook validation passed: " + currentUrl );
			            } else if ( currentUrl.contains( "x" ) ) {
				            System.out.println( "Twitter validation passed: " + currentUrl );
			            } else if ( currentUrl.contains( "instagram" ) ) {
				            System.out.println( "Instagram validation passed: " + currentUrl );
			            } else {
				            System.out.println( "URL validation failed: " + currentUrl );
			            }
			            driver.close( );
			            driver.switchTo( ).window( main );
			            return w;
		            } ).toList( );

	}

	/**
	 * This method closes the Scanner and quits the WebDriver after all tests have been executed.
	 */
	@AfterTest
	public void tearDown( ) {
		scanner.close( );
		driver.quit( );
	}

}

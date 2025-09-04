package automation.test.runner;

import java.time.Duration;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class Script036_Automation {
	WebDriver driver;

	@Test
	public void cricBuzzPlayers( ) {
		driver = new ChromeDriver( );
		driver.get( "https://www.cricbuzz.com/" );
		driver.manage( ).timeouts( ).implicitlyWait( Duration.ofSeconds( 10 ) );
		Actions actions = new Actions( driver );
		actions.moveToElement( driver.findElement( By.xpath( "//a[text()='Rankings']" ) ) )
		            .click( driver.findElement( By.xpath( "//a[text()='ICC Rankings - Men']" ) ) ).perform( );
		Map< String, Collection< String > > player  = new LinkedHashMap<>( );
		Collection< String >                players = driver.findElements( By.xpath( "//a[@class='text-hvr-underline text-bold cb-font-16']" ) ).stream( )
		            .map( WebElement::getText ).filter( s -> !s.isBlank( ) ).collect( Collectors.toCollection( LinkedList::new ) );
		player.put( driver.findElement( By.xpath( "//div[@class='cb-col cb-col-50 text-left']" ) ).getText( ), players );
		player.entrySet( ).stream( ).forEach( System.out::println );
	}

	@Test
	public void cricBuzzPlayersWithIdentityHashMap( ) {
		driver = new ChromeDriver( );
		driver.get( "https://www.cricbuzz.com/" );
		driver.manage( ).timeouts( ).implicitlyWait( Duration.ofSeconds( 10 ) );
		Actions actions = new Actions( driver );
		actions.moveToElement( driver.findElement( By.xpath( "//a[text()='Rankings']" ) ) )
		            .click( driver.findElement( By.xpath( "//a[text()='ICC Rankings - Men']" ) ) ).perform( );
		Map< String, String > player = new IdentityHashMap<>( );
		driver.findElements( By.xpath( "//a[@class='text-hvr-underline text-bold cb-font-16']" ) ).stream( ).map( WebElement::getText ).filter( s -> !s.isBlank( ) )
		            .forEach( text -> player.put( driver.findElement( By.xpath( "//div[@class='cb-col cb-col-50 text-left']" ) ).getText( ), text ) );

		player.entrySet( ).stream( ).forEach( System.out::println );
	}

	@Test
	public void easeMyTrip( ) {
		driver = new ChromeDriver( new ChromeOptions( ).addArguments( List.of( "--disable-blink-features=AutomationControlled", "--disable-notifications",
		            "--disable-infobars", "--disable-popup-blocking", "--disable-features=CookiesWithoutSameSiteMustBeSecure", "--disable-cookie-encryption",
		            "--disable-notifications", "--disable-popup-blocking" ) ) );
		driver.get( "https://www.easemytrip.com/" );
		driver.manage( ).timeouts( ).implicitlyWait( Duration.ofSeconds( 10 ) );
		driver.findElement( By.xpath( "//div[@id='frmcity']" ) ).click( );
		driver.findElement( By.xpath( "//span[text()='Bangalore(BLR)']" ) ).click( );
		driver.findElement(
		            By.xpath( "//div[@id='toautoFill']//ul[@class='ausuggest']//li[@onchange='ChangeCabin();']//div[@class='mflexcol']//div//span[@id='spn3']" ) )
		            .click( );
		driver.findElement( By.xpath( "//li[text()='4']" ) ).click( );
		driver.findElement( By.xpath( "//div[@class='innerspcr' and @id='divRtnCal']" ) ).click( );
		driver.findElement( By.xpath( "//li[text()='19']" ) ).click( );
		driver.findElement( By.xpath( "//input[@class='srchBtnSe']" ) ).click( );
	}

	@AfterTest( alwaysRun = true )
	public void tearDown( ) {
		driver.quit( );
	}

}

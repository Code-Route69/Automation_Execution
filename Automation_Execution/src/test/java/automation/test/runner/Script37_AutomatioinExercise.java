package automation.test.runner;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automation.main.java.util.JavaUtility;

public class Script37_AutomatioinExercise {
	WebDriver     driver;
	Select        select;
	String        main;
	WebDriverWait wait;
	Actions       action;

	@BeforeMethod
	public void config( ) {
		driver = new ChromeDriver( );
		driver.get( "https://testautomationpractice.blogspot.com/" );
		driver.manage( ).timeouts( ).implicitlyWait( Duration.ofSeconds( 10 ) );
		main   = driver.getWindowHandle( );
		wait   = new WebDriverWait( driver, Duration.ofSeconds( 10 ) );
		action = new Actions( driver );
	}

	@Test
	public void GUIElements( ) {
		driver.findElement( By.id( "name" ) ).sendKeys( "Ravi" );
		driver.findElement( By.id( "email" ) ).sendKeys( "Ravi@gmail.com" );
		driver.findElement( By.id( "phone" ) ).sendKeys( "9591588801" );
		driver.findElement( By.id( "textarea" ) ).sendKeys( "#58 3rd cross rajajinagar bengaluru 560010" );
		driver.findElement( By.id( "male" ) ).click( );
		driver.findElement( By.id( "monday" ) ).click( );
		select = new Select( driver.findElement( By.xpath( "//select[@id='country']" ) ) );
		select.selectByValue( "usa" );
		select = new Select( driver.findElement( By.xpath( "//select[@id='colors']" ) ) );
		select.selectByValue( "red" );
		select = new Select( driver.findElement( By.xpath( "//select[@id='animals']" ) ) );
		select.selectByValue( "cat" );
		driver.findElement( By.id( "datepicker" ) ).click( );
		driver.findElement( By.xpath( "//td[@data-handler='selectDay']/a[text()='5']" ) );
		driver.findElement( By.id( "txtDate" ) ).click( );
		driver.findElement( By.xpath( "//td[@data-handler='selectDay']/a[text()='5']" ) );
	}

	@Test
	public void tabs( ) {
		driver.findElement( By.id( "Wikipedia1_wikipedia-search-input" ) ).sendKeys( "Socrates" );
		driver.findElement( By.xpath( "//input[@class='wikipedia-search-button']" ) ).click( );
		driver.findElements( By.cssSelector( "#Wikipedia1_wikipedia-search-results > div" ) ).stream( ).forEach( i -> {
			i.click( );
			driver.switchTo( ).window( main );
		} );
//		driver.getWindowHandles( ).forEach( i -> {
//			driver.switchTo( ).window( i );
//			driver.close( );
//		} );

	}

	@Test
	public void dynamicButton( ) {
		driver.findElement( By.name( "start" ) ).click( );
		wait.until( ExpectedConditions.elementToBeClickable( By.name( "stop" ) ) ).click( );
	}

	@Test
	public void alertAndPopup( ) {
		driver.findElement( By.id( "alertBtn" ) ).click( );
		driver.switchTo( ).alert( ).accept( );
		driver.findElement( By.id( "confirmBtn" ) ).click( );
		driver.switchTo( ).alert( ).accept( );
		driver.findElement( By.id( "promptBtn" ) ).click( );
		Alert alert = driver.switchTo( ).alert( );
		alert.sendKeys( "Ravi" );
		alert.accept( );
		driver.findElement( By.xpath( "//button[text()='New Tab']" ) ).click( );
		driver.getWindowHandles( ).forEach( i -> {
			if ( !i.equals( main ) ) {
				driver.switchTo( ).window( i );
				driver.close( );
			}
		} );
		driver.switchTo( ).window( main );
		driver.findElement( By.id( "PopUp" ) ).click( );
		driver.getWindowHandles( ).forEach( i -> {
			if ( !i.equals( main ) ) {
				driver.switchTo( ).window( i );
				driver.close( );
			}
		} );
	}

	@Test
	public void moveHover( ) {
		action.moveToElement( driver.findElement( By.xpath( "//button[text()='Point Me']" ) ) ).pause( Duration.ofSeconds( 1 ) )
		            .moveToElement( driver.findElement( By.xpath( "//a[text()='Mobiles']" ) ) ).pause( Duration.ofSeconds( 1 ) )
		            .moveToElement( driver.findElement( By.xpath( "//a[text()='Laptops']" ) ) ).perform( );
	}

	@Test
	public void doubleClick( ) {
		driver.findElement( By.id( "field1" ) ).clear( );
		driver.findElement( By.id( "field1" ) ).sendKeys( "Ravi Shankar" );
		action.doubleClick( driver.findElement( By.xpath( "//button[text()='Copy Text']" ) ) ).perform( );
	}

	@Test
	public void dragAndDrop( ) {

		action.scrollToElement( driver.findElement( By.xpath( "//h2[text()='Slider']" ) ) )
		            .dragAndDrop( driver.findElement( By.id( "draggable" ) ), driver.findElement( By.id( "droppable" ) ) ).perform( );
	}

	@Test
	private void slider( ) {

		action.scrollToElement( driver.findElement( By.xpath( "//h2[text()='SVG Elements']" ) ) ).perform( );
		int to = 20;
		int i  = Integer
		            .parseInt( driver.findElement( By.cssSelector( "#slider-range > span" ) ).getAttribute( "style" ).replace( "left: ", "" ).replace( "%;", "" ) );
		while ( i > 0 && i <= to ) {

			action.clickAndHold( driver.findElement( By.cssSelector( "#slider-range > span" ) ) ).moveByOffset( i++, 0 ).perform( );

		}

		action.release( ).perform( );

	}

	@AfterMethod( alwaysRun = true )
	public void tearDown( ) {
		driver.quit( );
	}

}

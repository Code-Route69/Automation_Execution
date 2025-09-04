package automation.main.webdriver.util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebElementUtil {

	WebDriver             driver;
	static WebElementUtil webelement;

	private WebElementUtil( WebDriver driver ) {
		this.driver = driver;
	}

	public static WebElementUtil init( WebDriver driver ) {
		if ( webelement == null ) {
			webelement = new WebElementUtil( driver );
		}
		return webelement;
	}

	public WebElement find( Locator locator, String location ) {
		return switch ( locator ) {
			case ID              -> driver.findElement( By.id( location ) );
			case NAME            -> driver.findElement( By.name( location ) );
			case CLASSNAME       -> driver.findElement( By.className( location ) );
			case TAGNAME         -> driver.findElement( By.tagName( location ) );
			case LINKTEXT        -> driver.findElement( By.linkText( location ) );
			case PARTIALLINKTEXT -> driver.findElement( By.partialLinkText( location ) );
			case CSSSELECTOR     -> driver.findElement( By.cssSelector( location ) );
			case XPATH           -> driver.findElement( By.xpath( location ) );

			default              -> throw new IllegalArgumentException( "Unexpected value: " + locator );
		};
	}

	public List< WebElement > finds( Locator locator, String location ) {
		return switch ( locator ) {
			case ID              -> driver.findElements( By.id( location ) );
			case NAME            -> driver.findElements( By.name( location ) );
			case CLASSNAME       -> driver.findElements( By.className( location ) );
			case TAGNAME         -> driver.findElements( By.tagName( location ) );
			case LINKTEXT        -> driver.findElements( By.linkText( location ) );
			case PARTIALLINKTEXT -> driver.findElements( By.partialLinkText( location ) );
			case CSSSELECTOR     -> driver.findElements( By.cssSelector( location ) );
			case XPATH           -> driver.findElements( By.xpath( location ) );

			default              -> throw new IllegalArgumentException( "Unexpected value: " + locator );
		};
	}
}

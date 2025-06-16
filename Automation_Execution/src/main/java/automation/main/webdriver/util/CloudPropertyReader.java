package automation.main.webdriver.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CloudPropertyReader {
	private static Properties property;
	
	static {
		try {
			property = new Properties();
			property.load(new FileReader("./src/main/resources/UntangledCloud.properties"));
			
			
		} catch (IOException e) {
			throw new RuntimeException("File Not Loaded", e);
		}
	}
	
	public static String getProperty(String key) {
		return property.getProperty(key);
	}

}

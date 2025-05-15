package automation.main.file.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtility {
	Properties prop;
	public PropertyFileUtility(String path) throws FileNotFoundException, IOException {
	prop = new Properties();
	prop.load(new FileReader(path));
	}
	public String getValue(String key) {
		return prop.getProperty(key.toLowerCase());
	}
}

package automation.main.file.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONFileUtility {
	JSONObject jobject;
	public JSONFileUtility(String path) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		jobject = (JSONObject) parser.parse(new FileReader(path));
	}
	
	public String getValue(String key) {
		return jobject.get(key).toString();
	}

}

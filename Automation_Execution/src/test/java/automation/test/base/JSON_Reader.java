package automation.test.base;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSON_Reader {
	JSONObject jObj;
	public JSON_Reader() throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		jObj = (JSONObject) parser.parse(new FileReader("./src/test/resources/commondata_ninzahrm.json"));
	}
	
	public String browser() {
		return jObj.get("browser").toString();
	}
	public String testURL() {
		return jObj.get("testURL").toString();
	}
	public String userName() {
		return jObj.get("UserName").toString();
	}
	public String password() {
		return jObj.get("Password").toString();
	}
	public Object timeOut() {
		return jObj.get("TimeOut");
	}

}

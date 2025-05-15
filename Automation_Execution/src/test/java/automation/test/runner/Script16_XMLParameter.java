package automation.test.runner;

import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class Script16_XMLParameter {
	@Test
	public void testParameters(XmlTest xml) {
		System.out.println(xml.getParameter("Browser"));
	}

}

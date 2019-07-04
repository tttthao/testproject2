package testfw.testscript;

import org.testng.annotations.Test;

import testfw.UnitTestBase;
import testfw.object.GooglePage;
import utilities.selenium.PageGenerator;

public class testcasea extends UnitTestBase{
	@Test
	public void testcase() {
		PageGenerator page = new PageGenerator();
		GooglePage google = page.getInstance(GooglePage.class);
		google.openGoogle();
		
	}
}

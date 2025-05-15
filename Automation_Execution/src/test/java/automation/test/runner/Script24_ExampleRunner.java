package automation.test.runner;

import org.junit.Assert;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import automation.test.base.ExampleBase;

@Listeners(automation.util.listenerutil.ListenerUtil.class)
public class Script24_ExampleRunner extends ExampleBase {

	@Test(priority = 1)
	public void test1() {
		System.out.println("Test 1 Executed");
	}

	@Test(priority = 2)
	public void test2() {
		System.out.println("Test 2 Executed");
		Assert.fail();
	}

	@Test(priority = 3)
	public void test3() {
		throw new SkipException("Skipped Test");
	}

	@Test(priority = 4, invocationTimeOut = 0)
	public void test4() {
		System.out.println("Test 4 Executed");

	}

}

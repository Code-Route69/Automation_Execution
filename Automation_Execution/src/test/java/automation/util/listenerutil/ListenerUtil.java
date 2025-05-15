package automation.util.listenerutil;

import org.testng.IClassListener;
import org.testng.IRetryAnalyzer;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerUtil implements ITestListener, ISuiteListener, IClassListener, IRetryAnalyzer {

	@Override
	public void onBeforeClass(ITestClass testClass) {
		System.out.println("Listener : onBeforeClass");
		IClassListener.super.onBeforeClass(testClass);
	}

	@Override
	public void onAfterClass(ITestClass testClass) {
		System.out.println("Listener : onAfterClass");
		IClassListener.super.onAfterClass(testClass);
	}

	@Override
	public boolean isEnabled() {
		return ITestListener.super.isEnabled();
	}

	@Override
	public void onStart(ISuite suite) {

		System.out.println("Listener: onStart : Suite");
		ISuiteListener.super.onStart(suite);
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Listener : onFinish : Suite");
		ISuiteListener.super.onFinish(suite);
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Listener : onStart : Test");
		ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Listener : onSucess : Test");
		ITestListener.super.onTestSuccess(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Listener : onFailure : Test");
		ITestListener.super.onTestFailure(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Listener : onSkipped : Test");
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("Listener : on Percentage Success Test");
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		System.out.println("Listener : on TimeOut Fail Test");
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Listener : onStart : Context");
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Listener : onFinish : Context");
		ITestListener.super.onFinish(context);
	}

	@Override
	public boolean retry(ITestResult result) {
		System.out.println("Listen retry");
		return false;
	}

}

package utilities.reporting;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.qameta.allure.Step;
import utilities.common.JavaUtils;
import utilities.loggers.Log;

public class TestAssertion extends Assert {
	private TestAssertion() {}
	
	private static ThreadLocal<SoftAssert> softAssert = new ThreadLocal<SoftAssert>();

	@Step("{2}. Expected: {0}, Actual: {1}")
	public static synchronized void assertEquals(String actualText, String expectedText, String validateMessage) {
		Assert.assertEquals(actualText, expectedText, validateMessage);
		pass("[" + validateMessage + "] is Passed. Expected: [" + expectedText + "] and found [" + actualText + "]");
	}
	
	@Step("{1}. Expected: True, Actual: {1}")
	public static synchronized void assertTrue(boolean condition, String validateMessage) {
		Assert.assertTrue(condition, validateMessage);
		pass("[" + validateMessage + "] is Passed.");
	}
	
	public static synchronized void pass(String validateMessage) {
		Log.info(validateMessage);
		ExtentTestManager.setStepPass(validateMessage);
	}
	
	public static synchronized void info(String validateMessage) {
		Log.info(validateMessage);
		ExtentTestManager.setStepInfo(validateMessage);
	}
	
	public static synchronized void fail(String validateMessage, Throwable error) {
		if (error != null) {
			Log.error(validateMessage, JavaUtils.stackTraceToString(error));
			ExtentTestManager.setStepFail(validateMessage, error);
			Assert.fail(validateMessage, error);
		} else {
			Log.error(validateMessage, "");
			ExtentTestManager.setStepFail(validateMessage, new Throwable(validateMessage));
			Assert.fail(validateMessage);
		}
		
	}

	public static synchronized void setSoftValidate() {
		softAssert.set(new SoftAssert());
	}

	public static synchronized SoftAssert getSoftValidate() {
		return softAssert.get();
	}

	public static synchronized void softValidateAll(String testName) {
		try {
			softAssert.get().assertAll();
			ExtentTestManager.setStepPass(testName + " is Passed");
		} catch (AssertionError assertEx) {
			Log.error(assertEx.getMessage(), JavaUtils.stackTraceToString(assertEx));
			ExtentTestManager.setStepFail("", assertEx);
			Assert.fail(assertEx.getMessage(), assertEx);
		}
	}

}

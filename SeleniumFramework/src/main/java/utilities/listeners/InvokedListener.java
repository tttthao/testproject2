package utilities.listeners;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.IClassListener;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestClass;
import org.testng.ITestResult;

import configuration.ConfigReader;
import utilities.common.JavaUtils;
import utilities.loggers.Log;
import utilities.reporting.ExtentManager;
import utilities.reporting.ExtentTestManager;
import utilities.reporting.TestAssertion;
import utilities.selenium.BrowserFactory;
import utilities.selenium.DriverFactory;
import utilities.selenium.GetScreenshot;

public class InvokedListener implements IInvokedMethodListener, ISuiteListener, IClassListener {

	@Override
	public void onStart(ISuite suite) {
		String suiteName = suite.getXmlSuite().getName();

		Log.initLog();
		String browserName = System.getProperty("browser");
		if (null == browserName || browserName.equals("")) {
			browserName = suite.getXmlSuite().getParameter("browser");
		}

		if (null == browserName || browserName.equals("")) {
			Properties configFile = ConfigReader.getProjectConfig();
			if (configFile != null) {
				browserName = configFile.getProperty("defaultBrowserType");
				if (browserName == null || browserName.equals("")) {
					Log.info(
							"[defaultBrowserType] value in [project.properties] is not set up! Firefox will be started as default.");
					browserName = "firefox";
				}
			} else {
				browserName = "firefox";
			}
		}
		BrowserFactory.setBrowserName(browserName);
		ExtentManager.createInstance(suiteName, browserName);
	}

	@Override
	public void onFinish(ISuite suite) {
		ExtentManager.getExtentReport().flush();
	}

	@Override
	public void onBeforeClass(ITestClass testClass) {
		Log.startLog(testClass.getName());
		ExtentTestManager.startClassTest(testClass.getName());
		String browserName = BrowserFactory.getBrowserName();

		WebDriver webDriver = DriverFactory.setDriver(browserName);
		webDriver.manage().window().maximize();
	}

	@Override
	public void onAfterClass(ITestClass testClass) {
		WebDriver webDriver = DriverFactory.getDriver();
		if (webDriver != null) {
			webDriver.quit();
		}
		Log.endLog(testClass.getName());
	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		String methodName = method.getTestMethod().getMethodName();

		String testDescription = method.getTestMethod().getDescription();
		String browserName = BrowserFactory.getBrowserName();
		if (method.isTestMethod()) {
			Log.startLog(methodName);
			TestAssertion.setSoftValidate();

		}
		if (null == testDescription || testDescription.equals("")) {
			ExtentTestManager.startTest("Testing [" + methodName + "] on browser [" + browserName + "]");
		} else {
			ExtentTestManager.startTest("Testing [" + testDescription + "] on browser [" + browserName + "]");
		}
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod()) {
			if (testResult.isSuccess()) {
				TestAssertion.softValidateAll(testResult.getName());
			} else if (testResult.getThrowable() != null
					&& testResult.getThrowable().toString().contains("org.testng.SkipException")) {
				Log.warn(testResult.getThrowable().getMessage());
				ExtentTestManager.setStepWarning(testResult.getThrowable().getMessage());
			} else {
				GetScreenshot.saveAllureFailScreenshot();
				Log.error(testResult.getThrowable().getMessage(),
						JavaUtils.stackTraceToString(testResult.getThrowable()));
				ExtentTestManager.setStepFail(testResult.getThrowable().getMessage(), testResult.getThrowable());
			}
		}
	}

}

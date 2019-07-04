package utilities.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import utilities.selenium.GetScreenshot;

public class ExtentTestManager {
	private ExtentTestManager() {}
	
	private static ExtentReports extentReport = ExtentManager.getExtentReport();
	private static ThreadLocal<ExtentTest> extentClassTest = new ThreadLocal<ExtentTest>();
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	private static ThreadLocal<ExtentTest> extentStep = new ThreadLocal<ExtentTest>();
	private static ThreadLocal<Integer> stepNumber = new ThreadLocal<>();

	public static void startClassTest(String groupTest) {
		extentClassTest.set(extentReport.createTest(groupTest));
	}

	public static ExtentTest getClassTest() {
		return extentClassTest.get();
	}

	public static void startTest(String nodeDesc) {
		initStepNumber();
		extentTest.set(getClassTest().createNode(nodeDesc));
	}

	public static ExtentTest getTest() {
		return extentTest.get();
	}

	public static void startStep(String testDesc) {
		extentStep.set(getTest().createNode("Step " + getStepNumber() + ": " + testDesc));
		increaseStepNumber();
	}

	public static ExtentTest getStep() {
		if(extentStep.get()==null) {
			startStep("");
		} 	
		return extentStep.get();
	}

	public static void setStepInfo(String resultDesc) {
		getStep().info(resultDesc);
	}

	public static void setStepWarning(String resultDesc) {
		getStep().warning("<p style=\"color:orange\">" + resultDesc);
	}

	public static void setStepPass(String resultDesc) {
		getStep().pass(resultDesc);
	}

	public static void setStepFail(String stepDescription, Throwable error) {
		try {
			getStep().fail("<p style=\"color:red\">" + stepDescription + "</br>" + error + "</br>");
			getStep().fail(error).addScreenCaptureFromBase64String(GetScreenshot.captureError(), "Failure snapshoot");

		} catch (Exception ex) {
			getStep().fail(stepDescription + "</h5></br>Can't get screenshot: " + ex.getMessage());
		}
	}

	private static void initStepNumber() {
		stepNumber.set(1);
	}

	private static int getStepNumber() {
		return stepNumber.get();
	}

	private static void increaseStepNumber() {
		stepNumber.set(getStepNumber() + 1);
	}

}

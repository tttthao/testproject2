package utilities.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import configuration.ConfigConstant;
import configuration.ConfigReader;
import utilities.reporting.TestAssertion;

public class DriverFactory {
	private DriverFactory() {}
	
	private static BrowserOptionsManager optionsManager = new BrowserOptionsManager();
	private static ThreadLocal<WebDriver> threadlocalDriver = new ThreadLocal<>();

	private static int defaultWaitTimer = 60;

	public static WebDriver setDriver(String browser) {
		String driversFolderPath = ConfigReader.getDriverPath();
		if (browser.equalsIgnoreCase(BrowserFactory.FIREFOX)) {
			System.setProperty("webdriver.gecko.driver", driversFolderPath + ConfigConstant.FILE_SEPARATOR + "geckodriver.exe");
			threadlocalDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		} else if (browser.equalsIgnoreCase(BrowserFactory.CHROME)) {
			System.setProperty("webdriver.chrome.driver", driversFolderPath + ConfigConstant.FILE_SEPARATOR + "chromedriver.exe");
			threadlocalDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} else if (browser.equalsIgnoreCase(BrowserFactory.INTERNET_EXPLORER)) {
			System.setProperty("webdriver.ie.driver", driversFolderPath + ConfigConstant.FILE_SEPARATOR + "IEDriverServer.exe");
			threadlocalDriver.set(new InternetExplorerDriver(optionsManager.getInternetExplorerOption()));
		} else if (browser.equalsIgnoreCase(BrowserFactory.SAFARI)) {
			threadlocalDriver.set(new SafariDriver());
		} else {
			TestAssertion.fail("Invalid browser name! This browser is not supported or not defined",
					new Throwable("Invalid browser name!"));
		}
		return threadlocalDriver.get();
	}

	public static WebDriverWait getWait(WebDriver driver) {
		return new WebDriverWait(driver, defaultWaitTimer);
	}

	public static WebDriver getDriver() {
		return threadlocalDriver.get();
	}

	/**
	 * public static synchronized void setGridDriver(String browser, @Optional
	 * String gridHost) { if (null == gridHost) { gridHost =
	 * "http://localhost:4444/wd/hub"; } if (browser.equals("firefox")) { try {
	 * threadlocalDriver.set(new RemoteWebDriver(new URL(gridHost),
	 * optionsManager.getFirefoxOptions())); } catch (MalformedURLException e) {
	 * e.printStackTrace(); }
	 * 
	 * } else if (browser.equals("chrome")) { try { threadlocalDriver.set(new
	 * RemoteWebDriver(new URL(gridHost), optionsManager.getChromeOptions())); }
	 * catch (MalformedURLException e) { e.printStackTrace(); } } }
	 **/
}

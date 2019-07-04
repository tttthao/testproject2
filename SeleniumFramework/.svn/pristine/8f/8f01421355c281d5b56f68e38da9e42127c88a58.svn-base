package utilities.appium;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import utilities.loggers.Log;

public class MobileDriverFactory {
	private MobileDriverFactory() {}
	
	private static MobileCapabilities capabilities = new MobileCapabilities();
	private static ThreadLocal<WebDriver> threadlocalMobileDriver = new ThreadLocal<>();

	public static WebDriver setMobileDriver(String appiumURL, String deviceName, String platformName) {
		try {
			threadlocalMobileDriver.set(new AndroidDriver<AndroidElement>(new URL("http://" + appiumURL + "/wd/hub"),
					capabilities.getAndroidCapabilites(deviceName, platformName)));
		} catch (MalformedURLException e) {
			Log.error("Fail to init Driver", e.getMessage());
		}
		return threadlocalMobileDriver.get();
	}
}

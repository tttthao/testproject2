package utilities.appium;

import org.openqa.selenium.remote.DesiredCapabilities;

public class MobileCapabilities {
	public DesiredCapabilities getAndroidCapabilites(String deviceName, String platformName) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		/**capabilities.setCapability("idid", udid);**/
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("platformName", platformName);
		capabilities.setCapability("appPackage", "com.android.calculator2");
		capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
		return capabilities;
	}
}

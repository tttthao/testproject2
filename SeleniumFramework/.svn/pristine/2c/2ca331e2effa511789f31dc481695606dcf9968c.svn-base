package utilities.selenium;

import java.io.File;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

import configuration.ConfigConstant;
import configuration.ConfigReader;
import utilities.loggers.Log;

public class BrowserOptionsManager {

	public ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--incognito");
		options.addArguments("disable-infobars");
		return options;
	}

	public FirefoxOptions getFirefoxOptions() {
		boolean activeProfile = ConfigReader.getBrowserProfileConfig();
		FirefoxOptions option = new FirefoxOptions();
		option.setAcceptInsecureCerts(true);
		
		FirefoxProfile profile = null;
		if(activeProfile) {
			File ffProfileLocation = new File(ConfigConstant.CURRENT_DIR + ConfigConstant.FILE_SEPARATOR + "profiles");
			if(ffProfileLocation.exists()) {
				Log.info("[startWithProfile] option is Actived and Firefox profile folder exists. Project will run with this profile!!");
				profile = new FirefoxProfile(ffProfileLocation);
				profile.setAcceptUntrustedCertificates(true);
				profile.setPreference("webdriver.load.strategy", "unstable");
				profile.setPreference("app.update.enabled", false);
				option.addPreference("network.automatic-ntlm-auth.trusted-uris", "bosch.com,bosch.de");
				option.addPreference("network.negotiate-auth.delegation-uris", "bosch.com,bosch.de");
				option.addPreference("network.negotiate-auth.trusted-uris", "bosch.com,bosch.de");
			} else {
				Log.info("[startWithProfile] option is Actived but Firefox profile folder is not existed. Project will run in [Private Mode]!!");
				profile = new FirefoxProfile();
			}
		} else {
			Log.info("[startWithProfile] is Inactive for running profile. Project will run in [Private Mode]!!");
			profile = new FirefoxProfile();
		}
		
		profile.setPreference("browser.privatebrowsing.autostart", false);
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
				"application/vnd.ms-excel;application/vnd.ms-excel.addin.macroenabled.12;application/vnd.ms-excelsheet.binary.macroenabled.12;application/vnd.ms-excel.template.macroenabled.12;application/vnd.ms-excel.sheet.macroenabled.12");
		profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		profile.setPreference("browser.download.manager.showWhenStarting", false);
		profile.setPreference("security.mixed_content.block_active_content", false);
		profile.setPreference("security.mixed_content.block_display_content", true);
		
		option.setProfile(profile);
		
		return option;

	}

	public InternetExplorerOptions getInternetExplorerOption() {
		InternetExplorerOptions options = new InternetExplorerOptions();
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
		options.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, true);
		options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		options.setCapability("requireWindowFocus", false);
		options.setCapability("enablePersistentHover", false);
		options.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
		options.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "");
		options.addCommandSwitches("-private");
		return options;
	}
}

package configuration;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import utilities.common.JavaUtils;
import utilities.datadriven.PropertiesReader;
import utilities.loggers.Log;

public class ConfigReader {
	private ConfigReader() {
	}

	public static String getProjectConfigFilePath() {
		Path configPath = Paths.get(ConfigConstant.CURRENT_DIR, "configs");
		if (!Files.exists(configPath)) {
			JavaUtils.createDirectoryPath(configPath.toString());
		}
		return configPath.toString();
	}

	public static Properties getProjectConfig() {
		Properties projectPro = PropertiesReader
				.loadPropertiesFile(getProjectConfigFilePath() + ConfigConstant.FILE_SEPARATOR + "project.properties");
		if (projectPro == null) {
			throw new AssertionError("[project.properties] does not exist!!! Please set up for your project.");
		}
		return projectPro;
	}

	public static InputStream getLog4jConfig() {
		InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("configs/log4j.properties");
		if (inputStream == null) {
			throw new AssertionError("[log4j.properties] does not exist!!!");
		}
		return inputStream;
	}

	public static String getDriverPath() {
		String driverPath = null;
		try {
			driverPath = ConfigConstant.DRIVER_FOLDER_PATH;
		} catch (Exception ex) {
			throw new AssertionError(
					"[DRIVER_FOLDER_PATH] does not exist!!! Please set up environment variable [DRIVER_FOLDER_PATH] for your project.");
		}
		return driverPath;
	}

	public static boolean getBrowserProfileConfig() {
		boolean goWithProfile = true;
		String startWithProfile = getProjectConfig().getProperty("startWithProfile");
		if (startWithProfile == null || startWithProfile.equals("") || startWithProfile.equalsIgnoreCase("no")) {
			goWithProfile = false;
		}
		return goWithProfile;
	}

	public static String getExtentReportPath() {
		String reportPath = getProjectConfig().getProperty("report.extent.folder");
		if (reportPath == null || reportPath.equals("")) {
			Log.info("[report.extent.folder] is not set up or not have value!! Default report path will be used.");
			reportPath = ConfigConstant.CURRENT_DIR + ConfigConstant.FILE_SEPARATOR + "reports";
		}
		return reportPath;
	}
}

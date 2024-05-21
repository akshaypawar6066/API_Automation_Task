package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	public static String readPropData(String propertyToRead) throws IOException {
		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties";

		Properties prop = new Properties();
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		prop.load(fis);
		return prop.getProperty(propertyToRead).trim();

	}

}

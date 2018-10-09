package googletests.helpers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.Logger;

import googletests.apps.Constants;
import googletests.listeners.EventListener;

public class ConfigFileReader {
	private Properties properties;
	private final String propertyFilePath = Constants.PROPERTIES_DIRECTORY + "config.properties";
	private static Logger log = Log.getLogData(EventListener.class.getName());
	
	public ConfigFileReader() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			}catch (IOException e) {
				System.err.println("[Error] - Unable to load properties");
				log.error("Unable to load properties");
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.err.println("[Error] - Configuration.properties not found at " + propertyFilePath);
			log.error("Configuration.properties not found at" + propertyFilePath);
			e.printStackTrace();
		}
	}
	
	public long getPageLoadWait(){
		String pageLoadWait = properties.getProperty("pageLoadWait");
		if(pageLoadWait!= null) return Long.valueOf(pageLoadWait);
		else throw new RuntimeException("[Error] - pageLoadWait not specified in the Configuration.properties file");
	}
	
	public long getImplicitlyWait() {		
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
		else throw new RuntimeException("[Error] - implicitlyWait not specified in the Configuration.properties file");		
	}
	
	public String getBaseUrl() {
		String url = properties.getProperty("url");
		if(url != null) return url;
		else throw new RuntimeException("[Error] - url not specified in the Configuration.properties file");
	}
}
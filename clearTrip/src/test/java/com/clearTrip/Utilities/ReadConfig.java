package com.clearTrip.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	
	Properties property;
	
	public ReadConfig()
	{
		File file=new File("./Configuration/config.properties");
		
		try {
			
			FileInputStream fiStream=new FileInputStream(file);
			
			property=new Properties();
			property.load(fiStream);
			
			
		} catch (Exception e) {
			System.out.println("Exception is "+e.getMessage());
		}
		
		
	}
	
	public String getUrl()
	{
		String baseUrl=property.getProperty("baseURL");
		return baseUrl;
	}
	
	public String getChromePath()
	{
		String chromepath=property.getProperty("chromepath");
		return chromepath;
	}
	
	public String getFirefoxPath()
	{
		String firefoxpath=property.getProperty("firefox");
		return firefoxpath;
	}
	
	public String getIEPath()
	{
		String iepath=property.getProperty("iepath");
		return iepath;
	}
	
	public String getBrowser()
	{
		String browser=property.getProperty("browser");
		return browser;
	}

}

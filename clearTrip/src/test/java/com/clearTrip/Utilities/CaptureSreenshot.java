/*
 * 
 * @author : ABHISHEK BAGCHI
 * 
 */


package com.clearTrip.Utilities;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureSreenshot {

	public String captureScreenshot(WebDriver driver,String ScreenshotName) throws IOException
	{
		
		Date date=new Date();
		
		Timestamp timestamp=new Timestamp(date.getTime());
		
		String time=timestamp.toString();
		
		time=time.replace(' ', '-');
		
		time=time.replace(':', '-');
		
		File source=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		String destination=System.getProperty("user.dir")+"\\Screenshots\\"+ScreenshotName+"_"+time+"_"+"_"+".png";
		File fDestination=new File(destination);
		
		FileUtils.copyFile(source, fDestination);
		
		return destination;
	}
	
	
	
}

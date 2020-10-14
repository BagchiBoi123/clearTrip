package com.clearTrip.testCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.clearTrip.Utilities.ExcelReader;
import com.clearTrip.Utilities.ReadConfig;

public class BaseClass {
	
		
ReadConfig readConfig=new ReadConfig();
	
public String baseURL=readConfig.getUrl();
		

ExcelReader reader=new ExcelReader();

public static String from;
public static String to;
public static String depDate;
public static String retDate;

public static ArrayList<String> records;

public static int numberOfAdults=4;
public static int numberOfChildren=3;
public static int numberOfInfants=2;


public void readData() throws IOException {
records=reader.getExcelData();

for (int i = 0; i < records.size(); i++)  
{
    System.out.print(records.get(i) + " ");   

from=records.get(0);
to=records.get(1);
depDate=records.get(2);
retDate=records.get(3);
}
		 
}


/*public BaseClass() throws IOException {
String arr[]=reader.readExcel();

from=arr[0];
to=arr[1];
depDate=arr[2];
retDate=arr[3];


ArrayList<String> records=reader.getExcelData();
for (int i = 0; i < records.size(); i++)  
    System.out.print(records.get(i) + " ");   


from=records.get(0);
to=records.get(1);
depDate=records.get(2);
retDate=records.get(3);
}*/
		 public String tripType="Round";
		 
		 
		 public static WebDriver driver;
			
		
	public void setup()
	{
		
		System.setProperty("webdriver.chrome.driver", readConfig.getChromePath());
		
		driver=new ChromeDriver();
		
		driver.get("https://www.cleartrip.com/");
		
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		driver.manage().deleteAllCookies();

		driver.manage().window().maximize();
		
	}
	
	
	public void tearDown()
	{
		driver.quit();
	}

	
	public void scrollDown()
	{
		JavascriptExecutor jsExecutor=(JavascriptExecutor)driver;
		jsExecutor.executeScript("window.scrollBy(0,370)");
	}

	
	public void scrollDownTwo()
	{
		JavascriptExecutor jsExecutor=(JavascriptExecutor)driver;
		jsExecutor.executeScript("window.scrollBy(0,700)");
	}
	
	public void scrollUp()
	{
		JavascriptExecutor jsExecutor=(JavascriptExecutor)driver;
		jsExecutor.executeScript("window.scrollBy(0,370)");
	}
	
	
	public void slider()
	{
		WebElement slider=driver.findElement(By.xpath("//body/div[@id='root']/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/aside[1]/div[4]/div[5]/div[2]/div[1]/div[1]/div[1]/span[1]/span[1]/div[1]"));
		int width=slider.getSize().getWidth();
		Actions move=new Actions(driver);
		Action action=move.dragAndDropBy(slider, ((width*-700)/100), 0).click().build();
		action.perform();
	}
	
	
}






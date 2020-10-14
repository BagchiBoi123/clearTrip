/*
 * 
 * @author : ABHISHEK BAGCHI
 * 
 */



package com.clearTrip.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DatePicker {
	
	
	static WebDriver driver;
	
	public DatePicker(WebDriver driver)
	{
		
		this.driver=driver;
	}

	public void selectDepartureDate(String depDate) throws InterruptedException {
		
		int dd=Integer.parseInt(depDate.substring(0,2));
		int mm=Integer.parseInt(depDate.substring(3,5));
		int yyyy=Integer.parseInt(depDate.substring(6,10));
		
		String currentDate="07/10/2020";
		int dd1=Integer.parseInt(currentDate.substring(0,2));
		int mm1=Integer.parseInt(currentDate.substring(3,5));
		int yyyy1=Integer.parseInt(currentDate.substring(6,10));
		
		
		/*System.setProperty("webdriver.chrome.driver", "C:\\Users\\848829\\eclipse-workspace\\clearTrip\\Drivers\\chromedriver.exe");
		
		WebDriver driver=new ChromeDriver();
		
		driver.get("https://www.cleartrip.com/");

		driver.manage().window().maximize();*/
		
		
		driver.findElement(By.xpath("//body/section[@id='Home']/div[1]/div[1]/div[1]/form[1]/div[4]/section[2]/div[1]/dl[1]/dd[1]/div[1]/a[1]/i[1]")).click();
		Thread.sleep(2000);
		
		int monthGap=mm-mm1;
		if(monthGap>0 && monthGap<2)
		{
			driver.findElement(By.xpath("//div[@class='monthBlock last']//a[contains(text(),'"+dd+"')]")).click();
		}
		else if (monthGap==0) {
			driver.findElement(By.xpath("//div[@class='monthBlock first']//a[contains(text(),'"+dd+"')]")).click();
		}
		else {
			int monthDiff=(yyyy-yyyy1)*12+(mm-mm1);
			System.out.println(monthDiff+" Months left before flight takes off!");
			for(int i=1;i<=monthDiff;i++)
			{
				
			driver.findElement(By.xpath("//body/div[@id='ui-datepicker-div']/div[2]/div[1]/a[1]")).click();
			Thread.sleep(3000);
			
			}
			Thread.sleep(3000);
			if(mm%2==0)
			{
				driver.findElement(By.xpath("//div[@class='monthBlock last']//a[contains(text(),'"+dd+"')]")).click();
			}
			else
			{
				
				driver.findElement(By.xpath("//div[@class='monthBlock first']//a[contains(text(),'"+dd+"')]")).click();
			}
			Thread.sleep(2000);
		}
		
	}





public void selectReturnDate(String retDate,String depDate) throws InterruptedException {
	
	
	
	
	
	
	int dd=Integer.parseInt(retDate.substring(0,2));
	int mm=Integer.parseInt(retDate.substring(3,5));
	int yyyy=Integer.parseInt(retDate.substring(6,10));
	
	
	//String currentDate="07/10/2020";
	int dd1=Integer.parseInt(depDate.substring(0,2));
	int mm1=Integer.parseInt(depDate.substring(3,5));
	int yyyy1=Integer.parseInt(depDate.substring(6,10));
	
	
	/*System.setProperty("webdriver.chrome.driver", "C:\\Users\\848829\\eclipse-workspace\\clearTrip\\Drivers\\chromedriver.exe");
	
	WebDriver driver=new ChromeDriver();
	
	driver.get("https://www.cleartrip.com/");

	driver.manage().window().maximize();*/
	
	
	driver.findElement(By.xpath("//body/section[@id='Home']/div[1]/div[1]/div[1]/form[1]/div[4]/section[2]/div[3]/dl[1]/dd[1]/div[1]/a[1]/i[1]")).click();
	Thread.sleep(2000);
	
	int monthGap=mm-mm1;
	if(monthGap>0 && monthGap<2)
	{
		driver.findElement(By.xpath("//div[@class='monthBlock last']//a[contains(text(),'"+dd+"')]")).click();
	}
	else if (monthGap==0) {
		driver.findElement(By.xpath("//div[@class='monthBlock first']//a[contains(text(),'"+dd+"')]")).click();
	}
	else {
		int monthDiff=(yyyy-yyyy1)*12+(mm-mm1);
		System.out.println(monthDiff+" Months left before flight takes off!");
		for(int i=1;i<monthDiff;i++)
		{
		driver.findElement(By.xpath("//body/div[@id='ui-datepicker-div']/div[2]/div[1]/a[1]")).click();
		Thread.sleep(3000);
		}
		Thread.sleep(3000);
		if(mm%2==0)
		{
			driver.findElement(By.xpath("//div[@class='monthBlock last']//a[contains(text(),'"+dd+"')]")).click();
		}
		else {
			driver.findElement(By.xpath("//div[@class='monthBlock first']//a[contains(text(),'"+dd+"')]")).click();
		}
		Thread.sleep(2000);
	}
	
}

}




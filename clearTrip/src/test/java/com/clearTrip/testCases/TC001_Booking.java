package com.clearTrip.testCases;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.clearTrip.Utilities.CaptureSreenshot;
import com.clearTrip.Utilities.DatePicker;
import com.clearTrip.Utilities.ExcelUtility;
import com.clearTrip.pageObjects.BookFlights;
import com.clearTrip.pageObjects.SearchFlights;

public class TC001_Booking extends BaseClass {

	CaptureSreenshot screen=new CaptureSreenshot();
	
	SearchFlights sFlights;
	
	BookFlights bFlights;
	
	Logger logger=Logger.getLogger(TC001_Booking.class);
	
	public ExtentHtmlReporter htmlReporter;
	
	public ExtentReports extent;
	
	public ExtentTest test;
	

	@BeforeClass
	public void driverSetup()
	{
		logger.info("===========================================Launching Browser=========================================");
		setup();
	}
	
	
	@BeforeTest
	public void setExtent()
	{
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/myReport.html");
		
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Functional Report");
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("OS", "Windows10");
		extent.setSystemInfo("Tester Name", "Abhishek Bagchi");
		extent.setSystemInfo("Broweser", "Chrome");
		
	}
	
	
	@AfterTest
	public void endReport()
	{
		extent.flush();
	}
	

	@Test(priority=0)
	 public void search() throws InterruptedException, IOException
	 {
		test=extent.createTest("search");
	
	sFlights=new SearchFlights(driver);
	
	
	if(driver.findElement(SearchFlights.cityFrom).isEnabled())
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.border='3px solid green'",driver.findElement(SearchFlights.cityFrom));
	}
	Thread.sleep(2000);
	
	
	if(tripType.equalsIgnoreCase("round"))
	{
	sFlights.selectRoundTrip();	
	Thread.sleep(1000);
	
	readData();
	
	sFlights.selectFromCity(from);
	Thread.sleep(1000);
	sFlights.selectToCity(to);
	Thread.sleep(1000);
	
	
	
	
	DatePicker dPicker=new DatePicker(driver);
	dPicker.selectDepartureDate(depDate);
	dPicker.selectReturnDate(retDate,depDate);
	}
	else
	{
		sFlights.selectFromCity(from);
		Thread.sleep(1000);
		sFlights.selectToCity(to);
		Thread.sleep(1000);
		
		DatePicker dPicker=new DatePicker(driver);
		dPicker.selectDepartureDate(depDate);
	}
	
	Thread.sleep(1000);
	sFlights.selectNumberOfAdults(numberOfAdults);
	Thread.sleep(2000);
	sFlights.selectNumberOfChildren(numberOfChildren);
	Thread.sleep(2000);
	sFlights.selectNumberOfInfants(numberOfInfants);
	Thread.sleep(2000);
	sFlights.searchFlights();
	Thread.sleep(3000);
	
	
	
	
	}
	
	
	
	@Test(priority=1)
	public void verifyFlightsAvailabeTitle() throws IOException, InterruptedException
	{
		test=extent.createTest("verifyFlightsAvailableTitle");
		
		sFlights=new SearchFlights(driver);
		
		bFlights=new BookFlights(driver);
		
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Thread.sleep(3000);
		String originalTitle=driver.getTitle();
		Thread.sleep(3000);
		
		System.out.println(originalTitle);
		System.out.println(originalTitle.length());
		
		String expectedTitle=String.format("Cleartrip | %s ⇄ %s", from,to);
		System.out.println(expectedTitle);
		
		System.out.println(expectedTitle.length());
		
		if(originalTitle.equalsIgnoreCase(expectedTitle))
		{
			System.out.println("same");
		}
		else {
			System.out.println("different");
		}
		
		Thread.sleep(2000);
		sFlights.selectDepartureTime();
		Thread.sleep(2000);
		slider();
		Thread.sleep(2000);
		
		scrollDown();
		Thread.sleep(2000);
		
		bFlights.BookDesiredDepartureFlight();
		Thread.sleep(2000);
		
		scrollDownTwo();
		Thread.sleep(2000);
		bFlights.BookDesiredReturnFlight();
		
		Thread.sleep(2000);
		
	}
	@AfterMethod
	public void getStatus(ITestResult result) throws IOException
	{
		if(result.getStatus()== ITestResult.FAILURE)
		{
			test.log(Status.FAIL, "TEST CASE FAILED IS "+ result.getName());
			test.log(Status.FAIL, "TEST CASE FAILED IS "+ result.getThrowable());
			
			String screenShotPath=screen.captureScreenshot(driver,result.getName());
			
			test.addScreenCaptureFromPath(screenShotPath);
			
		}
		
		else if (result.getStatus()== ITestResult.SUCCESS) {
			test.log(Status.PASS, "TEST CASE PASSED IS "+result.getName());
			
		}
		else if(result.getStatus()== ITestResult.SKIP)
		{
			test.log(Status.SKIP, "TEST CASE SKIPPED IS "+result.getName());
		}
		
	}
	
	
	
	
	/*@AfterClass
	public void closeBrowser()
	{
		tearDown();
	}*/
	
	
}
	
	
	


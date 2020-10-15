package com.clearTrip.testCases;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.clearTrip.Utilities.CaptureSreenshot;
import com.clearTrip.Utilities.DatePicker;
import com.clearTrip.pageObjects.BookFlights;
import com.clearTrip.pageObjects.SearchFlights;

public class TC001_Booking extends BaseClass {

	CaptureSreenshot screen=new CaptureSreenshot();
	
	SearchFlights sFlights;
	
	BookFlights bFlights;
	
	Logger logger=LogManager.getLogger(TC001_Booking.class);
	
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
	public void verifyTitle() {
		
		test=extent.createTest("Verify Page Title");
		
		logger.info("==============================Verifying Page Title==========================");
		
		String actual=driver.getTitle();
		
		String expected="#1 Site for Booking Flights, Hotels, Packages, Trains & Local activities.";
		
		Assert.assertEquals(actual, expected, "This isn't the webpage we are working on");
		
		
	}
	
	
	
	@Test(priority=1)
	public void smoke() throws InterruptedException {
		
		test=extent.createTest("Check if from and to textboxes are enabled or not");
		
		logger.info("====================================Checking From AND To fields======================================");
		
		if((driver.findElement(SearchFlights.cityFrom).isEnabled()) && (driver.findElement(SearchFlights.cityTo).isEnabled()))
		{
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].style.border='3px solid green'",driver.findElement(SearchFlights.cityFrom));
			js.executeScript("arguments[0].style.border='3px solid green'",driver.findElement(SearchFlights.cityTo));
		}
		Thread.sleep(2000);
		
		
	}
	
	
	
	
	

	@Test(priority=2)
	 public void searchFlight() throws InterruptedException, IOException
	 {
		test=extent.createTest("Search for a Flight");
		
		logger.info("===================================Searching for a desired Flight=======================================");
	
	sFlights=new SearchFlights(driver);
	
	
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
	
	logger.info("=======================CLicked ON Submit Button======================");
	Thread.sleep(3000);
	
	}
	
	
	
	
	@Test(priority=3)
	public void selectDepartureTimeAndPrice() throws InterruptedException
	{
		test=extent.createTest("Select Departure Time And Budget");
		
		bFlights=new BookFlights(driver);
		
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Thread.sleep(3000);
		
		Thread.sleep(2000);
		bFlights.selectDepartureTime();
		
		logger.info("======================Selected the departure time======================");
		
		Thread.sleep(2000);
		slider();
		Thread.sleep(2000);
		
	}
	
	
	
	@Test(priority=4)
	public void verifyBookFlightPageTitle() throws InterruptedException
	{
		
		test=extent.createTest("Verify Flight Booking Page Title");
		
		logger.info("==================================Verifying Booking Page Title============================");
		
		
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
		
	}
	
	
	
	@Test(priority=5)
	public void bookFlight() throws IOException, InterruptedException
	{
		test=extent.createTest("Book FLight");
		
		
		
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
	
	
	


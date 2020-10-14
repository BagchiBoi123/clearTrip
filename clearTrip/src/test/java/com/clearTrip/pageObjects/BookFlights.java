package com.clearTrip.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookFlights {
	
WebDriver driver;
	
	public BookFlights(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(xpath="//p[contains(text(),'12:10')]")
	WebElement selectDesiredDepFlight;
	
	@FindBy(xpath="//p[contains(text(),'11:40')]")
	WebElement selectDesiredRetFlight;
	
	public void BookDesiredDepartureFlight()
	{
		selectDesiredDepFlight.click();
	}
	
	public void BookDesiredReturnFlight()
	{
		selectDesiredRetFlight.click();
	}

}

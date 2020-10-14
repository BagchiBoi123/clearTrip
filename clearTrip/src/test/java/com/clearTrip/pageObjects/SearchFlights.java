package com.clearTrip.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SearchFlights {
	
	WebDriver driver;
	
	public SearchFlights(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public static final By cityFrom =By.xpath("//input[@id='FromTag']");
	
	public static final By cityTo =By.xpath("//input[@id='ToTag']");
	
	
	
	@FindBy(xpath="//input[@id='FromTag']")
	WebElement fromCity;
	
	
	@FindBy(xpath="//input[@id='ToTag']")
	WebElement toCity;
	
	
	@FindBy(xpath="//input[@id='RoundTrip']")
	WebElement roundTrip;
	
	@FindBy(xpath="//select[@id='Adults']")
	WebElement numberOfAdults;
	
	
	@FindBy(xpath="//select[@id='Childrens']")
	WebElement numberOfChildren;
	
	@FindBy(xpath="//select[@id='Infants']")
	WebElement numberOfInfants;
	
	@FindBy(xpath="//input[@id='SearchBtn']")
	WebElement searchFlights;
	
	@FindBy(xpath="//p[contains(text(),'Afternoon')][1]")
	WebElement departureTime;
	
	
	
	public void selectFromCity(String from)
	{
		fromCity.sendKeys(from);
		fromCity.sendKeys(Keys.ENTER);
	}
	
	public void selectToCity(String to)
	{
		toCity.sendKeys(to);
		toCity.sendKeys(Keys.ENTER);
	}
	
	public void selectRoundTrip()
	{
		roundTrip.click();
	}
	
	public void selectDepartureTime()
	{
		departureTime.click();
	}
	
	
	public void selectNumberOfAdults(int adults)
	{
		Select selectAdults=new Select(numberOfAdults);
		selectAdults.selectByIndex(adults-1);
	}
	
	public void selectNumberOfChildren(int children)
	{
		Select selectChildren=new Select(numberOfChildren);
		selectChildren.selectByIndex(children);
	}
	
	public void selectNumberOfInfants(int infants)
	{
		Select selectInfants=new Select(numberOfInfants);
		selectInfants.selectByIndex(infants);
	}
	
	public void searchFlights()
	{
		searchFlights.click();
	}

}

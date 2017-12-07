package com.globant.training.automation.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightSearchResultPage extends BasePage {

	public FlightSearchResultPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@id=\"sortBar\"]/div/fieldset/label/select")
	private WebElement sortList;

	@FindBy(id = "flight-listing-container")
	private WebElement flightListContainer;

	@FindBy(id = "forcedChoiceNoThanks")
	private WebElement noThanksLink;
	
	@FindBy(tagName = "button")
	private List<WebElement> selectButtonList; 
	
	@FindBy(className = "duration-emphasis")
	private List<WebElement> flightDurationList; 
	
	@FindBy(linkText="Flight details and baggage fees")
	private List<WebElement> flightDetailsLinkList; 
	
	@FindBy(id = "flightModuleList")
	private WebElement flightModuleList;	
	
	public WebElement getFlightModuleList() {
		return flightModuleList;
	}

	public List<WebElement> getFlightDetailsLinkList() {
		return flightDetailsLinkList;
	}

	public WebElement getSortList() {
		return sortList;
	}

	public WebElement getFlightListContainer() {
		return flightListContainer;
	}

	public WebElement getNoThanksLink() {
		return noThanksLink;
	}	

	public List<WebElement> getSelectButtonList() {
		return selectButtonList;
	}
	
	public List<WebElement> getFlightDurationList() {
		
		ArrayList<WebElement> flightDurationListWithoutSpaces = new ArrayList<WebElement>();
		for (int i = 0; i<flightDurationList.size(); i++)
		{
			String durationText = flightDurationList.get(i).getText().trim();
			if(!durationText.isEmpty())
			{
				flightDurationListWithoutSpaces.add(flightDurationList.get(i));
			}			
		}
		return flightDurationListWithoutSpaces;
	}

	public FlightInformationPage selectFlights() {

		getWait().withTimeout(60, TimeUnit.SECONDS).until(ExpectedConditions.elementToBeClickable(sortList));		
		Select dropdown = new Select(sortList);
		dropdown.selectByIndex(2);
				
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		selectButtonList = flightListContainer.findElements(By.tagName("button"));
		
		getWait().until(ExpectedConditions.elementToBeClickable(selectButtonList.get(0))).click();
		getWait().until(ExpectedConditions.elementToBeClickable(sortList));
		dropdown = new Select(sortList);
		dropdown.selectByIndex(2);
				
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		selectButtonList = flightListContainer.findElements(By.tagName("button"));
		
		getWait().until(ExpectedConditions.elementToBeClickable(selectButtonList.get(2))).click();
		getWait().until(ExpectedConditions.elementToBeClickable(noThanksLink));
		noThanksLink.click();

		return new FlightInformationPage(getDriver());
	}
}
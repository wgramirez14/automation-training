package com.globant.training.automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FlightSearchDeparturePage extends BasePage {

	@FindBy(id = "flightModuleList")
	private WebElement flightDepartureCards;
	
	@FindBy(id = "flight-listing-container")
	private WebElement flightListingContainer;	
	
	
	public FlightSearchDeparturePage(WebDriver driver) {
		super(driver);
	}

	public WebElement getFlightDepartureCards() {
		return flightDepartureCards;
	}	

	public WebElement getFlightListingContainer() {
		return flightListingContainer;
	}
	

	public FlightSearchReturnPage selectFlightOption() {
		
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(flightListingContainer)));
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(flightDepartureCards)));
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("price-button-wrapper"))));
		
		List<WebElement> selectButtonList = flightDepartureCards.findElements(By.tagName("button"));
		WebElement selectButton = selectButtonList.get(0);
		getWait().until(ExpectedConditions.elementToBeClickable(selectButton));
		selectButton.click();
		
		return new FlightSearchReturnPage(getDriver());
	}
}
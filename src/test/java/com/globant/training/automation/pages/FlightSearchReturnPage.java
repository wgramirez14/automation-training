package com.globant.training.automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FlightSearchReturnPage extends BasePage {

	@FindBy(id = "flightModuleList")
	private WebElement flightReturnCards;
	
	@FindBy(id = "flight-listing-container")
	private WebElement flightListingContainer;	
	
	public FlightSearchReturnPage(WebDriver driver) {
		super(driver);
	}	

	public WebElement getFlightReturnCards() {
		return flightReturnCards;
	}

	public WebElement getFlightListingContainer() {
		return flightListingContainer;
	}
	

	public BookingDetailsPage selectFlightOption() {
		
		getWait().until(ExpectedConditions.visibilityOf(flightListingContainer));
		getWait().until(ExpectedConditions.visibilityOf(flightReturnCards));
		getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("button")));
		
		List<WebElement> selectButtonList = flightReturnCards.findElements(By.tagName("button"));		
		WebElement selectButton = selectButtonList.get(2);
		getWait().until(ExpectedConditions.elementToBeClickable(selectButton));
		selectButton.click();
		
		return new BookingDetailsPage(getDriver());
	}
}
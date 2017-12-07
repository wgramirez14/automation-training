package com.globant.training.automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HotelInformationPage extends BasePage {

	@FindBy(id = "hotel-name")
	private WebElement hotelName;
	
	@FindBy(className = "price")
	private WebElement hotelPrice;
	
	@FindBy(className = "star-rating")
	private WebElement starRating;
	
	@FindBy(className = "book-button-wrapper")
	private List<WebElement> bookButtonWrapper;
	
	
	public HotelInformationPage(WebDriver driver) {
		super(driver);
	}

	public WebElement getHotelName() {
		return hotelName;
	}

	public WebElement getHotelPrice() {
		return hotelPrice;
	}

	public WebElement getStarRating() {
		return starRating;
	}

	public List<WebElement> getBookButtonWrapper() {
		return bookButtonWrapper;
	}

	public FlightSearchDeparturePage selectRoomFlightOption() {

		getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("book-button-wrapper")));
		
		WebElement linkButton = bookButtonWrapper.get(0).findElement(By.tagName("a"));
		
		getWait().until(ExpectedConditions.elementToBeClickable(linkButton));
		linkButton.click();
		
		return new FlightSearchDeparturePage(getDriver());
	}
}
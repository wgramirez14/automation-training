package com.globant.training.automation.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BookingDetailsPage extends BasePage {

	@FindBy(className = "gt-add-btn")
	private WebElement addCarButton;
	
	@FindBy(id = "trip-flight-to")
	private WebElement tripFlightTo;
	
	@FindBy(id = "trip-flight-from")
	private WebElement tripFlightFrom;	
	
	@FindBy(id = "trip-flight-start")
	private WebElement tripFlightStartDate;	
	
	@FindBy(id = "trip-flight-end")
	private WebElement tripFlightEndDate;		
	
	@FindBy(id = "lx-gt-highlight")
	private WebElement addTransportationInfo;
	
	@FindBy(id = "departure-dates")
	private WebElement departureDates;	
	
	@FindBy(className = "btn-primary")
	private WebElement continueBookingButton;
	
	
	public BookingDetailsPage(WebDriver driver) {
		super(driver);
	}
	
	
	public WebElement getCarOptioButton() {
		return addCarButton;
	}

	public WebElement getAddTransportationInfo() {
		return addTransportationInfo;
	}

	public WebElement getTripFlightTo() {
		return tripFlightTo;
	}


	public WebElement getTripFlightFrom() {
		return tripFlightFrom;
	}
	
	public WebElement getDepartureDates() {
		return departureDates;
	}
	
	public WebElement getTripFlightStartDate() {
		return tripFlightStartDate;
	}


	public WebElement getTripFlightEndDate() {
		return tripFlightEndDate;
	}

	public WebElement getContinueBookingButton() {
		return continueBookingButton;
	}


	public void addCar() {
		
		getWait().until(ExpectedConditions.elementToBeClickable(addCarButton));
		addCarButton.click();
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(tripFlightTo)));
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(tripFlightFrom)));
		
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(By.id("trip-flight-to"))));
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(By.id("trip-flight-from"))));
	}


	public PackageCheckoutPage continueBooking() {
		
		getWait().until(ExpectedConditions.elementToBeClickable(continueBookingButton));
		continueBookingButton.click();
		
		return new PackageCheckoutPage(getDriver());
	}
}
package com.globant.training.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FlightInformationPage extends BasePage {

	public FlightInformationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "bookButton")
	private WebElement continueBookingButton;
	
	@FindBy(className = "tripTotalPrice")
	private WebElement tripTotalPrice;
	
	@FindBy(className = "flightSummary")
	private WebElement flightSummarySection;
	
	@FindBy(className = "priceGuarantee")
	private WebElement priceGuaranteeText;
	
	public WebElement getContinueBookingButton() {
		return continueBookingButton;
	}	

	public WebElement getTripTotalPrice() {
		return tripTotalPrice;
	}	

	public WebElement getFlightSummarySection() {
		return flightSummarySection;
	}	

	public WebElement getPriceGuaranteeText() {
		return priceGuaranteeText;
	}

	public FlightCheckoutPage continueBookingFlight() {

		for (String winHandle : getDriver().getWindowHandles()) {
			getDriver().switchTo().window(winHandle);
		}

		getDriver().navigate().refresh();

		getWait().until(ExpectedConditions.elementToBeClickable(continueBookingButton));
		continueBookingButton.click();
		
		return new FlightCheckoutPage(getDriver());
	}
}
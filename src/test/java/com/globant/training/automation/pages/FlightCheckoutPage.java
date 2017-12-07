package com.globant.training.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FlightCheckoutPage extends BasePage {

	public FlightCheckoutPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(className = "location-info")
	private WebElement locationInfoLabel;
	
	@FindBy(className = "date-info")
	private WebElement dateInfoLabel;
	
	@FindBy(className = "product-description")
	private WebElement productDescriptionLabel;
	
	@FindBy(id = "firstname[0]")
	private WebElement firstNameField;
	
	@FindBy(id = "lastname[0]")
	private WebElement lastNameField;	
	
	@FindBy(className = "billing-cardholder-name")
	private WebElement cardHolderNameField;
	
	@FindBy(id = "new_cc_security_code")
	private WebElement ccSecureCodeField;	
	
	@FindBy(className = "cc-master-card")
	private WebElement mastercardLogo;	
	
	@FindBy(className = "complete-booking")
	private WebElement completeBookingButton;		
	

	public WebElement getLocationInfoLabel() {
		return locationInfoLabel;
	}

	public WebElement getDateInfoLabel() {
		return dateInfoLabel;
	}

	public WebElement getProductDescriptionLabel() {
		return productDescriptionLabel;
	}

	public WebElement getFirstNameField() {
		return firstNameField;
	}

	public WebElement getLastNameField() {
		return lastNameField;
	}

	public WebElement getCardHolderNameField() {
		return cardHolderNameField;
	}	

	public WebElement getCcSecureCodeField() {
		return ccSecureCodeField;
	}

	public WebElement getMastercardLogo() {
		return mastercardLogo;
	}

	public WebElement getCompleteBookingButton() {
		return completeBookingButton;
	}	
}
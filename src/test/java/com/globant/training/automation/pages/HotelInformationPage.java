package com.globant.training.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HotelInformationPage extends BasePage {

	@FindBy(id = "hotel-name")
	private WebElement hotelName;
	
	@FindBy(className = "price")
	private WebElement hotelPrice;
	
	public HotelInformationPage(WebDriver driver) {
		super(driver);
	}

	public WebElement getHotelName() {
		return hotelName;
	}

	public WebElement getHotelPrice() {
		return hotelPrice;
	}	
}
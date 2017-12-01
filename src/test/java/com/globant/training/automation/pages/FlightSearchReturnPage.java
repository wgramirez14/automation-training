package com.globant.training.automation.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightSearchReturnPage extends BasePage {

	@FindBy(id = "flightModuleList")
	private WebElement flightReturnCards;
	
	@FindBy(id = "flight-listing-container")
	private WebElement flightListingContainer;	
	
	@FindBy(id = "flightModuleList")
	private WebElement flightModuleList;
	
	public FlightSearchReturnPage(WebDriver driver) {
		super(driver);
	}	

	public WebElement getFlightReturnCards() {
		return flightReturnCards;
	}

	public WebElement getFlightListingContainer() {
		return flightListingContainer;
	}	

	public WebElement getFlightModuleList() {
		return flightModuleList;
	}
	

	public BookingDetailsPage selectFlightOption() {
		
		/*getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(flightListingContainer)));
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(flightReturnCards)));*/
		new WebDriverWait(getDriver(), 30).until((ExpectedCondition<Boolean>) wd ->
        ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		getWait().until(ExpectedConditions.visibilityOf(flightModuleList));
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.className("price-button-wrapper"))));
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.id("flightModuleList"))));
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.id("flight-listing-container"))));
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("flight-listing-container"))));
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("flightModuleList"))));
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("button"))));
		getWait().withTimeout(30, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("button")));
		
		List<WebElement> selectButtonList = flightReturnCards.findElements(By.tagName("button"));		
		
		/*for(int i = 0; i<selectButtonList.size(); i++) {
			
			getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(selectButtonList.get(i))));
		}*/
		
		WebElement selectButton = selectButtonList.get(2);
		//getWait().until(ExpectedConditions.elementToBeClickable(selectButton));
		int attempts = 0;
		while(attempts < 10) {
            try {
            	getWait().until(ExpectedConditions.elementToBeClickable(selectButton));
            	selectButton.click();
                //result = true;
                break;
            } catch(StaleElementReferenceException e) {
            }
            attempts++;
        }
		
		
		return new BookingDetailsPage(getDriver());
	}
}
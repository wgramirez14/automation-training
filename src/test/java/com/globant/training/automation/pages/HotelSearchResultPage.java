package com.globant.training.automation.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HotelSearchResultPage extends BasePage {

	@FindBy(id = "hotelResultTitle")
	private WebElement hotelResultTitle;
	
	@FindBy(id = "star5-label")
	private WebElement star5labelFilter;	
	
	@FindBy(id = "inpHotelNameMirror")
	private WebElement propertyNameField;
	
	@FindBy(id = "airline_fees_link")
	private WebElement airlineFeesLink;
	
	@FindBy(id = "changeSearchWizard")
	private WebElement changeSearchWizardLink;
	
	@FindBy(xpath = "//*[@id=\"sortContainer\"]/div/div/div[2]/div/fieldset/ul/li[3]/button")
	private WebElement priceSortButton;
	
	@FindBy(className = "flex-listing")
	private WebElement hotelLinks;
	
	@FindBy(tagName = "a")
	private List<WebElement> hotelLinkList; 
	
	@FindBy(tagName = "star3")
	private WebElement star3CheckBox;
	
	@FindBy(tagName = "star4")
	private WebElement star4CheckBox;
	
	@FindBy(tagName = "star5")
	private WebElement star5CheckBox;
	
	@FindBy(className = "starRating")
	private List<WebElement> starRatingList;	
	
	
		
	public HotelSearchResultPage(WebDriver pDriver) {
		super(pDriver);
	}
	
	public WebElement getStar5labelFilter() {
		return star5labelFilter;
	}

	public WebElement getHotelResultTitle() {
		return hotelResultTitle;
	}	

	public WebElement getPropertyNameField() {
		return propertyNameField;
	}	

	public WebElement getAirlineFeesLink() {
		return airlineFeesLink;
	}

	public WebElement getChangeSearchWizardLink() {
		return changeSearchWizardLink;
	}

	public WebElement getPriceSortButton() {
		return priceSortButton;
	}	

	public WebElement getHotelLinks() {
		return hotelLinks;
	}	

	public List<WebElement> getHotelLinkList() {
		return hotelLinkList;
	}	

	public WebElement getStar3CheckBox() {
		return star3CheckBox;
	}

	public WebElement getStar4CheckBox() {
		return star4CheckBox;
	}

	public WebElement getStar5CheckBox() {
		return star5CheckBox;
	}
	
	public List<WebElement> getStarRatingList() {
		return starRatingList;
	}
	

	public List<WebElement> sortByPrice() {
		
		getWait().until(ExpectedConditions.elementToBeClickable(priceSortButton)).click();
		
		getWait().withTimeout(60, TimeUnit.SECONDS).until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("article"))));
		
		
		getWait().until(ExpectedConditions.visibilityOf(hotelLinks));
		
		return hotelLinks.findElements(By.tagName("a"));		
	}

	public void selectFirstHotelAtLeast3Stars() {
		
		for (int i = 0; i<starRatingList.size(); i++)
		{			
			String starRating = starRatingList.get(i).getText().substring(0, 3);
			System.out.println(starRating);
			
			if(Double.parseDouble(starRating) >= 3)
			{
				starRatingList.get(i).click();
				break;
			}
		}
	}	
}
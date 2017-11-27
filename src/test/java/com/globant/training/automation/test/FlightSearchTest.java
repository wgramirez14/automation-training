package com.globant.training.automation.test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.globant.training.automation.pages.FlightCheckoutPage;
import com.globant.training.automation.pages.FlightInformationPage;
import com.globant.training.automation.pages.FlightSearchResultPage;
import com.globant.training.automation.pages.HotelInformationPage;
import com.globant.training.automation.pages.HotelSearchResultPage;
import com.globant.training.automation.pages.TravelocityHomePage;

public class FlightSearchTest extends BaseTest {

	//@Test(priority=1)
	public void testFlightSearchWithoutHotel() {
		TravelocityHomePage homePage = getTravelocityHomePage();
		
		FlightSearchResultPage flightSearchResultPage = homePage.searchFlight("java");					
		Assert.assertNotNull(flightSearchResultPage.getSortList());
		Assert.assertNotNull(flightSearchResultPage.getSelectButtonList());
		Assert.assertNotNull(flightSearchResultPage.getFlightDurationList());
		Assert.assertNotNull(flightSearchResultPage.getFlightDetailsLinkList());			
		
		FlightInformationPage flightInformationPage = flightSearchResultPage.selectFlights();
		List<WebElement> sortedflightDurationList = flightSearchResultPage.getFlightDurationList();
		Assert.assertTrue(isListSorted(sortedflightDurationList));
		Assert.assertNotNull(flightInformationPage.getTripTotalPrice());
		Assert.assertNotNull(flightInformationPage.getFlightSummarySection());
		Assert.assertNotNull(flightInformationPage.getPriceGuaranteeText());		
		
		FlightCheckoutPage flightCheckout = flightInformationPage.continueBookingFlight();
		Assert.assertNotNull(flightCheckout.getLocationInfoLabel());
		Assert.assertNotNull(flightCheckout.getDateInfoLabel());
		Assert.assertNotNull(flightCheckout.getProductDescriptionLabel());
		Assert.assertNotNull(flightCheckout.getFirstNameField());
		Assert.assertNotNull(flightCheckout.getLastNameField());
		Assert.assertNotNull(flightCheckout.getCardHolderNameField());
		Assert.assertNotNull(flightCheckout.getCcSecureCodeField());
		Assert.assertNotNull(flightCheckout.getMastercardLogo());
		Assert.assertNotNull(flightCheckout.getCompleteBookingButton());	
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority=2)
	public void testFlightSearchWithHotel() {
		
		TravelocityHomePage homePage = getTravelocityHomePage();
		HotelSearchResultPage hotelSearchResultPage = homePage.searchFlightAndHotel();
		Assert.assertNotNull(hotelSearchResultPage.getHotelResultTitle());	
		Assert.assertNotNull(hotelSearchResultPage.getStar5labelFilter());
		Assert.assertNotNull(hotelSearchResultPage.getPropertyNameField());		
		Assert.assertTrue(hotelSearchResultPage.getAirlineFeesLink().getText().contains("baggage fees"));
		Assert.assertTrue(hotelSearchResultPage.getAirlineFeesLink().getText().contains("Baggage fees link opens in a new tab"));
		Assert.assertNotNull(hotelSearchResultPage.getChangeSearchWizardLink());				
		Assert.assertTrue(isHotelLinkListSorted(hotelSearchResultPage.sortByPrice()));		
		
		HotelInformationPage hotelInformationPage  = hotelSearchResultPage.selectFirstHotelAtLeast3Stars ();
		Assert.assertTrue(isInformationMatched(hotelSearchResultPage.getHotelDetails(), hotelInformationPage));
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private boolean isInformationMatched(Hashtable<String, String> hotelDetails, HotelInformationPage hotelInformationPage) {
		
		String hotelNameToCompare = hotelInformationPage.getHotelName().getText().trim();
		String hotelPriceToCompare = hotelInformationPage.getHotelPrice().getText().trim();
		
		if(!hotelDetails.get("hotelName").equals(hotelNameToCompare)) {
			
			return false;
		}
		
		else if (!hotelDetails.get("hotelPrice").equals(hotelPriceToCompare)) {
			
			return false;
		}
		
		return true;
	}

	private boolean isHotelLinkListSorted(List<WebElement> hotelLinkList) {
		
		List<Double> prices = new ArrayList<Double> ();
		
		for(int i = 0; i<hotelLinkList.size(); i++) 
		{
			String link = hotelLinkList.get(i).getAttribute("href");
			
			if(link.contains("exp_dp=")) {
				
				//System.out.println(hotelLinkList.get(i).getAttribute("href"));
				link = link.substring(link.indexOf("exp_dp=") + 7);
				link = link.substring(0, link.indexOf("&"));
				prices.add(Double.parseDouble(link));
				//System.out.println(link);
			}			
		}
		
		for (int i = 0; i<prices.size()-1; i++)
		{
			if(prices.get(i) > prices.get(i+1)) 
			{
				return false;
			}
		}
		
		return true;
	}

	private boolean isListSorted(List<WebElement> sortedflightDurationList) {
		
		for (int i = 1; i<sortedflightDurationList.size()-1; i++)
		{
			String currentDuration = "0"+sortedflightDurationList.get(i).getText().trim().replace("h",":").replace("m", "").replace(" ", "");
			String nextDuration = "0"+sortedflightDurationList.get(i+1).getText().trim().replace("h",":").replace("m", "").replace(" ", "");
			
			int amountMinuteDigits = 0;
			for(int j = currentDuration.length()-1; j>0; j--)
			{				
				if(currentDuration.charAt(j) == ':' && amountMinuteDigits==1)
				{
					currentDuration = currentDuration.substring(0, j+1) + "0" + currentDuration.charAt(j+1);
					break;
				}
				
				else
				{
					amountMinuteDigits++;
				}
			}
			
			amountMinuteDigits = 0;
			for(int j = nextDuration.length()-1; j>0; j--)
			{
				if(nextDuration.charAt(j) == ':' && amountMinuteDigits==1)
				{
					nextDuration = nextDuration.substring(0, j+1) + "0" + nextDuration.charAt(j+1);
					break;
				}
				
				else
				{
					amountMinuteDigits++;
				}
			}
			
			LocalTime duration1 = LocalTime.parse(currentDuration);
			LocalTime duration2 = LocalTime.parse(nextDuration);
			
			if(Duration.between(duration1, duration2).isNegative())
				return false;
		}
		
		return true;
	}
}
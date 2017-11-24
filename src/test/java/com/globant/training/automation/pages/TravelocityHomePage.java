package com.globant.training.automation.pages;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class TravelocityHomePage extends BasePage {

	public TravelocityHomePage(WebDriver driver) {
		super(driver);
		driver.get("https://www.travelocity.com/");
	}

	@FindBy(id = "tab-flight-tab-hp")
	private WebElement flightOptionButton;
	
	@FindBy(id = "tab-package-tab-hp")
	private WebElement flightAndHotelOptionButton;

	@FindBy(id = "flight-type-roundtrip-label-hp-flight")
	private WebElement roundtripFlightTypeLabel;

	@FindBy(id = "flight-origin-hp-flight")
	private WebElement flyingFromInput;
	
	@FindBy(id = "package-origin-hp-package")
	private WebElement packageFlyingFromInput;	

	@FindBy(id = "flight-destination-hp-flight")
	private WebElement flyingToInput;
	
	@FindBy(id = "package-destination-hp-package")
	private WebElement packageFlyingToInput;	

	@FindBy(id = "flight-departing-hp-flight")
	private WebElement departingDatePicker;
	
	@FindBy(id = "package-departing-hp-package")
	private WebElement packageDepartingDatePicker;	

	@FindBy(id = "flight-returning-hp-flight")
	private WebElement returningDatePicker;
	
	@FindBy(id = "package-returning-hp-package")
	private WebElement packageReturningDatePicker;	

	@FindBy(className = "datepicker-next")
	private WebElement datePickerNextButton;

	@FindBy(className = "datepicker-cal")
	private WebElement datePickerDropdown;

	@FindBy(id = "flight-adults-hp-flight")
	private WebElement amountAdultsList;
	
	@FindBy(id = "package-1-adults-hp-package")
	private WebElement packageAmountAdultsList;	

	@FindBy(xpath = "//*[@id=\"gcw-flights-form-hp-flight\"]/div[7]/label/button")
	private WebElement searchButton;
	
	@FindBy(id = "search-button-hp-package")
	private WebElement packageSearchButton;	

	public WebElement getFlightOptionButton() {
		return flightOptionButton;
	}	

	public WebElement getFlightAndHotelOptionButton() {
		return flightAndHotelOptionButton;
	}

	public WebElement getRoundtripFlightTypeLabel() {
		return roundtripFlightTypeLabel;
	}

	public WebElement getFlyingFromInput() {
		return flyingFromInput;
	}

	public WebElement getFlyingToInput() {
		return flyingToInput;
	}

	public WebElement getDepartingDatePicker() {
		return departingDatePicker;
	}

	public WebElement getReturningDatePicker() {
		return returningDatePicker;
	}

	public WebElement getAmountAdultsList() {
		return amountAdultsList;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}

	public WebElement getDatePickerDropdown() {
		return datePickerDropdown;
	}		

	public WebElement getPackageFlyingFromInput() {
		return packageFlyingFromInput;
	}		

	public WebElement getPackageFlyingToInput() {
		return packageFlyingToInput;
	}	

	public WebElement getPackageDepartingDatePicker() {
		return packageDepartingDatePicker;
	}

	public WebElement getPackageReturningDatePicker() {
		return packageReturningDatePicker;
	}

	public WebElement getPackageAmountAdultsList() {
		return packageAmountAdultsList;
	}	

	public WebElement getPackageSearchButton() {
		return packageSearchButton;
	}
	

	public FlightSearchResultPage searchFlight(String busqueda) {

		flightOptionButton.click();
		roundtripFlightTypeLabel.click();

		getWait().until(ExpectedConditions.elementToBeClickable(flyingFromInput));		
		flyingFromInput.sendKeys("LAS");
		getWait().until(ExpectedConditions.elementToBeClickable(flyingToInput));
		flyingToInput.sendKeys("LAX");

		getWait().until(ExpectedConditions.elementToBeClickable(departingDatePicker));
		departingDatePicker.click();
		getWait().until(ExpectedConditions.elementToBeClickable(datePickerNextButton));
		datePickerNextButton.click();
		datePickerNextButton.click();

		LocalDate futureDate = LocalDate.now().plusMonths(2);
		List<WebElement> columns = datePickerDropdown.findElements(By.className("datepicker-cal-date"));

		for (WebElement cell : columns) {
			if (cell.getText().equals(futureDate.getDayOfMonth() + "") && cell.isEnabled()) {
				getWait().until(ExpectedConditions.elementToBeClickable(cell));
				cell.click();
				break;
			}
		}

		getWait().until(ExpectedConditions.elementToBeClickable(returningDatePicker));
		returningDatePicker.click();

		futureDate = futureDate.plusDays(10);
		columns = datePickerDropdown.findElements(By.className("datepicker-cal-date"));
		for (WebElement cell : columns) {
			String text = cell.getText();
			Boolean enabled = cell.isEnabled();
			if (text.equals(futureDate.getDayOfMonth() + "") && enabled) {
				getWait().until(ExpectedConditions.elementToBeClickable(cell));
				cell.click();
				break;
			}
		}

		getWait().until(ExpectedConditions.elementToBeClickable(amountAdultsList));
		Select dropdown = new Select(amountAdultsList);
		dropdown.selectByIndex(0);

		getWait().until(ExpectedConditions.elementToBeClickable(searchButton));
		searchButton.click();

		return new FlightSearchResultPage(getDriver());
	}

	public HotelSearchResultPage searchFlightAndHotel() {
		
		getWait().until(ExpectedConditions.elementToBeClickable(flightAndHotelOptionButton));
		flightAndHotelOptionButton.click();
		
		getWait().until(ExpectedConditions.elementToBeClickable(packageFlyingFromInput));		
		packageFlyingFromInput.sendKeys("LAS");
		getWait().until(ExpectedConditions.elementToBeClickable(packageFlyingToInput));
		packageFlyingToInput.sendKeys("LAX");
		
		getWait().until(ExpectedConditions.elementToBeClickable(packageDepartingDatePicker));
		
		packageDepartingDatePicker.click();
		getWait().until(ExpectedConditions.elementToBeClickable(datePickerNextButton));
		datePickerNextButton.click();
		datePickerNextButton.click();

		LocalDate futureDate = LocalDate.now().plusMonths(2);
		List<WebElement> columns = datePickerDropdown.findElements(By.className("datepicker-cal-date"));

		for (WebElement cell : columns) {
			if (cell.getText().equals(futureDate.getDayOfMonth() + "") && cell.isEnabled()) {
				getWait().until(ExpectedConditions.elementToBeClickable(cell));
				cell.click();
				break;
			}
		}

		getWait().until(ExpectedConditions.elementToBeClickable(packageReturningDatePicker));
		packageReturningDatePicker.click();

		futureDate = futureDate.plusDays(13);
		columns = datePickerDropdown.findElements(By.className("datepicker-cal-date"));
		for (WebElement cell : columns) {
			String text = cell.getText();
			Boolean enabled = cell.isEnabled();
			if (text.equals(futureDate.getDayOfMonth() + "") && enabled) {
				getWait().until(ExpectedConditions.elementToBeClickable(cell));
				cell.click();
				break;
			}
		}

		getWait().until(ExpectedConditions.elementToBeClickable(packageAmountAdultsList));
		Select dropdown = new Select(packageAmountAdultsList);
		dropdown.selectByIndex(0);
		
		getWait().until(ExpectedConditions.elementToBeClickable(packageSearchButton));
		packageSearchButton.click();
		
		getWait().withTimeout(60, TimeUnit.SECONDS).until(ExpectedConditions.presenceOfElementLocated(By.className("section-header-main")));

		return new HotelSearchResultPage(getDriver());
	}
}
package com.globant.training.automation.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.globant.training.automation.pages.MyDriver;
import com.globant.training.automation.pages.TravelocityHomePage;

public class BaseTest {

	MyDriver myDriver;
	
	private TravelocityHomePage travelocityHomePage;
	
	@BeforeMethod(alwaysRun=true)
	@Parameters({"browser"})
	public void beforeSuite(String browser){
		myDriver = new MyDriver(browser);
		travelocityHomePage = new TravelocityHomePage(myDriver.getDriver());
	}
	
	@AfterMethod(alwaysRun=true)
	public void afterSuite(){
		travelocityHomePage.dispose();
		myDriver.getDriver().quit();
	}
	
	public TravelocityHomePage getTravelocityHomePage(){
		return travelocityHomePage;
	}
}
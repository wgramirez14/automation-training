package com.globant.training.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class MyDriver {

	private WebDriver driver;

	public MyDriver(String browser) {
		switch (browser) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver",
					"C:\\eclipse-jee-oxygen-R-win32-x86_64\\automationResources\\drivers\\geckodriver.exe");

			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("disable-infobars");
			firefoxOptions.addArguments("test-type");
			firefoxOptions.addArguments("--start-maximized");
			firefoxOptions.addArguments("--disable-web-security");
			firefoxOptions.addArguments("--allow-running-insecure-content");

			driver = new FirefoxDriver();
			break;
		case "chrome":
			System.setProperty("webdriver.chrome.driver",
					"C:\\eclipse-java-oxygen-1a-win32-x86_64\\eclipse\\automationResources\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			options.addArguments("test-type");
			options.addArguments("--start-maximized");
			options.addArguments("--disable-web-security");
			options.addArguments("--allow-running-insecure-content");
			driver = new ChromeDriver(options);
			break;
		default:
			break;
		}
	}

	public WebDriver getDriver() {
		return this.driver;
	}
}
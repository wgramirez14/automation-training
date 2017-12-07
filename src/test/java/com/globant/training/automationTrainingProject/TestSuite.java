package com.globant.training.automationTrainingProject;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestSuite
{
	@DataProvider(name = "numbersToMultiply")
	public Object[][] numbersToBeMultiplied(){
		return new Object [][]{
			{ThreadLocalRandom.current().nextInt(1, 1000 + 1), ThreadLocalRandom.current().nextInt(1, 1000 + 1)},
			{ThreadLocalRandom.current().nextInt(1, 1000 + 1), ThreadLocalRandom.current().nextInt(1, 1000 + 1)},
			{ThreadLocalRandom.current().nextInt(1, 1000 + 1), ThreadLocalRandom.current().nextInt(1, 1000 + 1)}
		};
	}
	
	@DataProvider(name = "stringValues")
	public Object[][] stringValues (){
		return new Object [][]{
			{"Hola", "Mundo", "Feliz"},
			{"Automation","Training","Test"},
			{"We","are","ready"}
		};
	}
		
	@Test(dataProvider = "numbersToMultiply", groups = { "Smoke", "Regression" }, priority = 1)
	public void multiplyNumbers(Integer n1, Integer n2){
		System.out.println ("Test de Multiplicacion: " + n1 + " x " + n2 + " = " + n1*n2);
	}
	
	@Test(dataProvider = "stringValues", groups = { "Smoke", "Regression" }, priority = 2)
	public void concatenateStrings(String s1, String s2, String s3){
		System.out.println (s1 + " " + s2 + " " + s3);
	}
	
	@Parameters({"date"})
	@Test(groups = {"Smoke"}, priority = 3)
	public void printDate(String date){
		
		LocalDate localDate = LocalDate.parse(date);
		System.out.println ("Date: " + localDate);
	}
	
	@Parameters({"ambiente"})
	@Test(groups = {"Smoke"}, priority = 4)
	public void ambiente(String ambiente){
		int n1 = ThreadLocalRandom.current().nextInt(1, 1000 + 1), n2 = ThreadLocalRandom.current().nextInt(1, 1000 + 1);
		
		if(ambiente.equals("AMBIENTE1")) {
			
			System.out.println ("Multiplicación: " + n1 + " x " + n2 + " = " + (n1*n2));
		}
		
		else if(ambiente.equals("AMBIENTE2")) {
			
			System.out.println ("Resta: " + n1 + " - " + n2 + " = " + (n1-n2));
		}
		
		else {
			
			System.out.println ("Parámetro no reconocido");
		}		
	}	
	
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite (){
		System.out.println ("Before Suite");
	}
	
	@BeforeGroups(alwaysRun = true)
	public void beforeGroups (){
		System.out.println ("Before Groups");
	}
	
	@BeforeClass(alwaysRun = true)
	public void beforeClass (){
		System.out.println ("Before Class");
	}
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod (){
		System.out.println ("Before Method");
	}
	
	@AfterMethod(alwaysRun = true)
	public void afterMethod (){
		System.out.println ("After Method");
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass (){
		System.out.println ("After Class");
	}
	
	@AfterSuite(alwaysRun = true)
	public void afterSuite (){
		System.out.println ("After Suite");
	}	
	
	@AfterGroups(alwaysRun = true)
	public void afterGroups (){
		System.out.println ("After Groups");
	}
}

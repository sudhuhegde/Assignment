package com.sudarshan.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.sudarshan.driver.InItDriver;

public abstract class SuiteSetup {
	protected static WebDriver driver;
	protected static final String TEST_URL ="http://ec2-54-90-154-147.compute-1.amazonaws.com";
	
	@BeforeSuite
	public  void beforeSuite(){
		
		driver = InItDriver.getDriver();
		driver.manage().window().maximize();
		driver.get(TEST_URL);
	}
	
	@AfterSuite
	public void afterSuite(){
		if(driver != null){
			driver.quit();
		}
	}

}

package com.sudarshan.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sudarshan.driver.DriverUtility;

public class HomePage {
	private  WebDriver driver;


	public HomePage(WebDriver driver) {
		this.driver = driver;
		//This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText="Log in")
	WebElement login;
	
	


	public void openLoginPage(){

		System.out.println("Opening login page");
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", login);
		DriverUtility.sleep(1);
		login.click();
	}


	public boolean verifyPostIsVisible(String postTitle){
		return driver.findElement(By.linkText(postTitle)).isDisplayed();
	}


	public void openAPost(String title) {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.linkText(title)));
		DriverUtility.sleep(1);
		driver.findElement(By.linkText(title)).click();
		
	}
	
}

package com.sudarshan.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.sudarshan.testcases.SuiteSetup;

public class BlogPage extends BasePage{
	private WebDriver driver;
	public BlogPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="submit")
	WebElement postComment;
	
	@FindBy(xpath="//div/p/Strong")
	WebElement boldText;
	
	public void waitTillBlogOpens(int timeout){
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(postComment));
		SuiteSetup.sleep(2);
	}
	
	public boolean verifyContentInBold(String content){
		return driver.findElement(By.xpath("//strong[contains(text(),'"+content+"')]")).isDisplayed();
			 
		
	}

}

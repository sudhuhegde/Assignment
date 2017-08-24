package com.sudarshan.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

	private WebDriver driver;
	public BasePage(WebDriver driver) {

		this.driver =driver;
		//This initElements method will create all WebElements

		PageFactory.initElements(driver, this);
	}

	public void setTextUsingActions(String text, WebElement element){
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.click();
		actions.sendKeys(text);
		actions.build().perform();
	}
}

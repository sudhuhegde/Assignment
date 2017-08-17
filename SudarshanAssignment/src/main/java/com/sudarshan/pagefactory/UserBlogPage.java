package com.sudarshan.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sudarshan.testcases.SuiteSetup;

public class UserBlogPage extends SuiteSetup {

	@FindBy(className="wp-menu-name")
	WebElement posts;

	@FindBy(xpath="span[text()='New']")
	WebElement newButton;

	@FindBy(id="title-prompt-text")
	WebElement newTitle;

	@FindBy(id="tinymce")
	WebElement newContent;

	@FindBy(css=".mce-ico.mce-i-bold")
	WebElement boldButton;

	@FindBy(id="publish")
	WebElement publishButton;
	
	@FindBy(linkText="View post")
	WebElement viewPostLink;
	
	@FindBy(className="display-name")
	WebElement displaName;
	@FindBy(id="wp-admin-bar-logout")
	WebElement logout;
	

	public void waitUntilPageLogIn(int maxTime){
		WebDriverWait wait = new WebDriverWait(driver, maxTime);
		wait.until(ExpectedConditions.titleIs("Dashboard ‹ user's Blog! — WordPress"));
	}

	public void createNewPost(String title , String content, int maxWait){
		newButton.click();
		WebDriverWait wait  = new WebDriverWait(driver, maxWait);
		wait.until(ExpectedConditions.elementToBeClickable(newTitle));
		newTitle.sendKeys(title);
		newContent.sendKeys(content);
	}

	public void addContentToPost(String content , boolean isBold){
		if(isBold){
			boldButton.click();
		}
		newContent.sendKeys(content);
	}

	public void publishPost(){
		publishButton.click();
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(viewPostLink));
	}
	
	public void logOut(){
		
		
		Actions action = new Actions(driver);
		action.moveToElement(displaName).click(logout).build().perform();
	}
}

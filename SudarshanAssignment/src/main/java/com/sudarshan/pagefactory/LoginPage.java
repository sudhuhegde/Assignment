package com.sudarshan.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
	
	@FindBy(id="user_login")
	WebElement username;

	@FindBy(id="user_pass")
	WebElement password;
	
	@FindBy(id="wp-submit")
	WebElement login;
	
	public void waitTillLoginPageOpens(int maxWait){
		WebDriverWait wait = new WebDriverWait(driver, maxWait);
		wait.until(ExpectedConditions.elementToBeClickable(username));
		
	}
	
	public void login(String userName, String pass){
		username.sendKeys(userName);
		password.sendKeys(pass);
		login.submit();	
	}
}

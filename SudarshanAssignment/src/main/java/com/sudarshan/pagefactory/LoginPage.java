package com.sudarshan.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sudarshan.driver.DriverUtility;

public class LoginPage extends BasePage {
	private WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

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
		System.out.println("Logging in");
		DriverUtility.sleep(3);
		username.sendKeys(userName);
		password.sendKeys(pass);
		login.submit();	
	}
}

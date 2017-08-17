package com.sudarshan.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
@FindBy(linkText="Log in")
WebElement login;


public void openLoginPage(){
	login.click();
	
}
}

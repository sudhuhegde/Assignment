package com.sudarshan.testcases;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.sudarshan.driver.InItDriver;
import com.sudarshan.pagefactory.BlogPage;
import com.sudarshan.pagefactory.HomePage;
import com.sudarshan.pagefactory.LoginPage;
import com.sudarshan.pagefactory.UserBlogPage;

public abstract class SuiteSetup {
	protected static WebDriver driver;
	protected static final String TEST_URL ="http://ec2-54-90-154-147.compute-1.amazonaws.com";
	protected static final String USER_NAME = "test_user";
	protected static final String PASSWORD = "testuser@123";
	
	protected static UserBlogPage userBlogPage ;
	protected static HomePage homePage ;
	protected static LoginPage loginPage ;
	protected static BlogPage blogPage;
	@BeforeSuite
	public  void beforeSuite(){
		
		driver = InItDriver.getDriver();
		driver.manage().window().maximize();
		//Initialize page factory
		userBlogPage = new UserBlogPage(driver);
		homePage =new HomePage(driver);
		loginPage = new LoginPage(driver);
		blogPage = new BlogPage(driver);
		
		
	}
	
	@BeforeMethod
	public void beforeMethod(){
		loadURL();
	}
	
	@AfterSuite
	public void afterSuite(){
		if(driver != null){
			driver.quit();
		}
	}
	
	protected void loadURL(){
		driver.get(TEST_URL);
	}


	
	public static int getRandom(){
		Random rand = new Random();

		int  n = rand.nextInt(50) + 1;
		return n;
	}
}

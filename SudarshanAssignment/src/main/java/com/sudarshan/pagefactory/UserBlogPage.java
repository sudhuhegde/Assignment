package com.sudarshan.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.sudarshan.testcases.SuiteSetup;

public class UserBlogPage extends BasePage {
	private WebDriver driver;

	public UserBlogPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	@FindBy(className="wp-menu-name")
	WebElement posts;

	@FindBy(xpath="//span[text()='New']")
	WebElement newButton;

	@FindBy(id="titlewrap")
	WebElement newTitle;

	@FindBy(id="tinymce")
	WebElement newContent;

	@FindBy(css=".mce-ico.mce-i-bold")
	WebElement boldButton;

	@FindBy(id="publish")
	WebElement publishButton;

	@FindBy(linkText="View post")
	WebElement viewPostLink;

	@FindBy(id="wp-admin-bar-my-account")
	WebElement displaName;
	@FindBy(id="wp-admin-bar-logout")
	WebElement logout;

	@FindBy(id="backtoblog")
	WebElement backToLog;


	public void waitUntilPageLogIn(int maxTime){
		WebDriverWait wait = new WebDriverWait(driver, maxTime);
		wait.until(ExpectedConditions.titleIs("Dashboard ‹ user's Blog! — WordPress"));
	}
	/**
	 * This will not publish , It will only create a post
	 * @param title
	 * @param content
	 * @param maxWait
	 */
	public void createNewPost(String title , String content, int maxWait){
		System.out.println("Creating new post");
		newButton.click();
		WebDriverWait wait  = new WebDriverWait(driver, maxWait);
		wait.until(ExpectedConditions.elementToBeClickable(newTitle));
		SuiteSetup.sleep(2);

		setTextUsingActions(title, newTitle);
		addContentToPost(content, false);
		System.out.println("");	

	}

	public void addContentToPost(String content , boolean isBold){

		System.out.println("adding content to post");
		WebElement iframe = driver.findElement(By.id("content_ifr"));
		driver.switchTo().frame(iframe);
		if(isBold){
			content = "<strong>"+content+"<strong>";
		}
		JavascriptExecutor myExecutor = ((JavascriptExecutor) driver);


		myExecutor.executeScript("arguments[0].innerHTML = '"+content+"';", newContent);


		//newContent.sendKeys(content);
		driver.switchTo().defaultContent();
	}

	public void publishPost(){
		System.out.println("publishing post");
		publishButton.click();
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(viewPostLink));
		//SuiteSetup.sleep(5);
	}

	public void logOut(){
		System.out.println("Logging out");
		SuiteSetup.sleep(5);
		Mouse mouse =((HasInputDevices)driver).getMouse();
		mouse.mouseMove(((Locatable)displaName).getCoordinates());
		SuiteSetup.sleep(3);
		logout.click();
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(backToLog));


	}

	public boolean isLoggedIn(){
		boolean loggedIn = false;
		try{
			loggedIn = displaName.isDisplayed();
			System.out.println("user has logged in");
		}
		catch(ElementNotFoundException | NoSuchElementException e){
			System.out.println("Not logged in");
			loggedIn= false;
		}
		return loggedIn;
	}
}

package com.sudarshan.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sudarshan.driver.DriverUtility;

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
	
	@FindBy(id="comment")
	WebElement commentBody;
	
	@FindBy(id="author")
	WebElement commenterName;
	
	@FindBy(id="email")
	WebElement commenterEmail;
	
	@FindBy(className="comments-title")
	WebElement commentsTitle;
	
	
	@FindBy(xpath="//div[contains(@class, 'comment-content')]/p")
	WebElement commentTextElement;
	
	public void waitTillBlogOpens(int timeout){
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(postComment));
		DriverUtility.sleep(2);
	}
	
	public boolean verifyContentInBold(String content){
		return driver.findElement(By.xpath("//strong[contains(text(),'"+content+"')]")).isDisplayed();
			 
		
	}
	
	public void addComment(String name, String email , String comment){
		System.out.println("adding comment");
		commentBody.clear();
		commentBody.sendKeys(comment);
		
		commenterName.clear();
		commenterName.sendKeys(name);
		
		commenterEmail.clear();
		commenterEmail.sendKeys(email);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", postComment);
		DriverUtility.sleep(1);
		postComment.click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(commentsTitle));
		DriverUtility.sleep(2);
	}
	
	public boolean verifyCommentAttribute(String commenterName, String commentText){
		System.out.println("Verifying commenter attribute");
		boolean result = true;
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getReplyElement(commenterName));
		if( ! getReplyElement(commenterName).isDisplayed()){
			result =false;
		}
		if(! commentTextElement.getText().equals(commentText) ){
			result = false;
		}
		
		return result;
	}

	private WebElement getReplyElement(String commenterName){
		return driver.findElement(By.xpath("//div/a[@aria-label='Reply to "+commenterName+"']"));
	}

	public void replyToComment(String commenterName, String replierName, String replierEmail, String replierBody) {
		
		System.out.println("Replying to comment made by "+ commenterName);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getReplyElement(commenterName));
		getReplyElement(commenterName).click();
		DriverUtility.sleep(5);
		addComment(replierName, replierEmail, replierBody);
	}
}

package com.sudarshan.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sudarshan.driver.DriverUtility;

public class CommentTest extends SuiteSetup {
	String title  = "This is a blog by a test_user for Assignment"+getRandom();
	String content ="Test user likes to blog. The content of the blog can be <strong>anything<strong>.";
	String boldContent = "";
	String commenterName = "User1";
	String commenterEmail = "User1@typeset.io";
	String comment =  "Comment by user 1";
	
	String replierName = "User2";
	String replierEmail = "User2@typeset.io";
	String replierBody= "Comment by user 2";
	
	@Test(groups={"Comment"},description="Verify  comment attributes")
	public void  testCommentAttributes(){
	
		
		//Login and create post
		if(! userBlogPage.isLoggedIn()){
			DriverUtility.sleep(2);
			homePage.openLoginPage();
			loginPage.waitTillLoginPageOpens(60);
			loginPage.login(USER_NAME, PASSWORD);
			userBlogPage.waitUntilPageLogIn(60);
		}



		userBlogPage.createNewPost(title, content, 60);
		//userBlogPage.addContentToPost(boldContent, true);
		userBlogPage.publishPost();

		//Log out

		userBlogPage.logOut();
		loadURL();
		homePage.openAPost(title);
		blogPage.waitTillBlogOpens(60);
		blogPage.addComment(commenterName, commenterEmail, comment);
		Assert.assertTrue(blogPage.verifyCommentAttribute(commenterName, comment));
	}
	
	
	@Test(groups={"Comment"},description="Verify  comment attributes after reply", dependsOnMethods="testCommentAttributes")
	public void  testReplyCommentAttributes(){
	
		homePage.openAPost(title);
		blogPage.waitTillBlogOpens(60);
		
		
		blogPage.replyToComment (commenterName , replierName, replierEmail, replierBody);
		Assert.assertTrue(blogPage.verifyCommentAttribute(replierName, comment));
	}
}

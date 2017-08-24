package com.sudarshan.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
/**
 * 
 * @author sudharshan
 *
 */
public class PublishTest extends SuiteSetup {


	@Test(groups={"Publish"},description="Verify UnAuthenticated  User can view the blog on index page")
	public void verifyUnAuthenticatedUserCanSeeBlog(){
		String title  = "This is a blog by a test_user for Assignment" + getRandom();
		String content ="Test user likes to blog. The content of the blog can be anything.";

		//Login and create post
		if(! userBlogPage.isLoggedIn()){
			homePage.openLoginPage();
			loginPage.waitTillLoginPageOpens(60);
			sleep(3);
			loginPage.login(USER_NAME, PASSWORD);
			userBlogPage.waitUntilPageLogIn(60);
		}



		userBlogPage.createNewPost(title, content, 60);
		userBlogPage.publishPost();

		//Log out

		userBlogPage.logOut();
		loadURL();


		Assert.assertTrue(homePage.verifyPostIsVisible(title));


	}

	@Test(groups={"Publish"},description="Verify  post content is in Bold")
	public void verifyContentInBold() {
		String title  = "This is a blog by a test_user for Assignment"+getRandom();
		String content ="Test user likes to blog. The content of the blog can be <strong>anything<strong>.";
		String boldContent = "";
		//Login and create post
		if(! userBlogPage.isLoggedIn()){
			sleep(2);
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
		blogPage.verifyContentInBold(boldContent);



	}
}

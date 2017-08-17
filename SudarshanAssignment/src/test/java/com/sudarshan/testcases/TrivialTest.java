package com.sudarshan.testcases;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sudarshan.utility.HTTPUtility;

public class TrivialTest extends SuiteSetup {

	@Test(groups={"Trivial"},description="Check site Sends 200 HTTP OK")
	public void siteOperationalTest() throws MalformedURLException, IOException{
		int actual = HTTPUtility.getResponseCode(TEST_URL);
		Assert.assertEquals(actual, 200);
	}
	
	@Test(groups={"Trivial"},description="Verify Title is User’s blog")
	public void titleTest(){
		Assert.assertEquals(driver.getTitle(), "user's Blog! – Just another WordPress site");
	}
}

package com.sudarshan.driver;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class InItDriver {

	private static WebDriver driver ;
	public InItDriver (){
		
	}
	/**
	 * This will return driver based on Capabilities passed 
	 * @param remoteAddress : Selenium Grid Server Address
	 * @param desiredCapabilities : Pass Capabilities such as BrowserName , OS version
	 * @return WebDriver instance
	 */
	public static WebDriver getDriver(URL remoteAddress , DesiredCapabilities desiredCapabilities){
		if (driver == null){
			driver = new RemoteWebDriver(remoteAddress, desiredCapabilities);
		}
		return driver;
	}
	
	/**
	 * 
	 * @return ChromeDriver on running machine OS ,depending on whether Windows or Mac
	 */
	public static WebDriver getDriver(){
		if(driver==null){
			String OS  = System.getProperty("os.name").toLowerCase();
			
			if (OS.contains("win")){

				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			}
			else{
				System.setProperty("webdriver.chrome.driver", "chromedriver");
				 File file = new File("chromedriver");
				file.setExecutable(true, false);
				file.setReadable(true,false);
				file.setWritable(true, false);
			}
		}
		driver =  new ChromeDriver();
		return driver;
	}
}

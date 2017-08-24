package com.sudarshan.driver;

public class DriverUtility {

	public static void sleep(int seconds){
		try {
			Thread.sleep(seconds*1000);
		} catch (Exception e) {
			// TODO: handle exception later
		}
	}
}

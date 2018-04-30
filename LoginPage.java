package com.ooyala.hometest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPage {

	WebDriver driver;

	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium Training\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("http://the-internet.herokuapp.com/login");
	}

	String expected_message ="You logged into a secure area!";
	String expected_message1 ="Your username is invalid!";
	String expected_message2 ="Your password is invalid!";
	
	@DataProvider(name = "Ooyala india")
	public Object[] dataProviderMethod() {
		Object[][] data = new Object[3][2];

		data[0][0] = "tomsmith";            // Valid username and password//
		data[0][1] = "SuperSecretPassword!";
		
		data[1][0] = "mohideenkalith";      // Incorrect username//
		data[1][1] = "SuperSecretPassword!";
  
		data[2][0] = "tomsmith";            // Incorrect password//
		data[2][1] = "SecretPassword!";
		
		return data;

	}

	@Test(dataProvider ="Ooyala india",priority = 0)
	public void loginCredentials(String username,String password) throws InterruptedException {
		// Both username and passwords are Case sensitive//
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.xpath("//i[contains(text(),'Login')]")).click();
		Thread.sleep(2000);
		
	  	
			String actual_Message = driver.findElement(By.xpath("//*[@id=\"flash\"]")).getAttribute("innerHTML");
			if(actual_Message.contains(expected_message)); // Valid username and password //
		    {
		     System.out.println(expected_message);
		    }
			driver.findElement(By.xpath("//i[contains(text(),'Logout')]")).click();

			
		    String actual_Message1 = driver.findElement(By.xpath("//*[@id=\"flash\"]")).getAttribute("innerHTML");
		    if(actual_Message.contains(expected_message1)); // Incorrect username check//
		    {
		     System.out.println(expected_message1);
		    }	

	     
	        String actual_Message2 = driver.findElement(By.xpath("//*[@id=\"flash\"]")).getAttribute("innerHTML");
	        if(actual_Message.contains(expected_message2)); // Incorrect password check//
		    {
		     System.out.println(expected_message2);

		   		    
		    }
		    }
	@Test(priority = 4, description = "Clicking on Elemental Selenium webelement to route to next page")
	public void goToElementalSelenium() throws InterruptedException {
		driver.findElement(By.xpath("//a[contains(text(),'Elemental Selenium')]")).click();
        Thread.sleep(5000);	
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}

}

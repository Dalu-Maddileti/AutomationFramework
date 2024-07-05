package data_driven_testing;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import vtiger_crm_generic_utility.PropertiesFileUtility;

public class ToReadTheDataFromPropertiesFile 
{

	public static void main(String[] args) throws IOException 
	{
//		// Step_1: Create a object FileInputStream
//		FileInputStream fis = new FileInputStream("./testData/facebook_credentials.properties");
//		
//		//Step_2: Create object properties file
//		Properties prop = new Properties();
//		
//		//Call methods
//		prop.load(fis);
		
		PropertiesFileUtility pf = new PropertiesFileUtility();
		
		String URL = pf.toReadDataFromPropertiesFile("url");
		String USERNAME = pf.toReadDataFromPropertiesFile("username");
		String PASSWORD = pf.toReadDataFromPropertiesFile("password");
		
		//Automation Script Starts
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(URL);
		driver.findElement(By.id("email")).sendKeys(USERNAME);
		driver.findElement(By.id("pass")).sendKeys(PASSWORD);
		driver.findElement(By.name("login")).click();
		driver.quit();
	}

}

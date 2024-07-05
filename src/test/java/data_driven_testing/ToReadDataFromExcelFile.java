package data_driven_testing;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import vtiger_crm_generic_utility.ExcelFileUtility;

public class ToReadDataFromExcelFile 
{

	public static void main(String[] args) throws Exception 
	{
		ExcelFileUtility eutil = new ExcelFileUtility();
		String URL = eutil.toReadDataFromExcelFile("Sheet1", 0, 1);
		String USERNAME = eutil.toReadDataFromExcelFile("Sheet1", 1, 1);
		String PASSWORD = eutil.toReadDataFromExcelFile("Sheet1", 2, 1);
		
		//Automation Script Starts
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(URL);
		driver.findElement(By.id("email")).sendKeys(USERNAME);
		driver.findElement(By.id("pass")).sendKeys(PASSWORD);
		driver.findElement(By.name("login")).click();
		//driver.quit();
	}

}

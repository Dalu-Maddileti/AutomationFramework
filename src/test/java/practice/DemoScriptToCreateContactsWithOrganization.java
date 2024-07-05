package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import vtiger_crm_generic_utility.ExcelFileUtility;
import vtiger_crm_generic_utility.PropertiesFileUtility;
import vtiger_crm_generic_utility.WebDriverUtility;

public class DemoScriptToCreateContactsWithOrganization 
{

	public static void main(String[] args) throws Exception 
	{
		PropertiesFileUtility putil = new PropertiesFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();
		
		//To read the data from properties file
		String URL = putil.toReadDataFromPropertiesFile("url");
		String BROWSER = putil.toReadDataFromPropertiesFile("browser");
		String USERNAME = putil.toReadDataFromPropertiesFile("username");
		String PASSWORD = putil.toReadDataFromPropertiesFile("password");
		
		//To fetch the data from the excel file
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		
		// Automation Script 
		WebDriver driver = null;
	    if(BROWSER.equalsIgnoreCase("chrome")) 
	    {
	    	driver = new ChromeDriver();
	    }
	    else if(BROWSER.equalsIgnoreCase("edge"))
	    {
	    	driver = new EdgeDriver();
	    }
	    else if(BROWSER.equalsIgnoreCase("firefox"))
	    {
	    	driver = new FirefoxDriver();
	    }
	  
	    driver.get(URL);
	    wutil.toMaximize(driver);
	    wutil.waitForElements(driver);
	   
	    //Login to the application with valid credentials
 		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
 		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
 		driver.findElement(By.id("submitButton")).click();
 		
 		//Navigate to contacts link
 		driver.findElement(By.linkText("Contacts")).click();
 		
 		//Click on create contact look up image
 		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
 		
 		//Create contact with mandatory fields
 		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
 		
 		WebElement element = driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]"));
		element.click();
		wutil.toSwitchWindow(driver, "Accounts");
		driver.findElement(By.linkText("Oracle")).click();
		wutil.toSwitchWindow(driver, "Conatacts");
		
		//Save and Verify 
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String name = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (name.contains(LASTNAME)) 
		{
			System.out.println(name + " created successfully.");
		}
		else 
		{
			System.out.println(name + " Failed to create contact.");
		}
		
		//Logout of application
		WebElement LogoutLink = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wutil.toMoveToElement(driver, LogoutLink);
		driver.findElement(By.linkText("Sign Out")).click();
		
		//Close the browser
		driver.quit();

	}

}

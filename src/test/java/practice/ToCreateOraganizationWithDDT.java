package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class ToCreateOraganizationWithDDT 
{

	public static void main(String[] args) throws IOException 
	{
		// Get Data From Properties File 
		FileInputStream pfis = new FileInputStream(".\\src\\test\\resources\\testData.properties");
		Properties prop = new Properties();
		prop.load(pfis);
		
		// Read the data from properties file
		String URL = prop.getProperty("url");
		String BROWSER = prop.getProperty("browser");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");
		
		// To get the data from Excel File
		FileInputStream efis = new FileInputStream(".\\src\\test\\resources\\testData.xlsx");
		Workbook wb = WorkbookFactory.create(efis);
		String ORGANIZATION = wb.getSheet("Organization").getRow(1).getCell(2).getStringCellValue();
		
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
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	    
	    //Login to the application with valid credentials
  		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
  		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
  		driver.findElement(By.id("submitButton")).click();
  	
  		//Navigate to organization link
  		driver.findElement(By.linkText("Organizations")).click();
  		
  		//Click on create organization look up image
  		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
  		
  		//To generate random number
  		Random r = new Random();
  		int random = r.nextInt(1000);
  		
  		//Create organization with mandatory fields 
  		driver.findElement(By.name("accountname")).sendKeys(ORGANIZATION + random);
  		
  		//Save and Verify
  		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
  		String name = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
  		if (name.contains(ORGANIZATION + random)) 
  		{
  			System.out.println(name + " created successfully.");
  		} 
  		else 
  		{
  			System.out.println(name + " failed to create organization");
  		}
  		
  		//Logout of application
  		WebElement LogoutLink = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
  		Actions action = new Actions(driver);
  		action.moveToElement(LogoutLink).perform();
  		driver.findElement(By.linkText("Sign Out")).click();
  		
  		//Close the browser
  		driver.quit();
	}

}

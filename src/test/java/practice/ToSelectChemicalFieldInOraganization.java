package practice;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class ToSelectChemicalFieldInOraganization 
{

	public static void main(String[] args) 
	{
		//To launch the browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("http://localhost:8888/");
		
		//Login to application with valid credentials
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
	
		//Navigate to organization link
		driver.findElement(By.linkText("Organizations")).click();
		
		//Click on create organization look up image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//To generate random number
		Random r = new Random();
		int random = r.nextInt(1000);
		
		//Create organization with mandatory fields 
		driver.findElement(By.name("accountname")).sendKeys("Infosys" + random);
		
		//Select 'Chemicals' in the industry drop down
		WebElement dropdown = driver.findElement(By.name("industry"));
		dropdown.click();
		List<WebElement> options = dropdown.findElements(By.tagName("option"));
		for (WebElement option : options) 
		{
			if (option.getText().equals("Chemicals")) 
			{
				option.click();
				break;
			}
		}
		
		//Save and Verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String name = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (name.contains("Infosys" + random)) 
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

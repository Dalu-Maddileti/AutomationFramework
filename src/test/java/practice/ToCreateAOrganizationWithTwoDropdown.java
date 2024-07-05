package practice;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ToCreateAOrganizationWithTwoDropdown 
{

	public static void main(String[] args) 
	{
		//To Launch the browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://localhost:8888/");
		
		//Login to the application with valid credentials
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
		
		//Navigate to the Organization link
		driver.findElement(By.linkText("Organizations")).click();
		
		//Click a Organization look up image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//Generate a random number 
		Random r = new Random();
		int random = r.nextInt(1000);
		
		//Create a Organization with mandatory fields 
		driver.findElement(By.name("accountname")).sendKeys("Tesla" + random);
		
		//Select 'Energy' in the industry drop down
		WebElement dropdown = driver.findElement(By.name("industry"));
		dropdown.click();
		List<WebElement> options = dropdown.findElements(By.tagName("option"));
		for (WebElement option : options) 
		{
			if (option.getText().equals("Energy")) 
			{
				option.click();
				break;
			}
		}
		
		//Select 'Customer' in the Type drop down
		WebElement dropdown1 = driver.findElement(By.name("accounttype"));
		Select select = new Select(dropdown1);
		select.selectByValue("Customer");
		
		//Save and Verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String name = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (name.contains("Tesla" + random)) 
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

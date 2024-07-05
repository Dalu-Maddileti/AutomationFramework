package practice;

import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
 
public class ToCreateContactBySelectingOraganization 
{

	public static void main(String[] args) 
	{
		//To Launch the web browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("http://localhost:8888/");

		//Login to the application with valid credentials
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
		
		//Navigate to contacts link
		driver.findElement(By.linkText("Contacts")).click();
		
		//Click on create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//Create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys("Dalu Maddileti");
		WebElement element = driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]"));
		element.click();
		String parentId = driver.getWindowHandle();
		Set<String> allWindowsId = driver.getWindowHandles();
		allWindowsId.remove(parentId);
		
		for (String Id : allWindowsId)
		{
			//Switch to child window
			driver.switchTo().window(Id);
			driver.findElement(By.linkText("Google")).click();
			break;
		}
		
		//switch back to parent window
		driver.switchTo().window(parentId);
		
		//Save and Verify 
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String name = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (name.contains("Dalu Maddileti")) 
		{
			System.out.println(name + " created successfully.");
		}
		else 
		{
			System.out.println(name + " Failed to create contact.");
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

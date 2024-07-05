package vtiger_crm_generic_utility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class consists of methods related to WebDriver 
 */
public class WebDriverUtility 
{
	
	/**
	 * This method is used to maximize the browser
	 * @param driver
	 */
	public void toMaximize(WebDriver driver)
	{
		driver.manage().window().maximize();
	} 
	
	/**
	 * This method is used to minimize the browser
	 * @param driver
	 */
	public void toMinimize(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	
	/**
	 * This method will wait until the web elements are loaded in the web page(Implicitly wait) 
	 * @param driver
	 */
	public void waitForElements(WebDriver driver) 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}
	
	/**
	 * This method will wait until the element is clickable.
	 * @param driver
	 * @param element
	 */
	public void elementToBeClickable(WebDriver driver, WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method will wait until the element is visible.
	 * @param driver
	 * @param element
	 */
	public void elementToBeVisible(WebDriver driver, WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method will handle the drop down by using the index.
	 * @param element
	 * @param index
	 */
	public void toHandleDropdown(WebElement element, int index) 
	{
		Select select = new Select(element);
		select.selectByIndex(index);
	}
	
	/**
	 * This method will handle the drop down by using the String value.
	 * @param element
	 * @param index
	 */
	public void toHandleDropdown(WebElement element, String value) 
	{
		Select select = new Select(element);
		select.selectByValue(value);
	}
	
	/**
	 * This method will handle the drop down by using the visible text
	 * @param element
	 * @param index
	 */
	public void toHandleDropdown(String value, WebElement element) 
	{
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}
	
	/**
	 * This method is used to handle frame by using index
	 * @param driver
	 * @param index
	 */
	public void toHandleFrame(WebDriver driver, int index) 
	{
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method is used to handle frame by using String id or name
	 * @param driver
	 * @param index
	 */
	public void toHandleFrame(WebDriver driver, String id_name) 
	{
		driver.switchTo().frame(id_name);
	}
	
	/**
	 * This method is used to handle frame by using web element
	 * @param driver
	 * @param index
	 */
	public void toHandleFrame(WebDriver driver, WebElement element) 
	{
		driver.switchTo().frame(element);
	}
	
	/**
	 * This method is used to switch back from frame to main web page
	 * @param driver
	 * @param index
	 */
	public void toSwitchFrame(WebDriver driver) 
	{
		driver.switchTo().defaultContent();
	}
	
	/**
	 * This method is used to switch back from child frame to parent frame
	 * @param driver
	 * @param index
	 */
	public void toSwitchPFrame(WebDriver driver) 
	{
		driver.switchTo().parentFrame();
	}
	
	/**
	 * This method is used to perform double click operation on given web element
	 * @param driver
	 */
	public void toDoubleClick(WebDriver driver, WebElement element) 
	{
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}
	
	/**
	 * This method is used to perform right click operation on given web element
	 * @param driver
	 */
	public void toRightClick(WebDriver driver, WebElement element) 
	{
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}
	
	/**
	 * This method is used to move cursor to particular web element
	 * @param driver
	 */
	public void toMoveToElement(WebDriver driver, WebElement target) 
	{
		Actions action = new Actions(driver);
		action.moveToElement(target).perform();
	}
	
	/**
	 * This method is used to perform drag and drop action 
	 * @param driver
	 */
	public void toDragAndDrop(WebDriver driver, WebElement src, WebElement target) 
	{
		Actions action = new Actions(driver);
		action.dragAndDrop(src, target).perform();
	}
	
	/**
	 * This method is used to perform click and hold on a web element 
	 * @param driver
	 */
	public void toClickAndHold(WebDriver driver,WebElement target) 
	{
		Actions action = new Actions(driver);
		action.clickAndHold(target).perform();
	}
	
	/**
	 * This method is used to switch driver control to alert pop ups and accept it 
	 * @param driver
	 */
	public void toHandleAlertPopUpAndToAccept(WebDriver driver) 
	{
		driver.switchTo().alert().accept();
	}
	
	
	/**
	 * This method is used to switch driver control to alert pop ups and dismiss it 
	 * @param driver
	 */
	public void toHandleAlertPopUpAndToDismiss(WebDriver driver) 
	{
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * This method is used to switch driver control to alert pop ups and capture the text.
	 * @param driver
	 * @return
	 */
	public String toHandleAlertPopUpAndCaptureText(WebDriver driver) 
	{
		Alert alertGroup = driver.switchTo().alert();
		String alertMsg = alertGroup.getText();
		alertGroup.accept();
		return alertMsg;
	}
	
	/**
	 * This method is used to take screenshot by providing the screenshot name
	 * @param driver
	 * @param screenshot_name
	 * @return 
	 * @throws IOException
	 */
	public String toTakeScreenshot(WebDriver driver, String screenshotName) throws IOException 
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File src = new File("./errorShots/" + screenshotName + ".jpeg");
		try {
			FileHandler.copy(temp, src);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return src.getAbsolutePath();
	}
	
	/**
	 * This method is used to switch driver control to window by provided title
	 * @param driver
	 * @param partialTindowTile
	 */
	public void toSwitchWindow(WebDriver driver, String partialTindowTile) 
	{
		Set<String> allIds = driver.getWindowHandles();
		for (String id : allIds) 
		{
			String windowTitle = driver.switchTo().window(id).getTitle();
			if (windowTitle.contains(partialTindowTile)) 
			{
				break;
			}
		}
	}

	
}

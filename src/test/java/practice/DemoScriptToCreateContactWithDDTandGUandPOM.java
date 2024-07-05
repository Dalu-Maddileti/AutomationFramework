package practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import vtiger_crm_generic_utility.ExcelFileUtility;
import vtiger_crm_generic_utility.PropertiesFileUtility;
import vtiger_crm_generic_utility.WebDriverUtility;
import vtigercrm_object_repo.ContactInformationPage;
import vtigercrm_object_repo.ContactPage;
import vtigercrm_object_repo.CreateContactsPage;
import vtigercrm_object_repo.HomePage;
import vtigercrm_object_repo.LoginPage;

public class DemoScriptToCreateContactWithDDTandGUandPOM {

	public static void main(String[] args) throws Exception {
		
		PropertiesFileUtility putil = new PropertiesFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();
		
		String URL = putil.toReadDataFromPropertiesFile("url");
		String BROWSER = putil.toReadDataFromPropertiesFile("browser");
		String USERNAME = putil.toReadDataFromPropertiesFile("username");
		String PASSWORD = putil.toReadDataFromPropertiesFile("password");
		
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		
		WebDriver driver = null;
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if(BROWSER.equalsIgnoreCase("edge")){
			driver = new EdgeDriver();
		} else if(BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		
		driver.get(URL);
		wutil.toMaximize(driver);
		wutil.waitForElements(driver);
		
		//Login Page
		LoginPage lp = new LoginPage(driver);
		lp.getUsernameTextField().sendKeys(USERNAME);
		lp.getPasswordTextField().sendKeys(PASSWORD);
		lp.getLoginButton().click();
		
		
		//Home Page
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();
		
		//Contact Page
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactIcon().click();
		
		//Create Contact Page 
		CreateContactsPage ccp = new CreateContactsPage(driver);
		ccp.getLastnameTextField().sendKeys(LASTNAME);
		ccp.getContactSaveButton().click();
		
		//Contact Information Page
		ContactInformationPage cip = new ContactInformationPage(driver);
		String name = cip.getVarifyingContactInfo().getText();
		if (name.contains(LASTNAME)) {
			System.out.println(LASTNAME + " is contact created successfully");
		} else {
			System.out.println(LASTNAME + " is failed to create contact");
		}
		
		//Logout 
		wutil.toMoveToElement(driver, hp.getAdminstratorIcon());
		hp.getSignOutButton().click();
		
		//Close the browser
		driver.quit();

	}

}

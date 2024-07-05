package vtiger_crm_generic_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import vtigercrm_object_repo.HomePage;
import vtigercrm_object_repo.LoginPage;

public class BaseClass {

	PropertiesFileUtility putil = new PropertiesFileUtility();
	ExcelFileUtility eutil = new ExcelFileUtility();
	WebDriverUtility wutil = new WebDriverUtility();

	public WebDriver driver = null;
	public static WebDriver sDriver; // This driver for listeners

	@BeforeSuite(groups = {"Smoke", "Regression"})
	public void beforeSuiteConfig() {
		Reporter.log("---Database connection established---", true);
	}
	//@Parameters("browser")
	//@BeforeTest
	@BeforeClass(groups = {"Smoke", "Regression"})
	public void beforeClassConfig(/*String BROWSER*/) throws Exception {
		String BROWSER = putil.toReadDataFromPropertiesFile("browser");
		String URL = putil.toReadDataFromPropertiesFile("url");
		
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		
		sDriver = driver; // This is for listeners
		
		Reporter.log("Browser launched successfully", true);
		driver.get(URL);
		Reporter.log("Log in page got opened", true);
		wutil.toMaximize(driver);
		wutil.waitForElements(driver);

	}

	@BeforeMethod(groups = {"Smoke", "Regression"})
	public void beforeMethodConfig() throws Exception {
		String USERNAME = putil.toReadDataFromPropertiesFile("username");
		String PASSWORD = putil.toReadDataFromPropertiesFile("password");

		LoginPage lp = new LoginPage(driver);
		lp.getUsernameTextField().sendKeys(USERNAME);
		lp.getPasswordTextField().sendKeys(PASSWORD);
		lp.getLoginButton().click();
		Reporter.log("Home page got opened", true);
		
	}
	

	@AfterMethod(groups = {"Smoke", "Regression"})
	public void afterMethodConfig() {
		HomePage hp = new HomePage(driver);
		wutil.toMoveToElement(driver, hp.getAdminstratorIcon());
		hp.getSignOutButton().click();
		Reporter.log("Logout page got opened", true);
	}

	@AfterClass(groups = {"Smoke", "Regression"})
	public void afterClass() {
		Reporter.log("Browser closed successfully", true);
		driver.quit();
	}

	@AfterSuite(groups = {"Smoke", "Regression"})
	public void afterSuiteConfig() {
		Reporter.log("---Database connection disconnected--", true);
	}

}

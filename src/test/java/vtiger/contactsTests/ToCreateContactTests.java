package vtiger.contactsTests;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import vtiger_crm_generic_utility.BaseClass;
import vtiger_crm_generic_utility.ExcelFileUtility;
import vtigercrm_object_repo.ContactInformationPage;
import vtigercrm_object_repo.ContactPage;
import vtigercrm_object_repo.CreateContactsPage;
import vtigercrm_object_repo.HomePage;


@Listeners(vtiger_crm_generic_utility.ListenersImplimentation.class)
public class ToCreateContactTests extends BaseClass {
	
	@Test(groups = "Smoke")
	public void toCreateContact_001() throws Exception {
		//To get contact page
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();
		Reporter.log("Contact page got opened", true);
		
		//To get create contact page
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactIcon().click();
		Reporter.log("Create contact page got opened", true);
		
		//To create new contact 
		CreateContactsPage ccp = new CreateContactsPage(driver);
		ExcelFileUtility eutil = new ExcelFileUtility();
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		ccp.getLastnameTextField().sendKeys(LASTNAME);
		ccp.getContactSaveButton().click();
		Reporter.log("Contact info page got opened", true);
		Assert.fail(); //Failed 
		
		//To get contact information page
		ContactInformationPage cip = new ContactInformationPage(driver);
		String name = cip.getVarifyingContactInfo().getText();
//		if (name.contains(LASTNAME)) {
//			Reporter.log(LASTNAME + " is created the contact successfully.", true);
//		} else {
//			Reporter.log(LASTNAME + " is failed created contact.", true);
//		}
		assertTrue(name.contains(LASTNAME));
		Reporter.log("Varified the text successfully", true);
		
	}
	
}

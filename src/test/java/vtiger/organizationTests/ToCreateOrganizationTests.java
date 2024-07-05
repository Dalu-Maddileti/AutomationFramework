package vtiger.organizationTests;

import static org.testng.Assert.assertTrue;

import org.testng.Reporter;
import org.testng.annotations.Test;

import vtiger_crm_generic_utility.BaseClass;
import vtiger_crm_generic_utility.ExcelFileUtility;
import vtiger_crm_generic_utility.JavaFileUtility;
import vtigercrm_object_repo.CreateOrganizationPage;
import vtigercrm_object_repo.HomePage;
import vtigercrm_object_repo.OrganizationInformationPage;
import vtigercrm_object_repo.OrganizationPage;

public class ToCreateOrganizationTests extends BaseClass {

	@Test(groups = "Regression")
	public void toCreateOrganization_001() throws Exception {

		//To get home page
		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();
		Reporter.log("Organization page got opened", true);

		//To get organization page
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrganizationIcon().click();
		Reporter.log("Create Organization page got opened", true);

		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		ExcelFileUtility eutil = new ExcelFileUtility();
		String organizationName = eutil.toReadDataFromExcelFile("Organization", 1, 2);
		JavaFileUtility jutil = new JavaFileUtility();
		int random = jutil.toGetRandomNumber();
		cop.getOrganizationNameTextField().sendKeys(organizationName + random);
		cop.getOrganizationSaveButton().click();
		Reporter.log("Organization Info page got opened", true);
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String name = oip.getVarifyingOrganizationName().getText();
//		if (name.contains(organizationName)) {
//			System.out.println(organizationName + " organization is created successfully.");
//		} else {
//			System.out.println(organizationName + " is failed to create organization.");
//		}
		assertTrue(name.contains(organizationName));
		Reporter.log("Verified the text successfully", true);
	}
}

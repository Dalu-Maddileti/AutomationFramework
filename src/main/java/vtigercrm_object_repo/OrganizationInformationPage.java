package vtigercrm_object_repo;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {
	
	//Constructor
	public OrganizationInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement varifyingOrganizationName;
	
	@FindBy(id = "dtlview_Organization")
	private WebElement organizationNameTextInfo;

	public WebElement getVarifyingOrganizationName() {
		return varifyingOrganizationName;
	}

	public WebElement getOrganizationNameTextInfo() {
		return organizationNameTextInfo;
	}
	
}

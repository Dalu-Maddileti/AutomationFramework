package vtigercrm_object_repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage {
	//Constructor
	public ContactInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement varifyingContactInfo;
	
	@FindBy(id = "dtlview_Last Name")
	private WebElement lastnameTextInfo;

	public WebElement getVarifyingContactInfo() {
		return varifyingContactInfo;
	}

	public WebElement getLastnameTextInfo() {
		return lastnameTextInfo;
	}
	
}

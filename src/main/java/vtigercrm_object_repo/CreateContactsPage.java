package vtigercrm_object_repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactsPage {

	//Constructor
	public CreateContactsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "lastname")
	private WebElement lastnameTextField;
	
	@FindBy(xpath = "(//img[@src='themes/softed/images/select.gif'])[1]")
	private WebElement organizatinNameIconInContacts;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement contactSaveButton;

	public WebElement getLastnameTextField() {
		return lastnameTextField;
	}

	public WebElement getOrganizatinNameIconInContacts() {
		return organizatinNameIconInContacts;
	}

	public WebElement getContactSaveButton() {
		return contactSaveButton;
	}
	
}

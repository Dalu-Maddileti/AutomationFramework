package vtigercrm_object_repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	//Constructor
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Contacts")
	private WebElement contactsLink;
	
	@FindBy(linkText = "Organizations")
	private WebElement organizationLink;
	
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminstratorIcon;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signOutButton;

	public WebElement getContactsLink() {
		return contactsLink;
	}

	public WebElement getOrganizationLink() {
		return organizationLink;
	}

	public WebElement getAdminstratorIcon() {
		return adminstratorIcon;
	}

	public WebElement getSignOutButton() {
		return signOutButton;
	}
	
	
}

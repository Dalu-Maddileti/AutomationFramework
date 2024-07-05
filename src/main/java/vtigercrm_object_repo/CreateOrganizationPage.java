package vtigercrm_object_repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrganizationPage {
	
	//Constructor
	public CreateOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "accountname")
	private WebElement organizationNameTextField;
	
	@FindBy(name = "industry")
	private WebElement industryDropdown;
	
	@FindBy(name = "accounttype")
	private WebElement accountTypeDropdown;
	
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]")
	private WebElement organizationSaveButton;

	public WebElement getOrganizationNameTextField() {
		return organizationNameTextField;
	}

	public WebElement getIndustryDropdown() {
		return industryDropdown;
	}

	public WebElement getAccountTypeDropdown() {
		return accountTypeDropdown;
	}

	public WebElement getOrganizationSaveButton() {
		return organizationSaveButton;
	}
	
	
}

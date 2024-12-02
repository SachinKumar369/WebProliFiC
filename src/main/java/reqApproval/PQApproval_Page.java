package reqApproval;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baselibrary.BaseTest;
import utils.DynamicWait;
import utils.Utilities;

public class PQApproval_Page {

	/*
	 * Constructor
	 * 
	 */
	public PQApproval_Page(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * Web Elements
	 */
	@FindBy(id="btnOK")
	private WebElement OkBtn;
	@FindBy(xpath = "//img[@id=\"imgLeftMenuOpen\"]")
	private WebElement Personalize;
	@FindBy(id="tdUserMail")
	private WebElement UserMail;
	
	
	
	
	/**
	 * Methods
	 * 
	 */
	public void Approve() {
		
		try {
		//click on ok button after the requisition number generated
		
			BaseTest.getDriver().switchTo().defaultContent();
			BaseTest.getDriver().switchTo().frame("iframeGridDialog");
			Utilities.Click(BaseTest.getDriver(), OkBtn);
			
			//Click On Personalize
			DynamicWait.mediumWait();
			Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Personalize);
			BaseTest.getDriver().switchTo().defaultContent();
			BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");   
			Utilities.Click(BaseTest.getDriver(), Personalize);
		
		
		} catch(Exception e) {
			
		}
		
		
	}
	
}

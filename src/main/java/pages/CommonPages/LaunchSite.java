package pages.CommonPages;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import extentreports.ExtentTestManager;
import utils.Utilities;

public class LaunchSite {
	/**
	 * Constructor
	 */
	public LaunchSite(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/**
	 * Page Methods
	 */

	/**
	 * @Method_Description : Launching the portal
	 * @Method_Name : LaunchPortal
	 * @Input_Parameters : String url
	 * @Output_Parameters : String
	 * @Modified_By : Sachin Kumar
	 * @Modified_Date :
	 **/
	public void LaunchPortal(String url) {
		try {
			BaseTest.getDriver().navigate().to(url);
			BaseTest.getDriver().manage().deleteAllCookies();
			Utilities.WaitForPageLoad(BaseTest.getDriver());
			ExtentTestManager.createAssertTestStepWithScreenshot("Launch Portal", Status.INFO,
					"Launched  portal - " + url, false);
		}
		catch (Exception e) {
			ExtentTestManager.createAssertTestStepWithScreenshot("LaunchPortal", Status.FAIL,
					"Exception found in Method - LaunchPortal", true, e);
		}
	}

}
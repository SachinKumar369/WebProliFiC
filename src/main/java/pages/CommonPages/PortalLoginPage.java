package pages.CommonPages;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import extentreports.ExtentTestManager;
import utils.DynamicWait;
import utils.Utilities;

import java.util.List;
import java.util.Set;

public class PortalLoginPage {

    /**
     * @Method_Description : Login into the portal using Username and Password
     * @Method_Name : LogInToPortal
     * @Input_Parameters : String uname, String pwd
     * @Output_Parameters : String , String
     * @Modified_By : Sachin kumar
     * @Modified_Date : 01/11/23
     **/

    public static String currentHandles = "";
    /**
     * Web Elements
     */



    @FindAll({
            @FindBy(how = How.XPATH, using = "//input[@id=\"txtChainID\"]"),
            @FindBy(how = How.ID, using = "txtChainID")
    })
    private static List<WebElement> ChainID;

    @FindAll({
            @FindBy(how = How.XPATH, using = "//input[@id=\"txtUnitID\"]"),
            @FindBy(how = How.ID, using = "txtUnitID")
    })
    private static List<WebElement> PropertyId;
    @FindAll({
            @FindBy(how = How.XPATH, using = "//input[@id=\"txtLogIN\"]"),
            @FindBy(how = How.ID, using = "txtLogIN")
    })
    private static List<WebElement> UserID;
    @FindAll({
            @FindBy(how = How.XPATH, using = "//span[@id='lblLogin_span']"),
            @FindBy(how = How.ID, using = "lblLogin_span")
    })
    private static List<WebElement> Login;
    @FindBy(id = "lblStatusDesc")
    private static WebElement AlreadyLoginPopup;
    @FindBy(id = "btnYes")
    private static WebElement YesLogin;
    @FindBy(id = "btnOK")
    private static WebElement OKBtn;
    @FindAll({
            @FindBy(how = How.XPATH, using = "//input[@id=\"lblEnterPassword\"]"),
            @FindBy(how = How.ID, using = "lblEnterPassword")
    })
    private List<WebElement> Password;
    @FindBy(xpath = "//select[@formcontrolname='company_name']//option")
    private List<WebElement> Divinity_SelectCompany;

    /**
     * Page Methods
     */


    /**
     * Constructor
     */
    public PortalLoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static void LogInToPortal(String chain, String property, String user) {
        try {
            // Wait for and enter login credentials
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ChainID.get(0));
            Utilities.SendKeys(BaseTest.getDriver(), ChainID.get(0), chain);
            Utilities.SendKeys(BaseTest.getDriver(), PropertyId.get(0), property);
            Utilities.SendKeys(BaseTest.getDriver(), UserID.get(0), user);

            currentHandles = BaseTest.getDriver().getWindowHandle();
            System.out.println("Current Handle is :"+currentHandles);

            // Click on Login button
            Utilities.Click(BaseTest.getDriver(), Login.get(0));
            DynamicWait.smallWait();

            // Check for the iframe and AlreadyLoginPopup within it
            if (isIframePresent("iframeGridDialog")) {
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                if (isElementPresent(AlreadyLoginPopup)) {
                    Utilities.Click(BaseTest.getDriver(), YesLogin);
                    DynamicWait.smallWait();
                }
            }

            // for liceance expire
//            try {
//                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//                if (isElementPresent(AlreadyLoginPopup)) {
//                    Utilities.Click(BaseTest.getDriver(), OKBtn);
//                    DynamicWait.smallWait();
//                }
//            } catch (Exception e){
//
//            }


            // Switch to the new window after login
            DynamicWait.smallWait();
            Set<String> handle = BaseTest.getDriver().getWindowHandles();
            for (String actual : handle) {
                if (!actual.equalsIgnoreCase(currentHandles)) {
                    BaseTest.getDriver().switchTo().window(actual);
                    System.out.println("Current handle is :" + actual);
                }
            }

            // Verify URL navigation
            DynamicWait.smallWait();
            String url = BaseTest.getDriver().getCurrentUrl();
            if (url.contains("http://203.122.16.200:85/WISH/Common/frmMultiPage.aspx?pgName=WishProduct&form_id=frmWishProduct")

                    || url.contains("https://qc2web.prologicfirst.in/WISH/Common/frmMultiPage.aspx?pgName=WishProduct&form_id=frmWishProduct")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Verify the navigation on tapping login", Status.PASS,
                        "Navigation on Product Page working fine", true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Verify the navigation on tapping login", Status.WARNING,
                        "Issue on the navigation on Product Page on tapping login", true);
            }

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("LogInToPortal",
                    Status.FAIL, "Exception found in Method - LogInToPortal", true, e);
        }
    }

    // Helper method to check if an iframe is present
    private static boolean isIframePresent(String iframeName) {
        try {
            BaseTest.getDriver().switchTo().frame(iframeName);
            BaseTest.getDriver().switchTo().defaultContent(); // Reset back to main content
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Helper method to check if an element is present within the iframe
    private static boolean isElementPresent(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }
}
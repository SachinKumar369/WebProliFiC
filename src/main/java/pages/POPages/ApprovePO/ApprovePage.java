package pages.POPages.ApprovePO;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.CommonPages.PortalLoginPage;
import utils.Utilities;

import java.util.Set;

public class ApprovePage {

    public ApprovePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "cphFooter_lblPersonalize")
    private WebElement Personalize;
    @FindBy(id="tdUserMail")
    private WebElement UserMail;
    @FindBy(xpath = "//*[@id=\"td_0_1\"]")
    private WebElement Mail;
    @FindBy(xpath = "//*[@id=\"divbody_preview\"]/div/a[2]")
    private WebElement Approve;
    @FindBy(xpath = "//*[@id=\"divbody_preview\"]/div/a[2]")
    private WebElement ApproveMail;

    @FindBy(xpath = "//select[contains(@id, 'ddlaction')]")
    private WebElement SelectApprove;
    @FindBy(id="cphDialogHeader_btnSave")
    private WebElement SaveBtn;


    public void ApprovePOPage(){

        try {

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Click on Personalize
            Utilities.Click(BaseTest.getDriver(),Personalize);

            // click on User Mail
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(BaseTest.getDriver()	, UserMail);
            BaseTest.getDriver().switchTo().defaultContent();


            String currentHandles1=BaseTest.getDriver().getWindowHandle();
            System.out.println(currentHandles1);

            // Switch to the new window after login
            Set<String> handle1 = BaseTest.getDriver().getWindowHandles();
            System.out.println(handle1);
            for (String actual : handle1) {
                if (!actual.equalsIgnoreCase(currentHandles1)&!actual.equalsIgnoreCase(PortalLoginPage.currentHandles)) {
                    BaseTest.getDriver().switchTo().window(actual);
                }
            }




            System.out.println();

            Actions actions = new Actions(BaseTest.getDriver());

            //Enter into third window
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Mail);

            // Utilities.Click(BaseTest.getDriver(), help);
            //Utilities.ActionClick(BaseTest.getDriver(), AddButton);
            // actions.doubleClick(BaseTest.getDriver().findElement(By.id("td_0_1")));


            actions.doubleClick(BaseTest.getDriver().findElement(By.id("td_0_1"))).perform();

            Utilities.Click(BaseTest.getDriver(), ApproveMail);

            //Select Approve
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
//            actions.doubleClick(BaseTest.getDriver().findElement(By.id("td_0_1"))).perform();
//            Utilities.Click(BaseTest.getDriver(), );
//            Utilities.selectByContainsText(BaseTest.getDriver(), SelectApprove, "Approve");

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.selectBy(BaseTest.getDriver(), "visibletext", SelectApprove, "Approve");
            Utilities.Click(BaseTest.getDriver(), SaveBtn);
//            Utilities.Click(BaseTest.getDriver(), ProcessBtn);


            //click ok btn
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");



            ExtentTestManager.createAssertTestStepWithScreenshot("Approve PO", Status.PASS, "PO Has Been Approved", true);



        } catch (Exception e){
            ExtentTestManager.createAssertTestStepWithScreenshot("Approve PO", Status.FAIL, "Exception found in Method - Approve PO", true, e);

        }
    }
}

package pages.accountPayables.processReceiving;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.databind.ser.Serializers;
import extentreports.ExtentTestManager;
import org.bouncycastle.pqc.crypto.util.PQCOtherInfoGenerator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DynamicWait;
import utils.Utilities;

import java.time.Duration;

public class ProcessReceivingPage {
    @FindBy(xpath = "//a[text()='Accounting']")
    private WebElement accounting;
    @FindBy(id = "divmodule_1")
    private WebElement accountPayble;
    @FindBy(xpath = "//a[text()='Make PJV']")
    private WebElement makePJV;
    // *************** FRAME ELEMENTS ***************
    @FindBy(id = "MultiPageiframeBrw")
    private WebElement multiPageIframeBrw;
    @FindBy(id = "iframeRecvDataPost")
    private WebElement iframeRecvDataPost;
    @FindBy(id = "iframeGridDialog")
    private WebElement iframeGridDialog;
    // *************** PAGE ELEMENTS ***************
    @FindBy(xpath = "//*[@title='AP27 Process Receiving']")
    private WebElement linkProcessReceiving;
    @FindBy(id = "cphBody_btnSearch")
    private WebElement btnSearch;
    @FindBy(id = "ischeck_13_0")
    private WebElement chkSelectFirstItem;
    @FindBy(id = "cphBody_btnNext")
    private WebElement btnNext;
    @FindBy(id = "ispost_12_0")
    private WebElement chkPostFirstItem;
    @FindBy(id = "btnYes")
    private WebElement btnYes;
    @FindBy(id = "lblErrorDesc")
    private WebElement confDlg;

    public ProcessReceivingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void accounting() {
        try {
            // Maximize browser window
            BaseTest.getDriver().manage().window().maximize();
            ExtentTestManager.createAssertTestStepWithScreenshot("Browser Window", Status.INFO, "Browser window maximized", true);

            // Click on Accounting
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(accounting);
            ExtentTestManager.createAssertTestStepWithScreenshot("Click Accounting", Status.PASS, "Clicked on 'Accounting'", true);

            // Click on Account Payable
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(accountPayble);
            ExtentTestManager.createAssertTestStepWithScreenshot("Click Account Payable", Status.PASS, "Clicked on 'Account Payable'", true);

            // Log completion
            ExtentTestManager.createAssertTestStepWithScreenshot("Accounting Navigation", Status.INFO, "Accounting navigation completed successfully", false);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Create AP Account", Status.FAIL, "Issue while creating AP Account", true, e);
        }
    }

    // *************** METHODS ***************

    WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
    public void blankSearch() {
        try {
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Blank Search - Start", Status.INFO,
                    "Starting 'Process Receiving' blank search flow", false);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(multiPageIframeBrw);

            // --- Step 1: Click "Process Receiving" link ---
            Utilities.Click(BaseTest.getDriver(), linkProcessReceiving);
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Click Process Receiving", Status.PASS,
                    "Clicked on 'Process Receiving' link", true);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(multiPageIframeBrw);
            Utilities.Click(BaseTest.getDriver(), btnSearch);
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Click Search", Status.PASS,
                    "Clicked on 'Search' button", true);

            // --- Step 3: Select the first item checkbox ---
            Utilities.Click(BaseTest.getDriver(), chkSelectFirstItem);
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Select Item", Status.PASS,
                    "Selected the first item checkbox successfully", true);

            // --- Step 4: Click "Next" button ---
            Utilities.Click(BaseTest.getDriver(), btnNext);
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Click Next", Status.PASS,
                    "Clicked on 'Next' button successfully", true);

            // --- Step 5: Select item for posting ---
            BaseTest.getDriver().switchTo().frame(iframeRecvDataPost);
            Utilities.Click(BaseTest.getDriver(), chkPostFirstItem);
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Post Item", Status.PASS,
                    "Checked 'Post Item' successfully", true);

            // --- Step 6: Click "Next" again for confirmation ---
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(multiPageIframeBrw);
            Utilities.Click(BaseTest.getDriver(), btnNext);
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Click Next Again", Status.PASS,
                    "Clicked on 'Next' button again for confirmation", true);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(iframeGridDialog);
            Utilities.Click(BaseTest.getDriver(), btnYes);
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Confirm Action", Status.PASS,
                    "Clicked 'Yes' to confirm successfully", true);


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(iframeGridDialog);
            BaseTest.getDriver().switchTo().frame(iframeGridDialog);


            wait.until(ExpectedConditions.visibilityOf(confDlg));
            String confTaxt = confDlg.getText();


            if (confTaxt.contains("[4533] - Invoice Posted. Invoice number is")) {
                ExtentTestManager.getTest().pass("Invoice posted popup displayed correctly.");
            } else {
                ExtentTestManager.getTest().fail("Popup text did not match expected format. Found: " + confTaxt);
            }



            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Blank Search - Completed", Status.PASS,
                    "Successfully completed the 'Process Receiving' blank search flow", false);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Process Receiving Flow", Status.FAIL,
                    "Process Receiving flow failed due to: " + e.getMessage(), true, e);
        } finally {
            try {
                BaseTest.getDriver().switchTo().defaultContent();
            } catch (Exception ignored) {}
            DynamicWait.smallWait();
        }
    }

    @FindBy(id = "ap_id_0_3")
    private WebElement txtAccountCodeSource;

    @FindBy(id = "cphBody_txtPayablesAccountCode")
    private WebElement txtPayablesAccountCode;

    @FindBy(id = "cphBody_btnRefresh")
    private WebElement btnRefresh;



    public void apAccount() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(multiPageIframeBrw);

            Utilities.Click(BaseTest.getDriver(), linkProcessReceiving);
            ExtentTestManager.createAssertTestStepWithScreenshot("Click Process Receiving", Status.PASS, "Clicked on 'Process Receiving' link", true);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(multiPageIframeBrw);
            Utilities.Click(BaseTest.getDriver(), btnSearch);
            ExtentTestManager.createAssertTestStepWithScreenshot("Click Search", Status.PASS, "Clicked on 'Search' button", true);

            Utilities.Click(BaseTest.getDriver(), txtAccountCodeSource);
            ExtentTestManager.createAssertTestStepWithScreenshot("Click Account Code Source", Status.INFO, "Focused on 'Account Code Source' field", false);

            Actions actions = new Actions(BaseTest.getDriver());
            actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
            actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();

            Utilities.Click(BaseTest.getDriver(), txtPayablesAccountCode);
            ExtentTestManager.createAssertTestStepWithScreenshot("Click Payables Account Code", Status.INFO, "Focused on 'Payables Account Code' field", false);

            actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();

            Utilities.Click(BaseTest.getDriver(), btnRefresh);
            ExtentTestManager.createAssertTestStepWithScreenshot("Click Refresh", Status.PASS, "Clicked on 'Refresh' button", true);
            DynamicWait.smallWait();

            Utilities.Click(BaseTest.getDriver(), btnSearch);
            ExtentTestManager.createAssertTestStepWithScreenshot("Click Search Again", Status.PASS, "Clicked 'Search' again after refresh", true);
            DynamicWait.smallWait();

            Utilities.Click(BaseTest.getDriver(), chkSelectFirstItem);
            ExtentTestManager.createAssertTestStepWithScreenshot("Select Item", Status.PASS, "Selected first item checkbox", true);

            Utilities.Click(BaseTest.getDriver(), btnNext);
            ExtentTestManager.createAssertTestStepWithScreenshot("Click Next", Status.PASS, "Clicked on 'Next' button to proceed", true);
            DynamicWait.smallWait();

            BaseTest.getDriver().switchTo().frame(iframeRecvDataPost);
            Utilities.Click(BaseTest.getDriver(), chkPostFirstItem);
            ExtentTestManager.createAssertTestStepWithScreenshot("Click Post Item", Status.PASS, "Checked 'Post Item' successfully", true);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(multiPageIframeBrw);
            Utilities.Click(BaseTest.getDriver(), btnNext);
            ExtentTestManager.createAssertTestStepWithScreenshot("Click Next Again", Status.PASS, "Clicked on 'Next' button again to confirm", true);
            DynamicWait.smallWait();

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(iframeGridDialog);
            Utilities.Click(BaseTest.getDriver(), btnYes);
            ExtentTestManager.createAssertTestStepWithScreenshot("Click Yes", Status.PASS, "Clicked 'Yes' on confirmation dialog", true);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(iframeGridDialog);
            BaseTest.getDriver().switchTo().frame(iframeGridDialog);

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), confDlg);
            String confirmationMessage = confDlg.getText().trim();

            if (confirmationMessage.contains("successfully") || confirmationMessage.contains("Invoice") || confirmationMessage.contains("Posted")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Validation", Status.PASS, "Confirmation received: " + confirmationMessage, true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Validation", Status.FAIL, "Unexpected confirmation message: " + confirmationMessage, true);
            }

            ExtentTestManager.createAssertTestStepWithScreenshot("Process Receiving Flow", Status.PASS, "Process Receiving flow completed successfully", false);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Process Receiving Flow", Status.FAIL, "Process Receiving flow failed", true, e);
        } finally {
            BaseTest.getDriver().switchTo().defaultContent();
        }
    }

}

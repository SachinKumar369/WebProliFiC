package pages.accountPayables.TransactionReversal;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import com.github.jaiimageio.plugins.tiff.TIFFDecompressor;
import extentreports.ExtentTestManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utilities;

import java.time.Duration;

public class TransactionReversalPage extends Utilities {

    WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
    @FindBy(xpath = "//a[text()='Accounting']")
    private WebElement accounting;
    @FindBy(id = "divmodule_1")
    private WebElement accountPayble;
    @FindBy(xpath = "//a[@title='AP05 Transaction Reversal']")
    private WebElement transactionReversal;
    @FindBy(id = "cphBody_cphChildBody_imgPayablesAcctCodeDesc")
    private WebElement imgPayablesAcctCodeDesc;
    @FindBy(id = "cphBody_cphChildBody_txtPayablesAccountCode")
    private WebElement txtPayablesAccountCode;
    @FindBy(xpath = "//button[normalize-space()='OK']")
    private WebElement btnOkDialog;
    @FindBy(xpath = "//td[normalize-space()='3 W EVENTS']")
    private WebElement cell3WEvents;
    @FindBy(id = "cphBody_cphChildBody_btnSearch")
    private WebElement btnSearch;
    @FindBy(id = "cphBody_cphMasterToolBar_btnSave")
    private WebElement btnSave;
    @FindBy(xpath = "//td[normalize-space()='9% CGST INPUT TAX CREDIT']")
    private WebElement cell9CGST;
    @FindBy(xpath = "//td[normalize-space()='9% SGST INPUT TAX CREDIT']")
    private WebElement cell9SGST;
    @FindBy(xpath = "//td[contains(@id, 'td_0_3')]")
    private WebElement cellSep25Consultancy;
    @FindBy(xpath = "//*[text()='Reverse Payment']")
    private WebElement linkReversePayment;
    @FindBy(id = "cphBody_cphChildBody_imgPaymentVoucherNumber")
    private WebElement imgPaymentVoucherNumber;
    @FindBy(id = "td_0_0")
    private WebElement cellFirstRow;

    public TransactionReversalPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void ReversePayament() {
        try {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FindBy(id = "tbl3")
    private WebElement reverseDebitCredit;
    @FindBy(id = "MultiPageiframeBrw")
    private WebElement payablesFrame;

    @FindBy(id = "cphBody_cphChildBody_txtPayablesAccountCode")
    private WebElement payablesAccountCode;

    @FindBy(id = "iframeGridDialog")
    private WebElement gridDialogFrame;

    @FindBy(xpath = "//*[text()='3 W EVENTS']")
    private WebElement threeWEventsCell;

    @FindBy(id = "cphBody_cphChildBody_btnSearch")
    private WebElement searchButton;

    @FindBy(id = "td_0_3")
    private WebElement accountCodeCell;

    @FindBy(id = "cphBody_cphMasterToolBar_btnSave")
    private WebElement saveButton;
    public void ReverseCreditDebitNote() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Click(reverseDebitCredit);

      // Switch to main content and then to payables frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(payablesFrame);

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), payablesAccountCode);
            Utilities.Click(BaseTest.getDriver(), payablesAccountCode);
            Utilities.SendKeys(BaseTest.getDriver(), payablesAccountCode, "%%");
            payablesAccountCode.sendKeys(Keys.TAB);

            // Switch to grid dialog frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(gridDialogFrame);

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), threeWEventsCell);
            Utilities.DoubleClick(threeWEventsCell);

            // Back to payables frame for search and selection
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(payablesFrame);

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), searchButton);
            Utilities.Click(BaseTest.getDriver(), searchButton);

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), accountCodeCell);
            Utilities.Click(BaseTest.getDriver(), accountCodeCell);

            // Handle popup trigger
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), saveButton);
            Utilities.Click(BaseTest.getDriver(), saveButton);



            ExtentTestManager.createAssertTestStepWithScreenshot("Select Payables Account Code",
                    Status.PASS, "Payables Account successfully selected and popup opened.", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Select Payables Account Code",
                    Status.FAIL, "Failed to select payables account.", true, e);
        }
    }

    public void accountPayable() {
        try {

            BaseTest.getDriver().manage().window().maximize();
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeBrw"));
            Click(accounting);
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeBrw"));


            Click(accountPayble);
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeBrw"));
            Click(transactionReversal);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Create AP Account", Status.FAIL, "Issue while creating AP Account", true, e);
        }
    }

    public void ReversePJV() {
        try {
            // Switch to parent frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");


            // Fill Payables Account Code
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), txtPayablesAccountCode);
            Utilities.SendKeys(BaseTest.getDriver(), txtPayablesAccountCode, "%%");
            BaseTest.getDriver().switchTo().activeElement().sendKeys(Keys.TAB);


            // Switch to iframeGridDialog -> double-click cell
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), cell3WEvents);
            Utilities.DoubleClick(cell3WEvents);

            // Search and Save operations
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(BaseTest.getDriver(), btnSearch);


            // Final Save before popup
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(BaseTest.getDriver(), cellSep25Consultancy);
            Utilities.Click(BaseTest.getDriver(), btnSave);

            ExtentTestManager.createAssertTestStepWithScreenshot("Perform Payables Account Update", Status.PASS, "Payables account update completed successfully", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Perform Payables Account Update", Status.FAIL, "Failed to complete Payables account update", true, e);
        }
    }

    public void performReversePayment() {
        try {
            // Switch to main iframe
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Click "Reverse Payment" text
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), linkReversePayment);
            Utilities.Click(BaseTest.getDriver(), linkReversePayment);

            // Switch to main iframe
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            // Click Payment Voucher Number icon
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), imgPaymentVoucherNumber);
            Utilities.Click(BaseTest.getDriver(), imgPaymentVoucherNumber);

            // Switch to iframeGridDialog and select the first row
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), cellFirstRow);
            Utilities.DoubleClick(cellFirstRow);

            // Switch back to MultiPageiframeBrw and click Search
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), btnSearch);
            Utilities.Click(BaseTest.getDriver(), btnSearch);

            // Click Save (popup expected)
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), btnSave);
            Utilities.Click(BaseTest.getDriver(), btnSave);

            ExtentTestManager.createAssertTestStepWithScreenshot("Perform Reverse Payment", Status.PASS, "Reverse payment completed successfully", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Perform Reverse Payment", Status.FAIL, "Failed to complete reverse payment", true, e);
        }
    }


}

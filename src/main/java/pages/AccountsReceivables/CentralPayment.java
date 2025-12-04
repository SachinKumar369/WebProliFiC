package pages.AccountsReceivables;



import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DynamicWait;
import utils.Utilities;

/**
 * Page object for Accounts Receivable -> Transaction Entry flows.
 * All interactions use framework utilities (Utilities, DynamicWait) and report via ExtentTestManager.
 */
public class CentralPayment {

    private WebDriver driver;

    public CentralPayment(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }

    // Top-level iframe containers (we switch into these frames before interacting)
    // Elements inside frames are declared here (we will switch to proper frame before using them)

    // Links within MultiPageiframeBrw


    @FindBy(id = "divmodule_2")
    private WebElement accountsReceivablesLink;

    @FindBy(xpath = "//*[text()='Transaction Entry']")
    private WebElement transactionEntryLink;

    // Button Add inside MultiPageiframeBrw

    @FindBy(id = "cphBrowserHeader_btnAddNew")
    private WebElement addButton;

    // Dialog elements (inside MultiPageiframeDlg)
    @FindBy(id = "cphBody_txtDrAccCode")
    private WebElement drAccCodeField;

    // iframeGridDialog grid cell: EASY TRIP PLANNERS
    @FindBy(id = "td_0_1")
    private WebElement easyTripPlannersCell;

    @FindBy(id = "cphBody_chkShowSplit")
    private WebElement chkShowSplit;

    @FindBy(id = "cphBody_txtTranNote")
    private WebElement txtTranNote;

    @FindBy(id = "cphBody_txtCity")
    private WebElement txtCity;

    @FindBy(id = "cphBody_txtCheqNo")
    private WebElement txtCheqNo;

    @FindBy(id = "cphBody_txtref_cheque_no")
    private WebElement txtRefChequeNo;

    @FindBy(id = "cphBody_txtTranDate")
    private WebElement txtTranDate;

    @FindBy(id = "cphBody_txtcheque_clear_dt")
    private WebElement txtChequeClearDate;

    @FindBy(id = "cphBody_txtChequeDate")
    private WebElement txtChequeDate;

    @FindBy(id = "cphBody_txtBankId")
    private WebElement txtBankId;

    // iframeGridDialog OK button
    @FindBy(xpath = "//button[normalize-space()='OK']")
    private WebElement gridOkButton;

    // iframeGridDialog row image/button (press Enter mapped to click via Utilities.Click)
    @FindBy(id = "imgRow_0")
    private WebElement imgRow0;

    // iframeDtl fields inside MultiPageiframeDlg -> iframeDtl
    @FindBy(id = "ar_ref_particulars_2_0")
    private WebElement arRefParticulars_2_0;

    @FindBy(id = "fx_ar_taxable_amount_6_0")
    private WebElement fxArTaxableAmount_6_0;

    // iframeSplitTran fields inside MultiPageiframeDlg -> iframeSplitTran
    @FindBy(id = "ar_id_0_0")
    private WebElement arId_0_0;

    @FindBy(id = "fx_amount_2_0")
    private WebElement fxAmount_2_0;

    @FindBy(id = "ar_id_0_1")
    private WebElement arId_0_1;

    @FindBy(id = "fx_amount_2_1")
    private WebElement fxAmount_2_1;

    @FindBy(xpath = "//button[normalize-space()='Add Row']")
    private WebElement addRowButton;

    @FindBy(xpath = "//button[normalize-space()='Save & Close']")
    private WebElement saveAndCloseButton;
    @FindBy(id = "LinkProduct0")
    private WebElement accounting;

    /**
     * Perform the full transaction entry flow described in the Playwright script.
     */
    public void performTransactionEntry() {
        try {
            // Start action - assume driver is already at the app login/landing page as in Playwright flow
            DynamicWait.longWait();

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            Utilities.Click(accounting);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), accountsReceivablesLink);
            Utilities.Click(BaseTest.getDriver(), accountsReceivablesLink);

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), transactionEntryLink);
            Utilities.Click(BaseTest.getDriver(), transactionEntryLink);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), addButton);
            Utilities.Click(BaseTest.getDriver(), addButton);

            // Back to default content then into dialog frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            // Enter Dr Account Code (fill '%%' and tab)
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), drAccCodeField);
            Utilities.Click(BaseTest.getDriver(), drAccCodeField);
            Utilities.SendKeys(BaseTest.getDriver(), drAccCodeField, "%%");
            drAccCodeField.sendKeys(Keys.TAB);

            // Select EASY TRIP PLANNERS from grid dialog (iframeGridDialog)
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), easyTripPlannersCell);
            // Double click the grid cell
            Utilities.DoubleClick(easyTripPlannersCell);

            // Return to dialog frame after selecting account
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            // Check show split
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), chkShowSplit);
            Utilities.Click(BaseTest.getDriver(), chkShowSplit); // Using Click since it's a checkbox; Utilities.check not available

            // Fill transaction note
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), txtTranNote);
            Utilities.Click(BaseTest.getDriver(), txtTranNote);
            Utilities.SendKeys(BaseTest.getDriver(), txtTranNote, "NOTE");

            // City - fill "OL" and tab
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), txtCity);
            Utilities.Click(BaseTest.getDriver(), txtCity);
            Utilities.SendKeys(BaseTest.getDriver(), txtCity, "OL");

            // Cheque number and reference
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), txtCheqNo);
            Utilities.SendKeys(BaseTest.getDriver(), txtCheqNo, "11212");

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), txtRefChequeNo);
            Utilities.Click(BaseTest.getDriver(), txtRefChequeNo);
            Utilities.SendKeys(BaseTest.getDriver(), txtRefChequeNo, "DJDDJ");

            // Tran Date copy/paste flow replaced by direct fills to target date fields
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), txtTranDate);
            Utilities.Click(BaseTest.getDriver(), txtTranDate);
            // Leave as is (original script copies then pastes); we'll directly set the clear and cheque dates

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), txtChequeClearDate);
            Utilities.Click(BaseTest.getDriver(), txtChequeClearDate);
            Utilities.SendKeys(BaseTest.getDriver(), txtChequeClearDate, "05/11/2025");

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), txtChequeDate);
            Utilities.Click(BaseTest.getDriver(), txtChequeDate);
            Utilities.SendKeys(BaseTest.getDriver(), txtChequeDate, "05/11/2025");

            // Bank ID
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), txtBankId);
            Utilities.Click(BaseTest.getDriver(), txtBankId);
            Utilities.SendKeys(BaseTest.getDriver(), txtBankId, "HDFC");
            txtBankId.sendKeys(Keys.TAB);

            // Some grid interaction: press Enter on imgRow_0 -> use Click to simulate selection
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), imgRow0);
            Utilities.DoubleClick(imgRow0);

            // Return to dialog and enter details in iframeDtl
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            // switch into iframeDtl
            BaseTest.getDriver().switchTo().frame("iframeDtl");

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), arRefParticulars_2_0);
            Utilities.Click(BaseTest.getDriver(), arRefParticulars_2_0);
            Utilities.SendKeys(BaseTest.getDriver(), arRefParticulars_2_0, "PARTICULAR");

            fxArTaxableAmount_6_0.sendKeys(Keys.CONTROL+"A"+Keys.DELETE);
            Utilities.SendKeys(BaseTest.getDriver(), fxArTaxableAmount_6_0, "2000");

            // Now handle split transaction iframe
            // First go back to dialog default then into iframeSplitTran
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            BaseTest.getDriver().switchTo().frame("iframeSplitTran");

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), arId_0_0);
            Utilities.Click(BaseTest.getDriver(), arId_0_0);
            Utilities.SendKeys(BaseTest.getDriver(), arId_0_0, "360one");

//            // Switch back to dialog's grid dialog to press OK (the Playwright clicked OK in iframeGridDialog earlier)
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), gridOkButton);
//            Utilities.Click(BaseTest.getDriver(), gridOkButton);

//            // Back to split frame, adjust entries
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            BaseTest.getDriver().switchTo().frame("iframeSplitTran");

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), arId_0_0);
            Utilities.SendKeys(BaseTest.getDriver(), arId_0_0, "ADANI");

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), fxAmount_2_0);
            Utilities.SendKeys(BaseTest.getDriver(), fxAmount_2_0, "1000");

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), addRowButton);
            Utilities.Click(BaseTest.getDriver(), addRowButton);

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), arId_0_1);
            Utilities.SendKeys(BaseTest.getDriver(), arId_0_1, "360one");

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), fxAmount_2_1);
            Utilities.Click(BaseTest.getDriver(), fxAmount_2_1);
            Utilities.SendKeys(BaseTest.getDriver(), fxAmount_2_1, "1000");

            // Save & Close (still inside MultiPageiframeDlg)
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), saveAndCloseButton);
            Utilities.Click(BaseTest.getDriver(), saveAndCloseButton);

            // Report success
            ExtentTestManager.createAssertTestStepWithScreenshot("Transaction Entry", Status.PASS, "Transaction entry flow completed successfully", true);

            // Ensure we return to default content
            BaseTest.getDriver().switchTo().defaultContent();

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Transaction Entry", Status.FAIL, "Transaction entry flow failed: " + e.getMessage(), true, e);
            // ensure frame context reset
            try {
                BaseTest.getDriver().switchTo().defaultContent();
            } catch (Exception ignored) {}
            throw new RuntimeException(e);
        }
    }
}


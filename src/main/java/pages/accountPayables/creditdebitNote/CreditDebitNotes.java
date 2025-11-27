package pages.accountPayables.creditdebitNote;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utilities;

import java.io.File;
import java.time.Duration;

public class CreditDebitNotes extends Utilities {

    WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
    @FindBy(xpath = "//a[text()='Accounting']")
    private WebElement accounting;
    @FindBy(id = "divmodule_1")
    private WebElement accountPayble;
    @FindBy(xpath = "//a[@title='AP10 Process Payment']")
    private WebElement processPayment;
    @FindBy(id = "cphBody_btnSearch")
    private WebElement search;
    // ======== Browse frame (#MultiPageiframeBrw) ========
    @FindBy(id = "cphBrowserHeader_btnAddNew")
    private WebElement addButton;
    // ======== Dialog frame (#MultiPageiframeDlg) ========
    @FindBy(id = "cphBody_txtap_id")
    private WebElement apIdField;
    @FindBy(id = "cphBody_txtap_amount")
    private WebElement amountField;
    @FindBy(id = "cphBody_txtPartucularsNote")
    private WebElement particularsNoteField;
    @FindBy(id = "cphBody_txtsupplier_bill_no")
    private WebElement supplierBillField;
    @FindBy(id = "cphBody_imgsupplier_bill_no")
    private WebElement supplierBillLookupImg;
    @FindBy(id = "acct_code_2_0")
    private WebElement accountCodeField;
    // Inner deduction iframe: #iframeDed
    @FindBy(id = "img_description_1_0")
    private WebElement deductionDescriptionLookupImg;
    // Footer buttons in dialog
    @FindBy(id = "cphDialogHeader_btnSaveClose")
    private WebElement saveAndCloseButton;
    // ======== Grid dialog frame (iframe[name='iframeGridDialog'] / #iframeGridDialog) ========
    @FindBy(xpath = "//*[self::td or self::div][normalize-space()='3 W EVENTS']")
    private WebElement cell3WEvents;
    //    @FindBy(xpath = "//*[self::td or self::div][normalize-space()='25-26/3W/012']")
//    private WebElement cellSupplierBill_2526_3W_012;
    @FindBy(id = "td_0_3")
    private WebElement cellSupplierBill_2526_3W_012;
    @FindBy(xpath = "//*[self::td or self::div][normalize-space()='SHARE CAPITAL']")
    private WebElement cellShareCapital;
    @FindBy(xpath = "//*[self::td or self::div][normalize-space()='TDS on Contractor 2%']")
    private WebElement cellTdsOnContractor2;
    @FindBy(id = "btnOK")
    private WebElement okButton;
    @FindBy(xpath = "//a[@title='AP16 Post Debit & Credit Notes']")
    private WebElement postcreditdebit;
    /**
     * Single action method that performs the entire AP entry flow shown in the Playwright script.
     * Equivalent to:
     * - Click Add in browse frame
     * - In dialog: search/select AP Id ('3 W EVENTS'), enter amount, particulars
     * - Select Supplier Bill No ('25-26/3W/012')
     * - Select Account Code ('SHARE CAPITAL')
     * - Select Deduction ('TDS on Contractor 2%')
     * - Save & Close and confirm OK
     */
    @FindBy(id = "cphBody_rdoCreditNote")
    private WebElement creditCheckbox;
    @FindBy(id = "cphBody_rdoDebitNote")
    private WebElement debitCheckbox;
    @FindBy(id = "deduction_type_0_0")
    private WebElement deduction;
    @FindBy(id = "td_5_1")
    private WebElement tds;
    @FindBy(id = "cphBody_chkRC_App")
    private WebElement rcmSelect;
    @FindBy(id = "hsn_code_0_0")
    private WebElement rcmGST;
    @FindBy(id = "cphBody_lblClickHereForUploadDownloadExcelData")
    private WebElement uploadExcel;
    @FindBy(id = "FileDlg")
    private WebElement uploadFile;
    @FindBy(id = "btnUpLoadData")
    private WebElement uploadButton;

    public CreditDebitNotes(WebDriver driver) {
        PageFactory.initElements(driver, this);
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
            Click(postcreditdebit);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Create AP Account", Status.FAIL, "Issue while creating AP Account", true, e);
        }
    }

    // ======== Frame helpers ========
    private void toDefault() {
        BaseTest.getDriver().switchTo().defaultContent();
    }

    private void toBrowseFrame() {
        BaseTest.getDriver().switchTo().defaultContent();
        BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
    }

    private void toDialogFrame() {
        BaseTest.getDriver().switchTo().defaultContent();
        BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
    }

    private void toGridDialogFrame() {
        BaseTest.getDriver().switchTo().defaultContent();
        BaseTest.getDriver().switchTo().frame("iframeGridDialog");
    }

    private void toDeductionInnerFrame() {
        // inside dialog frame
        BaseTest.getDriver().switchTo().defaultContent();
        BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
        BaseTest.getDriver().switchTo().frame("iframeDed");
    }

    public void performAPEntryFlow(NoteType noteType) {
        ExtentTestManager.createAssertTestStepWithScreenshot("Start AP Entry Flow", Status.INFO, "Navigating and executing AP entry flow", true);

        try {


            // Click Add in browse frame
            toBrowseFrame();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), addButton);
            Utilities.Click(BaseTest.getDriver(), addButton);


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            WebElement target = (noteType == NoteType.CREDIT) ? creditCheckbox : debitCheckbox;
            wait.until(ExpectedConditions.elementToBeClickable(target)).click();

            // Optional: ensure the checkbox is selected (idempotent)
            if (!target.isSelected()) {
                target.click();
            }

            // Fill AP Id and pick '3 W EVENTS' from grid dialog
            toDialogFrame();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), apIdField);
            Utilities.SendKeys(BaseTest.getDriver(), apIdField, "%%");
            BaseTest.getDriver().switchTo().activeElement().sendKeys(Keys.TAB);


            // Grid opens -> choose "3 W EVENTS"
            toGridDialogFrame();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), cell3WEvents);
            Utilities.DoubleClick(cell3WEvents);

            // Amount
            toDialogFrame();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), amountField);
            amountField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

            Utilities.SendKeys(BaseTest.getDriver(), amountField, "100");

            // Particulars note
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), particularsNoteField);
            Utilities.SendKeys(BaseTest.getDriver(), particularsNoteField, "particulars");

//            // Supplier Bill No -> open lookup -> pick bill -> OK
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), supplierBillField);
//            Utilities.SendKeys(BaseTest.getDriver(), supplierBillField, "%%");
//
//            toGridDialogFrame();
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), okButton);
//            Utilities.Click(BaseTest.getDriver(), okButton);

            toDialogFrame();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), supplierBillLookupImg);
            Utilities.Click(BaseTest.getDriver(), supplierBillLookupImg);

            toGridDialogFrame();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), cellSupplierBill_2526_3W_012);
            Utilities.DoubleClick(cellSupplierBill_2526_3W_012);

            // Account code -> 'SHARE CAPITAL'
            toDialogFrame();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), accountCodeField);
            Utilities.Click(BaseTest.getDriver(), accountCodeField);
            Utilities.SendKeys(BaseTest.getDriver(), accountCodeField, "%%");
            BaseTest.getDriver().switchTo().activeElement().sendKeys(Keys.TAB);


            toGridDialogFrame();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), cellShareCapital);
            Utilities.DoubleClick(cellShareCapital);

//            // Deduction -> inner frame lookup -> 'TDS on Contractor 2%'
//            toDeductionInnerFrame();
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), deductionDescriptionLookupImg);
//            Utilities.Click(BaseTest.getDriver(), deductionDescriptionLookupImg);
//
//            toGridDialogFrame();
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), cellTdsOnContractor2);
//            Utilities.DoubleClick(cellTdsOnContractor2);

            // Save & Close -> OK
            toDialogFrame();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), saveAndCloseButton);
            Utilities.Click(BaseTest.getDriver(), saveAndCloseButton);

            toGridDialogFrame();
            //Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), okButton);
            Utilities.Click(BaseTest.getDriver(), okButton);

            toDefault();
            ExtentTestManager.createAssertTestStepWithScreenshot("AP Entry Flow", Status.PASS, "AP Entry completed successfully", true);

        } catch (Exception e) {
            toDefault();
            ExtentTestManager.createAssertTestStepWithScreenshot("AP Entry Flow", Status.FAIL, "AP Entry failed: " + e.getMessage(), true, e);
        }
    }

    public void withTax() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            BaseTest.getDriver().switchTo().frame("iframeDed");

            SendKeys(deduction, "%%");
            BaseTest.getDriver().switchTo().activeElement().sendKeys(Keys.TAB);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void rcmFlow(NoteType noteType) {
        ExtentTestManager.createAssertTestStepWithScreenshot("Start AP Entry Flow", Status.INFO, "Navigating and executing AP entry flow", true);

        try {


            // Click Add in browse frame
            toBrowseFrame();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), addButton);
            Utilities.Click(BaseTest.getDriver(), addButton);


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            WebElement target = (noteType == NoteType.CREDIT) ? creditCheckbox : debitCheckbox;
            wait.until(ExpectedConditions.elementToBeClickable(target)).click();

            // Optional: ensure the checkbox is selected (idempotent)
            if (!target.isSelected()) {
                target.click();
            }

            // Fill AP Id and pick '3 W EVENTS' from grid dialog
            toDialogFrame();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), apIdField);
            Utilities.SendKeys(BaseTest.getDriver(), apIdField, "%%");
            BaseTest.getDriver().switchTo().activeElement().sendKeys(Keys.TAB);


            // Grid opens -> choose "3 W EVENTS"
            toGridDialogFrame();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), cell3WEvents);
            Utilities.DoubleClick(cell3WEvents);

            // Amount
            toDialogFrame();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), amountField);
            amountField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

            Utilities.SendKeys(BaseTest.getDriver(), amountField, "100");

            // Particulars note
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), particularsNoteField);
            Utilities.SendKeys(BaseTest.getDriver(), particularsNoteField, "particulars");


            Utilities.Click(rcmSelect);

//            // Supplier Bill No -> open lookup -> pick bill -> OK
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), supplierBillField);
//            Utilities.SendKeys(BaseTest.getDriver(), supplierBillField, "%%");
//
//            toGridDialogFrame();
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), okButton);
//            Utilities.Click(BaseTest.getDriver(), okButton);

            toDialogFrame();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), supplierBillLookupImg);
            Utilities.Click(BaseTest.getDriver(), supplierBillLookupImg);

            toGridDialogFrame();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), cellSupplierBill_2526_3W_012);
            Utilities.DoubleClick(cellSupplierBill_2526_3W_012);

            // Account code -> 'SHARE CAPITAL'
            toDialogFrame();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), accountCodeField);
            Utilities.Click(BaseTest.getDriver(), accountCodeField);
            Utilities.SendKeys(BaseTest.getDriver(), accountCodeField, "%%");
            BaseTest.getDriver().switchTo().activeElement().sendKeys(Keys.TAB);


            toGridDialogFrame();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), cellShareCapital);
            Utilities.DoubleClick(cellShareCapital);


            // select rcm gst

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            BaseTest.getDriver().switchTo().frame("iframeVAT");
            Utilities.SendKeys(rcmGST, "RST2");
            rcmGST.sendKeys(Keys.TAB);


//            // Deduction -> inner frame lookup -> 'TDS on Contractor 2%'
//            toDeductionInnerFrame();
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), deductionDescriptionLookupImg);
//            Utilities.Click(BaseTest.getDriver(), deductionDescriptionLookupImg);
//
//            toGridDialogFrame();
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), cellTdsOnContractor2);
//            Utilities.DoubleClick(cellTdsOnContractor2);

            // Save & Close -> OK
            toDialogFrame();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), saveAndCloseButton);
            Utilities.Click(BaseTest.getDriver(), saveAndCloseButton);

            toGridDialogFrame();
            //Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), okButton);
            Utilities.Click(BaseTest.getDriver(), okButton);

            toDefault();
            ExtentTestManager.createAssertTestStepWithScreenshot("AP Entry Flow", Status.PASS, "AP Entry completed successfully", true);

        } catch (Exception e) {
            toDefault();
            ExtentTestManager.createAssertTestStepWithScreenshot("AP Entry Flow", Status.FAIL, "AP Entry failed: " + e.getMessage(), true, e);
        }
    }

    public void xlUpload() {
        try {
            String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "UploadData" + File.separator + "CN.xml";

            toBrowseFrame();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), addButton);
            Utilities.Click(BaseTest.getDriver(), addButton);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(uploadExcel);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            BaseTest.getDriver().switchTo().frame("Iframe1");
            Utilities.SendKeys(uploadFile, filePath);
            Utilities.Click(uploadButton);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AP Entry Flow", Status.FAIL, "AP Entry failed: " + e.getMessage(), true, e);
        }
    }

    public enum NoteType {CREDIT, DEBIT}

}

package pages.accountPayables.PJV;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Dateformat;
import utils.DynamicWait;
import utils.Utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;


public class TaxDeductionPage {

    // ---- Frame names/IDs used in this flow ----
    private static final String FRAME_GRID_DIALOG = "iframeGridDialog";
    private static final String FRAME_MULTI_PAGE = "MultiPageiframeDlg";
    private static final String FRAME_DETAIL = "iframeDtl";
    // ---------- Elements inside GRID DIALOG frame ----------
    @FindBy(xpath = "//*[text()='(...)']")
    private WebElement moreOptionsTextCell;
    @FindBy(xpath = "//*[normalize-space()='TDS on Contractor 2%']")
    private WebElement tdsOnContractorCell;
    @FindBy(css = "#col_1")
    private WebElement hsnFilterColumn;
    @FindBy(css = "#FilterTD_1_1 img")
    private WebElement filterIconImg;
//    @FindBy(xpath = "//*[normalize-space()='GST 28 % HSN']")
//    private WebElement gst28HsnCell;

    @FindAll({@FindBy(id = "td_0_1"), @FindBy(xpath = "//*[normalize-space()='GST 28 % HSN']")})
    private WebElement gst28HsnCell;


    @FindBy(xpath = "//*[self::button or self::input][normalize-space()='OK']")
    private WebElement okButtonOnDialog;
    // ---------- Elements inside MULTI-PAGE frame ----------
    @FindBy(css = "#cphBody_imgtax_name_1")
    private WebElement taxNamePickerIcon;
    @FindBy(css = "#cphBody_imphsn_code_1")
    private WebElement hsnPickerIcon;
    @FindBy(xpath = "//*[self::button or self::input][normalize-space()='Save & Close']")
    private WebElement saveAndCloseButton;
    // ---------- Elements inside DETAIL (nested) frame ----------
    @FindBy(css = "#acct_code_3_3")
    private WebElement accountCodeInput;
    @FindBy(xpath = "//*[normalize-space()='ICICI BANK LOAN 76CR']")
    private WebElement iciciBankLoanCell;
    // ===== inside frame: MultiPageiframeBrw =====
    @FindBy(id = "cphBrowserHeader_btnAddNew")
    private WebElement addButton;
    // ===== inside frame: MultiPageiframeDlg (dialog) =====
    @FindBy(id = "cphBody_txtap_id")
    private WebElement supplierField;
    @FindBy(id = "cphBody_txtap_particulars")
    private WebElement particularsField;
    @FindBy(id = "supplier_bill_no_0_0")
    private WebElement supplierBillNoField;
    @FindBy(id = "supplier_bill_dt_1_0")
    private WebElement supplierBillDateField;
    @FindBy(id = "img_supplier_bill_dt_1_0")
    private WebElement supplierBillDatePickerIcon;

    // ================= Public flow methods =================
    @FindBy(id = "supplier_bill_amount_3_0")
    private WebElement supplierBillAmountField;
    @FindBy(id = "cphFooter_tdDatetime")
    private WebElement getCurrentDate;
    @FindBy(xpath = "//*[starts-with(@id, \"td_\") and substring(@id, string-length(@id)-1) = \"_1\"]")
    private List<WebElement> supplierCells;
    @FindBy(id = "lblErrorDesc")
    private WebElement confDlg;
    @FindBy(id = "acct_code_3_0")
    private WebElement accountCodeCell;
    @FindBy(id = "btnOK")
    private WebElement okButtonInGridDialog;
    @FindBy(id = "td_5_1")
    private WebElement tds;
    // ---------- Dialog (MultiPageiframeDlg) ----------
    @FindBy(id = "cphBody_txthsn_code_1")
    private WebElement hsnCodeField1;
    @FindBy(xpath = "//a[normalize-space()='Close']")
    private WebElement closeLink;
    // ---------- Details frame (iframeDtl) ----------
    @FindBy(xpath = "//button[normalize-space()='Add Row']")
    private WebElement addRowButton;
    @FindBy(id = "acct_code_3_4")
    private WebElement acctCode_3_4;
    @FindBy(id = "acct_code_3_3")
    private WebElement acctCode_3_3;
    @FindBy(id = "ap_particulars_12_0")
    private WebElement particulars_12_0;
    @FindBy(id = "ap_particulars_12_1")
    private WebElement particulars_12_1;
    @FindBy(id = "ap_particulars_12_2")
    private WebElement particulars_12_2;
    // ---------- Grid dialog (iframeGridDialog) ----------
    @FindBy(xpath = "//td[normalize-space()='TOYATO FIN. SERV. LTD.- 2']")
    private WebElement supplierCell_Toyato;
    @FindBy(xpath = "//td[normalize-space()='PROFIT/ LOSS - CURRENT YEAR']")
    private WebElement accountCell_PLCurrentYear;
    @FindBy(xpath = "//button[normalize-space()='OK']")
    private WebElement okButtonInGrid;
    @FindBy(id = "cphDialogHeader_btnSaveClose")
    private WebElement saveclose;
    @FindBy(id = "cphBody_chkRC_App")
    private WebElement rcm;
    @FindBy(id = "cphBody_lblMore_1")
    private WebElement more;
    @FindBy(id = "btnAddRow")
    private WebElement addRow;
    @FindBy(id = "hsn_code_1_2")
    private WebElement sendGst;
    @FindBy(id = "hsn_code_1_4")
    private WebElement sendGst1;
    @FindBy(id = "btnOK")
    private WebElement okBtn;
    @FindBy(xpath = "//input[starts-with(@id, 'acct_code_')]")
    private List<WebElement> accountCodeGL;
    @FindBy(xpath = "//a[@title='AP80 GST Hold Payment']")
    private WebElement gstonhold;
    @FindBy(css = "[id^='hold_flag_10_']")
    private List<WebElement> holdFlagElements;
    @FindBy(id = "cphDialogHeader_btnSave")
    private WebElement saveBtn;
    @FindBy(id = "cphBody_btnSearch")
    private WebElement search;
    @FindBy(id = "hold_flag_10_0")
    private WebElement holdFlag;
    @FindBy(id = "cphBody_txtap_id_from")
    private WebElement apAccountFrom;
    @FindBy(id = "cphBody_txtap_id_to")
    private WebElement apAccountTo;
    @FindBy(id = "divmodule_1")
    private WebElement accountPayable;

    public TaxDeductionPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // ======= Helper: Frame switching (required pattern) =======
    private void switchToDefault() {
        BaseTest.getDriver().switchTo().defaultContent();
    }

    private void switchToFrame(String frameName) {
        BaseTest.getDriver().switchTo().frame(frameName);
    }

    private void switchToMultiPage() {
        switchToDefault();
        switchToFrame(FRAME_MULTI_PAGE);
    }

    private void switchToGridDialog() {
        switchToDefault();
        switchToFrame(FRAME_GRID_DIALOG);
    }

    private void switchToDetailInsideMultiPage() {
        switchToDefault();
        switchToFrame(FRAME_MULTI_PAGE);
        BaseTest.getDriver().switchTo().frame(FRAME_DETAIL);
    }

    public void openTaxNamePicker() {
        try {
            switchToMultiPage();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), taxNamePickerIcon);
            Utilities.Click(BaseTest.getDriver(), taxNamePickerIcon);

            switchToGridDialog();

            Utilities.DoubleClick(tds);


//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), moreOptionsTextCell);
//            Utilities.Click(BaseTest.getDriver(), moreOptionsTextCell);

            ExtentTestManager.createAssertTestStepWithScreenshot("Open Tax Picker", Status.PASS, "Opened tax name picker", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Open Tax Picker", Status.FAIL, "Failed to open tax name picker", true, e);
        }
    }

    public void selectTax_TDSOnContractor2Pct1() {
        try {
            switchToMultiPage();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), taxNamePickerIcon);
            Utilities.Click(BaseTest.getDriver(), taxNamePickerIcon);
            switchToGridDialog();
            Utilities.DoubleClick(tds);


            ExtentTestManager.createAssertTestStepWithScreenshot("Select Tax", Status.PASS, "Selected 'TDS on Contractor 2%'", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Select Tax", Status.FAIL, "Failed to select 'TDS on Contractor 2%'", true, e);
        }
    }

    public void selectTax_TDSOnContractor2Pct() {
        try {
            // Navigate to the correct frame and open the Tax picker
            switchToMultiPage();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), taxNamePickerIcon);
            Utilities.Click(BaseTest.getDriver(), taxNamePickerIcon);
            ExtentTestManager.createAssertTestStepWithScreenshot("Open Tax Picker", Status.INFO, "Clicked on the Tax Name picker icon.", false);

            // Switch to grid dialog and select TDS on Contractor 2%
            switchToGridDialog();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), tds);
            Utilities.DoubleClick(tds);
            ExtentTestManager.createAssertTestStepWithScreenshot("Select Tax", Status.PASS, "Selected 'TDS on Contractor 2%'.", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Select Tax", Status.FAIL, "Failed to select 'TDS on Contractor 2%'.", true, e);
        } finally {
            // Return to default content (recommended for stability)
            BaseTest.getDriver().switchTo().defaultContent();
            DynamicWait.smallWait();
        }
    }

    public void openHSNPickerAndFilterGST() {
        try {
            // Open HSN picker
            switchToMultiPage();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), hsnPickerIcon);
            Utilities.Click(BaseTest.getDriver(), hsnPickerIcon);

            // Filter as "GST"
            switchToGridDialog();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), hsnFilterColumn);
            Utilities.Click(BaseTest.getDriver(), hsnFilterColumn);
            //DynamicWait.smallWait();
            Utilities.Click(BaseTest.getDriver(), hsnFilterColumn);
            Utilities.SendKeys(BaseTest.getDriver(), hsnFilterColumn, "GST");

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), filterIconImg);
            Utilities.Click(BaseTest.getDriver(), filterIconImg);

            ExtentTestManager.createAssertTestStepWithScreenshot("HSN Filter", Status.PASS, "Filtered HSN with 'GST'", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("HSN Filter", Status.FAIL, "Failed to filter HSN with 'GST'", true, e);
        }
    }

    public void rcmGST() {
        try {
            // Open HSN picker
            switchToMultiPage();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), hsnPickerIcon);
            Utilities.Click(BaseTest.getDriver(), hsnPickerIcon);

            // Filter as "GST"
            switchToGridDialog();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), hsnFilterColumn);
            Utilities.Click(BaseTest.getDriver(), hsnFilterColumn);
            //DynamicWait.smallWait();
            Utilities.Click(BaseTest.getDriver(), hsnFilterColumn);
            Utilities.SendKeys(BaseTest.getDriver(), hsnFilterColumn, "RCM");

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), filterIconImg);
            Utilities.Click(BaseTest.getDriver(), filterIconImg);

            ExtentTestManager.createAssertTestStepWithScreenshot("HSN Filter", Status.PASS, "Filtered HSN with 'GST'", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("HSN Filter", Status.FAIL, "Failed to filter HSN with 'GST'", true, e);
        }
    }

    public void selectHSN_GST28() {
        try {
            switchToGridDialog();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), gst28HsnCell);
            Utilities.DoubleClick(gst28HsnCell);

            switchToMultiPage();
            Utilities.Click(saveclose);
            ExtentTestManager.createAssertTestStepWithScreenshot("Select HSN", Status.PASS, "Selected 'GST 28 % HSN'", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Select HSN", Status.FAIL, "Failed to select 'GST 28 % HSN'", true, e);
        }
    }

    public void enterAccountCodeAndPickICICILoan() {
        try {
            // Enter account code in nested detail frame
            switchToDetailInsideMultiPage();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), accountCodeInput);
            Utilities.Click(BaseTest.getDriver(), accountCodeInput);
            Utilities.SendKeys(BaseTest.getDriver(), accountCodeInput, "%%");

            // Select account from the grid dialog
            switchToGridDialog();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), iciciBankLoanCell);
            Utilities.DoubleClick(iciciBankLoanCell);

            // Save & close the picker, then OK
            switchToMultiPage();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), saveAndCloseButton);
            Utilities.Click(BaseTest.getDriver(), saveAndCloseButton);

            switchToGridDialog();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), okButtonOnDialog);
            Utilities.Click(BaseTest.getDriver(), okButtonOnDialog);

            ExtentTestManager.createAssertTestStepWithScreenshot("Select Account", Status.PASS, "Picked 'ICICI BANK LOAN 76CR'", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Select Account", Status.FAIL, "Failed to pick 'ICICI BANK LOAN 76CR'", true, e);
        }
    }

    public void addPJVEntry1() {
        String uniqueBillNo = "BILL" + System.currentTimeMillis();
        String uniqueParticulars = "TEST-" + System.currentTimeMillis();
        try {
            // Add
            toBrw();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), addButton);
            Utilities.Click(BaseTest.getDriver(), addButton);

            // Supplier filter "%%" + TAB → dblclick supplier in grid
            toDlg();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), supplierField);
            Utilities.Click(BaseTest.getDriver(), supplierField);
            Utilities.SendKeys(BaseTest.getDriver(), supplierField, "PROW003");
            supplierField.sendKeys(Keys.TAB);

//            toGrid();
//
            Random random = new Random();
//            WebElement chosenSupplier = supplierCells.get(random.nextInt(supplierCells.size()));
//            String chosenSupplierName = chosenSupplier.getText().trim();
//
//// Perform double-click using Utilities
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), chosenSupplier);
//            Utilities.DoubleClick(chosenSupplier);


            // Particulars
            toDlg();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), particularsField);
            Utilities.SendKeys(BaseTest.getDriver(), particularsField, uniqueParticulars);

            // Supplier bill no
            Utilities.SendKeys(BaseTest.getDriver(), supplierBillNoField, uniqueBillNo);

            // Bill date pick → day 6
            String currentdate = getCurrentDate.getText();
            System.out.println("Current Systsem Date is :" + currentdate);
            String date = Dateformat.getDateInGivenFormat(currentdate, "dd MMMM yyyy", "dd/MM/yyyy");
            System.out.println("Date is :" + date);

            Utilities.SendKeys(BaseTest.getDriver(), supplierBillDateField, date);


            // Amount (Ctrl/Meta + A then 100)
            toDlg();
            supplierBillAmountField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

            //supplierBillAmountField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            Utilities.SendKeys(BaseTest.getDriver(), supplierBillAmountField, "100");

            // Account choose (%% + TAB → dblclick account)
            toDtl();

            Utilities.SendKeys(BaseTest.getDriver(), accountCodeCell, "%%");
            accountCodeCell.sendKeys(Keys.TAB);

            toGrid();
            //Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), chosenSupplier);
            WebElement account = supplierCells.get(random.nextInt(supplierCells.size()));

            Utilities.DoubleClick(account);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Add PJV (non-parameterized)", Status.FAIL, "Flow failed", true, e);
        } finally {
            BaseTest.getDriver().switchTo().defaultContent();
            DynamicWait.smallWait();
        }
    }

    public void addPJVEntry() {
        String uniqueBillNo = "BILL" + System.currentTimeMillis();
        String uniqueParticulars = "TEST-" + System.currentTimeMillis();

        try {
            // Click Add button
            toBrw();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), addButton);
            Utilities.Click(BaseTest.getDriver(), addButton);
            ExtentTestManager.createAssertTestStepWithScreenshot("Click Add Button", Status.INFO, "Clicked on 'Add' button to create a new PJV entry.", true);

            // Enter Supplier Code
            toDlg();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), supplierField);
            Utilities.Click(BaseTest.getDriver(), supplierField);
            Utilities.SendKeys(BaseTest.getDriver(), supplierField, "PROW003");
            supplierField.sendKeys(Keys.TAB);
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Supplier Code", Status.INFO, "Entered supplier code 'PROW003' and pressed TAB.", false);

            // Enter Particulars
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), particularsField);
            Utilities.SendKeys(BaseTest.getDriver(), particularsField, uniqueParticulars);
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Particulars", Status.INFO, "Entered particulars: " + uniqueParticulars, false);


            // Enter Supplier Bill No
            Utilities.SendKeys(BaseTest.getDriver(), supplierBillNoField, uniqueBillNo);
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Supplier Bill No", Status.INFO, "Entered supplier bill number: " + uniqueBillNo, false);

            // Set Bill Date
            String currentdate = getCurrentDate.getText();
            String date = Dateformat.getDateInGivenFormat(currentdate, "dd MMMM yyyy", "dd/MM/yyyy");
            Utilities.SendKeys(BaseTest.getDriver(), supplierBillDateField, date);
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Bill Date", Status.INFO, "Set bill date to: " + date, false);

            // Enter Amount
            toDlg();
            supplierBillAmountField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            Utilities.SendKeys(BaseTest.getDriver(), supplierBillAmountField, "1000");
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Bill Amount", Status.INFO, "Entered bill amount: 100", false);

            // Select Account
            toDtl();
            Utilities.SendKeys(BaseTest.getDriver(), accountCodeCell, "%%");
            accountCodeCell.sendKeys(Keys.TAB);

            toGrid();
            Random random = new Random();
            WebElement account = supplierCells.get(random.nextInt(supplierCells.size()));
            Utilities.DoubleClick(account);
            ExtentTestManager.createAssertTestStepWithScreenshot("Select Account", Status.INFO, "Random account selected successfully.", true);

            ExtentTestManager.createAssertTestStepWithScreenshot("Add PJV Entry", Status.PASS, String.format("Supplier: PROW003 | Particulars: %s | Bill#: %s | Amount: 100 | Account selected successfully.", uniqueParticulars, uniqueBillNo), true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Add PJV Entry", Status.FAIL, "Failed while adding PJV entry.", true, e);
        }
    }

    // ======= One-shot method to run the exact sequence from your snippet =======
    public void completePJVPickerFlow() {
        addPJVEntry();
        selectTax_TDSOnContractor2Pct();
        openHSNPickerAndFilterGST();
        //multipleGLaccount();
        selectHSN_GST28();
    }

    // ===== frame helpers =====
    private void toBrw() {
        BaseTest.getDriver().switchTo().defaultContent();
        BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
    }

    private void toDlg() {
        BaseTest.getDriver().switchTo().defaultContent();
        BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
    }

    private void toDtl() {
        BaseTest.getDriver().switchTo().defaultContent();
        BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
        BaseTest.getDriver().switchTo().frame("iframeDtl");
    }

    private void toGrid() {
        BaseTest.getDriver().switchTo().defaultContent();
        BaseTest.getDriver().switchTo().frame("iframeGridDialog");
    }

    // ---------- Flow mirroring your Playwright ----------
    public void multipleInvoice() {
        try {
            // Click HSN code, then Close
            toDlg();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), hsnCodeField1);
            Utilities.Click(BaseTest.getDriver(), hsnCodeField1);

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), closeLink);
            Utilities.Click(BaseTest.getDriver(), closeLink);

            // Add Row in details frame
            toDtl();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), addRowButton);
            Utilities.Click(BaseTest.getDriver(), addRowButton);

            // acct_code_3_4 -> "%%" -> TAB -> pick "TOYATO FIN. SERV. LTD.- 2"
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), acctCode_3_4);
            Utilities.Click(BaseTest.getDriver(), acctCode_3_4);
            Utilities.SendKeys(BaseTest.getDriver(), acctCode_3_4, "%%");
            acctCode_3_4.sendKeys(Keys.TAB);

            toGrid();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), supplierCell_Toyato);
            Utilities.DoubleClick(supplierCell_Toyato);

            // acct_code_3_3 -> "%%" -> TAB -> click "PROFIT/ LOSS - CURRENT YEAR"
            toDtl();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), acctCode_3_3);
            Utilities.Click(BaseTest.getDriver(), acctCode_3_3);
            Utilities.SendKeys(BaseTest.getDriver(), acctCode_3_3, "%%");
            acctCode_3_3.sendKeys(Keys.TAB);

            toGrid();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), accountCell_PLCurrentYear);
            Utilities.Click(BaseTest.getDriver(), accountCell_PLCurrentYear);

            // Save & Close (dialog) → OK (grid)
            toDlg();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), saveAndCloseButton);
            Utilities.Click(BaseTest.getDriver(), saveAndCloseButton);

            toGrid();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), okButtonInGrid);
            Utilities.Click(BaseTest.getDriver(), okButtonInGrid);

            // Copy particulars from 12_0 → paste to 12_1 and 12_2; then overwrite with "PARTICULARS"
            toDtl();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), particulars_12_0);
            Utilities.Click(BaseTest.getDriver(), particulars_12_0);
            particulars_12_0.sendKeys(Keys.chord(Keys.CONTROL, "A"));
            particulars_12_0.sendKeys(Keys.chord(Keys.CONTROL, "C"));

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), particulars_12_1);
            Utilities.Click(BaseTest.getDriver(), particulars_12_1);
            particulars_12_1.sendKeys(Keys.chord(Keys.CONTROL, "V"));
            Utilities.SendKeys(BaseTest.getDriver(), particulars_12_1, "PARTICULARS");

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), particulars_12_2);
            Utilities.Click(BaseTest.getDriver(), particulars_12_2);
            particulars_12_2.sendKeys(Keys.chord(Keys.CONTROL, "V"));
            Utilities.SendKeys(BaseTest.getDriver(), particulars_12_2, "PARTICULARS");

            ExtentTestManager.createAssertTestStepWithScreenshot("PJV Details – add rows & copy particulars", Status.PASS, "Row added, accounts selected (TOYATO / P&L Current Year), particulars copied and set.", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PJV Details – add rows & copy particulars", Status.FAIL, "Flow failed while adding rows or copying particulars.", true, e);
        } finally {
            BaseTest.getDriver().switchTo().defaultContent();
        }
    }

    public void multipleTaxes() {
        addPJVEntry();
        //selectTax_TDSOnContractor2Pct();
        multipleTax();

    }

    public void multipleTax() {
        try {
            switchToMultiPage();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), hsnPickerIcon);
            Utilities.Click(BaseTest.getDriver(), hsnPickerIcon);

            // Filter as "GST"
            switchToGridDialog();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), hsnFilterColumn);
            Utilities.Click(BaseTest.getDriver(), hsnFilterColumn);
            //DynamicWait.smallWait();
            Utilities.Click(BaseTest.getDriver(), hsnFilterColumn);
            Utilities.SendKeys(BaseTest.getDriver(), hsnFilterColumn, "GST");

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), filterIconImg);
            Utilities.Click(BaseTest.getDriver(), filterIconImg);

            switchToGridDialog();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), gst28HsnCell);
            Utilities.DoubleClick(gst28HsnCell);


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            Utilities.Click(more);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(addRow);
            Utilities.SendKeys(sendGst, "GST 18%");
            Utilities.Click(addRow);
            Utilities.SendKeys(sendGst1, "RST1");
            Utilities.Click(okBtn);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(saveclose);

            // Utilities.Click(saveclose);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addPJVRCM() {
        String uniqueBillNo = "BILL" + System.currentTimeMillis();
        String uniqueParticulars = "TEST-" + System.currentTimeMillis();

        try {
            // Click Add button
            toBrw();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), addButton);
            Utilities.Click(BaseTest.getDriver(), addButton);
            ExtentTestManager.createAssertTestStepWithScreenshot("Click Add Button", Status.INFO, "Clicked on 'Add' button to create a new PJV entry.", true);

            // Enter Supplier Code
            toDlg();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), supplierField);
            Utilities.Click(BaseTest.getDriver(), supplierField);
            Utilities.SendKeys(BaseTest.getDriver(), supplierField, "PROW003");
            supplierField.sendKeys(Keys.TAB);
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Supplier Code", Status.INFO, "Entered supplier code 'PROW003' and pressed TAB.", false);

            // Enter Particulars
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), particularsField);
            Utilities.SendKeys(BaseTest.getDriver(), particularsField, uniqueParticulars);
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Particulars", Status.INFO, "Entered particulars: " + uniqueParticulars, false);

            Utilities.Click(rcm);

            // Enter Supplier Bill No
            Utilities.SendKeys(BaseTest.getDriver(), supplierBillNoField, uniqueBillNo);
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Supplier Bill No", Status.INFO, "Entered supplier bill number: " + uniqueBillNo, false);

            // Set Bill Date
            String currentdate = getCurrentDate.getText();
            String date = Dateformat.getDateInGivenFormat(currentdate, "dd MMMM yyyy", "dd/MM/yyyy");
            Utilities.SendKeys(BaseTest.getDriver(), supplierBillDateField, date);
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Bill Date", Status.INFO, "Set bill date to: " + date, false);

            // Enter Amount
            toDlg();
            supplierBillAmountField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            Utilities.SendKeys(BaseTest.getDriver(), supplierBillAmountField, "1000");
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Bill Amount", Status.INFO, "Entered bill amount: 100", false);

            // Select Account
//            toDtl();
//            Utilities.SendKeys(BaseTest.getDriver(), accountCodeCell, "%%");
//            accountCodeCell.sendKeys(Keys.TAB);
//
//            toGrid();
//            Random random = new Random();
//            WebElement account = supplierCells.get(random.nextInt(supplierCells.size()));
//            Utilities.DoubleClick(account);
//            ExtentTestManager.createAssertTestStepWithScreenshot("Select Account", Status.INFO, "Random account selected successfully.", true);
//
//            ExtentTestManager.createAssertTestStepWithScreenshot("Add PJV Entry", Status.PASS, String.format("Supplier: PROW003 | Particulars: %s | Bill#: %s | Amount: 100 | Account selected successfully.", uniqueParticulars, uniqueBillNo), true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Add PJV Entry", Status.FAIL, "Failed while adding PJV entry.", true, e);
        }
    }

    public void selectRCMGL() {
        try {
            switchToGridDialog();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), gst28HsnCell);
            Utilities.DoubleClick(gst28HsnCell);

            switchToMultiPage();

            BaseTest.getDriver().switchTo().frame("iframeDtl");
            // Utilities.SendKeys(BaseTest.getDriver(), accountCodeGL, "%%");


            for (WebElement acctField : accountCodeGL) {
                String value = acctField.getAttribute("value");


                if (value == null || value.trim().isEmpty()) {
                    Utilities.Click(BaseTest.getDriver(), acctField);
                    Utilities.SendKeys(BaseTest.getDriver(), acctField, "%%");
                    acctField.sendKeys(Keys.TAB);

                    toGrid();
                    Random random = new Random();
                    WebElement account = supplierCells.get(random.nextInt(supplierCells.size()));
                    Utilities.DoubleClick(account);

                } else {
                    //  ExtentTestManager.logInfo("Account field already has value, skipping");
                }
            }


            // Select Account
//            toDtl();
//            Utilities.SendKeys(BaseTest.getDriver(), accountCodeGL, "%%");
//            accountCodeGL.sendKeys(Keys.TAB);
//            toGrid();
//            Random random = new Random();
//            WebElement account = supplierCells.get(random.nextInt(supplierCells.size()));
//            Utilities.DoubleClick(account);
//            ExtentTestManager.createAssertTestStepWithScreenshot("Select Account", Status.INFO, "Random account selected successfully.", true);
            switchToMultiPage();

            Utilities.Click(saveclose);
            ExtentTestManager.createAssertTestStepWithScreenshot("Select HSN", Status.PASS, "Selected 'GST 28 % HSN'", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Select HSN", Status.FAIL, "Failed to select 'GST 28 % HSN'", true, e);
        }
    }

    public void gstOnHold() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(okBtn);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeSubDlg");
            Utilities.Click(accountPayable);

            Utilities.Click(gstonhold);
            try {
                BaseTest.getDriver().switchTo().alert().accept();
            } catch (Exception e) {

            }

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.SendKeys(apAccountFrom, "PROW003");
            Utilities.SendKeys(apAccountTo, "PROW003");
            Utilities.Click(search);


            int count = holdFlagElements.size();

            if (count == 0) {
                ExtentTestManager.getTest().log(Status.WARNING, "No elements found with id starting with 'hold_flag_10_'");
                return;
            }

            try {
                WebElement lastElement = holdFlagElements.get(count - 1);
                String elementId = lastElement.getAttribute("id");
                Utilities.Click(lastElement);
                ExtentTestManager.getTest().log(Status.PASS, "Clicked the last element with id: " + elementId);
            } catch (Exception e) {
                ExtentTestManager.getTest().log(Status.FAIL, "Failed to click the last 'hold_flag_10_' element: " + e.getMessage());
            }


            Utilities.Click(saveBtn);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            String text = confDlg.getText();
            if (text.contains("[238] - Details Created/Updated")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("GST ON Hold", Status.PASS, "GST on hold Tested Sucessfully" + text, true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("GST ON Hold", Status.FAIL, "Issue in GST ON Hold" + text, true);

            }


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("GST ON Hold", Status.FAIL, "Issue in GST ON Hold", true, e);
        }
    }

    public void closeGL(){
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(generalLedger);
            Utilities.Click(opencloseGL);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            String closeperiodDate = lastClosePeriod.getAttribute("value");
            System.out.println("Last Close period Date is : "+closeperiodDate);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(closeperiodDate, formatter);
            LocalDate previousDate = date.minusDays(1);
            String newDate = previousDate.format(formatter);
            System.out.println("Previous Date (one day less): " + newDate);

            Utilities.Click(accountPayable);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(makePJV);









            String uniqueBillNo = "BILL" + System.currentTimeMillis();
            String uniqueParticulars = "TEST-" + System.currentTimeMillis();


                toBrw();
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), addButton);
                Utilities.Click(BaseTest.getDriver(), addButton);
                ExtentTestManager.createAssertTestStepWithScreenshot("Click Add Button", Status.INFO, "Clicked on 'Add' button to create a new PJV entry.", true);

                // Enter Supplier Code
                toDlg();
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), supplierField);
                Utilities.Click(BaseTest.getDriver(), supplierField);
                Utilities.SendKeys(BaseTest.getDriver(), supplierField, "PROW003");
                supplierField.sendKeys(Keys.TAB);
                ExtentTestManager.createAssertTestStepWithScreenshot("Enter Supplier Code", Status.INFO, "Entered supplier code 'PROW003' and pressed TAB.", false);

                // Enter Particulars
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), particularsField);
                Utilities.SendKeys(BaseTest.getDriver(), particularsField, uniqueParticulars);
                ExtentTestManager.createAssertTestStepWithScreenshot("Enter Particulars", Status.INFO, "Entered particulars: " + uniqueParticulars, false);


                // Enter Supplier Bill No
                Utilities.SendKeys(BaseTest.getDriver(), supplierBillNoField, uniqueBillNo);
                ExtentTestManager.createAssertTestStepWithScreenshot("Enter Supplier Bill No", Status.INFO, "Entered supplier bill number: " + uniqueBillNo, false);

                // Set Bill Date
                //String currentdate = getCurrentDate.getText();
                //String date = Dateformat.getDateInGivenFormat(currentdate, "dd MMMM yyyy", "dd/MM/yyyy");
                Utilities.SendKeys(trnDate,newDate);
                Utilities.SendKeys(BaseTest.getDriver(), supplierBillDateField, newDate);
                ExtentTestManager.createAssertTestStepWithScreenshot("Enter Bill Date", Status.INFO, "Set bill date to: " + newDate, false);

                // Enter Amount
                toDlg();
                supplierBillAmountField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
                Utilities.SendKeys(BaseTest.getDriver(), supplierBillAmountField, "1000");
                ExtentTestManager.createAssertTestStepWithScreenshot("Enter Bill Amount", Status.INFO, "Entered bill amount: 100", false);

                // Select Account
                toDtl();
                Utilities.SendKeys(BaseTest.getDriver(), accountCodeCell, "%%");
                accountCodeCell.sendKeys(Keys.TAB);

                toGrid();
                Random random = new Random();
                WebElement account = supplierCells.get(random.nextInt(supplierCells.size()));
                Utilities.DoubleClick(account);
                ExtentTestManager.createAssertTestStepWithScreenshot("Select Account", Status.INFO, "Random account selected successfully.", true);

                ExtentTestManager.createAssertTestStepWithScreenshot("Add PJV Entry", Status.PASS, String.format("Supplier: PROW003 | Particulars: %s | Bill#: %s | Amount: 100 | Account selected successfully.", uniqueParticulars, uniqueBillNo), true);







            } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FindBy(id = "divmodule_5")
    private WebElement generalLedger;
    @FindBy(xpath = "//a[text()='Open or Close GL Periods']")
    private WebElement opencloseGL;
    @FindBy(id = "cphBody_txtPeriodTo_2")
    private WebElement lastClosePeriod;
    @FindBy(xpath = "//a[@title='AP04 Make PJV']")
    private WebElement makePJV;
    @FindBy(id = "cphBody_txtap_date_trn")
    private WebElement trnDate;

    public void closeGLValidate() {
        try {
            switchToGridDialog();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), gst28HsnCell);
            Utilities.DoubleClick(gst28HsnCell);

            switchToMultiPage();
            Utilities.Click(saveclose);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            if (confDlg.isDisplayed()&&confDlg.getText().contains("Please check the Gl Periods they are either closed or not defined")){
                ExtentTestManager.createAssertTestStepWithScreenshot("Closed GL Period", Status.PASS, "GL period Validation Pass : "+confDlg.getText(), true);
            }else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Select HSN", Status.FAIL, "GL period Validation Failed :"+confDlg.getText(), true);

            }

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Select HSN", Status.FAIL, "GL period Validation Failed :"+confDlg.getText(), true, e);
        }
    }



}

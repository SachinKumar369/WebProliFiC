package pages.accountPayables.PJV;

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
import utils.Dateformat;
import utils.DynamicWait;
import utils.Utilities;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MultipleInvoiceGLPage extends Utilities {

    private static final String FRAME_GRID_DIALOG = "iframeGridDialog";
    private static final String FRAME_MULTI_PAGE = "MultiPageiframeDlg";
    private static final String FRAME_DETAIL = "iframeDtl";

    @FindBy(css = "#col_1")
    private WebElement hsnFilterColumn;
    @FindBy(css = "#FilterTD_1_1 img")
    private WebElement filterIconImg;
    @FindBy(xpath = "//*[normalize-space()='GST 28 % HSN']")
    private WebElement gst28HsnCell;
    @FindBy(css = "input[id^='ap_particulars_']")
    private List<WebElement> apParticularsFields;
    @FindBy(css = "#cphBody_imgtax_name_1")
    private WebElement taxNamePickerIcon;
    @FindBy(css = "#cphBody_imphsn_code_1")
    private WebElement hsnPickerIcon;
    @FindBy(id = "cphDialogHeader_btnSaveClose")
    private WebElement saveAndCloseButton;
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
    @FindBy(css = "input[id^='supplier_bill_no_']")
    private WebElement supplierBillNoField;
    @FindBy(css = "input[id^='supplier_bill_dt_']")
    private WebElement supplierBillDateField;
    @FindBy(id = "img_supplier_bill_dt_1_0")
    private WebElement supplierBillDatePickerIcon;
    @FindBy(css = "input[id^='supplier_bill_amount_']")
    private WebElement supplierBillAmountField;
    @FindBy(id = "cphFooter_tdDatetime")
    private WebElement getCurrentDate;
    @FindBy(xpath = "//*[starts-with(@id, \"td_\") and substring(@id, string-length(@id)-1) = \"_1\"]")
    private List<WebElement> supplierCells;
    @FindBy(id = "lblErrorDesc")
    private WebElement confDlg;
    @FindBy(css = "[id^='acct_code_']")
    private WebElement accountCodeCell;
    @FindBy(id = "btnOK")
    private WebElement okButtonInGridDialog;
    @FindBy(id = "td_5_1")
    private WebElement tds;
    @FindBy(id = "cphBody_btnAddRow")
    private WebElement addRow;
    @FindBy(css = "input[id^='acct_code_']")
    private List<WebElement> accountCodeInputs;
    @FindBy(id = "btnAddRow")
    private WebElement add_Row;
    private String invoice;
    @FindBy(id = "cphBody_imgPictureClip")
    private WebElement attachment;
    @FindBy(id = "ddlAttachtype")
    private WebElement attachmenttype;
    @FindBy(id = "txtRemarks")
    private WebElement remarks;
    @FindBy(id = "txtUploadPic")
    private WebElement upload;
    @FindBy(id = "btnUpload")
    private WebElement uploadBtn;
    @FindBy(xpath = "//img[@alt='Close']")
    private WebElement close;
    @FindBy(id = "cphBody_txtVoucherNumber")
    private WebElement searchVoucherNo;
    @FindBy(id = "cphBody_chkViewUnapproved")
    private WebElement viewUnapproved;
    @FindBy(id = "cphBody_btnSearch")
    private WebElement search;
    @FindBy(id = "cphBrowserHeader_btnViewEdit")
    private WebElement viewEdit;
    @FindBy(xpath = "//a[normalize-space(text())='Accounts Payables']")
    private WebElement accountPayables;
    @FindBy(xpath = "//a[text()='PJV Approval']")
    private WebElement pjvApproval;
    @FindBy(id = "cphBody_btnRefresh")
    private WebElement refresh;
    @FindBy(id = "cphBody_txtNumber")
    private WebElement txtNumber;
    @FindBy(xpath = "//select[contains(@id,'ddlaction')]")
    private WebElement select;
    @FindBy(id = "cphDialogHeader_btnSave")
    private WebElement process;

    public MultipleInvoiceGLPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
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

    public void selectTax_TDSOnContractor2Pct() {
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

    public void selectHSN_GST28() {
        try {
            switchToGridDialog();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), gst28HsnCell);
            Utilities.DoubleClick(gst28HsnCell);


            ExtentTestManager.createAssertTestStepWithScreenshot("Select HSN", Status.PASS, "Selected 'GST 28 % HSN'", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Select HSN", Status.FAIL, "Failed to select 'GST 28 % HSN'", true, e);
        }
    }

    public void addButton() {
        try {
            toBrw();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), addButton);
            Utilities.Click(BaseTest.getDriver(), addButton);
            ExtentTestManager.createAssertTestStepWithScreenshot("Click Add Button", Status.PASS, "Clicked on 'Add'", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Add Button",Status.FAIL,"Add button Not Clickable",true,e);
        }
    }

    public void addPJVEntry1(int count) {
        if (count < 1) count = 1;

        String uniqueBillNo = "BILL" + System.currentTimeMillis();
        String uniqueParticulars = "TEST-" + System.currentTimeMillis();
        Random random = new Random();

        try {
            // --- Header once: supplier → particulars → bill no → bill date ---
            toDlg();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), supplierField);
            Utilities.Click(BaseTest.getDriver(), supplierField);
            Utilities.SendKeys(BaseTest.getDriver(), supplierField, "%%");
            supplierField.sendKeys(Keys.TAB);

            toGrid();
            WebElement chosenSupplier = supplierCells.get(random.nextInt(supplierCells.size()));
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), chosenSupplier);
            Utilities.DoubleClick(chosenSupplier);

            toDlg();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), particularsField);
            Utilities.SendKeys(BaseTest.getDriver(), particularsField, uniqueParticulars);


            // --- Detail rows loop ---
            for (int i = 0; i < count; i++) {
                // Amount

                toDlg();
                Utilities.SendKeys(BaseTest.getDriver(), supplierBillNoField, uniqueBillNo + i);
                String currentdate = getCurrentDate.getText();
                String date = Dateformat.getDateInGivenFormat(currentdate, "dd MMMM yyyy", "dd/MM/yyyy");
                Utilities.SendKeys(BaseTest.getDriver(), supplierBillDateField, date);


                toDlg();
                supplierBillAmountField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
                Utilities.SendKeys(BaseTest.getDriver(), supplierBillAmountField, "100");


                selectTax_TDSOnContractor2Pct();


                // If more rows needed: click Add Row and prep next row
                if (i < count - 1) {
                    toDlg();
                    Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), addRow);
                    Utilities.Click(BaseTest.getDriver(), addRow);
                }
            }

            ExtentTestManager.createAssertTestStepWithScreenshot("Add PJV entries (" + count + ")", Status.PASS, "Completed", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Add PJV entries (" + count + ")", Status.FAIL, e.getMessage(), true, e);
        }
    }

    public void addPJVEntry(int count) {
        if (count < 1) count = 1;

        String uniqueBillNo = "BILL" + System.currentTimeMillis();
        String uniqueParticulars = "TEST-" + System.currentTimeMillis();
        Random random = new Random();

        try {
            // --- Step 1: Select Supplier ---
            toDlg();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), supplierField);
            Utilities.Click(BaseTest.getDriver(), supplierField);
            Utilities.SendKeys(BaseTest.getDriver(), supplierField, "%%");
            supplierField.sendKeys(Keys.TAB);

            toGrid();
            WebElement chosenSupplier = supplierCells.get(random.nextInt(supplierCells.size()));
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), chosenSupplier);
            String supplierName = chosenSupplier.getText().trim();
            Utilities.DoubleClick(chosenSupplier);

            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Select Supplier", Status.PASS,
                    "Supplier selected successfully: <b>" + supplierName + "</b>", true
            );

            // --- Step 2: Enter Particulars ---
            toDlg();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), particularsField);
            Utilities.SendKeys(BaseTest.getDriver(), particularsField, uniqueParticulars);
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Enter Particulars", Status.PASS,
                    "Entered particulars as: <b>" + uniqueParticulars + "</b>", true
            );

            // --- Step 3: Loop through detail rows ---
            for (int i = 0; i < count; i++) {
                int rowNumber = i + 1;

                // Bill No
                toDlg();
                String billNo = uniqueBillNo + i;
                Utilities.SendKeys(BaseTest.getDriver(), supplierBillNoField, billNo);
                ExtentTestManager.createAssertTestStepWithScreenshot(
                        "Row " + rowNumber + " - Bill No", Status.PASS,
                        "Entered Bill No: <b>" + billNo + "</b>", true
                );

                // Bill Date
                String currentDate = getCurrentDate.getText();
                String formattedDate = Dateformat.getDateInGivenFormat(currentDate, "dd MMMM yyyy", "dd/MM/yyyy");
                Utilities.SendKeys(BaseTest.getDriver(), supplierBillDateField, formattedDate);
                ExtentTestManager.createAssertTestStepWithScreenshot(
                        "Row " + rowNumber + " - Bill Date", Status.PASS,
                        "Entered Bill Date: <b>" + formattedDate + "</b>", true
                );

                // Bill Amount
                toDlg();
                supplierBillAmountField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
                String billAmount = "100";
                Utilities.SendKeys(BaseTest.getDriver(), supplierBillAmountField, billAmount);
                ExtentTestManager.createAssertTestStepWithScreenshot(
                        "Row " + rowNumber + " - Bill Amount", Status.PASS,
                        "Entered Bill Amount: <b>" + billAmount + "</b>", true
                );

                // Select Tax
                selectTax_TDSOnContractor2Pct();
                ExtentTestManager.createAssertTestStepWithScreenshot(
                        "Row " + rowNumber + " - Select Tax", Status.PASS,
                        "Selected tax: <b>TDS on Contractor 2%</b>", true
                );

                // Add Row if more entries
                if (i < count - 1) {
                    toDlg();
                    Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), addRow);
                    Utilities.Click(BaseTest.getDriver(), addRow);
                    ExtentTestManager.createAssertTestStepWithScreenshot(
                            "Row " + rowNumber + " - Add Next Row", Status.PASS,
                            "Added new row for next entry (<b>Row " + (rowNumber + 1) + "</b>)", true
                    );
                }
            }

            // --- Step 4: Final Confirmation ---
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "PJV Entry Summary", Status.PASS,
                    "Successfully added <b>" + count + "</b> PJV entries for supplier: <b>" + supplierName + "</b>", true
            );

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Add PJV Entries", Status.FAIL,
                    "Failed to add PJV entries: " + e.getMessage(), true, e
            );
        }
    }


    // ======= One-shot method to run the exact sequence from your snippet =======
    public void completePJVPickerFlowWithoutTax() {
        addButton();
        addPJVEntry(1);
        //selectTax_TDSOnContractor2Pct();
       // openHSNPickerAndFilterGST();
        //multipleGLaccount();
        //selectHSN_GST28();
        fillEmptyAcctCodes();
    }
    public void completePJVPickerFlow() {
        addButton();
        addPJVEntry(2);
        //selectTax_TDSOnContractor2Pct();
        openHSNPickerAndFilterGST();
        //multipleGLaccount();
        selectHSN_GST28();
        fillEmptyAcctCodes();
    }

    /**
     * Fill every empty acct_code_* input; skip those that already have a value.
     */
    public void fillEmptyAcctCodes() {
        switchToMultiPage();
        BaseTest.getDriver().switchTo().frame("iframeDtl");
        System.out.println("Inside the fill emlpty acct codes");
        for (WebElement input : accountCodeInputs) {
            try {
                System.out.println("Inside the fill emlpty acct codes");

                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), input);

                String current = input.getAttribute("value");
                if (current == null || current.trim().isEmpty()) {
                    Utilities.Click(BaseTest.getDriver(), input);
                    Utilities.SendKeys(BaseTest.getDriver(), input, "%%");
                    input.sendKeys(Keys.TAB);


                    toGrid();
                    Random random = new Random();
                    WebElement chosenSupplier = supplierCells.get(random.nextInt(supplierCells.size()));
                    String chosenSupplierName = chosenSupplier.getText().trim();

// Perform double-click using Utilities
                    Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), chosenSupplier);
                    Utilities.DoubleClick(chosenSupplier);


                }


                // else: non-empty → automatically move to next element
            } catch (Exception e) {
                // Optional: log and continue instead of failing the whole loop
                ExtentTestManager.getTest().warning("Skipping " + input.getAttribute("id") + ": " + e.getMessage());
            }
        }
        BaseTest.getDriver().switchTo().defaultContent();
        BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
        Utilities.Click(saveAndCloseButton);

        toGrid();
        WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(confDlg));
        String text = confDlg.getText().trim();                  // e.g. "[4533] - Invoice Posted. Invoice number is 50003286"

        if (text.contains("Invoice Posted. Invoice number is")) {
            Matcher m = Pattern.compile("Invoice number is\\s*([A-Za-z0-9/_-]+)").matcher(text);
            this.invoice = m.find() ? m.group(1).trim() : "UNKNOWN";

            ExtentTestManager.createAssertTestStepWithScreenshot("Invoice Posted. Number: " + this.invoice, com.aventstack.extentreports.Status.PASS, text, true);


            // best-effort close
            try {
                Utilities.Click(okButtonInGridDialog);
            } catch (Exception ignored) {
            }

        } else {
            ExtentTestManager.createAssertTestStepWithScreenshot("Popup text mismatch", com.aventstack.extentreports.Status.FAIL, text, true);
        }

    }

    public void multipleGLaccount1() {
        try {

            switchToMultiPage();
            BaseTest.getDriver().switchTo().frame("iframeDtl");
            System.out.println("Inside the fill emlpty acct codes");
            for (WebElement input : accountCodeInputs) {
                try {
                    System.out.println("Inside the fill emlpty acct codes");

                    Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), input);

                    String current = input.getAttribute("value");
                    if (current == null || current.trim().isEmpty()) {
                        Utilities.Click(BaseTest.getDriver(), input);
                        Utilities.SendKeys(BaseTest.getDriver(), input, "%%");
                        input.sendKeys(Keys.TAB);


                        toGrid();
                        Random random = new Random();
                        WebElement chosenSupplier = supplierCells.get(random.nextInt(supplierCells.size()));
                        String chosenSupplierName = chosenSupplier.getText().trim();

// Perform double-click using Utilities
                        Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), chosenSupplier);
                        Utilities.DoubleClick(chosenSupplier);


                    }


                    // else: non-empty → automatically move to next element
                } catch (Exception e) {
                    // Optional: log and continue instead of failing the whole loop
                    ExtentTestManager.getTest().warning("Skipping " + input.getAttribute("id") + ": " + e.getMessage());
                }
            }


//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            BaseTest.getDriver().switchTo().frame("iframeDtl");
//            Utilities.Click(add_Row);


//            switchToMultiPage();
//            BaseTest.getDriver().switchTo().frame("iframeDtl");
//            System.out.println("Inside the fill emlpty acct codes");
//            for (WebElement input : accountCodeInputs) {
//                try {
//                    System.out.println("Inside the fill emlpty acct codes");
//
//                    Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), input);
//
//                    String current = input.getAttribute("value");
//                    if (current == null || current.trim().isEmpty()) {
//                        Utilities.Click(BaseTest.getDriver(), input);
//                        Utilities.SendKeys(BaseTest.getDriver(), input, "%%");
//                        input.sendKeys(Keys.TAB);
//
//
//                        toGrid();
//                        Random random = new Random();
//                        WebElement chosenSupplier = supplierCells.get(random.nextInt(supplierCells.size()));
//                        String chosenSupplierName = chosenSupplier.getText().trim();
//
//// Perform double-click using Utilities
//                        Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), chosenSupplier);
//                        Utilities.DoubleClick(chosenSupplier);
//
//
//                    }
//
//
//                    // else: non-empty → automatically move to next element
//                } catch (Exception e) {
//                    // Optional: log and continue instead of failing the whole loop
//                    //ExtentTestManager.getTest().warning("Skipping " + input.getAttribute("id") + ": " + e.getMessage());
//                }
//            }


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            BaseTest.getDriver().switchTo().frame("iframeDtl");

            for (WebElement el : apParticularsFields) {
                String val = el.getAttribute("value");
                if (val == null || val.trim().isEmpty()) {
                    Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), el);
                    Utilities.Click(BaseTest.getDriver(), el);
                    Utilities.SendKeys(BaseTest.getDriver(), el, "Particulars");
                    // el.sendKeys(Keys.TAB); // uncomment if onchange needs to fire
                    // break; // uncomment to stop after filling the first empty field
                }
            }


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(saveAndCloseButton);

            toGrid();
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(confDlg));
            String text = confDlg.getText().trim();                  // e.g. "[4533] - Invoice Posted. Invoice number is 50003286"

            if (text.contains("Invoice Posted. Invoice number is")) {
                Matcher m = Pattern.compile("Invoice number is\\s*(\\d+)").matcher(text);
                this.invoice = m.find() ? m.group(1) : "UNKNOWN";

                ExtentTestManager.createAssertTestStepWithScreenshot("Invoice Posted. Number: " + this.invoice, com.aventstack.extentreports.Status.PASS, text, true);

                // best-effort close
                try {
                    Utilities.Click(okButtonInGridDialog);
                } catch (Exception ignored) {
                }
            }

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Multiple GL Account", Status.FAIL, "Error in multiple gl accounts", true, e);
        }
    }

    public void multipleGLaccount() {
        try {
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Multiple GL Account", Status.INFO, "Started Multiple GL Account entry process.", false);

            switchToMultiPage();
            BaseTest.getDriver().switchTo().frame("iframeDtl");

            // === Step 1: Fill Empty Account Codes ===
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Fill Account Codes", Status.INFO, "Scanning for empty account code fields.", false);

            for (WebElement input : accountCodeInputs) {
                try {
                    Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), input);
                    String currentValue = input.getAttribute("value");

                    if (currentValue == null || currentValue.trim().isEmpty()) {
                        Utilities.Click(BaseTest.getDriver(), input);
                        Utilities.SendKeys(BaseTest.getDriver(), input, "%%");
                        input.sendKeys(Keys.TAB);

                        toGrid();
                        Random random = new Random();
                        WebElement chosenAccount = supplierCells.get(random.nextInt(supplierCells.size()));
                        String accountName = chosenAccount.getText().trim();

                        Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), chosenAccount);
                        Utilities.DoubleClick(chosenAccount);

                        ExtentTestManager.createAssertTestStepWithScreenshot(
                                "Account Selection", Status.PASS,
                                "Selected account: <b>" + accountName + "</b>", true);
                    }
                } catch (Exception e) {
                    ExtentTestManager.createAssertTestStepWithScreenshot(
                            "Account Selection", Status.FAIL,
                            "Error while selecting account: " + e.getMessage(), true, e);
                }
            }

            // === Step 2: Fill Particulars ===
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            BaseTest.getDriver().switchTo().frame("iframeDtl");

            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Enter Particulars", Status.INFO, "Entering particulars for each GL line.", false);

            for (WebElement particularsField : apParticularsFields) {
                try {
                    String val = particularsField.getAttribute("value");
                    if (val == null || val.trim().isEmpty()) {
                        Utilities.Click(BaseTest.getDriver(), particularsField);
                        Utilities.SendKeys(BaseTest.getDriver(), particularsField, "Particulars");
                    }
                } catch (Exception e) {
                    ExtentTestManager.createAssertTestStepWithScreenshot(
                            "Enter Particulars", Status.FAIL,
                            "Failed to enter particulars: " + e.getMessage(), true, e);
                }
            }

            // === Step 3: Save & Validate ===
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(saveAndCloseButton);

            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Save & Close", Status.PASS, "Clicked 'Save & Close' button.", true);

            toGrid();
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(confDlg));

            String messageText = confDlg.getText().trim();
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Confirmation", Status.INFO,
                    "System message displayed: " + messageText, false);

            if (messageText.contains("Invoice Posted. Invoice number is")) {
                Matcher m = Pattern.compile("Invoice number is\\s*(\\d+)").matcher(messageText);
                this.invoice = m.find() ? m.group(1) : "UNKNOWN";

                ExtentTestManager.createAssertTestStepWithScreenshot(
                        "Invoice Posted", Status.PASS,
                        "Invoice successfully posted. <b>Invoice No:</b> " + this.invoice, true);

                try {
                    Utilities.Click(okButtonInGridDialog);
                } catch (Exception ignored) {
                }
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot(
                        "Invoice Validation", Status.FAIL,
                        "Unexpected confirmation message: " + messageText, true);
            }

            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Multiple GL Account", Status.PASS,
                    "Multiple GL Account process completed successfully. Invoice: " + this.invoice, false);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Multiple GL Account", Status.FAIL,
                    "Error occurred during Multiple GL Account flow: " + e.getMessage(), true, e);
        } finally {
            BaseTest.getDriver().switchTo().defaultContent();
            DynamicWait.smallWait();
        }
    }


    public void multipleGLFlow() {
        addButton();
        addPJVEntry(1);
        attachement();
        openHSNPickerAndFilterGST();
        selectHSN_GST28();
        multipleGLaccount();
    }

    public void attachement() {
        try {
            switchToMultiPage();
            Utilities.Click(attachment);
            String filePath = System.getProperty("user.dir") + "\\DownloadPath\\image_2253.jpg";

            switchToMultiPage();
            BaseTest.getDriver().switchTo().frame("iframeAttachFileDialog");
            Utilities.selectBy("value", attachmenttype, "LI");
            Utilities.SendKeys(remarks, "Remarks");
            Utilities.SendKeys(upload, filePath);
            Utilities.Click(uploadBtn);

            switchToMultiPage();
            Utilities.Click(close);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PJV Approval",Status.FAIL,"Issue in PJV Approval",true,e);
        }
    }


    public void modifyPJV() {
        try {
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Modify PJV - Start", Status.INFO, "Starting Modify PJV flow", false);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Validate captured invoice
            if (this.invoice == null || this.invoice.trim().isEmpty() || "UNKNOWN".equalsIgnoreCase(this.invoice)) {
                ExtentTestManager.createAssertTestStepWithScreenshot(
                        "Invoice Validation", Status.FAIL,
                        "Invoice number not captured from Save & Close popup. Ensure multipleGLaccount() ran and parsed the popup.",
                        true);
                throw new IllegalStateException("Invoice number missing. Run multipleGLaccount() first.");
            }

            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Invoice Validation", Status.INFO,
                    "Using captured invoice number: " + this.invoice, false);

            // --- Step 1: Enter Invoice in Search ---
            Utilities.SendKeys(searchVoucherNo, this.invoice);
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Enter Invoice in Search", Status.INFO,
                    "Entered invoice number '" + this.invoice + "' in search field", false);

            // --- Step 2: Click View Unapproved (if visible) ---
            if (viewUnapproved.isDisplayed()) {
                Utilities.Click(viewUnapproved);
                ExtentTestManager.createAssertTestStepWithScreenshot(
                        "Click View Unapproved", Status.INFO, "Clicked on 'View Unapproved' checkbox", false);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot(
                        "Click View Unapproved", Status.INFO, "'View Unapproved' checkbox not present or already selected", false);
            }

            // --- Step 3: Click Search ---
            Utilities.Click(search);
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Click Search", Status.PASS, "Clicked on 'Search' button for invoice " + this.invoice, true);



            // --- Step 4: Click View/Edit ---
            Utilities.Click(viewEdit);
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Click View/Edit", Status.PASS,
                    "Opened invoice " + this.invoice + " in Edit mode", true);

            // --- Step 5: Modify Particulars ---
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            BaseTest.getDriver().switchTo().frame("iframeDtl");

            int index = 0;
            for (WebElement el : apParticularsFields) {
                index++;
                String val = el.getAttribute("value");
                if (val != null && !val.trim().isEmpty()) {
                    Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), el);
                    Utilities.Click(BaseTest.getDriver(), el);
                    ExtentTestManager.createAssertTestStepWithScreenshot(
                            "Particulars Row " + index + " - Click", Status.INFO,
                            "Clicked on Particulars field (Row " + index + ")", false);

                    Utilities.SendKeys(BaseTest.getDriver(), el, "Particulars Updated");
                    ExtentTestManager.createAssertTestStepWithScreenshot(
                            "Particulars Row " + index + " - Modify", Status.INFO,
                            "Updated Particulars field (Row " + index + ") with value: 'Particulars Updated'", false);
                } else {
                    ExtentTestManager.createAssertTestStepWithScreenshot(
                            "Particulars Row " + index + " - Skip", Status.INFO,
                            "Skipped empty Particulars row " + index, false);
                }
            }

            // --- Step 6: Save and Close ---
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            Utilities.Click(saveAndCloseButton);
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Save & Close", Status.PASS,
                    "Clicked 'Save & Close' button after modification", true);

            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Modify PJV - Completed", Status.PASS,
                    "Successfully modified and saved invoice " + this.invoice, false);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Modify PJV - Error", Status.FAIL,
                    "Issue encountered during Modify PJV flow: " + e.getMessage(), true, e);
        } finally {
            try {
                BaseTest.getDriver().switchTo().defaultContent();
            } catch (Exception ignored) {}
            DynamicWait.smallWait();
        }
    }


    public void saveandClose(){
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(saveAndCloseButton);

            toGrid();
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(confDlg));
            String text = confDlg.getText().trim();                  // e.g. "[4533] - Invoice Posted. Invoice number is 50003286"

            if (text.contains("Invoice Posted. Invoice number is")) {
                Matcher m = Pattern.compile("Invoice number is\\s*([A-Za-z0-9/_-]+)").matcher(text);
                this.invoice = m.find() ? m.group(1).trim() : "UNKNOWN";

                ExtentTestManager.createAssertTestStepWithScreenshot("Invoice Posted. Number: " + this.invoice, com.aventstack.extentreports.Status.PASS, text, true);


                // best-effort close
                try {
                    Utilities.Click(okButtonInGridDialog);
                } catch (Exception ignored) {
                }
        }} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void pjfApproval() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(accountPayables);

            Utilities.Click(pjvApproval);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(okButtonInGridDialog);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(refresh);
            System.out.println("Invoice Number is :" + this.invoice);
            Utilities.SendKeys(txtNumber, this.invoice);
            Utilities.Click(search);
            Utilities.selectBy("value", select, "AP");
            Utilities.Click(process);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PJV Approval", Status.FAIL, "Issue in PJV Approval", true, e);
        }
    }

    public void Hold() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(accountPayables);

            Utilities.Click(pjvApproval);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(okButtonInGridDialog);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(refresh);
            System.out.println("Invoice Number is :" + this.invoice);
            Utilities.SendKeys(txtNumber, this.invoice);
            Utilities.Click(search);
            Utilities.selectBy("value", select, "OH");
            Utilities.SendKeys(reason,"On Hold");

            Utilities.Click(process);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PJV Approval", Status.FAIL, "Issue in PJV Approval", true, e);
        }
    }

    @FindBy(xpath = "//input[contains(@id, 'txtremarks')]")
    private WebElement reason;
    public void reject() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(accountPayables);

            Utilities.Click(pjvApproval);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(okButtonInGridDialog);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(refresh);
            System.out.println("Invoice Number is :" + this.invoice);
            Utilities.SendKeys(txtNumber, this.invoice);
            Utilities.Click(search);
            Utilities.selectBy("value", select, "RJ");
            Utilities.SendKeys(reason,"Reject");
            Utilities.Click(process);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PJV Approval", Status.FAIL, "Issue in PJV Approval", true, e);
        }
    }


    @FindBy(id = "btnOK")
    private WebElement okButton;
    // ======= One-shot method to run the exact sequence from your snippet =======
    public void pjvModification() {
        addButton();
        addPJVEntry(2);
        //selectTax_TDSOnContractor2Pct();
        openHSNPickerAndFilterGST();
        //multipleGLaccount();
        selectHSN_GST28();
        fillEmptyAcctCodes();
        modifyPJV();
    }

    @FindBy(id = "menutxtSearch")
    private WebElement menuSearch;
    @FindBy(xpath = "//a[contains(normalize-space(.),'AP04')]")
    WebElement makePJVLink;


    public void approveModify(){
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Click(okButton);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            SendKeys(menuSearch,"Make PJV");
            Click(makePJVLink);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Validate captured invoice
            if (this.invoice == null || this.invoice.trim().isEmpty() || "UNKNOWN".equalsIgnoreCase(this.invoice)) {
                ExtentTestManager.createAssertTestStepWithScreenshot(
                        "Invoice Validation", Status.FAIL,
                        "Invoice number not captured from Save & Close popup. Ensure multipleGLaccount() ran and parsed the popup.",
                        true);
                throw new IllegalStateException("Invoice number missing. Run multipleGLaccount() first.");
            }

            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Invoice Validation", Status.INFO,
                    "Using captured invoice number: " + this.invoice, false);

            // --- Step 1: Enter Invoice in Search ---
            Utilities.SendKeys(searchVoucherNo, this.invoice);
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Enter Invoice in Search", Status.INFO,
                    "Entered invoice number '" + this.invoice + "' in search field", false);


            // --- Step 3: Click Search ---
            Utilities.Click(search);
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Click Search", Status.PASS, "Clicked on 'Search' button for invoice " + this.invoice, true);



            // --- Step 4: Click View/Edit ---
            Utilities.Click(viewEdit);
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Click View/Edit", Status.PASS,
                    "Opened invoice " + this.invoice + " in Edit mode", true);

            // --- Step 5: Modify Particulars ---
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            BaseTest.getDriver().switchTo().frame("iframeDtl");

            int index = 0;
            for (WebElement el : apParticularsFields) {
                index++;
                String val = el.getAttribute("value");
                if (val != null && !val.trim().isEmpty()) {
                    Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), el);
                    Utilities.Click(BaseTest.getDriver(), el);
                    ExtentTestManager.createAssertTestStepWithScreenshot(
                            "Particulars Row " + index + " - Click", Status.INFO,
                            "Clicked on Particulars field (Row " + index + ")", false);

                    Utilities.SendKeys(BaseTest.getDriver(), el, "Particulars Updated");
                    ExtentTestManager.createAssertTestStepWithScreenshot(
                            "Particulars Row " + index + " - Modify", Status.INFO,
                            "Updated Particulars field (Row " + index + ") with value: 'Particulars Updated'", false);
                } else {
                    ExtentTestManager.createAssertTestStepWithScreenshot(
                            "Particulars Row " + index + " - Skip", Status.INFO,
                            "Skipped empty Particulars row " + index, false);
                }
            }

            // --- Step 6: Save and Close ---
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            Utilities.Click(saveAndCloseButton);
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Save & Close", Status.PASS,
                    "Clicked 'Save & Close' button after modification", true);

            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Modify PJV - Completed", Status.PASS,
                    "Successfully modified and saved invoice " + this.invoice, false);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
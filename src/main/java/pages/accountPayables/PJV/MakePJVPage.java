package pages.accountPayables.PJV;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Dateformat;
import utils.DynamicWait;
import utils.Utilities;

import java.util.List;
import java.util.Random;

public class MakePJVPage {


    String invoiceNo = "";
    String date = "";
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
    @FindBy(id = "supplier_bill_amount_3_0")
    private WebElement supplierBillAmountField;
    @FindBy(id = "cphDialogHeader_btnSaveClose")
    private WebElement saveAndCloseButton;
    // ===== inside frame: iframeDtl (nested within MultiPageiframeDlg) =====
    @FindBy(id = "acct_code_3_0")
    private WebElement accountCodeCell;
    @FindBy(id = "TableData")
    private WebElement tableDataArea;
    // ===== inside frame: iframeGridDialog =====
    @FindBy(id = "btnOK")
    private WebElement okButtonInGridDialog;
    // hardcoded grid selections (since not parameterized)
    @FindBy(xpath = "//td[normalize-space()='A H KAUSHIKS ENTERPRISES']")
    private WebElement supplierCell_AHKaushiks;
    @FindBy(xpath = "//td[normalize-space()='LAND AND BUILDING']")
    private WebElement accountCell_LandAndBuilding;
    @FindBy(xpath = "//*[self::a or self::td][normalize-space()='6']")
    private WebElement calendarDaySix;
    @FindBy(xpath = "//a[text()='Accounting']")
    private WebElement accounting;
    @FindBy(id = "divmodule_1")
    private WebElement accountPayble;
    @FindBy(xpath = "//a[text()='Make PJV']")
    private WebElement makePJV;
    @FindBy(id = "cphFooter_tdDatetime")
    private WebElement getCurrentDate;
    @FindBy(xpath = "//*[starts-with(@id, \"td_\") and substring(@id, string-length(@id)-1) = \"_1\"]")
    private List<WebElement> supplierCells;
    @FindBy(id = "lblErrorDesc")
    private WebElement confDlg;
    @FindBy(id = "td_0_3")
    private WebElement department;
    @FindBy(id = "divmodule_1")
    private WebElement accountPayables;
    @FindBy(xpath = "//a[@title='AP10 Process Payment']")
    private WebElement processPayment;
    @FindBy(id = "cphBody_txtPJVNumbersFrom")
    private WebElement pjvFrom;
    @FindBy(id = "cphBody_txtPJVNumbersTo")
    private WebElement pjvTo;
    @FindBy(id = "cphBody_btnSearch")
    private WebElement search;
    @FindBy(id = "process_9_0")
    private WebElement process;
    @FindBy(id = "cphDialogHeader_btnSave")
    private WebElement saveButton;
    @FindBy(id = "divmodule_5")
    private WebElement generalLedger;
    @FindBy(xpath = "//a[@title='GL17 Post Interface Transactions']")
    private WebElement postInterface;
    @FindBy(id = "cphBody_txtTransPosted")
    private WebElement enterDate;
    @FindBy(id = "cphBody_rdoPost")
    private WebElement post;
    @FindBy(id = "cphBody_txtVoucherNumber")
    private WebElement voucherNumber;
    @FindBy(id = "cphBrowserHeader_btnViewEdit")
    private WebElement viewEdit;
    public MakePJVPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
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

    public void accounting1() {
        try {
            BaseTest.getDriver().manage().window().maximize();
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(accounting);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(accountPayble);
            Utilities.Click(makePJV);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Create AP Account", Status.FAIL, "Issue while creating AP Account", true, e);
        }
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

            // Click on Make PJV
            Utilities.Click(makePJV);
            ExtentTestManager.createAssertTestStepWithScreenshot("Click Make PJV", Status.PASS, "Clicked on 'Make PJV'", true);

            // Log completion
            ExtentTestManager.createAssertTestStepWithScreenshot("Accounting Navigation", Status.INFO, "Accounting navigation completed successfully", false);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Create AP Account", Status.FAIL, "Issue while creating AP Account", true, e);
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
            Utilities.SendKeys(BaseTest.getDriver(), supplierField, "%%");
            supplierField.sendKeys(Keys.TAB);

            toGrid();

            Random random = new Random();
            WebElement chosenSupplier = supplierCells.get(random.nextInt(supplierCells.size()));
            String chosenSupplierName = chosenSupplier.getText().trim();

// Perform double-click using Utilities
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), chosenSupplier);
            Utilities.DoubleClick(chosenSupplier);


            // Particulars
            toDlg();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), particularsField);
            Utilities.SendKeys(BaseTest.getDriver(), particularsField, uniqueParticulars);

            // Supplier bill no
            // Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), supplierBillNoField);

            Utilities.SendKeys(BaseTest.getDriver(), supplierBillNoField, uniqueBillNo);

            // Bill date pick → day 6
            // Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), supplierBillDateField);
            String currentdate = getCurrentDate.getText();
            System.out.println("Current Systsem Date is :" + currentdate);
            String date = Dateformat.getDateInGivenFormat(currentdate, "dd MMMM yyyy", "dd/MM/yyyy");
            System.out.println("Date is :" + date);

            Utilities.SendKeys(BaseTest.getDriver(), supplierBillDateField, date);


            // Amount (Ctrl/Meta + A then 100)
            toDlg();
            //Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), supplierBillAmountField);
            //Utilities.Click(BaseTest.getDriver(), supplierBillAmountField);
            supplierBillAmountField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

            //supplierBillAmountField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            Utilities.SendKeys(BaseTest.getDriver(), supplierBillAmountField, "100");

            // Account choose (%% + TAB → dblclick account)
            toDtl();
            //Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), accountCodeCell);
            //Utilities.Click(BaseTest.getDriver(), accountCodeCell);
            Utilities.SendKeys(BaseTest.getDriver(), accountCodeCell, "%%");
            accountCodeCell.sendKeys(Keys.TAB);

            toGrid();
            //Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), chosenSupplier);
            WebElement account = supplierCells.get(random.nextInt(supplierCells.size()));

            Utilities.DoubleClick(account);

            // Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), accountCell_LandAndBuilding);
            //Utilities.DoubleClick(accountCell_LandAndBuilding);

            // Click neutral area, Save & Close → OK
//            toDtl();
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), tableDataArea);
//            Utilities.Click(BaseTest.getDriver(), tableDataArea);

            toDlg();
            // Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), saveAndCloseButton);
            Utilities.Click(BaseTest.getDriver(), saveAndCloseButton);

            toGrid();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), okButtonInGridDialog);
            String invoiceNo = "";

            // Validate text contains expected phrase
            if (confDlg.getText().contains("Invoice Posted. Invoice number is")) {
                invoiceNo = confDlg.getText().replaceAll(".*Invoice number is\\s*(\\d+).*", "$1");

                ExtentTestManager.createAssertTestStepWithScreenshot("Validate Invoice Posted", com.aventstack.extentreports.Status.PASS, "Success message: " + confDlg.getText() + " | Extracted Invoice No: " + invoiceNo, true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Validate Invoice Posted", Status.FAIL, "Unexpected message: " + confDlg.getText(), true);
                throw new AssertionError("Invoice not posted successfully!");
            }


//            ExtentTestManager.createAssertTestStepWithScreenshot(
//                    "Add PJV (non-parameterized)", Status.PASS,
//                    "Supplier: A H KAUSHIKS ENTERPRISES | Particulars: testing | Bill#: 1111 | Day: 6 | Amount: 100 | Account: LAND AND BUILDING",
//                    true
//            );


            ExtentTestManager.createAssertTestStepWithScreenshot("Add PJV (dynamic non-parameterized)", Status.PASS, String.format("Supplier: %s | Particulars: %s | Bill#: %s | Day: 6 | Amount: 100 | Account: LAND AND BUILDING | Invoice#: %s", chosenSupplierName, uniqueParticulars, uniqueBillNo, invoiceNo), true);

            Utilities.Click(BaseTest.getDriver(), okButtonInGridDialog);


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
            ExtentTestManager.createAssertTestStepWithScreenshot("Click Add Button", Status.INFO, "Clicked on Add button to create a new PJV entry.", true);

            // Supplier selection
            toDlg();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), supplierField);
            Utilities.Click(BaseTest.getDriver(), supplierField);
            Utilities.SendKeys(supplierField, "PROW003");
            //Utilities.SendKeys(BaseTest.getDriver(), supplierField, "%%");
            supplierField.sendKeys(Keys.TAB);

//            toGrid();
            Random random = new Random();
//            WebElement chosenSupplier = supplierCells.get(random.nextInt(supplierCells.size()));
//            String chosenSupplierName = chosenSupplier.getText().trim();
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), chosenSupplier);
//            Utilities.DoubleClick(chosenSupplier);
//            uncomment if you want to chose the random supplier

            // ExtentTestManager.createAssertTestStepWithScreenshot("Select Supplier", Status.INFO, "Random supplier selected: " + chosenSupplierName, true);

            // Enter particulars
            toDlg();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), particularsField);
            Utilities.SendKeys(BaseTest.getDriver(), particularsField, uniqueParticulars);
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Particulars", Status.INFO, "Entered particulars: " + uniqueParticulars, false);

            // Enter supplier bill number
            Utilities.SendKeys(BaseTest.getDriver(), supplierBillNoField, uniqueBillNo);
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Supplier Bill No", Status.INFO, "Entered supplier bill number: " + uniqueBillNo, false);

            // Enter bill date
            String currentdate = getCurrentDate.getText();
            date = Dateformat.getDateInGivenFormat(currentdate, "dd MMMM yyyy", "dd/MM/yyyy");
            Utilities.SendKeys(BaseTest.getDriver(), supplierBillDateField, date);
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Bill Date", Status.INFO, "Bill date set to: " + date, false);

            // Enter amount
            toDlg();
            supplierBillAmountField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            Utilities.SendKeys(BaseTest.getDriver(), supplierBillAmountField, "100");
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Amount", Status.INFO, "Entered bill amount: 100", false);

            // Select account
            toDtl();
            Utilities.SendKeys(BaseTest.getDriver(), accountCodeCell, "%%");
            accountCodeCell.sendKeys(Keys.TAB);

            toGrid();
            WebElement account = supplierCells.get(random.nextInt(supplierCells.size()));
            Utilities.DoubleClick(account);
            ExtentTestManager.createAssertTestStepWithScreenshot("Select Account", Status.INFO, "Random account selected", true);

            // Save & validate invoice
            toDlg();
            Utilities.Click(BaseTest.getDriver(), saveAndCloseButton);
            toGrid();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), okButtonInGridDialog);

            //String invoiceNo = "";
            if (confDlg.getText().contains("Invoice Posted. Invoice number is")) {
                invoiceNo = confDlg.getText().replaceAll(".*Invoice number is\\s*(\\d+).*", "$1");
                ExtentTestManager.createAssertTestStepWithScreenshot("Validate Invoice Posted", Status.PASS, "Success message: " + confDlg.getText() + " | Extracted Invoice No: " + invoiceNo, true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Validate Invoice Posted", Status.FAIL, "Unexpected message: " + confDlg.getText(), true);
                throw new AssertionError("Invoice not posted successfully!");
            }

            // Log PJV summary
            ExtentTestManager.createAssertTestStepWithScreenshot("Add PJV (dynamic)", Status.PASS, String.format("Particulars: %s | Bill#: %s | Amount: 100 | Account: LAND AND BUILDING | Invoice#: %s", uniqueParticulars, uniqueBillNo, invoiceNo), true);

            Utilities.Click(BaseTest.getDriver(), okButtonInGridDialog);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Add PJV Entry", Status.FAIL, "Flow failed", true, e);
        } finally {
            BaseTest.getDriver().switchTo().defaultContent();
            DynamicWait.smallWait();
        }
    }

    public void addPJVEntryTEPD() {
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
            Utilities.SendKeys(BaseTest.getDriver(), supplierField, "NUM007");
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
            // Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), supplierBillNoField);

            Utilities.SendKeys(BaseTest.getDriver(), supplierBillNoField, uniqueBillNo);

            // Bill date pick → day 6
            // Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), supplierBillDateField);
            String currentdate = getCurrentDate.getText();
            System.out.println("Current Systsem Date is :" + currentdate);
            String date = Dateformat.getDateInGivenFormat(currentdate, "dd MMMM yyyy", "dd/MM/yyyy");
            System.out.println("Date is :" + date);

            Utilities.SendKeys(BaseTest.getDriver(), supplierBillDateField, date);


            // Amount (Ctrl/Meta + A then 100)
            toDlg();
            //Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), supplierBillAmountField);
            //Utilities.Click(BaseTest.getDriver(), supplierBillAmountField);
            supplierBillAmountField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

            //supplierBillAmountField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            Utilities.SendKeys(BaseTest.getDriver(), supplierBillAmountField, "100");

            // Account choose (%% + TAB → dblclick account)
            toDtl();
            //Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), accountCodeCell);
            //Utilities.Click(BaseTest.getDriver(), accountCodeCell);
            Utilities.SendKeys(BaseTest.getDriver(), accountCodeCell, "%%");
            accountCodeCell.sendKeys(Keys.TAB);

            toGrid();
            //Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), chosenSupplier);
            WebElement account = supplierCells.get(random.nextInt(supplierCells.size()));
            Utilities.DoubleClick(account);
            toGrid();
            Utilities.DoubleClick(department);


            // Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), accountCell_LandAndBuilding);
            //Utilities.DoubleClick(accountCell_LandAndBuilding);

            // Click neutral area, Save & Close → OK
//            toDtl();
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), tableDataArea);
//            Utilities.Click(BaseTest.getDriver(), tableDataArea);

            toDlg();
            // Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), saveAndCloseButton);
            Utilities.Click(BaseTest.getDriver(), saveAndCloseButton);

            toGrid();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), okButtonInGridDialog);
            String invoiceNo = "";

            // Validate text contains expected phrase
            if (confDlg.getText().contains("Invoice Posted. Invoice number is")) {
                invoiceNo = confDlg.getText().replaceAll(".*Invoice number is\\s*(\\d+).*", "$1");

                ExtentTestManager.createAssertTestStepWithScreenshot("Validate Invoice Posted", com.aventstack.extentreports.Status.PASS, "Success message: " + confDlg.getText() + " | Extracted Invoice No: " + invoiceNo, true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Validate Invoice Posted", Status.FAIL, "Unexpected message: " + confDlg.getText(), true);
                throw new AssertionError("Invoice not posted successfully!");
            }


//            ExtentTestManager.createAssertTestStepWithScreenshot(
//                    "Add PJV (non-parameterized)", Status.PASS,
//                    "Supplier: A H KAUSHIKS ENTERPRISES | Particulars: testing | Bill#: 1111 | Day: 6 | Amount: 100 | Account: LAND AND BUILDING",
//                    true
//            );


            ExtentTestManager.createAssertTestStepWithScreenshot("Add PJV (dynamic non-parameterized)", Status.PASS, String.format("Supplier: %s | Particulars: %s | Bill#: %s | Day: 6 | Amount: 100 | Account: LAND AND BUILDING | ", uniqueParticulars, uniqueBillNo, invoiceNo), true);

            Utilities.Click(BaseTest.getDriver(), okButtonInGridDialog);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Add PJV (non-parameterized)", Status.FAIL, "Flow failed", true, e);
        } finally {
            BaseTest.getDriver().switchTo().defaultContent();
            DynamicWait.smallWait();
        }
    }

    public void processpay() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeSubDlg");

            Utilities.Click(accountPayables);
            Utilities.Click(processPayment);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.SendKeys(pjvFrom, invoiceNo);
            Utilities.SendKeys(pjvTo, invoiceNo);
            Utilities.Click(search);
            Utilities.Click(process);
            Utilities.Click(saveButton);
            try {
                BaseTest.getDriver().switchTo().alert().accept();

            } catch (Exception e) {

            }

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(okButtonInGridDialog);


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(generalLedger);
            Utilities.Click(postInterface);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.SendKeys(enterDate, date);
            Utilities.Click(saveButton);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(okButtonInGridDialog);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(post);

            Utilities.Click(saveButton);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(okButtonInGridDialog);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(accountPayble);

            Utilities.Click(makePJV);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.SendKeys(voucherNumber, invoiceNo);

            Utilities.Click(search);
            Utilities.Click(viewEdit);

//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//
//            if (confDlg.isDisplayed() && confDlg.getText().contains("[4571] - All the Details for this Voucher cannot be modified"))
//                ;
//            {
//                ExtentTestManager.createAssertTestStepWithScreenshot("Data Transfer to GL", Status.PASS, "Data Transfer to GL Successfully", true);
//            }


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Advance Control Update", Status.FAIL, "Failed to update advance control value." + confDlg.getText(), true, e);
        }
    }

    public void multiCurrency() {
        String uniqueBillNo = "BILL" + System.currentTimeMillis();
        String uniqueParticulars = "TEST-" + System.currentTimeMillis();

        try {
            // Click Add button
            toBrw();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), addButton);
            Utilities.Click(BaseTest.getDriver(), addButton);
            ExtentTestManager.createAssertTestStepWithScreenshot("Click Add Button", Status.INFO, "Clicked on Add button to create a new PJV entry.", true);

            // Supplier selection
            toDlg();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), supplierField);
            Utilities.Click(BaseTest.getDriver(), supplierField);
            Utilities.SendKeys(supplierField, "10251231");
            //Utilities.SendKeys(BaseTest.getDriver(), supplierField, "%%");
            supplierField.sendKeys(Keys.TAB);

//            toGrid();
            Random random = new Random();
//            WebElement chosenSupplier = supplierCells.get(random.nextInt(supplierCells.size()));
//            String chosenSupplierName = chosenSupplier.getText().trim();
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), chosenSupplier);
//            Utilities.DoubleClick(chosenSupplier);
//            uncomment if you want to chose the random supplier

            // ExtentTestManager.createAssertTestStepWithScreenshot("Select Supplier", Status.INFO, "Random supplier selected: " + chosenSupplierName, true);

            // Enter particulars
            toDlg();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), particularsField);
            Utilities.SendKeys(BaseTest.getDriver(), particularsField, uniqueParticulars);
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Particulars", Status.INFO, "Entered particulars: " + uniqueParticulars, false);

            // Enter supplier bill number
            Utilities.SendKeys(BaseTest.getDriver(), supplierBillNoField, uniqueBillNo);
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Supplier Bill No", Status.INFO, "Entered supplier bill number: " + uniqueBillNo, false);

            // Enter bill date
            String currentdate = getCurrentDate.getText();
            date = Dateformat.getDateInGivenFormat(currentdate, "dd MMMM yyyy", "dd/MM/yyyy");
            Utilities.SendKeys(BaseTest.getDriver(), supplierBillDateField, date);
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Bill Date", Status.INFO, "Bill date set to: " + date, false);

            // Enter amount
            toDlg();
            supplierBillAmountField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            Utilities.SendKeys(BaseTest.getDriver(), supplierBillAmountField, "100");
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Amount", Status.INFO, "Entered bill amount: 100", false);

            // Select account
            toDtl();
            Utilities.SendKeys(BaseTest.getDriver(), accountCodeCell, "%%");
            accountCodeCell.sendKeys(Keys.TAB);

            toGrid();
            WebElement account = supplierCells.get(random.nextInt(supplierCells.size()));
            Utilities.DoubleClick(account);
            ExtentTestManager.createAssertTestStepWithScreenshot("Select Account", Status.INFO, "Random account selected", true);

            // Save & validate invoice
            toDlg();
            Utilities.Click(BaseTest.getDriver(), saveAndCloseButton);
            toGrid();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), okButtonInGridDialog);

            //String invoiceNo = "";
            if (confDlg.getText().contains("Invoice Posted. Invoice number is")) {
                invoiceNo = confDlg.getText().replaceAll(".*Invoice number is\\s*(\\d+).*", "$1");
                ExtentTestManager.createAssertTestStepWithScreenshot("Validate Invoice Posted", Status.PASS, "Success message: " + confDlg.getText() + " | Extracted Invoice No: " + invoiceNo, true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Validate Invoice Posted", Status.FAIL, "Unexpected message: " + confDlg.getText(), true);
                throw new AssertionError("Invoice not posted successfully!");
            }

            // Log PJV summary
            ExtentTestManager.createAssertTestStepWithScreenshot("Add PJV (dynamic)", Status.PASS, String.format("Particulars: %s | Bill#: %s | Amount: 100 | Account: LAND AND BUILDING | Invoice#: %s", uniqueParticulars, uniqueBillNo, invoiceNo), true);

            Utilities.Click(BaseTest.getDriver(), okButtonInGridDialog);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Add PJV Entry", Status.FAIL, "Flow failed", true, e);
        } finally {
            BaseTest.getDriver().switchTo().defaultContent();
            DynamicWait.smallWait();
        }
    }


}


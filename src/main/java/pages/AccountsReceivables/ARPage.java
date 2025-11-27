package pages.AccountsReceivables;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DynamicWait;
import utils.Utilities;

import java.time.Duration;
import java.util.List;
import java.util.Random;


public class ARPage extends Utilities {

    String uniqueAccount = "";
    String date = "";
    String arAccount = "";
    String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\UploadData\\img.jpg";
    WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(15));
    @FindBy(id = "divmodule_2")
    private WebElement accountsReceivablesLink;
    @FindBy(xpath = "//a[text()='Create/Modify AR Account']")
    private WebElement createModifyARAccountLink;
    @FindBy(id = "cphDialogHeader_btnAddRecords")
    private WebElement addButton;
    @FindBy(id = "cphBrowserHeader_btnAddNew")
    private WebElement addButtons;
    @FindBy(id = "cphBody_txtAccountType")
    private WebElement accountTypeField;
    @FindBy(id = "cphBody_txtReceivablesAccountCode")
    private WebElement receivableAccountCodeField;
    @FindBy(id = "cphBody_txtName")
    private WebElement nameField;
    @FindBy(id = "cphBody_txtAddress1")
    private WebElement address1Field;
    @FindBy(id = "cphBody_txtAddress2")
    private WebElement address2Field;
    @FindBy(id = "cphBody_txtAddress3")
    private WebElement address3Field;
    @FindBy(id = "cphBody_txtcity")
    private WebElement cityField;
    @FindBy(id = "cphBody_txtstate")
    private WebElement stateField;
    @FindBy(id = "cphBody_txtzip")
    private WebElement zipField;
    @FindBy(id = "cphBody_txtcountry")
    private WebElement countryField;
    @FindBy(id = "cphBody_txtCoveringLetter")
    private WebElement coveringLetterField;
    @FindBy(id = "cphDialogHeader_btnSave")
    private WebElement saveButton;
    @FindBy(id = "btnOK")
    private WebElement okButton;
    @FindBy(xpath = "//td[text()='STAFF']")
    private WebElement staffCell;
    @FindBy(id = "LinkProduct0")
    private WebElement accounting;
    @FindBy(xpath = "//*[starts-with(@id, 'td_') and substring(@id, string-length(@id) - 1) = '_1']")
    private List<WebElement> accountGroup;
    @FindBy(id = "lblErrorDesc")
    private WebElement confDlg;
    @FindBy(id = "iframeGridDialog")
    private WebElement iframeGridDialog;
    @FindBy(id = "MultiPageiframeBrw")
    private WebElement multiPageFrame;
    @FindBy(id = "col_1")
    private WebElement companyCodeField;
    @FindBy(id = "FilterTD_1_1")
    private WebElement filterButton;
    @FindBy(id = "cphBody_txtAddress1")
    private WebElement addressLine1;
    @FindBy(id = "cphBody_txtAddress2")
    private WebElement addressLine2;
    @FindBy(id = "tdifmGridDialog")
    private WebElement gridDialog;
    @FindBy(id = "chkFreeze")
    private WebElement freeze;
    // Iframes
    @FindBy(id = "MultiPageiframeBrw")
    private WebElement iframeBrowser;
    @FindBy(id = "MultiPageiframeDlg")
    private WebElement iframeDialog;
    @FindBy(id = "iframeDtl")
    private WebElement iframeDetail;
    // Elements
    @FindBy(xpath = "//*[text()='Transaction Entry']")
    private WebElement transactionEntryLink;
    @FindBy(id = "cphBody_rdoInvoice")
    private WebElement invoiceRadio;
    @FindBy(id = "cphBody_txtDrAccCode")
    private WebElement drAccCode;
    @FindBy(xpath = "//td[starts-with(@id,'td_') and substring(@id,string-length(@id)-1) = '_1']")
    private List<WebElement> arAccouont;
    @FindBy(id = "cphBody_txtTranNote")
    private WebElement tranNote;
    @FindBy(id = "ar_ref_doc_no_0_0")
    private WebElement docNo;
    @FindBy(id = "cphBody_txtTranDate")
    private WebElement tranDate;
    @FindBy(id = "ar_ref_doc_date_1_0")
    private WebElement refDocDate;
    @FindBy(id = "ar_ref_particulars_2_0")
    private WebElement particulars;
    @FindBy(id = "ar_taxable_amount_5_0")
    private WebElement taxableAmount;
    @FindAll({@FindBy(id = "ar_taxable_amount_5_0"), @FindBy(id = "fx_ar_taxable_amount_6_0")})
    private List<WebElement> dynamicAmountFields;
    @FindBy(id = "acct_code_2_0")
    private WebElement accountCode;
    @FindBy(xpath = "//td[text()='P & L ACCOUNT']")
    private WebElement plAccountCell;
    @FindBy(id = "cphDialogHeader_btnSaveClose")
    private WebElement saveCloseButton;
    @FindBy(id = "gst_type_8_0")
    private WebElement addTax;
    @FindBy(id = "cphBody_rdoCreditNote")
    private WebElement creditNoteRadio;
    @FindBy(id = "cphBody_rdoDebitNote")
    private WebElement debitNoteRadio;
    @FindBy(id = "cphBody_txtTranDate")
    private WebElement getDate;
    @FindBy(xpath = "//*[starts-with(@id, 'ar_ref_doc_no_0_')]")
    private List<WebElement> invoiceNo1;
    @FindBy(id = "btnAddRow")
    private WebElement addRow;
    //    @FindBy(id = "fx_ar_taxable_amount_6_0")
//    private WebElement forexAmount;
    @FindBy(xpath = "//*[@id='ar_taxable_amount_5_0' or @id='fx_ar_taxable_amount_6_0']")
    private WebElement forexAmount;
    @FindBy(id = "gst_type_8_0")
    private WebElement gstType;
    @FindBy(id = "td_0_3")
    private WebElement selectGL;
    @FindBy(id = "cphBody_rdoPayment")
    private WebElement paymentRadio;
    @FindBy(xpath = "//input[starts-with(@id,'ar_taxable_amount_5_') or starts-with(@id,'fx_ar_taxable_amount_6_')]")
    private List<WebElement> amountFields;
    @FindBy(xpath = "//input[starts-with(@id,'acct_code_2_')]")
    private List<WebElement> acctCodeList;
    @FindBy(id = "cphBody_imgCity")
    private WebElement city;
    @FindBy(id = "td_0_1")
    private WebElement selectCity;
    @FindBy(id = "cphBody_txtCheqNo")
    private WebElement chequeNo;
    @FindBy(id = "cphBody_txtcheque_clear_dt")
    private WebElement clearDate;
    @FindBy(id = "cphBody_txtChequeDate")
    private WebElement chequeDate;
    @FindBy(id = "cphBody_imgBank")
    private WebElement bank;
    @FindBy(id = "cphBody_txtCommision")
    private WebElement tds;
    @FindBy(xpath = "//input[starts-with(@id,'ar_gl_particulars_11_')]")
    private List<WebElement> particularsList;
    @FindBy(id = "cphBody_txtref_gst_invoice_date")
    private WebElement invDate;
    @FindBy(id = "cphBody_txtref_gst_invoice_no")
    private WebElement reference;
    @FindBy(id = "btnUpload")
    private WebElement uploadBtn;
    @FindBy(id = "cphBrowserHeader_btnViewEdit")
    private WebElement viewEdit;
    @FindBy(id = "cphBody_btnSearch")
    private WebElement searchBtn;
    @FindBy(id = "cphBody_txtAcctCode")
    private WebElement arAcc;
    @FindBy(id = "cphBody_chkIncludeSettled")
    private WebElement includeSettled;
    @FindBy(id = "cphBody_lblToAttachViewDocumentClickHere")
    private WebElement attach;
    @FindBy(id = "txtUploadPic")
    private WebElement upload;
    @FindBy(id = "cphBody_txtReceivableAcctCode_1")
    private WebElement receCode;
    @FindBy(id = "menutxtSearch")
    private WebElement menuSearch;
    @FindBy(xpath = "//a[contains(@onclick,'form_id=AR08') and contains(@onclick,'Transfer Transactions')]")
    private WebElement transferTrans;
    @FindBy(id = "cphBody_txtReceivableAcctCode")
    private WebElement reveivableAccount;
    @FindBy(id = "istransfer_10_0")
    private WebElement istransfer;
    @FindBy(id = "cphBody_btnNext")
    private WebElement next;
    @FindBy(xpath = "//a[@title='AR14 Invoice Inquiry']")
    private WebElement invoiceInquiry;
    @FindBy(xpath = "//a[@title='AR11 Document Inquiry']")
    private WebElement docInquiry;
    @FindBy(id = "cphBody_txtReceivableAcctCode")
    private WebElement recAccCode;
    @FindBy(xpath = "//a[@title='AR24 Cheque Inquiry']")
    private WebElement chequeInquiry;

    public ARPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement invoiceNo(int itemId) {
        String xpath = "//input[@id='ar_ref_doc_no_0_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath));
    }

    public WebElement docDate(int itemId) {
        String xpath = "//input[@id='ar_ref_doc_date_1_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath));
    }

    public WebElement particular(int itemId) {
        String xpath = "//input[@id='ar_ref_particulars_2_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath));
    }

    public WebElement amount(int itemId) {
        String xpath = "//input[@id='ar_taxable_amount_5_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath));
    }

    public WebElement amountIHCL(int itemId) {
        String xpath = "//input[@id='fx_ar_taxable_amount_6_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath));
    }

    public WebElement tax(int itemId) {
        String xpath = "//input[@id='gst_type_8_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath));
    }

    public void navigateToARAccount() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            Utilities.Click(accounting);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), accountsReceivablesLink);
            Utilities.Click(BaseTest.getDriver(), accountsReceivablesLink);

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), createModifyARAccountLink);
            Utilities.Click(BaseTest.getDriver(), createModifyARAccountLink);

            ExtentTestManager.createAssertTestStepWithScreenshot("Navigate to AR Account", Status.PASS, "Navigated to AR Account screen successfully", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Navigate to AR Account", Status.FAIL, "Failed to navigate to AR Account screen", true, e);
        }
    }

    public void createARAccount() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), addButton);
            Utilities.Click(BaseTest.getDriver(), addButton);

            Utilities.SendKeys(BaseTest.getDriver(), accountTypeField, "%%");
            DynamicWait.smallWait();
            accountTypeField.sendKeys(Keys.TAB);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            if (!accountGroup.isEmpty()) {
                WebElement lastElement = accountGroup.get(accountGroup.size() - 1);
                ExtentTestManager.getTest().info("Clicking last element with id: " + lastElement.getAttribute("id"));
                Utilities.DoubleClick(lastElement);
            } else {
                ExtentTestManager.getTest().fail("No elements found with id pattern td_*_1");
            }
            //Utilities.DoubleClick(staffCell);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            uniqueAccount = Utilities.generateUniqueCode("PF", 8);
            Utilities.SendKeys(BaseTest.getDriver(), receivableAccountCodeField, uniqueAccount);
            Utilities.SendKeys(BaseTest.getDriver(), nameField, "Prologic First");
            Utilities.SendKeys(BaseTest.getDriver(), address1Field, "Phase V");
            Utilities.SendKeys(BaseTest.getDriver(), address2Field, "Sector 19");
            Utilities.SendKeys(BaseTest.getDriver(), cityField, "Gurgaon");
            Utilities.SendKeys(BaseTest.getDriver(), stateField, "HR");
            Utilities.SendKeys(BaseTest.getDriver(), zipField, "190120");
            Utilities.SendKeys(BaseTest.getDriver(), countryField, "INDIA");
            Utilities.SendKeys(BaseTest.getDriver(), coveringLetterField, "1100");

            Utilities.Click(BaseTest.getDriver(), saveButton);
            System.out.println("Account ID is : " + uniqueAccount);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            if (confDlg.isDisplayed() && confDlg.getText().contains("[238] - Details Created/Updated")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Create AR Account", Status.PASS, "AR Account created successfully", true);

            } else {

            }


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Create AR Account", Status.FAIL, "Failed to create AR Account : " + confDlg.getText(), true, e);
        }

    }

    public void updateCompanyAddress() {
        try {
            // Switch to first iframe and click OK
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), okButton);
            Utilities.Click(BaseTest.getDriver(), okButton);

            // Switch to main multi-page iframe
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Enter company code
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), companyCodeField);
            Utilities.Click(BaseTest.getDriver(), companyCodeField);
            Utilities.SendKeys(BaseTest.getDriver(), companyCodeField, uniqueAccount);

            // Click filter icon
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), filterButton);
            Utilities.Click(BaseTest.getDriver(), filterButton);

            // Double click and clear Address1 field
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), addressLine1);
            Utilities.Click(BaseTest.getDriver(), addressLine1);
            Utilities.DoubleClick(addressLine1);
            Utilities.SendKeys(BaseTest.getDriver(), addressLine1, "");
            DynamicWait.smallWait();
            Utilities.SendKeys(BaseTest.getDriver(), addressLine1, "Sector 19");

            // Fill Address2
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), addressLine2);
            Utilities.Click(BaseTest.getDriver(), addressLine2);
            Utilities.SendKeys(BaseTest.getDriver(), addressLine2, "Udhyog Vihar");

            // Save details
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), saveButton);
            Utilities.Click(BaseTest.getDriver(), saveButton);

            // Optional validation step
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            if (confDlg.isDisplayed() && confDlg.getText().contains("[238] - Details Created/Updated")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Update Address", Status.PASS, "Address updated successfully for : " + uniqueAccount, true);

            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Update Address", Status.FAIL, "Address updated failed for : " + confDlg.getText(), true);
            }

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Update Address", Status.FAIL, "Failed to update company address", true, e);
        }
    }

    public void freeze() {
        try {
            // Switch to first iframe and click OK
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), okButton);
            Utilities.Click(BaseTest.getDriver(), okButton);

            // Switch to main multi-page iframe
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Enter company code
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), companyCodeField);
            Utilities.Click(BaseTest.getDriver(), companyCodeField);
            Utilities.SendKeys(BaseTest.getDriver(), companyCodeField, uniqueAccount);

            // Click filter icon
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), filterButton);
            Utilities.Click(BaseTest.getDriver(), filterButton);

            Utilities.Click(freeze);


            // Save details
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), saveButton);
            Utilities.Click(BaseTest.getDriver(), saveButton);

            // Optional validation step
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            if (confDlg.isDisplayed() && confDlg.getText().contains("[238] - Details Created/Updated")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Update Address", Status.PASS, "Address updated successfully for : " + uniqueAccount, true);

            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Update Address", Status.FAIL, "Address updated failed for : " + confDlg.getText(), true);
            }

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Update Address", Status.FAIL, "Failed to update company address", true, e);
        }
    }

    public void xlUpload() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void transectionType() {
        try {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void ar() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            Utilities.Click(accounting);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), accountsReceivablesLink);
            Utilities.Click(BaseTest.getDriver(), accountsReceivablesLink);

            // Transaction Entry (inside main iframe)
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(iframeBrowser);
            Utilities.Click(BaseTest.getDriver(), transactionEntryLink);

            // Click Add
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            Utilities.Click(addButtons);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void selectTransactionType(String type) {
        try {

            ExtentTestManager.getTest().info("Selecting transaction type: " + type);
// Switch to Dialog iframe
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(iframeDialog);

            switch (type.toLowerCase()) {

                case "payment":
                    Utilities.Click(paymentRadio);
                    ExtentTestManager.getTest().pass("Payment radio selected");
                    break;

                case "invoice":
                    Utilities.Click(invoiceRadio);
                    ExtentTestManager.getTest().pass("Invoice radio selected");
                    break;

                case "credit note":
                    Utilities.Click(creditNoteRadio);
                    ExtentTestManager.getTest().pass("Credit Note radio selected");
                    break;

                case "debit note":
                    Utilities.Click(debitNoteRadio);
                    ExtentTestManager.getTest().pass("Debit Note radio selected");
                    break;

                default:
                    ExtentTestManager.getTest().fail("Invalid transaction type: " + type);
                    throw new IllegalArgumentException("Invalid transaction type: " + type);
            }


        } catch (Exception e) {
            ExtentTestManager.getTest().fail("Failed to select transaction type: " + e.getMessage());

        }
    }

    public void transectionDetails(boolean DebitNotes) {
        try {

            // Dr Account Code "%%"
            Utilities.Click(BaseTest.getDriver(), drAccCode);
            Utilities.SendKeys(BaseTest.getDriver(), drAccCode, "%%");

            DynamicWait.smallWait();
            drAccCode.sendKeys(org.openqa.selenium.Keys.TAB);

            // Select ACP Tollways (inside grid iframe)
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(iframeGridDialog);
            Utilities.DoubleClick(arAccouont.get(0));


            // Back to Dialog iframe
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(iframeDialog);
            arAccount = drAccCode.getAttribute("value");
            System.out.println("Account code is : " + arAccount);

            date = getDate.getAttribute("value");

            if (DebitNotes) {
                SendKeys(invDate, date);
                SendKeys(reference, "Reference");

            }

            // Enter Note
            Utilities.Click(BaseTest.getDriver(), tranNote);
            Utilities.SendKeys(BaseTest.getDriver(), tranNote, "Notes");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void invoice(int iterations) {
        for (int i = 0; i <= iterations; i++) {


            ExtentTestManager.getTest().info("Invoice iteration: " + i);

            try {


                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                BaseTest.getDriver().switchTo().frame(iframeDetail);

                if (i > 0) {
                    Utilities.Click(addRow);
                }

                Utilities.Click(BaseTest.getDriver(), invoiceNo(i));
                Utilities.SendKeys(BaseTest.getDriver(), invoiceNo(i), "INV" + i);

                Utilities.SendKeys(BaseTest.getDriver(), docDate(i), date);
                Utilities.SendKeys(BaseTest.getDriver(), particular(i), "Particulars " + i);


                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                BaseTest.getDriver().switchTo().frame(iframeDetail);


                try {
                    Utilities.Click(amountIHCL(i));
                    amountIHCL(i).sendKeys(Keys.CONTROL + "A", Keys.DELETE);
                    Utilities.SendKeys(amountIHCL(i), "100");
                } catch (Exception e) {

                }


//                Utilities.Click(amountFields.get(0));
//                amountFields.get(0).sendKeys(Keys.CONTROL+"A",Keys.DELETE);
//                Utilities.SendKeys(amountFields.get(0),"100");


//                try{
//                    Utilities.Click(BaseTest.getDriver(), amount(i));
//                    amount(i).sendKeys(Keys.CONTROL + "A", Keys.DELETE);
//                    Utilities.SendKeys(BaseTest.getDriver(), amount(i), String.valueOf(100));
//                } catch (Exception e) {
//                     Utilities.Click(amountIHCL(i));
//                     amountIHCL(i).sendKeys(Keys.CONTROL+"A",Keys.DELETE);
//                     Utilities.SendKeys(amountIHCL(i),"100");
//                }


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void invoice() {
        try {
            // Back to Dialog iframe
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(iframeDialog);

            // Enter Note
            Utilities.Click(BaseTest.getDriver(), tranNote);
            Utilities.SendKeys(BaseTest.getDriver(), tranNote, "Notes");

            // Detail Frame
            BaseTest.getDriver().switchTo().frame(iframeDetail);

            Utilities.Click(BaseTest.getDriver(), docNo);
            Utilities.SendKeys(BaseTest.getDriver(), docNo, "INV123");

            Utilities.SendKeys(BaseTest.getDriver(), refDocDate, "20/09/2025");
            Utilities.SendKeys(BaseTest.getDriver(), particulars, "Particulars");

            Utilities.Click(BaseTest.getDriver(), taxableAmount);
            taxableAmount.sendKeys(Keys.CONTROL + "A", Keys.DELETE);
            Utilities.SendKeys(BaseTest.getDriver(), taxableAmount, "100");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void performTransactionEntry() {
        try {

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            Utilities.Click(accounting);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), accountsReceivablesLink);
            Utilities.Click(BaseTest.getDriver(), accountsReceivablesLink);

            // Transaction Entry (inside main iframe)
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(iframeBrowser);
            Utilities.Click(BaseTest.getDriver(), transactionEntryLink);

            // Click Add
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            Utilities.Click(addButtons);


            //Utilities.Click(BaseTest.getDriver(), addButtons.get(0));

            // Switch to Dialog iframe
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(iframeDialog);

            // Select Invoice
            Utilities.Click(BaseTest.getDriver(), invoiceRadio);

            // Dr Account Code "%%"
            Utilities.Click(BaseTest.getDriver(), drAccCode);
            Utilities.SendKeys(BaseTest.getDriver(), drAccCode, "%%");

            DynamicWait.smallWait();
            drAccCode.sendKeys(org.openqa.selenium.Keys.TAB);

            // Select ACP Tollways (inside grid iframe)
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(iframeGridDialog);
            Utilities.DoubleClick(arAccouont.get(0));

            // Back to Dialog iframe
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(iframeDialog);

            // Enter Note
            Utilities.Click(BaseTest.getDriver(), tranNote);
            Utilities.SendKeys(BaseTest.getDriver(), tranNote, "Notes");

            // Detail Frame
            BaseTest.getDriver().switchTo().frame(iframeDetail);

            Utilities.Click(BaseTest.getDriver(), docNo);
            Utilities.SendKeys(BaseTest.getDriver(), docNo, "INV123");

            Utilities.SendKeys(BaseTest.getDriver(), refDocDate, "20/09/2025");
            Utilities.SendKeys(BaseTest.getDriver(), particulars, "Particulars");

            Utilities.Click(BaseTest.getDriver(), taxableAmount);
            taxableAmount.sendKeys(Keys.CONTROL + "A", Keys.DELETE);
            Utilities.SendKeys(BaseTest.getDriver(), taxableAmount, "100");


            // Account Code
            BaseTest.getDriver().switchTo().parentFrame();
            Utilities.Click(BaseTest.getDriver(), accountCode);
            Utilities.SendKeys(BaseTest.getDriver(), accountCode, "%%");

            accountCode.sendKeys(org.openqa.selenium.Keys.TAB);

            // Select P&L Account (grid dialog)
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(iframeGridDialog);
            Utilities.DoubleClick(plAccountCell);

            // Save & Close
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(iframeDialog);
            Utilities.Click(BaseTest.getDriver(), saveCloseButton);

            ExtentTestManager.createAssertTestStepWithScreenshot("Transaction Entry", Status.PASS, "Transaction Entry executed successfully", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Transaction Entry", Status.FAIL, "Failed during Transaction Entry", true, e);
        }
    }

    public void withTax() {
        try {

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            Utilities.Click(accounting);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), accountsReceivablesLink);
            Utilities.Click(BaseTest.getDriver(), accountsReceivablesLink);

            // Transaction Entry (inside main iframe)
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(iframeBrowser);
            Utilities.Click(BaseTest.getDriver(), transactionEntryLink);

            // Click Add
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            Utilities.Click(addButtons);


            //Utilities.Click(BaseTest.getDriver(), addButtons.get(0));

            // Switch to Dialog iframe
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(iframeDialog);

            // Select Invoice
            Utilities.Click(BaseTest.getDriver(), invoiceRadio);

            // Dr Account Code "%%"
            Utilities.Click(BaseTest.getDriver(), drAccCode);
            Utilities.SendKeys(BaseTest.getDriver(), drAccCode, "%%");

            DynamicWait.smallWait();
            drAccCode.sendKeys(org.openqa.selenium.Keys.TAB);

            // Select ACP Tollways (inside grid iframe)
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(iframeGridDialog);
            Utilities.DoubleClick(arAccouont.get(0));

            // Back to Dialog iframe
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(iframeDialog);

            // Enter Note
            Utilities.Click(BaseTest.getDriver(), tranNote);
            Utilities.SendKeys(BaseTest.getDriver(), tranNote, "Notes");

            // Detail Frame
            BaseTest.getDriver().switchTo().frame(iframeDetail);

            Utilities.Click(BaseTest.getDriver(), docNo);
            Utilities.SendKeys(BaseTest.getDriver(), docNo, "INV123");

            Utilities.SendKeys(BaseTest.getDriver(), refDocDate, "20/09/2025");
            Utilities.SendKeys(BaseTest.getDriver(), particulars, "Particulars");

            Utilities.Click(BaseTest.getDriver(), forexAmount);
            forexAmount.sendKeys(Keys.CONTROL + "A", Keys.DELETE);
            Utilities.SendKeys(BaseTest.getDriver(), forexAmount, "100");

            Utilities.SendKeys(gstType, "%%");
            gstType.sendKeys(Keys.TAB);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Utilities.DoubleClick(arAccouont.get(0));


            // Account Code
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), accountCode);
            Utilities.SendKeys(BaseTest.getDriver(), accountCode, "%%");

            accountCode.sendKeys(org.openqa.selenium.Keys.TAB);

            // Select P&L Account (grid dialog)
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(iframeGridDialog);
            Utilities.DoubleClick(arAccouont.get(0));


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            try {
                Utilities.DoubleClick(selectGL);
            } catch (Exception e) {

            }

            // Save & Close
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(iframeDialog);
            Utilities.Click(BaseTest.getDriver(), saveCloseButton);

            ExtentTestManager.createAssertTestStepWithScreenshot("Transaction Entry", Status.PASS, "Transaction Entry executed successfully", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Transaction Entry", Status.FAIL, "Failed during Transaction Entry", true, e);
        }
    }

    public void enterGL() {
        try {
            // Account Code
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");


            Utilities.Click(BaseTest.getDriver(), accountCode);
            Utilities.SendKeys(BaseTest.getDriver(), accountCode, "%%");

            accountCode.sendKeys(org.openqa.selenium.Keys.TAB);

            // Select P&L Account (grid dialog)
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(iframeGridDialog);
            Utilities.DoubleClick(arAccouont.get(5));


            try {
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                Utilities.DoubleClick(selectGL);
            } catch (Exception e) {

            }

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            //BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), saveCloseButton);

            try {
                BaseTest.getDriver().switchTo().alert().accept();
            } catch (Exception e) {

            }

            // Save & Close
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame(iframeDialog);
//            Utilities.Click(BaseTest.getDriver(), saveCloseButton);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void enterTax(int iterations) {
        try {
            for (int i = 0; i <= iterations; i++) {

                Utilities.SendKeys(tax(i), "STI1");
                tax(i).sendKeys(Keys.TAB);
//                BaseTest.getDriver().switchTo().defaultContent();
//                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//
//                Utilities.DoubleClick(arAccouont.get(0));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void chequeDetail(boolean TDS) {
        try {
            Click(city);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            DoubleClick(selectCity);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            int random = new Random().nextInt(900_000_000) + 100_000_000;

            SendKeys(chequeNo, String.valueOf(random));
            SendKeys(chequeDate, date);
            SendKeys(clearDate, date);
            Click(bank);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            DoubleClick(selectCity);

            if (TDS) {
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                SendKeys(tds, "100");
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void paymentParticular() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            BaseTest.getDriver().switchTo().frame("iframeDtl");
            SendKeys(particular(0), "Particulars");

            Click(forexAmount);
            forexAmount.sendKeys(Keys.CONTROL + "A", Keys.DELETE);
            SendKeys(forexAmount, "200");

//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            //BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            Utilities.Click(BaseTest.getDriver(), saveCloseButton);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void paymentGL() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), acctCodeList.get(1));
            Utilities.SendKeys(BaseTest.getDriver(), acctCodeList.get(1), "%%");
            acctCodeList.get(1).sendKeys(org.openqa.selenium.Keys.TAB);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(iframeGridDialog);
            Utilities.DoubleClick(arAccouont.get(5));


            try {
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                Utilities.DoubleClick(selectGL);
            } catch (Exception e) {

            }

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            for (WebElement ele : particularsList) {

                ExtentTestManager.getTest().info("Processing particulars field");

                Utilities.Click(BaseTest.getDriver(), ele);
                ExtentTestManager.getTest().info("Clicked particulars field");

                Utilities.SendKeys(BaseTest.getDriver(), ele, "Particulars");
                ExtentTestManager.getTest().pass("Entered 'Particulars' successfully");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void savePayment() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            //BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), saveCloseButton);
            try {
                BaseTest.getDriver().switchTo().alert().accept();
            } catch (Exception e) {

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void inquiryAttachment() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Click(okButton);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            SendKeys(arAcc, arAccount);
            Click(includeSettled);
            Click(searchBtn);
            DoubleClick(selectGL);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Click(attach);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            BaseTest.getDriver().switchTo().frame("iframeAttachFileDialog");
            SendKeys(upload, filePath);
            Click(uploadBtn);

            //Click(viewEdit);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void cn() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Click(searchBtn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void transferTran() {
        try {

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeSubDlg");
            SendKeys(menuSearch, "Transfer Transaction");
            Click(transferTrans);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            SendKeys(reveivableAccount, arAccount);
            Click(searchBtn);
            Click(istransfer);
            Click(next);
            SendKeys(receCode, "TA0001");
            Click(saveButton);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void transectionTEPD(boolean DebitNotes) {
        try {

            // Dr Account Code "%%"
            Utilities.Click(BaseTest.getDriver(), drAccCode);
            Utilities.SendKeys(BaseTest.getDriver(), drAccCode, "CC001");

            DynamicWait.smallWait();
            drAccCode.sendKeys(org.openqa.selenium.Keys.TAB);
//
//            // Select ACP Tollways (inside grid iframe)
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame(iframeGridDialog);
//            Utilities.DoubleClick(arAccouont.get(0));


            // Back to Dialog iframe
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(iframeDialog);
            arAccount = drAccCode.getAttribute("value");
            System.out.println("Account code is : " + arAccount);

            date = getDate.getAttribute("value");

            if (DebitNotes) {
                SendKeys(invDate, date);
                SendKeys(reference, "Reference");

            }

            // Enter Note
            Utilities.Click(BaseTest.getDriver(), tranNote);
            Utilities.SendKeys(BaseTest.getDriver(), tranNote, "Notes");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void invoiceInquiry() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            Utilities.Click(accounting);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), accountsReceivablesLink);
            Utilities.Click(BaseTest.getDriver(), accountsReceivablesLink);
            Click(invoiceInquiry);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Click(searchBtn);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void docInq() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(accounting);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), accountsReceivablesLink);
            Utilities.Click(BaseTest.getDriver(), accountsReceivablesLink);
            Click(docInquiry);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            SendKeys(recAccCode, "360one");
            Click(searchBtn);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void inquiry(InquiryType type) {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(accounting);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), accountsReceivablesLink);
            Utilities.Click(BaseTest.getDriver(), accountsReceivablesLink);


            switch (type) {
                case Doc_Inquiry:
                    Click(docInquiry);
                    break;

                case Invoice_Inquiry:
                    Click(invoiceInquiry);
                    break;

                case ARAccount_Inquiry:
                    Click(arAccountInquiry);
                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                    Click(searchBtn);

                    break;

                case Cheque_Inquiry:
                    Click(chequeInquiry);
                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                    Click(searchBtn);
                    break;

            }

//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
//            SendKeys(recAccCode, "360one");
//            Click(searchBtn);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public enum InquiryType {
        Doc_Inquiry, Invoice_Inquiry, Cheque_Inquiry, ARAccount_Inquiry
    }

    @FindBy(xpath = "//a[@title='AR62 AR Accounts Inquiry']")
    private WebElement arAccountInquiry;


}

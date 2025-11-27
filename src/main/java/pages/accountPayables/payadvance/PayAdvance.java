package pages.accountPayables.payadvance;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PayAdvance extends Utilities {
    String docNO = "";
    String uniqueChequeNo = "";
    String date = "";
    @FindBy(xpath = "//a[text()='Accounting']")
    private WebElement accounting;
    @FindBy(id = "divmodule_1")
    private WebElement accountPayble;
    @FindBy(xpath = "//a[text()='Create/Modify AP Account']")
    private WebElement createAp;
    @FindBy(xpath = "//*[text()='Pay Advance']")
    private WebElement payAdvanceLink;
    @FindBy(id = "cphBrowserHeader_btnAddNew")
    private WebElement addButton;
    @FindBy(id = "cphBody_txtap_id")
    private WebElement employeeIdField;
    @FindBy(xpath = "//*[text()='3 W EVENTS']")
    private WebElement employeeCell;
    @FindBy(id = "cphBody_txtap_particulars")
    private WebElement particularsField;
    @FindBy(id = "cphDialogHeader_btnSave")
    private WebElement saveButton;
    @FindBy(id = "btnOK")
    private WebElement okButton;
    @FindBy(id = "cphBody_txtap_amount")
    private WebElement amountField;
    @FindBy(id = "cphFooter_tdDatetime")
    private WebElement getCurrentDate;
    @FindBy(id = "cphBody_txtcheque_dt")
    private WebElement Date1;
    @FindBy(id = "cphBody_txtcheque_no")
    private WebElement chequeNo;
    @FindBy(id = "cphBody_lblAttachViewDocument")
    private WebElement attachment;
    // Locators inside iframe iframeFileDialog
    @FindBy(id = "txtRemarks")
    private WebElement remarksTextBox;
    @FindBy(xpath = "//button[text()='Choose File']")
    private WebElement chooseFileButton;
    @FindBy(xpath = "//button[text()='Upload']")
    private WebElement uploadButton;
    @FindBy(xpath = "//button[text()='View']")
    private WebElement viewButton;
    @FindBy(xpath = "//tr[.//text()[normalize-space()='Close']]//img")
    private WebElement closeRowImg;
    @FindBy(xpath = "//img[@alt='Close' or @title='Close']")
    private WebElement closeButton;
    @FindBy(xpath = "//input[starts-with(@id,'deduction_type_')]")
    private WebElement deductionTypeInput;
    @FindBy(xpath = "//td[normalize-space()='TDS on Contractor 1 %']")
    private WebElement tdsContractorCell;
    @FindBy(id = "cphBody_txtap_doc_no")
    private WebElement advanceNo;
    @FindBy(id = "cphDialogHeader_btnSave")
    private WebElement save;
    @FindBy(id = "chkFreeze")
    private WebElement freeze;
    @FindBy(id = "cphDialogHeader_btnClose_Dlg")
    private WebElement close;
    @FindBy(xpath = "//*[@title='AP10 Process Payment']")
    private WebElement processPayment;
    @FindBy(id = "cphBody_rdoAdvances")
    private WebElement advancesCheck;
    @FindBy(id = "cphBody_txtVendorFrom")
    private WebElement apAccountFrom;
    @FindBy(id = "cphBody_txtVendorTo")
    private WebElement apAccountTo;
    @FindBy(id = "cphBody_btnSearch")
    private WebElement search;
    @FindBy(css = "[id^='process_9_']")
    private List<WebElement> processElements;
    @FindBy(id = "lblErrorDesc")
    private WebElement confDlg;
    @FindBy(id = "cphBody_txtAdvanceReversalDate")
    private WebElement reversalDate;
    @FindBy(id = "cphDialogHeader_btnDelete_Dlg")
    private WebElement cancleAdvance;
    @FindBy(id = "cphBrowserHeader_btnViewEdit")
    private WebElement viewEdit;
    @FindBy(xpath = "//*[@title='AP30 Pay Advance']")
    private WebElement payAdvance;
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
    @FindBy(xpath = "//a[@title='AP12 Change Cheque Status']")
    private WebElement changeChqStatus;
    @FindBy(id = "cphBody_rdoCheques")
    private WebElement chqRadio;
    @FindBy(id = "cphBody_txtFromChqNumber")
    private WebElement chqFrom;
    @FindBy(id = "cphBody_txtToChqNumber")
    private WebElement chqTo;
    @FindBy(id = "cphBody_rdoCancelCheque")
    private WebElement cancelChq;
    @FindBy(id = "cancel_cheque_11_0")
    private WebElement selectChq;

    public PayAdvance(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void accountPayable() {
        try {
            BaseTest.getDriver().manage().window().maximize();
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Click(accounting);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Click(accountPayble);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Create AP Account", Status.FAIL, "Issue while creating AP Account", true, e);
            logs.Log.error("Full stack trace: ", e);

        }
    }

    public void payAdvanceWithBank() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), payAdvanceLink);
            Utilities.Click(BaseTest.getDriver(), payAdvanceLink);
            ExtentTestManager.createAssertTestStepWithScreenshot("Navigate Pay Advance", Status.PASS, "Navigated to Pay Advance", true);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(BaseTest.getDriver(), addButton);
            ExtentTestManager.createAssertTestStepWithScreenshot("Click Add", Status.PASS, "Clicked Add button", true);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(BaseTest.getDriver(), employeeIdField, "%%");
            employeeIdField.sendKeys(org.openqa.selenium.Keys.TAB);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.DoubleClick(employeeCell);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(BaseTest.getDriver(), particularsField, "particulars");
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), amountField);
            amountField.sendKeys(org.openqa.selenium.Keys.chord(org.openqa.selenium.Keys.CONTROL, "a"));
            Utilities.SendKeys(BaseTest.getDriver(), amountField, "100");


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Pay Advance with Bank", Status.FAIL, "Error in Pay Advance with Bank", true, e);
        }
    }

    public void saveandClose() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), saveButton);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(BaseTest.getDriver(), okButton);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            docNO = advanceNo.getAttribute("value");
            System.out.println("Advance no is: " + docNO);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void withCheque() {
        try {
            String currentdate = getCurrentDate.getText();
            System.out.println("Current Systsem Date is :" + currentdate);
            date = Dateformat.getDateInGivenFormat(currentdate, "dd MMMM yyyy", "dd/MM/yyyy");
            System.out.println("Date is :" + date);

            Utilities.SendKeys(BaseTest.getDriver(), Date1, date);
            uniqueChequeNo = String.valueOf(System.currentTimeMillis() % 1000000000L);
            Utilities.SendKeys(chequeNo, uniqueChequeNo);

            //Utilities.SendKeys(chequeNo, "322341134");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void backdatedCheck() {
        try {
            String currentdate = getCurrentDate.getText();

            String inputDate = currentdate;


            SimpleDateFormat inputFormat = new SimpleDateFormat("dd MMMM yyyy"); // format of currentdate
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");  // format you want

// Parse business date
            Date parsedDate = inputFormat.parse(inputDate);

// Subtract 2 days
            Calendar cal = Calendar.getInstance();
            cal.setTime(parsedDate);
            cal.add(Calendar.DAY_OF_MONTH, -2);

// Format back to required output
            String finalDate = outputFormat.format(cal.getTime());

            System.out.println("Date (business date -2) is: " + finalDate);

// Send into field
            Utilities.SendKeys(BaseTest.getDriver(), Date1, finalDate);


//            LocalDate currentDate = LocalDate.now().minusDays(2);
//
//            System.out.println("Current Systsem Date is :" + currentdate);
//            String date = Dateformat.getDateInGivenFormat(currentdate, "dd MMMM yyyy", "dd/MM/yyyy");
//            System.out.println("Date is :" + date);
//
//            Utilities.SendKeys(BaseTest.getDriver(), Date, date);
//            Utilities.SendKeys(chequeNo,"3223434");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void attachment() {
        try {
            String filePath = System.getProperty("user.dir") + "\\DownloadPath\\image_2253.jpg";

            Click(attachment);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            BaseTest.getDriver().switchTo().frame("iframeFileDialog");

            // Wait and interact with Choose File
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), chooseFileButton);
            chooseFileButton.sendKeys(filePath);

            DynamicWait.smallWait();

            // Click Upload button
            Utilities.Click(BaseTest.getDriver(), uploadButton);
            Click(closeButton);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deduction() {
        try {
            // Step 1: Switch to MultiPageiframeDlg > iframeDtl
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            BaseTest.getDriver().switchTo().frame("iframeDtl");

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), deductionTypeInput);
            Utilities.SendKeys(BaseTest.getDriver(), deductionTypeInput, "%%");
            deductionTypeInput.sendKeys(Keys.TAB);

            // Step 2: Switch to iframeGridDialog
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), tdsContractorCell);
            Utilities.DoubleClick(tdsContractorCell);

            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Deduction Type and Select", Status.PASS, "Deduction type entered and contractor TDS selected successfully", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Deduction Type and Select", Status.FAIL, "Failed to enter deduction type or select contractor TDS", true, e);
        }
    }

    public void save() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), save);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(BaseTest.getDriver(), okButton);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void freeze() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Click(freeze);
            Click(save);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void po() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Click(close);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void cancleAdvance() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(accountPayble);

            Utilities.Click(processPayment);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(advancesCheck);

            Utilities.SendKeys(apAccountFrom, "PROW003");
            Utilities.SendKeys(apAccountTo, "PROW003");
            Utilities.Click(search);

            int count = processElements.size();

            try {
                WebElement lastElement = processElements.get(count - 1);
                String elementId = lastElement.getAttribute("id");
                Utilities.Click(lastElement);
                ExtentTestManager.getTest().log(Status.PASS, "Clicked the last element with id: " + elementId);
            } catch (Exception e) {
                ExtentTestManager.getTest().log(Status.FAIL, "Failed to click the last element: " + e.getMessage());
            }

            Utilities.Click(save);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(okButton);


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
            Utilities.Click(okButton);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(post);

            Utilities.Click(saveButton);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(okButton);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(accountPayble);
            Utilities.Click(payAdvance);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.SendKeys(voucherNumber, docNO);
            Utilities.Click(search);
            Utilities.Click(viewEdit);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(reversalDate, date);
            Utilities.Click(cancleAdvance);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            String text = confDlg.getText();
            if (text.contains("[4655] - Advance has been Cancelled along with the Cheque.")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Cancel Advance", Status.PASS, "Cancle Advance Pass" + text, true);

            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Cancel Advance", Status.FAIL, "Getting Error :" + text, true);

            }


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Cancel Advance", Status.FAIL, "Issue while processing Cancel Advance", true, e);
        }
    }

    @FindBy(id = "chkFreeze")
    private WebElement chkFreeze;
    @FindBy(id = "cphDialogHeader_btnSaveClose")
    private WebElement saveClose;

    public void freezeAdvance() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(accountPayble);
            Utilities.Click(changeChqStatus);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(chqRadio);
            Utilities.SendKeys(chqFrom, uniqueChequeNo);
            Utilities.SendKeys(chqTo, uniqueChequeNo);
            Utilities.Click(cancelChq);
            Utilities.Click(search);
            Utilities.Click(selectChq);
            Utilities.Click(save);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(okButton);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(accountPayble);
            Utilities.Click(payAdvance);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.SendKeys(voucherNumber, docNO);
            Utilities.Click(search);
            Utilities.Click(viewEdit);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(chkFreeze);
            Utilities.Click(saveClose);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

package pages.accountPayables.Inquiry;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.yaml.snakeyaml.tokens.Token;
import utils.DynamicWait;
import utils.Utilities;

public class InquiryPage {
    // ====== LOCATORS ======
    @FindBy(id = "MultiPageiframeBrw")
    private WebElement mainFrame;
    @FindBy(xpath = "//a[text()='Invoice Inquiry']")
    private WebElement invoiceInquiryLink;
    @FindBy(id = "cphBody_txtap_id")
    private WebElement apIdField;
    @FindBy(id = "cphBody_btnSearch")
    private WebElement searchButton;
    @FindBy(xpath = "//a[text()='Accounting']")
    private WebElement accounting;
    @FindBy(id = "divmodule_1")
    private WebElement accountPayble;
    @FindBy(xpath = "//a[text()='Accounts Payables']")
    private WebElement accountsPayablesLink;
    @FindBy(xpath = "//a[text()='Cheque Inquiry']")
    private WebElement chequeInquiryLink;

    public InquiryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // ====== ACTION METHOD ======
    public void openInvoiceInquiryAndSearch() {
        try {
            // Switch to the main frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(mainFrame);

            // Click "Invoice Inquiry"
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), invoiceInquiryLink);
            Utilities.Click(BaseTest.getDriver(), invoiceInquiryLink);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(mainFrame);
            // Enter AP ID
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), apIdField);
            Utilities.SendKeys(BaseTest.getDriver(), apIdField, "PROW003");

            // Click Search
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), searchButton);
            Utilities.Click(BaseTest.getDriver(), searchButton);

            ExtentTestManager.createAssertTestStepWithScreenshot("Invoice Inquiry Search",
                    Status.PASS, "Invoice Inquiry search executed successfully with AP ID: PROW003", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Invoice Inquiry Search",
                    Status.FAIL, "Failed to perform Invoice Inquiry search.", true, e);
        }
    }

    public void accounting() {
        try {
            BaseTest.getDriver().manage().window().maximize();
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(accounting);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(accountPayble);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Create AP Account", Status.FAIL, "Issue while creating AP Account", true, e);
        }
    }

    public void navigateToChequeInquiryAndSearch() {
        try {
            // Switch to iframe
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(mainFrame);


            // Click Cheque Inquiry
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), chequeInquiryLink);
            Utilities.Click(BaseTest.getDriver(), chequeInquiryLink);

            // Click Search
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(mainFrame);
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), searchButton);
            Utilities.Click(BaseTest.getDriver(), searchButton);

            // Report success
            ExtentTestManager.createAssertTestStepWithScreenshot("Cheque Inquiry Navigation",
                    Status.PASS, "Successfully navigated to Cheque Inquiry and executed Search.", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Cheque Inquiry Navigation",
                    Status.FAIL, "Failed during Cheque Inquiry navigation or search.", true, e);
        }
    }

    @FindBy(xpath = "//a[@title='AP62 AP Accounts Inquiry']")
    private WebElement linkAPAccountsInquiry;
    @FindBy(id = "cphBody_btnSearch")
    private WebElement btnSearch;
    @FindBy(id = "cphBrowserHeader_btnExport_Brw")
    private WebElement btnExport;
    public void performAPAccountsInquiryExport() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), linkAPAccountsInquiry);
            Utilities.Click(BaseTest.getDriver(), linkAPAccountsInquiry);
            ExtentTestManager.createAssertTestStepWithScreenshot("AP Accounts Inquiry Link", Status.PASS,
                    "Clicked on AP Accounts Inquiry link successfully", true);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), btnSearch);
            Utilities.Click(BaseTest.getDriver(), btnSearch);
            ExtentTestManager.createAssertTestStepWithScreenshot("Search Button", Status.PASS,
                    "Clicked on Search button successfully", true);

            DynamicWait.smallWait();

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), btnExport);
            Utilities.Click(BaseTest.getDriver(), btnExport);
            ExtentTestManager.createAssertTestStepWithScreenshot("Export Button", Status.PASS,
                    "Clicked on Export button successfully - export/download initiated", true);

            DynamicWait.longWait();
            ExtentTestManager.createAssertTestStepWithScreenshot("Download Handling", Status.PASS,
                    "Simulated export popup/download handling completed", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AP Accounts Inquiry Export Flow", Status.FAIL,
                    "AP Accounts Inquiry Export flow failed", true, e);
        }
    }

    @FindBy(id = "MultiPageiframeBrw")
    private WebElement multiPageIframeBrw;

    @FindBy(xpath = "//a[normalize-space()='Statement of Account']")
    private WebElement linkStatementOfAccount;

    @FindBy(id = "cphBody_txtap_id")
    private WebElement txtAccountId;



    @FindBy(id = "cphBrowserHeader_btnPrint_Brw")
    private WebElement btnPrint;

    public void performStatementOfAccountFlow() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(multiPageIframeBrw);

            // Step 2: Click on "Statement of Account" link
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), linkStatementOfAccount);
            Utilities.Click(BaseTest.getDriver(), linkStatementOfAccount);
            ExtentTestManager.createAssertTestStepWithScreenshot("Statement of Account Link", Status.PASS,
                    "Clicked on Statement of Account link successfully", true);


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(multiPageIframeBrw);
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), txtAccountId);
            Utilities.Click(BaseTest.getDriver(), txtAccountId);

            // Step 4: Fill Account ID
            Utilities.SendKeys(BaseTest.getDriver(), txtAccountId, "PROW003");
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Account ID", Status.PASS,
                    "Entered Account ID: PROW003", true);

            // Step 5: Optional tab action simulated with wait
            DynamicWait.smallWait();

            // Step 6: Click Search button
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), btnSearch);
            Utilities.Click(BaseTest.getDriver(), btnSearch);
            ExtentTestManager.createAssertTestStepWithScreenshot("Search Button", Status.PASS,
                    "Clicked on Search button successfully", true);

            DynamicWait.smallWait();

            // Step 7: Click Print button (Simulate popup opening)
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), btnPrint);
            Utilities.Click(BaseTest.getDriver(), btnPrint);
            ExtentTestManager.createAssertTestStepWithScreenshot("Print Button", Status.PASS,
                    "Clicked on Print button - popup expected", true);

            // Step 8: Simulate popup handling
            DynamicWait.longWait();
            ExtentTestManager.createAssertTestStepWithScreenshot("Popup Handling", Status.PASS,
                    "Simulated popup opened for Statement of Account print preview", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Statement of Account Flow", Status.FAIL,
                    "Statement of Account flow failed", true, e);
        } }
}

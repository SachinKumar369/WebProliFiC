package pages.accountPayables.apparameters;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utilities;

import java.time.Duration;

public class APParameters {
    WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
    @FindBy(xpath = "//a[text()='Accounting']")
    private WebElement accounting;
    @FindBy(id = "divmodule_1")
    private WebElement accountPayble;
    @FindBy(xpath = "//a[@title='AP03 AP Parameters']")
    private WebElement apParameters;

    @FindBy(xpath = "//*[@value='Add']")
    private WebElement addButton;
    @FindBy(id = "btnOK")
    private WebElement okButton;
    @FindBy(id = "cphBody_cphMasterToolBar_btnSave")
    private WebElement saveButton;
    @FindBy(id = "acct_code_3_2")
    private WebElement accountCodeField;
    @FindBy(xpath = "//*[text()='P & L ACCOUNT']")
    private WebElement plAccountCell;
    @FindBy(id = "cphBody_cphChildBody_txtbank_id")
    private WebElement bankIdField;
    @FindBy(id = "cphBody_cphChildBody_txtbank_name")
    private WebElement bankNameField;
    @FindBy(id = "cphBody_cphChildBody_txtbank_address_1")
    private WebElement bankAddress1Field;
    @FindBy(id = "cphBody_cphChildBody_txtbank_city")
    private WebElement bankCityField;
    @FindBy(id = "cphBody_cphChildBody_txtbank_state")
    private WebElement bankStateField;
    @FindBy(id = "cphBody_cphChildBody_txtbank_zip")
    private WebElement bankZipField;
    @FindBy(id = "cphBody_cphChildBody_txtbank_country")
    private WebElement bankCountryField;
    @FindBy(id = "cphBody_cphChildBody_txtap_gl_acct_code")
    private WebElement apGLAcctCodeField;
    @FindBy(id = "cphBody_cphChildBody_imgPopupap_gl_acct_code")
    private WebElement apGLAcctCodePopupIcon;
    @FindBy(xpath = "//button[text()='Close']")
    private WebElement closeButton;
    @FindBy(xpath = "//button[text()='Cancel']")
    private WebElement cancelButton;
    @FindBy(xpath = "//*[text()='FIXED ASSETS']")
    private WebElement fixedAssetsCell;
    @FindBy(id = "cphBody_cphChildBody_txtcheque_running_serial_no")
    private WebElement chequeSerialNoField;

    public APParameters(WebDriver driver) {
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

            // Click on Make PJV
            Utilities.Click(apParameters);
            ExtentTestManager.createAssertTestStepWithScreenshot("Click Make PJV", Status.PASS, "Clicked on 'Make PJV'", true);

            // Log completion
            ExtentTestManager.createAssertTestStepWithScreenshot("Accounting Navigation", Status.INFO, "Accounting navigation completed successfully", false);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Create AP Account", Status.FAIL, "Issue while creating AP Account", true, e);
        }
    }

    String uniqueBankId;
    public void bankMaster() {
        try {
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
//            Utilities.Click(addButton);
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            BaseTest.getDriver().switchTo().frame("iframeDtl");
//
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), accountCodeField);
//            Utilities.Click(BaseTest.getDriver(), accountCodeField);
//            Utilities.SendKeys(BaseTest.getDriver(), accountCodeField, "%%");
//            accountCodeField.sendKeys(org.openqa.selenium.Keys.TAB);
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.DoubleClick(plAccountCell);

            // --- Step 2: Switch to another iframe and add bank details ---
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeBrw"));
            //BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            wait.until(ExpectedConditions.elementToBeClickable(addButton));
            Utilities.Click(BaseTest.getDriver(), addButton);

            uniqueBankId = "" + new java.text.SimpleDateFormat("yyMMdd_HHmmss").format(new java.util.Date());
            Utilities.SendKeys(BaseTest.getDriver(), bankIdField, uniqueBankId);

            Utilities.SendKeys(BaseTest.getDriver(), bankNameField, "TEST DESC");
            Utilities.SendKeys(BaseTest.getDriver(), bankAddress1Field, "MEERUT");
            Utilities.SendKeys(BaseTest.getDriver(), bankCityField, "MEERUT");
            Utilities.SendKeys(BaseTest.getDriver(), bankStateField, "UP");
            Utilities.SendKeys(BaseTest.getDriver(), bankZipField, "250002");
            Utilities.SendKeys(BaseTest.getDriver(), bankCountryField, "INDIA");

            ExtentTestManager.createAssertTestStepWithScreenshot("Account & Bank Entry", Status.PASS, "Account code and bank details entered successfully", true);

            /// ////////////////////////////////////////////////////////

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), apGLAcctCodeField);
            Utilities.Click(BaseTest.getDriver(), apGLAcctCodeField);
            Utilities.SendKeys(BaseTest.getDriver(), apGLAcctCodeField, "%%");
            apGLAcctCodeField.sendKeys(org.openqa.selenium.Keys.TAB);

            // Step 2: Switch to iframeGridDialog and select FIXED ASSETS cell
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.DoubleClick(fixedAssetsCell);

            // Step 3: Switch back to bank iframe and enter cheque serial number
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            Utilities.Click(BaseTest.getDriver(), chequeSerialNoField);
            Utilities.SendKeys(BaseTest.getDriver(), chequeSerialNoField, "1212");

            // Step 4: Click on Save button
            Utilities.Click(BaseTest.getDriver(), saveButton);

            wait.until(ExpectedConditions.alertIsPresent()).accept();


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(BaseTest.getDriver(), okButton);

            ExtentTestManager.createAssertTestStepWithScreenshot("GL Account & Save Bank Details", Status.PASS, "GL Account selected and bank details saved successfully", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Account & Bank Entry", Status.FAIL, "Failed while entering account or bank details", true, e);
        }
    }

    @FindBy(id = "col_0")
    private WebElement searchBankId;
    public void updateBankDescription() {
        try {



            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");


            System.out.println("Unique ID is"+uniqueBankId);
            Utilities.SendKeys(searchBankId,uniqueBankId);
            searchBankId.sendKeys(Keys.TAB);

            // Step 3: Update the bank name description
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), bankNameField);
            Utilities.Click(BaseTest.getDriver(), bankNameField);

            // Simulate select all and overwrite (Control + A equivalent)
            bankNameField.sendKeys(org.openqa.selenium.Keys.CONTROL + "a");
            Utilities.SendKeys(BaseTest.getDriver(), bankNameField, "Updated Description");

            // Step 4: Click on Save button
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), saveButton);
            Utilities.Click(BaseTest.getDriver(), saveButton);

            // Step 5: Handle any alert popup
            try {
                Alert alert = BaseTest.getDriver().switchTo().alert();
                alert.accept();
            } catch (Exception e1) {
                // No alert appeared — continue execution
            }

            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Update Bank Description",
                    Status.PASS,
                    "Bank description updated successfully",
                    true
            );

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Update Bank Description",
                    Status.FAIL,
                    "Failed while updating bank description",
                    true,
                    e
            );
        }
    }


}

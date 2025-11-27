package pages.accountPayables.settlement;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utilities;

import java.time.Duration;

public class SettlePage extends Utilities {
    WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
    @FindBy(xpath = "//a[text()='Accounting']")
    private WebElement accounting;
    @FindBy(id = "divmodule_1")
    private WebElement accountPayble;
    @FindBy(id = "MultiPageiframeBrw")
    private WebElement mainFrame;
    @FindBy(xpath = "//a[text()='Settle Documents']")
    private WebElement settleDocumentsLink;
    @FindBy(id = "cphBody_txtPayablesAccounts")
    private WebElement payablesAccountsField;
    @FindBy(xpath = "//iframe[@id='iframeGridDialog']")
    private WebElement gridDialogFrame;
    @FindBy(xpath = "//*[text()='3 W EVENTS']")
    private WebElement threeWEventsCell;
    @FindBy(id = "cphBody_btnSearch")
    private WebElement searchButton;
    @FindBy(id = "td_0_6")
    private WebElement firstRecordCell;
    @FindBy(xpath = "//*[contains(text(),'To settle with')]")
    private WebElement settleWithAdvancesLink;
    @FindBy(id = "MultiPageiframeDlg")
    private WebElement dialogFrame;
    @FindBy(id = "cphBody_btnSearch")
    private WebElement dialogSearchButton;

    @FindAll({
            @FindBy(id = "apply_9_0"),
            @FindBy(id = "apply_8_0")
    })
    private WebElement applyCheckbox1;

    @FindBy(id = "apply_8_0")
    private WebElement applyCheckbox;

    @FindBy(id = "cphDialogHeader_btnSave")
    private WebElement saveButton;
    @FindBy(id = "apply_amount_10_0")
    private WebElement applyAmount;
    @FindBy(xpath = "//a[text()='Settle PJV & Credit Notes']")
    private WebElement settlePjvCreditNotesLink;



    @FindBy(id = "apply_amount_11_0")
    private WebElement applyAmountField;

    public SettlePage(WebDriver driver) {
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


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Create AP Account", Status.FAIL, "Issue while creating AP Account", true, e);
        }
    }

    public void settleDocumentsFlow() {
        try {
            // Switch to main frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(mainFrame);

            // Click "Settle Documents"
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), settleDocumentsLink);
            Utilities.Click(BaseTest.getDriver(), settleDocumentsLink);

            // Switch to main frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(mainFrame);
            // Enter Payables Account
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), payablesAccountsField);
            Utilities.SendKeys(BaseTest.getDriver(), payablesAccountsField, "PROW003");
            payablesAccountsField.sendKeys(Keys.TAB);


            // Return to main frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(mainFrame);


            // Search
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), searchButton);
            Utilities.Click(BaseTest.getDriver(), searchButton);

            // Select first record
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), firstRecordCell);
            Utilities.Click(BaseTest.getDriver(), firstRecordCell);

            // Click "To settle with Advances &"
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), settleWithAdvancesLink);
            Utilities.Click(BaseTest.getDriver(), settleWithAdvancesLink);

            // Switch to dialog frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(dialogFrame);

            // Click Search
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), dialogSearchButton);
            Utilities.Click(BaseTest.getDriver(), dialogSearchButton);

            // Check apply checkbox
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), applyCheckbox);
            if (!applyCheckbox.isSelected()) {
                Utilities.Click(BaseTest.getDriver(), applyCheckbox);
            }


            applyAmount.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            applyAmount.sendKeys(Keys.DELETE);
            SendKeys(applyAmount, "1");
            // Save
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), saveButton);
            Utilities.Click(BaseTest.getDriver(), saveButton);

            ExtentTestManager.createAssertTestStepWithScreenshot("Settle Documents Flow", Status.PASS, "Settle Documents flow executed successfully.", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Settle Documents Flow", Status.FAIL, "Failed to execute Settle Documents flow.", true, e);
        }
    }



    // ====== ACTION METHOD ======

    public void settlePjvAndCreditNotesFlow() {
        try {
            // Switch to dialog frame and click "Settle PJV & Credit Notes"
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), settlePjvCreditNotesLink);
            Utilities.Click(BaseTest.getDriver(), settlePjvCreditNotesLink);

            // Switch to main frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(mainFrame);

            // Enter payables account
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), payablesAccountsField);
            Utilities.SendKeys(BaseTest.getDriver(), payablesAccountsField, "PROW003");
            payablesAccountsField.sendKeys(Keys.TAB);

            // Click Search
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), searchButton);
            Utilities.Click(BaseTest.getDriver(), searchButton);

            // Click "To settle with PJV & Credit"
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), settleWithAdvancesLink);
            Utilities.Click(BaseTest.getDriver(), settleWithAdvancesLink);

            // Switch to dialog frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(dialogFrame);

            // Search in dialog
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), dialogSearchButton);
            Utilities.Click(BaseTest.getDriver(), dialogSearchButton);

            // Check the checkbox
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), applyCheckbox1);
            if (!applyCheckbox.isSelected()) {
                Utilities.Click(BaseTest.getDriver(), applyCheckbox1);
            }

            // Enter apply amount = 1
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), applyAmountField);
            Utilities.Click(BaseTest.getDriver(), applyAmountField);
            applyAmountField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            Utilities.SendKeys(BaseTest.getDriver(), applyAmountField, "1");

            // Click Save
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), saveButton);
            Utilities.Click(BaseTest.getDriver(), saveButton);

            ExtentTestManager.createAssertTestStepWithScreenshot("Settle PJV & Credit Notes Flow",
                    Status.PASS, "Successfully completed Settle PJV & Credit Notes process.", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Settle PJV & Credit Notes Flow",
                    Status.FAIL, "Failed during Settle PJV & Credit Notes process.", true, e);
        }
    }


}

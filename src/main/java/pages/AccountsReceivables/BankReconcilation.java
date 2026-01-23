package pages.AccountsReceivables;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import com.prologic.util.data.DateUtility;
import extentreports.ExtentTestManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utilities;

import java.time.Duration;

public class BankReconcilation extends Utilities {
    public BankReconcilation(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void multipageiframebrw() {
        BaseTest.getDriver().switchTo().defaultContent();
        BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
    }

    public void iframeGridDialog(){
        BaseTest.getDriver().switchTo().defaultContent();
        BaseTest.getDriver().switchTo().frame("iframeGridDialog");
    }

    WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));

    @FindBy(id = "LinkProduct0")
    private WebElement accounting;
    @FindBy(id = "divmodule_3")
    private WebElement bankReconcile;
    @FindBy(xpath = "//a[@title='BN04 Bank Transaction Type']")
    private WebElement bankTransaction;
    @FindBy(xpath = "//input[@value='Add']")
    private WebElement add;
    @FindBy(id = "cphBody_txtType")
    private WebElement type;
    @FindBy(id = "cphBody_txtDescription")
    private WebElement description;
    @FindBy(id = "cphBody_ddlIndicator")
    private WebElement indicator;
    @FindBy(id = "cphBody_txtAccountId")
    private WebElement glAc;
    @FindBy(id = "td_0_3")
    private WebElement gl;
    @FindBy(xpath = "//input[@value='Save']")
    private WebElement saveBtn;


    public void transectionType(){
        try {

                BaseTest.getDriver().manage().window().maximize();
                multipageiframebrw();
                Click(accounting);
                multipageiframebrw();
                wait.until(ExpectedConditions.elementToBeClickable(bankReconcile));
                Click(bankReconcile);
                Click(bankTransaction);
                multipageiframebrw();
                Click(add);
                SendKeys(type,"BV");
                SendKeys(description,"Description");
                selectBy("value",indicator,"CA");
                SendKeys(glAc,"test");
                glAc.sendKeys(Keys.TAB);
                iframeGridDialog();
                DoubleClick(gl);
                multipageiframebrw();
                Click(saveBtn);

            }
         catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Bank Transection", Status.FAIL,"Issue in Bank Transection",true,e);
        }
    }

    public void deleteTransection(){
        try {
            BaseTest.getDriver().manage().window().maximize();
            multipageiframebrw();
            Click(accounting);
            multipageiframebrw();
            wait.until(ExpectedConditions.elementToBeClickable(bankReconcile));
            Click(bankReconcile);
            Click(bankTransaction);
            multipageiframebrw();

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Delete Bank Transection", Status.FAIL,"Issue in Deleting the  Bank Transection",true,e);

        }
    }

    @FindBy(xpath = "//a[@title=\"BN03 Reconcile Bank Statement\"]")
    private WebElement reconcileBank;
    @FindBy(id = "cphBody_imgBankId")
    private WebElement bankIdimg;
    @FindBy(id = "td_5_0")
    private WebElement bankID;
    @FindBy(id = "cphFooter_tdDatetime")
    private WebElement date;
    @FindBy(id = "cphBody_txtReconciledUpTo")
    private WebElement reconcileDate;
    @FindBy(id = "cphBody_btnSearch")
    private WebElement searchButton;


    public void reconcileBankStatement(){
        try {
            BaseTest.getDriver().manage().window().maximize();
            multipageiframebrw();
            Click(accounting);
            multipageiframebrw();
            wait.until(ExpectedConditions.elementToBeClickable(bankReconcile));
            Click(bankReconcile);
            Click(reconcileBank);
            multipageiframebrw();
            Click(bankIdimg);
            iframeGridDialog();
            DoubleClick(bankID);
            multipageiframebrw();
            String businessDate = date.getText();
            System.out.println("Bussiness Date is :" +businessDate);
            String ddmmyyDate = DateUtility.convertDateToDDMMYYYY(businessDate);
            System.out.println("ddmmyy Date is " +ddmmyyDate);
            SendKeys(reconcileDate,ddmmyyDate);
            Click(searchButton);



        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Add Transaction Details", Status.FAIL,"Issue in Deleting the  Bank Transection",true,e);
        }
    }

    @FindBy(xpath = "//a[contains(@module_name,'General Cashier')]")
    private WebElement generalCash;
    @FindBy(xpath = "//a[@title='GC02 General Cashier Main Screen']")
    private WebElement generalCashierMainScreen;
    @FindBy(id = "cphBody_btnAddRow_Top")
    private WebElement addRow;


    public void generalCashier(){
        try {
            BaseTest.getDriver().manage().window().maximize();
            multipageiframebrw();
            Click(accounting);
            multipageiframebrw();
            Click(generalCash);
            Click(generalCashierMainScreen);
            multipageiframebrw();
            Click(addRow);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("General Cashier",Status.FAIL,"Error while creating General Cashier" , true,e);
        }
    }


}

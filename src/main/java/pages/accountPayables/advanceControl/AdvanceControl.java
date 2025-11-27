package pages.accountPayables.advanceControl;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.databind.ser.Serializers;
import extentreports.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DynamicWait;
import utils.Utilities;

import java.time.Duration;

public class AdvanceControl {
    public AdvanceControl(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "MultiPageiframeBrw")
    private WebElement multiPageFrame;

    @FindBy(id = "cphDialogHeader_btnSave")
    private WebElement saveButton;

    @FindBy(xpath = "//a[normalize-space()='Accounting']")
    private WebElement accountingLink;

    @FindBy(id = "menutxtSearch")
    private WebElement menuSearchBox;

    @FindBy(id = "divMnu_0")
    private WebElement advanceControlLink;

    @FindBy(id = "txtSearchString")
    private WebElement searchStringField;

    @FindBy(id = "cphBody_btnGo")
    private WebElement goButton;

    @FindBy(id = "control_value_char_3_0")
    private WebElement controlValueField;

    @FindBy(id = "btnOK")
    private WebElement okButton;

    @FindBy(id = "cphDialogHeader_btnClose_Dlg")
    private WebElement closeButton;

    @FindBy(id = "cphHeader_lnkHome")
    private WebElement homeLink;
    @FindBy(id = "LinkProduct0")
    private WebElement accounting;
    @FindBy(id = "lblErrorDesc")
    private WebElement confDlg;
    @FindBy(id = "divmodule_1")
    private WebElement accountPayables;
    @FindBy(xpath = "//a[@title='AP10 Process Payment']")
    private WebElement processPayment;
    // ===== Actions =====
    public void updateAdvanceControlValue(String searchTerm, String newValue) {
        try {
            // Switch to frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(multiPageFrame);

            Utilities.Click(accounting);


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(multiPageFrame);
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), menuSearchBox);
            Utilities.SendKeys(BaseTest.getDriver(), menuSearchBox, "advance");

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), advanceControlLink);
            Utilities.Click(BaseTest.getDriver(), advanceControlLink);


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(multiPageFrame);
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), searchStringField);
            Utilities.SendKeys(BaseTest.getDriver(), searchStringField, searchTerm);
            DynamicWait.smallWait(); // represents Tab + load delay

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), goButton);
            Utilities.Click(BaseTest.getDriver(), goButton);

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), controlValueField);
            Utilities.Click(BaseTest.getDriver(), controlValueField);
            Utilities.SendKeys(BaseTest.getDriver(), controlValueField, newValue);

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), saveButton);
            Utilities.Click(BaseTest.getDriver(), saveButton);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            if (confDlg.isDisplayed()&&confDlg.getText().contains("[238] - Details Created/Updated")){
                ExtentTestManager.createAssertTestStepWithScreenshot("Advance Control Update", Status.PASS,
                        "Advance control value updated successfully.", true);
            }
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), okButton);
            Utilities.Click(BaseTest.getDriver(), okButton);

            //BaseTest.getDriver().


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(multiPageFrame);

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), closeButton);
            Utilities.Click(BaseTest.getDriver(), closeButton);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame(multiPageFrame);
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), homeLink);
            Utilities.Click(BaseTest.getDriver(), homeLink);



        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Advance Control Update", Status.FAIL,
                    "Failed to update advance control value.", true, e);
        }
    }

    WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));



}

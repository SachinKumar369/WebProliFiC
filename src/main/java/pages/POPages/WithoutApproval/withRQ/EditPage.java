package pages.POPages.WithoutApproval.withRQ;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DynamicWait;
import utils.Utilities;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

public class EditPage {

    public EditPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "btnCancel")
    private WebElement Cancel;
    @FindBy(id = "btnClose")
    private WebElement Close;
    @FindBy(id = "rdoFinal")
    private WebElement rdFinal;
    @FindBy(id = "btnCancel")
    private WebElement CancelBtn;
    @FindBy(id = "cphDialogHeader_btnAddRecords")
    private WebElement RevokeApproval;
    @FindBy(xpath = "//input[@name='btnOK']")
    private WebElement OKBTN;
    @FindBy(xpath = "//*[starts-with(@id, 'item_rate_9_')]")
    private List<WebElement> itemRate;
    @FindBy(id = "cphDialogHeader_btnSaveClose")
    private WebElement SaveBtn;
    @FindBy(id = "cphDialogHeader_btnPrint")
    private WebElement Print;
    @FindBy(id = "img_item_tax_class_16_2")
    private WebElement ItemTaxClass;
    @FindBy(xpath = "//*[@id=\"td_2_10\"]")
    private WebElement SelectTax;
    @FindBy(id = "cphBody_btnAddRow")
    private WebElement AddRow;
    @FindBy(id = "cphBody_btnDeleteRow")
    private WebElement DeleteRow;
    @FindBy(id = "item_desc_2_0")
    private WebElement SelectItem;
    @FindBy(id = "chkSendEmail")
    private WebElement Sendmail;


    public void EditPO() {
        try {

            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), RevokeApproval);

            Alert alert = BaseTest.getDriver().switchTo().alert();
            wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
            try {
                wait.until(ExpectedConditions.alertIsPresent());
                alert.accept();
            } catch (Exception e) {

            }


            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            wait.until(ExpectedConditions.elementToBeClickable(OKBTN));
            Utilities.Click(BaseTest.getDriver(), OKBTN);

            Actions actions = new Actions(BaseTest.getDriver());
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            for (WebElement rate : itemRate) {
                //Utilities.SendKeys(BaseTest.getDriver(),rate,"10");
                Utilities.Click(BaseTest.getDriver(), rate);
                actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("99").perform();

            }

            // Item Class TAX
            Utilities.Click(BaseTest.getDriver(),ItemTaxClass);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            Utilities.DoubleClick(SelectTax);






            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
            Utilities.Click(BaseTest.getDriver(), SaveBtn);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            wait.until(ExpectedConditions.elementToBeClickable(OKBTN));
            Utilities.Click(BaseTest.getDriver(), OKBTN);


            ///  testing

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeDlg"));
            Utilities.Click(BaseTest.getDriver(), Print);


            try {
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
                try {
                    wait.until(ExpectedConditions.elementToBeClickable(rdFinal));
                    Utilities.ClickCheckBox( rdFinal);
                    Utilities.ClickCheckBox(Sendmail);
                } catch (Exception e) {

                }

                WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"btnOk\"]")));

                try {
                    OKBtn.click();
                    System.out.println("OK Button clicked successfully!");
                } catch (Exception e) {
                    System.out.println("Normal click failed, trying JavaScript click.");
                    ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].click();", OKBtn);
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            DynamicWait.longWait();

            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            DynamicWait.longWait();
            robot.keyRelease(KeyEvent.VK_ENTER);

            String expected = "Approved";

//            try {
//                BaseTest.getDriver().switchTo().defaultContent();
//                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//
//                Utilities.Click(BaseTest.getDriver(),CancelBtn);
//            } catch (Exception e) {
//
//
//            }


           // RPPOApprovalPage.validate();

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Edit", Status.FAIL, "Exception found in Method Edit PO ", true, e);
        }
    }

    public void EditPO1() {
        try {

            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), RevokeApproval);

            Alert alert = BaseTest.getDriver().switchTo().alert();
            wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
            try {
                wait.until(ExpectedConditions.alertIsPresent());
                alert.accept();
            } catch (Exception e) {

            }


            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            wait.until(ExpectedConditions.elementToBeClickable(OKBTN));
            Utilities.Click(BaseTest.getDriver(), OKBTN);

            Actions actions = new Actions(BaseTest.getDriver());
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            for (WebElement rate : itemRate) {
                //Utilities.SendKeys(BaseTest.getDriver(),rate,"10");
                Utilities.Click(BaseTest.getDriver(), rate);
                actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("99").perform();

            }




            // Delete a ROW

            Utilities.Click(BaseTest.getDriver(),SelectItem);
            Utilities.Click(BaseTest.getDriver(),DeleteRow);

            alert.accept();

            //Utilities.Click(BaseTest.getDriver(),AddRow);






            // Item Class TAX
            Utilities.Click(BaseTest.getDriver(),ItemTaxClass);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            Utilities.DoubleClick(SelectTax);









            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
            Utilities.Click(BaseTest.getDriver(), SaveBtn);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            wait.until(ExpectedConditions.elementToBeClickable(OKBTN));
            Utilities.Click(BaseTest.getDriver(), OKBTN);


            ///  testing


            try {
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");

                Utilities.Click(BaseTest.getDriver(),CancelBtn);
            } catch (Exception e) {


            }


            // RPPOApprovalPage.validate();

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Edit", Status.FAIL, "Exception found in Method Edit PO ", true, e);
        }
    }

}

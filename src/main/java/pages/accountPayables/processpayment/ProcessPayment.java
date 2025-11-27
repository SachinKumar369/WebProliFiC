package pages.accountPayables.processpayment;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utilities;

import java.time.Duration;

public class ProcessPayment extends Utilities {

    public ProcessPayment(WebDriver driver){
        PageFactory.initElements(driver,this);
    }


    @FindBy(xpath = "//a[text()='Accounting']")
    private WebElement accounting;
    @FindBy(id = "divmodule_1")
    private WebElement accountPayble;
    @FindBy(xpath = "//a[@title='AP10 Process Payment']")
    private WebElement processPayment;
    @FindBy(id = "cphBody_btnSearch")
    private WebElement search;

    WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));

    public void accountPayable() {
        try {

            BaseTest.getDriver().manage().window().maximize();
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeBrw"));
            //BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Click(accounting);
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeBrw"));

//            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            Click(accountPayble);
            Click(processPayment);



        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Create AP Account", Status.FAIL, "Issue while creating AP Account", true, e);
        }
    }

    @FindBy(id = "Header_supplier_bill_dt_30")
    private WebElement dateArrange;
    @FindBy(id = "process_9_0")
    private WebElement process;

    public void blankSearch(){
        try {
            Click(search);
            Click(dateArrange);
            Click(dateArrange);
            Click(process);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

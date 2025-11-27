package pages.POPages.WithRQ;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utilities;

public class POGeneration {

    /**
     * Constructor
     */

    public POGeneration(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     *  Web Elements
     */

    @FindBy(xpath = "//*[text()='Purchase Order Generation']")
    private WebElement POGeneration;
    @FindBy(xpath = "//a[@id='LinkProduct2']")
    private WebElement Inventory;
    @FindBy(id = "divmodule_2")
    private WebElement InventoryModule;
    @FindBy(xpath = "//input[@id=\"cphBody_chkWithoutQuotations\"]")
    private WebElement WithoutQuotations;
    @FindBy(id = "cphBody_chkWithoutQuotationsReceipt")
    private WebElement WithoutQuoatReceipt;
    @FindBy(id = "cphBody_chkWithPendingContractPurchaseOrders")
    private WebElement WithPendingContract;
    @FindBy(id = "cphBody_chkWithQuotations")
    private WebElement WithQuotations;
    @FindBy(id = "cphBody_btnSearchDefaultVendor")
    private WebElement SearchWithDefaultVendor;
    @FindBy(id = "quotes_link_6_2")
    private WebElement SelectQuote;
    @FindBy(id = "APPROVED_9_0")
    private WebElement SelectCheckBox;
    @FindBy(id = "btnSave")
    private WebElement SaveBtn;
    @FindBy(id = "cphDialogHeader_btnSave")
    private WebElement GeneratePO;
    @FindBy(id = "btnClose")
    private WebElement Close;









    public void CreatePO(){

        try {

            // Switch to the main content and then to the required frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Wait for Inventory element and click it
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Inventory);
            Utilities.Click(BaseTest.getDriver(), Inventory);

            //Inventory Module Select
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
            Utilities.Click(BaseTest.getDriver(), InventoryModule);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Click on PO Generation
            Utilities.Click(BaseTest.getDriver(),POGeneration);


            // Select the Unchecked CheckBox

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            if (!WithoutQuotations.isSelected()) {
                WithoutQuotations.click();
            }
            if (!WithoutQuoatReceipt.isSelected()) {
                WithoutQuoatReceipt.click();
            }
            if (!WithPendingContract.isSelected()) {
                WithPendingContract.click();
            }
            if (!WithQuotations.isSelected()) {
                WithQuotations.click();
            }


            Utilities.Click(BaseTest.getDriver(),SearchWithDefaultVendor);

            Utilities.Click(BaseTest.getDriver(),SelectQuote);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            BaseTest.getDriver().switchTo().frame("iframeDtl");

            if (SelectCheckBox.isDisplayed()) {
                    Utilities.Click(BaseTest.getDriver(), SelectCheckBox);
            } else {
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                Utilities.Click(BaseTest.getDriver(),Close);
            }

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Utilities.Click(BaseTest.getDriver(),SaveBtn);



            // Click Generate PO
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(BaseTest.getDriver(),GeneratePO);





            } catch (Exception e)        {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);

        }
    }
}

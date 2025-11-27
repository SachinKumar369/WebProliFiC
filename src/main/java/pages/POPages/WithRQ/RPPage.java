package pages.POPages.WithRQ;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import datahandlers.ExcelHandler;
import extentreports.ExtentTestManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DynamicWait;
import utils.Utilities;

import java.io.File;
import java.time.Duration;
import java.util.List;

/**
 * Author: SACHIN KUMAR (replace sachin with your name or a fixed string)
 * Date: 23-01-2025
 */
public class RPPage {

    /**
     * Constructor
     */

    public RPPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static String PONumber;


    /**
     * Web Elements
     */

    @FindBy(xpath = "//a[@id='LinkProduct2']")
    private WebElement Inventory;
    @FindBy(id = "divmodule_2")
    private WebElement InventoryModule;
    @FindBy(id = "cphBody_lblWithPurchaseRequisition")
    private WebElement WithPurchaseReq;
    //    @FindBy(xpath = "//*[@id=\"btnOk\"]")
//    private WebElement OKBtn;
    @FindBy(id = "btnOk")
    private WebElement OKBtn;
    @FindBy(xpath = "//a[text()=\"Purchase Orders\"][1]")
    private WebElement PurchaseOrder;
    @FindBy(id = "cphBody_imgType")
    private WebElement SelectType;
    @FindBy(id = "col_0")
    private WebElement POType;
    @FindBy(id = "cphBody_txtDateTo")
    private WebElement ToDate;
    @FindBy(id = "cphBody_txtVendor")
    private WebElement Search_vendor;
    @FindBy(id = "cphBody_btnEnterItemDetails")
    private WebElement EnterItemDetails;
    @FindBy(id = "txtNumber")
    private WebElement RequisitionNumber;

    @FindBy(id = "btnSearch")
    private WebElement SearchBtn;
    @FindBy(id = "cphBody_txtType")
    private WebElement EnterPOType;
    @FindBy(id = "divMsg")
    private WebElement ConfirmationDialog;


    @FindBy(css = "input[id^='ischeck_13_']")
    private List<WebElement> checkBoxes;

    public WebElement getItemRate(int itemId) {
        String xpath3 = "//input[@id='item_rate_9_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }


    @FindBy(xpath = "//input[starts-with(@id, 'item_rate_9_')]")
    private List<WebElement> itemRates;
    @FindBy(id = "cphDialogHeader_btnSaveClose")
    private WebElement SaveBtn;
    @FindBy(id = "cphDialogHeader_btnPrint")
    private WebElement Print;


    public static void setPONumber(String poNumber) {
        PONumber = poNumber;
    }


    public WebElement getItemSearchElement(int itemId) {
        String xpath = "//input[@id='item_id_1_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath));
    }


    public WebElement getrequestQuantityelement(int itemId) {
        String xpath3 = "//input[@id='qty_ordered_5_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }


    /**
     * Methods
     */

    // method to create PO with Requisition
    public void CPPOCreation() {

        try {

            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "POData.xlsx";
            // ExcelHandler.loadExcelFile(Updload_Path,0);

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

            //Click on Purchase Order
            Utilities.Click(BaseTest.getDriver(), PurchaseOrder);

            //Click on Add Button
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw"); // MultiPageiframeBrw
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), WithPurchaseReq);
            Utilities.Click(BaseTest.getDriver(), WithPurchaseReq);

            //Enter RQ Number
            BaseTest.getDriver().switchTo().defaultContent();

            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));


            // Read Data From the Excel File

            ExcelHandler.loadExcelFile(Updload_Path, 0);
            List<String> Resources1 = ExcelHandler.getAllColumnData("RQ Number");

            //for(int i=0;i<Resources1.size();i++) {
            String resource1 = Resources1.get(Resources1.size() - 1);
            Utilities.SendKeys(BaseTest.getDriver(), RequisitionNumber, resource1);

            Utilities.Click(BaseTest.getDriver(), SearchBtn);


            // Get the total number of checkboxes
            int totalCheckboxes = checkBoxes.size();
            System.out.println("Total checkboxes found: " + totalCheckboxes);


            for (WebElement checkbox : checkBoxes) {
                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
            }


            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));  //iframeGridDialog

            try {
                Utilities.JavaScriptClick(BaseTest.getDriver(), OKBtn);
            } catch (Exception e) {
            }


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);

        }
    }

    public void SelectPO() {
        try {
            //Select PO Type
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            Utilities.SendKeys(BaseTest.getDriver(), EnterPOType, "RP");
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));


            //Select To Date
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(BaseTest.getDriver(), ToDate, "12/09/2025");
            String Value = "CCL001";
            //Select Vendor
            Utilities.SendKeys(BaseTest.getDriver(), Search_vendor, "CCL001");

            //Click Enter Items Details
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);

            // Enter Rate
            for (int i = 0; i < itemRates.size(); i++) {
                Actions actions = new Actions(BaseTest.getDriver());
                //WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getItemRate(i));
                getItemRate(i).click();

                String value = getItemRate(i).getAttribute("value").replace(",", ""); // Remove commas
                float ItemRate = Float.parseFloat(value);

                // float ItemRate = Float.parseFloat((getItemRate(i).getAttribute("value")));
                if (ItemRate > 0) {
                    // actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("100").perform();
                } else {
                    actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("125").perform();

                }
            }

            Utilities.Click(BaseTest.getDriver(), SaveBtn);


            try {
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

                WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));

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


            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeDlg"));
            Utilities.Click(BaseTest.getDriver(), Print);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            // Utilities.Click(BaseTest.getDriver(),EntirePO);
            Utilities.Click(BaseTest.getDriver(), OKBtn);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);
        }
    }


}




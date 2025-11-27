package pages.POPages.WithoutRQ;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utils.DynamicWait;
import utils.Utilities;

import java.time.Duration;
import java.util.List;
import java.util.Random;

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
    @FindBy(id = "cphBrowserHeader_btnAddNew")
    private WebElement AddButton;
    @FindBy(id = "btnOK")
    private WebElement FirstColumn;
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
    @FindBy(id = "btnCancel")
    private WebElement Cancel;
    @FindBy(id = "btnClose")
    private WebElement Close;
    @FindBy(id = "cphBody_txtVendor")
    private WebElement Vendor;
    @FindBy(id = "cphBody_imgVendor")
    private WebElement VendorImg;
    @FindBy(xpath = "//td[@class=\"ControlItemStyle\"][1]")
    private List<WebElement> VendorList;
    @FindBy(xpath = "//input[@id='col_1']")
    private WebElement Filter;


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

    @FindBy(id = "lblErrorDesc")
    private WebElement confDlg;

    @FindBy(id = "btnOK")
            private WebElement ok;
    @FindBy(id = "cphBody_txtType")
            private WebElement poType;
    @FindBy(id = "cphBody_txtDateFrom")
            private WebElement fromDate;

    /**
     * Methods
     */

    WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
    // method to create PO without Requisition
    public void POCreation() {

        try {
            // Switch to the main content and then to the required frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Wait for Inventory element and click it
            DynamicWait.mediumWait();
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
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), AddButton);
            Utilities.Click(BaseTest.getDriver(), AddButton);

            //Select PO Type
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), SelectType);

            //Select PO Type form the popup
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.SendKeys(BaseTest.getDriver(), POType, "RP");
            Utilities.Click(BaseTest.getDriver(), FirstColumn);

            //Select To Date
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(BaseTest.getDriver(), ToDate, "12/09/2025");
            String Value = "CCL001";
            //Select Vendor
            //Utilities.SendKeys(BaseTest.getDriver(), Search_vendor, "CCL001");

            Utilities.SendKeys(BaseTest.getDriver(), Vendor, "%%");
            //Utilities.Click(BaseTest.getDriver(), VendorImg);


            Actions actions = new Actions(BaseTest.getDriver());
            actions.sendKeys(Keys.TAB).perform();

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));


            Random random = new Random();
            System.out.println(VendorList);
            int randomIndex = random.nextInt(VendorList.size());
            // Click on the random department
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
            String vendorlist = VendorList.get(randomIndex).getText();
            Utilities.SendKeys(BaseTest.getDriver(), Filter, vendorlist);
            DynamicWait.longWait();

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.DoubleClick(Cancel);

            try {
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                Utilities.Click(BaseTest.getDriver(), Close);
            } catch (Exception e) {
                System.out.println("No Close Button");
            }


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(BaseTest.getDriver(), Search_vendor, vendorlist);



            //Click Enter Items Details
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);

        }
    }
    public void CPCreation() {

        try {
            // Switch to the main content and then to the required frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Wait for Inventory element and click it
            DynamicWait.mediumWait();
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
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), AddButton);
            Utilities.Click(BaseTest.getDriver(), AddButton);

            //Select PO Type
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), SelectType);

            //Select PO Type form the popup
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.SendKeys(BaseTest.getDriver(), POType, "CP");
            Utilities.Click(BaseTest.getDriver(), FirstColumn);

            //Select To Date
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(BaseTest.getDriver(), ToDate, "12/09/2025");
            String Value = "CCL001";
            //Select Vendor
            //Utilities.SendKeys(BaseTest.getDriver(), Search_vendor, "CCL001");

            Utilities.SendKeys(BaseTest.getDriver(), Vendor, "%%");
            //Utilities.Click(BaseTest.getDriver(), VendorImg);

            Actions actions = new Actions(BaseTest.getDriver());
            actions.sendKeys(Keys.TAB).perform();

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));


            Random random = new Random();
            System.out.println(VendorList);
            int randomIndex = random.nextInt(VendorList.size());
            // Click on the random department
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
            String vendorlist = VendorList.get(randomIndex).getText();
            Utilities.SendKeys(BaseTest.getDriver(), Filter, vendorlist);
            DynamicWait.longWait();

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.DoubleClick(Cancel);

            try {
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                Utilities.Click(BaseTest.getDriver(), Close);
            } catch (Exception e) {
                System.out.println("No Close Button");
            }


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(BaseTest.getDriver(), Search_vendor, vendorlist);



            //Click Enter Items Details
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);

        }
    }

    public void poValidation() {

        try {
            // Switch to the main content and then to the required frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Wait for Inventory element and click it
            DynamicWait.mediumWait();
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
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), AddButton);
            Utilities.Click(BaseTest.getDriver(), AddButton);

            //Click on Enter Item Details
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals("[1021] -Please Enter Vendor Id.",confDlg.getText());

            Utilities.Click(ok);
            Actions actions = new Actions(BaseTest.getDriver());



            // Enter Vendor ID
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(BaseTest.getDriver(), Vendor, "%%");
            actions.sendKeys(Keys.TAB).perform();


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            softAssert.assertEquals("[1067] - Please Enter Po Type.",confDlg.getText());
            Utilities.Click(ok);




            //Select PO Type
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(poType,"RP");
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            softAssert.assertEquals("[1021] -Please Enter Vendor Id.",confDlg.getText());
            Utilities.Click(ok);

            // Enter Vendor ID
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(BaseTest.getDriver(), Vendor, "%%");
            actions.sendKeys(Keys.TAB).perform();
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            softAssert.assertEquals("[1178] - Please enter Date To.",confDlg.getText());
            Utilities.Click(ok);








            //Select To Date
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(fromDate);
            actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
            actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
            Utilities.Click(ToDate);
            actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();

            softAssert.assertAll();
            Utilities.SendKeys(BaseTest.getDriver(), Vendor, "%%");
            actions.sendKeys(Keys.TAB).perform();
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            Random random = new Random();
            int randomIndex = random.nextInt(VendorList.size());
            String vendorlist = VendorList.get(randomIndex).getText();
            WebElement vendor = VendorList.get(randomIndex);
            Utilities.DoubleClick(vendor);




            //Select PO Type
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), SelectType);

            //Select PO Type form the popup
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.SendKeys(BaseTest.getDriver(), POType, "RP");
            Utilities.Click(BaseTest.getDriver(), FirstColumn);



            //Select To Date
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(BaseTest.getDriver(), ToDate, "12/09/2025");


            Utilities.SendKeys(BaseTest.getDriver(), Vendor, "%%");


            //Actions actions = new Actions(BaseTest.getDriver());
            actions.sendKeys(Keys.TAB).perform();

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));


           // Random random = new Random();
            System.out.println(VendorList);
            //int randomIndex = random.nextInt(VendorList.size());
            // Click on the random department
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
            //String vendorlist = VendorList.get(randomIndex).getText();
            Utilities.SendKeys(BaseTest.getDriver(), Filter, vendorlist);
            DynamicWait.longWait();

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.DoubleClick(Cancel);

            try {
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                Utilities.Click(BaseTest.getDriver(), Close);
            } catch (Exception e) {
                System.out.println("No Close Button");
            }


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(BaseTest.getDriver(), Search_vendor, vendorlist);



            //Click Enter Items Details
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);

        }
    }


}




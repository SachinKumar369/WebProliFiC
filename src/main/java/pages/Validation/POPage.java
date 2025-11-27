package pages.Validation;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SRV.SRVPage;
import utils.DynamicWait;
import utils.Utilities;

import java.io.File;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Author: SACHIN KUMAR (replace sachin with your name or a fixed string)
 * Date: 23-01-2025
 */
public class POPage {

    /**
     * Constructor
     */

    public POPage(WebDriver driver) {
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
    @FindBy(id = "cphBody_btnSearch")  //cphBody_btnSearch
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


















    @FindBy(id = "cphBody_btnAddRow")
    private WebElement AddRow;
    @FindBy(xpath = "//input[@id='col_0']")
    private WebElement ItemFilter;
    @FindBy(xpath = "//td[@class=\"ControlItemStyle\"][1]")
    private List<WebElement> Item;
    @FindBy(id = "cphDialogHeader_btnSaveClose")
    private WebElement SaveBtn;
    @FindBy(id = "lblErrorDesc")
    private WebElement PODialougue;
    @FindBy(id = "lblErrorDesc")
    private WebElement ProductConfirmation;
    @FindBy(xpath = "//input[starts-with(@id,'item_id_1_')]")
    private List<WebElement> Items;
    @FindBy(xpath = "//input[starts-with(@id,'qty_ordered_5_')]")
    private List<WebElement> Quantity;
    @FindBy(xpath = "//input[starts-with(@id,'item_desc_2_')]")
    private List<WebElement> ItemName;

    @FindBy(xpath = "//input[starts-with(@id,'item_rate_9_')]")
    private List<WebElement> Rate;
    @FindBy(id = "cphBody_ddlApproved")
    private WebElement Rejected;
    @FindBy(xpath = "//*[starts-with(@id, 'free_of_cost_27_')]")
    List<WebElement> FreeofCost;
    @FindBy(id = "cphBody_lblApproveRemarks")
    private WebElement Reason;
    @FindBy(id = "TextAreaValue_remarksApproval")
    private WebElement Remark;
    @FindBy(xpath = "//a[@href='#' and @onclick='HideDiv_remarksApproval();']")
    private WebElement CloseImg;
    @FindBy(xpath = "//*[starts-with(@id, 'freeze_29_')]")
    List<WebElement> Freeze;




    @FindBy(xpath = "//input[starts-with(@id,'tot_amount_11_')]")
    private List<WebElement> TotalAmount;

    @FindBy(xpath = "//*[starts-with(@id, 'qty_recvd_6_')]")
    private List<WebElement> quantitys;




    public WebElement getItemRate(int itemId) {
        String xpath3 = "//input[@id='item_rate_9_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    public WebElement Justification(int itemId) {
        String xpath3 = "//input[@id='justification_18_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }
    @FindBy(id = "btnOK")
    private WebElement OKBtn;





    @FindBy(xpath = "//*[text()='Store Receipt from Vendor']")
    private WebElement SRV;

    @FindBy(id = "cphBrowserHeader_btnAddNew")
    private WebElement AddBtn;


    @FindBy(id = "item_id_1_0")
    private WebElement SelectItem;

    @FindBy(id = "cphBody_lblAttachDoc")
    private WebElement Attechment;
    @FindBy(id = "txtUploadPic")
    private WebElement ChoseFile;
    @FindBy(id = "btnUpload")
    private WebElement Upload;
    @FindBy(xpath = "//*[starts-with(@id, 'item_id_0_')]")
    private List<WebElement> items;
    @FindBy(id = "cphBody_btnDeleteRow_Top")
    private WebElement DeleteRow;


    @FindBy(id = "cphBody_txtDocDate")
    private WebElement DocDate;

    @FindBy(id = "cphBody_txtBillNumber")
    private WebElement SupplierInvoice;
    @FindBy(id = "cphBody_txtBillDate")
    private WebElement Date;

    @FindBy(id = "cphBody_lblbilldetails")
    private WebElement BillDetails;
    @FindBy(id = "txtBillNumber")
    private WebElement BillNumber;
    @FindBy(id = "txtbillentrydate")
    private WebElement BillEntryDate;
    @FindBy(id = "txtportcode")
    private WebElement ProtCode;

    @FindBy(id = "item_id_0_0")
    private WebElement EnterItem;




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

    public void EnterItems() {

        try {

            Random random = new Random();
            // WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
            Actions actions = new Actions(BaseTest.getDriver());
            Set<String> usedTexts = new HashSet<>();

            for (int i = 0; i < 5; i++) {
                if (i > 0) { // Execute only after i = 0
                    Utilities.Click(BaseTest.getDriver(), AddRow);
                }
                Utilities.SendKeys(BaseTest.getDriver(), getItemSearchElement(i), "%%");
                getItemSearchElement(i).sendKeys(Keys.TAB);
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("iframeGridDialog")));
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ItemFilter);
                String text = Item.get(i).getText();
                // Check for unique text
                while (usedTexts.contains(text)) {
                    int randomIndex1 = random.nextInt(Item.size() - 2);
                    text = Item.get(randomIndex1).getText(); // Get a new text if duplicate
                }

                usedTexts.add(text); // Add unique text to the set
                Utilities.SendKeys(BaseTest.getDriver(), ItemFilter, text); // Use the unique text
                DynamicWait.smallWait();
                Utilities.Click(BaseTest.getDriver(), FirstColumn);
                try {
                    wait.until(ExpectedConditions.alertIsPresent()).accept();

                } catch (Exception e) {

                }

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

                try {
                    wait.until(ExpectedConditions.alertIsPresent()).accept();

                } catch (Exception e) {

                }
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getrequestQuantityelement(i));
                getrequestQuantityelement(i).click();
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("100").perform();


                // Enter Rate
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getItemRate(i));
                getItemRate(i).click();

                float ItemRate = Float.parseFloat((getItemRate(i).getAttribute("value")));
                if (ItemRate > 0) {
                    // actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("100").perform();
                } else {
                    actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("125").perform();

                }

                try {
                    Utilities.SendKeys(BaseTest.getDriver(), Justification(i), "Testing");
                } catch (Exception e) {

                }

            }

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Items", Status.FAIL, "Exception found in Method - Items", true, e);
        }
    }


    public void EnterQty() {
        try {

            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            Actions actions = new Actions(BaseTest.getDriver());

            for (WebElement quantity : quantitys) {
                quantity.click();
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("105").perform();
                actions.keyDown(Keys.TAB).perform();
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                Utilities.Click(OKBtn);
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));

                quantity.click();
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("100").perform();
            }


            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
            Utilities.Click(BaseTest.getDriver(), SaveBtn);

            try {
                wait.until(ExpectedConditions.alertIsPresent()).accept();
            } catch (Exception e) {

            }
            //BaseTest.getDriver().switchTo().defaultContent();
            try {
                wait.until(ExpectedConditions.alertIsPresent()).accept();
            } catch (Exception e) {

            }
            try {
                wait.until(ExpectedConditions.alertIsPresent()).accept();
            } catch (Exception e) {

            }

//            try {
//                BaseTest.getDriver().switchTo().defaultContent();
//                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
//
//                WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));
//
//                try {
//                    OKBtn.click();
//                    System.out.println("OK Button clicked successfully! ::");
//                } catch (Exception e) {
//                    System.out.println("Normal click failed, trying JavaScript click.");
//                    ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].click();", OKBtn);
//                }
//
//            } catch (Exception e) {
//                System.out.println("Error: " + e.getMessage());
//            }


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("POSRV", Status.FAIL, "Error in Adding Attachment ", true, e);
        }
    }


    public void CreateSRV() {
        try {

            String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "image.jpg";

            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            // Switch to the main content and then to the required frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Wait for Inventory element and click it
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Inventory);
            Utilities.Click(BaseTest.getDriver(), Inventory);

            //Inventory Module Select
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            if (InventoryModule.isDisplayed()&&InventoryModule.isEnabled()) {
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
                Utilities.Click(BaseTest.getDriver(), InventoryModule);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Inventory Button",Status.FAIL,"Inventory Button is not Displayed or not Enabled",true);
            }
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Click on SRV
            Utilities.Click(SRV);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(AddBtn);


            // Select the Vendor

            if (Vendor.isEnabled()&&Vendor.isDisplayed()) {
                Utilities.SendKeys(BaseTest.getDriver(), Vendor, "%%");
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Vendor",Status.FAIL,"Error Sending vendor is not intrectable",true);
            }
            //Utilities.Click(BaseTest.getDriver(), VendorImg);

            Actions actions = new Actions(BaseTest.getDriver());
            actions.sendKeys(Keys.TAB).perform();


            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));


            Random random = new Random();
            System.out.println(VendorList);

            int randomIndex = random.nextInt(VendorList.size());
           // Utilities.DoubleClick(randomIndex);
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


//            // Click Attachment
//            Utilities.Click(Attechment);
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            BaseTest.getDriver().switchTo().frame("iframeAttachFileDialog");
//
//            Utilities.SendKeys(ChoseFile, path);
//            Utilities.Click(Upload);


            // Enter the Supplier No
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(DocDate);
            actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
            actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
            Utilities.SendKeys(SupplierInvoice, "Supplier");
            //Utilities.SendKeys(Date,"14/09/2024");
            Utilities.Click(Date);
            actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform(); // Paste


//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            Utilities.Click(BillDetails);
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.SendKeys(BillNumber, "369");
//            Utilities.SendKeys(ProtCode, "999");
//            Utilities.SendKeys(BillEntryDate, "12/09/2024");
//            OKBtn.click();




            //Click Enter Items Details
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("SRV Creation", Status.FAIL, "Error in Creation SRV", true, e);
        }
    }


    public void EnterRecivingItemz() {

        try {
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));

            Random random = new Random();
            // WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
            Actions actions = new Actions(BaseTest.getDriver());
            Set<String> usedTexts = new HashSet<>();


            for (int i = 0; i < 1; i++) {
//                if (i > 0) { // Execute only after i = 0
//                    Utilities.Click(BaseTest.getDriver(), AddRow);
//                }


                Utilities.SendKeys(BaseTest.getDriver(), EnterItem, "GS000327");
                EnterItem.sendKeys(Keys.TAB);
//                BaseTest.getDriver().switchTo().defaultContent();
//                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("iframeGridDialog")));
//                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ItemFilter);
//                String text = Item.get(i).getText();
//                // Check for unique text
//                while (usedTexts.contains(text)) {
//                    int randomIndex1 = random.nextInt(Item.size() - 2);
//                    text = Item.get(randomIndex1).getText(); // Get a new text if duplicate
//                }
//
//                usedTexts.add(text); // Add unique text to the set
//                Utilities.SendKeys(BaseTest.getDriver(), ItemFilter, text); // Use the unique text
//                DynamicWait.smallWait();
//                Utilities.Click(BaseTest.getDriver(), FirstColumn);
                try {
                    wait.until(ExpectedConditions.alertIsPresent()).accept();

                } catch (Exception e) {
                    System.out.println("No alert is present");
                }

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

                try {
                    wait.until(ExpectedConditions.alertIsPresent()).accept();

                } catch (Exception e) {

                }
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getrequestQuantityelement(i));
                getrequestQuantityelement(i).click();
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("10").perform();


                // Enter Rate
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getItemRate(i));
                getItemRate(i).click();

                float ItemRate = Float.parseFloat((getItemRate(i).getAttribute("value")));
//                if (ItemRate > 0) {
//                    // actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("100").perform();
//                } else {
                    actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("250").perform();

              //  }


                try {
                    Utilities.SendKeys(BaseTest.getDriver(), Justification(i), "Testing");
                } catch (Exception e) {

                }
            }

//            SRVPage ob = new SRVPage(BaseTest.getDriver());
//            // Call the Direct issue method
//            ob.DirectIssue();
//            ob.SelectDepartment();

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
            Utilities.Click(BaseTest.getDriver(), SaveBtn);


            try {
                wait.until(ExpectedConditions.alertIsPresent()).accept();
            } catch (Exception e) {

            }
            //BaseTest.getDriver().switchTo().defaultContent();
            try {
                wait.until(ExpectedConditions.alertIsPresent()).accept();
            } catch (Exception e) {

            }
            try {
                wait.until(ExpectedConditions.alertIsPresent()).accept();
            } catch (Exception e) {

            }

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


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Items", Status.FAIL, "Exception found in Method - Items", true, e);
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


}




package pages.POPages.WithoutApproval.withRQ;

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

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Random;


public class RPPage {

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
    @FindBy(id = "cphBody_txtVendor")
    private WebElement Vendor;
    @FindBy(id = "cphBody_imgVendor")
    private WebElement VendorImg;
    @FindBy(xpath = "//td[@class=\"ControlItemStyle\"][1]")
    private List<WebElement> VendorList;
    @FindBy(xpath = "//input[@id='col_1']")
    private WebElement Filter;
    @FindBy(id = "btnOK")
    private WebElement FirstColumn;
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
    @FindBy(id = "cphBody_lblSchedules_1")
    private WebElement Schedule;
    @FindBy(id = "imgItem")
    private WebElement ItemDropDown;
    @FindBy(id = "td_0_0")
    private WebElement SelectItem;
    @FindBy(id = "btnSave")
    private WebElement Save;
    @FindBy(id = "btnSearch")
    private WebElement Search;
    @FindBy(id = "lblMonthly")
    private WebElement Monthly;
    @FindBy(css = "[id^='Quantity_1_']")
    private List<WebElement> quantity;

    public WebElement Justification(int itemId) {
        String xpath3 = "//input[@id='justification_18_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    @FindBy(xpath = "//*[@id='schd_qty_2_0']")
    private WebElement ScheduleQty;
    @FindBy(xpath = "//*[@id=\"schd_date_1_0\"]")
    private WebElement Date;
    @FindBy(id = "cphBody_lblSchedules")
    private WebElement SchedulePO;
    @FindBy(xpath = "//*[@id=\"txtScheduleDate\"]")
    private WebElement ScheduleDate;
    @FindBy(id = "txtExpectedDate")
    private WebElement ExpectedDate;
    @FindBy(id = "cphBody_lblPaymentSchedules_1")
    private WebElement PaymentSchedule;
    @FindBy(id = "txtAdvAmount")
    private WebElement Advance;
    @FindBy(id = "txtDeliAmount")
    private WebElement onDelivery;
    @FindBy(xpath = "//input[starts-with(@id,'item_id_1_')]")
    private List<WebElement> ItemID;
    @FindBy(xpath = "//input[starts-with(@id,'po_item_amount_10_')]")
    private List<WebElement> ItemAmount;
    @FindBy(xpath = "//input[starts-with(@id,'qty_ordered_5_')]")
    private List<WebElement> ItemQuantity;
    @FindBy(id = "cphHeader_lnkHome")
    private WebElement HomePage;
    @FindBy(id = "txtEffectiveFrom")
    private WebElement EffectiveFrom;


    public static void setPONumber(String poNumber) {
        PONumber = poNumber;
    }

    WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));

    public WebElement getItemSearchElement(int itemId) {
        String xpath = "//input[@id='item_id_1_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath));
    }

    public WebElement getrequestQuantityelement(int itemId) {
        String xpath3 = "//input[@id='qty_ordered_5_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    Actions actions = new Actions(BaseTest.getDriver());

    public void RPPOCreation() {
        try {
            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "POData.xlsx";

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            try {
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Inventory);

            } catch (Exception e) {
                Utilities.WaitTillElementIsClickable(BaseTest.getDriver(), Inventory);
            }
            //Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Inventory);
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
            Utilities.SendKeys(BaseTest.getDriver(), ToDate, "05/11/2025");
            String Value = "CCL001";
            //Select Vendor



            Utilities.SendKeys(BaseTest.getDriver(), Vendor, "%%");
            actions.sendKeys(Keys.TAB).sendKeys(Keys.TAB).perform();
            //Utilities.Click(BaseTest.getDriver(), VendorImg);


            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
//
//            System.out.println(VendorList);
//            Random random = new Random();
//            int randomIndex = random.nextInt(VendorList.size());
//           for (int i=1;i<randomIndex;i++) {
//               System.out.println(VendorList.get(i).getText());
//           }
////            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
////            Utilities.SendKeys(BaseTest.getDriver(), Filter, VendorList.get(randomIndex).getText());
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
//
//            Utilities.Click(BaseTest.getDriver(), OKBtn);


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

            // Enter Rate
            for (int i = 0; i < itemRates.size(); i++) {
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
                try {
                    Utilities.SendKeys(BaseTest.getDriver(), Justification(i), "Testing");
                } catch (Exception e) {

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


//            BaseTest.getDriver().switchTo().defaultContent();
//            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeDlg"));
//            Utilities.Click(BaseTest.getDriver(), Print);
//
//            try {
//                BaseTest.getDriver().switchTo().defaultContent();
//                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
//                try
//                {
//                    wait.until(ExpectedConditions.elementToBeClickable(rdFinal));
//                    Utilities.Click(BaseTest.getDriver(),rdFinal);
//                }
//                catch (Exception e){
//
//                }
//
//                WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"btnOk\"]")));
//
//                try {
//                    OKBtn.click();
//                    System.out.println("OK Button clicked successfully!");
//                } catch (Exception e) {
//                    System.out.println("Normal click failed, trying JavaScript click.");
//                    ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].click();", OKBtn);
//                }
//
//            } catch (Exception e) {
//                System.out.println("Error: " + e.getMessage());
//            }
//
//            DynamicWait.longWait();
//            Robot robot = new Robot();
//            robot.keyPress(KeyEvent.VK_ENTER);
//            DynamicWait.longWait();
//            robot.keyRelease(KeyEvent.VK_ENTER);
//            DynamicWait.longWait();
//
//            String expected = "Approved";
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//
//            Utilities.Click(BaseTest.getDriver(),CancelBtn);
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            Utilities.Click(BaseTest.getDriver(),RevokeApproval);
//
//            Alert alert = BaseTest.getDriver().switchTo().alert();
//            wait.until(ExpectedConditions.alertIsPresent());
//            alert.accept();
//            wait.until(ExpectedConditions.alertIsPresent());
//            alert.accept();
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
//            wait.until(ExpectedConditions.elementToBeClickable(OKBTN));
//            Utilities.Click(BaseTest.getDriver(),OKBTN);
//
//            Actions actions = new Actions(BaseTest.getDriver());
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            for (WebElement rate : itemRate) {
//                //Utilities.SendKeys(BaseTest.getDriver(),rate,"10");
//                Utilities.Click(BaseTest.getDriver(),rate);
//                actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("99").perform();
//
//            }
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
//            Utilities.Click(BaseTest.getDriver(), SaveBtn);
//
//
//
//            ///  testing
//
//
//
//
//            RPPOApprovalPage.validate();

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);
        }
    }


    public void ScheduleItem() {

        ExtentTestManager.logTestStepWithScreenshot("ScheduleItem", Status.INFO, "Starting ScheduleItem workflow", false);

        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            Utilities.Click(BaseTest.getDriver(), Schedule);
            ExtentTestManager.logTestStepWithScreenshot("Click", Status.PASS, "Clicked on Schedule", false);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Utilities.Click(BaseTest.getDriver(), ItemDropDown);
            ExtentTestManager.logTestStepWithScreenshot("Click", Status.PASS, "Clicked on ItemDropDown", false);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Utilities.DoubleClick(SelectItem);
            ExtentTestManager.logTestStepWithScreenshot("DoubleClick", Status.PASS, "Double clicked on SelectItem", false);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Utilities.Click(BaseTest.getDriver(), Search);
            ExtentTestManager.logTestStepWithScreenshot("Click", Status.PASS, "Clicked on Search", false);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            try {
                OKBtn.click();
                ExtentTestManager.logTestStepWithScreenshot("Click", Status.PASS, "Clicked on OKBtn", false);
            } catch (Exception e) {
                OKBTN.click();
                ExtentTestManager.logTestStepWithScreenshot("Click", Status.PASS, "Clicked on OKBTN fallback", false);
            }

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Utilities.Click(BaseTest.getDriver(), Monthly);
            ExtentTestManager.logTestStepWithScreenshot("Click", Status.PASS, "Clicked on Monthly", false);

            Actions actions = new Actions(BaseTest.getDriver());

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            ExtentTestManager.logTestStepWithScreenshot("Quantity", Status.INFO, "Updating item quantities", false);

            for (WebElement element : quantity) {
                Utilities.Click(BaseTest.getDriver(), element);
                ExtentTestManager.logTestStepWithScreenshot("Click", Status.PASS, "Clicked on quantity field", false);

                actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("125").perform();
                ExtentTestManager.logTestStepWithScreenshot("Input", Status.INFO, "Entered quantity: 125", false);
            }

            try {
                OKBtn.click();
                ExtentTestManager.logTestStepWithScreenshot("Click", Status.PASS, "Clicked on OKBtn", false);
            } catch (Exception e) {
                OKBTN.click();
                ExtentTestManager.logTestStepWithScreenshot("Click", Status.PASS, "Clicked on OKBTN fallback", false);
            }

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Utilities.Click(BaseTest.getDriver(), ScheduleQty);
            ExtentTestManager.logTestStepWithScreenshot("Click", Status.PASS, "Clicked on ScheduleQty", false);

            actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("125").perform();
            ExtentTestManager.logTestStepWithScreenshot("Input", Status.INFO, "Entered Schedule Qty: 125", false);

            Utilities.SendKeys(BaseTest.getDriver(), Date, "17/09/2024");
            ExtentTestManager.logTestStepWithScreenshot("Input", Status.PASS, "Entered Effective Date: 17/09/2024", false);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");






            Utilities.Click(BaseTest.getDriver(), Save);
            ExtentTestManager.logTestStepWithScreenshot("Click", Status.PASS, "Clicked on Save", false);

            try {
                Alert alert = BaseTest.getDriver().switchTo().alert();
                alert.accept();
                ExtentTestManager.logTestStepWithScreenshot("Alert", Status.INFO, "Accepted Save alert", false);
            } catch (Exception e) {
                ExtentTestManager.logTestStepWithScreenshot("Alert", Status.INFO, "No alert displayed after Save", false);
            }

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            try {
                OKBtn.click();
                ExtentTestManager.logTestStepWithScreenshot("Click", Status.PASS, "Clicked final OKBtn", false);
            } catch (Exception e) {
                OKBTN.click();
                ExtentTestManager.logTestStepWithScreenshot("Click", Status.PASS, "Clicked final OKBTN fallback", false);
            }

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Utilities.Click(BaseTest.getDriver(), Close);
            ExtentTestManager.logTestStepWithScreenshot("Click", Status.PASS, "Clicked on Close", false);

            ExtentTestManager.logTestStepWithScreenshot("ScheduleItem", Status.PASS, "ScheduleItem completed successfully", false);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("ScheduleItem", Status.FAIL, "Exception in ScheduleItem", true, e);
        }
    }


    public void ScheduleItem1() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            Utilities.Click(BaseTest.getDriver(), Schedule);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Utilities.Click(BaseTest.getDriver(), ItemDropDown);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Utilities.DoubleClick(SelectItem);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(BaseTest.getDriver(), Search);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            //Utilities.Click(BaseTest.getDriver(),OKBtn);

            try {
                OKBtn.click();
            } catch (Exception e) {
                OKBTN.click();
            }

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(BaseTest.getDriver(), Monthly);

            Actions actions = new Actions(BaseTest.getDriver());

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            for (WebElement element : quantity) {
                Utilities.Click(BaseTest.getDriver(), element);
                actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("125").perform();

                //Utilities.SendKeys(BaseTest.getDriver(),element,"10");
            }

            try {
                OKBtn.click();
            } catch (Exception e) {
                OKBTN.click();
            }

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(BaseTest.getDriver(), ScheduleQty);
            actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("125").perform();


            //String Date = EffectiveFrom.getAttribute("value");
            Utilities.SendKeys(BaseTest.getDriver(), Date, "17/09/2024");


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(BaseTest.getDriver(), Save);

            try {
                Alert alert = BaseTest.getDriver().switchTo().alert();
                alert.accept();
            } catch (Exception e) {

            }



            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            try {
                OKBtn.click();
            } catch (Exception e) {
                OKBTN.click();
            }


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(BaseTest.getDriver(), Close);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Schedule", Status.FAIL, "Exception in Schedule", true, e);
        }
    }





    public void SchedulePO() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            Utilities.Click(BaseTest.getDriver(), SchedulePO);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            String Date1 = EffectiveFrom.getAttribute("value");
            Utilities.SendKeys(BaseTest.getDriver(), ScheduleDate, Date1);


//            actions.sendKeys(Keys.TAB).perform();



            Utilities.SendKeys(BaseTest.getDriver(), ExpectedDate, Date1);


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            try {
                OKBtn.click();
            } catch (Exception e) {
                OKBTN.click();
            }



            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            //Utilities.Click(BaseTest.getDriver(),rdFinal);
            Utilities.Click(BaseTest.getDriver(), Save);

//            try {
//                OKBtn.click();
//            } catch (Exception e) {
//                //OKBTN.click();
//            }
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            try {
                OKBtn.click();
            } catch (Exception e) {
                OKBTN.click();
            }
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            try {
//                Utilities.Click(BaseTest.getDriver(), Save);
//            } catch (Exception e){
//
//            }
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//
//            try {
//                OKBtn.click();
//            } catch (Exception e) {
//                OKBTN.click();
//            }


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(BaseTest.getDriver(), Close);


//            Utilities.Click(BaseTest.getDriver(), ItemDropDown);
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//
//            Utilities.DoubleClick(SelectItem);
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.Click(BaseTest.getDriver(), Search);
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            //Utilities.Click(BaseTest.getDriver(),OKBtn);
//
//            try {
//                OKBtn.click();
//            } catch (Exception e) {
//                OKBTN.click();
//            }
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.Click(BaseTest.getDriver(), Monthly);
//
//            Actions actions = new Actions(BaseTest.getDriver());
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            for (WebElement element : quantity) {
//                Utilities.Click(BaseTest.getDriver(), element);
//                actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("125").perform();
//
//                //Utilities.SendKeys(BaseTest.getDriver(),element,"10");
//            }
//
//            try {
//                OKBtn.click();
//            } catch (Exception e) {
//                OKBTN.click();
//            }
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.Click(BaseTest.getDriver(), ScheduleQty);
//            actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("125").perform();
//
//            Utilities.SendKeys(BaseTest.getDriver(), Date, "17/09/2024");
//
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.Click(BaseTest.getDriver(), Save);
//
//            Alert alert = BaseTest.getDriver().switchTo().alert();
//            alert.accept();
//
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//
//            try {
//                OKBtn.click();
//            } catch (Exception e) {
//                OKBTN.click();
//            }
//
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.Click(BaseTest.getDriver(), Close);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Schedule", Status.FAIL, "Exception in Schedule", true, e);
        }
    }

    public void PaymentSchedule() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            Utilities.Click(BaseTest.getDriver(), PaymentSchedule);


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Utilities.Click(BaseTest.getDriver(), ItemDropDown);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Utilities.DoubleClick(SelectItem);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Utilities.Click(BaseTest.getDriver(), Search);
            Utilities.SendKeys(BaseTest.getDriver(), Advance, "10");
            Utilities.SendKeys(BaseTest.getDriver(), onDelivery, "10");
            Utilities.Click(BaseTest.getDriver(), Save);


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            try {
                OKBtn.click();
            } catch (Exception e) {
                OKBTN.click();
            }


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(BaseTest.getDriver(), Close);


//            Utilities.Click(BaseTest.getDriver(), ItemDropDown);
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//
//            Utilities.DoubleClick(SelectItem);
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.Click(BaseTest.getDriver(), Search);
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            //Utilities.Click(BaseTest.getDriver(),OKBtn);
//
//            try {
//                OKBtn.click();
//            } catch (Exception e) {
//                OKBTN.click();
//            }
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.Click(BaseTest.getDriver(), Monthly);
//
//            Actions actions = new Actions(BaseTest.getDriver());
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            for (WebElement element : quantity) {
//                Utilities.Click(BaseTest.getDriver(), element);
//                actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("125").perform();
//
//                //Utilities.SendKeys(BaseTest.getDriver(),element,"10");
//            }
//
//            try {
//                OKBtn.click();
//            } catch (Exception e) {
//                OKBTN.click();
//            }
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.Click(BaseTest.getDriver(), ScheduleQty);
//            actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("125").perform();
//
//            Utilities.SendKeys(BaseTest.getDriver(), Date, "17/09/2024");
//
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.Click(BaseTest.getDriver(), Save);
//
//            Alert alert = BaseTest.getDriver().switchTo().alert();
//            alert.accept();
//
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//
//            try {
//                OKBtn.click();
//            } catch (Exception e) {
//                OKBTN.click();
//            }
//
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.Click(BaseTest.getDriver(), Close);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Schedule", Status.FAIL, "Exception in Schedule", true, e);
        }
    }

    public void Print() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeDlg"));
            Utilities.Click(BaseTest.getDriver(), Print);
            try {
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
                try {
                    wait.until(ExpectedConditions.elementToBeClickable(rdFinal));
                    Utilities.ClickCheckBox( rdFinal);
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
            Robot robot = new Robot();
            DynamicWait.longWait();
            robot.keyPress(KeyEvent.VK_ENTER);
            DynamicWait.longWait();
            robot.keyRelease(KeyEvent.VK_ENTER);
            DynamicWait.longWait();


            // Store the Entered Details to for the validations
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeDlg"));

            List<String> ratesWeb = ItemAmount.stream().map(e -> e.getAttribute("value")).filter(s -> !s.isEmpty()).toList();
            System.out.println("Rates are: " + ratesWeb);
            List<String> itemIDWeb = ItemID.stream().map(e -> e.getAttribute("value")).filter(s -> !s.isEmpty()).toList();
            System.out.println("ItemID are: " + itemIDWeb);
            List<String> quantitiesWeb = ItemQuantity.stream().map(e -> e.getAttribute("value")).filter(s -> !s.isEmpty()).toList();
            System.out.println("Quantity are: " + quantitiesWeb);


           // validatePDFData(itemIDWeb, quantitiesWeb, ratesWeb);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Utilities.Click(BaseTest.getDriver(),CancelBtn);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);

        }
    }

    public void NavigateHome(){
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeDlg"));

            Utilities.Click(BaseTest.getDriver(),HomePage);
            try {
                // Wait briefly for the alert and accept it if it appears
                WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(5));
                wait.until(ExpectedConditions.alertIsPresent()).accept(); // Accept the alert if present
                wait.until(ExpectedConditions.alertIsPresent()).dismiss(); // Dismiss the data unsave alert if present
            } catch (TimeoutException e) {
                // If no alert is present within the wait time, continue without doing anything
            }
            try {
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeDlg"));

                Utilities.Click(BaseTest.getDriver(),HomePage);
            } catch (Exception e){

            }

        } catch (Exception e){
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);

        }


    }
    public void Edit(){
        try {

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Edit PO",Status.FAIL,"Error in Editing the PO",true,e);
        }
    }
}




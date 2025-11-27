package pages.PO;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import datahandlers.ExcelHandler;
import datahandlers.PdfHandler;
import extentreports.ExtentTestManager;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utils.Dateformat;
import utils.DynamicWait;
import utils.GetLatestFile;
import utils.Utilities;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class POPage {


    public POPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static String PONumber;


    @FindBy(xpath = "//a[text()='Inventory']")
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
    @FindBy(xpath = "//*[starts-with(@id, 'new_order_12_')]")
    private List<WebElement> NewOrder;


    @FindBy(xpath = "//*[starts-with(@id, 'td_0_')]")
    private List<WebElement> Items;

    @FindBy(xpath = "//*[starts-with(@id, 'justification_18_')]")
    private List<WebElement> Justification;








    @FindBy(id = "cphBrowserHeader_btnAddNew")
    private WebElement AddButton;
    @FindBy(xpath = "//select[@id=\"cphHeader_ddlPropertyList\"]")
    private WebElement SwitchTo;
    @FindBy(id = "img_item_tax_class_16_0")
    private WebElement ItemTaxClass;
    @FindBy(xpath = "//*[@id=\"td_2_10\"]")
    private WebElement SelectTax;
    @FindBy(id = "item_desc_2_2")
    private WebElement SelectRow;
    @FindBy(id = "cphBody_btnAddRow")
    private WebElement AddRow;
    @FindBy(id = "cphBody_btnDeleteRow")
    private WebElement DeleteRow;
    @FindBy(xpath = "//input[@id='col_0']")
    private WebElement ItemFilter;
    @FindBy(id = "item_id_1_0")
    private WebElement SelectItem;
    @FindBy(id = "cphBody_lblCharges_1")
    private WebElement Charges;
    @FindBy(id = "cphBody_lblCharges")
    private WebElement POLevelCharges;
    @FindBy(id = "img_charge_desc_2_0")
    private WebElement SelectCharges;
    @FindBy(xpath = "//td[@id='td_0_0' and text()='OTAA']")
    private WebElement ClickCharge;
    @FindBy(id = "btnSave")
    private WebElement Save;
    @FindBy(xpath = "//*[@id='btnClose']")
    private WebElement CloseBtn;
    @FindBy(xpath = "//td[@class=\"ControlItemStyle\"][1]")
    private List<WebElement> Item;
    @FindBy(id = "cphBody_txtDateFrom")
    private WebElement DateFrom;
    @FindBy(id = "cphBody_btnSearch")
    private WebElement Search;
    @FindBy(id = "td_1_0")
    private WebElement SelectPO;
    @FindBy(id = "cphBody_lblCopyPurchaseOrder")
    private WebElement CopyPO;
    @FindBy(xpath = "//*[text()='Approve Purchase Orders']")
    private WebElement ApprovePO;
    @FindBy(id = "btnOK")
    private WebElement OK;
    @FindBy(id = "cphBody_btnRefresh")
    private WebElement Refresh;
    @FindBy(xpath = "(//select[contains(@id, 'ddlaction')])[1]")
    private WebElement SelectAP;
    @FindBy(id = "cphDialogHeader_btnSave")
    private WebElement Process;
    @FindBy(id = "cphHeader_lnkHome")
    private WebElement HomePage;
    @FindBy(xpath = "//a[text()='Generate delivery Order']")
    private WebElement DeliveryOrder;
    @FindBy(id = "cphBody_txtNumber")
    private WebElement RQNumber;
    @FindBy(id = "cphBody_rdogendis")
    private WebElement GeneratePrint;
    @FindBy(id = "cphBody_txtdatedelivery")
    private WebElement DeliveryDate;
    @FindBy(css = "input[id^='selected_9_']")
    private List<WebElement> checkboxes;
    @FindBy(xpath = "//*[text()='Generate Draft PO']")
    private WebElement DraftPO;
    @FindBy(id = "cphBody_txtPeriodSince")
    private WebElement Date;
    @FindBy(id = "cphBody_txtNoOfDaysInPeriod")
    private WebElement NoOfDays;

    public WebElement Justification(int itemId) {
        String xpath3 = "//input[@id='justification_18_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }


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



    @FindBy(xpath = "//*[starts-with(@id, 'qty_ordered_5_')]")
    private List<WebElement> Quantity;



    public WebElement NewOrder(int itemId) {
        String xpath3 = "//input[@id='new_order_12_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    @FindBy(id = "chkSendEmail")
    private WebElement Sendmail;
    @FindBy(id = "lblErrorDesc")
    private WebElement ProductConfirmation;
    @FindBy(xpath = "//input[starts-with(@id,'item_desc_2_')]")
    private List<WebElement> ItemName;
    @FindBy(xpath = "//input[starts-with(@id,'item_rate_9_')]")
    private List<WebElement> Rate;

    @FindBy(xpath = "//input[starts-with(@id,'tot_amount_11_')]")
    private List<WebElement> TotalAmount;
    @FindBy(id = "lblErrorDesc")
    private WebElement PODialougue;




    public void DraftPOCreation() {

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
            Utilities.Click(BaseTest.getDriver(), DraftPO);

            //Click on Add Button
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Vendor);

            Utilities.SendKeys(Vendor,"GNSPA004");
            Utilities.SendKeys(Date,"12/09/2024");
            Utilities.SendKeys(NoOfDays,"5");
            Utilities.Click(Search);



            Actions actions = new Actions(BaseTest.getDriver());
            for (WebElement itemrate : NewOrder) {

                itemrate.click();
                actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("125").perform();

            }

            Utilities.Click(Process);


            //float ItemRate = Float.parseFloat((NewOrder(i).getAttribute("value")));
            // if (ItemRate > 0) {
            // actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("100").perform();
            //} else {

            //}



        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);

        }
    }

    public void DeliveryOrder(){
        try {

            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "POData.xlsx";

            ExcelHandler.loadExcelFile(Updload_Path, 0);
            List<String> Resources1 = ExcelHandler.getAllColumnData("RQ Number");
            BaseTest.getDriver().navigate().refresh();


//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            //Utilities.Click(HomePage);

            // Wait for Inventory element and click it
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Inventory);
//            Utilities.Click(BaseTest.getDriver(), Inventory);

            //Inventory Module Select
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
            Utilities.Click(BaseTest.getDriver(), InventoryModule);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Click on PO Generation
            Utilities.Click(DeliveryOrder);

            // Enter RQ Number for generating the Delivery Order
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            String resource1 = Resources1.get(Resources1.size() - 1);
            Utilities.SendKeys(BaseTest.getDriver(), RQNumber, resource1);

            Utilities.Click(GeneratePrint);


            Utilities.SendKeys(DeliveryDate,"17/09/2024");

            Utilities.Click(Search);



            for (WebElement checkbox : checkboxes) {
                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
            }


            Utilities.Click(Process);





        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Delivery Order", Status.FAIL, "Exception found in Method - Delivery Order", true, e);

        }
    }

    public void POApproveScreen(){
        try {
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
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
            Utilities.Click(BaseTest.getDriver(), InventoryModule);

            Utilities.Click(ApprovePO);


            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            OK.click();

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            Utilities.Click(Refresh);
            Utilities.SendKeys(DateFrom,"23/08/2020");
            Utilities.Click(Search);
            Utilities.selectBy("visibletext", SelectAP, "Approve");

//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(Process);




        } catch (Exception e){
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Approve", Status.FAIL, "Exception found in Method - Approve PO", true, e);

        }
    }

    public void Print() {

        WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
        try {


            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

            WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));

            OKBtn.click();





//            try {
//                BaseTest.getDriver().switchTo().defaultContent();
//                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
//
//                WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));
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


            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeDlg"));
            Utilities.Click(BaseTest.getDriver(), Print);


            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

            wait.until(ExpectedConditions.elementToBeClickable(rdFinal));
            Utilities.ClickCheckBox(rdFinal);

            Utilities.ClickCheckBox(Sendmail);


            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

            WebElement OKB = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"btnOk\"]")));


            OKB.click();
            System.out.println("OK Button clicked successfully!");


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PODATA", Status.FAIL, "Exception found in Method - AddProduct", true, e);
        }
    }

    public void CPPO() {
        WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));

        try {
            // Switch to the main content and then to the required frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Wait for Inventory element and click it
            DynamicWait.longWait();
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

            // Enter Rate


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);

        }
    }

    public void RPPO() {
        WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));

        try {
            // Switch to the main content and then to the required frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Wait for Inventory element and click it
            DynamicWait.longWait();
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

            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);

        }
    }

    public void Switch0346() {
        try {
            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "TestData.xlsx";
            ExcelHandler.loadExcelFile(Updload_Path, 1);
            List<String> Resources = ExcelHandler.getAllColumnData("Resources");
            String resource = Resources.get(6);

            //C0345 - Citrus Classic Bhopal(Madhya Pradesh) Resource select
            try {
                WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(5));
                wait.until(ExpectedConditions.alertIsPresent()).accept();
                wait.until(ExpectedConditions.alertIsPresent()).dismiss();
            } catch (TimeoutException e) {

            }
            BaseTest.getDriver().manage().window().maximize();
            BaseTest.getDriver().switchTo().defaultContent();

            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), SwitchTo);
            Utilities.selectBy(BaseTest.getDriver(), "visibletext", SwitchTo, resource);
            try {
                WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(5));
                wait.until(ExpectedConditions.alertIsPresent()).accept();
                wait.until(ExpectedConditions.alertIsPresent()).dismiss();
            } catch (TimeoutException e) {

            }
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Switch Property", Status.FAIL, "Exception found in Method - Switch Property", true, e);
        }

    }


    public static void validate() {
        try {
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));

            // 1️⃣ Switch to iframe if needed
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeDlg"));

            // 2️⃣ Ensure the table is visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[contains(@class, 'tableClassName')]"))); // Adjust XPath

            // 3️⃣ Extract Item Names, Quantities, and Rates (Using Correct XPaths)
            List<WebElement> itemNames = BaseTest.getDriver().findElements(By.xpath("//input[starts-with(@id,'item_id_1_')]"));
            List<WebElement> quantities = BaseTest.getDriver().findElements(By.xpath("//input[starts-with(@id,'item_quantity_')]")); // Adjust XPath
            List<WebElement> rates = BaseTest.getDriver().findElements(By.xpath("//input[starts-with(@id,'item_rate_')]")); // Adjust XPath
            WebElement totalAmountElement = BaseTest.getDriver().findElement(By.xpath("//input[@id='totalAmount']")); // Adjust XPath

            // 4️⃣ Convert WebElements to Strings (Use getAttribute("value") for Input Fields)
            List<String> itemNamesWeb = itemNames.stream().map(e -> e.getAttribute("value")).filter(s -> !s.isEmpty()).toList();
            List<String> quantitiesWeb = quantities.stream().map(e -> e.getAttribute("value")).filter(s -> !s.isEmpty()).toList();
            // List<String> ratesWeb = rates.stream().map(e -> e.getAttribute("value")).filter(s -> !s.isEmpty()).toList();
            String totalAmountWeb = totalAmountElement.getAttribute("value").trim();

            // 5️⃣ Debugging: Print extracted values
            System.out.println("✅ Webpage Data:");
            System.out.println("Items: " + itemNamesWeb);
            System.out.println("Quantities: " + quantitiesWeb);
            // System.out.println("Rates: " + ratesWeb);
            System.out.println("Total Amount: " + totalAmountWeb);

            // 6️⃣ Validate against PDF data

            // validatePDFData(itemNamesWeb, quantitiesWeb, ratesWeb, totalAmountWeb);

        } catch (Exception e) {
            System.out.println("❌ Error Extracting Webpage Data: " + e.getMessage());
        }
    }

    // public void validatePDFData(List<String> itemNamesWeb, List<String> quantitiesWeb, List<String> ratesWeb, String totalAmountWeb) {
    public void validatePDFData(List<String> itemNamesWeb, List<String> quantitiesWeb, List<String> ratesWeb) {

        // public void validatePDFData(  List<String> ratesWeb) {
        // Get latest downloaded PDF file
        //  String path = "";
        String path = System.getProperty("user.dir") + File.separator + "DownloadPath";

        File latestPdf = GetLatestFile.getLatestFileFromDirectory(path);

        if (latestPdf != null) {
            System.out.println("Latest PDF found: " + latestPdf.getAbsolutePath());

            // Extract text from PDF
            String pdfContent = PdfHandler.ReadPdf(String.valueOf(latestPdf));

//            System.out.println("\n==== Extracted PDF Content ====");
//            System.out.println(pdfContent);
//            System.out.println("==== PDF Content End ====\n");

            // **Validation**
            boolean allMatched = true;

            // Check if each item name from webpage exists in PDF
            for (String item : itemNamesWeb) {
                if (!pdfContent.contains(item)) {
                    System.out.println("Mismatch Found: Item Name '" + item + "' is NOT in PDF");
                    allMatched = false;
                }
            }

            // Check if each quantity from webpage exists in PDF
            for (String quantity : quantitiesWeb) {
                if (!pdfContent.contains(quantity)) {
                    System.out.println("Mismatch Found: Quantity '" + quantity + "' is NOT in PDF");
                    allMatched = false;
                }
            }

            // Check if each rate from webpage exists in PDF
            for (String rate : ratesWeb) {
                if (!pdfContent.contains(rate)) {
                    System.out.println("Mismatch Found: Rate '" + rate + "' is NOT in PDF");
                    allMatched = false;
                }
            }

            // Check if total amount from webpage exists in PDF
//            if (!pdfContent.contains(totalAmountWeb)) {
//                System.out.println("Mismatch Found: Total Amount '" + totalAmountWeb + "' is NOT in PDF");
//                allMatched = false;
//            }

            // Log final validation result
            if (allMatched) {
                System.out.println("✅ PDF Data Matches Webpage Data!");
                ExtentTestManager.createAssertTestStepWithScreenshot("PDF Validation", Status.PASS, "PDF data matches webpage data", true);
            } else {
                System.out.println("❌ PDF Data DOES NOT Match Webpage Data!");
                ExtentTestManager.createAssertTestStepWithScreenshot("PDF Validation", Status.FAIL, "PDF data mismatch", true);
            }

        } else {
            System.out.println("❌ No PDF file found in directory.");
        }
    }

    public void EditPO() {
        try {

            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));


//            BaseTest.getDriver().switchTo().defaultContent();
//            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
//            Utilities.Click(BaseTest.getDriver(),SaveBtn);
//
//            try {
//                wait.until(ExpectedConditions.alertIsPresent()).accept();
//            } catch (Exception e) {
//
//            }
//            BaseTest.getDriver().switchTo().defaultContent();
//            try {
//                wait.until(ExpectedConditions.alertIsPresent()).accept();
//            } catch (Exception e) {
//
//            }
//
//            try {
//                BaseTest.getDriver().switchTo().defaultContent();
//                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
//
//                WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));
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
            Utilities.Click(BaseTest.getDriver(), ItemTaxClass);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            Utilities.DoubleClick(SelectTax);


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), SelectRow);
            Utilities.Click(BaseTest.getDriver(), DeleteRow);
            wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();


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
                    Utilities.ClickCheckBox(rdFinal);
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

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Utilities.Click(BaseTest.getDriver(), CancelBtn);

            // RPPOApprovalPage.validate();

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Edit", Status.FAIL, "Exception found in Method Edit PO ", true, e);
        }
    }

    public void AddRow() {
        try {
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));

            Actions actions = new Actions(BaseTest.getDriver());
            for (int i = 0; i < 1; i++) {
                if (i > 0) { // Execute only after i = 0
                    Utilities.Click(BaseTest.getDriver(), AddRow);
                }
                Random random = new Random();

                Utilities.SendKeys(BaseTest.getDriver(), getItemSearchElement(i), "%%");
                getItemSearchElement(i).sendKeys(Keys.TAB);
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("iframeGridDialog")));
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ItemFilter);
                String text = Item.get(i).getText();
                Set<String> usedTexts = new HashSet<>();
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
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("10").perform();


                // Enter Rate
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getItemRate(i));
                getItemRate(i).click();
                actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("100").perform();

                try {
                    Utilities.SendKeys(BaseTest.getDriver(), Justification(i), "Testing");
                } catch (Exception e) {

                }
            }

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Edit", Status.FAIL, "Exception found in Method Edit PO ", true, e);

        }
    }



    public void ItemCharges() {
        try {
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            Utilities.Click(BaseTest.getDriver(), SelectItem);
            Utilities.Click(BaseTest.getDriver(), Charges);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

            Utilities.Click(BaseTest.getDriver(), SelectCharges);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

            Utilities.DoubleClick(ClickCharge);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            Utilities.Click(BaseTest.getDriver(), Save);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

            try {

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
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            Utilities.Click(BaseTest.getDriver(), CloseBtn);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("ItemCharges", Status.FAIL, "Exception Found in Applying Item Level Charges", true, e);
        }
    }

    public void POCharges() {
        try {
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            Utilities.Click(BaseTest.getDriver(), SelectItem);
            Utilities.Click(BaseTest.getDriver(), POLevelCharges);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

            Utilities.Click(BaseTest.getDriver(), SelectCharges);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

            Utilities.DoubleClick(ClickCharge);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            Utilities.Click(BaseTest.getDriver(), Save);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            //Utilities.Click(BaseTest.getDriver(), OKBtn);


            try {

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
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            Utilities.Click(BaseTest.getDriver(), CloseBtn);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("ItemCharges", Status.FAIL, "Exception Found in Applying Item Level Charges", true, e);
        }
    }

    public void Switch0345() {
        try {
            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "TestData.xlsx";
            ExcelHandler.loadExcelFile(Updload_Path, 1);
            List<String> Resources = ExcelHandler.getAllColumnData("Resources");
            String resource = Resources.get(5);

            //C0345 - Citrus Classic Bhopal(Madhya Pradesh) Resource select
            try {
                WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(5));
                wait.until(ExpectedConditions.alertIsPresent()).accept();
                wait.until(ExpectedConditions.alertIsPresent()).dismiss();
            } catch (TimeoutException e) {

            }
            BaseTest.getDriver().manage().window().maximize();
            BaseTest.getDriver().switchTo().defaultContent();

            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), SwitchTo);
            Utilities.selectBy(BaseTest.getDriver(), "visibletext", SwitchTo, resource);
            try {
                WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(5));
                wait.until(ExpectedConditions.alertIsPresent()).accept();
                wait.until(ExpectedConditions.alertIsPresent()).dismiss();
            } catch (TimeoutException e) {

            }
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Switch Property", Status.FAIL, "Exception found in Method - Switch Property", true, e);
        }

    }

    public void Switch0419() {
        try {
            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "TestData.xlsx";
            ExcelHandler.loadExcelFile(Updload_Path, 1);
            List<String> Resources = ExcelHandler.getAllColumnData("Resources");
            String resource = Resources.get(2);

            //C0345 - Citrus Classic Bhopal(Madhya Pradesh) Resource select
            try {
                WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(5));
                wait.until(ExpectedConditions.alertIsPresent()).accept();
                wait.until(ExpectedConditions.alertIsPresent()).dismiss();
            } catch (TimeoutException e) {

            }
            BaseTest.getDriver().manage().window().maximize();
            BaseTest.getDriver().switchTo().defaultContent();

            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), SwitchTo);
            Utilities.selectBy(BaseTest.getDriver(), "visibletext", SwitchTo, resource);
            try {
                WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(5));
                wait.until(ExpectedConditions.alertIsPresent()).accept();
                wait.until(ExpectedConditions.alertIsPresent()).dismiss();
            } catch (TimeoutException e) {

            }
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Switch Property", Status.FAIL, "Exception found in Method - Switch Property", true, e);
        }

    }

    public void CopyPO(){
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

            //Click on Purchase Order
            Utilities.Click(BaseTest.getDriver(),PurchaseOrder);


//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.SendKeys(DateFrom,"17/08/2024");
            Utilities.Click(Search);
            Utilities.Click(SelectPO);
            Utilities.Click(CopyPO);



        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Copy", Status.FAIL, "Exception found in Copying",
                    true, e);
        }
    }


    public void EnterRQ() {
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

    public void SelectCP() {
        try {
            //Select PO Type
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            Utilities.SendKeys(BaseTest.getDriver(), EnterPOType, "CP");
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));


            //Select To Date
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(BaseTest.getDriver(), ToDate, "12/09/2025");
            String Value = "CCL001";
            //Select Vendor


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

            // Enter Rate
            for (int i = 0; i < itemRates.size(); i++) {
                //Actions actions = new Actions(BaseTest.getDriver());
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

            try {
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
                try
                {
                    wait.until(ExpectedConditions.elementToBeClickable(rdFinal));
                    Utilities.ClickCheckBox(rdFinal);
                }
                catch (Exception e){

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
            DynamicWait.longWait();

            String expected = "Approved";

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Utilities.Click(BaseTest.getDriver(),CancelBtn);
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

    public void SelectRP() {
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

            // Enter Rate
            for (int i = 0; i < itemRates.size(); i++) {
                //Actions actions = new Actions(BaseTest.getDriver());
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

            try {
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
                try
                {
                    wait.until(ExpectedConditions.elementToBeClickable(rdFinal));
                    Utilities.ClickCheckBox(rdFinal);
                    Utilities.ClickCheckBox(Sendmail);
                }
                catch (Exception e){

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
            DynamicWait.longWait();

            String expected = "Approved";

//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            //Utilities.Click(BaseTest.getDriver(),CancelBtn);
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

    public void RPPOwithoutRQ() {

        try {

            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(),Duration.ofSeconds(10));
            // Switch to the main content and then to the required frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Wait for Inventory element and click it
            DynamicWait.longWait();
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
            Utilities.SendKeys(EnterPOType,"RP");


            //Select To Date
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(BaseTest.getDriver(), ToDate, "12/09/2025");

            //Select Vendor
            Utilities.SendKeys(BaseTest.getDriver(), Vendor, "%%");
            //Utilities.Click(BaseTest.getDriver(), VendorImg);

            Actions actions = new Actions(BaseTest.getDriver());
            actions.sendKeys(Keys.TAB).perform();

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));





            Random random = new Random();
            int randomIndex = random.nextInt(VendorList.size());
            WebElement randomVendor = VendorList.get(randomIndex);

            Utilities.DoubleClick(randomVendor);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);

        }
    }

    public void EnterItems() {

        try {
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));

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
//                Utilities.SendKeys(BaseTest.getDriver(), ItemFilter, text); // Use the unique text
//                DynamicWait.smallWait();
//                Utilities.Click(BaseTest.getDriver(), FirstColumn);

                WebElement elementToClick = BaseTest.getDriver().findElement(By.xpath("//*[text()='" + text + "']"));


                Utilities.DoubleClick(elementToClick);



                try {
                    wait.until(ExpectedConditions.alertIsPresent()).accept();

                } catch (Exception e) {

                }



                try {
                    wait.until(ExpectedConditions.alertIsPresent()).accept();

                } catch (Exception e) {

                }

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");


                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getrequestQuantityelement(i));
                getrequestQuantityelement(i).click();
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("10").perform();


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

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
            Utilities.Click(BaseTest.getDriver(), SaveBtn);

            try {
                wait.until(ExpectedConditions.alertIsPresent()).accept();
            } catch (Exception e) {

            }
            BaseTest.getDriver().switchTo().defaultContent();
            try {
                wait.until(ExpectedConditions.alertIsPresent()).accept();
            } catch (Exception e) {

            }

 //           try {
//                BaseTest.getDriver().switchTo().defaultContent();
//                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
//
//                WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));
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


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Items", Status.FAIL, "Exception found in Method - Items", true, e);
        }
    }

    public void EnterItems2() {

        try {
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));

            Random random = new Random();
            // WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
            Actions actions = new Actions(BaseTest.getDriver());
            Set<String> usedTexts = new HashSet<>();

            for (int i = 0; i < 5; i++) {
                if (i > 0) { // Execute only after i = 0

                    BaseTest.getDriver().switchTo().defaultContent();
                    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));


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

                WebElement elementToClick = BaseTest.getDriver().findElement(By.xpath("//*[text()='" + text + "']"));


                Utilities.DoubleClick(elementToClick);


                try {
                    wait.until(ExpectedConditions.alertIsPresent()).accept();

                } catch (Exception e) {

                }

                try {
                    wait.until(ExpectedConditions.alertIsPresent()).accept();

                } catch (Exception e) {

                }





            }


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");


            for (WebElement quantity : Quantity) {
                Utilities.Click(quantity);
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("10").perform();
            }

            for (WebElement rate : itemRates) {
                Utilities.Click(rate);
                actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("125").perform();

            }


            for (WebElement justific : Justification){
                try {
                    Utilities.SendKeys(BaseTest.getDriver(), justific, "Testing");
                } catch (Exception e) {

                }
            }








            Utilities.Click(BaseTest.getDriver(), SaveBtn);


            try {
                wait.until(ExpectedConditions.alertIsPresent()).accept();
            } catch (Exception e) {

            }
            BaseTest.getDriver().switchTo().defaultContent();
            try {
                wait.until(ExpectedConditions.alertIsPresent()).accept();
            } catch (Exception e) {

            }


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            if (ProductConfirmation.isDisplayed() && ProductConfirmation.getText().contains("Purchase Order Number")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Purchase Order Number Confirmation ", Status.PASS, "Purchase Order Number successfully generated", true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Purchase Order NumberConfirmation", Status.FAIL, "Issue in Purchase Order Number", true);
            }

           // String input = ProductConfirmation.getText();


//            BaseTest.getDriver().switchTo().defaultContent();
//            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
//
//            WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));
//
//            OKBtn.click();


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Items", Status.FAIL, "Exception found in Method - Items", true, e);
        }
    }


    public void CreateExcel(){
        try {



            Pattern pattern = Pattern.compile("Purchase Order Number\\s(\\d+)");
            String input = ProductConfirmation.getText();
            Matcher matcher = pattern.matcher(input);

            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(),Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

            WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));

            OKBtn.click();



// Print input for debugging
            System.out.println("Input: " + input);

// Regular expressions to match specific fields
            Pattern totalAmountPattern = Pattern.compile("Total Amount\\(incl\\. charge\\)\\s*([0-9.]+)");
            Pattern finalAmountPattern = Pattern.compile("Final Amount\\(incl\\. tax\\)\\s*([0-9.]+)");
            Pattern totalItemsPattern = Pattern.compile("Total Items\\s*(\\d+)");
            Pattern purchaseOrderPattern = Pattern.compile("Purchase Order Number\\s*(\\d+)");

// Matchers for each field
            Matcher totalAmountMatcher = totalAmountPattern.matcher(input);
            Matcher finalAmountMatcher = finalAmountPattern.matcher(input);
            Matcher totalItemsMatcher = totalItemsPattern.matcher(input);
            Matcher purchaseOrderMatcher = purchaseOrderPattern.matcher(input);

// Extract values if they match
            String TotalAmount = totalAmountMatcher.find() ? totalAmountMatcher.group(1) : "Not Found";
            String FinalAmount = finalAmountMatcher.find() ? finalAmountMatcher.group(1) : "Not Found";
            String TotalItems = totalItemsMatcher.find() ? totalItemsMatcher.group(1) : "Not Found";
            String PurchaseOrderNumber = purchaseOrderMatcher.find() ? purchaseOrderMatcher.group(1) : "Not Found";

// Print the extracted values
            System.out.println("Total Amount: " + TotalAmount);
            System.out.println("Final Amount: " + FinalAmount);
            System.out.println("Total Items: " + TotalItems);
            System.out.println("Purchase Order Number: " + PurchaseOrderNumber);


            SoftAssert softAssert = new SoftAssert();
           // softAssert.assertEquals(TotalAmount, sum, "Total Amount Mismatch ");

            if (matcher.find()) {
                PONumber = matcher.group(1);

            }
            setPONumber(PONumber);
            System.out.println("Purchase Order Number: " + PONumber);
            if (1==1) {
                // Define requisition type and requisition number
                String PO = "PO";

                // Construct the file path dynamically
                String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + PO + ".xlsx"; // File name remains constant for adding new sheets

                // Ensure the directory exists
                File file = new File(filePath);
                file.getParentFile().mkdirs();

                Workbook workbook;

                // Check if file exists
                if (file.exists()) {
                    try (FileInputStream fis = new FileInputStream(file)) {
                        workbook = new XSSFWorkbook(fis);
                    }
                } else {
                    workbook = new XSSFWorkbook();
                }

                // Create a unique sheet name
                String sheetName = "PO_" + PONumber;

                // Check if the sheet already exists
                if (workbook.getSheet(sheetName) != null) {
                    System.out.println("Sheet with name '" + sheetName + "' already exists. Skipping sheet creation.");
                    return;
                }

                // Create a new sheet
                Sheet sheet = workbook.createSheet(sheetName);

                // First row: Header row for requisition details
                Row requisitionHeaderRow = sheet.createRow(0);
                requisitionHeaderRow.createCell(0).setCellValue("PO Number");
                requisitionHeaderRow.createCell(1).setCellValue("PO Type");
                requisitionHeaderRow.createCell(2).setCellValue("Date");
                requisitionHeaderRow.createCell(3).setCellValue("Vendor");
                requisitionHeaderRow.createCell(5).setCellValue("Total Amount");
                requisitionHeaderRow.createCell(6).setCellValue("Final Amount");
                requisitionHeaderRow.createCell(7).setCellValue("Total Item");

                // Second row: Values for requisition details
                Row requisitionDataRow = sheet.createRow(1);
                requisitionDataRow.createCell(0).setCellValue(String.valueOf(PONumber));
                //   requisitionDataRow.createCell(1).setCellValue(getPOTypeValue);
                requisitionDataRow.createCell(2).setCellValue(Dateformat.get_SystemDate());
                // requisitionDataRow.createCell(3).setCellValue(vendor);
                requisitionDataRow.createCell(5).setCellValue(TotalAmount);
                requisitionDataRow.createCell(6).setCellValue(FinalAmount);
                requisitionDataRow.createCell(7).setCellValue(TotalItems);


                // Fourth row: Header for item and quantity
                Row itemHeaderRow = sheet.createRow(3);

                itemHeaderRow.createCell(0).setCellValue("Item");
                itemHeaderRow.createCell(1).setCellValue("Name");
                itemHeaderRow.createCell(2).setCellValue("Quantity");
                itemHeaderRow.createCell(3).setCellValue("Rate");

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

                // Populate item and quantity data starting from the fifth row
                int rowIndex = 4;  // Starting row index for items and quantities
                int itemCount = Math.min(Items.size(), Quantity.size());  // To handle if lists are of unequal length
                for (int i = 0; i < itemCount; i++) {
                    Row itemDataRow = sheet.createRow(rowIndex++);
                    itemDataRow.createCell(0).setCellValue(Items.get(i).getAttribute("value")); // Get item name from WebElement
                    itemDataRow.createCell(1).setCellValue(ItemName.get(i).getAttribute("value"));
                    itemDataRow.createCell(2).setCellValue(Quantity.get(i).getAttribute("value"));
                    itemDataRow.createCell(3).setCellValue(Rate.get(i).getAttribute("value"));

                }

                // Row itemDataRow = sheet.createRow(10)

                // Write the updated workbook back to the Excel file
                try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                    workbook.write(fileOut);
                }

                // Close the workbook
                workbook.close();
                System.out.println("Sheet '" + sheetName + "' added successfully in Excel file at: " + filePath);
                //softAssert.assertAll();


            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void SaveDetailsinExcel() {

        try {
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
//            String vendor = Vendor.getText();
//            String getPOTypeValue = getPOType.getText();


            ///  Calculate the total amount
            double sum = 0.0;
            for (WebElement amountElement : TotalAmount) {
                try {
                    // Get the text or value of the input field
                    String amountText = amountElement.getAttribute("value");

                    // Convert it to a double and add to the sum
                    if (amountText != null && !amountText.isEmpty()) {
                        sum += Double.parseDouble(amountText.trim());
                    }
                    //System.out.println("Sum is :"+sum);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format in element: " + amountElement.getAttribute("id"));
                }
            }
            System.out.println(sum);


//            Utilities.Click(BaseTest.getDriver(), SaveBtn);
//            try {
//                wait.until(ExpectedConditions.alertIsPresent()).accept();
//            } catch (Exception e) {
//
//
//            }
//            try {
//                wait.until(ExpectedConditions.alertIsPresent()).accept();
//            } catch (Exception e) {
//
//            }
            BaseTest.getDriver().switchTo().defaultContent();

            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), PODialougue);
            String PoDialouge = PODialougue.getText();
            if (PoDialouge.contains("Purchase Order Number")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.PASS, "PO Created successfully with ", true);

            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.FAIL, "Exception found in Method - PO Creation", true);

            }
            //Getting All resoureces from Testdata excel sheet
            //  String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "TestData.xlsx";


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            if (ProductConfirmation.isDisplayed() && ProductConfirmation.getText().contains("Purchase Order Number")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Purchase Order Number Confirmation ", Status.PASS, "Purchase Order Number successfully generated", true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Purchase Order NumberConfirmation", Status.FAIL, "Issue in Purchase Order Number", true);
            }

            Pattern pattern = Pattern.compile("Purchase Order Number\\s(\\d+)");
            String input = ProductConfirmation.getText();
            Matcher matcher = pattern.matcher(input);


// Print input for debugging
            System.out.println("Input: " + input);

// Regular expressions to match specific fields
            Pattern totalAmountPattern = Pattern.compile("Total Amount\\(incl\\. charge\\)\\s*([0-9.]+)");
            Pattern finalAmountPattern = Pattern.compile("Final Amount\\(incl\\. tax\\)\\s*([0-9.]+)");
            Pattern totalItemsPattern = Pattern.compile("Total Items\\s*(\\d+)");
            Pattern purchaseOrderPattern = Pattern.compile("Purchase Order Number\\s*(\\d+)");

// Matchers for each field
            Matcher totalAmountMatcher = totalAmountPattern.matcher(input);
            Matcher finalAmountMatcher = finalAmountPattern.matcher(input);
            Matcher totalItemsMatcher = totalItemsPattern.matcher(input);
            Matcher purchaseOrderMatcher = purchaseOrderPattern.matcher(input);

// Extract values if they match
            String TotalAmount = totalAmountMatcher.find() ? totalAmountMatcher.group(1) : "Not Found";
            String FinalAmount = finalAmountMatcher.find() ? finalAmountMatcher.group(1) : "Not Found";
            String TotalItems = totalItemsMatcher.find() ? totalItemsMatcher.group(1) : "Not Found";
            String PurchaseOrderNumber = purchaseOrderMatcher.find() ? purchaseOrderMatcher.group(1) : "Not Found";

// Print the extracted values
            System.out.println("Total Amount: " + TotalAmount);
            System.out.println("Final Amount: " + FinalAmount);
            System.out.println("Total Items: " + TotalItems);
            System.out.println("Purchase Order Number: " + PurchaseOrderNumber);


            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(TotalAmount, sum, "Total Amount Mismatch ");

            if (matcher.find()) {
                PONumber = matcher.group(1);

            }
            setPONumber(PONumber);
            System.out.println("Purchase Order Number: " + PONumber);
            if (ProductConfirmation.isDisplayed()) {
                // Define requisition type and requisition number
                String PO = "PO";

                // Construct the file path dynamically
                String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + PO + ".xlsx"; // File name remains constant for adding new sheets

                // Ensure the directory exists
                File file = new File(filePath);
                file.getParentFile().mkdirs();

                Workbook workbook;

                // Check if file exists
                if (file.exists()) {
                    try (FileInputStream fis = new FileInputStream(file)) {
                        workbook = new XSSFWorkbook(fis);
                    }
                } else {
                    workbook = new XSSFWorkbook();
                }

                // Create a unique sheet name
                String sheetName = "PO_" + PONumber;

                // Check if the sheet already exists
                if (workbook.getSheet(sheetName) != null) {
                    System.out.println("Sheet with name '" + sheetName + "' already exists. Skipping sheet creation.");
                    return;
                }

                // Create a new sheet
                Sheet sheet = workbook.createSheet(sheetName);

                // First row: Header row for requisition details
                Row requisitionHeaderRow = sheet.createRow(0);
                requisitionHeaderRow.createCell(0).setCellValue("PO Number");
                requisitionHeaderRow.createCell(1).setCellValue("PO Type");
                requisitionHeaderRow.createCell(2).setCellValue("Date");
                requisitionHeaderRow.createCell(3).setCellValue("Vendor");
                requisitionHeaderRow.createCell(5).setCellValue("Total Amount");
                requisitionHeaderRow.createCell(6).setCellValue("Final Amount");
                requisitionHeaderRow.createCell(7).setCellValue("Total Item");

                // Second row: Values for requisition details
                Row requisitionDataRow = sheet.createRow(1);
                requisitionDataRow.createCell(0).setCellValue(String.valueOf(PONumber));
                //   requisitionDataRow.createCell(1).setCellValue(getPOTypeValue);
                requisitionDataRow.createCell(2).setCellValue(Dateformat.get_SystemDate());
                // requisitionDataRow.createCell(3).setCellValue(vendor);
                requisitionDataRow.createCell(5).setCellValue(TotalAmount);
                requisitionDataRow.createCell(6).setCellValue(FinalAmount);
                requisitionDataRow.createCell(7).setCellValue(TotalItems);


                // Fourth row: Header for item and quantity
                Row itemHeaderRow = sheet.createRow(3);

                itemHeaderRow.createCell(0).setCellValue("Item");
                itemHeaderRow.createCell(1).setCellValue("Name");
                itemHeaderRow.createCell(2).setCellValue("Quantity");
                itemHeaderRow.createCell(3).setCellValue("Rate");

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

                // Populate item and quantity data starting from the fifth row
                int rowIndex = 4;  // Starting row index for items and quantities
                int itemCount = Math.min(Rate.size(), Quantity.size());  // To handle if lists are of unequal length


                //int itemCoun = Math.min(Quantity.size());
                for (int i = 0; i < itemCount; i++) {
                    Row itemDataRow = sheet.createRow(rowIndex++);
                    itemDataRow.createCell(0).setCellValue(getItemSearchElement(i).getAttribute("value")); // Get item name from WebElement
                    itemDataRow.createCell(1).setCellValue(ItemName.get(i).getAttribute("value"));
                    itemDataRow.createCell(2).setCellValue(Quantity.get(i).getAttribute("value"));
                    itemDataRow.createCell(3).setCellValue(Rate.get(i).getAttribute("value"));

                }

                // Row itemDataRow = sheet.createRow(10)

                // Write the updated workbook back to the Excel file
                try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                    workbook.write(fileOut);
                }

                // Close the workbook
                workbook.close();
                System.out.println("Sheet '" + sheetName + "' added successfully in Excel file at: " + filePath);
                //softAssert.assertAll();
            }
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);
        }
    }


}




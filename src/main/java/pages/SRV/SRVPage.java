package pages.SRV;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import datahandlers.ExcelHandler;
import extentreports.ExtentTestManager;
import logs.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.POPages.PO;
import utils.DynamicWait;
import utils.Utilities;

import javax.mail.Transport;
import javax.xml.xpath.XPath;
import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.BaseStream;

public class SRVPage {

    private static final int counter = 1;
    @FindBy(xpath = "//*[starts-with(@id, 'img_item_tax_class_16_')]")
    List<WebElement> IGSTImg;
    @FindBy(xpath = "//*[starts-with(@id, 'item_vat_class_23_')]")
    List<WebElement> itemVatClassElements;
    @FindBy(xpath = "//*[starts-with(@id, 'qty_returned_5_')]")
    List<WebElement> QtyReturned;
    @FindBy(xpath = "//*[text()='Store Receipt from Vendor']")
    private WebElement SRV;
    @FindBy(xpath = "//a[@id='LinkProduct2']")
    private WebElement Inventory;
    @FindBy(id = "divmodule_2")
    private WebElement InventoryModule;
    @FindBy(id = "cphBrowserHeader_btnAddNew")
    private WebElement AddBtn;
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
    @FindBy(id = "cphBody_txtVendor")
    private WebElement Search_vendor;
    @FindBy(id = "cphBody_btnSearch")
    private WebElement EnterItemDetails;
    @FindBy(xpath = "//td[@class=\"ControlItemStyle\"][1]")
    private List<WebElement> Item;
    @FindBy(xpath = "//input[@id='col_0']")
    private WebElement ItemFilter;

    //    @FindBy(css = "[id='img_item_vat_class_23_']")
//    private List<WebElement> VAT;
    @FindBy(id = "item_id_1_0")
    private WebElement SelectItem;
    @FindBy(id = "cphBody_btnAddRow_Top")
    private WebElement AddRow;
    @FindBy(id = "cphDialogHeader_btnSaveClose")
    private WebElement SaveBtn;
    @FindBy(css = "[id^='img_item_vat_class_23_']")
    private List<WebElement> VAT;
    @FindBy(id = "cphBody_lblbilldetails")
    private WebElement BillDetails;
    @FindBy(id = "txtBillNumber")
    private WebElement BillNumber;
    @FindBy(id = "txtbillentrydate")
    private WebElement BillEntryDate;
    @FindBy(id = "txtportcode")
    private WebElement ProtCode;
    @FindBy(id = "btnOk")
    private WebElement OKBtn;
    @FindBy(id = "cphBody_lbltransportdetails")
    private WebElement TransportDetails;
    @FindBy(id = "txtTransporterName")
    private WebElement TransporterName;
    @FindBy(id = "txtVehicleNumber")
    private WebElement VehicalNO;
    @FindBy(id = "txtVehicleName")
    private WebElement VehicalName;
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
    @FindBy(xpath = "//*[starts-with(@id, 'qty_recvd_6_')]")
    private WebElement quantity;
    @FindBy(xpath = "//*[starts-with(@id, 'qty_recvd_6_')]")
    private List<WebElement> quantitys;
    @FindBy(xpath = "//*[starts-with(@id, 'vatable_amount_15_')]")
    private WebElement Amount;
    @FindBy(xpath = "//*[starts-with(@id, 'item_id_0_')]")
    private WebElement EditItem;
    @FindBy(id = "td_0_0")
    private WebElement SelectTAX;
    @FindAll({@FindBy(xpath = "//*[starts-with(@id, 'img_default_dept_id_11_')]")})
    private List<WebElement> Departments;
    @FindBy(id = "td_0_0")
    private WebElement SelectDept;
    @FindBy(id = "cphHeader_lnkHome")
    private WebElement HomePage;
    @FindBy(id = "cphBody_txtPONumber")
    private WebElement PONumber;
    @FindBy(id = "cphBody_txtBillNumber")
    private WebElement SupplierInvoice;
    @FindBy(id = "cphBody_txtBillDate")
    private WebElement Date;
    @FindBy(id = "cphBody_chkDirectIssue")
    private WebElement DirectIssue;
    @FindBy(id = "cphBody_txtDocDate")
    private WebElement DocDate;
    @FindBy(id = "lblErrorDesc")
    private WebElement ConfDilouge;
    @FindBy(id = "cphBody_chkCashPurchase")
    private WebElement CashPurchase;
    @FindBy(id = "cphBody_txtDocNumber")
    private WebElement SRVNO;
    @FindBy(id = "cphBody_btnConfirmToAP")
    private WebElement ConfirmtoAP;
    @FindBy(id = "cphDialogHeader_btnPrint")
    private WebElement Print;
    @FindBy(id = "rdoFinal")
    private WebElement rdFinal;
    @FindBy(id = "chkSendEmail")
    private WebElement Sendmail;
    @FindBy(id = "cphDialogHeader_btnClose_Dlg")
    private WebElement CloseBtn;
    @FindBy(id = "cphBody_txtRVNumber")
    private WebElement EnterSRV;
    @FindBy(id = "cphBody_lblCreateModifyVendorReturn")
    private WebElement CreateVendorReturn;
    @FindBy(id = "cphDialogHeader_btnSave")
    private WebElement Save;
    @FindBy(id = "cphBody_txtRemarks")
    private WebElement Remark;
    @FindBy(id = "cphBody_txtPurchaseOrderNumber")
    private WebElement PurchaseOrder;
    @FindBy(id = "item_id_0_0")
    private WebElement TaxItem;
    @FindBy(id = "cphBody_lblVATDetail")
    private WebElement TaxDetail;
    @FindBy(id = "cphBody_btnConfirmToAP")
    private WebElement confirmToAP;

    public SRVPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement VAT(int itemId) {
        String xpath = "//input[@id='img_item_vat_class_23_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath));
    }

    public WebElement getItemSearchElement(int itemId) {
        String xpath = "//input[@id='item_id_0_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath));
    }

    public WebElement Justification(int itemId) {
        String xpath3 = "//input[@id='justification_18_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    public WebElement getrequestQuantityelement(int itemId) {
        String xpath3 = "//input[@id='qty_recvd_6_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    public WebElement getItemRate(int itemId) {
        String xpath3 = "//input[@id='vatable_amount_15_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    public WebElement Department(int itemId) {
        String xpath3 = "//*[@id='img_default_dept_id_11_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    public WebElement Dept(int itemId) {
        String xpath3 = "//input[@id='default_dept_id_11_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    public WebElement SelectDept(int itemId) {
        String xpath3 = "//td[@id='td_" + itemId + "_0']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    public void CreateBackDateSRV() {
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
            if (InventoryModule.isDisplayed() && InventoryModule.isEnabled()) {
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
                Utilities.Click(BaseTest.getDriver(), InventoryModule);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Inventory Button", Status.FAIL, "Inventory Button is not Displayed or not Enabled", true);
            }
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Click on SRV
            Utilities.Click(SRV);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(AddBtn);


//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
//
//            Utilities.SendKeys(BaseTest.getDriver(), Vendor, "%%");
//
//
            Actions actions = new Actions(BaseTest.getDriver());
//            actions.sendKeys(Keys.TAB).perform();
//
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
//
//
//            Random random = new Random();
//            System.out.println(VendorList);
//            int randomIndex = random.nextInt(VendorList.size());
//            // Click on the random department
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
//            String vendorlist = VendorList.get(randomIndex).getText();
//            Utilities.SendKeys(BaseTest.getDriver(), Filter, vendorlist);
//            DynamicWait.longWait();
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.DoubleClick(Cancel);
//
//            try {
//                BaseTest.getDriver().switchTo().defaultContent();
//                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//                Utilities.Click(BaseTest.getDriver(), Close);
//            } catch (Exception e) {
//                System.out.println("No Close Button");
//            }
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            Utilities.SendKeys(BaseTest.getDriver(), Search_vendor, vendorlist);
//
//            Utilities.SendKeys(DocDate, "20/01/2025");


            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddmm");
            String timestamp = LocalDateTime.now().format(dtf);


            // Enter the Supplier No
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(SupplierInvoice, "Supplier" + timestamp);
            Utilities.SendKeys(Date, "20/01/2025");

            ///
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            Utilities.SendKeys(BaseTest.getDriver(), Vendor, "%%");


            ///


            //Click Enter Items Details
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);

            Utilities.SendKeys(TaxItem, "GS000319");

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            try {
                wait.until(ExpectedConditions.alertIsPresent()).accept();

            } catch (Exception e) {

            }
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getrequestQuantityelement(0));
            getrequestQuantityelement(0).click();
            actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("10").perform();


            // Enter Rate
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getItemRate(0));
            getItemRate(0).click();

            float ItemRate = Float.parseFloat((getItemRate(0).getAttribute("value")));
            if (ItemRate > 0) {
                // actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("100").perform();
            } else {
                actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("10").perform();

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


            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

            WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));


            OKBtn.click();


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("SRV Creation", Status.FAIL, "Error in Creation SRV", true, e);
        }
    }

    public void DirectIssue() {
        try {

            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            Utilities.Click(DirectIssue);

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
            ExtentTestManager.createAssertTestStepWithScreenshot("Attachment ", Status.FAIL, "Error in Adding Attachment ", true, e);
        }
    }

    public void POSRV() {
        try {

            try {
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                Utilities.Click(HomePage);
            } catch (Exception e) {

            }


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


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Click on SRV
            Utilities.Click(SRV);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(AddBtn);


            //Enter PO Number


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "POData.xlsx";

            ExcelHandler.loadExcelFile(Updload_Path, 0);
            List<String> Resources1 = ExcelHandler.getAllColumnData("RQ Number");

            //for(int i=0;i<Resources1.size();i++) {
            String resource1 = Resources1.get(Resources1.size() - 1);
            System.out.println(resource1);
            Utilities.SendKeys(BaseTest.getDriver(), PONumber, resource1);


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);


            Actions actions = new Actions(BaseTest.getDriver());

            for (WebElement quantity : quantitys) {
                quantity.click();
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("10").perform();

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
            ExtentTestManager.createAssertTestStepWithScreenshot("POSRV", Status.FAIL, "Error in Adding Attachment ", true, e);
        }
    }

    public void POSRV2() {
        try {

            try {
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                Utilities.Click(HomePage);
            } catch (Exception e) {

            }


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
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
            Utilities.Click(BaseTest.getDriver(), InventoryModule);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Click on SRV
            Utilities.Click(SRV);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(AddBtn);


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "POData.xlsx";

            ExcelHandler.loadExcelFile(Updload_Path, 0);
            List<String> Resources1 = ExcelHandler.getAllColumnData("RQ Number");

            //for(int i=0;i<Resources1.size();i++) {
            String resource1 = Resources1.get(Resources1.size() - 1);
            System.out.println(resource1);
            Utilities.SendKeys(BaseTest.getDriver(), PONumber, resource1);


            // Select the Vendor

//            Utilities.SendKeys(BaseTest.getDriver(), Vendor, "%%");
//            //Utilities.Click(BaseTest.getDriver(), VendorImg);
//
//            Actions actions = new Actions(BaseTest.getDriver());
//            actions.sendKeys(Keys.TAB).perform();
//
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
//
//
//            Random random = new Random();
//            System.out.println(VendorList);
//            int randomIndex = random.nextInt(VendorList.size());
//            // Click on the random department
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
//            String vendorlist = VendorList.get(randomIndex).getText();
//            Utilities.SendKeys(BaseTest.getDriver(), Filter, vendorlist);
//            DynamicWait.longWait();
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.DoubleClick(Cancel);
//
//            try {
//                BaseTest.getDriver().switchTo().defaultContent();
//                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//                Utilities.Click(BaseTest.getDriver(), Close);
//            } catch (Exception e) {
//                System.out.println("No Close Button");
//            }
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            Utilities.SendKeys(BaseTest.getDriver(), Search_vendor, vendorlist);


            // Click Attachment
//            Utilities.Click(Attechment);
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            BaseTest.getDriver().switchTo().frame("iframeAttachFileDialog");
//
//            Utilities.SendKeys(ChoseFile, path);
//            Utilities.Click(Upload);


            // Enter the Supplier No

            /// ///////

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddmm");
            String timestamp = LocalDateTime.now().format(dtf);


            // Enter the Supplier No
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(SupplierInvoice, "Supplier" + timestamp);

            /// //

//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            Utilities.SendKeys(SupplierInvoice, "Supplier");
            //Utilities.SendKeys(Date,"15/09/2024");

            Actions actions = new Actions(BaseTest.getDriver());
            Utilities.Click(DocDate);
            actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
            actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
            //Utilities.SendKeys(SupplierInvoice, "Supplier");
            //Utilities.SendKeys(Date,"14/09/2024");
            Utilities.Click(Date);
            actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();


//            Utilities.Click(Date);
//            actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BillDetails);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.SendKeys(BillNumber, "369");
            Utilities.SendKeys(ProtCode, "999");
            Utilities.SendKeys(BillEntryDate, "12/09/2024");
            OKBtn.click();

//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            Utilities.Click(TransportDetails);
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.SendKeys(TransporterName,"ABC Group");
//            Utilities.SendKeys(VehicalNO,"UP15EQ5442");
//            Utilities.SendKeys(VehicalName,"Swift");
//            OKBtn.click();


            //Click Enter Items Details
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("SRV Creation", Status.FAIL, "Error in Creation SRV", true, e);
        }
    }

    public void EnterQty() {
        try {

            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            Actions actions = new Actions(BaseTest.getDriver());

            for (WebElement quantity : quantitys) {
                quantity.click();
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("10").perform();

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

            try {
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

                WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));

                try {
                    OKBtn.click();
                    System.out.println("OK Button clicked successfully! ::");
                } catch (Exception e) {
                    System.out.println("Normal click failed, trying JavaScript click.");
                    ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].click();", OKBtn);
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(confirmToAP);

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
            if (InventoryModule.isDisplayed() && InventoryModule.isEnabled()) {
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
                Utilities.Click(BaseTest.getDriver(), InventoryModule);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Inventory Button", Status.FAIL, "Inventory Button is not Displayed or not Enabled", true);
            }
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Click on SRV
            Utilities.Click(SRV);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(AddBtn);


            // Select the Vendor

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            if (Vendor.isEnabled() && Vendor.isDisplayed()) {
                Utilities.SendKeys(BaseTest.getDriver(), Vendor, "%%");
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Vendor", Status.FAIL, "Error Sending vendor is not intrectable", true);
            }
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

            WebElement selectedVendor = VendorList.get(randomIndex);

            Utilities.DoubleClick(selectedVendor);

//            Utilities.SendKeys(BaseTest.getDriver(), Filter, vendorlist);
//            DynamicWait.longWait();
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.DoubleClick(Cancel);

//            try {
//                BaseTest.getDriver().switchTo().defaultContent();
//                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//                Utilities.Click(BaseTest.getDriver(), Close);
//            } catch (Exception e) {
//                System.out.println("No Close Button");
//            }
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            Utilities.SendKeys(BaseTest.getDriver(), Search_vendor, vendorlist);


            // Click Attachment
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


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BillDetails);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.SendKeys(BillNumber, "369");
            Utilities.SendKeys(ProtCode, "999");
            Utilities.SendKeys(BillEntryDate, "12/09/2024");
            OKBtn.click();

//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            Utilities.Click(TransportDetails);
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.SendKeys(TransporterName,"ABC Group");
//            Utilities.SendKeys(VehicalNO,"UP15EQ5442");
//            Utilities.SendKeys(VehicalName,"Swift");
//            OKBtn.click();


            //Click Enter Items Details
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("SRV Creation", Status.FAIL, "Error in Creation SRV", true, e);
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


                Utilities.SendKeys(BaseTest.getDriver(), getItemSearchElement(i), "WIRE");
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

            SRVPage ob = new SRVPage(BaseTest.getDriver());
            // Call the Direct issue method
            ob.DirectIssue();
            ob.SelectDepartment();

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

    public void Edit() {
        try {
            //Alert alert = BaseTest.getDriver().switchTo().alert();

            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

//            try {
//                wait.until(ExpectedConditions.alertIsPresent()).accept();
//            } catch (Exception e) {
//
//            }
//
//            try {
//                wait.until(ExpectedConditions.alertIsPresent()).accept();
//            } catch (Exception e) {
//
//            }

            for (int i = 0; i < 1; i++) {
//                try {
//                    wait.until(ExpectedConditions.alertIsPresent()).accept();
//                } catch (Exception e) {
//
//                }
//                try {
//                    wait.until(ExpectedConditions.alertIsPresent()).accept();
//                } catch (Exception e) {

                //  }


                items.get(i).click();
                DeleteRow.click();
                wait.until(ExpectedConditions.alertIsPresent()).accept();
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


            }

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Attachment ", Status.FAIL, "Error in Adding Attachment ", true, e);
        }
    }

    public void AddRow() {
        try {

            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));

            Random random = new Random();
            // WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
            Actions actions = new Actions(BaseTest.getDriver());
            Set<String> usedTexts = new HashSet<>();

            for (int i = 0; i < 1; i++) {

                Utilities.Click(BaseTest.getDriver(), AddRow);

                EditItem.sendKeys("%%");
                EditItem.sendKeys(Keys.TAB);

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


                wait.until(ExpectedConditions.elementToBeClickable(quantity));
                quantity.click();


                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("10").perform();


                // Enter Rate
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));


                wait.until(ExpectedConditions.elementToBeClickable(Amount));
                Amount.click();


                actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("125").perform();


                try {
                    Utilities.SendKeys(BaseTest.getDriver(), Justification(i), "Testing");
                } catch (Exception e) {
                }


            }
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Attachment ", Status.FAIL, "Error in Adding Attachment ", true, e);
        }
    }

    public void AddTax() {
        try {

            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            // for (int i = 0; i < VAT.size(); i++) {
            //  wait.until(ExpectedConditions.elementToBeClickable(VAT.get(i)));
            //VAT.get(i).click();
            // Utilities.Click(VAT.get(i));

            for (WebElement vat : VAT) {
                //vat.click();

                Utilities.Click(vat);

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                Utilities.DoubleClick(SelectTAX);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            }


            //


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
            ExtentTestManager.createAssertTestStepWithScreenshot("AddTax", Status.FAIL, "Error in Adding TAX", true, e);
        }
    }

    public void SelectDepartment() {
        try {

            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            //for (WebElement department : Departments) {

            //String deptValue = Dept.getText().trim();

//                department.click();
//
//                BaseTest.getDriver().switchTo().defaultContent();
//                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//                Utilities.DoubleClick(SelectDept);
//                try {
//                    wait.until(ExpectedConditions.alertIsPresent()).dismiss();
//                } catch (Exception e) {
//
//                    System.out.println("No alert is present");
//                }
//                BaseTest.getDriver().switchTo().defaultContent();
//                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");


            int size1 = Departments.size();
            for (int i = 0; i < size1; i++) {

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                String Deptvalue = Dept(i).getAttribute("value");
                if (Deptvalue == null || Deptvalue.isEmpty() || Deptvalue.isBlank()) {

                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

                    Utilities.Click(Department(i));
                    System.out.println(" Select the department");
                    //Department(i).click();

                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                    Utilities.DoubleClick(SelectDept);
                    try {
                        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
                    } catch (Exception e) {

                        System.out.println("No alert is present");
                    }


                }

            }

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("SelectDepartment", Status.FAIL, "Error in SelectDepartment", true, e);
        }
    }

    public void Save() {
        try {

            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));


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
            ExtentTestManager.createAssertTestStepWithScreenshot("Save", Status.FAIL, "Error in Save", true, e);
        }
    }

    public void POSRVCash() {
        try {

            try {
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                Utilities.Click(HomePage);
            } catch (Exception e) {

            }


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
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
            Utilities.Click(BaseTest.getDriver(), InventoryModule);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Click on SRV
            Utilities.Click(SRV);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(AddBtn);


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "POData.xlsx";

            ExcelHandler.loadExcelFile(Updload_Path, 0);
            List<String> Resources1 = ExcelHandler.getAllColumnData("RQ Number");

            //for(int i=0;i<Resources1.size();i++) {
            String resource1 = Resources1.get(Resources1.size() - 1);
            System.out.println(resource1);


            Utilities.Click(CashPurchase);


            // Select the Vendor

//            Utilities.SendKeys(BaseTest.getDriver(), Vendor, "%%");
//            //Utilities.Click(BaseTest.getDriver(), VendorImg);
//
//            Actions actions = new Actions(BaseTest.getDriver());
//            actions.sendKeys(Keys.TAB).perform();
//
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
//
//
//            Random random = new Random();
//            System.out.println(VendorList);
//            int randomIndex = random.nextInt(VendorList.size());
//            // Click on the random department
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
//            String vendorlist = VendorList.get(randomIndex).getText();
//            Utilities.SendKeys(BaseTest.getDriver(), Filter, vendorlist);
//            DynamicWait.longWait();
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.DoubleClick(Cancel);
//
//            try {
//                BaseTest.getDriver().switchTo().defaultContent();
//                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//                Utilities.Click(BaseTest.getDriver(), Close);
//            } catch (Exception e) {
//                System.out.println("No Close Button");
//            }
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            Utilities.SendKeys(BaseTest.getDriver(), Search_vendor, vendorlist);


            // Click Attachment
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
            Utilities.SendKeys(SupplierInvoice, "Supplier");
            //Utilities.SendKeys(Date,"15/09/2024");

            Actions actions = new Actions(BaseTest.getDriver());
            Utilities.Click(DocDate);
            actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
            actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
            Utilities.SendKeys(SupplierInvoice, "Supplier");
            //Utilities.SendKeys(Date,"14/09/2024");
            Utilities.Click(Date);
            actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();


//            Utilities.Click(Date);
//            actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();


//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            Utilities.Click(BillDetails);
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.SendKeys(BillNumber, "369");
//            Utilities.SendKeys(ProtCode, "999");
//            Utilities.SendKeys(BillEntryDate, "12/09/2024");
//            OKBtn.click();


            // WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(),Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            Utilities.SendKeys(BaseTest.getDriver(), PONumber, resource1);

            //Utilities.SendKeys(BaseTest.getDriver(), PONumber, resource1);


            //Utilities.Click(DirectIssue);


//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            Utilities.Click(TransportDetails);
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.SendKeys(TransporterName,"ABC Group");
//            Utilities.SendKeys(VehicalNO,"UP15EQ5442");
//            Utilities.SendKeys(VehicalName,"Swift");
//            OKBtn.click();


            //Click Enter Items Details
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);

            try {
                // Utilities.Click(BaseTest.getDriver(), EnterItemDetails);
            } catch (Exception e) {

            }


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("SRV Creation", Status.FAIL, "Error in Creation SRV", true, e);
        }
    }

    public void CashRecieptItems() {

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


                Utilities.SendKeys(BaseTest.getDriver(), getItemSearchElement(i), "WIRE");
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

            SRVPage ob = new SRVPage(BaseTest.getDriver());
            // Call the Direct issue method
            //ob.DirectIssue();
            ob.CashReciept();

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

    public void CashReciept() {
        try {

            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            //Utilities.Click(DirectIssue);

            Utilities.Click(CashPurchase);
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
            ExtentTestManager.createAssertTestStepWithScreenshot("Attachment ", Status.FAIL, "Error in Adding Attachment ", true, e);
        }
    }

    public void POSRVDirectIssue() {
        try {

            try {
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                Utilities.Click(HomePage);
            } catch (Exception e) {

            }


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
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
            Utilities.Click(BaseTest.getDriver(), InventoryModule);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Click on SRV
            Utilities.Click(SRV);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(AddBtn);


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "POData.xlsx";

            ExcelHandler.loadExcelFile(Updload_Path, 0);
            List<String> Resources1 = ExcelHandler.getAllColumnData("RQ Number");

            //for(int i=0;i<Resources1.size();i++) {
            String resource1 = Resources1.get(Resources1.size() - 1);
            System.out.println(resource1);


            Utilities.Click(DirectIssue);


            // Select the Vendor

//            Utilities.SendKeys(BaseTest.getDriver(), Vendor, "%%");
//            //Utilities.Click(BaseTest.getDriver(), VendorImg);
//
//            Actions actions = new Actions(BaseTest.getDriver());
//            actions.sendKeys(Keys.TAB).perform();
//
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
//
//
//            Random random = new Random();
//            System.out.println(VendorList);
//            int randomIndex = random.nextInt(VendorList.size());
//            // Click on the random department
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
//            String vendorlist = VendorList.get(randomIndex).getText();
//            Utilities.SendKeys(BaseTest.getDriver(), Filter, vendorlist);
//            DynamicWait.longWait();
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.DoubleClick(Cancel);
//
//            try {
//                BaseTest.getDriver().switchTo().defaultContent();
//                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//                Utilities.Click(BaseTest.getDriver(), Close);
//            } catch (Exception e) {
//                System.out.println("No Close Button");
//            }
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            Utilities.SendKeys(BaseTest.getDriver(), Search_vendor, vendorlist);


            // Click Attachment
//            Utilities.Click(Attechment);
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            BaseTest.getDriver().switchTo().frame("iframeAttachFileDialog");
//
//            Utilities.SendKeys(ChoseFile, path);
//            Utilities.Click(Upload);


            // Enter the Supplier No
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            Utilities.SendKeys(SupplierInvoice,"1122");
//            //Utilities.SendKeys(Date,"15/09/2024");
//
//            Actions actions = new Actions(BaseTest.getDriver());
//            Utilities.Click(DocDate);
//            actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
//            actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
//            Utilities.SendKeys(SupplierInvoice,"1122");
//            //Utilities.SendKeys(Date,"14/09/2024");
//            Utilities.Click(Date);
//            actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();


//            Utilities.Click(Date);
//            actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();


//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            Utilities.Click(BillDetails);
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.SendKeys(BillNumber, "369");
//            Utilities.SendKeys(ProtCode, "999");
//            Utilities.SendKeys(BillEntryDate, "12/09/2024");
//            OKBtn.click();


            // WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(),Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            Utilities.SendKeys(BaseTest.getDriver(), PONumber, resource1);


//            SRVPage ob = new SRVPage(BaseTest.getDriver());
//            ob.DirectIssue();


            //Utilities.SendKeys(BaseTest.getDriver(), PONumber, resource1);


            //Utilities.Click(DirectIssue);


//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            Utilities.Click(TransportDetails);
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.SendKeys(TransporterName,"ABC Group");
//            Utilities.SendKeys(VehicalNO,"UP15EQ5442");
//            Utilities.SendKeys(VehicalName,"Swift");
//            OKBtn.click();


            //Click Enter Items Details
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);
            try {
                Utilities.Click(BaseTest.getDriver(), EnterItemDetails);
            } catch (Exception e) {
                System.out.println("Second Click on Search Button");
            }


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("SRV Creation", Status.FAIL, "Error in Creation SRV", true, e);
        }
    }

    public void AddAttechment() {
        try {

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Attachment ", Status.FAIL, "Error in Adding Attachment ", true, e);
        }
    }

    public void EditQty() {

        try {

            try {
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                Utilities.Click(HomePage);
            } catch (Exception e) {

            }


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


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Click on SRV
            Utilities.Click(SRV);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(AddBtn);


            //Enter PO Number


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "POData.xlsx";

            ExcelHandler.loadExcelFile(Updload_Path, 0);
            List<String> Resources1 = ExcelHandler.getAllColumnData("RQ Number");

            //for(int i=0;i<Resources1.size();i++) {
            String resource1 = Resources1.get(Resources1.size() - 1);
            System.out.println(resource1);
            Utilities.SendKeys(BaseTest.getDriver(), PONumber, resource1);


            // int counter = 1;


            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddmm");
            String timestamp = LocalDateTime.now().format(dtf);


            // Enter the Supplier No
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(SupplierInvoice, "Supplier" + timestamp);
            //Utilities.SendKeys(SupplierInvoice, "Supplier");
            //Utilities.SendKeys(Date,"15/09/2024");

            Actions actions = new Actions(BaseTest.getDriver());
            Utilities.Click(DocDate);
            actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
            actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
            Utilities.SendKeys(SupplierInvoice, "Supplier" + timestamp);

            //Utilities.SendKeys(SupplierInvoice, "Supplier");
            //Utilities.SendKeys(Date,"14/09/2024");
            Utilities.Click(Date);
            actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);
            for (WebElement quantity : quantitys) {
                quantity.click();
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("10").perform();

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


            try {
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

                WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));

                try {
                    OKBtn.click();
                    System.out.println("OK Button clicked successfully! ::");
                } catch (Exception e) {
                    System.out.println("Normal click failed, trying JavaScript click.");
                    ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].click();", OKBtn);
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }


            /// ///////////////////////////////////////  //////////////


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            String SrvNo = SRVNO.getAttribute("value");
            System.out.println("SRV NO is:" + SrvNo);

            //String Updload_Path1 = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "POData.xlsx";

            String Updload_Path1 = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "POData.xlsx";


            ExcelHandler.loadExcelFile(Updload_Path1, 1);

            // Find the first empty row in the Excel sheet
            int rowCount = ExcelHandler.getRowCount();

            // Set the "Requisition Number" in the next available row
            ExcelHandler.setCellData("SRV Number", SrvNo, rowCount);

            ExcelHandler.saveExcelFileCommon(Updload_Path1);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("POSRV", Status.FAIL, "Error in Adding Attechment", true, e);
        }
    }

    public void EnterItemsDI() {

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

            SRVPage ob = new SRVPage(BaseTest.getDriver());
            // Call the Direct issue method
            ob.DirectIssue();
            ob.SelectDepartment1();

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

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            Utilities.Click(ConfirmtoAP);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Items", Status.FAIL, "Exception found in Method - Items", true, e);
        }
    }

    public void SelectDepartment1() {
        try {

            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");


            int size1 = Departments.size();
            for (int i = 0; i < size1; i++) {

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                String Deptvalue = Dept(i).getAttribute("value");
                if (Deptvalue == null || Deptvalue.isEmpty() || Deptvalue.isBlank()) {

                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

                    Utilities.Click(Department(i));
                    System.out.println(" Select the department");
                    //Department(i).click();

                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                    Utilities.DoubleClick(SelectDept(i));
                    try {
                        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
                    } catch (Exception e) {

                        System.out.println("No alert is present");
                    }


                }

            }

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("SelectDepartment", Status.FAIL, "Error in SelectDepartment", true, e);
        }
    }

    public void SaveSRVToExcel() {
        try {

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            String SrvNo = SRVNO.getAttribute("value");
            System.out.println("SRV NO is:" + SrvNo);

            //String Updload_Path1 = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "POData.xlsx";

            String Updload_Path1 = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "POData.xlsx";


            ExcelHandler.loadExcelFile(Updload_Path1, 1);

            // Find the first empty row in the Excel sheet
            int rowCount = ExcelHandler.getRowCount();

            // Set the "Requisition Number" in the next available row
            ExcelHandler.setCellData("SRV Number", SrvNo, rowCount);

            ExcelHandler.saveExcelFileCommon(Updload_Path1);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("POSRV", Status.FAIL, "Error in Adding Attechment", true, e);
        }

    }

    public void SelectIGST() {
        try {

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));
            OKBtn.click();

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            for (WebElement el : IGSTImg) {

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                wait.until(ExpectedConditions.elementToBeClickable(el));
                Utilities.Click(el);
                DynamicWait.smallWait();
                try {
                    Utilities.Click(el);

                } catch (Exception e) {
                }


                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                Utilities.DoubleClick(SelectTAX);
                DynamicWait.smallWait();

            }


            ///  Click on Save Button
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
            Utilities.Click(BaseTest.getDriver(), SaveBtn);

            try {
                wait.until(ExpectedConditions.alertIsPresent()).accept();
            } catch (Exception e) {
            }
            try {
                wait.until(ExpectedConditions.alertIsPresent()).accept();
            } catch (Exception e) {
            }
            try {
                wait.until(ExpectedConditions.alertIsPresent()).accept();
            } catch (Exception e) {
            }


            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

            WebElement OKBtn1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));
            OKBtn1.click();


            /// Click Print

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
            ExtentTestManager.createAssertTestStepWithScreenshot("IGST", Status.FAIL, "Error while selecting the IGST", true, e);
        }
    }

    public void SRVIGST() {

        try {

            try {
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                Utilities.Click(HomePage);
            } catch (Exception e) {

            }


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


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Click on SRV
            Utilities.Click(SRV);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(AddBtn);


            //Enter PO Number


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "POData.xlsx";

            ExcelHandler.loadExcelFile(Updload_Path, 0);
            List<String> Resources1 = ExcelHandler.getAllColumnData("RQ Number");

            //for(int i=0;i<Resources1.size();i++) {
            String resource1 = Resources1.get(Resources1.size() - 1);
            System.out.println(resource1);
            Utilities.SendKeys(BaseTest.getDriver(), PONumber, resource1);


            // int counter = 1;


            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddmm");
            String timestamp = LocalDateTime.now().format(dtf);


            // Enter the Supplier No
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(SupplierInvoice, "Supplier" + timestamp);
            //Utilities.SendKeys(SupplierInvoice, "Supplier");
            //Utilities.SendKeys(Date,"15/09/2024");

            Actions actions = new Actions(BaseTest.getDriver());
            Utilities.Click(DocDate);
            actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
            actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
            Utilities.SendKeys(SupplierInvoice, "Supplier" + timestamp);

            //Utilities.SendKeys(SupplierInvoice, "Supplier");
            //Utilities.SendKeys(Date,"14/09/2024");
            Utilities.Click(Date);
            actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);


            for (WebElement element : itemVatClassElements) {
                String value = element.getAttribute("value");

                if (value == null || value.trim().isEmpty()) {
                    System.err.println("❌ Error: Element with ID '" + element.getAttribute("id") + "' has null or empty value.");
                    // Optionally throw an exception if this should fail the test
                    // throw new RuntimeException("Element with ID '" + element.getAttribute("id") + "' has empty value.");
                } else {
                    System.out.println("✅ Element with ID '" + element.getAttribute("id") + "' contains value: " + value);
                }
            }


//            for (WebElement quantity : quantitys) {
//                quantity.click();
//                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("10").perform();
//
//            }
//
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
//            Utilities.Click(BaseTest.getDriver(), SaveBtn);
//
//            try {
//                wait.until(ExpectedConditions.alertIsPresent()).accept();
//            } catch (Exception e) {
//
//            }
//            //BaseTest.getDriver().switchTo().defaultContent();
//            try {
//                wait.until(ExpectedConditions.alertIsPresent()).accept();
//            } catch (Exception e) {
//
//            }
//            try {
//                wait.until(ExpectedConditions.alertIsPresent()).accept();
//            } catch (Exception e) {
//
//            }
//
//
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
//
//
//            /// ///////////////////////////////////////  //////////////
//
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//
//            String SrvNo = SRVNO.getAttribute("value");
//            System.out.println("SRV NO is:" + SrvNo);
//
//            //String Updload_Path1 = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "POData.xlsx";
//
//            String Updload_Path1 = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "POData.xlsx";
//
//
//            ExcelHandler.loadExcelFile(Updload_Path1, 1);
//
//            // Find the first empty row in the Excel sheet
//            int rowCount = ExcelHandler.getRowCount();
//
//            // Set the "Requisition Number" in the next available row
//            ExcelHandler.setCellData("SRV Number", SrvNo, rowCount);
//
//            ExcelHandler.saveExcelFileCommon(Updload_Path1);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("POSRV", Status.FAIL, "Error in Adding Attechment", true, e);
        }
    }

    public void CheckIGST() {
        try {

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("POSRV", Status.FAIL, "Error in Adding Attechment", true, e);

        }
    }

    public void EditQty2() {

        try {

            try {
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                Utilities.Click(HomePage);
            } catch (Exception e) {

            }


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


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Click on SRV
            Utilities.Click(SRV);

            // Enter the SRV Number
            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "POData.xlsx";

            ExcelHandler.loadExcelFile(Updload_Path, 0);
            List<String> Resources1 = ExcelHandler.getAllColumnData("RQ Number");

            //for(int i=0;i<Resources1.size();i++) {
            String resource1 = Resources1.get(Resources1.size() - 1);
            System.out.println(resource1);
            if (PONumber.isDisplayed() && PONumber.isEnabled()) {
                Utilities.SendKeys(BaseTest.getDriver(), PONumber, resource1);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Purchase Order No", Status.FAIL, "Purchase Order No is not Displayed", true);
            }


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(AddBtn);


            //Enter PO Number


//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//
//            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "POData.xlsx";
//
//            ExcelHandler.loadExcelFile(Updload_Path, 0);
//            List<String> Resources1 = ExcelHandler.getAllColumnData("RQ Number");
//
//            //for(int i=0;i<Resources1.size();i++) {
//            String resource1 = Resources1.get(Resources1.size() - 1);
//            System.out.println(resource1);
//            Utilities.SendKeys(BaseTest.getDriver(), PONumber, resource1);


            // int counter = 1;


            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddmm");
            String timestamp = LocalDateTime.now().format(dtf);


            // Enter the Supplier No
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(SupplierInvoice, "Supplier" + timestamp);
            //Utilities.SendKeys(SupplierInvoice, "Supplier");
            //Utilities.SendKeys(Date,"15/09/2024");

            Actions actions = new Actions(BaseTest.getDriver());
            Utilities.Click(DocDate);
            actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
            actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
            Utilities.SendKeys(SupplierInvoice, "Supplier" + timestamp);

            //Utilities.SendKeys(SupplierInvoice, "Supplier");
            //Utilities.SendKeys(Date,"14/09/2024");
            Utilities.Click(Date);
            actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);
            for (WebElement quantity : quantitys) {
                quantity.click();
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("10").perform();

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


            try {
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

                WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));

                try {
                    OKBtn.click();
                    System.out.println("OK Button clicked successfully! ::");
                } catch (Exception e) {
                    System.out.println("Normal click failed, trying JavaScript click.");
                    ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].click();", OKBtn);
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }


            /// ///////////////////////////////////////  //////////////


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            String SrvNo = SRVNO.getAttribute("value");
            System.out.println("SRV NO is:" + SrvNo);

            //String Updload_Path1 = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "POData.xlsx";

            String Updload_Path1 = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "POData.xlsx";


            ExcelHandler.loadExcelFile(Updload_Path1, 1);

            // Find the first empty row in the Excel sheet
            int rowCount = ExcelHandler.getRowCount();

            // Set the "Requisition Number" in the next available row
            ExcelHandler.setCellData("SRV Number", SrvNo, rowCount);

            ExcelHandler.saveExcelFileCommon(Updload_Path1);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("POSRV", Status.FAIL, "Error in Adding Attechment", true, e);
        }
    }

    public void VendorReturn() {
        try {

            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            String SrvNo = SRVNO.getAttribute("value");
            System.out.println("SRV NO is:" + SrvNo);

            String Updload_Path1 = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "POData.xlsx";
            ExcelHandler.loadExcelFile(Updload_Path1, 1);

            // Find the first empty row in the Excel sheet
            int rowCount = ExcelHandler.getRowCount();

            // Set the "SRV Numberr" in the next available row
            ExcelHandler.setCellData("SRV Number", SrvNo, rowCount);
            ExcelHandler.saveExcelFileCommon(Updload_Path1);

            // Click on Close Button
            Utilities.Click(CloseBtn);


            // Get the SRV Number from the Excel Sheet
            ExcelHandler.loadExcelFile(Updload_Path1, 1);
            List<String> Resources1 = ExcelHandler.getAllColumnData("SRV Number");

            String resource1 = Resources1.get(Resources1.size() - 1);
            System.out.println(resource1);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.SendKeys(EnterSRV, resource1);

            /// Click on Search Button after entering the SRV Number
            Utilities.Click(EnterItemDetails);
            Utilities.Click(CreateVendorReturn);


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Actions actions = new Actions(BaseTest.getDriver());
            for (int i = 0; i < QtyReturned.size(); i++) {
                WebElement element = QtyReturned.get(i);
                System.out.println("Inside the quantity returned for loop!");

                if (i < 2) {
                    element.click();
                    actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("5").perform();
                } else {
                    element.click();
                    actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("10").perform();
                }
            }


            try {
                Utilities.SendKeys(Remark, "Testing");

            } catch (Exception e) {

            }

            /// Click on Save Button
            Utilities.Click(Save);
            try {
                wait.until(ExpectedConditions.alertIsPresent()).accept();
            } catch (Exception e) {
            }

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            String message = ConfDilouge.getText();
            if (message.contains("Details Created/Updated")) {
                System.out.println(message);
                System.out.println("Vendor Return Sucessful");
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Confirmation Dlg", Status.FAIL, "Error in Saving the Vendor Return", true);

            }


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("POSRV", Status.FAIL, "Error in Adding Attechment", true, e);

        }
    }

    public void SavePOToExcel() {
        try {

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            String PurchaseOrderNo = PurchaseOrder.getAttribute("value");
            System.out.println("PurchasecOrder No:" + PurchaseOrderNo);

            String Updload_Path1 = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "POData.xlsx";


            ExcelHandler.loadExcelFile(Updload_Path1, 0);

            // Find the first empty row in the Excel sheet
            int rowCount = ExcelHandler.getRowCount();

            // Set the "PO Number" in the next available row
            ExcelHandler.setCellData("RQ Number", PurchaseOrderNo, rowCount);
            ExcelHandler.saveExcelFileCommon(Updload_Path1);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("POSRV", Status.FAIL, "Error in Adding Attechment", true, e);
        }

    }

    public void CheckItemCharge() {
        try {

            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");


            Actions actions = new Actions(BaseTest.getDriver());

            for (WebElement quantity : quantitys) {
                quantity.click();
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("10").perform();
            }


            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
            Utilities.Click(BaseTest.getDriver(), SaveBtn);
            try {
                wait.until(ExpectedConditions.alertIsPresent()).accept();
            } catch (Exception e) {
            }


            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

            WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));
            OKBtn.click();

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(TaxItem);

            Utilities.Click(TaxDetail);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(SelectTAX);


            String elementText = SelectTAX.getText().trim();
            SoftAssert softAssert = new SoftAssert();
            System.out.println(elementText);

            softAssert.assertNotEquals(SelectTAX.getText().trim(), "ZERO", "Contains Charge " + elementText);
            softAssert.assertAll();


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("CheckItemCharge", Status.FAIL, "Item Charge not come from PO", true, e);
        }
    }

    public void EnterItemz() {

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

            SRVPage ob = new SRVPage(BaseTest.getDriver());
            // Call the Direct issue method
            ob.DirectIssue();
            ob.SelectDepartment();

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

    public void Validations() {
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
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
            Utilities.Click(BaseTest.getDriver(), InventoryModule);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Click on SRV
            Utilities.Click(SRV);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(AddBtn);


            // Select the Vendor

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
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);


            // Click Attachment
//            Utilities.Click(Attechment);
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            BaseTest.getDriver().switchTo().frame("iframeAttachFileDialog");
//
//            Utilities.SendKeys(ChoseFile, path);
//            Utilities.Click(Upload);
//
//
//            // Enter the Supplier No
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            Utilities.Click(DocDate);
//            actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
//            actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
//            Utilities.SendKeys(SupplierInvoice, "Supplier");
//            //Utilities.SendKeys(Date,"14/09/2024");
//            Utilities.Click(Date);
//            actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform(); // Paste
//
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            Utilities.Click(BillDetails);
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.SendKeys(BillNumber, "369");
//            Utilities.SendKeys(ProtCode, "999");
//            Utilities.SendKeys(BillEntryDate, "12/09/2024");
//            OKBtn.click();

//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            Utilities.Click(TransportDetails);
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.SendKeys(TransporterName,"ABC Group");
//            Utilities.SendKeys(VehicalNO,"UP15EQ5442");
//            Utilities.SendKeys(VehicalName,"Swift");
//            OKBtn.click();


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("SRV Creation", Status.FAIL, "Error in Creation SRV", true, e);
        }
    }

}

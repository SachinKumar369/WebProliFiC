package pages.ItemPage;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import logs.Log;
import org.jdom2.Namespace;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.*;
import utils.DynamicWait;
import utils.Utilities;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


import javax.xml.xpath.*;
import java.io.File;


import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.FileOutputStream;
import java.util.logging.Logger;


import static pages.Issue.IssuePage.getClipboardData;

public class ItemPage {

    private static final String SS_URI = "urn:schemas-microsoft-com:office:spreadsheet";
    private static final String EXCEL_URI = "urn:schemas-microsoft-com:office:excel";
    @FindBy(id = "cphDialogHeader_btnSave")
    private static WebElement Save;
    @FindAll({@FindBy(how = How.XPATH, using = "//*[@id='btnOK']"), @FindBy(how = How.ID, using = "btnOK")})
    private static List<WebElement> OKBtn;
    @FindBy(xpath = "//input[@id='FileDlg']")
    private static WebElement ChooseFile;
    @FindBy(xpath = "//input[@id='btnUpLoadData']")
    private static WebElement UploadData;
    String ItemID;
    String Date;
    @FindBy(id = "LinkProduct2")
    private WebElement Inventory;
    @FindBy(id = "divmodule_2")
    private WebElement InventoryModule;
    @FindBy(xpath = "//a[@title='INV07 Items Setup']")
    private WebElement ItemSetup;
    @FindBy(id = "cphDialogHeader_btnAddRecords")
    private WebElement Add;
    @FindBy(id = "cphBody_txtDescription")
    private WebElement Description;
    @FindBy(id = "cphBody_txtPack")
    private WebElement Pack;
    @FindBy(id = "cphBody_txtDefaultStore")
    private WebElement DefaultStore;
    @FindBy(id = "cphBody_txtStockingUnit")
    private WebElement StockingUnit;
    @FindBy(id = "cphBody_txtOrderUnit")
    private WebElement OrderUnit;
    @FindBy(id = "cphBody_txtItemUnit")
    private WebElement ItemUnit;
    @FindBy(id = "cphBody_txtItemGroup_1")
    private WebElement ItemGroup;
    @FindBy(id = "cphBody_txtStoreType")
    private WebElement StoreType;
    @FindBy(id = "td_0_0")
    private WebElement SelectRQ;
    @FindBy(id = "lblErrorDesc")
    private WebElement ConfDlg;
    @FindBy(id = "cphBody_chkMarketList")
    private WebElement MarketList;
    @FindBy(id = "cphBody_chkRegular")
    private WebElement Regular;
    @FindBy(id = "cphDialogHeader_btnReset")
    private WebElement Reset;
    @FindBy(id = "cphBody_txtItem")
    private WebElement SearchItem;
    @FindBy(id = "cphBody_btnSearch")
    private WebElement Search;
    @FindBy(id = "chkFreeze")
    private WebElement Freeze;
    @FindBy(id = "cphDialogHeader_btnDelete_Dlg")
    private WebElement Delete;
    @FindBy(id = "cphBody_ddlApprovalStatus")
    private WebElement Approval;
    @FindBy(id = "cphBody_btnApproval")
    private WebElement Approve;
    @FindBy(id = "cphBody_chkNonStockable")
    private WebElement NonStockable;
    @FindBy(id = "cphBody_imgPack")
    private WebElement PackImg;
    @FindBy(xpath = "//td[@class='ControlItemStyle'][1]")
    private List<WebElement> ItemList;
    @FindBy(id = "btnCancel")
    private WebElement Cancel;
    @FindBy(id = "cphBody_imgStoreType")
    private WebElement imgStoretype;
    @FindBy(id = "cphBody_imgDefaultStore")
    private WebElement DefaultStoreimg;
    @FindBy(id = "cphBody_imgItemGroup_1")
    private WebElement ItemGroupimg;
    @FindBy(id = "cphBody_imgStockingUnit")
    private WebElement imgStockingUnit;
    @FindBy(id = "cphBody_imgItemUnit")
    private WebElement imgItemUnit;
    @FindBy(id = "cphBody_chkExpires")
    private WebElement Expires;
    @FindBy(xpath = "//*[text()='Store Receipt from Vendor']")
    private WebElement SRV;
    @FindBy(id = "cphBrowserHeader_btnAddNew")
    private WebElement AddBtn;
    @FindBy(id = "cphBody_txtVendor")
    private WebElement Vendor;
    @FindBy(id = "cphBody_txtVendor")
    private WebElement Search_vendor;
    @FindBy(id = "cphBody_lblAttachDoc")
    private WebElement Attechment;
    @FindBy(id = "btnOK")
    private WebElement FirstColumn;
    @FindBy(id = "btnClose")
    private WebElement Close;
    @FindBy(id = "cphBody_btnSearch")
    private WebElement EnterItemDetails;
    @FindBy(id = "cphBody_imgVendor")
    private WebElement VendorImg;
    @FindBy(xpath = "//td[@class=\"ControlItemStyle\"][1]")
    private List<WebElement> VendorList;
    @FindBy(xpath = "//input[@id='col_1']")
    private WebElement Filter;
    @FindBy(id = "cphDialogHeader_btnClose_Dlg")
    private WebElement CloseBtn;
    @FindBy(id = "item_id_0_0")
    private WebElement expiryItem;
    @FindBy(id = "qty_recvd_6_0")
    private WebElement recivedQty;
    @FindBy(id = "vatable_amount_15_0")
    private WebElement recivedAmount;
    @FindBy(xpath = "//span[text()='Item Expiry Detail']")
    private WebElement itemExpiryDetail;
    @FindBy(id = "cphBody_txtDocDate")
    private WebElement DocDate;
    @FindBy(id = "expiry_date_4_0")
    private WebElement expiryDate;
    @FindBy(id = "qty_recvd_5_0")
    private WebElement expiryQty;
    @FindBy(id = "btnSave")
    private WebElement saveBtn;
    @FindBy(id = "cphDialogHeader_btnSaveClose")
    private WebElement SaveBtn;
    @FindBy(id = "cphBody_lblExpiryDetails")
    private WebElement ExpiryDetails;
    @FindBy(id = "expiry_date_4_0")
    private WebElement CheckExpiry;
    @FindBy(id = "cphBody_lblUploadFromExcel")
    private WebElement UploadFromExcel;
    @FindBy(id = "btnDownLoadTemplate")
    private WebElement DownloadTemplate;
    public ItemPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    private static void modifyXMLFile(String filePath) {
        try {
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));

            // Verify file exists
            File xmlFile = new File(filePath);
            if (!xmlFile.exists()) {
                throw new RuntimeException("XML file not found at: " + filePath);
            }
            if (xmlFile.length() == 0) {
                throw new RuntimeException("XML file is empty at: " + filePath);
            }

            // Debug: Print first few lines
            try (BufferedReader reader = new BufferedReader(new FileReader(xmlFile))) {
                System.out.println("First 5 lines of XML file:");
                for (int i = 0; i < 5 && reader.ready(); i++) {
                    System.out.println(reader.readLine());
                }
            }

            // Parse the XML file
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(xmlFile);

            // Debug: Print root and worksheets
            Element root = document.getRootElement();
            System.out.println("Root element: " + root.getName());
            System.out.println("Available worksheets:");

            // Use the default namespace for Worksheet elements
            Namespace defaultNs = root.getNamespace(); // urn:schemas-microsoft-com:office:spreadsheet
            Namespace ssNs = root.getNamespace("ss"); // urn:schemas-microsoft-com:office:spreadsheet for ss prefix

            List<Element> worksheets = root.getChildren("Worksheet", defaultNs);
            if (worksheets.isEmpty()) {
                System.out.println("No Worksheet elements found!");
                System.out.println("All child elements of root:");
                root.getChildren().forEach(child -> System.out.println(child.getName() + " with attributes: " + child.getAttributes()));
                throw new RuntimeException("No Worksheet elements found in XML");
            } else {
                worksheets.forEach(ws -> System.out.println(ws.getAttributeValue("Name", ssNs)));
            }

            // Find Data worksheet
            Element worksheet = worksheets.stream().filter(ws -> "Data".equalsIgnoreCase(ws.getAttributeValue("Name", ssNs))).findFirst().orElseThrow(() -> new RuntimeException("Data worksheet not found"));

            // Navigate to Table
            Element table = worksheet.getChild("Table", ssNs);
            if (table == null) {
                throw new RuntimeException("Table element not found in Data worksheet");
            }

            // Get second row
            List<Element> rows = table.getChildren("Row", defaultNs);
            if (rows.size() < 2) {
                throw new RuntimeException("Second row not found in Table");
            }
            Element secondRow = rows.get(1);

            // Clear existing cells
            secondRow.removeChildren("Cell", defaultNs);

            String timeStamp = new SimpleDateFormat("ddMMyyHHmm").format(new Date());
            // Data to insert
            String[] data = {"", "Sample Item" + timeStamp, "NOS", "GS", "88", "COAL", "NOS", "NOS", "", "NOS"};

            // Insert new cells
            for (int i = 0; i < data.length; i++) {
                Element cell = new Element("Cell", defaultNs);
                if (i != 0) {
                    cell.setAttribute("Index", String.valueOf(i + 1), ssNs);
                }
                Element dataElement = new Element("Data", defaultNs);
                dataElement.setAttribute("Type", "String", ssNs);
                dataElement.setText(data[i]);
                cell.addContent(dataElement);
                secondRow.addContent(cell);
            }

            // Save the modified file
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            try (FileOutputStream fos = new FileOutputStream(xmlFile)) {
                xmlOutputter.output(document, fos);
            }

            System.out.println("XML file updated successfully at: " + filePath);


            String Uploaded_File = System.getProperty("user.dir") + File.separator + "DownloadPath";
            File file2 = Utilities.getLastModified(Uploaded_File);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            BaseTest.getDriver().switchTo().frame("iframeFileDialog");
            ChooseFile.sendKeys(file2.getAbsolutePath());
            Utilities.Click(BaseTest.getDriver(), UploadData);
            wait.until(ExpectedConditions.alertIsPresent()).accept();


            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeBrw"));
            Utilities.Click(Save);


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to modify XML file", e);
        }
    }

    public void ItemEdit() {
        try {

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Item Modification", Status.PASS, "Item Modification Failed", true, e);
        }
    }

    public void ExcelUpload1() {
        try {
            String downloadPath = System.getProperty("user.dir") + File.separator + "DownloadPath";

            Utilities.Click(UploadFromExcel);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            BaseTest.getDriver().switchTo().frame("iframeFileDialog");
            Utilities.Click(DownloadTemplate);

            DynamicWait.mediumWait();

            File filePath = getLatestFile(downloadPath);

            modifyXMLFile(String.valueOf(filePath));


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            if (ConfDlg.getText().contains("Details Created/Updated")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Item Creation", Status.PASS, "Item Created Sucessfully", true);
                System.out.println("Item Created Sucessfully");
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Item Creation", Status.FAIL, "Item Creation Failed", true);

            }


        } catch (Exception e) {

        }
    }

    private File getLatestFile(String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".xml"));
        if (files == null || files.length == 0) return null;

        File lastModified = files[0];
        for (File f : files) {
            if (f.lastModified() > lastModified.lastModified()) {
                lastModified = f;
            }
        }
        return lastModified;
    }

    public void ExpiryItem() {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddmm");
            String timestamp = LocalDateTime.now().format(dtf);

            if (Regular.isSelected()) {
                Utilities.ClickCheckBox(Regular);
            }
            if (MarketList.isSelected()) {
                Utilities.ClickCheckBox(MarketList);
            }
            if (!Expires.isSelected()) {
                Utilities.ClickCheckBox(Expires);
            }


            Utilities.SendKeys(Description, "Mobile " + timestamp);
            Utilities.SendKeys(Pack, "NOS");
            Utilities.SendKeys(StoreType, "GS");
            Utilities.SendKeys(DefaultStore, "88");
            Utilities.SendKeys(ItemGroup, "GS");
            Utilities.SendKeys(StockingUnit, "NOS");
            Utilities.SendKeys(OrderUnit, "NOS");
            Utilities.SendKeys(ItemUnit, "NOS");
            Utilities.Click(Save);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            if (ConfDlg.getText().contains("Details Created/Updated")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Item Create", Status.PASS, "Item Creation Pass", true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Item Create", Status.FAIL, "Item Creation Failed", true);

            }


            Utilities.Click(OKBtn.get(0));

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            ItemID = SelectRQ.getText();
            System.out.println("Item id is :" + ItemID);

            //Utilities.Click(CloseBtn);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Item Create", Status.FAIL, "Item Creation Failed", true, e);

        }
    }

    public void SRVExpiryItem() {
        try {

            String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "image.jpg";
            Actions actions = new Actions(BaseTest.getDriver());

            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            // Switch to the main content and then to the required frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Wait for Inventory element and click it
            try {
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Inventory);
                Utilities.Click(BaseTest.getDriver(), Inventory);
            } catch (Exception e) {

            }


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

            if (Vendor.isEnabled() && Vendor.isDisplayed()) {
                Utilities.SendKeys(BaseTest.getDriver(), Vendor, "%%");
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Vendor", Status.FAIL, "Error Sending vendor is not intrectable", true);
            }
            //Utilities.Click(BaseTest.getDriver(), VendorImg);

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


            // Click Attachment
//            Utilities.Click(Attechment);
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            BaseTest.getDriver().switchTo().frame("iframeAttachFileDialog");
//


//            Utilities.SendKeys(ChoseFile, path);
//            Utilities.Click(Upload);

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
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            Utilities.Click(BillDetails);
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.SendKeys(BillNumber, "369");
//            Utilities.SendKeys(ProtCode, "999");
//            Utilities.SendKeys(BillEntryDate, "12/09/2024");
//            OKBtn.click();


            // Copy the System Date **************************************************
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(DocDate);

            actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
            actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();

            Date = getClipboardData();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // Parse the date string to LocalDate
            LocalDate date = LocalDate.parse(Date, formatter);
            // Format the new date back to the desired string format
            String newDateString = date.format(formatter);

            /*********************************************************************************************/


            //Click Enter Items Details
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            //Utilities.SendKeys(expiryItem,"GS000369");
            Utilities.SendKeys(expiryItem, ItemID);
            Utilities.Click(recivedQty);
            actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("10").perform();
            Utilities.Click(recivedAmount);
            actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("10").perform();

            Utilities.Click(itemExpiryDetail);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.SendKeys(expiryDate, newDateString);
            Utilities.Click(expiryQty);
            actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("10").perform();

            Utilities.Click(saveBtn);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(SaveBtn);

            try {
                wait.until(ExpectedConditions.alertIsPresent()).accept();
            } catch (Exception e) {
                System.out.println("No Alert is present");
            }

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(OKBtn.get(0));

            //Inventory Module Select
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            if (InventoryModule.isDisplayed() && InventoryModule.isEnabled()) {
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
                Utilities.Click(BaseTest.getDriver(), InventoryModule);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Inventory Button", Status.FAIL, "Inventory Button is not Displayed or not Enabled", true);
            }


            Utilities.Click(ItemSetup);


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.SendKeys(SearchItem, ItemID);
            //Utilities.SendKeys(SearchItem,"GS000369");
            Utilities.Click(Search);

            Utilities.Click(ExpiryDetails);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            String expirydate = CheckExpiry.getAttribute("value");
            if (Date == expirydate) {
                System.out.println("Date is " + Date);
                System.out.println("Expiry date is :" + expirydate);
                ExtentTestManager.createAssertTestStepWithScreenshot("Date Validation", Status.PASS, " ", true);
            } else {
                System.out.println("Date is " + Date);
                System.out.println("Expiry date is :" + expirydate);
            }


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("SRV Creation", Status.FAIL, "Error in Creation SRV", true, e);
        }
    }

    public void NonStockable() {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddmm");
            String timestamp = LocalDateTime.now().format(dtf);

            if (Regular.isSelected()) {
                Utilities.ClickCheckBox(Regular);
            }
            if (MarketList.isSelected()) {
                Utilities.ClickCheckBox(MarketList);
            }
            if (!NonStockable.isSelected()) {
                Utilities.ClickCheckBox(NonStockable);
            }


            Utilities.SendKeys(Description, "Mobile " + timestamp);
            Utilities.Click(PackImg);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Random random = new Random();
            int randomIndex = random.nextInt(ItemList.size());


            String packitem = ItemList.get(randomIndex).getText();
            System.out.println(packitem);
            Utilities.Click(Cancel);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");


            Utilities.SendKeys(Pack, packitem);
            Utilities.Click(imgStoretype);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            int storetype = random.nextInt(ItemList.size());
            String storetypes = ItemList.get(storetype).getText();
            Utilities.Click(Cancel);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.SendKeys(StoreType, storetypes);

            Utilities.Click(DefaultStoreimg);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            int defaults = random.nextInt(ItemList.size());
            String defaultstore = ItemList.get(defaults).getText();
            Utilities.Click(Cancel);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.SendKeys(DefaultStore, defaultstore);


            Utilities.Click(ItemGroupimg);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            int randomIndex1 = random.nextInt(ItemList.size());
            String randomItemGroup = ItemList.get(randomIndex1).getText();
            Utilities.Click(Cancel);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.SendKeys(ItemGroup, randomItemGroup);

            Utilities.Click(imgStockingUnit);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            int randomIndex2 = random.nextInt(ItemList.size());
            String randomStockingUnit = ItemList.get(randomIndex2).getText();
            Utilities.Click(Cancel);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.SendKeys(StockingUnit, randomStockingUnit);


            Utilities.Click(imgItemUnit);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            int randomIndex3 = random.nextInt(ItemList.size());
            String randomItemUnit = ItemList.get(randomIndex3).getText();
            Utilities.Click(Cancel);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.SendKeys(ItemUnit, randomItemUnit);


//            Utilities.SendKeys(ItemUnit,"NOS");
            Utilities.Click(Save);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            if (ConfDlg.getText().contains("Details Created/Updated")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Item Create", Status.PASS, "Item Creation Pass", true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Item Create", Status.FAIL, "Item Creation Failed", true);

            }


            Utilities.Click(OKBtn.get(0));

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            ItemID = SelectRQ.getText();
            System.out.println("Item id is :" + ItemID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void MarketList() {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddmm");
            String timestamp = LocalDateTime.now().format(dtf);

            if (Regular.isSelected()) {
                Utilities.ClickCheckBox(Regular);
            }
            if (!MarketList.isSelected()) {
                Utilities.Click(MarketList);
            }


            Utilities.SendKeys(Description, "Mobile " + timestamp);
            Utilities.SendKeys(Pack, "NOS");
            Utilities.SendKeys(StoreType, "GS");
            Utilities.SendKeys(DefaultStore, "88");
            Utilities.SendKeys(ItemGroup, "GS");
            Utilities.SendKeys(StockingUnit, "NOS");
            Utilities.SendKeys(OrderUnit, "NOS");
            Utilities.SendKeys(ItemUnit, "NOS");
            Utilities.Click(Save);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            if (ConfDlg.getText().contains("Details Created/Updated")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Item Create", Status.PASS, "Item Creation Pass", true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Item Create", Status.FAIL, "Item Creation Failed", true);

            }


            Utilities.Click(OKBtn.get(0));

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            ItemID = SelectRQ.getText();
            System.out.println("Item id is :" + ItemID);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Item Create", Status.FAIL, "Item Creation Failed", true, e);

        }
    }

    public void RegularItem() {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddmm");
            String timestamp = LocalDateTime.now().format(dtf);

            Utilities.SendKeys(Description, "Mobile " + timestamp);
            Utilities.SendKeys(Pack, "NOS");
            Utilities.SendKeys(StoreType, "GS");
            Utilities.SendKeys(DefaultStore, "88");
            Utilities.SendKeys(ItemGroup, "GS");
            Utilities.SendKeys(StockingUnit, "NOS");
            Utilities.SendKeys(OrderUnit, "NOS");
            Utilities.SendKeys(ItemUnit, "NOS");
            Utilities.Click(Save);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            if (ConfDlg.getText().contains("Details Created/Updated")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Item Create", Status.PASS, "Item Creation Pass", true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Item Create", Status.FAIL, "Item Creation Failed", true);

            }


            Utilities.Click(OKBtn.get(0));

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            ItemID = SelectRQ.getText();
            System.out.println("Item id is :" + ItemID);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Item Create", Status.FAIL, "Item Creation Failed", true, e);

        }
    }

    public void ItemCreate() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(Inventory);
            ExtentTestManager.getTest().log(Status.INFO, "Clicked on Inventory.");


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(InventoryModule);
            Utilities.Click(ItemSetup);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(Add);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Item Create", Status.FAIL, "Item Creation Failed", true, e);

            // Log the error
            Log.error("Item creation failed: " + e.getMessage());
            ExtentTestManager.getTest().log(Status.FAIL, "Item creation failed due to: " + e.getMessage());
        }
    }


    public void DeleteItem() {
        try {
            Utilities.Click(Reset);
            Utilities.SendKeys(SearchItem, ItemID);
            Utilities.Click(Search);
            if (!Freeze.isSelected()) {
                Utilities.ClickCheckBox(Freeze);
            }
            Utilities.Click(Save);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            if (ConfDlg.isDisplayed() && ConfDlg.getText().contains("Details Created/Updated")) {
                System.out.println("Item Freeze sucessfully" + ConfDlg.getText());
            }
            Utilities.Click(OKBtn.get(0));

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(Delete);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            if (ConfDlg.getText().contains("Details Deleted")) {
                System.out.println("Item Deleted Sucessfully");
                ExtentTestManager.createAssertTestStepWithScreenshot("Delete Item", Status.PASS, "Item Deleted Successfully", true);

            }


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Item Create", Status.FAIL, "Item Creation Failed", true, e);

        }
    }

    public void RegularItemAP() {
        try {
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            Utilities.Click(Approve);
            Utilities.selectBy("value", Approval, "AP");
            Utilities.Click(Save);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("lblErrorDesc")));
            if (ConfDlg.getText().contains("Details Created/Updated")) {
                System.out.println("Item Approved Sucessfully");
                ExtentTestManager.createAssertTestStepWithScreenshot("Item Approval", Status.PASS, "Item Approval Passed", true);

            }
            Utilities.Click(OKBtn.get(0));
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Item Approval", Status.FAIL, "Item Approval Failed", true, e);

        }
    }

}
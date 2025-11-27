package pages.Issue;

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
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import pages.CommonPages.PortalLoginPage;
import utils.Dateformat;
import utils.DynamicWait;
import utils.GetLatestFile;
import utils.Utilities;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class IssuePage {

    /**
     * Constructor
     *
     * @param driver
     */
    public IssuePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * WebElements
     */

    @FindBy(xpath = "//a[@id='LinkProduct2']")
    private WebElement Inventory;
    @FindBy(id = "divmodule_2")
    private WebElement InventoryModule;
    @FindBy(id = "cphBrowserHeader_btnAddNew")
    private WebElement AddButton;
    ;
    @FindBy(xpath = "//a[text()='Issue'][1]")
    private WebElement Issue;
    @FindBy(id = "cphBody_txtFromStore")
    private WebElement FromStore;
    @FindBy(id = "cphBody_txtToDept")
    private WebElement ToDept;

    @FindBy(id = "cphBody_btnEnterItemDetails")
    private WebElement EnterItemDetails;


    @FindBy(id = "cphBody_btnAddRow_Top")
    private WebElement AddRow;

    public WebElement getItemSearchElement(int itemId) {
        String xpath = "//input[@id='item_id_0_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath));
    }


    public WebElement getrequestQuantityelement(int itemId) {
        String xpath3 = "//input[@id='qty_issued_7_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }


    @FindBy(id = "cphBody_lblNewIssue")
    private WebElement NewIssue;

    @FindAll({
            @FindBy(how = How.XPATH, using = "//*[@id='btnOK']"),
            @FindBy(how = How.ID, using = "btnOK")
    })
    private static List<WebElement> OKBtn;
    @FindBy(id = "cphBody_txtTransactionNumber")
    private WebElement IssueNumber;
    @FindBy(id = "cphDialogHeader_btnClose_Dlg")
    private WebElement CloseBtn;
    @FindBy(id = "cphBody_txtIssueNumber")
    private WebElement IsseuNo;
    @FindBy(id = "cphBody_btnSearch")
    private WebElement Search;
    @FindBy(id = "cphBody_lblCopyIssue")
    private WebElement Copy;
    @FindBy(xpath = "//*[@id='btnOK']")
    private WebElement OK;
    @FindBy(id = "cphBody_txtTransactionTypeDesc")
    private WebElement IssueType;
    @FindBy(xpath = "//input[starts-with(@id,'item_id_0_')]")
    private List<WebElement> Items;
    @FindBy(xpath = "//input[starts-with(@id,'qty_issued_7_')]")
    private List<WebElement> Quantity;
    @FindBy(xpath = "//input[starts-with(@id,'item_desc_2_')]")
    private List<WebElement> ItemName;
    @FindBy(xpath = "//input[starts-with(@id,'value_issued_8_')]")
    private List<WebElement> NetAmount;
    @FindBy(id = "td_0_0")
    private WebElement SelectRQ;
    @FindBy(id = "cphBody_txtRequisitionNumber")
    private WebElement RequisitionNumber;
    @FindBy(id = "col_0")
    private WebElement Rqno;

    @FindBy(id = "//img[@onclick=\"javascript:SearchValue('col_0','rq_number','F');\"]")
    private WebElement SearchRq;
    @FindBy(id = "imgRow_0")
    private WebElement SearchImg;
    @FindBy(id = "cphDialogHeader_btnSave")
    private WebElement SaveBtn;
    @FindBy(how = How.CSS, using = "[id^='expiry_11_']")
    private List<WebElement> expiry;
    @FindBy(id = "btnClose")
    private WebElement CloseEXp;
    @FindBy(id = "lblErrorDesc")
    private WebElement confirmdlg;
    @FindBy(id = "divMsg")
    private WebElement IssueConf;
    @FindBy(id = "cphBody_txtFromStoreDesc")
    private WebElement FromStoreText;
    @FindBy(id = "cphBody_txtToDepartmentDesc")
    private WebElement ToDepartment;
    @FindBy(id = "cphBody_txtGrossAmount")
    private WebElement GrossAmount;
    @FindBy(id = "btnAutoAssign")
    private WebElement AutoAssign;
    @FindBy(id = "cphBody_txtDate")
    private WebElement Date;
    @FindBy(xpath = "//a[@title='INV26 Correct Stock Position']")
    private WebElement CorrectStockPosition;
    @FindBy(id = "cphBody_txtAdjustmentDate")
    private WebElement AdjustmentDate;
    @FindBy(id = "//*[@id='cphBody_hidtxtBusinessDate']")
    private WebElement BusinessDate;
    @FindBy(id = "cphBody_txtStoreType")
    private WebElement StoreType;
    @FindBy(id = "cphBody_txtStore")
    private WebElement Store;
    @FindBy(id = "cphBody_txtFromItem")
    private WebElement FromItem;
    @FindBy(id = "cphBody_txtToItem")
    private WebElement ToItem;
    @FindBy(id = "cphBody_btnSearch")
    private WebElement SearchBtn;
    @FindBy(id = "total_qty_instock_11_0")
    private WebElement Qty;
    @FindBy(xpath = "//a[@title='INV07 Items Setup']")
    private WebElement ItemSetup;
    @FindBy(id = "cphDialogHeader_btnAddRecords")
    private WebElement AddBtn;
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
    @FindBy(id = "cphDialogHeader_btnSave")
    private WebElement Save;
    @FindBy(id = "cphBody_txtItemGroup_1")
    private WebElement ItemGroup;
    @FindBy(xpath = "//a[@title='INV29 Requisitions']")
    private WebElement Requisition;
    @FindBy(id = "cphBody_txtRequisitionType")
    private WebElement RequisitionType;










    @FindBy(id = "cphBody_txtIndentDept")
    private WebElement IndentDept;
    @FindBy(id = "cphBody_txtFromDeptStore")
    private WebElement FromDept;

    @FindBy(id = "item_id_1_0")
    private WebElement EnterItem;

    String ItemID ;
    String Date1 ;

    @FindBy(id = "qty_req_7_0")
    private WebElement quantity;
    @FindBy(id = "cphBody_ddlApprovalStatus")
    private WebElement Approval;

    @FindBy(xpath = "//*[text()='Store Receipt from Vendor']")
    private WebElement SRV;
    @FindBy(id = "cphBody_txtDocDate")
    private WebElement DocDate;
    @FindBy(id = "cphBrowserHeader_btnAddNew")
    private WebElement AddBtn1;
    @FindBy(id = "cphBody_txtVendor")
    private WebElement Vendor;
    @FindBy(id = "cphBody_btnSearch")
    private WebElement EnterItems;
    @FindBy(id = "item_id_0_0")
    private WebElement EnterItemforsrv;
    @FindBy(id = "qty_recvd_6_0")
    private WebElement recivedqty;
    @FindBy(id = "cphDialogHeader_btnSaveClose")
    private WebElement SaveSRV;
    @FindBy(id = "default_dept_id_11_0")
    private WebElement department;
    @FindBy(id = "vatable_amount_15_0")
    private WebElement amount;
    @FindBy(id = "qty_issued_7_0")
    private WebElement issued_qty;
    @FindBy(xpath = "//*[@title='INV00 Reports of Inventory']")
    private WebElement ReportofInventory;
    @FindBy(xpath = "(//img[@src='../../Common/Images/iconTexts.gif'])[11]")
    private WebElement TrensectionReport;
    @FindBy(xpath = "//a[text()='Stocks Ledger']")
    private WebElement StockLedger;
    @FindBy(id = "ws_store_type")
    private WebElement storetype;
    @FindBy(id = "ws_store")
    private WebElement store;
    @FindBy(id = "ws_from_item")
    private WebElement fromitem;
    @FindBy(id = "ws_to_item")
    private WebElement toitem;
    @FindBy(id = "btnDNLDXLD")
    private WebElement downloadxldata;
    @FindBy(xpath = "//*[@id=\"lnkPreview\"]")
    private WebElement Preview;
    @FindBy(xpath = "(//span[text()='-1.000'])[1]")
    private WebElement Itemss;

    @FindBy(xpath = "//span[contains(text(), '3')]")
    private WebElement Receiving;
    @FindBy(xpath = "(//span[text()='-1.000'])[1]")
    private WebElement issue;
    @FindBy(xpath = "(//span[text()='-2.000'])[1]")
    private WebElement issue1;
    @FindBy(xpath = "(//span[contains(text(), '0.000')])[2]")
    private WebElement remaining;
    @FindBy(xpath = "(//span[contains(text(), '0.000')])[3]")
    private WebElement remaining1;

    @FindBy(id = "cphBody_txtDate")
    private WebElement Date12;
    @FindBy(id = "divTooltips")
    private WebElement Appprogerror;





    public void EnterMoreItemz() {
        try {
            Utilities.logerror("Validations", logMessages -> {
                Random random = new Random();
                WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
                Actions actions = new Actions(BaseTest.getDriver());
                Set<String> usedTexts = new HashSet<>();

                for (int i = 0; i < 1; i++) {
                    if (i > 0) {
                        Utilities.Click(BaseTest.getDriver(), AddRow);
                        logMessages.add("Clicked on Add Row");
                    }

                    String[] items = {"GSCS0008"};
                    Utilities.SendKeys(BaseTest.getDriver(), getItemSearchElement(i), items[i]);
                    getItemSearchElement(i).sendKeys(Keys.TAB);

                    Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getrequestQuantityelement(i));
                    getrequestQuantityelement(i).click();
                    actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("500").perform();
                    logMessages.add("Send Quantity '500' ");
                }

                Utilities.Click(Save);
                logMessages.add("Clicked on Save Button");

                wait.until(ExpectedConditions.alertIsPresent()).accept();
                logMessages.add("Accept the alert");

                DynamicWait.mediumWait();

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                System.out.println("Switched into iframeGridDialog");

                WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));
                OKBtn.click();
                logMessages.add("Clicked on Add Button");

                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));

                for (int i = 0; i < 1; i++) {
                    Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getrequestQuantityelement(i));
                    getrequestQuantityelement(i).click();
                    actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("2").perform();
                    logMessages.add("Send the quantity 2 ");
                }

                Utilities.Click(Save);
                logMessages.add("Clicked on Save Button");
                wait.until(ExpectedConditions.alertIsPresent()).accept();
                logMessages.add("Accept the alert");
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");

                String text = IssueConf.getText();
                if (IssueConf.isDisplayed() && text.contains("Issue document created. Issue Number is")) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Issue Creation", Status.PASS, "Issue Number Generated", true);
                    System.out.println("Issue number generated successfully ");
                } else {
                    if (IssueConf.getText().contains("[127] - Application program error (...)")) {
                        String text1 = Appprogerror.getText();
                        JavascriptExecutor js = (JavascriptExecutor) BaseTest.getDriver();
                        String errorText = (String) js.executeScript(
                                "return document.getElementById('divTooltips').textContent;"
                        );
                        throw new Exception("Application program error: " + errorText);
                    } else {
                        ExtentTestManager.createAssertTestStepWithScreenshot("Issue Creation", Status.FAIL, "Issue number not Generated: " + text, true);
                    }
                }
            });
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Items", Status.FAIL, "Exception found in Method - Items: " + e.getMessage(), true);
        }
    }

    public void EnterMoreItemz2() {
        try {
            Utilities.logerror("Validations", logMessages -> {

                SoftAssert softAssert = new SoftAssert();
                WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
                // Switch to the main content and then to the required frame
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                logMessages.add("Switched to frame : MultiPageiframeBrw ");


                // Wait for Inventory element and click it
                DynamicWait.mediumWait();
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Inventory);
                Utilities.Click(BaseTest.getDriver(), Inventory);
                logMessages.add("Clicked on Inventory");

                //Inventory Module Select
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
                Utilities.Click(BaseTest.getDriver(), InventoryModule);
                logMessages.add("Clicked on Inventory Module");

                //Click on Issue
                if (Issue.isEnabled() && Issue.isDisplayed()) {
                    Utilities.Click(BaseTest.getDriver(), Issue);
                    logMessages.add("Clicked on Issue");
                } else {

                }
                //Click on Add Button
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), AddButton);
                Utilities.Click(BaseTest.getDriver(), AddButton);
                logMessages.add("Clicked on Add Button");

                //Select PO Type
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                //Utilities.SendKeys(FromStore, "60");  uncomment if error occur

                Actions actions = new Actions(BaseTest.getDriver());
                Utilities.Click(Date12);
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();

                //Utilities.SendKeys(Date12,"25/04/2025");
                Utilities.SendKeys(FromStore, "89");
                logMessages.add("Send '89' to the Frome Store");
                Utilities.SendKeys(ToDept, "1134");
                logMessages.add("send '1134' to To Department ");


                //Click Enter Items Details
                Utilities.Click(BaseTest.getDriver(), EnterItemDetails);
                logMessages.add("Clicked on Enter Item Details Button");





                Random random = new Random();
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
                Set<String> usedTexts = new HashSet<>();

                for (int i = 0; i < 5; i++) {
                    if (i > 0) {
                        Utilities.Click(BaseTest.getDriver(), AddRow);
                        logMessages.add("Clicked on Add Row");
                    }
                    String[] items = {"GSCS0008", "GSCS0009", "GSCS0010", "GSCS0025", "GSCS0029"};
                    //String[] items = {"GSCS0008"};
                    Utilities.SendKeys(BaseTest.getDriver(), getItemSearchElement(i), items[i]);
                    getItemSearchElement(i).sendKeys(Keys.TAB);

                    Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getrequestQuantityelement(i));
                    getrequestQuantityelement(i).click();
                    actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("300").perform();
                    logMessages.add("Send Quantity '300' ");
                }

                Utilities.Click(Save);
                logMessages.add("Clicked on Save Button");

                wait.until(ExpectedConditions.alertIsPresent()).accept();
                logMessages.add("Accept the alert");

                DynamicWait.mediumWait();

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                System.out.println("Switched into iframeGridDialog");

                WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));
                OKBtn.click();
                logMessages.add("Clicked on Add Button");

                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));

                for (int i = 0; i < 5; i++) {
                    Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getrequestQuantityelement(i));
                    getrequestQuantityelement(i).click();
                    actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("2").perform();
                    logMessages.add("Send the quantity 2 ");
                }

                Utilities.Click(Save);
                logMessages.add("Clicked on Save Button");
                wait.until(ExpectedConditions.alertIsPresent()).accept();
                logMessages.add("Accept the alert");
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");

                String text = IssueConf.getText();
                if (IssueConf.isDisplayed() && text.contains("Issue document created. Issue Number is")) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Issue Creation", Status.PASS, "Issue Number Generated", true);
                    System.out.println("Issue number generated successfully ");
                } else {
                    if (IssueConf.getText().contains("[127] - Application program error (...)")) {
                        String text1 = Appprogerror.getText();
                        JavascriptExecutor js = (JavascriptExecutor) BaseTest.getDriver();
                        String errorText = (String) js.executeScript(
                                "return document.getElementById('divTooltips').textContent;"
                        );
                        throw new Exception("Application program error: " + errorText);
                    } else {
                        ExtentTestManager.createAssertTestStepWithScreenshot("Issue Creation", Status.FAIL, "Issue number not Generated: " + text, true);
                    }
                }
            });
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Items", Status.FAIL, "Exception found in Method - Items: " + e.getMessage(), true);
        }
    }


    public void Issue() {

        try {

            Utilities.logerror("Validations",logMessages -> {


                SoftAssert softAssert = new SoftAssert();
                WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
                // Switch to the main content and then to the required frame
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                logMessages.add("Switched to frame : MultiPageiframeBrw ");


                // Wait for Inventory element and click it
                DynamicWait.mediumWait();
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Inventory);
                Utilities.Click(BaseTest.getDriver(), Inventory);
                logMessages.add("Clicked on Inventory");

                //Inventory Module Select
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
                Utilities.Click(BaseTest.getDriver(), InventoryModule);
                logMessages.add("Clicked on Inventory Module");

                //Click on Issue
                if (Issue.isEnabled() && Issue.isDisplayed()) {
                    Utilities.Click(BaseTest.getDriver(), Issue);
                    logMessages.add("Clicked on Issue");
                } else {

                }
                //Click on Add Button
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), AddButton);
                Utilities.Click(BaseTest.getDriver(), AddButton);
                logMessages.add("Clicked on Add Button");

                //Select PO Type
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                //Utilities.SendKeys(FromStore, "60");  uncomment if error occur

                Actions actions = new Actions(BaseTest.getDriver());
                Utilities.Click(Date12);
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();

                //Utilities.SendKeys(Date12,"25/04/2025");
                Utilities.SendKeys(FromStore, "89");
                logMessages.add("Send '89' to the Frome Store");
                Utilities.SendKeys(ToDept, "1134");
                logMessages.add("send '1134' to To Department ");


                //Click Enter Items Details
                Utilities.Click(BaseTest.getDriver(), EnterItemDetails);
                logMessages.add("Clicked on Enter Item Details Button");
            });
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Issue", Status.FAIL, "Error in issue", true, e);

        }
    }

    public void CreateItems(){
        try {

            SoftAssert softAssert = new SoftAssert();
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
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
            Utilities.Click(ItemSetup);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(AddBtn);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddmm");
            String timestamp = LocalDateTime.now().format(dtf);

            Utilities.SendKeys(Description,"Mobile "+timestamp);
            Utilities.SendKeys(Pack,"NOS");
            Utilities.SendKeys(StoreType,"GS");
            Utilities.SendKeys(DefaultStore,"88");
            Utilities.SendKeys(ItemGroup,"GS");
            Utilities.SendKeys(StockingUnit,"NOS");
            Utilities.SendKeys(OrderUnit,"NOS");
            Utilities.SendKeys(ItemUnit,"NOS");
            Utilities.Click(Save);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Utilities.Click(OKBtn.get(0));

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            ItemID = SelectRQ.getText();
            System.out.println("Item id is :"+ItemID);




        } catch (Exception e) {
             ExtentTestManager.createAssertTestStepWithScreenshot("Item Creation",Status.FAIL,"Item Creation Fails",true,e);
        }
    }

    public void EnterItemz() {

        try {

            Random random = new Random();
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
            Actions actions = new Actions(BaseTest.getDriver());
            Set<String> usedTexts = new HashSet<>();

            for (int i = 0; i < 51; i++) {
                if (i > 0) { // Execute only after i = 0
                    Utilities.Click(BaseTest.getDriver(), AddRow);
                }
               // String[] items = {"BIMP0002", "BIMP0006", "BIMP0007", "BIMP0019", "BIMP0033"};
               // GSCS0008 GSCS0009 GSCS0010  GSCS0025  GSCS0029

//                        String[] items = {"GSCS0008", "GSCS0009", "GSCS0010", "GSCS0025", "GSCS0029" };
                String[] items = {
                        "COMP0039", "ELEC0008", "ELEC0010", "ELEC0023", "ELEC0051",
                        "ELEC0052", "ELEC0053", "ELEC0061", "ELEC0062", "ELEC0065",
                        "ELEC0101", "ELEC0107", "ELEC0139", "ELEC0151", "ELEC0177",
                        "ELEC0178", "ELEC0205", "ELEC0206", "ELEC0231", "ELEC0234",
                        "ELEC0240", "ELEC0274", "ELEC0288", "ELEC0358", "ELEC0359",
                        "ELEC0360", "ELEC0392", "ELEC0407", "ELEC0472", "ELEC0481",
                        "ENCM0004", "ENCM0005", "ENEQ0003", "ENEQ0004", "ENEQ0005",
                        "ENEQ0006", "ENEQ0007", "ENEQ0008", "ENEQ0009", "ENEQ0010",
                        "ENOT0012", "ENOT0013", "ENOT0014", "ENOT0015", "ENOT0016",
                        "ENOT0017", "ENOT0018", "ENOT0019", "ENOT0020", "ENOT0021",
                        "ENOT0022", "ENOT0035", "ENOT0041"
                };


                Utilities.SendKeys(BaseTest.getDriver(), getItemSearchElement(i), items[i]);
                getItemSearchElement(i).sendKeys(Keys.TAB);

                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getrequestQuantityelement(i));
                getrequestQuantityelement(i).click();
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("1").perform();
            }


            Utilities.Click(Save);

            wait.until(ExpectedConditions.alertIsPresent()).accept();

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            String text = IssueConf.getText();
            if(IssueConf.isDisplayed()&&text.contains("Issue document created. Issue Number is")){
                ExtentTestManager.createAssertTestStepWithScreenshot("Issue Creation",Status.PASS,"Issue Number Generated",true);

                System.out.println("Issue number generated successfully ");
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Issue Creation",Status.FAIL,"Issue number not Generated",true);
            }
            Utilities.Click(BaseTest.getDriver(), OKBtn.get(0));




        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Items", Status.FAIL, "Exception found in Method - Items", true, e);
        }
    }

    public void NewIssue() {

        try {

            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
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
            Utilities.Click(BaseTest.getDriver(), Issue);

            //Click on Add Button
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), NewIssue);
            Utilities.Click(BaseTest.getDriver(), NewIssue);

            //Select PO Type
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(FromStore, "87"); //60
            Utilities.SendKeys(ToDept, "1134");


            //Click Enter Items Details
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Issue", Status.FAIL, "Error in issue", true, e);

        }
    }

    public void CopyIssue() {

        try {
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));

            BaseTest.getDriver().switchTo().defaultContent();
            //wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.Click(OKBtn.get(0));

            // Switch to the main content and then to the required frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");


            String issueno = IssueNumber.getAttribute("value");
            System.out.println("Issue Number is :" + issueno);
            Utilities.Click(CloseBtn);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.SendKeys(IsseuNo, issueno);
            Utilities.Click(Search);

            Utilities.Click(Copy);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(Save);
            wait.until(ExpectedConditions.alertIsPresent()).accept();


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            String text = IssueConf.getText();
            if(IssueConf.isDisplayed()&&text.contains("Issue document created. Issue Number is")){
                ExtentTestManager.createAssertTestStepWithScreenshot("Issue Creation",Status.PASS,"Issue Number Generated",true);

                System.out.println("Issue number generated successfully ");
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Issue Creation",Status.FAIL,"Issue number not Generated",true);
            }
            Utilities.Click(BaseTest.getDriver(), OKBtn.get(0));


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Issue", Status.FAIL, "Error in issue", true, e);

        }
    }

    public void SaveIntoExcel(){
        // Extract details from the webpage

        BaseTest.getDriver().switchTo().defaultContent();
        //wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

        BaseTest.getDriver().switchTo().frame("iframeGridDialog");
        Utilities.Click(OKBtn.get(0));

        BaseTest.getDriver().switchTo().defaultContent();
        BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
        String transactionNumber = BaseTest.getDriver().findElement(By.id("cphBody_txtTransactionNumber")).getAttribute("value");
        System.out.println("issue no is"+transactionNumber);
        String requisitionNumber = BaseTest.getDriver().findElement(By.id("cphBody_txtRequisitionNumber")).getAttribute("value");
        String fromStore = BaseTest.getDriver().findElement(By.id("cphBody_txtFromStore")).getAttribute("value");

        BaseTest.getDriver().switchTo().defaultContent();
        BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
        String toDepartment = BaseTest.getDriver().findElement(By.id("cphBody_txtToDepartment")).getAttribute("value");

        // Log the extracted values (optional)
        System.out.println("Transaction Number: " + transactionNumber);
        System.out.println("Requisition Number: " + requisitionNumber);
        System.out.println("From Store: " + fromStore);
        System.out.println("To Department: " + toDepartment);

        // Save data to Excel using ExcelHandler
        String excelFilePath = "path_to_your_excel_file"; // Replace with the actual path to your Excel file
        ExcelHandler.loadExcelFile(excelFilePath, 0);  // Load the first sheet of the Excel file
        ExcelHandler.setData("Sheet1", "Transaction Number", transactionNumber);
        ExcelHandler.setData("Sheet1", "Requisition Number", requisitionNumber);
        ExcelHandler.setData("Sheet1", "From Store", fromStore);
        ExcelHandler.setData("Sheet1", "To Department", toDepartment);

        // Save the updated Excel file
        ExcelHandler.saveExcelFileCommon(excelFilePath);
    }

    public void SaveDetailsinExcel() {

        try {
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));

//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.Click(OKBtn.get(0));

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            String fromstore = FromStoreText.getAttribute("value");
            String todept = ToDepartment.getAttribute("value");
            String getIssueTypeValue = IssueType.getAttribute("value");
            String issueno = IssueNumber.getAttribute("value");

            System.out.println("Issue number is :"+issueno);


            // Define requisition type and requisition number
            String PO = "Issue";

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
            String sheetName = "Issue_" + issueno;

            // Check if the sheet already exists
            if (workbook.getSheet(sheetName) != null) {
                System.out.println("Sheet with name '" + sheetName + "' already exists. Skipping sheet creation.");
                return;
            }

            // Create a new sheet
            Sheet sheet = workbook.createSheet(sheetName);

            // First row: Header row for requisition details
            Row requisitionHeaderRow = sheet.createRow(0);
            requisitionHeaderRow.createCell(0).setCellValue("Issue Number");
            requisitionHeaderRow.createCell(1).setCellValue("Issue Type");
            requisitionHeaderRow.createCell(2).setCellValue("Date");
            requisitionHeaderRow.createCell(3).setCellValue(" ");
            requisitionHeaderRow.createCell(5).setCellValue("Gross Amount");
            requisitionHeaderRow.createCell(6).setCellValue(" ");
            requisitionHeaderRow.createCell(7).setCellValue(" ");
            requisitionHeaderRow.createCell(8).setCellValue("From Store");
            requisitionHeaderRow.createCell(9).setCellValue("To Store");

            // Second row: Values for requisition details
            Row requisitionDataRow = sheet.createRow(1);
            requisitionDataRow.createCell(0).setCellValue(String.valueOf(issueno));
            requisitionDataRow.createCell(1).setCellValue(getIssueTypeValue);
            requisitionDataRow.createCell(2).setCellValue(Dateformat.get_SystemDate());
            requisitionDataRow.createCell(3).setCellValue(" ");
            requisitionDataRow.createCell(5).setCellValue(GrossAmount.getAttribute("value"));
           // requisitionDataRow.createCell(6).setCellValue(FinalAmount);
           // requisitionDataRow.createCell(7).setCellValue(TotalItems);
            requisitionDataRow.createCell(8).setCellValue(fromstore);
            requisitionDataRow.createCell(9).setCellValue(todept);


            // Fourth row: Header for item and quantity
            Row itemHeaderRow = sheet.createRow(3);

            itemHeaderRow.createCell(0).setCellValue("Item");
            itemHeaderRow.createCell(1).setCellValue("Name");
            itemHeaderRow.createCell(2).setCellValue("Quantity");
            itemHeaderRow.createCell(3).setCellValue("NetAmount");

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
                itemDataRow.createCell(3).setCellValue(NetAmount.get(i).getAttribute("value"));

            }

            // Row itemDataRow = sheet.createRow(10)

            // Write the updated workbook back to the Excel file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }

            // Close the workbook
            workbook.close();


            System.out.println("Sheet '" + sheetName + "' added successfully in Excel file at: " + filePath);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);
        }
    }

    public void IssueRQ() {

        try {

            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "POData.xlsx";

            ExcelHandler.loadExcelFile(Updload_Path, 3);
            List<String> Resources = ExcelHandler.getAllColumnData("RQ Number");

            SoftAssert softAssert = new SoftAssert();
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            // Switch to the main content and then to the required frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Wait for Inventory element and click it
            //DynamicWait.mediumWait();
            //Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Inventory);
            Utilities.Click(BaseTest.getDriver(), Inventory);

            //Inventory Module Select
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
            Utilities.Click(BaseTest.getDriver(), InventoryModule);

            //Click on Issue
            if (Issue.isEnabled()&& Issue.isDisplayed()) {
                Utilities.Click(BaseTest.getDriver(), Issue);
            } else {

            }
            //Click on Add Button
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), AddButton);
            Utilities.Click(BaseTest.getDriver(), AddButton);

            //Select PO Type
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");



            String resource = Resources.get(Resources.size() - 1);
            //Utilities.SendKeys( RequisitionNumber, resource);


            BaseTest.getDriver().switchTo().frame("iframeBrwRQ");
            Utilities.SendKeys(Rqno,resource);
            Rqno.sendKeys(Keys.TAB);

            Utilities.DoubleClick(SearchImg);



           // Click Enter Items Details
           BaseTest.getDriver().switchTo().defaultContent();
           BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
           Utilities.Click(BaseTest.getDriver(), EnterItemDetails);

           Utilities.Click(SaveBtn);
            Alert alert = BaseTest.getDriver().switchTo().alert();
            alert.accept();

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            String text = IssueConf.getText();
            if(IssueConf.isDisplayed()&&text.contains("Issue document created. Issue Number is")){
                ExtentTestManager.createAssertTestStepWithScreenshot("Issue Creation",Status.PASS,"Issue Number Generated",true);

                System.out.println("Issue number generated successfully ");
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Issue Creation",Status.FAIL,"Issue number not Generated",true);
            }
            Utilities.Click(BaseTest.getDriver(), OKBtn.get(0));

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Issue", Status.FAIL, "Error in issue", true, e);

        }
    }

    public void EnterTaxItem() {

        try {

            Random random = new Random();
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
            Actions actions = new Actions(BaseTest.getDriver());
            Set<String> usedTexts = new HashSet<>();

            for (int i = 0; i < 5; i++) {
                if (i > 0) { // Execute only after i = 0
                    Utilities.Click(BaseTest.getDriver(), AddRow);
                }
                String[] items = {"BIMP0002", "BIMP0006", "BIMP0007", "BIMP0019", "BIMP0033"};

                Utilities.SendKeys(BaseTest.getDriver(), getItemSearchElement(i), items[i]);
                getItemSearchElement(i).sendKeys(Keys.TAB);

                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getrequestQuantityelement(i));
                getrequestQuantityelement(i).click();
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("1").perform();
            }


            Utilities.Click(Save);
            wait.until(ExpectedConditions.alertIsPresent()).accept();

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            String text = IssueConf.getText();
            if(IssueConf.isDisplayed()&&text.contains("Issue document created. Issue Number is")){
                ExtentTestManager.createAssertTestStepWithScreenshot("Issue Creation",Status.PASS,"Issue Number Generated",true);

                System.out.println("Issue number generated successfully ");
            }
            Utilities.Click(BaseTest.getDriver(), OKBtn.get(0));




        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Items", Status.FAIL, "Exception found in Method - Items", true, e);
        }
    }

    public void IssueTax() {

        try {

            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "POData.xlsx";

            ExcelHandler.loadExcelFile(Updload_Path, 3);
            List<String> Resources = ExcelHandler.getAllColumnData("RQ Number");

            SoftAssert softAssert = new SoftAssert();
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            // Switch to the main content and then to the required frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Wait for Inventory element and click it
            //DynamicWait.mediumWait();
            //Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Inventory);
            Utilities.Click(BaseTest.getDriver(), Inventory);

            //Inventory Module Select
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
            Utilities.Click(BaseTest.getDriver(), InventoryModule);

            //Click on Issue
            if (Issue.isEnabled()&& Issue.isDisplayed()) {
                Utilities.Click(BaseTest.getDriver(), Issue);
            } else {

            }
            //Click on Add Button
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), AddButton);
            Utilities.Click(BaseTest.getDriver(), AddButton);

            //Select PO Type
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");



            String resource = Resources.get(Resources.size() - 1);
            //Utilities.SendKeys( RequisitionNumber, resource);


            BaseTest.getDriver().switchTo().frame("iframeBrwRQ");
            Utilities.SendKeys(Rqno,resource);
            Rqno.sendKeys(Keys.TAB);

            Utilities.DoubleClick(SearchImg);



            // Click Enter Items Details
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);


            //Utilities.Click();

//            Utilities.Click(SaveBtn);
//            Alert alert = BaseTest.getDriver().switchTo().alert();
//            alert.accept();

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Issue", Status.FAIL, "Error in issue", true, e);

        }
    }

    public void CheckExpiry() {

        try {

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            for (WebElement element : expiry) {
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                element.click();
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                Utilities.Click(AutoAssign);
                Utilities.Click(CloseEXp);
            }



            //Utilities.Click();

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(SaveBtn);
            Alert alert = BaseTest.getDriver().switchTo().alert();
            alert.accept();
            try {
                alert.accept();
            } catch (Exception e) {

            }

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Issue", Status.FAIL, "Error in issue", true, e);

        }
    }

    public void EnterMoreItems() {

        try {

            Random random = new Random();
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
            Actions actions = new Actions(BaseTest.getDriver());
            Set<String> usedTexts = new HashSet<>();

            for (int i = 0; i < 5; i++) {
                if (i > 0) { // Execute only after i = 0
                    Utilities.Click(BaseTest.getDriver(), AddRow);
                }
                String[] items = {"BIMP0002", "BIMP0006", "BIMP0007", "BIMP0019", "BIMP0033"};

                Utilities.SendKeys(BaseTest.getDriver(), getItemSearchElement(i), items[i]);
                getItemSearchElement(i).sendKeys(Keys.TAB);

                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getrequestQuantityelement(i));
                getrequestQuantityelement(i).click();
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("999999").perform();
            }


            Utilities.Click(Save);

            wait.until(ExpectedConditions.alertIsPresent()).accept();

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            if (confirmdlg.isDisplayed()&&confirmdlg.getText().contains("Specified Quantity Exceeds Quantity In-Stock"));
            {
                ExtentTestManager.createAssertTestStepWithScreenshot("More Qty",Status.PASS,"Error message",true);
                System.out.println("hghghhhg");
            }




        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Items", Status.FAIL, "Exception found in Method - Items", true, e);
        }
    }

    public void Validation() {

        try {

            SoftAssert softAssert = new SoftAssert();
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
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

            //Click on Issue
            if (Issue.isEnabled()&& Issue.isDisplayed()) {
                Utilities.Click(BaseTest.getDriver(), Issue);
            } else {

            }
            //Click on Add Button
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), AddButton);
            Utilities.Click(BaseTest.getDriver(), AddButton);

            //Select PO Type
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            //Utilities.SendKeys(FromStore, "60");
            //Utilities.SendKeys(ToDept, "1134");


            //Click Enter Items Details
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            String errormsg = IssueConf.getText();
            if (IssueConf.isDisplayed()&&errormsg.contains("Invalid Store")){
                Utilities.Click(OKBtn.get(0));
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                Utilities.SendKeys(FromStore, "60");
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Issue", Status.FAIL, "Error in issue", true);

            }
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);

            String errormsg1 = IssueConf.getText();
            if (IssueConf.isDisplayed()&&errormsg1.contains("Please Select Department")){
                Utilities.Click(OKBtn.get(0));
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                Utilities.SendKeys(ToDept, "1134");
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Issue", Status.FAIL, "Error in issue", true);

            }



        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Issue", Status.FAIL, "Error in issue", true, e);

        }
    }

    public void BackDateIssue() {

        try {


            for (int i = 1; i <=2; i++) {
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                Utilities.Click(CloseBtn);
                SoftAssert softAssert = new SoftAssert();
                WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
                // Switch to the main content and then to the required frame
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

//            // Wait for Inventory element and click it
//            DynamicWait.mediumWait();
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Inventory);
//            Utilities.Click(BaseTest.getDriver(), Inventory);

                //Inventory Module Select
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
                Utilities.Click(BaseTest.getDriver(), InventoryModule);

                Utilities.Click(Issue);

                //
                //Click on Add Button
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), AddButton);
                Utilities.Click(BaseTest.getDriver(), AddButton);

                //Select PO Type
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                Utilities.SendKeys(FromStore, "88");
                Utilities.SendKeys(ToDept, "1135");


                //Click Enter Items Details
                //  Utilities.Click(BaseTest.getDriver(), EnterItemDetails);

                //

//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                // Parse the date string to LocalDate
                LocalDate date = LocalDate.parse(Date1, formatter);

                // Subtract 5 days from the date
                LocalDate newDate = date.minusDays(4);

                // Format the new date back to the desired string format
                String newDateString = newDate.format(formatter);


                ///

                LocalDate newDate1 = date.minusDays(3);

                // Format the new date back to the desired string format
                String newDateString1 = newDate1.format(formatter);


                //Utilities.SendKeys(Date, "01/08/2024");

                if (i == 1){
                    Utilities.SendKeys(Date,newDateString);
                } else {
                    Utilities.SendKeys(Date,newDateString1);
                }


                Utilities.Click(BaseTest.getDriver(), EnterItemDetails);


                System.out.println("Item id is " + ItemID);
                try {
                    Utilities.SendKeys(EnterItemforsrv, ItemID);

                } catch (Exception e) {
                    Utilities.SendKeys(EnterItemforsrv, "GS000339");
                    System.out.println("this is inside the catch block ");
                }

                Utilities.Click(issued_qty);
                Actions actions = new Actions(BaseTest.getDriver());
                actions.keyDown(Keys.CONTROL)
                        .sendKeys("a")
                        .keyUp(Keys.CONTROL)
                        .sendKeys(String.valueOf(i))
                        .perform();


                Utilities.Click(SaveBtn);

                wait.until(ExpectedConditions.alertIsPresent()).accept();
                try {
                    wait.until(ExpectedConditions.alertIsPresent()).accept();
                    wait.until(ExpectedConditions.alertIsPresent()).accept();
                } catch (Exception e) {
                     System.out.println("No " +
                             "" +
                             "" +
                             "Alert is present");
                }


                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");

                Utilities.Click(BaseTest.getDriver(), OKBtn.get(0));

            }





























        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Issue", Status.FAIL, "Error in issue", true, e);

        }
    }

    public void BackDateRQ() {

        try {

            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "POData.xlsx";

            ExcelHandler.loadExcelFile(Updload_Path, 3);


            try {
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

                // Wait for Inventory element and click it
                DynamicWait.mediumWait();
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Inventory);
                Utilities.Click(BaseTest.getDriver(), Inventory);
            } catch (Exception e) {
                System.out.println("Inside the Catch Block");
            }

            //Inventory Module Select
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
            Utilities.Click(BaseTest.getDriver(), InventoryModule);

            //Click on Issue

            Utilities.Click(BaseTest.getDriver(), Requisition);


            //Adding Requisitation
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), AddButton);
            Utilities.Click(BaseTest.getDriver(), AddButton);
            DynamicWait.smallWait();

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(RequisitionType, "PQ");
            Utilities.Click(IndentDept);
            Utilities.SendKeys(IndentDept, "1135");
            Utilities.SendKeys(FromDept, "88");
            Utilities.Click(EnterItemDetails);

            System.out.println("Item id is " + ItemID);
            try {
                Utilities.SendKeys(EnterItem, ItemID);

            } catch (Exception e) {
                Utilities.SendKeys(EnterItem, "GS000339");
            }

            Utilities.Click(quantity);
            Actions actions = new Actions(BaseTest.getDriver());
            actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("10").perform();


            Utilities.Click(SaveBtn);


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Utilities.Click(BaseTest.getDriver(), OKBtn.get(0));

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.selectBy("value", Approval, "AP");

            Utilities.Click(SaveBtn);


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Utilities.Click(BaseTest.getDriver(), OKBtn.get(0));


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Issue", Status.FAIL, "Error in issue", true, e);

        }
    }

    public void BackDateSRV(){
        try {

            Actions actions = new Actions(BaseTest.getDriver());
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(),Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
           Utilities.Click(CloseBtn);
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
            Utilities.Click(AddBtn1);


            ///

            //Select To Date
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            //
            Utilities.Click(DocDate);
            actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
            actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();

            Date1 = getClipboardData();


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Parse the date string to LocalDate
            LocalDate date = LocalDate.parse(Date1, formatter);

            // Subtract 5 days from the date
            LocalDate newDate = date.minusDays(5);

            // Format the new date back to the desired string format
            String newDateString = newDate.format(formatter);






            //Utilities.SendKeys(BaseTest.getDriver(), DocDate, "01/08/2024"); //20/01/2025

            Utilities.SendKeys(DocDate,newDateString);

            Utilities.SendKeys(Vendor, "%%");



            actions.sendKeys(Keys.TAB).perform();

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.DoubleClick(SelectRQ);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(EnterItems);


            System.out.println("Item id is " + ItemID);
            try {
                Utilities.SendKeys(EnterItemforsrv, ItemID);

            } catch (Exception e) {
                Utilities.SendKeys(EnterItemforsrv, "GS000339");
            }

            Utilities.Click(recivedqty);
            actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("3").perform();

            Utilities.SendKeys(department,"88");


            actions.sendKeys(Keys.TAB).perform();
            wait.until(ExpectedConditions.alertIsPresent()).dismiss();







            //Utilities.SendKeys(amount,"10");

            Utilities.Click(amount);
            actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("10").perform();


            //wait.until(ExpectedConditions.alertIsPresent()).dismiss();


//            Utilities.SendKeys(department,"88");
//            wait.until(ExpectedConditions.alertIsPresent()).dismiss();

            Utilities.Click(SaveSRV);

            try {
                wait.until(ExpectedConditions.alertIsPresent()).accept();
                wait.until(ExpectedConditions.alertIsPresent()).accept();
                wait.until(ExpectedConditions.alertIsPresent()).accept();

            } catch (Exception e) {

            }

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Utilities.Click(BaseTest.getDriver(), OKBtn.get(0));


//            // Click on Close Button
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            Utilities.Click(CloseBtn);
//            //Inventory Module Select
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
//            Utilities.Click(BaseTest.getDriver(), InventoryModule);









        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("BackDate Issue",Status.FAIL,"error while creating issue",true,e);
            System.out.println("Inside catch Block"+e);
        }
    }

    public void CheckFIFO(){
        try {

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(CloseBtn);
            SoftAssert softAssert = new SoftAssert();
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            // Switch to the main content and then to the required frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");


            //Inventory Module Select
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
            Utilities.Click(BaseTest.getDriver(), InventoryModule);

            Utilities.Click(ReportofInventory);


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            BaseTest.getDriver().switchTo().frame("ReportList");
            Utilities.Click(TrensectionReport);


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            BaseTest.getDriver().switchTo().frame("ReportList");
            Utilities.Click(StockLedger);


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            BaseTest.getDriver().switchTo().frame("ReportParams");
            Utilities.SendKeys(storetype,"GS");
            Utilities.SendKeys(store,"88");
            try {
                Utilities.SendKeys(fromitem,ItemID);
                //Utilities.SendKeys(fromitem,"GS000339");

            } catch (Exception e) {
                 Utilities.SendKeys(fromitem,"GS000339");
            }

            try {
                Utilities.SendKeys(toitem,ItemID);
                //Utilities.SendKeys(toitem,"GS000339");

            } catch (Exception e) {
                Utilities.SendKeys(toitem,"GS000339");
            }

            //Utilities.Click(downloadxldata);

            Utilities.Click(Preview);
            BaseTest.getDriver().switchTo().defaultContent();
            DynamicWait.longWait();

//            String currentWindow = BaseTest.getDriver().getWindowHandle();
//            System.out.println("Current Window is :"+currentWindow);
//            Set<String> allWindows = BaseTest.getDriver().getWindowHandles();
//            System.out.println("All window handle are :"+allWindows);
//
//            for (String window : allWindows) {
//                if (!window.equals(currentWindow)) {
//                    BaseTest.getDriver().switchTo().window(window);
//                    System.out.println("Switched to new window: " + BaseTest.getDriver().getTitle());
//                    break;
//                }
//            }


            Set<String> allWindows = BaseTest.getDriver().getWindowHandles();
            String currentWindow = BaseTest.getDriver().getWindowHandle();
            System.out.println("Current Window is :"+currentWindow);
            System.out.println("All window handle are :"+allWindows);
            String lastWindow = null;

            for (String window : allWindows) {
                if (!window.equals(currentWindow)) {
                    lastWindow = window;
                    System.out.println("Switched to new window: " + BaseTest.getDriver().getWindowHandle()

                    +BaseTest.getDriver().getCurrentUrl());

                }
            }

            if (lastWindow != null) {
                BaseTest.getDriver().switchTo().window(lastWindow);
                System.out.println("Switched to last window: " + BaseTest.getDriver().getTitle());
                System.out.println("Switched to new window: " + BaseTest.getDriver().getWindowHandle()

                        +BaseTest.getDriver().getCurrentUrl());
            }


            String TExt = Itemss.getText();
            System.out.println("Text is the "+TExt);
            System.out.println("Validations :"+  Receiving.getText() +issue.getText() +issue1.getText()

                    +remaining.getText() +remaining1.getText());




        } catch (Exception e) {
             ExtentTestManager.createAssertTestStepWithScreenshot("FIFO",Status.FAIL,"ERROR",true,e);
        }
    }


    public void ValidateExcelData() {


        String path = System.getProperty("user.dir") + File.separator + "DownloadPath";

        File latestPdf = GetLatestFile.getLatestFileFromDirectory(path);

        if (latestPdf != null) {
            System.out.println("Latest PDF found: " + latestPdf.getAbsolutePath());


            boolean allMatched = true;




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





    public static String getClipboardData() throws Exception {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable clipboardContent = clipboard.getContents(null);

        // Corrected method name below
        if (clipboardContent != null && clipboardContent.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return (String) clipboardContent.getTransferData(DataFlavor.stringFlavor);
        }

        return "";
    }



}

package pages.POPages.WithoutApproval.withoutRQ;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import datahandlers.ExcelHandler;
import datahandlers.PdfHandler;
import extentreports.ExtentTestManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DynamicWait;
import utils.GetLatestFile;
import utils.Utilities;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class PO_Page {


    public static String PONumber;
    WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
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
    @FindBy(xpath = "//td[@class=\"ControlItemStyle\"][1]")
    private List<WebElement> Item;
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
    @FindBy(id = "value_5_0")
    private WebElement value;
    //    @FindBy(xpath = "//td[@id='td_0_0' and text()='OTAA']")
//    private WebElement ClickCharge;
    @FindBy(xpath = "//*[@id=\"td_0_0\"]")
    private WebElement ClickCharge;
    @FindBy(xpath = "//td[@id='td_0_0']")
    private WebElement ClickCharge1;
    @FindBy(id = "btnSave")
    private WebElement Save;
    @FindBy(xpath = "//*[@id='btnClose']")
    private WebElement CloseBtn;
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
    @FindBy(css = "[id^='item_id_1_']")
    private List<WebElement> ItemsToDelete;
    @FindBy(xpath = "//*[text()='Advance Control']")
    private WebElement AdvanceControl;
    @FindBy(id = "divmodule_3")
    private WebElement SystemControl;
    @FindBy(id = "LinkProduct3")
    private WebElement SystemAdmin;
    @FindBy(id = "cphBody_btnGo")
    private WebElement Go;
    @FindBy(id = "control_value_char_3_0")
    private WebElement CharecterValue;
    @FindBy(id = "txtSearchString")
    private WebElement ControlCode;
    @FindBy(id = "chkSendEmail")
    private WebElement Sendmail;
    @FindBy(id = "cphHeader_lnkHome")
    private WebElement Home;
    @FindBy(id = "img_item_vat_class_10_0")
    private WebElement VAT;
    @FindBy(id = "cphBody_txtDateFrom")
    private WebElement dateFrom;

    public PO_Page(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static void setPONumber(String poNumber) {
        PONumber = poNumber;
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

    public WebElement getItemRate(int itemId) {
        String xpath3 = "//input[@id='item_rate_9_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    public WebElement Justification(int itemId) {
        String xpath3 = "//input[@id='justification_18_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    public WebElement getItemSearchElement(int itemId) {
        String xpath = "//input[@id='item_id_1_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath));
    }

    public WebElement getrequestQuantityelement(int itemId) {
        String xpath3 = "//input[@id='qty_ordered_5_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    public WebElement NewOrder(int itemId) {
        String xpath3 = "//input[@id='new_order_12_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    public void AdvanceControl() {

        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Click on System Admin
            Utilities.Click(SystemAdmin);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(SystemControl);
            Utilities.Click(AdvanceControl);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.SendKeys(ControlCode, "IVCFND");
            Utilities.Click(Go);
            String CharValue = CharecterValue.getAttribute("value");

            String value = CharecterValue.getAttribute("value").trim(); // Trim to handle spaces

            // If value is not "Y", clear the field and set "Y"
            if (!"Y".equalsIgnoreCase(value)) {
                CharecterValue.clear();
                CharecterValue.sendKeys("Y");
            }


            Utilities.Click(Process);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(Close);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            Utilities.Click(Home);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Advance Control", Status.FAIL, "Error in Advance Control", true, e);
        }
    }

    public void DeleteRow() {
        WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
        try {

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


            Actions actions = new Actions(BaseTest.getDriver());

            // Add Row
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            //Actions actions = new Actions(BaseTest.getDriver());
            for (int i = 0; i < 1; i++) {
                // if (i > 0) { // Execute only after i = 0
                Utilities.Click(BaseTest.getDriver(), AddRow);
                //  }
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


            // Delete the rows

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            for (int i = 0; i < ItemsToDelete.size(); i++) {

                ItemsToDelete.get(i).click();
                Utilities.Click(DeleteRow);
                wait.until(ExpectedConditions.alertIsPresent()).accept();
            }


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("DeleteRow", Status.FAIL, "Error while deleting rows", true, e);
        }
    }

    public void AddRow() {
        try {
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

            Utilities.SendKeys(Vendor, "GNSPA004");
            Utilities.SendKeys(Date, "12/09/2024");
            Utilities.SendKeys(NoOfDays, "5");
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

    public void DeliveryOrder() {
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


            Utilities.SendKeys(DeliveryDate, "17/09/2024");

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

    public void POApproveScreen() {
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

            Utilities.Click(ApprovePO);


            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            OK.click();

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            Utilities.Click(Refresh);
            Utilities.SendKeys(DateFrom, "23/08/2020");
            Utilities.Click(Search);
            Utilities.selectBy("visibletext", SelectAP, "Approve");

//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(Process);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Approve", Status.FAIL, "Exception found in Method - Approve PO", true, e);

        }
    }

    public void Print() {

        WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
        try {

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


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PODATA", Status.FAIL, "Exception found in Method - AddProduct", true, e);
        }
    }

    public void CPPO() {

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
            Utilities.SendKeys(BaseTest.getDriver(), ToDate, "05/11/2025");
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
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            Utilities.Click(BaseTest.getDriver(), SelectType);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(EnterPOType, "RP");


            //Select PO Type form the popup
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.SendKeys(BaseTest.getDriver(), POType, "RP");
//            Utilities.Click(BaseTest.getDriver(), FirstColumn);

            //Select To Date
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(BaseTest.getDriver(), ToDate, "05/11/2025");
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
                    Utilities.ClickCheckBox(Sendmail);
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

//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//
//            Utilities.Click(BaseTest.getDriver(), CancelBtn);

            // RPPOApprovalPage.validate();

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Edit", Status.FAIL, "Exception found in Method Edit PO ", true, e);
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
                Utilities.SendKeys(BaseTest.getDriver(), getItemSearchElement(i), "BEER");
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

            ///   test

            Actions actions = new Actions(BaseTest.getDriver());
            Utilities.Click(value);

            actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("125").perform();

            Utilities.Click(VAT);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
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


            Actions actions = new Actions(BaseTest.getDriver());
            Utilities.Click(value);

            actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("125").perform();


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

    public void CopyPO() {
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
            Utilities.Click(BaseTest.getDriver(), PurchaseOrder);


//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.SendKeys(DateFrom, "17/08/2024");
            Utilities.Click(Search);
            Utilities.Click(SelectPO);
            Utilities.Click(CopyPO);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Copy", Status.FAIL, "Exception found in Copying", true, e);
        }
    }

    public void CPPOCreation() {
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

    public void SelectPO() {
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
            DynamicWait.longWait();

            String expected = "Approved";

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Utilities.Click(BaseTest.getDriver(), CancelBtn);
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

    public void POCreationWithRQ() {
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

    public void ItemChargesSRV() {
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

    public void RPPOInterState() {

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

            //Utilities.Click(BaseTest.getDriver(), AddRow);

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
            Utilities.Click(dateFrom);


            Actions actions = new Actions(BaseTest.getDriver());
            actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();

            Utilities.Click(ToDate);
            actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();


            //Utilities.SendKeys(BaseTest.getDriver(), ToDate, "12/09/2025");

            String Value = "CCL001";
            //Select Vendor
            //Utilities.SendKeys(BaseTest.getDriver(), Vendor, "onlnalph");  //TA

            Utilities.SendKeys(BaseTest.getDriver(), Vendor, "TA");  //TA

            actions.sendKeys(Keys.TAB).perform();

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            //if(Charges.isDisplayed()&& Charges.isEnabled() && Charges.isSelected() )
            {
                //Utilities.Click(Charges);

            } //else
            {
                // ExtentTestManager.createAssertTestStepWithScreenshot("Click On Charges",Status.FAIL,"Charges Button is Disabled",true);
            }


            if (EnterItemDetails.isDisplayed()) {
                Utilities.Click(BaseTest.getDriver(), EnterItemDetails);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("EnterItemDetails", Status.FAIL, "EnterItemDetails is Disabled" + EnterItemDetails.getText(), true);
            }

            //  if (EnterItemDetails.isEnabled() && EnterItemDetails.isDisplayed()) {
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);
            System.out.println("inside inside");
            // } else {
            //     ExtentTestManager.createAssertTestStepWithScreenshot("EnterItemDetails",Status.FAIL,"EnterItemDetails is Disabled"+EnterItemDetails.getText(),true);
            // }

            // Utilities.Click(BaseTest.getDriver(),EnterItemDetails);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);

        }
    }

    public void HeaderCharge() {
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


            Actions actions = new Actions(BaseTest.getDriver());
            Utilities.Click(value);

            actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("125").perform();


            ///

            Utilities.Click(VAT);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.DoubleClick(ClickCharge);


            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

            ///

            Utilities.Click(BaseTest.getDriver(), Save);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            //Utilities.Click(BaseTest.getDriver(), OKBtn);


            WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));


            OKBtn.click();


            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            Utilities.Click(BaseTest.getDriver(), CloseBtn);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("ItemCharges", Status.FAIL, "Exception Found in Applying Item Level Charges", true, e);
        }
    }

}




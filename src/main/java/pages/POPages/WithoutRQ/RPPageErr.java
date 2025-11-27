package pages.POPages.WithoutRQ;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utils.DynamicWait;
import utils.Utilities;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Author: SACHIN KUMAR (replace sachin with your name or a fixed string)
 * Date: 23-01-2025
 */
public class RPPageErr {

    public static String PONumber;
    public static String RequisitionNumber;
    WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
    @FindBy(xpath = "//*[starts-with(@id, 'free_of_cost_27_')]")
    List<WebElement> FreeofCost;
    @FindBy(xpath = "//*[starts-with(@id, 'freeze_29_')]")
    List<WebElement> Freeze;
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
    @FindBy(id = "lblErrorDesc")
    private WebElement confDlg;

    @FindBy(id = "btnOK")
    private WebElement ok;
    @FindBy(id = "cphBody_txtType")
    private WebElement poType;
    @FindBy(id = "cphBody_txtDateFrom")
    private WebElement fromDate;
    /**
     * Web Elements
     */


    @FindBy(id = "btnOK")
    private WebElement OKBtn;
    @FindBy(id = "cphBody_txtTypeDesc")
    private WebElement getPOType;
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
    @FindBy(id = "cphBody_lblApproveRemarks")
    private WebElement Reason;
    @FindBy(id = "TextAreaValue_remarksApproval")
    private WebElement Remark;
    @FindBy(xpath = "//a[@href='#' and @onclick='HideDiv_remarksApproval();']")
    private WebElement CloseImg;
    @FindBy(xpath = "//*[starts-with(@id,'tot_amount_11_')]")
    private List<WebElement> totalAmount;
    @FindBy(xpath = "//input[starts-with(@id,'tot_amount_11_')]")
    private List<WebElement> TotalAmount;
    @FindBy(id = "cphBody_lblReason")
    private WebElement reason;
    @FindBy(id = "txtReason")
    private WebElement enterReason;

    /**
     * Constructor
     */

    public RPPageErr(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static void setPONumber(String poNumber) {
        PONumber = poNumber;
    }

    public static void setRequisitionNumber(String requisitionNumber) {

        RequisitionNumber = requisitionNumber;
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

    public WebElement getItemSearchElement(int itemId) {
        String xpath = "//input[@id='item_id_1_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath));
    }

    public WebElement getrequestQuantityelement(int itemId) {
        String xpath3 = "//input[@id='qty_ordered_5_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    public WebElement Justification(int itemId) {
        String xpath3 = "//input[@id='justification_18_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    public WebElement getItemRate(int itemId) {
        String xpath3 = "//input[@id='item_rate_9_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    //id="item_tax_class_16_0"
    public WebElement getItemTax(int itemId) {
        String xpath3 = "//input[@id='item_tax_class_16_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    /**
     * Methods
     */

    // WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
    // method to create PO without Requisition
    public void POCreation() {
        try {
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.INFO, "Starting PO Creation process", false);

            // Switch to the main content and then to the required frame
            //ExtentTestManager.createAssertTestStepWithScreenshot("Frame Switching", Status.INFO, "Switching to default content and then to MultiPageiframeBrw frame", false);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Wait for Inventory element and click it
            DynamicWait.mediumWait();
            ExtentTestManager.createAssertTestStepWithScreenshot("Inventory Click", Status.INFO, "Waiting for Inventory element to be displayed", false);
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Inventory);
            Utilities.Click(BaseTest.getDriver(), Inventory);
            ExtentTestManager.createAssertTestStepWithScreenshot("Inventory Click", Status.PASS, "Successfully clicked on Inventory", true);

            // Inventory Module Select
            // ExtentTestManager.createAssertTestStepWithScreenshot("Frame Switching", Status.INFO, "Switching back to default content and MultiPageiframeBrw frame", false);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            ExtentTestManager.createAssertTestStepWithScreenshot("Inventory Module", Status.INFO, "Waiting for Inventory Module element", false);
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
            Utilities.Click(BaseTest.getDriver(), InventoryModule);
            ExtentTestManager.createAssertTestStepWithScreenshot("Inventory Module", Status.PASS, "Successfully clicked on Inventory Module", true);

            // Click on Purchase Order
            ExtentTestManager.createAssertTestStepWithScreenshot("Purchase Order", Status.INFO, "Clicking on Purchase Order", false);
            Utilities.Click(BaseTest.getDriver(), PurchaseOrder);
            ExtentTestManager.createAssertTestStepWithScreenshot("Purchase Order", Status.PASS, "Successfully clicked on Purchase Order", true);

            // Click on Add Button
            // ExtentTestManager.createAssertTestStepWithScreenshot("Frame Switching", Status.INFO, "Switching frames for Add Button", false);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            ExtentTestManager.createAssertTestStepWithScreenshot("Add Button", Status.INFO, "Waiting for Add Button to be displayed", false);
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), AddButton);
            Utilities.Click(BaseTest.getDriver(), AddButton);
            ExtentTestManager.createAssertTestStepWithScreenshot("Add Button", Status.PASS, "Successfully clicked on Add Button", true);

            // Select PO Type
            //ExtentTestManager.createAssertTestStepWithScreenshot("Frame Switching", Status.INFO, "Switching to MultiPageiframeDlg for PO Type", false);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            ExtentTestManager.createAssertTestStepWithScreenshot("PO Type", Status.INFO, "Selecting PO Type", false);
            Utilities.Click(BaseTest.getDriver(), SelectType);
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Type", Status.PASS, "Successfully clicked on PO Type dropdown", true);

            // Select PO Type from the popup
            //ExtentTestManager.createAssertTestStepWithScreenshot("Frame Switching", Status.INFO, "Switching to iframeGridDialog for PO Type selection", false);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            ExtentTestManager.createAssertTestStepWithScreenshot("PO Type Selection", Status.INFO, "Entering PO Type 'RP'", false);
            Utilities.SendKeys(BaseTest.getDriver(), POType, "RP");
            Utilities.Click(BaseTest.getDriver(), FirstColumn);
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Type Selection", Status.PASS, "Successfully selected PO Type 'RP'", true);

            // Select To Date
            //ExtentTestManager.createAssertTestStepWithScreenshot("Frame Switching", Status.INFO, "Switching back to MultiPageiframeDlg for date selection", false);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            ExtentTestManager.createAssertTestStepWithScreenshot("To Date", Status.INFO, "Setting To Date to 12/09/2025", false);
            Utilities.SendKeys(BaseTest.getDriver(), ToDate, "12/09/2025");
            ExtentTestManager.createAssertTestStepWithScreenshot("To Date", Status.PASS, "Successfully set To Date to 12/09/2025", true);

            // Select Vendor
            String Value = "CCL001";
            ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Selection", Status.INFO, "Starting vendor selection process", false);
            Utilities.SendKeys(BaseTest.getDriver(), Vendor, "%%");

            Actions actions = new Actions(BaseTest.getDriver());
            actions.sendKeys(Keys.TAB).perform();
            ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Selection", Status.INFO, "Pressed TAB key after vendor search", false);

            //ExtentTestManager.createAssertTestStepWithScreenshot("Frame Switching", Status.INFO, "Switching to iframeGridDialog for vendor selection", false);
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

            Random random = new Random();
            System.out.println(VendorList);
            int randomIndex = random.nextInt(VendorList.size());

            ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Selection", Status.INFO, "Selecting random vendor from list", false);
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
            String vendorlist = VendorList.get(randomIndex).getText();
            Utilities.SendKeys(BaseTest.getDriver(), Filter, vendorlist);
            DynamicWait.longWait();
            ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Selection", Status.INFO, "Selected vendor: " + vendorlist, false);

            //ExtentTestManager.createAssertTestStepWithScreenshot("Frame Switching", Status.INFO, "Switching back to iframeGridDialog", false);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.DoubleClick(Cancel);
            ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Selection", Status.PASS, "Completed vendor selection process", true);

            try {
                // ExtentTestManager.createAssertTestStepWithScreenshot("Frame Switching", Status.INFO, "Attempting to close vendor dialog if present", false);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                Utilities.Click(BaseTest.getDriver(), Close);
                ExtentTestManager.createAssertTestStepWithScreenshot("Dialog Close", Status.INFO, "Closed vendor dialog successfully", false);
            } catch (Exception e) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Dialog Close", Status.INFO, "No Close Button found - continuing execution", false);
            }

            // ExtentTestManager.createAssertTestStepWithScreenshot("Frame Switching", Status.INFO, "Switching back to MultiPageiframeDlg", false);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(BaseTest.getDriver(), Search_vendor, vendorlist);
            ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Search", Status.PASS, "Successfully searched for vendor: " + vendorlist, true);

            // Click Enter Items Details
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Items", Status.INFO, "Clicking on Enter Items Details", false);
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Items", Status.PASS, "Successfully clicked on Enter Items Details", true);


            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Items", Status.INFO, "Starting item entry process", false);

            // Random random = new Random();
            //ExtentTestManager.createAssertTestStepWithScreenshot("Frame Switching", Status.INFO, "Switching to default content and MultiPageiframeDlg frame", false);
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));

            // Actions actions = new Actions(BaseTest.getDriver());
            Set<String> usedTexts = new HashSet<>();

            for (int i = 0; i < 2; i++) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Item Entry", Status.INFO, "Processing item #" + (i + 1), false);

                if (i > 0) { // Execute only after i = 0
                    ExtentTestManager.createAssertTestStepWithScreenshot("Add Row", Status.INFO, "Adding new row for item", false);
                    Utilities.Click(BaseTest.getDriver(), AddRow);
                    ExtentTestManager.createAssertTestStepWithScreenshot("Add Row", Status.PASS, "Successfully added new row", true);
                }

                ExtentTestManager.createAssertTestStepWithScreenshot("Item Search", Status.INFO, "Searching for 'wire' in item search", false);
                Utilities.SendKeys(BaseTest.getDriver(), getItemSearchElement(i), "wire");
                getItemSearchElement(i).sendKeys(Keys.TAB);

                //ExtentTestManager.createAssertTestStepWithScreenshot("Frame Switching", Status.INFO, "Switching to iframeGridDialog for item selection", false);
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("iframeGridDialog")));

                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ItemFilter);
                String text = Item.get(i).getText();

                // Check for unique text
                while (usedTexts.contains(text)) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Item Selection", Status.INFO, "Duplicate item found, selecting alternative", false);
                    int randomIndex1 = random.nextInt(Item.size() - 2);
                    text = Item.get(randomIndex1).getText();
                }

                usedTexts.add(text);
                ExtentTestManager.createAssertTestStepWithScreenshot("Item Selection", Status.INFO, "Selecting unique item: " + text, false);
                Utilities.SendKeys(BaseTest.getDriver(), ItemFilter, text);
                DynamicWait.smallWait();
                Utilities.Click(BaseTest.getDriver(), FirstColumn);
                ExtentTestManager.createAssertTestStepWithScreenshot("Item Selection", Status.PASS, "Successfully selected item: " + text, true);

                try {
                    if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
                        BaseTest.getDriver().switchTo().alert().accept();
                        ExtentTestManager.createAssertTestStepWithScreenshot("Alert Handling", Status.INFO, "Handled unexpected alert", false);
                    }
                } catch (Exception e) {
                    // No alert present
                }

                //ExtentTestManager.createAssertTestStepWithScreenshot("Frame Switching", Status.INFO, "Switching back to MultiPageiframeDlg", false);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

                try {
                    if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
                        BaseTest.getDriver().switchTo().alert().accept();
                        ExtentTestManager.createAssertTestStepWithScreenshot("Alert Handling", Status.INFO, "Handled second unexpected alert", false);
                    }
                } catch (Exception e) {
                    // No alert present
                }

                ExtentTestManager.createAssertTestStepWithScreenshot("Quantity Entry", Status.INFO, "Entering quantity for item", false);
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getrequestQuantityelement(i));
                getrequestQuantityelement(i).click();
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("100").perform();
                ExtentTestManager.createAssertTestStepWithScreenshot("Quantity Entry", Status.PASS, "Successfully entered quantity: 100", true);

                // Enter Rate
                // ExtentTestManager.createAssertTestStepWithScreenshot("Frame Switching", Status.INFO, "Switching frames for rate entry", false);
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));

                ExtentTestManager.createAssertTestStepWithScreenshot("Rate Entry", Status.INFO, "Processing rate for item", false);
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getItemRate(i));
                getItemRate(i).click();

                float ItemRate = Float.parseFloat((getItemRate(i).getAttribute("value")));
                if (ItemRate > 0) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Rate Entry", Status.INFO, "Keeping existing rate: " + ItemRate, false);
                } else {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Rate Entry", Status.INFO, "Setting new rate: 125", false);
                    actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("125").perform();
                    ExtentTestManager.createAssertTestStepWithScreenshot("Rate Entry", Status.PASS, "Successfully set rate to 125", true);
                }

                try {
                    // ExtentTestManager.createAssertTestStepWithScreenshot("Justification", Status.INFO, "Adding justification text", false);
                    Utilities.SendKeys(BaseTest.getDriver(), Justification(i), "Testing");
                    // ExtentTestManager.createAssertTestStepWithScreenshot("Justification", Status.PASS, "Added justification: 'Testing'", true);
                } catch (Exception e) {
                    // ExtentTestManager.createAssertTestStepWithScreenshot("Justification", Status.WARNING, "Could not add justification text", false);
                }
            }

//            ExtentTestManager.createAssertTestStepWithScreenshot("Save Button", Status.PASS,
//                    "Successfully clicked Save button", false);
//            ExtentTestManager.createAssertTestStepWithScreenshot("Purchase Order Number Confirmation ", Status.PASS,
//                    "Purchase Order Number successfully generated", false);


//
//            ExtentTestManager.createAssertTestStepWithScreenshot("OK Button", Status.PASS,
//                    "Successfully clicked OK button", false);
//            ExtentTestManager.createAssertTestStepWithScreenshot("Reason Field", Status.PASS,
//                    "Successfully clicked reason field", false);
//            ExtentTestManager.createAssertTestStepWithScreenshot("Reason Entry", Status.INFO,
//                    "Entering reason text", false);
            ExtentTestManager.createAssertTestStepWithScreenshot("Application Program Error", Status.WARNING, "Got Unexpected Error : Application Program Error", false);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Items", Status.FAIL, "Clicking on Enter Items Details", true);

        }
    }

    public void EnterItems() {
        try {
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Items", Status.INFO, "Starting item entry process", false);

            Random random = new Random();
            //ExtentTestManager.createAssertTestStepWithScreenshot("Frame Switching", Status.INFO, "Switching to default content and MultiPageiframeDlg frame", false);
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));

            Actions actions = new Actions(BaseTest.getDriver());
            Set<String> usedTexts = new HashSet<>();

            for (int i = 0; i < 2; i++) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Item Entry", Status.INFO, "Processing item #" + (i + 1), false);

                if (i > 0) { // Execute only after i = 0
                    ExtentTestManager.createAssertTestStepWithScreenshot("Add Row", Status.INFO, "Adding new row for item", false);
                    Utilities.Click(BaseTest.getDriver(), AddRow);
                    ExtentTestManager.createAssertTestStepWithScreenshot("Add Row", Status.PASS, "Successfully added new row", true);
                }

                ExtentTestManager.createAssertTestStepWithScreenshot("Item Search", Status.INFO, "Searching for 'wire' in item search", false);
                Utilities.SendKeys(BaseTest.getDriver(), getItemSearchElement(i), "wire");
                getItemSearchElement(i).sendKeys(Keys.TAB);

                //ExtentTestManager.createAssertTestStepWithScreenshot("Frame Switching", Status.INFO, "Switching to iframeGridDialog for item selection", false);
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("iframeGridDialog")));

                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ItemFilter);
                String text = Item.get(i).getText();

                // Check for unique text
                while (usedTexts.contains(text)) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Item Selection", Status.INFO, "Duplicate item found, selecting alternative", false);
                    int randomIndex1 = random.nextInt(Item.size() - 2);
                    text = Item.get(randomIndex1).getText();
                }

                usedTexts.add(text);
                ExtentTestManager.createAssertTestStepWithScreenshot("Item Selection", Status.INFO, "Selecting unique item: " + text, false);
                Utilities.SendKeys(BaseTest.getDriver(), ItemFilter, text);
                DynamicWait.smallWait();
                Utilities.Click(BaseTest.getDriver(), FirstColumn);
                ExtentTestManager.createAssertTestStepWithScreenshot("Item Selection", Status.PASS, "Successfully selected item: " + text, true);

                try {
                    if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
                        BaseTest.getDriver().switchTo().alert().accept();
                        ExtentTestManager.createAssertTestStepWithScreenshot("Alert Handling", Status.INFO, "Handled unexpected alert", false);
                    }
                } catch (Exception e) {
                    // No alert present
                }

                //ExtentTestManager.createAssertTestStepWithScreenshot("Frame Switching", Status.INFO, "Switching back to MultiPageiframeDlg", false);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

                try {
                    if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
                        BaseTest.getDriver().switchTo().alert().accept();
                        ExtentTestManager.createAssertTestStepWithScreenshot("Alert Handling", Status.INFO, "Handled second unexpected alert", false);
                    }
                } catch (Exception e) {
                    // No alert present
                }

                ExtentTestManager.createAssertTestStepWithScreenshot("Quantity Entry", Status.INFO, "Entering quantity for item", false);
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getrequestQuantityelement(i));
                getrequestQuantityelement(i).click();
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("100").perform();
                ExtentTestManager.createAssertTestStepWithScreenshot("Quantity Entry", Status.PASS, "Successfully entered quantity: 100", true);

                // Enter Rate
                //ExtentTestManager.createAssertTestStepWithScreenshot("Frame Switching", Status.INFO, "Switching frames for rate entry", false);
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));

                ExtentTestManager.createAssertTestStepWithScreenshot("Rate Entry", Status.INFO, "Processing rate for item", false);
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getItemRate(i));
                getItemRate(i).click();

                float ItemRate = Float.parseFloat((getItemRate(i).getAttribute("value")));
                if (ItemRate > 0) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Rate Entry", Status.INFO, "Keeping existing rate: " + ItemRate, false);
                } else {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Rate Entry", Status.INFO, "Setting new rate: 125", false);
                    actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("125").perform();
                    ExtentTestManager.createAssertTestStepWithScreenshot("Rate Entry", Status.PASS, "Successfully set rate to 125", true);
                }

                try {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Justification", Status.INFO, "Adding justification text", false);
                    Utilities.SendKeys(BaseTest.getDriver(), Justification(i), "Testing");
                    ExtentTestManager.createAssertTestStepWithScreenshot("Justification", Status.PASS, "Added justification: 'Testing'", true);
                } catch (Exception e) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Justification", Status.WARNING, "Could not add justification text", false);
                }
            }

            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Items", Status.PASS, "Successfully entered all items", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Justification", Status.WARNING, "Could not add justification text", false);

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
            softAssert.assertEquals("[1021] -Please Enter Vendor Id.", confDlg.getText());

            Utilities.Click(ok);
            Actions actions = new Actions(BaseTest.getDriver());


            // Enter Vendor ID
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(BaseTest.getDriver(), Vendor, "%%");
            actions.sendKeys(Keys.TAB).perform();


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            softAssert.assertEquals("[1067] - Please Enter Po Type.", confDlg.getText());
            Utilities.Click(ok);


            //Select PO Type
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(poType, "RP");
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            softAssert.assertEquals("[1021] -Please Enter Vendor Id.", confDlg.getText());
            Utilities.Click(ok);

            // Enter Vendor ID
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(BaseTest.getDriver(), Vendor, "%%");
            actions.sendKeys(Keys.TAB).perform();
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            softAssert.assertEquals("[1178] - Please enter Date To.", confDlg.getText());
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




package pages.POPages;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
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
import utils.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: SACHIN KUMAR (replace sachin with your name or a fixed string)
 * Date: 23-01-2025
 */
public class POWithoutReq {

    /**
     * Constructor
     */

    public POWithoutReq(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static String PONumber;


    /**
     * Web Elements
     */

    @FindBy(id = "cphBody_ddlApproved")
    private WebElement Approval;
    @FindBy(id = "btnOK")
    private WebElement OKBtn;

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
    @FindBy(id = "cphBody_txtTypeDesc")
    private WebElement getPOType;
    @FindBy(id = "cphBody_txtDateTo")
    private WebElement ToDate;
    @FindBy(id = "cphBody_txtVendor")
    private WebElement Search_vendor;
    @FindBy(id = "cphBody_btnEnterItemDetails")
    private WebElement EnterItemDetails;
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
    @FindBy(id = "cphBody_txtVendorDesc")
    private WebElement Vendor;
    @FindBy(xpath = "//input[starts-with(@id,'item_rate_9_')]")
    private List<WebElement> Rate;

    @FindBy(xpath = "//input[starts-with(@id,'tot_amount_11_')]")
    private List<WebElement> TotalAmount;


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

    public WebElement getItemRate(int itemId) {
        String xpath3 = "//input[@id='item_rate_9_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    public WebElement Justification(int itemId) {
        String xpath3 = "//input[@id='justification_18_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }


    /**
     * Methods
     */

    // method to create PO without Requisition
    public void POCreation() {

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
            Utilities.SendKeys(BaseTest.getDriver(), Search_vendor, "CCL001");

            //Click Enter Items Details
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);

        }
    }

    public void EnterItems() {
        try {

            Random random = new Random();
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
            Actions actions = new Actions(BaseTest.getDriver());
            Set<String> usedTexts = new HashSet<>();

            for (int i = 0; i < 2; i++) {
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
                actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("100").perform();

                try {
                    Utilities.SendKeys(BaseTest.getDriver(), Justification(i), "Testing");
                } catch (Exception e) {

                }
            }

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Items", Status.FAIL, "Exception found in Method - Items", true, e);
        }
    }

    public void SaveDetailsinExcel() {

        try {
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            String vendor = Vendor.getText();
            String getPOTypeValue = getPOType.getText();


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


            Utilities.Click(BaseTest.getDriver(), SaveBtn);
            try {
                wait.until(ExpectedConditions.alertIsPresent()).accept();
            } catch (Exception e) {

            }
           // BaseTest.getDriver().switchTo().defaultContent();
            try {
                wait.until(ExpectedConditions.alertIsPresent()).accept();
            } catch (Exception e) {

            }

            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), PODialougue);
            String PoDialouge = PODialougue.getText();
            if (PoDialouge.contains("Purchase Order Number")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.PASS, "PO Created successfully with ", true);

            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.FAIL, "Exception found in Method - PO Creation", true);

            }
            //Getting All resoureces from Testdata excel sheet
            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "TestData.xlsx";


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            if (ProductConfirmation.isDisplayed() && ProductConfirmation.getText().contains("Purchase Order Number")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Purchase Order Number Confirmation ", Status.PASS, "Purchase Order Number successfully generated", true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Purchase Order NumberConfirmation", Status.FAIL, "Issue in Purchase Order Number", true);
            }


            String input = ProductConfirmation.getText();

            // Regular expression to match the last number in the string
            Pattern pattern = Pattern.compile("Purchase Order Number\\s(\\d+)");

            Matcher matcher = pattern.matcher(input);

            // Regular expressions to match specific fields

            Pattern totalAmountPattern = Pattern.compile("Total Amount\\s([0-9.]+)");
            Pattern finalAmountPattern = Pattern.compile("Final Amount\\s([0-9.]+)");
            Pattern totalItemsPattern = Pattern.compile("Total Items\\s(\\d+)");

            // Matchers for each field
            Matcher totalAmountMatcher = totalAmountPattern.matcher(input);
            Matcher finalAmountMatcher = finalAmountPattern.matcher(input);
            Matcher totalItemsMatcher = totalItemsPattern.matcher(input);

            // Extract values if they match
            String TotalAmount = totalAmountMatcher.find() ? totalAmountMatcher.group(1) : "Not Found";
            String FinalAmount = finalAmountMatcher.find() ? finalAmountMatcher.group(1) : "Not Found";
            String TotalItems = totalItemsMatcher.find() ? totalItemsMatcher.group(1) : "Not Found";

            // Print the extracted values
            System.out.println("Total Amount: " + TotalAmount);
            System.out.println("Final Amount: " + FinalAmount);
            System.out.println("Total Items: " + TotalItems);


            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(TotalAmount,sum, "Total Amount Mismatch ");

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
                requisitionDataRow.createCell(1).setCellValue(getPOTypeValue);
                requisitionDataRow.createCell(2).setCellValue(Dateformat.get_SystemDate());
                requisitionDataRow.createCell(3).setCellValue(vendor);
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
            }
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);
        }
    }
}




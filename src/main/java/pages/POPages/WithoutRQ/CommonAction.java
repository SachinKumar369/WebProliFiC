package pages.POPages.WithoutRQ;

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
import templateCreate.TempCreate;
import utils.Dateformat;
import utils.DynamicWait;
import utils.GetLatestFile;
import utils.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonAction {

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

    @FindBy(id = "btnOK")
    private WebElement FirstColumn;
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
    @FindBy(id = "cphBody_txtVendorDesc")
    private WebElement Vendor;
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

    public CommonAction(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static void setRequisitionNumber(String requisitionNumber) {

        RequisitionNumber = requisitionNumber;
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

    //id="item_tax_class_16_0"
    public WebElement getItemTax(int itemId) {
        String xpath3 = "//input[@id='item_tax_class_16_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    public WebElement Justification(int itemId) {
        String xpath3 = "//input[@id='justification_18_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    public void EnterItems() {

        try {

            Random random = new Random();
            // WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
            Actions actions = new Actions(BaseTest.getDriver());
            Set<String> usedTexts = new HashSet<>();

            for (int i = 0; i < 2; i++) {
                if (i > 0) { // Execute only after i = 0
                    Utilities.Click(BaseTest.getDriver(), AddRow);
                }
                Utilities.SendKeys(BaseTest.getDriver(), getItemSearchElement(i), "beer");  //wire
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

    public void EnterItemsFreeofCost() {

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
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            for (WebElement checkbox : FreeofCost) {
                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
            }


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Items", Status.FAIL, "Exception found in Method - Items", true, e);
        }
    }

    public void EnterVATItems() {

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


            ///  ADD VAT
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Items", Status.FAIL, "Exception found in Method - Items", true, e);
        }
    }

    public void Freezes() {

        try {


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
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

            WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));

            try {
                OKBtn.click();
                System.out.println("OK Button clicked successfully!");
            } catch (Exception e) {
                System.out.println("Normal click failed, trying JavaScript click.");
                ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].click();", OKBtn);
            }


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            for (WebElement element : Freeze) {
                element.click();
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


        } catch (Exception e) {

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


            Utilities.Click(BaseTest.getDriver(), SaveBtn);
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
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);
        }
    }

    public void SaveRQNumber() {

        String POData = "POData";
        //String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + POData + ".xlsx"; // File name remains constant for adding new sheets
        // int sheetIndex = 0; // Assuming "Sheet1" is the first sheet


        String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "POData.xlsx";

        try {
            {
                // Load the Excel file
                ExcelHandler.loadExcelFile(Updload_Path, 0);

                // Find the first empty row in the Excel sheet
                int rowCount = ExcelHandler.getRowCount();

                // Set the "Requisition Number" in the next available row


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


                Pattern purchaseOrderPattern = Pattern.compile("Purchase Order Number\\s*(\\d+)");

                Matcher purchaseOrderMatcher = purchaseOrderPattern.matcher(input);

                String PurchaseOrderNumber = purchaseOrderMatcher.find() ? purchaseOrderMatcher.group(1) : "Not Found";

                ExcelHandler.setCellData("RQ Number", String.valueOf(PurchaseOrderNumber), rowCount);

                // Set the "User" in the same row
                // ExcelHandler.setCellData("User", UserName, rowCount);

                // Save changes to the Excel file
                ExcelHandler.saveExcelFileCommon(Updload_Path);
            }


            System.out.println("Data written successfully!");
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PODATA", Status.FAIL, "Exception found in Method - AddProduct", true, e);
        }


    }

    public void SaveReq() {

        String POData = "POData";
        String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "POData.xlsx";

        String input = ProductConfirmation.getText();

        // Regular expression to match the last number in the string
        Pattern pattern = Pattern.compile("Requisition Number\\s(\\d+)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            RequisitionNumber = matcher.group(1);
        }
        setRequisitionNumber(RequisitionNumber);
        System.out.println("Requisition Number: " + RequisitionNumber);


        if (ProductConfirmation.isDisplayed()) {
            // Load the Excel file
            ExcelHandler.loadExcelFile(Updload_Path, 1);

            // Find the first empty row in the Excel sheet
            int rowCount = ExcelHandler.getRowCount();

            // Set the "Requisition Number" in the next available row
            ExcelHandler.setCellData("Requisition Number", String.valueOf(RequisitionNumber), rowCount);

            // Set the "User" in the same row
            // ExcelHandler.setCellData("User", UserName, rowCount);

            // Save changes to the Excel file
            ExcelHandler.saveExcelFile();
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

    public void Reject() {
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


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.selectBy("value", Rejected, "RJ");


            Utilities.Click(Reason);
            Utilities.SendKeys(Remark, "Testing");

            Utilities.Click(CloseImg);

//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            // Click Save Button
            Utilities.Click(SaveBtn);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Rejection", Status.FAIL, "Error in Rejection", true, e);
        }
    }

    public void Approve() {
        try {

        } catch (Exception e) {

        }
    }

    public void EnterItemsInterState() {

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

    public void EnterItemz() {

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

    public void EnterUnitItems() {
        try {
            Random random = new Random();
            // WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
            Actions actions = new Actions(BaseTest.getDriver());
            Set<String> usedTexts = new HashSet<>();

            String[] items = {"FDSS0114", "PRFZ0003", "FRIN0031", "VGIN0106", "INDG0208"};

            for (int i = 0; i < items.length; i++) {
                if (i > 0) { // Execute only after i = 0
                    Utilities.Click(BaseTest.getDriver(), AddRow);
                }
                Utilities.SendKeys(BaseTest.getDriver(), getItemSearchElement(i), items[i]);
                getItemSearchElement(i).sendKeys(Keys.TAB);
//                try {
//                    wait.until(ExpectedConditions.alertIsPresent()).accept();
//
//                } catch (Exception e) {
//
//                }

//                BaseTest.getDriver().switchTo().defaultContent();
//                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//
//                try {
//                    wait.until(ExpectedConditions.alertIsPresent()).accept();
//
//                } catch (Exception e) {
//
//                }


//                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getrequestQuantityelement(i));
//                getrequestQuantityelement(i).click();
//                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("100").perform();
//
//
//                // Enter Rate
//                BaseTest.getDriver().switchTo().defaultContent();
//                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
//                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getItemRate(i));
//                getItemRate(i).click();
//
//                float ItemRate = Float.parseFloat((getItemRate(i).getAttribute("value")));
//                if (ItemRate > 0) {
//                    // actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("100").perform();
//                } else {
//                    actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("125").perform();
//
//                }
//
//                try {
//                    Utilities.SendKeys(BaseTest.getDriver(), Justification(i), "Testing");
//                } catch (Exception e) {
//
//                }


            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void ItemTax() {
        try {
            Random random = new Random();
            // WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
            Actions actions = new Actions(BaseTest.getDriver());
            Set<String> usedTexts = new HashSet<>();

            String[] items = {"WIIM0002", "IMPG0528", "FDBC0235", "ENOT1379", "BSSB0048"};

            for (int i = 0; i < items.length; i++) {
                if (i > 0) { // Execute only after i = 0
                    Utilities.Click(BaseTest.getDriver(), AddRow);
                }
                Utilities.SendKeys(BaseTest.getDriver(), getItemSearchElement(i), items[i]);
                getItemSearchElement(i).sendKeys(Keys.TAB);
//                try {
//                    wait.until(ExpectedConditions.alertIsPresent()).accept();
//
//                } catch (Exception e) {
//
//                }

//                BaseTest.getDriver().switchTo().defaultContent();
//                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//
//                try {
//                    wait.until(ExpectedConditions.alertIsPresent()).accept();
//
//                } catch (Exception e) {
//
//                }


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
            throw new RuntimeException(e);
        }
    }

    public void ValidateItemTax() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));

            try {
                OKBtn.click();
                System.out.println("OK Button clicked successfully!");
            } catch (Exception e) {
                System.out.println("Normal click failed, trying JavaScript click.");
                ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].click();", OKBtn);
            }

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            List<String> taxValues = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                WebElement element = getItemTax(i);
                String value = element.getAttribute("value");
                taxValues.add(value);
            }
            System.out.println(taxValues);


            List<String> expectedValues = Arrays.asList("VAT1", "GST2", "GST0", "GST3", "GST4");
            SoftAssert softAssert = new SoftAssert();

            for (int i = 0; i < expectedValues.size(); i++) {
                String expected = expectedValues.get(i);
                String actual = taxValues.get(i);

                if (actual.equals(expected)) {
                    System.out.println("✅ Match at index " + i + ": " + actual);
                } else {
                    System.out.println("❌ Mismatch at index " + i + ": expected=" + expected + ", actual=" + actual);
                }
                softAssert.assertEquals(actual, expected,
                        "❌ Mismatch at index " + i + ": expected=" + expected + ", actual=" + actual);
            }
            softAssert.assertAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void validateFreeOfCost() {
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

            SoftAssert softAssert = new SoftAssert();

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            for (int i = 0; i < totalAmount.size(); i++) {
                String value = totalAmount.get(i).getAttribute("value");
                softAssert.assertEquals(value, "0.00",
                        "❌ Mismatch at index " + i + ": Expected 0.00 but found " + value);
                System.out.println("Value are " + totalAmount.get(i).getAttribute("value"));

            }

            softAssert.assertAll();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void EnterMoreItemz() {
        try {
            Utilities.logerror("Validations", logMessages -> {


                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                System.out.println("Switched into iframeGridDialog");

                WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));
                OKBtn.click();
                logMessages.add("Clicked on Add Button");

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

                Utilities.Click(BaseTest.getDriver(),reason);

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                Utilities.SendKeys(enterReason,"Testing");
                Actions actions = new Actions(BaseTest.getDriver());
                actions.click(enterReason)
                        .sendKeys(Keys.TAB)
                        .build()
                        .perform();



                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");


                if (ProductConfirmation.isDisplayed() && ProductConfirmation.getText().contains("Details Created/Updated")) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Template Creation Confirmation", Status.PASS,
                            "Template successfully generated", true);
                } else {
                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("iframeGridDialog");

                    BaseTest.getDriver().switchTo().frame("iframeGridDialog"); // Or correct frame if different
                    if (ProductConfirmation.isDisplayed() && ProductConfirmation.getText().contains("Application program error")) {

                        TempCreate tempCreate = new TempCreate(BaseTest.getDriver());
                        tempCreate.ApplicationProgError1();
                        ExtentTestManager.createAssertTestStepWithScreenshot("Application Error", Status.FAIL,
                                "Application error occurred during AddProduct execution", true);
                        // Optionally click OK or close on error popup if needed
                        // Utilities.Click(BaseTest.getDriver(), ApplicationErrorOKButton);
                        return; // Exit early if needed
                    }

                }
            });
        } catch (Exception e) {

            try {
                // Check if ApplicationError element is displayed (you can define this WebElement or use By locator)
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");

                BaseTest.getDriver().switchTo().frame("iframeGridDialog"); // Or correct frame if different
                if (ProductConfirmation.isDisplayed() && ProductConfirmation.getText().contains("Application program error")) {
                    TempCreate.ApplicationProgError();
                    ExtentTestManager.createAssertTestStepWithScreenshot("Application Error", Status.FAIL,
                            "Application error occurred during AddProduct execution", true);
                    // Optionally click OK or close on error popup if needed
                    // Utilities.Click(BaseTest.getDriver(), ApplicationErrorOKButton);
                    return; // Exit early if needed
                }
            } catch (Exception innerEx) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Application", Status.FAIL, "Error while handling applicaton prog error", true, e);
            }


            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);
        }    }

    public void EnterMoreItemz1() {
        try {
            // Start of process logging
//            ExtentTestManager.createAssertTestStepWithScreenshot("Enter More Items", Status.INFO,
//                    "Starting additional item entry process", false);

            Utilities.logerror("Validations", logMessages -> {
                try {



                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                    logMessages.add("Switched into iframeGridDialog");

                    // Click OK button
                    ExtentTestManager.createAssertTestStepWithScreenshot("OK Button", Status.INFO,
                            "Waiting for OK button to be clickable", false);
                    WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));
                    OKBtn.click();
                    logMessages.add("Clicked on OK Button");



                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                    logMessages.add("Switched to MultiPageiframeDlg");

                    // Click reason field
                    ExtentTestManager.createAssertTestStepWithScreenshot("Reason Field", Status.INFO,
                            "Clicking reason field", false);
                    Utilities.Click(BaseTest.getDriver(), reason);
                    logMessages.add("Clicked reason field");


                    // Enter reason text

                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                    Utilities.SendKeys(enterReason, "Testing");
                    logMessages.add("Entered reason: Testing");

                    // Press TAB key
                    Actions actions = new Actions(BaseTest.getDriver());
                    actions.click(enterReason)
                            .sendKeys(Keys.TAB)
                            .build()
                            .perform();
                    ExtentTestManager.createAssertTestStepWithScreenshot("Reason Entry", Status.PASS,
                            "Successfully entered reason and pressed TAB", true);
                    logMessages.add("Pressed TAB after reason entry");

                    // Check for confirmation message
                    ExtentTestManager.createAssertTestStepWithScreenshot("Confirmation Check", Status.INFO,
                            "Checking for confirmation message", false);
                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                    BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                    logMessages.add("Switched to nested iframeGridDialog");

                    if (ProductConfirmation.isDisplayed() && ProductConfirmation.getText().contains("Details Created/Updated")) {
                        ExtentTestManager.createAssertTestStepWithScreenshot("Template Creation Confirmation", Status.PASS,
                                "Template successfully generated", true);
                        logMessages.add("Template creation confirmed");
                    } else {
                        ExtentTestManager.createAssertTestStepWithScreenshot("Error Check", Status.INFO,
                                "Checking for application errors", false);
                        BaseTest.getDriver().switchTo().defaultContent();
                        BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                        BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                        logMessages.add("Checking for application errors");

                        if (ProductConfirmation.isDisplayed() && ProductConfirmation.getText().contains("Application program error")) {
                            TempCreate tempCreate = new TempCreate(BaseTest.getDriver());

                            tempCreate.ApplicationProgError1();
                            ExtentTestManager.createAssertTestStepWithScreenshot("Application Error", Status.FAIL,
                                    "Application error occurred during execution", false);
                            logMessages.add("Application error occurred");
                            return;
                        }
                    }
                } catch (Exception innerException) {
                    throw innerException;
                }
            });

//            ExtentTestManager.createAssertTestStepWithScreenshot("Enter More Items", Status.PASS,
//                    "Successfully completed additional item entry", true);

        } catch (Exception e) {
            try {
                // Error handling with proper logging
                ExtentTestManager.createAssertTestStepWithScreenshot("Error Handling", Status.INFO,
                        "Checking for application errors after exception", false);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");

                if (ProductConfirmation.isDisplayed() && ProductConfirmation.getText().contains("Application program error")) {
                    TempCreate.ApplicationProgError();
                    ExtentTestManager.createAssertTestStepWithScreenshot("Application Error", Status.FAIL,
                            "Application error occurred during execution", true);
                    return;
                }
            } catch (Exception innerEx) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Error Handling", Status.FAIL,
                        "Error while handling application error", true, innerEx);
            }

            throw e;
        }
    }


}

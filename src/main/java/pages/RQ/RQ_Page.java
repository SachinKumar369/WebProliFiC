package pages.RQ;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import datahandlers.ExcelHandler;
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
import utils.Dateformat;
import utils.DynamicWait;
import utils.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RQ_Page {
    /**
     * Constructor
     */
    public RQ_Page(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Web Elements
     */
    @FindBy(xpath = "//select[@id=\"cphHeader_ddlPropertyList\"]")
    private WebElement SwitchTo;
    @FindBy(xpath = "//a[@id='LinkProduct2']")
    private WebElement Inventory;
    @FindBy(id = "divmodule_2")
    private WebElement InventoryModule;
    @FindBy(xpath = "//a[normalize-space()='Requisitions']")
    private WebElement Requisitions;
    @FindBy(id = "cphBrowserHeader_btnAddNew")
    private WebElement AddButton;
    @FindBy(id = "cphBody_imgRequisitionType")
    private WebElement RequisitationType;
    @FindBy(xpath = "//input[@id='col_1']")
    private WebElement Filter;
    @FindBy(id = "btnOK")
    private WebElement FirstColumn;
    @FindBy(id = "cphBody_imgFromDeptStore")
    private WebElement FromDepartment;
    @FindBy(xpath = "//td[@class=\"ControlItemStyle\"][2]")
    private List<WebElement> DepartmentList;
    @FindBy(id = "cphBody_imgToDeptStore")
    private WebElement ToDepartment;
    @FindBy(id = "cphBody_btnEnterItemDetails")
    private WebElement EnterItemDetail;
    @FindBy(xpath = "//img[@id=\"img_item_id_1_0\"]")
    private WebElement ItemDropdown;
    @FindBy(xpath = "//td[@class=\"ControlItemStyle\"][1]")
    private List<WebElement> Item;
    @FindBy(xpath = "//input[@id='col_0']")
    private WebElement ItemFilter;
    @FindBy(xpath = "//input[@id=\"qty_req_7_0\"]")
    private WebElement RequestQuantity;
    @FindBy(id = "cphBody_btnAddRow_Top")
    private WebElement AddRow;
    @FindBy(id = "cphDialogHeader_btnSave")
    private WebElement SaveButton;
    @FindBy(id = "lblErrorDesc")
    private WebElement ProductConfirmation;
    @FindBy(xpath = "//input[@id='btnOK']")
    private WebElement OkButton;
    @FindBy(xpath = "//input[@id='FileDlg']")
    private WebElement ChooseFile;
    @FindBy(xpath = "//input[@id='btnUpLoadData']")
    private WebElement UploadData;
    @FindBy(id = "cphBody_btnDownLoadTemplate")
    private WebElement DownloadTemplate;
    @FindBy(xpath = "//input[starts-with(@id,'item_id_1_')]")
    private List<WebElement> Items;
    @FindBy(xpath = "//input[starts-with(@id,'org_req_qty_6_')]")
    private List<WebElement> Quantity;
    @FindBy(xpath = "//input[starts-with(@id,'item_desc_3_')]")
    private List<WebElement> ItemName;
    @FindBy(id = "cphBody_imgIndentDept")
    private WebElement IndentDept;
    @FindBy(id = "cphBody_imgFromDeptStore")
    private WebElement FromDept;
    @FindBy(id = "cphBody_txtunit_id")
    private WebElement Property;
    @FindBy(id = "cphBody_txtRequisitionType")
    private WebElement ReqType;
    @FindBy(id = "btnOK")
    private WebElement OKBtn;
    @FindBy(id = "cphDialogHeader_btnPrint")
    private WebElement Print;

    public WebElement getItemSearchElement(int itemId) {
        String xpath = "//input[@id='item_id_1_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath));
    }

    public WebElement getItemDropdownElement(int itemId) {
        String xpath2 = "//img[@id='img_item_id_1_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath2));
    }

    public WebElement getrequestQuantityelement(int itemId) {
        String xpath3 = "//input[@id='qty_req_7_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    @FindBy(id = "cphBody_txtDate")
    private WebElement date;
    public static String RequisitionNumber;

    public static void setRequisitionNumber(String requisitionNumber) {
        RequisitionNumber = requisitionNumber;
    }

    /**
     * @Method_Description : Add Requisition Type with consolidated logging using logerror
     * @Method_Name : IssueRQ
     * @Modified_By : Sachin Kumar
     * @Modified_Date : 23/05/25
     * @param UserName The username for the requisition
     * @param requisitionType The type of requisition to be created
     */
    public void IssueRQ1(String UserName, String requisitionType) {
        String stepDescription = "";
        try {
            stepDescription = "Issue Requisition";
            Utilities.logerror(stepDescription, logMessages -> {
                // Initialize Excel path
                String uploadPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" +
                        File.separator + "resources" + File.separator + "TestData" + File.separator + "TestData.xlsx";
                logMessages.add("Initialized Excel path: " + uploadPath);

                String toDepartment1 = "", toDepartment2 = "";
                Actions actions = new Actions(BaseTest.getDriver());
                Random random = new Random();

                // Switch to main content and frame
                logMessages.add("Switching to MultiPageiframeBrw frame");
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                logMessages.add("Successfully switched to MultiPageiframeBrw frame");

                // Navigate to Inventory
                logMessages.add("Waiting for Inventory element");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Inventory);
                logMessages.add("Clicking Inventory element");
                Utilities.Click(BaseTest.getDriver(), Inventory);
                logMessages.add("Successfully clicked Inventory");

                // Select Inventory Module
                logMessages.add("Switching to MultiPageiframeBrw frame for Inventory Module");
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                logMessages.add("Waiting for Inventory Module element");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
                logMessages.add("Clicking Inventory Module");
                Utilities.Click(BaseTest.getDriver(), InventoryModule);
                logMessages.add("Successfully clicked Inventory Module");

                // Select Requisitions
                logMessages.add("Waiting for Requisitions element");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Requisitions);
                logMessages.add("Clicking Requisitions");
                Utilities.Click(BaseTest.getDriver(), Requisitions);
                logMessages.add("Successfully clicked Requisitions");

                // Add New Requisition
                logMessages.add("Switching to MultiPageiframeBrw frame for Add Button");
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                logMessages.add("Waiting for Add Button");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), AddButton);
                logMessages.add("Clicking Add Button");
                Utilities.Click(BaseTest.getDriver(), AddButton);
                logMessages.add("Waiting after clicking Add Button");
                DynamicWait.smallWait();
                logMessages.add("Successfully clicked Add Button");

                // Select Requisition Type
                logMessages.add("Switching to MultiPageiframeDlg frame for Requisition Type");
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                logMessages.add("Waiting for Requisition Type field");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ReqType);
                logMessages.add("Entering requisition type: " + requisitionType);
                Utilities.SendKeys(ReqType, requisitionType);
                logMessages.add("Successfully entered requisition type");
                ExtentTestManager.createAssertTestStepWithScreenshot("Selecting Requisition Type: " + requisitionType,
                        Status.PASS, "Successfully entered requisition type: " + requisitionType, true);

                // Validate Indent Department for Issue Requisition
                if (requisitionType.equalsIgnoreCase("Issue Requisition")) {
                    logMessages.add("Validating Indent Department for Issue Requisition");
                    logMessages.add("Switching to MultiPageiframeDlg frame");
                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                    logMessages.add("Locating Indent Department field");
                    WebElement inputField = BaseTest.getDriver().findElement(By.id("cphBody_txtIndentDept"));
                    logMessages.add("Checking if Indent Department is disabled");
                    boolean isDisabled = inputField.getAttribute("readonly") != null &&
                            inputField.getAttribute("tabindex").equals("-1") &&
                            inputField.getAttribute("class").contains("readonlytextbox");
                    if (!isDisabled) {
                        throw new RuntimeException("Indent Department is enabled unexpectedly");
                    }
                    logMessages.add("Indent Department is disabled as expected");
                    ExtentTestManager.createAssertTestStepWithScreenshot("Validating Indent Department",
                            Status.PASS, "Indent Department is disabled as expected", true);
                }

                // Validate From Department
                logMessages.add("Validating From Department clickability");
                WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(2));
                WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(FromDepartment));
                logMessages.add("From Department is clickable");
                ExtentTestManager.createAssertTestStepWithScreenshot("Validating From Department",
                        Status.PASS, "From Department is clickable", true);

                // Validate To Department
                logMessages.add("Validating To Department clickability");
                clickableElement = wait.until(ExpectedConditions.elementToBeClickable(ToDepartment));
                logMessages.add("To Department is clickable");
                ExtentTestManager.createAssertTestStepWithScreenshot("Validating To Department",
                        Status.PASS, "To Department is clickable", true);

                // Validate Property Display
                logMessages.add("Validating Property visibility");
                if (Property.isDisplayed()) {
                    throw new RuntimeException("Property is displayed unexpectedly");
                }
                logMessages.add("Property is not displayed as expected");
                ExtentTestManager.createAssertTestStepWithScreenshot("Validating Property Display",
                        Status.PASS, "Property is not displayed as expected", true);

                // Select From Department
                logMessages.add("Selecting From Department");
                logMessages.add("Switching to MultiPageiframeDlg frame");
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                logMessages.add("Waiting for From Department element");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), FromDepartment);
                logMessages.add("Clicking From Department");
                Utilities.Click(BaseTest.getDriver(), FromDepartment);
                logMessages.add("Switching to iframeGridDialog frame");
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                logMessages.add("Waiting for Filter element");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
                int randomIndex = random.nextInt(DepartmentList.size());
                String fromDepartment = DepartmentList.get(randomIndex).getText();
                logMessages.add("Entering department: " + fromDepartment);
                Utilities.SendKeys(BaseTest.getDriver(), Filter, fromDepartment);
                logMessages.add("Waiting after entering department");
                DynamicWait.smallWait();
                logMessages.add("Clicking First Column");
                Utilities.Click(BaseTest.getDriver(), FirstColumn);
                logMessages.add("Successfully selected From Department: " + fromDepartment);

                // Select To or Indent Department
                logMessages.add("Selecting To/Indent Department");
                logMessages.add("Switching to MultiPageiframeDlg frame");
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                try {
                    logMessages.add("Attempting to select To Department");
                    logMessages.add("Waiting for To Department element");
                    Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ToDepartment);
                    logMessages.add("Clicking To Department");
                    Utilities.Click(BaseTest.getDriver(), ToDepartment);
                    logMessages.add("Switching to iframeGridDialog frame");
                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                    logMessages.add("Waiting for Filter element");
                    Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
                    int randomIndex2 = random.nextInt(DepartmentList.size());
                    toDepartment1 = DepartmentList.get(randomIndex2).getText();
                    logMessages.add("Entering department: " + toDepartment1);
                    Utilities.SendKeys(BaseTest.getDriver(), Filter, toDepartment1);
                    logMessages.add("Waiting after entering department");
                    DynamicWait.smallWait();
                    logMessages.add("Clicking First Column");
                    Utilities.Click(BaseTest.getDriver(), FirstColumn);
                    logMessages.add("Successfully selected To Department: " + toDepartment1);
                } catch (Exception e) {
                    logMessages.add("To Department selection failed, trying Indent Department");
                    logMessages.add("Switching to MultiPageiframeDlg frame");
                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                    logMessages.add("Waiting for Indent Department element");
                    Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), IndentDept);
                    logMessages.add("Clicking Indent Department");
                    Utilities.Click(BaseTest.getDriver(), IndentDept);
                    logMessages.add("Switching to iframeGridDialog frame");
                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                    logMessages.add("Waiting for Filter element");
                    Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
                    int randomIndex2 = random.nextInt(DepartmentList.size());
                    toDepartment2 = DepartmentList.get(randomIndex2).getText();
                    logMessages.add("Entering department: " + toDepartment2);
                    Utilities.SendKeys(BaseTest.getDriver(), Filter, toDepartment2);
                    logMessages.add("Waiting after entering department");
                    DynamicWait.smallWait();
                    logMessages.add("Clicking First Column");
                    Utilities.Click(BaseTest.getDriver(), FirstColumn);
                    logMessages.add("Successfully selected Indent Department: " + toDepartment2);
                }

                // Selecting the Date
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

                Utilities.Click(date);
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("11/07/2023").perform();

                // Enter Item Details
                logMessages.add("Entering Item Details");
                logMessages.add("Switching to MultiPageiframeDlg frame");
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                logMessages.add("Waiting for Enter Item Details button");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), EnterItemDetail);
                logMessages.add("Clicking Enter Item Details");
                Utilities.Click(BaseTest.getDriver(), EnterItemDetail);
                logMessages.add("Successfully clicked Enter Item Details");

                // Add Items
                Set<String> usedTexts = new HashSet<>();
                boolean isPage2Switched = false;
                int itemsFromPage1 = 15;

                for (int i = 0; i < 10; i++) {
                    logMessages.add("Adding Item " + (i + 1));
                    if (i > 0) {
                        logMessages.add("Waiting for Add Row button");
                        Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), AddRow);
                        logMessages.add("Clicking Add Row");
                        Utilities.Click(BaseTest.getDriver(), AddRow);
                        logMessages.add("Successfully added new row for item " + (i + 1));
                    }

                    logMessages.add("Waiting for Item Search element");
                    Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getItemSearchElement(i));
                    logMessages.add("Entering search term: %%");
                    Utilities.SendKeys(BaseTest.getDriver(), getItemSearchElement(i), "%%");
                    logMessages.add("Tabbing out of search field");
                    getItemSearchElement(i).sendKeys(Keys.TAB);
                    logMessages.add("Switching to iframeGridDialog frame");
                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                    logMessages.add("Waiting for Item Filter element");
                    Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ItemFilter);

                    if (i >= itemsFromPage1 && !isPage2Switched) {
                        try {
                            logMessages.add("Locating Page 2 button");
                            WebElement page2Button = BaseTest.getDriver().findElement(By.xpath("//select[@class='dropDown']"));
                            if (page2Button.isDisplayed() && page2Button.isEnabled()) {
                                logMessages.add("Switching to Page 2");
                                Utilities.selectBy(BaseTest.getDriver(), "index", page2Button, "3");
                                logMessages.add("Waiting for Item Filter after page switch");
                                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ItemFilter);
                                isPage2Switched = true;
                                logMessages.add("Successfully switched to Page 2");
                            }
                        } catch (NoSuchElementException e) {
                            logMessages.add("Page 2 button not found, continuing on current page");
                        }
                    }

                    int itemIndex = isPage2Switched ? i - itemsFromPage1 : i;
                    String text = Item.get(itemIndex).getText();
                    while (usedTexts.contains(text)) {
                        int randomInde = random.nextInt(Item.size() - 2);
                        text = Item.get(randomInde).getText();
                        logMessages.add("Duplicate item found, selecting new item: " + text);
                    }
                    usedTexts.add(text);
                    logMessages.add("Entering item filter: " + text);
                    Utilities.SendKeys(BaseTest.getDriver(), ItemFilter, text);
                    logMessages.add("Waiting after entering item filter");
                    DynamicWait.smallWait();
                    logMessages.add("Clicking First Column");
                    Utilities.Click(BaseTest.getDriver(), FirstColumn);
                    logMessages.add("Successfully selected item: " + text);

                    logMessages.add("Switching to MultiPageiframeDlg frame for quantity");
                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                    logMessages.add("Waiting for Quantity element");
                    Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getrequestQuantityelement(i));
                    logMessages.add("Clicking Quantity field");
                    getrequestQuantityelement(i).click();
                    logMessages.add("Entering quantity: 10");
                    actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("10").perform();
                    logMessages.add("Successfully entered quantity for item " + (i + 1));
                }

                // Save Requisition
                logMessages.add("Saving Requisition");
                logMessages.add("Switching to MultiPageiframeDlg frame");
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                logMessages.add("Waiting for Save Button");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), SaveButton);
                logMessages.add("Clicking Save Button");
                Utilities.Click(BaseTest.getDriver(), SaveButton);
                logMessages.add("Successfully clicked Save Button");

                // Verify Requisition Number
                logMessages.add("Verifying Requisition Number");
                logMessages.add("Switching to iframeGridDialog frame");
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                logMessages.add("Waiting for Product Confirmation element");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ProductConfirmation);
                logMessages.add("Checking Product Confirmation text");
                if (!ProductConfirmation.isDisplayed() || !ProductConfirmation.getText().contains("Requisition Number")) {
                    throw new RuntimeException("Requisition Number confirmation not displayed or incorrect");
                }
                logMessages.add("Product Confirmation displayed with Requisition Number");
                ExtentTestManager.createAssertTestStepWithScreenshot("Verifying Requisition Number",
                        Status.PASS, "Requisition Number successfully generated", true);

                // Extract Requisition Number
                String input = ProductConfirmation.getText();
                Pattern pattern = Pattern.compile("Requisition Number\\s(\\d+)");
                Matcher matcher = pattern.matcher(input);
                if (matcher.find()) {
                    RequisitionNumber = matcher.group(1);
                    setRequisitionNumber(RequisitionNumber);
                    logMessages.add("Extracted Requisition Number: " + RequisitionNumber);
                    ExtentTestManager.createAssertTestStepWithScreenshot("Extracting Requisition Number",
                            Status.PASS, "Extracted Requisition Number: " + RequisitionNumber, true);
                } else {
                    throw new RuntimeException("Failed to extract Requisition Number from confirmation text");
                }

                String xl = "Requisition";
                // Save to Excel
                logMessages.add("Saving Requisition to Excel");
                String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" +
                        File.separator + "resources" + File.separator + "TestData" + File.separator + xl + ".xlsx";
                logMessages.add("Creating Excel file path: " + filePath);
                File file = new File(filePath);
                file.getParentFile().mkdirs();
                logMessages.add("Ensured parent directories exist");

                Workbook workbook;
                if (file.exists()) {
                    logMessages.add("Loading existing Excel file");
                    try (FileInputStream fis = new FileInputStream(file)) {
                        workbook = new XSSFWorkbook(fis);
                        logMessages.add("Successfully loaded Excel file");
                    }
                } else {
                    logMessages.add("Creating new Excel workbook");
                    workbook = new XSSFWorkbook();
                    logMessages.add("Successfully created new Excel workbook");
                }

                String sheetName = "Requisition_" + RequisitionNumber;
                if (workbook.getSheet(sheetName) != null) {
                    logMessages.add("Sheet '" + sheetName + "' already exists, skipping creation");
                    workbook.close();
                    return;
                }

                logMessages.add("Creating new sheet: " + sheetName);
                Sheet sheet = workbook.createSheet(sheetName);
                Row requisitionHeaderRow = sheet.createRow(0);
                requisitionHeaderRow.createCell(0).setCellValue("Requisition Number");
                requisitionHeaderRow.createCell(1).setCellValue("User");
                requisitionHeaderRow.createCell(2).setCellValue("Date");
                requisitionHeaderRow.createCell(3).setCellValue("From Department");
                requisitionHeaderRow.createCell(4).setCellValue("To Department");
                requisitionHeaderRow.createCell(5).setCellValue("Indent Department");

                Row requisitionDataRow = sheet.createRow(1);
                requisitionDataRow.createCell(0).setCellValue(String.valueOf(RequisitionNumber));
                requisitionDataRow.createCell(1).setCellValue(String.valueOf(UserName));
                requisitionDataRow.createCell(2).setCellValue(Dateformat.get_SystemDate());
                requisitionDataRow.createCell(3).setCellValue(fromDepartment);
                requisitionDataRow.createCell(4).setCellValue(toDepartment1);
                requisitionDataRow.createCell(5).setCellValue(toDepartment2);
                logMessages.add("Added requisition details to Excel");

                Row itemHeaderRow = sheet.createRow(3);
                itemHeaderRow.createCell(0).setCellValue("Item");
                itemHeaderRow.createCell(1).setCellValue("Name");
                itemHeaderRow.createCell(2).setCellValue("Quantity");

                logMessages.add("Switching to MultiPageiframeDlg frame for item data");
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                int rowIndex = 4;
                int itemCount = Math.min(Items.size(), Quantity.size());
                logMessages.add("Processing " + itemCount + " items for Excel");
                for (int i = 0; i < itemCount; i++) {
                    Row itemDataRow = sheet.createRow(rowIndex++);
                    itemDataRow.createCell(0).setCellValue(Items.get(i).getAttribute("value"));
                    itemDataRow.createCell(1).setCellValue(ItemName.get(i).getAttribute("value"));
                    itemDataRow.createCell(2).setCellValue(Quantity.get(i).getAttribute("value"));
                    logMessages.add("Added item " + (i + 1) + " to Excel");
                }

                logMessages.add("Saving Excel file");
                try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                    workbook.write(fileOut);
                    logMessages.add("Successfully saved Excel file: " + filePath);
                }
                workbook.close();

                logMessages.add("Loading TestData.xlsx");
                ExcelHandler.loadExcelFile(uploadPath, 3);
                int rowCount = ExcelHandler.getRowCount();
                logMessages.add("Writing Requisition Number to TestData.xlsx");
                ExcelHandler.setCellData("Requisition Number", String.valueOf(RequisitionNumber), rowCount);
                logMessages.add("Writing User to TestData.xlsx");
                ExcelHandler.setCellData("User", UserName, rowCount);
                logMessages.add("Saving TestData.xlsx");
                ExcelHandler.saveExcelFile();
                logMessages.add("Successfully updated TestData.xlsx");
                ExtentTestManager.createAssertTestStepWithScreenshot("Saving Requisition to Excel",
                        Status.PASS, "Successfully saved requisition data to Excel", true);
            });
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("IssueRQ Failure", Status.FAIL,
                    "Failed in step: " + stepDescription + ". Error: " + e.getMessage(), true, e);
            throw new RuntimeException("IssueRQ failed at step: " + stepDescription, e);
        } finally {
            // Frame cleanup
            Utilities.logerror("Frame Cleanup", logMessages -> {
                logMessages.add("Switching back to default content");
                BaseTest.getDriver().switchTo().defaultContent();
                logMessages.add("Successfully switched to default content");
            });
        }
    }

    public void Print(){
        BaseTest.getDriver().switchTo().defaultContent();
        BaseTest.getDriver().switchTo().frame("iframeGridDialog");
        Utilities.Click(OKBtn);

        BaseTest.getDriver().switchTo().defaultContent();
        BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
        Utilities.Click(Print);
        BaseTest.getDriver().switchTo().defaultContent();
    }

    public void IssueRQ(String UserName, String requisitionType) {
        String stepDescription = "";
        try {
            stepDescription = "Issue Requisition";
            Utilities.logerror(stepDescription, logMessages -> {
                // Initialize Excel path with detailed logging
                //logMessages.add("Initializing test data paths...");
                String uploadPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" +
                        File.separator + "resources" + File.separator + "TestData" + File.separator + "TestData.xlsx";
                //logMessages.add("Excel test data path initialized: " + uploadPath);
                logMessages.add("Requisition type parameter received: " + requisitionType);
                logMessages.add("User name parameter received: " + UserName);

                String toDepartment1 = "", toDepartment2 = "";
                Actions actions = new Actions(BaseTest.getDriver());
                Random random = new Random();

                // Frame switching with detailed state tracking
                logMessages.add("Starting frame navigation...");
                //logMessages.add("Current frame state: " + BaseTest.getDriver().getWindowHandle());
                logMessages.add("Switching to default content before frame navigation");
                BaseTest.getDriver().switchTo().defaultContent();
                logMessages.add("Attempting to switch to MultiPageiframeBrw frame");
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                logMessages.add("Successfully switched to MultiPageiframeBrw frame");

                // Navigation flow with timing information
                logMessages.add("Starting navigation to Inventory module...");
                long startTime = System.currentTimeMillis();
                logMessages.add("Waiting for Inventory element to be displayed");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Inventory);
                logMessages.add("Clicking Inventory element");
                Utilities.Click(BaseTest.getDriver(), Inventory);
                logMessages.add("Inventory clicked successfully. Time taken: " +
                        (System.currentTimeMillis() - startTime) + "ms");

                // Inventory Module selection with state verification
                logMessages.add("Preparing to select Inventory Module...");
                BaseTest.getDriver().switchTo().defaultContent();
                //logMessages.add("Reswitching to MultiPageiframeBrw frame for Inventory Module");
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                logMessages.add("Waiting for Inventory Module element visibility");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
                logMessages.add("Clicking Inventory Module");
                Utilities.Click(BaseTest.getDriver(), InventoryModule);
                logMessages.add("Inventory Module selected successfully");

                // Requisitions navigation with element state logging
                logMessages.add("Navigating to Requisitions section...");
                logMessages.add("Waiting for Requisitions element to be clickable");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Requisitions);
                logMessages.add("Element state - Displayed: " + Requisitions.isDisplayed() +
                        ", Enabled: " + Requisitions.isEnabled());
                Utilities.Click(BaseTest.getDriver(), Requisitions);
                logMessages.add("Requisitions clicked successfully");

                // Add New Requisition with explicit wait logging
                //logMessages.add("Initiating new requisition creation...");
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                logMessages.add("Waiting for Add Button with explicit wait");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), AddButton);
                logMessages.add("Add Button state - Displayed: " + AddButton.isDisplayed() +
                        ", Enabled: " + AddButton.isEnabled());
                Utilities.Click(BaseTest.getDriver(), AddButton);
                logMessages.add("Add Button clicked. Applying small wait for UI update");
                DynamicWait.smallWait();
                //logMessages.add("UI wait completed");

                // Requisition Type selection with validation
                logMessages.add("Selecting requisition type...");
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                logMessages.add("Waiting for Requisition Type field");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ReqType);
                logMessages.add("Entering requisition type: " + requisitionType);
                Utilities.SendKeys(ReqType, requisitionType);
                logMessages.add("Requisition type entered successfully");
                ExtentTestManager.createAssertTestStepWithScreenshot("Selecting Requisition Type: " + requisitionType,
                        Status.PASS, "Successfully entered requisition type: " + requisitionType, true);

                // Enhanced department validation logging
                if (requisitionType.equalsIgnoreCase("Issue Requisition")) {
                    //logMessages.add("Validating Indent Department for Issue Requisition...");
                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                    logMessages.add("Locating Indent Department field with detailed attributes check");
                    WebElement inputField = BaseTest.getDriver().findElement(By.id("cphBody_txtIndentDept"));
                    logMessages.add("Field attributes - Readonly: " + inputField.getAttribute("readonly") +
                            ", Tabindex: " + inputField.getAttribute("tabindex") +
                            ", Class: " + inputField.getAttribute("class"));

                    boolean isDisabled = inputField.getAttribute("readonly") != null &&
                            inputField.getAttribute("tabindex").equals("-1") &&
                            inputField.getAttribute("class").contains("readonlytextbox");

                    if (!isDisabled) {
                        logMessages.add("VALIDATION FAILED: Indent Department is enabled unexpectedly");
                        throw new RuntimeException("Indent Department is enabled unexpectedly");
                    }
                    logMessages.add("Indent Department validation passed - disabled as expected");
                    ExtentTestManager.createAssertTestStepWithScreenshot("Validating Indent Department",
                            Status.PASS, "Indent Department is disabled as expected", true);
                }

                // Department validation with explicit wait logging
                //logMessages.add("Validating department fields...");
                WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(2));
                logMessages.add("Validating From Department clickability with 2s explicit wait");
                WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(FromDepartment));
                logMessages.add("From Department validation passed - element is clickable");

                //logMessages.add("Validating To Department clickability");
                clickableElement = wait.until(ExpectedConditions.elementToBeClickable(ToDepartment));
                logMessages.add("To Department validation passed - element is clickable");

                ExtentTestManager.createAssertTestStepWithScreenshot("Validating To Department",
                        Status.PASS, "To Department is clickable", true);

                // Property validation with detailed state logging
                logMessages.add("Validating Property field visibility...");
                logMessages.add("Property element state - Displayed: " + Property.isDisplayed() +
                        ", Enabled: " + Property.isEnabled());
                if (Property.isDisplayed()) {
                    logMessages.add("VALIDATION FAILED: Property is displayed unexpectedly");
                    throw new RuntimeException("Property is displayed unexpectedly");
                }
                logMessages.add("Property validation passed - not displayed as expected");
                ExtentTestManager.createAssertTestStepWithScreenshot("Validating Property Display",
                        Status.PASS, "Property is not displayed as expected", true);

                // Department selection with random choice logging
                //logMessages.add("Starting department selection process...");
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                //logMessages.add("Waiting for From Department element");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), FromDepartment);
                logMessages.add("Clicking From Department");
                Utilities.Click(BaseTest.getDriver(), FromDepartment);

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                //logMessages.add("Waiting for Filter element in department selection dialog");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);

                int randomIndex = random.nextInt(DepartmentList.size());
                String fromDepartment = DepartmentList.get(randomIndex).getText();
                logMessages.add("Selected random department index: " + randomIndex +
                        ", Department name: " + fromDepartment);
                logMessages.add("Entering department filter: " + fromDepartment);
                Utilities.SendKeys(BaseTest.getDriver(), Filter, fromDepartment);
                DynamicWait.smallWait();
                logMessages.add("Selecting first matching department");
                Utilities.Click(BaseTest.getDriver(), FirstColumn);
                logMessages.add("From Department selection completed: " + fromDepartment);

                // To/Indent Department selection with fallback logging
                logMessages.add("Starting To/Indent Department selection...");
                try {
                    logMessages.add("Attempting To Department selection first...");
                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                    Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ToDepartment);
                    Utilities.Click(BaseTest.getDriver(), ToDepartment);

                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                    Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);

                    int randomIndex2 = random.nextInt(DepartmentList.size());
                    toDepartment1 = DepartmentList.get(randomIndex2).getText();
                    logMessages.add("Selected random To Department index: " + randomIndex2 +
                            ", Department name: " + toDepartment1);
                    Utilities.SendKeys(BaseTest.getDriver(), Filter, toDepartment1);
                    DynamicWait.smallWait();
                    Utilities.Click(BaseTest.getDriver(), FirstColumn);
                    logMessages.add("To Department selection completed: " + toDepartment1);
                } catch (Exception e) {
                    logMessages.add("To Department selection failed, attempting Indent Department fallback. Error: " +
                            e.getMessage());

                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                    Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), IndentDept);
                    Utilities.Click(BaseTest.getDriver(), IndentDept);

                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                    Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);

                    int randomIndex2 = random.nextInt(DepartmentList.size());
                    toDepartment2 = DepartmentList.get(randomIndex2).getText();
                    logMessages.add("Selected random Indent Department index: " + randomIndex2 +
                            ", Department name: " + toDepartment2);
                    Utilities.SendKeys(BaseTest.getDriver(), Filter, toDepartment2);
                    DynamicWait.smallWait();
                    Utilities.Click(BaseTest.getDriver(), FirstColumn);
                    logMessages.add("Indent Department selection completed: " + toDepartment2);
                }

                // Date selection with action chain logging
                logMessages.add("Setting requisition date...");
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                logMessages.add("Performing date selection actions");
                Utilities.Click(date);
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("11/07/2023").perform();
                //logMessages.add("Date set to current date + 10 days");

                // Item details entry with pagination handling
                logMessages.add("Starting item details entry process...");
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), EnterItemDetail);
                Utilities.Click(BaseTest.getDriver(), EnterItemDetail);
                logMessages.add("Enter Item Details clicked");

                // Item addition with duplicate prevention
                logMessages.add("Adding items to requisition...");
                Set<String> usedTexts = new HashSet<>();
                boolean isPage2Switched = false;
                int itemsFromPage1 = 15;
                int totalItemsToAdd = 10;

                for (int i = 0; i < totalItemsToAdd; i++) {
                    logMessages.add(String.format("Processing item %d of %d", i+1, totalItemsToAdd));
                    if (i > 0) {
                        logMessages.add("Adding new row for additional item");
                        Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), AddRow);
                        Utilities.Click(BaseTest.getDriver(), AddRow);
                        logMessages.add("New row added successfully");
                    }

                    Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getItemSearchElement(i));
                    //logMessages.add("Entering wildcard search pattern for item " + (i+1));
                    Utilities.SendKeys(BaseTest.getDriver(), getItemSearchElement(i), "%%");
                    getItemSearchElement(i).sendKeys(Keys.TAB);

                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                    Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ItemFilter);

                    // Pagination handling with detailed logging
                    if (i >= itemsFromPage1 && !isPage2Switched) {
                        try {
                            logMessages.add("Attempting to switch to page 2 for more items");
                            WebElement page2Button = BaseTest.getDriver().findElement(By.xpath("//select[@class='dropDown']"));
                            if (page2Button.isDisplayed() && page2Button.isEnabled()) {
                                logMessages.add("Switching to page 2 using index 3");
                                Utilities.selectBy(BaseTest.getDriver(), "index", page2Button, "3");
                                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ItemFilter);
                                isPage2Switched = true;
                                logMessages.add("Successfully switched to page 2");
                            }
                        } catch (NoSuchElementException e) {
                            logMessages.add("Page navigation control not found, continuing with current page items");
                        }
                    }

                    int itemIndex = isPage2Switched ? i - itemsFromPage1 : i;
                    String text = Item.get(itemIndex).getText();
                    logMessages.add("Initial item text: " + text);

                    while (usedTexts.contains(text)) {
                        int randomInde = random.nextInt(Item.size() - 2);
                        text = Item.get(randomInde).getText();
                        logMessages.add("Duplicate item detected, new random selection: " + text);
                    }
                    usedTexts.add(text);

                    logMessages.add("Final item selected: " + text);
                    Utilities.SendKeys(BaseTest.getDriver(), ItemFilter, text);
                    DynamicWait.smallWait();
                    Utilities.Click(BaseTest.getDriver(), FirstColumn);
                    logMessages.add("Item selection confirmed");

                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                    Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getrequestQuantityelement(i));
                    getrequestQuantityelement(i).click();
                    logMessages.add("Setting quantity to 10 for item " + (i+1));
                    actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("10").perform();
                }

                // Save process with confirmation logging
                logMessages.add("Initiating requisition save process...");
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), SaveButton);
                Utilities.Click(BaseTest.getDriver(), SaveButton);
                logMessages.add("Save button clicked");

                // Requisition number verification with pattern matching
                logMessages.add("Verifying requisition number generation...");
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ProductConfirmation);

                String confirmationText = ProductConfirmation.getText();
                logMessages.add("Confirmation text received: " + confirmationText);

                if (!ProductConfirmation.isDisplayed() || !confirmationText.contains("Requisition Number")) {
                    logMessages.add("VALIDATION FAILED: "+confirmationText);
                    throw new RuntimeException("Requisition Number confirmation not displayed "+confirmationText);
                }
                logMessages.add("Requisition confirmation validated successfully");

                Pattern pattern = Pattern.compile("Requisition Number\\s(\\d+)");
                Matcher matcher = pattern.matcher(confirmationText);
                if (matcher.find()) {
                    RequisitionNumber = matcher.group(1);
                    setRequisitionNumber(RequisitionNumber);
                    logMessages.add("Requisition number extracted: " + RequisitionNumber);
                    ExtentTestManager.createAssertTestStepWithScreenshot("Extracting Requisition Number",
                            Status.PASS, "Extracted Requisition Number: " + RequisitionNumber, true);
                } else {
                    logMessages.add("PATTERN MATCH FAILED: Could not extract requisition number");
                    throw new RuntimeException("Failed to extract Requisition Number from confirmation text");
                }

                // Excel operations with detailed file handling
                logMessages.add("Starting Excel data storage process...");
                String xl = "Requisition";
                String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" +
                        File.separator + "resources" + File.separator + "TestData" + File.separator + xl + ".xlsx";
                logMessages.add("Excel file path determined: " + filePath);

                File file = new File(filePath);
                logMessages.add("Ensuring directory structure exists");
                file.getParentFile().mkdirs();

                Workbook workbook;
                if (file.exists()) {
                    logMessages.add("Existing Excel file found, loading...");
                    try (FileInputStream fis = new FileInputStream(file)) {
                        workbook = new XSSFWorkbook(fis);
                        logMessages.add("Excel workbook loaded successfully");
                    }
                } else {
                    logMessages.add("No existing file, creating new workbook");
                    workbook = new XSSFWorkbook();
                    logMessages.add("New workbook created");
                }

                String sheetName = "Requisition_" + RequisitionNumber;
                if (workbook.getSheet(sheetName) != null) {
                    logMessages.add("Sheet already exists, skipping creation: " + sheetName);
                    workbook.close();
                    return;
                }

                logMessages.add("Creating new sheet: " + sheetName);
                Sheet sheet = workbook.createSheet(sheetName);

                // Header creation
                Row requisitionHeaderRow = sheet.createRow(0);
                String[] headers = {"Requisition Number", "User", "Date", "From Department", "To Department", "Indent Department"};
                for (int i = 0; i < headers.length; i++) {
                    requisitionHeaderRow.createCell(i).setCellValue(headers[i]);
                }
                logMessages.add("Header row created");

                // Data row creation
                Row requisitionDataRow = sheet.createRow(1);
                requisitionDataRow.createCell(0).setCellValue(String.valueOf(RequisitionNumber));
                requisitionDataRow.createCell(1).setCellValue(String.valueOf(UserName));
                requisitionDataRow.createCell(2).setCellValue(Dateformat.get_SystemDate());
                requisitionDataRow.createCell(3).setCellValue(fromDepartment);
                requisitionDataRow.createCell(4).setCellValue(toDepartment1);
                requisitionDataRow.createCell(5).setCellValue(toDepartment2);
                logMessages.add("Requisition data written");

                // Item header
                Row itemHeaderRow = sheet.createRow(3);
                String[] itemHeaders = {"Item", "Name", "Quantity"};
                for (int i = 0; i < itemHeaders.length; i++) {
                    itemHeaderRow.createCell(i).setCellValue(itemHeaders[i]);
                }
                logMessages.add("Item header written");

                // Item data
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                int rowIndex = 4;
                int itemCount = Math.min(Items.size(), Quantity.size());
                logMessages.add("Writing " + itemCount + " items to Excel");

                for (int i = 0; i < itemCount; i++) {
                    Row itemDataRow = sheet.createRow(rowIndex++);
                    itemDataRow.createCell(0).setCellValue(Items.get(i).getAttribute("value"));
                    itemDataRow.createCell(1).setCellValue(ItemName.get(i).getAttribute("value"));
                    itemDataRow.createCell(2).setCellValue(Quantity.get(i).getAttribute("value"));
                    if (i % 5 == 0) {
                        logMessages.add("Processed item " + (i+1) + " of " + itemCount);
                    }
                }

                logMessages.add("Saving Excel workbook...");
                try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                    workbook.write(fileOut);
                    logMessages.add("Excel file saved successfully: " + filePath);
                }
                workbook.close();

                // TestData.xlsx update
                logMessages.add("Updating TestData.xlsx with requisition information...");
                ExcelHandler.loadExcelFile(uploadPath, 3);
                int rowCount = ExcelHandler.getRowCount();
                logMessages.add("TestData.xlsx has " + rowCount + " rows");

                ExcelHandler.setCellData("Requisition Number", String.valueOf(RequisitionNumber), rowCount);
                ExcelHandler.setCellData("User", UserName, rowCount);
                ExcelHandler.saveExcelFile();
                logMessages.add("TestData.xlsx updated successfully");

                ExtentTestManager.createAssertTestStepWithScreenshot("Saving Requisition to Excel",
                        Status.PASS, "Successfully saved requisition data to Excel", true);
            });
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("IssueRQ Failure", Status.FAIL,
                    "Failed in step: " + stepDescription + ". Error: " + e.getMessage(), true, e);
            throw new RuntimeException("IssueRQ failed at step: " + stepDescription, e);
        } finally {
            Utilities.logerror("Frame Cleanup", logMessages -> {
                logMessages.add("Executing final cleanup...");
                try {
                    logMessages.add("Current frame state before cleanup: " +
                            BaseTest.getDriver().getWindowHandle());
                    BaseTest.getDriver().switchTo().defaultContent();
                    logMessages.add("Successfully switched to default content in finally block");
                } catch (Exception e) {
                    logMessages.add("Error during frame cleanup: " + e.getMessage());
                }
            });
        }
    }
}
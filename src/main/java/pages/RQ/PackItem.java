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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.CommonPages.PortalLoginPage;
import utils.Dateformat;
import utils.DynamicWait;
import utils.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PackItem {

    public static String RequisitionNumber;
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
    @FindBy(id = "cphBody_txtFromDeptStore")
    private WebElement FromDepartment;
    @FindBy(xpath = "//*[starts-with(@id, 'item_group_id_')]")
    private WebElement ItemGroup;
    @FindBy(xpath = "//*[starts-with(@id, 'item_group_id_')]")
    private List<WebElement> ItemGroups;
    @FindBy(xpath = "//input[starts-with(@id,'item_id_1_')]")
    private WebElement Items;
    @FindBy(xpath = "//*[starts-with(@id,'qty_req_7_')]")
    private WebElement reqesteQuantity;
    @FindBy(xpath = "//*[text()='Items Setup']")
    private WebElement ItemSetup;
    @FindBy(id = "col_0")
    private WebElement ItemSearch;
    @FindBy(id = "td_0_5")
    private WebElement itemGroup;

    @FindBy(xpath = "//*[starts-with(@id, 'item_group_id_')]")
    private List<WebElement> ItemGroup2;
    @FindBy(xpath = "//*[starts-with(@id, 'pack_id_2_')]")
    private List<WebElement> ItemPack;

    public PackItem(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static void setRequisitionNumber(String requisitionNumber) {
        RequisitionNumber = requisitionNumber;
    }

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

    public void packItem(String UserName, String requisitionType) {
        String stepDescription = "";
        try {
            stepDescription = "Issue Requisition";
            Utilities.logerror(stepDescription, logMessages -> {
                // Initialize Excel path
                String uploadPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "TestData.xlsx";
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

                // Validate Indent Department for Issue Requisition
//                if (requisitionType.equalsIgnoreCase("Issue Requisition")) {
//                    logMessages.add("Validating Indent Department for Issue Requisition");
//                    logMessages.add("Switching to MultiPageiframeDlg frame");
//                    BaseTest.getDriver().switchTo().defaultContent();
//                    BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//                    logMessages.add("Locating Indent Department field");
//                    WebElement inputField = BaseTest.getDriver().findElement(By.id("cphBody_txtIndentDept"));
//                    logMessages.add("Checking if Indent Department is disabled");
//                    boolean isDisabled = inputField.getAttribute("readonly") != null &&
//                            inputField.getAttribute("tabindex").equals("-1") &&
//                            inputField.getAttribute("class").contains("readonlytextbox");
//                    if (!isDisabled) {
//                        throw new RuntimeException("Indent Department is enabled unexpectedly");
//                    }
//                    logMessages.add("Indent Department is disabled as expected");
//                    ExtentTestManager.createAssertTestStepWithScreenshot("Validating Indent Department",
//                            Status.PASS, "Indent Department is disabled as expected", true);
//                }

                // Validate From Department

//                logMessages.add("Validating From Department clickability");
//                WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(2));
//                WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(FromDepartment));
//                logMessages.add("From Department is clickable");
//                ExtentTestManager.createAssertTestStepWithScreenshot("Validating From Department",
//                        Status.PASS, "From Department is clickable", true);
//
//                // Validate To Department
//                logMessages.add("Validating To Department clickability");
//                clickableElement = wait.until(ExpectedConditions.elementToBeClickable(ToDepartment));
//                logMessages.add("To Department is clickable");
//                ExtentTestManager.createAssertTestStepWithScreenshot("Validating To Department",
//                        Status.PASS, "To Department is clickable", true);

//                // Validate Property Display
//                logMessages.add("Validating Property visibility");
//                if (Property.isDisplayed()) {
//                    throw new RuntimeException("Property is displayed unexpectedly");
//                }
//                logMessages.add("Property is not displayed as expected");
//                ExtentTestManager.createAssertTestStepWithScreenshot("Validating Property Display",
//                        Status.PASS, "Property is not displayed as expected", true);

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

                for (int i = 0; i < 2; i++) {
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
                ExtentTestManager.createAssertTestStepWithScreenshot("Verifying Requisition Number", Status.PASS, "Requisition Number successfully generated", true);

                // Extract Requisition Number
                String input = ProductConfirmation.getText();
                Pattern pattern = Pattern.compile("Requisition Number\\s(\\d+)");
                Matcher matcher = pattern.matcher(input);
                if (matcher.find()) {
                    RequisitionNumber = matcher.group(1);
                    setRequisitionNumber(RequisitionNumber);
                    logMessages.add("Extracted Requisition Number: " + RequisitionNumber);
                    ExtentTestManager.createAssertTestStepWithScreenshot("Extracting Requisition Number", Status.PASS, "Extracted Requisition Number: " + RequisitionNumber, true);
                } else {
                    throw new RuntimeException("Failed to extract Requisition Number from confirmation text");
                }

                String xl = "Requisition";
                // Save to Excel
                logMessages.add("Saving Requisition to Excel");
                String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + xl + ".xlsx";
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
                ExtentTestManager.createAssertTestStepWithScreenshot("Saving Requisition to Excel", Status.PASS, "Successfully saved requisition data to Excel", true);
            });
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("IssueRQ Failure", Status.FAIL, "Failed in step: " + stepDescription + ". Error: " + e.getMessage(), true, e);
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

    public void Pack(String requisitionType) {
        try {
            Utilities.logerror("Pack Item", logMessages -> {


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

                Utilities.SendKeys(FromDepartment, "MS03");
                Utilities.Click(EnterItemDetail);

                List<String> itemIds = Arrays.asList("12200001", "12200002", "12200003", "12200004", "12200023", "12200024");

                for (int i = 0; i < 3; i++) {

                    if (i > 0) {
                        Utilities.Click(AddRow);
                    }
                    Utilities.SendKeys(Items, itemIds.get(i));

                    reqesteQuantity.click();
                    Actions actions = new Actions(BaseTest.getDriver());

                    actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("10").perform();

                }
                Utilities.Click(SaveButton);

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                if (ProductConfirmation.getText().contains("[238] - Details Created/Updated")) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Pack Item", Status.PASS, "Requistion Created with Pack Item", true);
                }

                Utilities.Click(OKBtn);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");


                List<String> itemPack = new ArrayList<>();
                for (WebElement element : ItemPack) {
                    String text = element.getAttribute("value");
                    itemPack.add(text);
                }
                System.out.println("Item Packs are :" + itemPack);

                // ✅ Compare each item with "KGS"
                for (int i = 0; i < itemPack.size(); i++) {
                    String pack = itemPack.get(i);
                    if ("KGS".equalsIgnoreCase(pack)) {
                        System.out.println("ItemPack[" + i + "] = KGS ✅");
                    } else {
                        System.err.println("❌ ItemPack[" + i + "] = " + pack + " (Expected: KGS)");
                    }
                }


            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void ItemGroup(String requisitionType) {
        try {
            Utilities.logerror("Pack Item", logMessages -> {


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

                Utilities.SendKeys(FromDepartment, "MS03");
                Utilities.Click(EnterItemDetail);

                List<String> itemIds = Arrays.asList("12200001", "12200002", "12200003", "12200004", "12200023", "12200024");

                for (int i = 0; i < 6; i++) {

                    if (i > 0) {
                        Utilities.Click(AddRow);
                    }
                    Utilities.SendKeys(Items, itemIds.get(i));

                    reqesteQuantity.click();
                    Actions actions = new Actions(BaseTest.getDriver());

                    actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("10").perform();

                }
                Utilities.Click(SaveButton);

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                if (ProductConfirmation.getText().contains("[238] - Details Created/Updated")) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Pack Item", Status.PASS, "Requistion Created with Pack Item", true);
                }

                Utilities.Click(OKBtn);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

                // get the item group from the requisition page
                List<String> itemGroupTexts = new ArrayList<>();  // class-level or method-level list
                for (WebElement element : ItemGroup2) {
                    String text = element.getAttribute("value").trim();
                    if (!text.isEmpty()) {
                        itemGroupTexts.add(text);
                    }
                    System.out.println("Item Group Texts: " + itemGroupTexts);

                }


                String itemgroup = ItemGroup.getAttribute("value");
                System.out.println("Item Group is :" + itemgroup);

                Utilities.Click(InventoryModule);
                Utilities.Click(ItemSetup);

                String currentHandles1 = BaseTest.getDriver().getWindowHandle();
                System.out.println(currentHandles1);

                // Switch to the new window after login
                Set<String> handle = BaseTest.getDriver().getWindowHandles();
                System.out.println(handle);
                for (String actual : handle) {
                    if (!actual.equalsIgnoreCase(currentHandles1) & !actual.equalsIgnoreCase(PortalLoginPage.currentHandles)) {
                        BaseTest.getDriver().switchTo().window(actual);
                    }
                }
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

                int loopSize = Math.min(6, Math.min(itemIds.size(), ItemGroup2.size()));

                System.out.println("Item group size is " + loopSize);
                for (int j = 0; j < 6; j++) {
                    Utilities.SendKeys(ItemSearch, itemIds.get(j));
                    Actions actions = new Actions(BaseTest.getDriver());
                    actions.sendKeys(Keys.TAB).perform();

                    String validateitemgroup = itemGroupTexts.get(j);

                    if (validateitemgroup.equalsIgnoreCase(itemgroup)) {
                        System.out.println(itemgroup + " and " + validateitemgroup + " are Same");
                    } else {
                        System.out.println(itemgroup + " and " + validateitemgroup + " are not same");
                    }
                }

            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void DifferentItemPack(String requisitionType) {
        try {
            Utilities.logerror("Pack Item", logMessages -> {


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

                Utilities.SendKeys(FromDepartment, "MS03");
                Utilities.Click(EnterItemDetail);

                List<String> itemIds = Arrays.asList("12200001", "12200002", "12200003", "12200004", "12200023", "12200024");

                for (int i = 0; i < 6; i++) {

                    if (i > 0) {
                        Utilities.Click(AddRow);
                    }
                    Utilities.SendKeys(Items, itemIds.get(i));

                    reqesteQuantity.click();
                    Actions actions = new Actions(BaseTest.getDriver());

                    actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("10").perform();

                }
                Utilities.Click(SaveButton);

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                if (ProductConfirmation.getText().contains("[238] - Details Created/Updated")) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Pack Item", Status.PASS, "Requistion Created with Pack Item", true);
                }

                Utilities.Click(OKBtn);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");


                List<String> itemPack = new ArrayList<>();
                for (WebElement element : ItemPack) {
                    String text = element.getAttribute("value");
                    itemPack.add(text);
                }
                System.out.println("Item Packs are :" + itemPack);

                // ✅ Check if all values are the same
                boolean allSame = itemPack.stream().distinct().count() == 1;

                if (allSame) {
                    System.err.println("❌ All item pack values are the same: " + itemPack.get(0));
                    // You can throw an assertion error if this is a test case
                    // throw new AssertionError("All item pack values are the same");
                } else {
                    System.out.println("✅ Not all item pack values are the same.");
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package pages.T0419_Pages;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import datahandlers.ExcelHandler;
import extentreports.ExtentTestManager;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Dateformat;
import utils.DynamicWait;
import utils.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Duration;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class IQ_Page {
    public static String RequisitionNumber;
    @FindBy(id = "LinkProduct3")
    private static WebElement SystemAdmin;
    @FindBy(id = "divmodule_1")
    private static WebElement other;
    @FindBy(xpath = "//*[@title='PS13 Admin Panel']")
    private static WebElement AdminPanel;
    @FindBy(id = "cphBody_btnBrowsing")
    private static WebElement Browsing;
    @FindBy(id = "ddlSelectmodule_Browsing")
    private static WebElement selectWish;
    @FindBy(id = "Name_0_0")
    private static WebElement clickChain;
    @FindBy(id = "Name_0_0")
    private static WebElement Log;
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
    /**
     * Methods
     */
    @FindBy(id = "cphBody_imgFromDeptStore")
    private WebElement FromDept;
    @FindBy(id = "cphBody_txtunit_id")
    private WebElement Property;


    /**
     * Constructor
     */
    public IQ_Page(WebDriver driver) {
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
        String xpath2 = "//img[@id=\'img_item_id_1_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath2));
    }

    public WebElement getrequestQuantityelement(int itemId) {
        String xpath3 = "//input[@id=\'qty_req_7_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    /**
     * /*
     *
     * @Method_Description : Add Requistion Type
     * @Method_Name : AddProduct
     * @Modified_By : Sachin kumar
     * @Modified_Date : 18/11/24
     **/
    public void AddProduct1(String UserName) {
        try {
            //Getting All resoureces from Testdata excel sheet
            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "TestData.xlsx";

            String ToDepartment1 = "", ToDepartment2 = "";


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

            //Requisitation Type Select
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Requisitions);
            Utilities.Click(BaseTest.getDriver(), Requisitions);

            //Adding Requisitation
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), AddButton);
            Utilities.Click(BaseTest.getDriver(), AddButton);
            DynamicWait.smallWait();

            //Selecting Requisitation Type
            Actions actions = new Actions(BaseTest.getDriver());
            ExcelHandler.loadExcelFile(Updload_Path, 2);
            List<String> Requisition_Type = ExcelHandler.getAllColumnData("Requisition Type");
            List<String> Description = ExcelHandler.getAllColumnData("Description");
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), RequisitationType);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
            //To Select Requisition Type
            String RequestionType = Description.get(3);
            Utilities.SendKeys(BaseTest.getDriver(), Filter, RequestionType);
            DynamicWait.smallWait();
            Utilities.Click(BaseTest.getDriver(), FirstColumn);


            //validation on the basic of requisition type

            //check whether indent department is disabled or not
//            if(Description.get(3).contains("Issue Requisition")){
//                BaseTest.getDriver().switchTo().defaultContent();
//                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//                if(IndentDept.isEnabled()){
//                    ExtentTestManager.createAssertTestStepWithScreenshot("Indent Dept", Status.PASS, "Indent Department is Disabled", true);
//                }else {
//                    ExtentTestManager.createAssertTestStepWithScreenshot("Indent Dept", Status.WARNING, "Indent Department is Enabled", true);
//
//                }
//            }
//
//
//            //check whether from dipartment is enabled or not
//            //if(Description.get(3).contains("Issue Requisition")){
//                BaseTest.getDriver().switchTo().defaultContent();
//                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//                if(FromDept.isEnabled()){
//                    ExtentTestManager.createAssertTestStepWithScreenshot("From Department", Status.PASS, "From Department is Enabled ", true);
//                }else {
//                    ExtentTestManager.createAssertTestStepWithScreenshot("From Department", Status.WARNING, "From Department is Disabled", true);
//
//                }
            //}


            //validation on the basic of requisition type

            //check whether indent department is disabled or not
            if (Description.get(3).contains("Issue Requisition")) {
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

                WebElement inputField = BaseTest.getDriver().findElement(By.id("cphBody_txtIndentDept"));

                // Verify the input field is disabled
                boolean isDisabled = inputField.getAttribute("readonly") != null && inputField.getAttribute("tabindex").equals("-1") && inputField.getAttribute("class").contains("readonlytextbox");

                if (isDisabled) {
                    System.out.println("The Indent Department is disabled.");
                    ExtentTestManager.createAssertTestStepWithScreenshot("Indent Dept", Status.PASS, "Indent Department is Disabled", true);
                } else {
                    System.out.println("The Indent Department is enabled.");
                    ExtentTestManager.createAssertTestStepWithScreenshot("Indent Dept", Status.WARNING, "Indent Department is Enabled", true);
                }
            }


            try {
                // Wait for the element to be clickable (visible and enabled)
                WebDriverWait wait1 = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(2));
                WebElement clickableElement = wait1.until(ExpectedConditions.elementToBeClickable(FromDepartment));
                ExtentTestManager.createAssertTestStepWithScreenshot("From Department", Status.PASS, "From Department is Enabled ", true);

                System.out.println("From Department is clickable");
            } catch (TimeoutException e) {
                System.out.println("From Department is NOT clickable");
                ExtentTestManager.createAssertTestStepWithScreenshot("From Department", Status.WARNING, "From Department is Disabled", true);
            }

            try {
                // Wait for the element to be clickable (visible and enabled)
                WebDriverWait wait1 = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(2));
                WebElement clickableElement = wait1.until(ExpectedConditions.elementToBeClickable(ToDepartment));
                ExtentTestManager.createAssertTestStepWithScreenshot("To Department", Status.PASS, "To Department is Enabled ", true);

                System.out.println("To Department is clickable");
            } catch (TimeoutException e) {
                System.out.println("To Department is NOT clickable");
                ExtentTestManager.createAssertTestStepWithScreenshot("To Department", Status.WARNING, "To Department is Disabled", true);
            }


            //Check whether property is displayed or not


            if (Property.isDisplayed() == false) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Property", Status.PASS, "Property not displayed", true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Property", Status.WARNING, " Department is Enabled", true);

            }


            //Select From Department/Store
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), FromDepartment);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            // Generate a random index within the bounds of the DepartmentList
            Random random = new Random();
            int randomIndex = random.nextInt(DepartmentList.size());
            // Click on the random department
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
            String FromDepartment = DepartmentList.get(randomIndex).getText();
            Utilities.SendKeys(BaseTest.getDriver(), Filter, DepartmentList.get(randomIndex).getText());
            DynamicWait.smallWait();
            Utilities.Click(BaseTest.getDriver(), FirstColumn);

            try {
                //Select To Department
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                Utilities.Click(BaseTest.getDriver(), ToDepartment);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                int randomIndex2 = random.nextInt(DepartmentList.size());
                // Click on the random department
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
                ToDepartment1 = DepartmentList.get(randomIndex2).getText();
                Utilities.SendKeys(BaseTest.getDriver(), Filter, DepartmentList.get(randomIndex2).getText());
                DynamicWait.smallWait();
                Utilities.Click(BaseTest.getDriver(), FirstColumn);
            } catch (Exception e) {

                //Select Indent Department
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                Utilities.Click(BaseTest.getDriver(), IndentDept);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                int randomIndex2 = random.nextInt(DepartmentList.size());
                // Click on the random department
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
                ToDepartment2 = DepartmentList.get(randomIndex2).getText();
                Utilities.SendKeys(BaseTest.getDriver(), Filter, DepartmentList.get(randomIndex2).getText());
                DynamicWait.smallWait();
                Utilities.Click(BaseTest.getDriver(), FirstColumn);

            }

            // ToDepartment1 = DepartmentList.get(randomIndex2).getText();

            //Item Add
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), EnterItemDetail);
            Utilities.Click(BaseTest.getDriver(), EnterItemDetail);


            Set<String> usedTexts = new HashSet<>();
            boolean isPage2Switched = false; // Track if switched to Page 2
            int itemsFromPage1 = 15; // Define the number of items to pick from Page 1

            for (int i = 0; i < 10; i++) {
                if (i > 0) { // Add a new row after the first iteration
                    Utilities.Click(BaseTest.getDriver(), AddRow);
                }

                // Perform actions to filter items
                Utilities.SendKeys(BaseTest.getDriver(), getItemSearchElement(i), "%%");
                getItemSearchElement(i).sendKeys(Keys.TAB);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ItemFilter);

                // Switch to Page 2 if 3 items are selected from Page 1, or after the 4th item
                if (i >= itemsFromPage1) {
                    try {
                        WebElement page2Button = BaseTest.getDriver().findElement(By.xpath("//select[@class='dropDown']"));
                        if (page2Button.isDisplayed() && page2Button.isEnabled()) {
                            Utilities.selectBy(BaseTest.getDriver(), "index", page2Button, "3"); // Switch to Page 2  //change number to increase the page
                            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ItemFilter); // Wait for Page 2 to load
                            isPage2Switched = true; // Mark that Page 2 is active
                        }
                    } catch (NoSuchElementException e) {
                        System.out.println("Page 2 button not found, continuing on current page.");
                    }
                }

                // After switching to Page 2, reset item selection index
                int itemIndex;
                if (isPage2Switched) {
                    itemIndex = i - itemsFromPage1; // Start selecting from Page 2 after 3 items from Page 1
                } else {
                    itemIndex = i; // Continue selecting from Page 1
                }

                // Get and ensure unique text
                String text = Item.get(itemIndex).getText(); // Get text from the correct page
                while (usedTexts.contains(text)) {
                    int randomInde = random.nextInt(Item.size() - 2); // Adjust as per available items
                    text = Item.get(randomInde).getText(); // Get a new text if duplicate
                }
                usedTexts.add(text); // Add the unique text to the set
                Utilities.SendKeys(BaseTest.getDriver(), ItemFilter, text); // Use the unique text
                DynamicWait.smallWait();
                Utilities.Click(BaseTest.getDriver(), FirstColumn);

                // Switch back to the main frame and enter quantity
                double j = (i + 1) * 10.25;
                String a = String.valueOf(j);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getrequestQuantityelement(i));
                getrequestQuantityelement(i).click();
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(a).perform();
            }


//            Set<String> usedTexts = new HashSet<>();
//
//            for (int i = 0; i < 2; i++) {
//                if (i > 0) { // Execute only after i = 0
//                    Utilities.Click(BaseTest.getDriver(), AddRow);
//                }
//                Utilities.SendKeys(BaseTest.getDriver(), getItemSearchElement(i), "%%");
//                getItemSearchElement(i).sendKeys(Keys.TAB);
//                BaseTest.getDriver().switchTo().defaultContent();
//                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ItemFilter);
//                String text = Item.get(i).getText();
//                // Check for unique text
//                while (usedTexts.contains(text)) {
//                    int randomIndex1 = random.nextInt(Item.size() - 2);
//                    text = Item.get(randomIndex1).getText(); // Get a new text if duplicate
//                }
//
//                double j=(i+1)*10.25;
//                String a=String.valueOf(j);
//                usedTexts.add(text); // Add unique text to the set
//                Utilities.SendKeys(BaseTest.getDriver(), ItemFilter, text); // Use the unique text
//                DynamicWait.smallWait();
//                Utilities.Click(BaseTest.getDriver(), FirstColumn);
//                BaseTest.getDriver().switchTo().defaultContent();
//                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getrequestQuantityelement(i));
//                getrequestQuantityelement(i).click();
//                actions.keyDown(Keys.CONTROL)
//                        .sendKeys("a")
//                        .keyUp(Keys.CONTROL)
//                        .sendKeys(a)
//                        .perform();
//            }

            Utilities.Click(BaseTest.getDriver(), SaveButton);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            if (ProductConfirmation.isDisplayed() && ProductConfirmation.getText().contains("Requisition Number")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Requisition Number Confirmation ", Status.PASS, "Requisition Number successfully generated", true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Requisition Number Confirmation", Status.FAIL, "Issue in Requisition Number", true);
            }

//            String input = ProductConfirmation.getText();
//
//            // Regular expression to match the last number in the string
//            Pattern pattern = Pattern.compile("\\b\\d+\\b(?=\\s*$)");
//            Matcher matcher = pattern.matcher(input);
//
//            if (matcher.find()) {
//                RequisitionNumber = matcher.group();
//            }
//            setRequisitionNumber(RequisitionNumber);
//            System.out.println("Requisition Number: " + RequisitionNumber);

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
                // Define requisition type and requisition number
                String requisitionType = "Requisition";

                // Construct the file path dynamically
                String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + requisitionType + ".xlsx"; // File name remains constant for adding new sheets

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
                String sheetName = "Requisition_" + RequisitionNumber;

                // Check if the sheet already exists
                if (workbook.getSheet(sheetName) != null) {
                    System.out.println("Sheet with name '" + sheetName + "' already exists. Skipping sheet creation.");
                    return;
                }

                // Create a new sheet
                Sheet sheet = workbook.createSheet(sheetName);

                // First row: Header row for requisition details
                Row requisitionHeaderRow = sheet.createRow(0);
                requisitionHeaderRow.createCell(0).setCellValue("Requisition Number");
                requisitionHeaderRow.createCell(1).setCellValue("User");
                requisitionHeaderRow.createCell(2).setCellValue("Date");
                requisitionHeaderRow.createCell(3).setCellValue("From Department");
                requisitionHeaderRow.createCell(4).setCellValue("To Department");
                requisitionHeaderRow.createCell(5).setCellValue("Indent Department");

                // Second row: Values for requisition details
                Row requisitionDataRow = sheet.createRow(1);
                requisitionDataRow.createCell(0).setCellValue(String.valueOf(RequisitionNumber));
                requisitionDataRow.createCell(1).setCellValue(String.valueOf(UserName));
                requisitionDataRow.createCell(2).setCellValue(Dateformat.get_SystemDate());
                requisitionDataRow.createCell(3).setCellValue(FromDepartment);
                requisitionDataRow.createCell(4).setCellValue(ToDepartment1);
                requisitionDataRow.createCell(5).setCellValue(ToDepartment2);

                // Fourth row: Header for item and quantity

//                CellStyle style = workbook.createCellStyle();
//                style.setFillForegroundColor(IndexedColors.YELLOW.getIndex()); // Set background color
//                style.setFillPattern(FillPatternType.SOLID_FOREGROUND); // Apply the fill pattern

                Row itemHeaderRow = sheet.createRow(3);
                // itemHeaderRow.setRowStyle(style);
                itemHeaderRow.createCell(0).setCellValue("Item");
                // itemHeaderRow.setRowStyle().
                itemHeaderRow.createCell(1).setCellValue("Name");
                itemHeaderRow.createCell(2).setCellValue("Quantity");

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

                // Populate item and quantity data starting from the fifth row
                int rowIndex = 4;  // Starting row index for items and quantities
                int itemCount = Math.min(Items.size(), Quantity.size());  // To handle if lists are of unequal length

                for (int i = 0; i < itemCount; i++) {
                    Row itemDataRow = sheet.createRow(rowIndex++);
                    itemDataRow.createCell(0).setCellValue(Items.get(i).getAttribute("value")); // Get item name from WebElement
                    itemDataRow.createCell(1).setCellValue(ItemName.get(i).getAttribute("value"));
                    itemDataRow.createCell(2).setCellValue(Quantity.get(i).getAttribute("value")); // Get quantity from WebElement

                }

                // Write the updated workbook back to the Excel file
                try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                    workbook.write(fileOut);
                }

                // Close the workbook
                workbook.close();

                //Add Requisition Number to the excel Sheet TESTDATA
                //   if (ProductConfirmation.isDisplayed()) {
                // Load the Excel file
                ExcelHandler.loadExcelFile(Updload_Path, 3);

                // Find the first empty row in the Excel sheet
                int rowCount = ExcelHandler.getRowCount();

                // Set the "Requisition Number" in the next available row
                ExcelHandler.setCellData("Requisition Number", String.valueOf(RequisitionNumber), rowCount);


                //  ExcelHandler.setCellData(resource, RequestionType, input);

                // Set the "User" in the same row
                ExcelHandler.setCellData("User", UserName, rowCount);

                // Save changes to the Excel file
                ExcelHandler.saveExcelFile();
                //  }


                System.out.println("Sheet '" + sheetName + "' added successfully in Excel file at: " + filePath);
            }


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);
        }
    }

    public void AddProduct2(String UserName) {
        try {
            ExtentTestManager.createAssertTestStepWithScreenshot("Add Product Process", Status.INFO, "Starting Add Product process for user: " + UserName, false);

            //Getting All resources from Testdata excel sheet
            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "TestData.xlsx";
            ExtentTestManager.createAssertTestStepWithScreenshot("File Path", Status.INFO, "Test data file path: " + Updload_Path, false);

            String ToDepartment1 = "", ToDepartment2 = "";

            // Switch to the main content and then to the required frame
            ExtentTestManager.createAssertTestStepWithScreenshot("Frame Switching", Status.INFO, "Switching to default content and then to MultiPageiframeBrw frame", false);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Wait for Inventory element and click it
            ExtentTestManager.createAssertTestStepWithScreenshot("Inventory", Status.INFO, "Waiting for and clicking Inventory element", false);
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Inventory);
            Utilities.Click(BaseTest.getDriver(), Inventory);

            //Inventory Module Select
            ExtentTestManager.createAssertTestStepWithScreenshot("Inventory Module", Status.INFO, "Switching frames and selecting Inventory Module", false);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
            Utilities.Click(BaseTest.getDriver(), InventoryModule);

            //Requisition Type Select
            ExtentTestManager.createAssertTestStepWithScreenshot("Requisitions", Status.INFO, "Selecting Requisitions", false);
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Requisitions);
            Utilities.Click(BaseTest.getDriver(), Requisitions);

            //Adding Requisition
            ExtentTestManager.createAssertTestStepWithScreenshot("Add Requisition", Status.INFO, "Clicking Add button to create new requisition", false);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), AddButton);
            Utilities.Click(BaseTest.getDriver(), AddButton);
            DynamicWait.smallWait();

            //Selecting Requisition Type
            ExtentTestManager.createAssertTestStepWithScreenshot("Requisition Type", Status.INFO, "Loading Excel data and selecting Requisition Type", false);
            Actions actions = new Actions(BaseTest.getDriver());
            ExcelHandler.loadExcelFile(Updload_Path, 2);
            List<String> Requisition_Type = ExcelHandler.getAllColumnData("Requisition Type");
            List<String> Description = ExcelHandler.getAllColumnData("Description");

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), RequisitationType);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);

            String RequestionType = Description.get(3);
            ExtentTestManager.createAssertTestStepWithScreenshot("Filter Requisition", Status.INFO, "Filtering for Requisition Type: " + RequestionType, false);
            Utilities.SendKeys(BaseTest.getDriver(), Filter, RequestionType);
            DynamicWait.smallWait();
            Utilities.Click(BaseTest.getDriver(), FirstColumn);

            // Validation for Indent Department
            if (Description.get(3).contains("Issue Requisition")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Indent Department Validation", Status.INFO, "Validating Indent Department status", false);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

                WebElement inputField = BaseTest.getDriver().findElement(By.id("cphBody_txtIndentDept"));
                boolean isDisabled = inputField.getAttribute("readonly") != null && inputField.getAttribute("tabindex").equals("-1") && inputField.getAttribute("class").contains("readonlytextbox");

                if (isDisabled) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Indent Dept", Status.PASS, "Indent Department is Disabled as expected", true);
                } else {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Indent Dept", Status.WARNING, "Indent Department is Enabled when it should be disabled", true);
                }
            }

            // Validate From Department is clickable
            try {
                ExtentTestManager.createAssertTestStepWithScreenshot("From Department Validation", Status.INFO, "Checking if From Department is clickable", false);
                WebDriverWait wait1 = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(2));
                WebElement clickableElement = wait1.until(ExpectedConditions.elementToBeClickable(FromDepartment));
                ExtentTestManager.createAssertTestStepWithScreenshot("From Department", Status.PASS, "From Department is Enabled as expected", true);
            } catch (TimeoutException e) {
                ExtentTestManager.createAssertTestStepWithScreenshot("From Department", Status.WARNING, "From Department is Disabled when it should be enabled", true);
            }

            // Validate To Department is clickable
            try {
                ExtentTestManager.createAssertTestStepWithScreenshot("To Department Validation", Status.INFO, "Checking if To Department is clickable", false);
                WebDriverWait wait1 = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(2));
                WebElement clickableElement = wait1.until(ExpectedConditions.elementToBeClickable(ToDepartment));
                ExtentTestManager.createAssertTestStepWithScreenshot("To Department", Status.PASS, "To Department is Enabled as expected", true);
            } catch (TimeoutException e) {
                ExtentTestManager.createAssertTestStepWithScreenshot("To Department", Status.WARNING, "To Department is Disabled when it should be enabled", true);
            }

            //Check whether property is displayed or not
            ExtentTestManager.createAssertTestStepWithScreenshot("Property Validation", Status.INFO, "Checking if Property field is displayed", false);
            if (Property.isDisplayed() == false) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Property", Status.PASS, "Property not displayed as expected", true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Property", Status.WARNING, "Property is displayed when it shouldn't be", true);
            }

            //Select From Department/Store
            ExtentTestManager.createAssertTestStepWithScreenshot("From Department Selection", Status.INFO, "Selecting From Department", false);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), FromDepartment);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Random random = new Random();
            int randomIndex = random.nextInt(DepartmentList.size());
            String FromDepartmentName = DepartmentList.get(randomIndex).getText();
            ExtentTestManager.createAssertTestStepWithScreenshot("Filter Department", Status.INFO, "Filtering for Department: " + FromDepartmentName, false);
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
            Utilities.SendKeys(BaseTest.getDriver(), Filter, FromDepartmentName);
            DynamicWait.smallWait();
            Utilities.Click(BaseTest.getDriver(), FirstColumn);

            try {
                //Select To Department
                ExtentTestManager.createAssertTestStepWithScreenshot("To Department Selection", Status.INFO, "Selecting To Department", false);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                Utilities.Click(BaseTest.getDriver(), ToDepartment);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");

                int randomIndex2 = random.nextInt(DepartmentList.size());
                ToDepartment1 = DepartmentList.get(randomIndex2).getText();
                ExtentTestManager.createAssertTestStepWithScreenshot("Filter Department", Status.INFO, "Filtering for Department: " + ToDepartment1, false);
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
                Utilities.SendKeys(BaseTest.getDriver(), Filter, ToDepartment1);
                DynamicWait.smallWait();
                Utilities.Click(BaseTest.getDriver(), FirstColumn);
            } catch (Exception e) {
                ExtentTestManager.createAssertTestStepWithScreenshot("To Department Fallback", Status.INFO, "Falling back to Indent Department selection", false);

                //Select Indent Department
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                Utilities.Click(BaseTest.getDriver(), IndentDept);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");

                int randomIndex2 = random.nextInt(DepartmentList.size());
                ToDepartment2 = DepartmentList.get(randomIndex2).getText();
                ExtentTestManager.createAssertTestStepWithScreenshot("Filter Department", Status.INFO, "Filtering for Department: " + ToDepartment2, false);
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
                Utilities.SendKeys(BaseTest.getDriver(), Filter, ToDepartment2);
                DynamicWait.smallWait();
                Utilities.Click(BaseTest.getDriver(), FirstColumn);
            }

            //Item Add
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Item Details", Status.INFO, "Clicking Enter Item Detail button", false);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), EnterItemDetail);
            Utilities.Click(BaseTest.getDriver(), EnterItemDetail);

            Set<String> usedTexts = new HashSet<>();
            boolean isPage2Switched = false;
            int itemsFromPage1 = 15;

            for (int i = 0; i < 10; i++) {
                if (i > 0) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Add Row", Status.INFO, "Adding new row for item #" + (i + 1), false);
                    Utilities.Click(BaseTest.getDriver(), AddRow);
                }

                ExtentTestManager.createAssertTestStepWithScreenshot("Item Search", Status.INFO, "Searching for item #" + (i + 1), false);
                Utilities.SendKeys(BaseTest.getDriver(), getItemSearchElement(i), "%%");
                getItemSearchElement(i).sendKeys(Keys.TAB);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ItemFilter);

                if (i >= itemsFromPage1) {
                    try {
                        ExtentTestManager.createAssertTestStepWithScreenshot("Page Navigation", Status.INFO, "Switching to page 2 for more items", false);
                        WebElement page2Button = BaseTest.getDriver().findElement(By.xpath("//select[@class='dropDown']"));
                        if (page2Button.isDisplayed() && page2Button.isEnabled()) {
                            Utilities.selectBy(BaseTest.getDriver(), "index", page2Button, "3");
                            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ItemFilter);
                            isPage2Switched = true;
                        }
                    } catch (NoSuchElementException e) {
                        ExtentTestManager.createAssertTestStepWithScreenshot("Page Navigation", Status.INFO, "Page 2 button not found, continuing on current page", false);
                    }
                }

                int itemIndex = isPage2Switched ? i - itemsFromPage1 : i;
                String text = Item.get(itemIndex).getText();

                while (usedTexts.contains(text)) {
                    int randomInde = random.nextInt(Item.size() - 2);
                    text = Item.get(randomInde).getText();
                }
                usedTexts.add(text);

                ExtentTestManager.createAssertTestStepWithScreenshot("Item Selection", Status.INFO, "Selecting item: " + text, false);
                Utilities.SendKeys(BaseTest.getDriver(), ItemFilter, text);
                DynamicWait.smallWait();
                Utilities.Click(BaseTest.getDriver(), FirstColumn);

                // Enter quantity
                double quantity = (i + 1) * 10.25;
                String quantityStr = String.valueOf(quantity);
                ExtentTestManager.createAssertTestStepWithScreenshot("Quantity Entry", Status.INFO, "Entering quantity: " + quantityStr + " for item #" + (i + 1), false);

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getrequestQuantityelement(i));
                getrequestQuantityelement(i).click();
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(quantityStr).perform();
            }

            // Save the requisition
            ExtentTestManager.createAssertTestStepWithScreenshot("Save Requisition", Status.INFO, "Clicking Save button to submit requisition", false);
            Utilities.Click(BaseTest.getDriver(), SaveButton);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            if (ProductConfirmation.isDisplayed() && ProductConfirmation.getText().contains("Requisition Number")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Requisition Confirmation", Status.PASS, "Requisition Number successfully generated", true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Requisition Confirmation", Status.FAIL, "Issue in Requisition Number generation", true);
            }

            // Extract requisition number
            String input = ProductConfirmation.getText();
            Pattern pattern = Pattern.compile("Requisition Number\\s(\\d+)");
            Matcher matcher = pattern.matcher(input);

            if (matcher.find()) {
                RequisitionNumber = matcher.group(1);
                setRequisitionNumber(RequisitionNumber);
                ExtentTestManager.createAssertTestStepWithScreenshot("Requisition Number", Status.INFO, "Extracted Requisition Number: " + RequisitionNumber, false);
            }

            // Save requisition details to Excel
            if (ProductConfirmation.isDisplayed()) {
                String requisitionType = "Requisition";
                String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + requisitionType + ".xlsx";

                ExtentTestManager.createAssertTestStepWithScreenshot("Excel Save", Status.INFO, "Saving requisition details to Excel file: " + filePath, false);

                Workbook workbook;
                File file = new File(filePath);
                file.getParentFile().mkdirs();

                if (file.exists()) {
                    try (FileInputStream fis = new FileInputStream(file)) {
                        workbook = new XSSFWorkbook(fis);
                    }
                } else {
                    workbook = new XSSFWorkbook();
                }

                String sheetName = "Requisition_" + RequisitionNumber;
                if (workbook.getSheet(sheetName) != null) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Excel Sheet", Status.WARNING, "Sheet with name '" + sheetName + "' already exists. Skipping sheet creation.", false);
                    return;
                }

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
                requisitionDataRow.createCell(3).setCellValue(FromDepartmentName);
                requisitionDataRow.createCell(4).setCellValue(ToDepartment1);
                requisitionDataRow.createCell(5).setCellValue(ToDepartment2);

                Row itemHeaderRow = sheet.createRow(3);
                itemHeaderRow.createCell(0).setCellValue("Item");
                itemHeaderRow.createCell(1).setCellValue("Name");
                itemHeaderRow.createCell(2).setCellValue("Quantity");

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

                int rowIndex = 4;
                int itemCount = Math.min(Items.size(), Quantity.size());

                for (int i = 0; i < itemCount; i++) {
                    Row itemDataRow = sheet.createRow(rowIndex++);
                    itemDataRow.createCell(0).setCellValue(Items.get(i).getAttribute("value"));
                    itemDataRow.createCell(1).setCellValue(ItemName.get(i).getAttribute("value"));
                    itemDataRow.createCell(2).setCellValue(Quantity.get(i).getAttribute("value"));
                }

                try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                    workbook.write(fileOut);
                    ExtentTestManager.createAssertTestStepWithScreenshot("Excel Save", Status.PASS, "Successfully saved requisition details to Excel sheet: " + sheetName, true);
                }

                workbook.close();

                // Save requisition number to TestData sheet
                ExtentTestManager.createAssertTestStepWithScreenshot("TestData Update", Status.INFO, "Updating TestData.xlsx with requisition number", false);
                ExcelHandler.loadExcelFile(Updload_Path, 3);
                int rowCount = ExcelHandler.getRowCount();
                ExcelHandler.setCellData("Requisition Number", String.valueOf(RequisitionNumber), rowCount);
                ExcelHandler.setCellData("User", UserName, rowCount);
                ExcelHandler.saveExcelFile();
                ExtentTestManager.createAssertTestStepWithScreenshot("TestData Update", Status.PASS, "Successfully updated TestData.xlsx with requisition information", false);
            }

            ExtentTestManager.createAssertTestStepWithScreenshot("Add Product Process", Status.PASS, "Successfully completed Add Product process for user: " + UserName, true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct: " + e.getMessage(), true, e);
        }
    }

    public void AddProduct11(String UserName) {
        try {
            ExtentTestManager.createAssertTestStepWithScreenshot("Add Product Process", Status.INFO, "Starting Add Product process for user: " + UserName, false);

            //Getting All resources from Testdata excel sheet
            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "TestData.xlsx";
            ExtentTestManager.createAssertTestStepWithScreenshot("File Path", Status.INFO, "Test data file path: " + Updload_Path, false);

            String ToDepartment1 = "", ToDepartment2 = "";

            // Switch to the main content and then to the required frame
            ExtentTestManager.createAssertTestStepWithScreenshot("Frame Switching", Status.INFO, "Switching to default content and then to MultiPageiframeBrw frame", false);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Wait for Inventory element and click it
            ExtentTestManager.createAssertTestStepWithScreenshot("Inventory", Status.INFO, "Waiting for and clicking Inventory element", false);
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Inventory);
            Utilities.Click(BaseTest.getDriver(), Inventory);

            //Inventory Module Select
            ExtentTestManager.createAssertTestStepWithScreenshot("Inventory Module", Status.INFO, "Switching frames and selecting Inventory Module", false);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
            Utilities.Click(BaseTest.getDriver(), InventoryModule);

            //Requisition Type Select
            ExtentTestManager.createAssertTestStepWithScreenshot("Requisitions", Status.INFO, "Selecting Requisitions", false);
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Requisitions);
            Utilities.Click(BaseTest.getDriver(), Requisitions);

            //Adding Requisition
            ExtentTestManager.createAssertTestStepWithScreenshot("Add Requisition", Status.INFO, "Clicking Add button to create new requisition", false);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), AddButton);
            Utilities.Click(BaseTest.getDriver(), AddButton);
            DynamicWait.smallWait();

            //Selecting Requisition Type
            ExtentTestManager.createAssertTestStepWithScreenshot("Requisition Type", Status.INFO, "Loading Excel data and selecting Requisition Type", false);
            Actions actions = new Actions(BaseTest.getDriver());
            ExcelHandler.loadExcelFile(Updload_Path, 2);
            List<String> Requisition_Type = ExcelHandler.getAllColumnData("Requisition Type");
            List<String> Description = ExcelHandler.getAllColumnData("Description");

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), RequisitationType);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);

            String RequestionType = Description.get(3);
            ExtentTestManager.createAssertTestStepWithScreenshot("Filter Requisition", Status.INFO, "Filtering for Requisition Type: " + RequestionType, false);
            Utilities.SendKeys(BaseTest.getDriver(), Filter, RequestionType);
            DynamicWait.smallWait();
            Utilities.Click(BaseTest.getDriver(), FirstColumn);

            // Validation for Indent Department
            if (Description.get(3).contains("Issue Requisition")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Indent Department Validation", Status.INFO, "Validating Indent Department status", false);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

                WebElement inputField = BaseTest.getDriver().findElement(By.id("cphBody_txtIndentDept"));
                boolean isDisabled = inputField.getAttribute("readonly") != null && inputField.getAttribute("tabindex").equals("-1") && inputField.getAttribute("class").contains("readonlytextbox");

                if (isDisabled) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Indent Dept", Status.PASS, "Indent Department is Disabled as expected", true);
                } else {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Indent Dept", Status.WARNING, "Indent Department is Enabled when it should be disabled", true);
                }
            }

            // Validate From Department is clickable
            try {
                ExtentTestManager.createAssertTestStepWithScreenshot("From Department Validation", Status.INFO, "Checking if From Department is clickable", false);
                WebDriverWait wait1 = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(2));
                WebElement clickableElement = wait1.until(ExpectedConditions.elementToBeClickable(FromDepartment));
                ExtentTestManager.createAssertTestStepWithScreenshot("From Department", Status.PASS, "From Department is Enabled as expected", true);
            } catch (TimeoutException e) {
                ExtentTestManager.createAssertTestStepWithScreenshot("From Department", Status.WARNING, "From Department is Disabled when it should be enabled", true);
            }

            // Validate To Department is clickable
            try {
                ExtentTestManager.createAssertTestStepWithScreenshot("To Department Validation", Status.INFO, "Checking if To Department is clickable", false);
                WebDriverWait wait1 = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(2));
                WebElement clickableElement = wait1.until(ExpectedConditions.elementToBeClickable(ToDepartment));
                ExtentTestManager.createAssertTestStepWithScreenshot("To Department", Status.PASS, "To Department is Enabled as expected", true);
            } catch (TimeoutException e) {
                ExtentTestManager.createAssertTestStepWithScreenshot("To Department", Status.WARNING, "To Department is Disabled when it should be enabled", true);
            }

            //Check whether property is displayed or not
            ExtentTestManager.createAssertTestStepWithScreenshot("Property Validation", Status.INFO, "Checking if Property field is displayed", false);
            if (Property.isDisplayed() == false) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Property", Status.PASS, "Property not displayed as expected", true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Property", Status.WARNING, "Property is displayed when it shouldn't be", true);
            }

            //Select From Department/Store
            ExtentTestManager.createAssertTestStepWithScreenshot("From Department Selection", Status.INFO, "Selecting From Department", false);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), FromDepartment);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Random random = new Random();
            int randomIndex = random.nextInt(DepartmentList.size());
            String FromDepartmentName = DepartmentList.get(randomIndex).getText();
            ExtentTestManager.createAssertTestStepWithScreenshot("Filter Department", Status.INFO, "Filtering for Department: " + FromDepartmentName, false);
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
            Utilities.SendKeys(BaseTest.getDriver(), Filter, FromDepartmentName);
            DynamicWait.smallWait();
            Utilities.Click(BaseTest.getDriver(), FirstColumn);

            try {
                //Select To Department
                ExtentTestManager.createAssertTestStepWithScreenshot("To Department Selection", Status.INFO, "Selecting To Department", false);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                Utilities.Click(BaseTest.getDriver(), ToDepartment);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");

                int randomIndex2 = random.nextInt(DepartmentList.size());
                ToDepartment1 = DepartmentList.get(randomIndex2).getText();
                ExtentTestManager.createAssertTestStepWithScreenshot("Filter Department", Status.INFO, "Filtering for Department: " + ToDepartment1, false);
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
                Utilities.SendKeys(BaseTest.getDriver(), Filter, ToDepartment1);
                DynamicWait.smallWait();
                Utilities.Click(BaseTest.getDriver(), FirstColumn);
            } catch (Exception e) {
                ExtentTestManager.createAssertTestStepWithScreenshot("To Department Fallback", Status.INFO, "Falling back to Indent Department selection", false);

                //Select Indent Department
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                Utilities.Click(BaseTest.getDriver(), IndentDept);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");

                int randomIndex2 = random.nextInt(DepartmentList.size());
                ToDepartment2 = DepartmentList.get(randomIndex2).getText();
                ExtentTestManager.createAssertTestStepWithScreenshot("Filter Department", Status.INFO, "Filtering for Department: " + ToDepartment2, false);
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
                Utilities.SendKeys(BaseTest.getDriver(), Filter, ToDepartment2);
                DynamicWait.smallWait();
                Utilities.Click(BaseTest.getDriver(), FirstColumn);
            }

            //Item Add
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Item Details", Status.INFO, "Clicking Enter Item Detail button", false);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), EnterItemDetail);
            Utilities.Click(BaseTest.getDriver(), EnterItemDetail);

            Set<String> usedTexts = new HashSet<>();
            boolean isPage2Switched = false;
            int itemsFromPage1 = 15;

            for (int i = 0; i < 10; i++) {
                if (i > 0) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Add Row", Status.INFO, "Adding new row for item #" + (i + 1), false);
                    Utilities.Click(BaseTest.getDriver(), AddRow);
                }

                ExtentTestManager.createAssertTestStepWithScreenshot("Item Search", Status.INFO, "Searching for item #" + (i + 1), false);
                Utilities.SendKeys(BaseTest.getDriver(), getItemSearchElement(i), "%%");
                getItemSearchElement(i).sendKeys(Keys.TAB);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ItemFilter);

                if (i >= itemsFromPage1) {
                    try {
                        ExtentTestManager.createAssertTestStepWithScreenshot("Page Navigation", Status.INFO, "Switching to page 2 for more items", false);
                        WebElement page2Button = BaseTest.getDriver().findElement(By.xpath("//select[@class='dropDown']"));
                        if (page2Button.isDisplayed() && page2Button.isEnabled()) {
                            Utilities.selectBy(BaseTest.getDriver(), "index", page2Button, "3");
                            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ItemFilter);
                            isPage2Switched = true;
                        }
                    } catch (NoSuchElementException e) {
                        ExtentTestManager.createAssertTestStepWithScreenshot("Page Navigation", Status.INFO, "Page 2 button not found, continuing on current page", false);
                    }
                }

                int itemIndex = isPage2Switched ? i - itemsFromPage1 : i;
                String text = Item.get(itemIndex).getText();

                while (usedTexts.contains(text)) {
                    int randomInde = random.nextInt(Item.size() - 2);
                    text = Item.get(randomInde).getText();
                }
                usedTexts.add(text);

                ExtentTestManager.createAssertTestStepWithScreenshot("Item Selection", Status.INFO, "Selecting item: " + text, false);
                Utilities.SendKeys(BaseTest.getDriver(), ItemFilter, text);
                DynamicWait.smallWait();
                Utilities.Click(BaseTest.getDriver(), FirstColumn);

                // Enter quantity
                double quantity = (i + 1) * 10.25;
                String quantityStr = String.valueOf(quantity);
                ExtentTestManager.createAssertTestStepWithScreenshot("Quantity Entry", Status.INFO, "Entering quantity: " + quantityStr + " for item #" + (i + 1), false);

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getrequestQuantityelement(i));
                getrequestQuantityelement(i).click();
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(quantityStr).perform();
            }

            // Save the requisition
            ExtentTestManager.createAssertTestStepWithScreenshot("Save Requisition", Status.INFO, "Clicking Save button to submit requisition", false);
            Utilities.Click(BaseTest.getDriver(), SaveButton);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            if (ProductConfirmation.isDisplayed() && ProductConfirmation.getText().contains("Requisition Number")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Requisition Confirmation", Status.PASS, "Requisition Number successfully generated", true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Requisition Confirmation", Status.FAIL, "Issue in Requisition Number generation", true);
            }

            // Extract requisition number
            String input = ProductConfirmation.getText();
            Pattern pattern = Pattern.compile("Requisition Number\\s(\\d+)");
            Matcher matcher = pattern.matcher(input);

            if (matcher.find()) {
                RequisitionNumber = matcher.group(1);
                setRequisitionNumber(RequisitionNumber);
                ExtentTestManager.createAssertTestStepWithScreenshot("Requisition Number", Status.INFO, "Extracted Requisition Number: " + RequisitionNumber, false);
            }

            // Save requisition details to Excel
            if (ProductConfirmation.isDisplayed()) {
                String requisitionType = "Requisition";
                String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + requisitionType + ".xlsx";

                ExtentTestManager.createAssertTestStepWithScreenshot("Excel Save", Status.INFO, "Saving requisition details to Excel file: " + filePath, false);

                Workbook workbook;
                File file = new File(filePath);
                file.getParentFile().mkdirs();

                if (file.exists()) {
                    try (FileInputStream fis = new FileInputStream(file)) {
                        workbook = new XSSFWorkbook(fis);
                    }
                } else {
                    workbook = new XSSFWorkbook();
                }

                String sheetName = "Requisition_" + RequisitionNumber;
                if (workbook.getSheet(sheetName) != null) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Excel Sheet", Status.WARNING, "Sheet with name '" + sheetName + "' already exists. Skipping sheet creation.", false);
                    return;
                }

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
                requisitionDataRow.createCell(3).setCellValue(FromDepartmentName);
                requisitionDataRow.createCell(4).setCellValue(ToDepartment1);
                requisitionDataRow.createCell(5).setCellValue(ToDepartment2);

                Row itemHeaderRow = sheet.createRow(3);
                itemHeaderRow.createCell(0).setCellValue("Item");
                itemHeaderRow.createCell(1).setCellValue("Name");
                itemHeaderRow.createCell(2).setCellValue("Quantity");

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

                int rowIndex = 4;
                int itemCount = Math.min(Items.size(), Quantity.size());

                for (int i = 0; i < itemCount; i++) {
                    Row itemDataRow = sheet.createRow(rowIndex++);
                    itemDataRow.createCell(0).setCellValue(Items.get(i).getAttribute("value"));
                    itemDataRow.createCell(1).setCellValue(ItemName.get(i).getAttribute("value"));
                    itemDataRow.createCell(2).setCellValue(Quantity.get(i).getAttribute("value"));
                }

                try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                    workbook.write(fileOut);
                    ExtentTestManager.createAssertTestStepWithScreenshot("Excel Save", Status.PASS, "Successfully saved requisition details to Excel sheet: " + sheetName, true);
                }

                workbook.close();

                // Save requisition number to TestData sheet
                ExtentTestManager.createAssertTestStepWithScreenshot("TestData Update", Status.INFO, "Updating TestData.xlsx with requisition number", false);
                ExcelHandler.loadExcelFile(Updload_Path, 3);
                int rowCount = ExcelHandler.getRowCount();
                ExcelHandler.setCellData("Requisition Number", String.valueOf(RequisitionNumber), rowCount);
                ExcelHandler.setCellData("User", UserName, rowCount);
                ExcelHandler.saveExcelFile();
                ExtentTestManager.createAssertTestStepWithScreenshot("TestData Update", Status.PASS, "Successfully updated TestData.xlsx with requisition information", false);
            }

            ExtentTestManager.createAssertTestStepWithScreenshot("Add Product Process", Status.PASS, "Successfully completed Add Product process for user: " + UserName, true);

        }
//            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL,
//                    "Exception found in Method - AddProduct: " + e.getMessage(), true, e);
//        }
        catch (Exception e) {
            try {
                // Error handling with proper logging
                ExtentTestManager.createAssertTestStepWithScreenshot("Error Handling", Status.INFO, "Checking for application errors after exception", false);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                //BaseTest.getDriver().switchTo().frame("iframeGridDialog");

                if (ProductConfirmation.isDisplayed() && ProductConfirmation.getText().contains("Application program error")) {
                    ApplicationProgError();
                    ExtentTestManager.createAssertTestStepWithScreenshot("Application Error", Status.FAIL, "Application error occurred during execution", true);
                    return;
                }
            } catch (Exception innerEx) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Error Handling", Status.FAIL, "Error while handling application error", true, innerEx);
            }

            //throw e;
        }
    }

    public void ApplicationProgError() {
        try {
            String homepage = "https://qc2web.prologicfirst.in/WISH/Common/frmMultiPage.aspx?pgName=WishProduct&form_id=frmWishProduct";
            //Utilities.Click(OKBtn);
            BaseTest.getDriver().navigate().to(homepage);
            try {
                BaseTest.getDriver().switchTo().alert().accept();
            } catch (Exception e) {
                System.out.println("Inside catch block : No alert is present");
            }

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            wait.until(ExpectedConditions.elementToBeClickable(SystemAdmin));
            Utilities.Click(BaseTest.getDriver(), SystemAdmin);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(other);
            Utilities.Click(AdminPanel);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(Browsing);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            BaseTest.getDriver().switchTo().frame("iframeimgDlgBrowse");


            try {
                Utilities.selectBy("value", selectWish, "WISHLOG");
                Utilities.DoubleClick(clickChain);
            } catch (Exception e) {
                Select select = new Select(selectWish);
                select.selectByValue("WISHLOG");
            }

            Utilities.DoubleClick(Log);


            String downloadPath = System.getProperty("user.dir") + File.separator + "DownloadPath";
            Path latestFile = getLatestFile(downloadPath, ".xml");


            if (latestFile != null) {
                String xmlContent = new String(Files.readAllBytes(latestFile));
                ExtentTestManager.getTest().log(Status.INFO, "Latest Downloaded XML File Content: <br><pre>" + xmlContent + "</pre>");
            } else {
                ExtentTestManager.getTest().log(Status.WARNING, "No XML file found in the download directory: " + downloadPath);
            }
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Application", Status.FAIL, "Error while handling application prog error: " + e.getMessage(), true, e);
        }
    }


    public Path getLatestFile(String directoryPath, String extension) throws IOException {
        try (Stream<Path> files = Files.list(Paths.get(directoryPath))) {
            return files.filter(p -> p.toString().endsWith(extension)).max(Comparator.comparing(p -> {
                try {
                    return Files.getLastModifiedTime(p);
                } catch (IOException e) {
                    return FileTime.fromMillis(0);
                }
            })).orElse(null);
        }
    }

    public void AddProduct(String UserName) {
        try {
            ExtentTestManager.createAssertTestStepWithScreenshot("Add Product Process", Status.INFO, "Starting Add Product process for user: " + UserName, false);

            //Getting All resources from Testdata excel sheet
            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "TestData.xlsx";
            ExtentTestManager.createAssertTestStepWithScreenshot("File Path", Status.INFO, "Test data file path: " + Updload_Path, false);

            String ToDepartment1 = "", ToDepartment2 = "";

            // Switch to the main content and then to the required frame
            ExtentTestManager.createAssertTestStepWithScreenshot("Frame Switching", Status.INFO, "Switching to default content and then to MultiPageiframeBrw frame", false);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Wait for Inventory element and click it
            ExtentTestManager.createAssertTestStepWithScreenshot("Inventory", Status.INFO, "Waiting for and clicking Inventory element", false);
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Inventory);
            Utilities.Click(BaseTest.getDriver(), Inventory);

            //Inventory Module Select
            ExtentTestManager.createAssertTestStepWithScreenshot("Inventory Module", Status.INFO, "Switching frames and selecting Inventory Module", false);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
            Utilities.Click(BaseTest.getDriver(), InventoryModule);

            //Requisition Type Select
            ExtentTestManager.createAssertTestStepWithScreenshot("Requisitions", Status.INFO, "Selecting Requisitions", false);
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Requisitions);
            Utilities.Click(BaseTest.getDriver(), Requisitions);

            //Adding Requisition
            ExtentTestManager.createAssertTestStepWithScreenshot("Add Requisition", Status.INFO, "Clicking Add button to create new requisition", false);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), AddButton);
            Utilities.Click(BaseTest.getDriver(), AddButton);
            DynamicWait.smallWait();

            //Selecting Requisition Type
            ExtentTestManager.createAssertTestStepWithScreenshot("Requisition Type", Status.INFO, "Loading Excel data and selecting Requisition Type", false);
            Actions actions = new Actions(BaseTest.getDriver());
            ExcelHandler.loadExcelFile(Updload_Path, 2);
            List<String> Requisition_Type = ExcelHandler.getAllColumnData("Requisition Type");
            List<String> Description = ExcelHandler.getAllColumnData("Description");

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), RequisitationType);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);

            String RequestionType = Description.get(3);
            ExtentTestManager.createAssertTestStepWithScreenshot("Filter Requisition", Status.INFO, "Filtering for Requisition Type: " + RequestionType, false);
            Utilities.SendKeys(BaseTest.getDriver(), Filter, RequestionType);
            DynamicWait.smallWait();
            Utilities.Click(BaseTest.getDriver(), FirstColumn);

            // Validation for Indent Department
            if (Description.get(3).contains("Issue Requisition")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Indent Department Validation", Status.INFO, "Validating Indent Department status", false);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

                WebElement inputField = BaseTest.getDriver().findElement(By.id("cphBody_txtIndentDept"));
                boolean isDisabled = inputField.getAttribute("readonly") != null && inputField.getAttribute("tabindex").equals("-1") && inputField.getAttribute("class").contains("readonlytextbox");

                if (isDisabled) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Indent Dept", Status.PASS, "Indent Department is Disabled as expected", true);
                } else {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Indent Dept", Status.WARNING, "Indent Department is Enabled when it should be disabled", true);
                }
            }

            // Validate From Department is clickable
            try {
                ExtentTestManager.createAssertTestStepWithScreenshot("From Department Validation", Status.INFO, "Checking if From Department is clickable", false);
                WebDriverWait wait1 = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(2));
                WebElement clickableElement = wait1.until(ExpectedConditions.elementToBeClickable(FromDepartment));
                ExtentTestManager.createAssertTestStepWithScreenshot("From Department", Status.PASS, "From Department is Enabled as expected", true);
            } catch (TimeoutException e) {
                ExtentTestManager.createAssertTestStepWithScreenshot("From Department", Status.WARNING, "From Department is Disabled when it should be enabled", true);
            }

            // Validate To Department is clickable
            try {
                ExtentTestManager.createAssertTestStepWithScreenshot("To Department Validation", Status.INFO, "Checking if To Department is clickable", false);
                WebDriverWait wait1 = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(2));
                WebElement clickableElement = wait1.until(ExpectedConditions.elementToBeClickable(ToDepartment));
                ExtentTestManager.createAssertTestStepWithScreenshot("To Department", Status.PASS, "To Department is Enabled as expected", true);
            } catch (TimeoutException e) {
                ExtentTestManager.createAssertTestStepWithScreenshot("To Department", Status.WARNING, "To Department is Disabled when it should be enabled", true);
            }

            //Check whether property is displayed or not
            ExtentTestManager.createAssertTestStepWithScreenshot("Property Validation", Status.INFO, "Checking if Property field is displayed", false);
            if (Property.isDisplayed() == false) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Property", Status.PASS, "Property not displayed as expected", true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Property", Status.WARNING, "Property is displayed when it shouldn't be", true);
            }

            //Select From Department/Store
            ExtentTestManager.createAssertTestStepWithScreenshot("From Department Selection", Status.INFO, "Selecting From Department", false);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), FromDepartment);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Random random = new Random();
            int randomIndex = random.nextInt(DepartmentList.size());
            String FromDepartmentName = DepartmentList.get(randomIndex).getText();
            ExtentTestManager.createAssertTestStepWithScreenshot("Filter Department", Status.INFO, "Filtering for Department: " + FromDepartmentName, false);
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
            Utilities.SendKeys(BaseTest.getDriver(), Filter, FromDepartmentName);
            DynamicWait.smallWait();
            Utilities.Click(BaseTest.getDriver(), FirstColumn);

            try {
                //Select To Department
                ExtentTestManager.createAssertTestStepWithScreenshot("To Department Selection", Status.INFO, "Selecting To Department", false);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                Utilities.Click(BaseTest.getDriver(), ToDepartment);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");

                int randomIndex2 = random.nextInt(DepartmentList.size());
                ToDepartment1 = DepartmentList.get(randomIndex2).getText();
                ExtentTestManager.createAssertTestStepWithScreenshot("Filter Department", Status.INFO, "Filtering for Department: " + ToDepartment1, false);
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
                Utilities.SendKeys(BaseTest.getDriver(), Filter, ToDepartment1);
                DynamicWait.smallWait();
                Utilities.Click(BaseTest.getDriver(), FirstColumn);
            } catch (Exception e) {
                ExtentTestManager.createAssertTestStepWithScreenshot("To Department Fallback", Status.INFO, "Falling back to Indent Department selection", false);

                //Select Indent Department
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                Utilities.Click(BaseTest.getDriver(), IndentDept);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");

                int randomIndex2 = random.nextInt(DepartmentList.size());
                ToDepartment2 = DepartmentList.get(randomIndex2).getText();
                ExtentTestManager.createAssertTestStepWithScreenshot("Filter Department", Status.INFO, "Filtering for Department: " + ToDepartment2, false);
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
                Utilities.SendKeys(BaseTest.getDriver(), Filter, ToDepartment2);
                DynamicWait.smallWait();
                Utilities.Click(BaseTest.getDriver(), FirstColumn);
            }

            // 🔥 ULTRA-FAST ITEM ADDING - NO PAGE SCANNING (CURRENT PAGE ONLY)
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Item Details", Status.INFO, "Clicking Enter Item Detail button", false);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), EnterItemDetail);
            Utilities.Click(BaseTest.getDriver(), EnterItemDetail);

            Set<String> usedTexts = new HashSet<>();
            int totalItemsToAdd = 100;  // ⚡ Configurable - change as needed
            int itemsAdded = 0;

            while (itemsAdded < totalItemsToAdd) {
                // Add new row (skip for first item)
                if (itemsAdded > 0) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Add Row", Status.INFO, "Adding row #" + (itemsAdded + 1), false);
                    Utilities.Click(BaseTest.getDriver(), AddRow);
                   // DynamicWait.tinyWait(); // ⚡ Ultra-fast
                }

                // ⚡ FAST item search trigger
                ExtentTestManager.createAssertTestStepWithScreenshot("Item Search", Status.INFO, "Searching item #" + (itemsAdded + 1), false);
                Utilities.SendKeys(BaseTest.getDriver(), getItemSearchElement(itemsAdded), "%%");
                getItemSearchElement(itemsAdded).sendKeys(Keys.TAB);

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ItemFilter);

                // 🔥 ULTRA-FAST: Random unique item from CURRENT PAGE ONLY
                String selectedItemText = getRandomUniqueItemFromCurrentPage(usedTexts, random);
                if (selectedItemText == null) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Item Limit", Status.WARNING,
                            "No more unique items on current page. Added " + itemsAdded + "/" + totalItemsToAdd, true);
                    break;
                }

                // ⚡ Select item (super fast)
                ExtentTestManager.createAssertTestStepWithScreenshot("Item Selection", Status.INFO, "Selecting: " + selectedItemText, false);
                Utilities.SendKeys(BaseTest.getDriver(), ItemFilter, selectedItemText);
                //DynamicWait.tinyWait();
                Utilities.Click(BaseTest.getDriver(), FirstColumn);

                // ⚡ Enter quantity
                double quantity = (itemsAdded + 1) * 10.25;
                String quantityStr = String.valueOf(quantity);
                ExtentTestManager.createAssertTestStepWithScreenshot("Quantity Entry", Status.INFO,
                        "Quantity: " + quantityStr + " for item #" + (itemsAdded + 1), false);

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getrequestQuantityelement(itemsAdded));
                getrequestQuantityelement(itemsAdded).click();
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(quantityStr).perform();

                usedTexts.add(selectedItemText);
                itemsAdded++;
                //DynamicWait.tinyWait(); // ⚡ Keep it fast
            }

            ExtentTestManager.createAssertTestStepWithScreenshot("Fast Items Summary", Status.PASS,
                    "⚡ SUCCESS: Added " + itemsAdded + "/" + totalItemsToAdd + " items from CURRENT PAGE", true);

            // Save the requisition
            ExtentTestManager.createAssertTestStepWithScreenshot("Save Requisition", Status.INFO, "Clicking Save button", false);
            Utilities.Click(BaseTest.getDriver(), SaveButton);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            if (ProductConfirmation.isDisplayed() && ProductConfirmation.getText().contains("Requisition Number")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Requisition Confirmation", Status.PASS, "Requisition Number generated successfully", true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Requisition Confirmation", Status.FAIL, "Issue in Requisition Number generation", true);
            }

            // Extract requisition number
            String input = ProductConfirmation.getText();
            Pattern pattern = Pattern.compile("Requisition Number\\s(\\d+)");
            Matcher matcher = pattern.matcher(input);

            if (matcher.find()) {
                RequisitionNumber = matcher.group(1);
                setRequisitionNumber(RequisitionNumber);
                ExtentTestManager.createAssertTestStepWithScreenshot("Requisition Number", Status.INFO, "Extracted: " + RequisitionNumber, false);
            }

            // Save requisition details to Excel (unchanged)
            if (ProductConfirmation.isDisplayed()) {
                String requisitionType = "Requisition";
                String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + requisitionType + ".xlsx";

                ExtentTestManager.createAssertTestStepWithScreenshot("Excel Save", Status.INFO, "Saving to Excel: " + filePath, false);

                Workbook workbook;
                File file = new File(filePath);
                file.getParentFile().mkdirs();

                if (file.exists()) {
                    try (FileInputStream fis = new FileInputStream(file)) {
                        workbook = new XSSFWorkbook(fis);
                    }
                } else {
                    workbook = new XSSFWorkbook();
                }

                String sheetName = "Requisition_" + RequisitionNumber;
                if (workbook.getSheet(sheetName) != null) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Excel Sheet", Status.WARNING, "Sheet '" + sheetName + "' already exists", false);
                    return;
                }

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
                requisitionDataRow.createCell(3).setCellValue(FromDepartmentName);
                requisitionDataRow.createCell(4).setCellValue(ToDepartment1);
                requisitionDataRow.createCell(5).setCellValue(ToDepartment2);

                Row itemHeaderRow = sheet.createRow(3);
                itemHeaderRow.createCell(0).setCellValue("Item");
                itemHeaderRow.createCell(1).setCellValue("Name");
                itemHeaderRow.createCell(2).setCellValue("Quantity");

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

                int rowIndex = 4;
                int itemCount = Math.min(Items.size(), Quantity.size());

                for (int i = 0; i < itemCount; i++) {
                    Row itemDataRow = sheet.createRow(rowIndex++);
                    itemDataRow.createCell(0).setCellValue(Items.get(i).getAttribute("value"));
                    itemDataRow.createCell(1).setCellValue(ItemName.get(i).getAttribute("value"));
                    itemDataRow.createCell(2).setCellValue(Quantity.get(i).getAttribute("value"));
                }

                try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                    workbook.write(fileOut);
                    ExtentTestManager.createAssertTestStepWithScreenshot("Excel Save", Status.PASS, "Saved to sheet: " + sheetName, true);
                }
                workbook.close();

                // Save requisition number to TestData sheet
                ExtentTestManager.createAssertTestStepWithScreenshot("TestData Update", Status.INFO, "Updating TestData.xlsx", false);
                ExcelHandler.loadExcelFile(Updload_Path, 3);
                int rowCount = ExcelHandler.getRowCount();
                ExcelHandler.setCellData("Requisition Number", String.valueOf(RequisitionNumber), rowCount);
                ExcelHandler.setCellData("User", UserName, rowCount);
                ExcelHandler.saveExcelFile();
                ExtentTestManager.createAssertTestStepWithScreenshot("TestData Update", Status.PASS, "TestData.xlsx updated successfully", false);
            }

            ExtentTestManager.createAssertTestStepWithScreenshot("Add Product Process", Status.PASS,
                    "✅ ULTRA-FAST COMPLETED: " + itemsAdded + " items for " + UserName, true);

        } catch (Exception e) {
            try {
                ExtentTestManager.createAssertTestStepWithScreenshot("Error Handling", Status.INFO, "Checking application errors", false);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");

                if (ProductConfirmation.isDisplayed() && ProductConfirmation.getText().contains("Application program error")) {
                    ApplicationProgError();
                    ExtentTestManager.createAssertTestStepWithScreenshot("Application Error", Status.FAIL, "Application error occurred", true);
                    return;
                }
            } catch (Exception innerEx) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Error Handling", Status.FAIL, "Error in error handling", true, innerEx);
            }
        }
    }

    /**
     * ⚡ ULTRA-FAST HELPER: Gets random unique item from CURRENT PAGE ONLY
     */
    private String getRandomUniqueItemFromCurrentPage(Set<String> usedTexts, Random random) {
        try {
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Item);
//            DynamicWait.tinyWait();

            List<WebElement> currentPageItems = Item;
            List<String> availableItems = new ArrayList<>();

            // Collect unique items from current page only ⚡
            for (WebElement item : currentPageItems) {
                String itemText = item.getText().trim();
                if (!itemText.isEmpty() && !usedTexts.contains(itemText)) {
                    availableItems.add(itemText);
                }
            }

            // Return random available item ⚡
            return availableItems.isEmpty() ? null : availableItems.get(random.nextInt(availableItems.size()));
        } catch (Exception e) {
            return null;
        }
    }


}


/*


catch (Exception e) {
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
} */



package pages.T0419_Pages;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import datahandlers.XMLHandler;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import datahandlers.ExcelHandler;
import extentreports.ExtentTestManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Dateformat;
import utils.DynamicWait;
import utils.Utilities;
import utils.Utilities.*;


import java.io.FileOutputStream;
import java.time.Duration;
import java.util.HashSet;
import java.util.Random;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MQReq_Page {
    /**
     * Constructor
     */
    public MQReq_Page(WebDriver driver) {
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
    @FindBy(id="cphBody_imgIndentDept")
    private WebElement IndentDept;
    @FindBy(id="cphBody_txtunit_id")
    private WebElement SelectProperty;
    @FindBy(id="cphBody_txtTemplate")
    private WebElement Template;
    
    
    
    @FindBy(xpath = "//input[starts-with(@id,'item_id_1_')]")
    private List<WebElement> Items;
    @FindBy(xpath = "//input[starts-with(@id,'org_req_qty_6_')]")
    private List<WebElement> Quantity;
    @FindBy(xpath = "//input[starts-with(@id,'item_desc_3_')]")
    private List<WebElement> ItemName;
    
    

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

    public static String RequisitionNumber;

    public static String Date;

    public static void setRequisitionNumber(String requisitionNumber) {

        RequisitionNumber = requisitionNumber;
    }
    /**
     * Methods
     */
    /**
      /*
     *
     * @Method_Description : Add Requistion Type
     * @Method_Name : AddProduct
     * @Modified_By : Sachin kumar
     * @Modified_Date : 18/11/24
     **/
    public void AddProduct(String UserName) {
        try {
            //Getting All resoureces from Testdata excel sheet
            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "TestData.xlsx";
            

            String ToDepartment1="";
            String ToDepartment2="";
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
            String RequestionType = Description.get(5);
            Utilities.SendKeys(BaseTest.getDriver(), Filter, RequestionType);
            DynamicWait.smallWait();
            Utilities.Click(BaseTest.getDriver(), FirstColumn);
            
            


            //Select From Department/Store
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Date =BaseTest.getDriver().findElement(By.id("cphBody_txtDate")).getAttribute("value");
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
            }catch(Exception e) {
            
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
            
            //Add Template
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(BaseTest.getDriver(), Template, "Egg");
            
          
            
            //Item Add
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), EnterItemDetail);
            Utilities.Click(BaseTest.getDriver(), EnterItemDetail);
            
            //Dismiss the alert
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(2));
            wait.until(ExpectedConditions.alertIsPresent()).dismiss();
            

            Set<String> usedTexts = new HashSet<>();
            boolean isPage2Switched = false; // Track if switched to Page 2
            int itemsFromPage1 = 15; // Define the number of items to pick from Page 1

            for (int i = 0; i < 2; i++) {
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
                if (i >= itemsFromPage1 ) {
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
                    int randomInde= random.nextInt(Item.size() - 2); // Adjust as per available items
                    text = Item.get(randomInde).getText(); // Get a new text if duplicate
                }
                usedTexts.add(text); // Add the unique text to the set
                Utilities.SendKeys(BaseTest.getDriver(), ItemFilter, text); // Use the unique text
                DynamicWait.smallWait();
                Utilities.Click(BaseTest.getDriver(), FirstColumn);

                // Switch back to the main frame and enter quantity
                double j=(i+1)*10.25;
                String a=String.valueOf(j);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getrequestQuantityelement(i));
                getrequestQuantityelement(i).click();
                actions.keyDown(Keys.CONTROL)
                        .sendKeys("a")
                        .keyUp(Keys.CONTROL)
                        .sendKeys(a)
                        .perform();
            }





//            Set<String> usedTexts = new HashSet<>();
//
//            for (int i = 0; i < 6; i++) {
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
//                        .sendKeys("10")
//                        .perform();
//            }

            Utilities.Click(BaseTest.getDriver(), SaveButton);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            if (ProductConfirmation.isDisplayed() && ProductConfirmation.getText().contains("Requisition Number")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Requisition Number Confirmation ", Status.PASS,
                        "Requisition Number successfully generated", true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Requisition Number Confirmation", Status.FAIL,
                        "Issue in Requisition Number", true);
            }

            String input = ProductConfirmation.getText();

            // Regular expression to match the last number in the string
            Pattern pattern = Pattern.compile("\\b\\d+\\b(?=\\s*$)");
            Matcher matcher = pattern.matcher(input);

            if (matcher.find()) {
                RequisitionNumber = matcher.group();
            }
            setRequisitionNumber(RequisitionNumber);
            System.out.println("Requisition Number: " + RequisitionNumber);
            
            
            if (ProductConfirmation.isDisplayed()) {
                // Define requisition type and requisition number
                String requisitionType = "Requisition";

                // Construct the file path dynamically
                String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
                        + File.separator + "resources" + File.separator + "TestData" + File.separator
                        + requisitionType + ".xlsx"; // File name remains constant for adding new sheets

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
                requisitionDataRow.createCell(2).setCellValue(String.valueOf(Date));
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

}
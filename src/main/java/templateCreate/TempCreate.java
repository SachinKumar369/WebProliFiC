package templateCreate;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import datahandlers.ExcelHandler;
import extentreports.ExtentTestManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DynamicWait;
import utils.Utilities;
import utils.Utilities.*;


import java.time.Duration;
import java.util.HashSet;
import java.util.Random;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TempCreate {
    /**
     * Constructor
     */
    public TempCreate(WebDriver driver) {
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
    @FindBy(xpath = "//a[@title=\"OFB02 Requisition Templates\"]")
    private WebElement ReqTemplate;
    @FindBy(xpath = "//input[@id=\"cphDialogHeader_btnAddRecords\"]")
    private WebElement AddBtn;
    @FindBy(xpath = "//select[@id='cphBody_ddlTemplateType']")
    private WebElement TemplateType;
    
    @FindBy(id="cphDialogHeader_btnAddRecords")
    private WebElement Add;
    @FindBy(id="cphBody_ddlTemplateType")
    private WebElement TempType;
    @FindBy(id="cphBody_txtTemplateId")
    private WebElement TempName;
    @FindBy(id="cphBody_txtStore")
    private WebElement Store;
    @FindBy(id="cphBody_txtDepartment")
    private WebElement Departmet;
    @FindBy(id="cphBody_txtRemarks")
    private WebElement Remark;
    @FindBy(id="cphBody_btnAddRow")
    private WebElement AddRow;
    @FindBy(id="cphBody_imgStore")
    private WebElement StoreImg;
    @FindBy(xpath = "//input[@id=\"item_id_2_1\"]")
    private WebElement ItemSearch;
    @FindBy(xpath = "//input[@id=\"col_0\"]")
    private WebElement ItemFilter1;
   // @FindBy(xpath)
    
    
    
    
    
    

    
    
    
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
    private WebElement AddRow1;
    @FindBy(id = "cphDialogHeader_btnSave")
    private WebElement SaveButton;
    @FindBy(id = "lblErrorDesc")
    private WebElement ProductConfirmation;
    @FindBy(xpath = "//input[@id='btnOK']")
    private WebElement OkButton;
    @FindBy(xpath = "//img[@id=\"cphBody_imgIndentDept\"]")
    private WebElement IndendDepartment;
    @FindBy(xpath = "//img[@id=\"img_item_id_2_0\"]")
    private WebElement SearchItem;
    

    public WebElement getItemSearchElement(int itemId) {
        String xpath = "//input[@id='item_id_2_" + itemId + "']";  
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

    public static void setRequisitionNumber(String requisitionNumber) {

        RequisitionNumber = requisitionNumber;
    }
    /**
     * Methods
     */
    /**
     * /**
     *
     * @Method_Description : Add Requistion Type
     * @Method_Name : AddProduct
     * @Modified_By : Sachin kumar
     * @Modified_Date : 01/11/24
     **/
    public void AddProduct(String UserName) {
        try {
            //Getting All resources from Testdata excel sheet
            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "TestData.xlsx";
            ExcelHandler.loadExcelFile(Updload_Path, 1);
            List<String> Resources = ExcelHandler.getAllColumnData("Resources");
            String resource = Resources.get(2);

            //T0419 Resource select
            BaseTest.getDriver().manage().window().maximize();
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), SwitchTo);
            // Select the item by visible text
            Utilities.selectBy(BaseTest.getDriver(), "visibletext", SwitchTo, resource);
            try {
                // Wait briefly for the alert and accept it if it appears
                WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(5));
                wait.until(ExpectedConditions.alertIsPresent()).accept(); // Accept the alert if present
                WebDriverWait wait1 = new WebDriverWait(BaseTest.getDriver(),Duration.ofSeconds(0));
                wait.until(ExpectedConditions.alertIsPresent()).dismiss();
            } catch (TimeoutException e) {
                // If no alert is present within the wait time, continue without doing anything
            }

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

            
            //Select Inventory Template
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ReqTemplate);
            Utilities.Click(BaseTest.getDriver(), ReqTemplate);
            
            
          //Click Add Button
          BaseTest.getDriver().switchTo().defaultContent();
          BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
          Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Add);
          Utilities.Click(BaseTest.getDriver(), Add);
          //DynamicWait.smallWait();
          
          //Add Template Name
          BaseTest.getDriver().switchTo().defaultContent();
          BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
          Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), TempName);
          Utilities.SendKeys(BaseTest.getDriver(), TempName, "Eggs Template");
          
          
          
          
          //Select Store
          BaseTest.getDriver().switchTo().defaultContent();
          BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
          Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), StoreImg);
          Utilities.Click(BaseTest.getDriver(), StoreImg);
          BaseTest.getDriver().switchTo().defaultContent();
          BaseTest.getDriver().switchTo().frame("iframeGridDialog");
          
          
          // Generate a random index within the bounds of the DepartmentList
          Random random = new Random();
          int randomIndex = random.nextInt(DepartmentList.size());
          // Click on the random department
          Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
          Utilities.SendKeys(BaseTest.getDriver(), Filter, DepartmentList.get(randomIndex).getText());
          DynamicWait.smallWait();
          Utilities.Click(BaseTest.getDriver(), FirstColumn);

          
          
          
          //Utilities.SendKeys(BaseTest.getDriver(), Store, "52");
          
          //Select Dept
          BaseTest.getDriver().switchTo().defaultContent();
          BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
          Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Departmet);
          Utilities.SendKeys(BaseTest.getDriver(), Departmet, "1142");
          
        //Add Remark
          BaseTest.getDriver().switchTo().defaultContent();
          BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
          Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Remark);
          Utilities.SendKeys(BaseTest.getDriver(), Remark, "Testing purpose");
          
          
          
          
          
          
          //Select Template Type
          DynamicWait.longWait();
          BaseTest.getDriver().switchTo().defaultContent();
          BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
          Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), TempType);
          Utilities.selectBy(BaseTest.getDriver(), "visibletext", TempType, "Requisition Template");
          
         
          
//          //Select Store
//          // Generate a random index within the bounds of the DepartmentList
//          Random random2 = new Random();
//          int randomIndex1 = random2.nextInt(DepartmentList.size());
//          // Click on the random department
//          Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
//          Utilities.SendKeys(BaseTest.getDriver(), Filter, DepartmentList.get(randomIndex).getText());
//          DynamicWait.smallWait();
//          Utilities.Click(BaseTest.getDriver(), FirstColumn);

          
          //Click Add Row
          BaseTest.getDriver().switchTo().defaultContent();
          BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
          Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), AddRow);
          Utilities.Click(BaseTest.getDriver(), AddRow);
          
          
          
          //Item Add
          BaseTest.getDriver().switchTo().defaultContent();
          BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//          Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), EnterItemDetail);
//          Utilities.Click(BaseTest.getDriver(), EnterItemDetail);

          Set<String> usedTexts2 = new HashSet<>();

          for (int i = 1; i < 6; i++) {
              if (i > 0) { // Execute only after i = 0
                  Utilities.Click(BaseTest.getDriver(), AddRow);
              }
              Utilities.SendKeys(BaseTest.getDriver(), getItemSearchElement(i), "%%");
              getItemSearchElement(i).sendKeys(Keys.TAB);
              BaseTest.getDriver().switchTo().defaultContent();
              BaseTest.getDriver().switchTo().frame("iframeGridDialog");
              Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ItemFilter);
              String text1 = Item.get(i).getText();
              // Check for unique text
              while (usedTexts2.contains(text1)) {
                  int randomIndex3 = random.nextInt(Item.size() - 2);
                  text1 = Item.get(randomIndex3).getText(); // Get a new text if duplicate
              }
            Utilities.SendKeys(BaseTest.getDriver(), AddRow, "%%");
            Utilities.SendKeys(BaseTest.getDriver(), Add, text1);
            
            
            
            
            
            
            
            
            
//            //Requisition Type Select
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Requisitions);
//            Utilities.Click(BaseTest.getDriver(), Requisitions);
//
//            //Adding Requisition
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), AddButton);
//            Utilities.Click(BaseTest.getDriver(), AddButton);
//            DynamicWait.smallWait();
//
//            //Selecting Requisition Type
//            Actions actions = new Actions(BaseTest.getDriver());
//            ExcelHandler.loadExcelFile(Updload_Path, 2);
//            List<String> Requisition_Type = ExcelHandler.getAllColumnData("Requisition Type");
//            List<String> Description = ExcelHandler.getAllColumnData("Description");
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            Utilities.Click(BaseTest.getDriver(), RequisitationType);
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
//            //To Select Requisition Type
//            String RequestionType = Description.get(0);
//            Utilities.SendKeys(BaseTest.getDriver(), Filter, RequestionType);
//            DynamicWait.smallWait();
//            Utilities.Click(BaseTest.getDriver(), FirstColumn);
//
//            //Select From Department/Store
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            Utilities.Click(BaseTest.getDriver(), FromDepartment);
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            // Generate a random index within the bounds of the DepartmentList
//            Random random = new Random();
//            int randomIndex = random.nextInt(DepartmentList.size());
//            // Click on the random department
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
//            Utilities.SendKeys(BaseTest.getDriver(), Filter, DepartmentList.get(randomIndex).getText());
//            DynamicWait.smallWait();
//            Utilities.Click(BaseTest.getDriver(), FirstColumn);
//
//            
//            try {
//            //Select To Department
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            Utilities.Click(BaseTest.getDriver(), ToDepartment);
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            int randomIndex2 = random.nextInt(DepartmentList.size());
//            // Click on the random department
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
//            Utilities.SendKeys(BaseTest.getDriver(), Filter, DepartmentList.get(randomIndex2).getText());
//            DynamicWait.smallWait();
//            Utilities.Click(BaseTest.getDriver(), FirstColumn);
//            
//            } catch(Exception e) {
//            	//Select Indent Department
//                BaseTest.getDriver().switchTo().defaultContent();
//                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//                Utilities.Click(BaseTest.getDriver(), IndendDepartment);
//                BaseTest.getDriver().switchTo().defaultContent();
//                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//                int randomIndex2 = random.nextInt(DepartmentList.size());
//                // Click on the random department
//                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
//                Utilities.SendKeys(BaseTest.getDriver(), Filter, DepartmentList.get(randomIndex2).getText());
//                DynamicWait.smallWait();
//                Utilities.Click(BaseTest.getDriver(), FirstColumn);
//            }
//            
//            
//            
//            
//            
//            
//            
//            

//            //Item Add
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), EnterItemDetail);
//            Utilities.Click(BaseTest.getDriver(), EnterItemDetail);
//
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
//                    int randomIndex3 = random.nextInt(Item.size() - 2);
//                    text = Item.get(randomIndex3).getText(); // Get a new text if duplicate
//                }
               
                
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
            }



            

            Utilities.Click(BaseTest.getDriver(), OkButton);


            }  } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);
        }
    }

}

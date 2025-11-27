package editreq;

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
import java.awt.Desktop.Action;
import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PQEdit_Page {
    /**
     * Constructor
     */
    public PQEdit_Page(WebDriver driver) {
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
    @FindBy(xpath = "//img[@id=\"cphBody_imgIndentDept\"]")
    private WebElement IndendDepartment;
    @FindBy(xpath = "//input[@id=\"cphBody_txtNumber\"]")
    private WebElement EnterReqNo;
    @FindBy(xpath = "//input[@id=\"cphBody_btnSearch\"]")
    private WebElement SearchBtn;
    @FindBy(xpath = "//input[@id=\"cphBrowserHeader_btnViewEdit\"]")
    private WebElement ViewEdit;
    @FindBy(id="qty_indent_9_3")
    private WebElement EditQty;
    @FindBy(id="delete_21_4")
    private WebElement DeleteRow;
    
    @FindBy(xpath = "//input[@id=\"item_id_1_7\"]")
    private WebElement itemid;
    
    @FindBy(xpath = "//input[@id=\"qty_indent_9_6\"]")    
    private WebElement editqty;
    
    
    @FindBy(xpath ="//input[starts-with(@id,'item_id_1_')]")
    public WebElement getItemSearchElement;
    
    @FindBy(xpath="//input[starts-with(@id,'qty_indent_9_')]")    //id="qty_indent_9_8"  
    public WebElement AddQty;

    public WebElement getItemDropdownElement(int itemId) {
        String xpath2 = "//img[@id=\'img_item_id_1_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath2));
    }

    public WebElement getrequestQuantityelement(int itemId) {
        String xpath3 = "//input[@id=\'qty_req_7_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    //public We

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
     * @Method_Description : Add Requisition Type
     * @Method_Name : AddProduct
     * @Modified_By : Sachin kumar
     * @Modified_Date : 01/11/24
     **/
    public void EditProduct() {
        try {
            //Getting All resoureces from Testdata excel sheet
            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "TestData.xlsx";
            ExcelHandler.loadExcelFile(Updload_Path, 1);
            List<String> Resources = ExcelHandler.getAllColumnData("Resources");
            String resource = Resources.get(2);

            //T0410 Resource select
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

            //Requisition Type Select
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Requisitions);
            Utilities.Click(BaseTest.getDriver(), Requisitions);
            BaseTest.getDriver().switchTo().defaultContent();

            //Get Requisition Number from the Excel Sheet
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");  
            //wait(1000);
            ExcelHandler.loadExcelFile(Updload_Path, 3);
            
            List<String> Resources1 = ExcelHandler.getAllColumnData("Requisition Number");
           
            //for(int i=0;i<Resources1.size();i++) {
            String resource1 = Resources1.get(Resources1.size() - 1);
            
            				
            
            
            String resources1 = ExcelHandler.getCellData("1", "Req No");
            //String resource1 = Resources1.get(2);
            Utilities.SendKeys(BaseTest.getDriver(), EnterReqNo, resource1);
          //  Utilities.SendKeys(BaseTest.getDriver(),EnterReqNo,"73986");
           // }
            Utilities.Click(BaseTest.getDriver(), SearchBtn);
            Utilities.Click(BaseTest.getDriver(), ViewEdit);
            DynamicWait.smallWait();
            BaseTest.getDriver().switchTo().defaultContent();
            
           
            
          //Change Quantity
            Actions actions = new Actions(BaseTest.getDriver());
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), AddQty);
            AddQty.click();
            actions.keyDown(Keys.CONTROL)
                    .sendKeys("a")
                    .keyUp(Keys.CONTROL)
                    .sendKeys("5")
                    .perform();

            //Add row
            Utilities.Click(BaseTest.getDriver(), AddRow);
            Utilities.SendKeys(BaseTest.getDriver(), getItemSearchElement, "%%");
            getItemSearchElement.sendKeys(Keys.TAB);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ItemFilter);
            if (!Item.isEmpty()) {
                // Get the last WebElement from the Item list
                WebElement lastElement = Item.get(Item.size()-1);

                // Send the text of the last WebElement to ItemFilter
                Utilities.SendKeys(BaseTest.getDriver(), ItemFilter, lastElement.getText());  //KSTS0355 //GSKC0024
            }

            DynamicWait.smallWait();
            Utilities.Click(BaseTest.getDriver(), FirstColumn);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            //Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getrequestQuantityelement(1));
            //getrequestQuantityelement(6).click();
            //editqty.click();
            AddQty.click();
            actions.keyDown(Keys.CONTROL)
                    .sendKeys("a")
                    .keyUp(Keys.CONTROL)
                    .sendKeys("5")
                    .perform();

            //Delete Row
            //Utilities.Click(BaseTest.getDriver(), DeleteRow);

            Utilities.Click(BaseTest.getDriver(), SaveButton);
            if (ProductConfirmation.isDisplayed() && ProductConfirmation.getText().contains("Requisition Number")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Requisition Number Confirmation ", Status.PASS,
                        "Requisition Number successfully generated", true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Requisition Number Confirmation", Status.FAIL,
                        "Issue in Requisition Number", true);
            }


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("EditProduct", Status.FAIL, "Exception found in Method - EditProduct", true, e);
        }



    }

}

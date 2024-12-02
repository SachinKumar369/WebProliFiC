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

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static pages.T0419_Pages.Requisition_Page.RequisitionNumber;

public class EditCPQ_Page {
    /**
     * Constructor
     */
    public EditCPQ_Page(WebDriver driver) {
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
    @FindBy(id = "cphBody_txtNumber")
    private WebElement NumberInputField;
    @FindBy(id = "cphBody_btnSearch")
    private WebElement SearchButton;
    @FindBy(id = "cphBrowserHeader_btnViewEdit")
    private WebElement ViewButton;
    @FindBy(id = "delete_21_0")
    private WebElement DeleteRow;
    @FindBy(xpath = "//input[starts-with(@id, 'item_id_1')]")
    private List<WebElement> Addeditem;
    
    @FindBy(xpath = "//input[starts-with(@id, 'item_id_1')]")
    private WebElement Addeditem1;
    @FindBy(xpath = "//input[starts-with(@id,'qty_indent_9_']")
    private WebElement QTY;

    public WebElement getItemSearchElement(int itemId) {
        String xpath = "//input[@id='item_id_1_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath));
    }

    public WebElement getItemDropdownElement(int itemId) {
        String xpath2 = "//img[@id=\'img_item_id_1_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath2));
    }

    public WebElement getrequestQuantityelement(int itemId) {
        String xpath3 = "//input[@id='qty_indent_9_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    public static String getRequisitionNumber() {
        return RequisitionNumber;  // Get the global value
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
     * @Modified_Date : 08/11/24
     **/
    public void EditProduct() {
        try {

        	String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "TestData.xlsx";
            ExcelHandler.loadExcelFile(Updload_Path, 1);
            List<String> Resources = ExcelHandler.getAllColumnData("Resources");
            String resource = Resources.get(2);

        	
        	
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

            
            //Enter Requisition Number
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            
            
            ExcelHandler.loadExcelFile(Updload_Path, 3);
            String Resources1 = ExcelHandler.getCellData("1", "Req No");
            //String resource1 = Resources1.get(0);
            
            
            NumberInputField.sendKeys(Resources1);
            Utilities.SendKeys(BaseTest.getDriver(), NumberInputField, Resources1);
            Utilities.Click(BaseTest.getDriver(), SearchButton);


            //ViewEdit Item
            Utilities.Click(BaseTest.getDriver(), ViewButton);

            //Change Quantity
            Actions actions = new Actions(BaseTest.getDriver());
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getrequestQuantityelement(5));
            getrequestQuantityelement(5).click();
            actions.keyDown(Keys.CONTROL)
                    .sendKeys("a")
                    .keyUp(Keys.CONTROL)
                    .sendKeys("5")
                    .perform();

            //Add row
            Utilities.Click(BaseTest.getDriver(), AddRow);
            Utilities.SendKeys(BaseTest.getDriver(), Addeditem1, "%%");
            Addeditem1.sendKeys(Keys.TAB);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ItemFilter);
            if (!Item.isEmpty()) {
                // Get the last WebElement from the Item list
                WebElement lastElement = Item.get(Item.size() - 1);

                // Send the text of the last WebElement to ItemFilter
                Utilities.SendKeys(BaseTest.getDriver(), ItemFilter, lastElement.getText());
            }

            DynamicWait.smallWait();
            Utilities.Click(BaseTest.getDriver(), FirstColumn);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), QTY);
            QTY.click();
            actions.keyDown(Keys.CONTROL)
                    .sendKeys("a")
                    .keyUp(Keys.CONTROL)
                    .sendKeys("10")
                    .perform();

            //Delete Row
            Utilities.Click(BaseTest.getDriver(), DeleteRow);
            
            
            
            Utilities.Click(BaseTest.getDriver(), SaveButton);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("EditProduct", Status.FAIL, "Exception found in Method - EditProduct", true, e);
        }
    }

}

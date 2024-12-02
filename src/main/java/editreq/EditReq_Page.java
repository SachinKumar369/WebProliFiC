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

public class EditReq_Page {
    /**
     * Constructor
     */
    public EditReq_Page(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Web Elements
     */

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
    @FindBy(id = "cphBody_txtNumber")   //input[@id="cphBody_txtNumber"]
    private WebElement NumberInputField;
    
    
    @FindBy(id="qty_indent_9_5")
    private WebElement ChangeQty;
    @FindBy(id = "cphBody_btnSearch")
    private WebElement SearchButton;
    @FindBy(id = "cphBrowserHeader_btnViewEdit")
    private WebElement ViewButton;
    @FindBy(id = "delete_21_3")
    private WebElement DeleteRow;
    @FindBy(xpath = "//input[starts-with(@id, 'item_id_1')]")
    private List<WebElement> Addeditem;

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

            //Inventory Module Select
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
            Utilities.ActionClick(BaseTest.getDriver(), InventoryModule);

            //Requisition Type Select
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Requisitions);
            Utilities.Click(BaseTest.getDriver(), Requisitions);

            //Enter Requisition Number
            //Load Excel File
            String path="D:\\Prologic\\src\\test\\resources\\TestData\\TestData.xlsx";
            ExcelHandler.loadExcelFile(path, 3);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            String ReqNo=ExcelHandler.getCellData("1", "Req No");
            Utilities.SendKeys(BaseTest.getDriver(), NumberInputField, ReqNo);
            
            
            
            
            
            
            
           
           // Utilities.SendKeys(BaseTest.getDriver(), NumberInputField, getRequisitionNumber());
            Utilities.Click(BaseTest.getDriver(), SearchButton);


            //ViewEdit Item
            Utilities.Click(BaseTest.getDriver(), ViewButton);

            //Change Quantity
            Actions actions = new Actions(BaseTest.getDriver());
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), ChangeQty);
            //
            Utilities.Click(BaseTest.getDriver(), ChangeQty);
            //getrequestQuantityelement(5).click();
            actions.keyDown(Keys.CONTROL)
                    .sendKeys("a")
                    .keyUp(Keys.CONTROL)
                    .sendKeys("50")
                    .perform();
 
            //Add row
            Utilities.Click(BaseTest.getDriver(), AddRow);
            Utilities.SendKeys(BaseTest.getDriver(), getItemSearchElement(6), "%%");
            getItemSearchElement(1).sendKeys(Keys.TAB);
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
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getrequestQuantityelement(1));
            getrequestQuantityelement(6).click();
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

package reqApproval;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import datahandlers.ExcelHandler;
import extentreports.ExtentTestManager;
import pages.CommonPages.PortalLoginPage;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.Assertion;

import utils.DynamicWait;
import utils.Utilities;
import utils.Utilities.*;


import java.time.Duration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import java.io.File;
import java.security.PrivateKey;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReqViewEditPage {
    /**
     * Constructor
     */
    public ReqViewEditPage(WebDriver driver) {
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
//    @FindBy(xpath = "//img[@id=\"img_item_id_1_0\"]")
//    private WebElement ItemDropdown;
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
    @FindBy(xpath = "//*[@id=\"imgLeftMenuOpen\"]")
    private WebElement pers;  //*[@id="imgLeftMenuOpen"]
    @FindBy(id="tdUserMail")
    private WebElement UserMail;
    @FindBy(xpath = "//*[@id=\"td_0_1\"]/b")
    private WebElement Mail;
    @FindBy(xpath = "//*[@id=\"divbody_preview\"]/div/a[2]")
    private WebElement Approve2;
    @FindBy(xpath = "//*[@id=\"divbody_preview\"]/div/a[2]")
    private WebElement ApproveMail;
    
//    @FindBy(xpath = "//td[@id=\"td_0_24\"]")
//    private WebElement SelectApprove;
    
    @FindBy(id="td_0_1")
    private WebElement ApprovalView;
    @FindBy(xpath = "//select[@class=\"gridInput\"]")
    private WebElement SelectApprove;
    @FindBy(id="cphDialogHeader_btnSave")
    private WebElement SaveBtn;
    @FindBy(id="td_0_24") 
    private WebElement SelectTD;
    @FindBy(xpath = "//*[@id=\"ddlaction~73713~11 Sep 2024~0~0.00~1~1~IQ    \"]/option[2]")
    private WebElement SelectAprove;
    @FindBy(xpath = "//option[@value=\"AP\"]")
    private WebElement Aprove;
    
    @FindBy(id="cphBody_txtNumber")
    private WebElement EnterReqNo;
    @FindBy(id="cphBody_btnSearch")
    private WebElement SearchBtn;
    @FindBy(id="cphBrowserHeader_btnViewEdit")
    private WebElement ViewEdit;
    
    @FindBy(id="cphBody_txtLastStatus")
    private WebElement Approval;
    
    @FindBy(xpath = "//input[starts-with(@id,'item_id_1_')]")
    private WebElement Items;
    
    

    @FindBy(id="cphDialogHeader_btnSave")
    private WebElement ProcessBtn;
    @FindBy(id="btnOK")
    private WebElement OKBtn;
    @FindBy(id="cphDialogHeader_btnClose_Dlg") 
    private WebElement CloseBtn;
    @FindBy(id="cphBrowserHeader_btnClose_Brw")
    private WebElement CloseBtn1;
    @FindBy( id="tdApproved")
    private WebElement SelectApprove1;
    @FindBy(xpath = "//option[@value=\"AP\"]")
    private WebElement SelectAP;
    
    
    @FindBy(id="cphHeader_lnkHelp")
    private WebElement help;
    @FindBy(xpath = "//*[@id=\"divbody_preview\"]/div/a[1]")  //*[@id="divbody_preview"]/div/a[1]
    private WebElement ViewEdit1;
    
    
    @FindBy(xpath = "//input[starts-with(@id,'qty_indent_9_')]")   
    private WebElement Quantity;
    

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
     * @Modified_Date : 01/11/24
     **/
    public void ViewEdit(String UserName) {
        try {
            
        	 String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "TestData.xlsx";
        	   
            
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), pers);
            
            
            
            String currentHandles1=BaseTest.getDriver().getWindowHandle();
            System.out.println(currentHandles1);
            
            //
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver()	, UserMail);
            BaseTest.getDriver().switchTo().defaultContent();
            
            
            
            // Switch to the new window after login
            Set<String> handle1 = BaseTest.getDriver().getWindowHandles();
            System.out.println(handle1);
            for (String actual : handle1) {
                if (!actual.equalsIgnoreCase(currentHandles1)&!actual.equalsIgnoreCase(PortalLoginPage.currentHandles)) {
                    BaseTest.getDriver().switchTo().window(actual);
                }
            }
            
            
          
            
            System.out.println();
            
            //Enter into third window
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");  
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Mail);
            
           // Utilities.Click(BaseTest.getDriver(), help);
            //Utilities.ActionClick(BaseTest.getDriver(), AddButton);
           // actions.doubleClick(BaseTest.getDriver().findElement(By.id("td_0_1")));
            
            Actions actions = new Actions(BaseTest.getDriver());
            actions.doubleClick(BaseTest.getDriver().findElement(By.id("td_0_1"))).perform();
            
            Utilities.Click(BaseTest.getDriver(), ViewEdit1);
            
            
            
            
            
            
            //
            Random random=new Random();
            Set<String> usedTexts = new HashSet<>();
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(BaseTest.getDriver(), AddRow);
            
            for (int i = 0; i < 2; i++) {
                if (i > 0) { // Execute only after i = 0
                	 BaseTest.getDriver().switchTo().defaultContent();
                     BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                    Utilities.Click(BaseTest.getDriver(), AddRow);
                }
                Utilities.SendKeys(BaseTest.getDriver(), Items, "%%");
                getItemSearchElement(i).sendKeys(Keys.TAB);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
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
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");    
                //Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Quantity);
                Quantity.click();
                actions.keyDown(Keys.CONTROL)
                        .sendKeys("a")
                        .keyUp(Keys.CONTROL)
                        .sendKeys("10")
                        .perform();
            }
            
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            
            Utilities.Click(BaseTest.getDriver(), SelectApprove1);
            Utilities.Click(BaseTest.getDriver(), SelectAP);
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
            Pattern pattern = Pattern.compile("Requisition Number\\s(\\d+)");
            Matcher matcher = pattern.matcher(input);

            if (matcher.find()) {
                RequisitionNumber = matcher.group(1);
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

                // Set the "User" in the same row
                ExcelHandler.setCellData("User", UserName, rowCount);

                // Save changes to the Excel file
                ExcelHandler.saveExcelFile();
            }


            Utilities.Click(BaseTest.getDriver(), OkButton);
            

            
            
            
           
            
            //Select Approve
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
//            Utilities.Click(BaseTest.getDriver(), SelectTD);
//            Utilities.Click(BaseTest.getDriver(), Aprove);
//            Utilities.Click(BaseTest.getDriver(), ProcessBtn);
//            
//             
//            //Click on ok Button
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.Click(BaseTest.getDriver(), OKBtn);
//            
//            
//            //Click on Close Button
////            BaseTest.getDriver().switchTo().defaultContent();
////            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
////            Utilities.Click(BaseTest.getDriver(), CloseBtn);
////            BaseTest.getDriver().switchTo().defaultContent();
//            
//            
//            
//            //Switch to previous window i.e. Second window
//            BaseTest.getDriver().switchTo().window(currentHandles1);
//            
//            
//            //Click on Close Button
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            Utilities.Click(BaseTest.getDriver(), CloseBtn);
//            
//            
//            //Click on Close Button again
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
//            Utilities.Click(BaseTest.getDriver(), CloseBtn1);
//            
//            
//            
//            
//            //
//            // Switch to the main content and then to the required frame
////            BaseTest.getDriver().switchTo().defaultContent();
////            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
////
////            // Wait for Inventory element and click it
////            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Inventory);
////            Utilities.Click(BaseTest.getDriver(), Inventory);
//
//
//            //Inventory Module Select
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
//            Utilities.Click(BaseTest.getDriver(), InventoryModule);
//            
//            
//          //Requisitation Type Select
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Requisitions);
//            Utilities.Click(BaseTest.getDriver(), Requisitions);
//            
//            
//            
//            
//            ////Get Requisition Number from the Excel Sheet
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");  
//            //wait(1000);
//            ExcelHandler.loadExcelFile(Updload_Path, 3);
//            
//            List<String> Resources1 = ExcelHandler.getAllColumnData("Requisition Number");
//           
//            //for(int i=0;i<Resources1.size();i++) {
//            String resource1 = Resources1.getLast();
//            
//            				
//            
//            
//            //String resources1 = ExcelHandler.getCellData("1", "Req No");
//            //String resource1 = Resources1.get(2);
//            Utilities.SendKeys(BaseTest.getDriver(), EnterReqNo, resource1);
//           // }
//            Utilities.Click(BaseTest.getDriver(), SearchBtn);
//            Utilities.Click(BaseTest.getDriver(), ViewEdit);
//            DynamicWait.smallWait();
//            BaseTest.getDriver().switchTo().defaultContent();
//            
//            
//            
//            //check whether requisition has been approved or not
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//            
//            
//            String Text=Approval.getAttribute("value");
//            String Text1="Approve";
//            if(Approval.getText()==Text1) {
//            	System.out.println("Requisition has been approved" +Text);
//            	 ExtentTestManager.createAssertTestStepWithScreenshot("Verify the Requisition has been Approved", Status.PASS,
//                         "Navigation on Product Page working fine", true);
//            } else {
//				System.out.println("Requisition is in "+Text+ "State");
//				 ExtentTestManager.createAssertTestStepWithScreenshot("Verify the navigation on tapping login", Status.FAIL,
//	                        "Navigation on Product Page working fine", true);
//			}
//            
//            //Assert.assertEquals("Approve", Text);
//
//           
//            
//            
//            
//         
//            
// 
//
//         
//            
//            
            
            
            

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);
        }
    }

} 
    
    
    
    
    
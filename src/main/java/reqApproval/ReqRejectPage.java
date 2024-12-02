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

public class ReqRejectPage {
    /**
     * Constructor
     */
    public ReqRejectPage(WebDriver driver) {
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
    @FindBy(xpath = "//option[@value=\"OH\"][1]")
    private WebElement OnHold;
    
    @FindBy(xpath = "//option[@value=\"RJ\"][1]")    //option[@value=""][1]
    private WebElement Reject;
    @FindBy(xpath = "//input[@class=\"gridInput\"]")
    private WebElement Reason;
    
    @FindBy(id="cphBody_txtNumber")
    private WebElement EnterReqNo;
    @FindBy(id="cphBody_btnSearch")
    private WebElement SearchBtn;
    @FindBy(id="cphBrowserHeader_btnViewEdit")
    private WebElement ViewEdit;
    
    @FindBy(id="cphBody_txtLastStatus")
    private WebElement Approval;
    
    @FindBy(id="tdApproved")
	private WebElement ReInstate;
	@FindBy(xpath = "//option[@value=\"RI\"][1]")
	private WebElement Reinstate;
    
    
    
    
    
    
//    @FindBy(xpath = "//*[@id=\"ddlaction~73692~11 Sep 2024~0~11105.60~1~1~IQ    \"]")
//    private WebElement SelectApprove;
    @FindBy(id="cphDialogHeader_btnSave")
    private WebElement ProcessBtn;
    @FindBy(id="btnOK")
    private WebElement OKBtn;
    @FindBy(id="cphDialogHeader_btnClose_Dlg") 
    private WebElement CloseBtn;
    @FindBy(id="cphBrowserHeader_btnClose_Brw")
    private WebElement CloseBtn1;
    
    
    @FindBy(id="cphHeader_lnkHelp")
    private WebElement help;
    
    
    
    

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
    public void Approve() {
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
            
            Utilities.Click(BaseTest.getDriver(), ApproveMail);
            
            //Select Approve
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(BaseTest.getDriver(), SelectTD);
            
            
            
            
            
            WebElement element = BaseTest.getDriver().findElement(By.id("td_0_24"));

            // Create an Actions object
            //Actions actions = new Actions(driver);

            // Press the "Arrow Down" key using the Actions class
            actions.moveToElement(element)       // Focus on the element
                   .sendKeys(Keys.ARROW_DOWN)  // Press the Arrow Down key
                   .sendKeys(Keys.ARROW_DOWN)
                   .sendKeys(Keys.ARROW_DOWN)
                  // .sendKeys(Keys.ARROW_DOWN)
                   .perform();                  // Execute the action

            // Optionally: Perform other actions like sending other keys or combinations
            actions.sendKeys(Keys.ENTER).perform(); 
            
            
            
            
            
            
            
            
            
            
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(BaseTest.getDriver(), Reject);
            Utilities.SendKeys(BaseTest.getDriver(), Reason, "Need more items");
            Utilities.Click(BaseTest.getDriver(), ProcessBtn);
            
            
//            //Click on ok Button
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//            Utilities.Click(BaseTest.getDriver(), OKBtn);
            
          
            
            
            
            //Switch to previous window i.e. Second window
            BaseTest.getDriver().switchTo().window(currentHandles1);
            
            
            //Click on Close Button
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), CloseBtn);
            
            
            //Click on Close Button again
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(BaseTest.getDriver(), CloseBtn1);
            
            
            
            
            //
            // Switch to the main content and then to the required frame
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
//
//            // Wait for Inventory element and click it
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Inventory);
//            Utilities.Click(BaseTest.getDriver(), Inventory);


            //Inventory Module Select
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
            Utilities.Click(BaseTest.getDriver(), InventoryModule);
            
            
             //Requisitation Type Select
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Requisitions);
            Utilities.Click(BaseTest.getDriver(), Requisitions);
            
            
            
            
            ////Get Requisition Number from the Excel Sheet
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");  
            //wait(1000);
            ExcelHandler.loadExcelFile(Updload_Path, 3);
            
            List<String> Resources1 = ExcelHandler.getAllColumnData("Requisition Number");
           
            //for(int i=0;i<Resources1.size();i++) {
            String resource1 = Resources1.getLast();
            
            				
            
            
            //String resources1 = ExcelHandler.getCellData("1", "Req No");
            //String resource1 = Resources1.get(2);
            Utilities.SendKeys(BaseTest.getDriver(), EnterReqNo, resource1);
           // }
            Utilities.Click(BaseTest.getDriver(), SearchBtn);
            Utilities.Click(BaseTest.getDriver(), ViewEdit);
            
            
            DynamicWait.smallWait();
            BaseTest.getDriver().switchTo().defaultContent();
            
             
            
            //check whether requisition has been approved or not
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            
            
//            String Text=Approval.getText();
//            String Text1="Approve";
//            if(Approval.getText()==Text1) {
//            	System.out.println("Requisition has been approved" +Text);
//            } else {
//				System.out.println("Requisition is in "+Text+ "State");
//			}
            
            //Assert.assertEquals("Approve", Text);

           
            
            String Text=Approval.getAttribute("value");
            String Text1="Approve";
            if(Approval.getText()==Text1) {
            	System.out.println("Requisition has been approved" +Text);
            	 ExtentTestManager.createAssertTestStepWithScreenshot("Verify the Requisition has been Approved", Status.PASS,
                         "Navigation on Product Page working fine", true);
            } else {
				System.out.println("Requisition is in "+Text+ "State");
				 ExtentTestManager.createAssertTestStepWithScreenshot("Verify the navigation on tapping login", Status.FAIL,
	                        "Navigation on Product Page working fine", true);
			}   
            
         
            
            
            //
            BaseTest.getDriver().switchTo().defaultContent();
        	BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
        	
        	//Click on ReInstate
        	Utilities.Click(BaseTest.getDriver(), ReInstate);
        	Utilities.Click(BaseTest.getDriver(), Reinstate);
        	Utilities.Click(BaseTest.getDriver(), SaveButton);
 

         
            
            
            
            
            

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);
        }
    }
    
}
    
    
    
    
    	
    	
    	
    	
    	
    	
    


    
    
    
    
    
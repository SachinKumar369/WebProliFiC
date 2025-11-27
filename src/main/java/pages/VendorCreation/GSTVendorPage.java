package pages.VendorCreation;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class GSTVendorPage {
    public GSTVendorPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
    String uniqueText;

    @FindBy(id = "lblErrorDesc")
    private WebElement ConfDlg;
    @FindBy(id = "btnOK")
    private WebElement OkBtn;
    @FindBy(id = "cphBody_lblAttachDocument")
    private WebElement AttachDoc;
    @FindBy(id = "txtUploadPic")
    private WebElement ChoseFile;
    @FindBy(id = "btnUpload")
    private WebElement Upload;
    @FindBy(id = "//*[@id=\"td1\"]/img")
    private WebElement Close;
    @FindBy(id = "txtattachment")
    private WebElement AttachmentConf;




    @FindBy(xpath = "//a[text()='Vendor Setup']")
    private WebElement VendorSetup;
    @FindBy(id = "LinkProduct2")
    private WebElement Inventory;
    @FindBy(id = "divmodule_2")
    private WebElement InventoryModule;
    @FindBy(id = "cphDialogHeader_btnAddRecords")
    private WebElement Add;
    @FindBy(id = "cphBody_imgPopupap_type")
    private WebElement AccountGroup;
    @FindBy(id = "td_0_0")
    private WebElement SelectAccGroup;
    @FindBy(id = "cphBody_txtap_id")
    private WebElement AccountID;
    @FindBy(id = "cphBody_txtap_name")
    private WebElement AccountName;
    @FindBy(id = "cphBody_txtap_address_1")
    private WebElement Address;
    @FindBy(id = "cphBody_txtap_city")
    private WebElement City;
    @FindBy(id = "cphBody_txtap_state")
    private WebElement State;
    @FindBy(id = "cphBody_txtap_zip")
    private WebElement Zip;
    @FindBy(id = "cphBody_txtap_country")
    private WebElement Country;
    @FindBy(id = "cphBody_ddlstate")
    private WebElement GstState;
    @FindBy(id = "cphDialogHeader_btnSave")
    private WebElement Save;

    @FindBy(id = "cphBody_imgfx_code")
    private WebElement CurrencyDropdown;
    @FindBy(id = "td_4_0")
    private WebElement SelectCurrency;
    @FindBy(id = "cphBody_ddlvendorTaxCategory")
    private WebElement VendorTaxCategory;
    @FindBy(id = "cphBody_txtap_gst_no")
    private WebElement GSTRegistrationNo;
    @FindBy(id = "cphBody_txtpan_no")
    private WebElement PanNo;




    public void VendorTax() {
        try {


            Utilities.logerror("VendorCreation", logMessages -> {
                // Step 1: Maximize window
                BaseTest.getDriver().manage().window().maximize();
                logMessages.add("Maximized browser window");

                // Step 2: Switch to frame
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                logMessages.add("Switched to MultiPageiframeBrw frame");

                // Step 3: Click Inventory
                Utilities.Click(Inventory);
                logMessages.add("Clicked on Inventory");

                // Step 4: Switch to frame again
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                logMessages.add("Switched to MultiPageiframeBrw frame");

                // Step 5: Click Inventory Module
                Utilities.Click(InventoryModule);
                logMessages.add("Clicked on Inventory Module");

                // Step 6: Click Vendor Setup
                Utilities.Click(VendorSetup);
                logMessages.add("Clicked on VendorSetup");

                // Step 7: Switch to frame again
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                logMessages.add("Switched to MultiPageiframeBrw frame");

                // Step 8: Click Add
                Utilities.Click(Add);
                logMessages.add("Clicked on Add Button");

                // Step 9: Click Account Group Dropdown
                Utilities.Click(BaseTest.getDriver(),AccountGroup);
                logMessages.add("Clicked on Account Group Dropdown");

                // Step 10: Select Account Group
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                Utilities.DoubleClick(SelectAccGroup);
                logMessages.add("Selected the Account Group");

                // Step 11: Switch back to main frame
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                logMessages.add("Switched to MultiPageiframeBrw frame");

                // Step 12: Generate unique text for Account ID
                uniqueText = new SimpleDateFormat("ddyyHHmm").format(new Date());
                logMessages.add("Generated unique text for Account ID: " + uniqueText);

                // Step 13: Enter Account ID
                Utilities.SendKeys(AccountID, uniqueText);
                logMessages.add("Entered Account ID");

                // Step 14: Enter Account Name
                Utilities.SendKeys(AccountName, "Testing");
                logMessages.add("Entered Account Name");

                // Step 15: Enter Address
                Utilities.SendKeys(Address, "Phase V");
                logMessages.add("Entered Address");

                // Step 16: Enter City
                Utilities.SendKeys(City, "Meerut");
                logMessages.add("Entered City");

                // Step 17: Enter State
                Utilities.SendKeys(State, "UP");
                logMessages.add("Entered State");

                // Step 18: Enter Zip
                Utilities.SendKeys(Zip, "250002");
                logMessages.add("Entered Pin Code");

                // Step 19: Select GST State
                Utilities.selectBy("value", GstState, "09");
                logMessages.add("Entered GST State");

                // Step 20: Enter Country
                Utilities.SendKeys(Country, "India");
                logMessages.add("Entered Country");

                // Step 21: Select Currency
                try {

                    Utilities.Click(CurrencyDropdown);
                    logMessages.add("Clicked on Currency Dropdown");
                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                    logMessages.add("Switched to iframeGridDialog frame");
                    Utilities.DoubleClick(SelectCurrency);
                    logMessages.add("Selected Currency");
                } catch (Exception e) {

                }
                // Step 22: Switch back to main frame
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                logMessages.add("Switched to MultiPageiframeBrw frame");

                Utilities.selectBy("value",VendorTaxCategory,"RG");
                Utilities.SendKeys(GSTRegistrationNo,"09AAJCR2207E1Z2");
                Utilities.SendKeys(PanNo,"AAJCR2207E");



                // Step 23: Click Save
                Utilities.Click(Save);
                logMessages.add("Clicked Save");

                // Step 24: Check confirmation dialog
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                logMessages.add("Switched to iframeGridDialog frame");
                if (ConfDlg.isDisplayed() && ConfDlg.getText().contains("[238] - Details Created/Updated")) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Creation", Status.PASS, "Vendor Creation Passed", true);
                } else {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Creation", Status.FAIL, "Vendor Creation Failed", true);
                }
            });
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Creation9", Status.FAIL, "Vendor Creation Failed", true);

        }
    }


    public void Validations(){
        try {
            Utilities.logerror("Validations",logMessages -> {


                BaseTest.getDriver().manage().window().maximize();
                logMessages.add("Maximized browser window");

                // Step 2: Switch to frame
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                logMessages.add("Switched to MultiPageiframeBrw frame");

                // Step 3: Click Inventory
                Utilities.Click(Inventory);
                logMessages.add("Clicked on Inventory");

                // Step 4: Switch to frame again
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                logMessages.add("Switched to MultiPageiframeBrw frame");

                // Step 5: Click Inventory Module
                wait.until(ExpectedConditions.elementToBeClickable(InventoryModule));
                Utilities.Click(InventoryModule);
                logMessages.add("Clicked on Inventory Module");

                // Step 6: Click Vendor Setup
                Utilities.Click(VendorSetup);
                logMessages.add("Clicked on VendorSetup");

                // Step 7: Switch to frame again
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                logMessages.add("Switched to MultiPageiframeBrw frame");

                // Step 8: Click Add
                Utilities.Click(Add);
                logMessages.add("Clicked on Add Button");

                Utilities.Click(Save);
                logMessages.add("Clicked on Save Button");

                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
                if (ConfDlg.isDisplayed()&&ConfDlg.getText().contains("[499]- Please enter Account ID")){
                    ExtentTestManager.createAssertTestStepWithScreenshot("Account ID",Status.PASS,"Account ID Validation Passed",true );
                    Utilities.Click(OkBtn);
                }else {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Account ID",Status.FAIL,"Account ID Validation Failed",true );

                }
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                uniqueText = new SimpleDateFormat("ddyyHHmm").format(new Date());
                Utilities.SendKeys(AccountID,uniqueText);
                ExtentTestManager.getTest().log(Status.INFO,"Enter Account ID");
                Utilities.Click(Save);
                logMessages.add("Clicked on Save Button");


                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
                if (ConfDlg.isDisplayed()&&ConfDlg.getText().contains("[8272]-Account Group can't be blank")){
                    ExtentTestManager.createAssertTestStepWithScreenshot("Account ID",Status.PASS,"Account ID Validation Passed",true );
                    Utilities.Click(OkBtn);
                }else {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Account ID",Status.FAIL,"Account ID Validation Failed",true );

                }


                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                Utilities.Click(AccountGroup);

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                Utilities.DoubleClick(SelectAccGroup);


                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                Utilities.Click(Save);
                logMessages.add("Clicked on Save Button");

                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
                if (ConfDlg.isDisplayed()&&ConfDlg.getText().contains("[230] - Please Enter Name")){
                    ExtentTestManager.createAssertTestStepWithScreenshot("Account ID",Status.PASS,"Account ID Validation Passed",true );
                    Utilities.Click(OkBtn);
                }else {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Account ID",Status.FAIL,"Account ID Validation Failed",true );
                }

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                Utilities.SendKeys(AccountName,"Testing");
                ExtentTestManager.getTest().log(Status.INFO,"Enter Account Name");

                Utilities.Click(Save);
                logMessages.add("Clicked on Save Button");
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
                if (ConfDlg.isDisplayed()&&ConfDlg.getText().contains("[227] - Please Enter Address")){
                    ExtentTestManager.createAssertTestStepWithScreenshot("Account ID",Status.PASS,"Account ID Validation Passed",true );
                    Utilities.Click(OkBtn);
                }else {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Account ID",Status.FAIL,"Account ID Validation Failed",true );
                }


                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                Utilities.SendKeys(Address,"Phase V");


//                Utilities.Click(Save);
//                logMessages.add("Clicked on Save Button");
//
//                BaseTest.getDriver().switchTo().defaultContent();
//                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
//                if (ConfDlg.isDisplayed()&&ConfDlg.getText().contains("[218] - Invalid Currency Code , Currency code is mandatory")){
//                    ExtentTestManager.createAssertTestStepWithScreenshot("Account ID",Status.PASS,"Account ID Validation Passed",true );
//                    Utilities.Click(OkBtn);
//                }else {
//                    ExtentTestManager.createAssertTestStepWithScreenshot("Account ID",Status.FAIL,"Account ID Validation Failed",true );
//                }


                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

                try {
                    Utilities.Click(CurrencyDropdown);
                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                    Utilities.DoubleClick(SelectCurrency);
                }catch (Exception e){

                }


                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");


                Utilities.Click(Save);
                logMessages.add("Clicked on Save Button");

                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
                if (ConfDlg.isDisplayed() && ConfDlg.getText().contains("[238] - Details Created/Updated1")) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Account ID", Status.PASS, "Account ID Validation Passed", true);
                    //
                    // Utilities.Click(OkBtn);
                } else {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Account ID", Status.FAIL, "Account ID Validation Failed", true);
                }


            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Attachment() {
        try {
            Utilities.logerror("VendorCreation", logMessages -> {
                String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "image.jpg";

                Utilities.Click(OkBtn);
                logMessages.add("Click ok");

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                Utilities.Click(AttachDoc);


                // Click Attachment
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                BaseTest.getDriver().switchTo().frame("iframeAttachment");

                Utilities.SendKeys(ChoseFile, path);
                Utilities.Click(Upload);

                if (!(AttachmentConf.getAttribute("value").isEmpty()||AttachmentConf.getAttribute("value").isBlank())){
                    ExtentTestManager.createAssertTestStepWithScreenshot("Attachement",Status.PASS,"Document Attached Successfully",true);
                }else {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Attachement",Status.FAIL,"Document Attachment Failed",true);

                }







//                if (!Freeze.isSelected()) {
//                    Utilities.ClickCheckBox(Freeze);
//                    logMessages.add("Clicked Freeze checkbox");
//                }
//
////                Utilities.Click(Save);
////                logMessages.add("Clicked Save");
//
//                BaseTest.getDriver().switchTo().defaultContent();
//                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
//
//
//                Utilities.Click(Approval);
//                //Utilities.Click(Freeze);
//
//                logMessages.add("Click on Approval");
//
//                Utilities.selectBy("value", ApprovalStatus, "UP");
//                logMessages.add("Select Approval form the Dropdown");
//
//                Utilities.Click(Save);
//                logMessages.add("Click on Save");
//
//
//                BaseTest.getDriver().switchTo().defaultContent();
//                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//                if (ConfDlg.isDisplayed()&&ConfDlg.getText().contains("[238] - Details Created/Updated"))
//                {
//                    ExtentTestManager.createAssertTestStepWithScreenshot("Item Freeze", Status.PASS,"Item Freeze Sucessfully",true);
//                }else {
//
//                    ExtentTestManager.createAssertTestStepWithScreenshot("Item Freeze",Status.FAIL,"Error while Freezing Item",true);
//                }
            });

        } catch (Exception e) {
            // Log all accumulated messages only on failure

            ExtentTestManager.createAssertTestStepWithScreenshot("testing", Status.FAIL, "", true, e);
        }
    }

    public void GST() {
        try {


            Utilities.logerror("VendorCreation", logMessages -> {
                // Step 1: Maximize window
                BaseTest.getDriver().manage().window().maximize();
                logMessages.add("Maximized browser window");

                // Step 2: Switch to frame
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                logMessages.add("Switched to MultiPageiframeBrw frame");

                // Step 3: Click Inventory
                Utilities.Click(Inventory);
                logMessages.add("Clicked on Inventory");

                // Step 4: Switch to frame again
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                logMessages.add("Switched to MultiPageiframeBrw frame");

                // Step 5: Click Inventory Module
                Utilities.Click(InventoryModule);
                logMessages.add("Clicked on Inventory Module");

                // Step 6: Click Vendor Setup
                Utilities.Click(VendorSetup);
                logMessages.add("Clicked on VendorSetup");

                // Step 7: Switch to frame again
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                logMessages.add("Switched to MultiPageiframeBrw frame");

                // Step 8: Click Add
                Utilities.Click(Add);
                logMessages.add("Clicked on Add Button");

                // Step 9: Click Account Group Dropdown
                Utilities.Click(BaseTest.getDriver(),AccountGroup);
                logMessages.add("Clicked on Account Group Dropdown");

                // Step 10: Select Account Group
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                Utilities.DoubleClick(SelectAccGroup);
                logMessages.add("Selected the Account Group");

                // Step 11: Switch back to main frame
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                logMessages.add("Switched to MultiPageiframeBrw frame");

                // Step 12: Generate unique text for Account ID
                uniqueText = new SimpleDateFormat("ddyyHHmm").format(new Date());
                logMessages.add("Generated unique text for Account ID: " + uniqueText);

                // Step 13: Enter Account ID
                Utilities.SendKeys(AccountID, uniqueText);
                logMessages.add("Entered Account ID");

                // Step 14: Enter Account Name
                Utilities.SendKeys(AccountName, "Testing");
                logMessages.add("Entered Account Name");

                // Step 15: Enter Address
                Utilities.SendKeys(Address, "Phase V");
                logMessages.add("Entered Address");

                // Step 16: Enter City
                Utilities.SendKeys(City, "Meerut");
                logMessages.add("Entered City");

                // Step 17: Enter State
                Utilities.SendKeys(State, "UP");
                logMessages.add("Entered State");

                // Step 18: Enter Zip
                Utilities.SendKeys(Zip, "250002");
                logMessages.add("Entered Pin Code");

                // Step 19: Select GST State
                Utilities.selectBy("value", GstState, "09");
                logMessages.add("Entered GST State");

                // Step 20: Enter Country
                Utilities.SendKeys(Country, "India");
                logMessages.add("Entered Country");

                // Step 21: Select Currency
                try {

                    Utilities.Click(CurrencyDropdown);
                    logMessages.add("Clicked on Currency Dropdown");
                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                    logMessages.add("Switched to iframeGridDialog frame");
                    Utilities.DoubleClick(SelectCurrency);
                    logMessages.add("Selected Currency");
                } catch (Exception e) {

                }
                // Step 22: Switch back to main frame
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                logMessages.add("Switched to MultiPageiframeBrw frame");

               // Utilities.selectBy("value",VendorTaxCategory,"RG");
                Utilities.SendKeys(GSTRegistrationNo,"09AAJCR2207E1Z2");
                Utilities.SendKeys(PanNo,"AAJCR2207E");



                // Step 23: Click Save
                Utilities.Click(Save);
                logMessages.add("Clicked Save");

                // Step 24: Check confirmation dialog
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                logMessages.add("Switched to iframeGridDialog frame");
                if (ConfDlg.isDisplayed() && ConfDlg.getText().contains("[238] - Details Created/Updated")) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Creation", Status.PASS, "Vendor Creation Passed", true);
                } else {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Creation", Status.FAIL, "Vendor Creation Failed", true);
                }
            });
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Creation9", Status.FAIL, "Vendor Creation Failed", true);

        }
    }

    public void Delete(){
        Utilities.logerror("Delete",logMessages -> {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("");
        });
    }

}

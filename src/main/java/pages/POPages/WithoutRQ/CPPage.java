package pages.POPages.WithoutRQ;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DynamicWait;
import utils.Utilities;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.yaml.snakeyaml.tokens.Token.ID.Value;

/**
 * Author: SACHIN KUMAR (replace sachin with your name or a fixed string)
 * Date: 23-01-2025
 */
public class CPPage {

    /**
     * Constructor
     */

    public CPPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static String PONumber;


    /**
     * Web Elements
     */

//    @FindBy(xpath = "//a[@id='LinkProduct2']")
//    private WebElement Inventory;


            @FindBy(xpath = "//a[text()='Inventory']")
            private WebElement Inventory;

    @FindBy(id = "divmodule_2")
    private WebElement InventoryModule;


    @FindBy(id = "cphBody_lblWithPurchaseRequisition")
    private WebElement WithPurchaseReq;
    @FindBy(id = "btnOk")
    private WebElement OKBtn;
    @FindBy(xpath = "//a[text()=\"Purchase Orders\"][1]")
    private WebElement PurchaseOrder;
    @FindBy(id = "cphBody_imgType")
    private WebElement SelectType;
    @FindBy(id = "col_0")
    private WebElement POType;
    @FindBy(id = "cphBody_txtDateTo")
    private WebElement ToDate;
    @FindBy(id = "cphBody_txtVendor")
    private WebElement Search_vendor;
    @FindBy(id = "cphBody_btnEnterItemDetails")
    private WebElement EnterItemDetails;
    @FindBy(id = "txtNumber")
    private WebElement RequisitionNumber;
    @FindBy(id = "btnSearch")
    private WebElement SearchBtn;
    @FindBy(id = "cphBody_txtType")
    private WebElement EnterPOType;
    @FindBy(id = "divMsg")
    private WebElement ConfirmationDialog;
    @FindBy(css = "input[id^='ischeck_13_']")
    private List<WebElement> checkBoxes;

    public WebElement getItemRate(int itemId) {
        String xpath3 = "//input[@id='item_rate_9_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    @FindBy(xpath = "//input[starts-with(@id, 'item_rate_9_')]")
    private List<WebElement> itemRates;
    @FindBy(id = "cphDialogHeader_btnSaveClose")
    private WebElement SaveBtn;
    @FindBy(id = "cphDialogHeader_btnPrint")
    private WebElement Print;
    @FindBy(id = "cphBody_txtVendor")
    private WebElement Vendor;
    @FindBy(id = "cphBody_imgVendor")
    private WebElement VendorImg;
    @FindBy(xpath = "//td[@class=\"ControlItemStyle\"][1]")
    private List<WebElement> VendorList;
    @FindBy(xpath = "//input[@id='col_1']")
    private WebElement Filter;
    @FindBy(id = "btnOK")
    private WebElement FirstColumn;
    @FindBy(id = "btnCancel")
    private WebElement Cancel;
    @FindBy(id = "btnClose")
    private WebElement Close;
    @FindBy(id = "rdoFinal")
    private WebElement rdFinal;
    @FindBy(id = "btnCancel")
    private WebElement CancelBtn;
    @FindBy(id = "cphDialogHeader_btnAddRecords")
    private WebElement RevokeApproval;
    @FindBy(xpath = "//input[@name='btnOK']")
    private WebElement OKBTN;
    @FindBy(xpath = "//*[starts-with(@id, 'item_rate_9_')]")
    private List<WebElement> itemRate;

    @FindBy(id = "cphBrowserHeader_btnAddNew")
    private WebElement AddButton;
    @FindBy(id = "chkSendEmail")
    private WebElement Sendmail;



    public static void setPONumber(String poNumber) {
        PONumber = poNumber;
    }

    public WebElement getItemSearchElement(int itemId) {
        String xpath = "//input[@id='item_id_1_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath));
    }


    public WebElement getrequestQuantityelement(int itemId) {
        String xpath3 = "//input[@id='qty_ordered_5_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    @FindBy(id = "cphBody_lblCharges_1")
    private WebElement Charge;
    @FindBy(id = "item_desc_2_0")
            private WebElement SelectItem;
    @FindBy(id = "charge_id_1_0")
            private WebElement EnterCharge;
    @FindBy(id = "value_5_0")
    private WebElement value;
    @FindBy(id = "btnSave")
    private WebElement Save;
    @FindBy(xpath = "//input[@type='submit' and @value='OK']")
    private WebElement ok;
    @FindBy(id = "cphBody_lblCharges")
    private WebElement POCharge;
    @FindBy(id = "cphBody_ddlApproved")
    private WebElement Approval;



    /**
     * Methods
     */

    // method to create PO without Requisition

    //WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(),Duration.ofSeconds(10));
    public void CPPOCreation() {

        try {
            // Switch to the main content and then to the required frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Wait for Inventory element and click it
            DynamicWait.longWait();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Inventory);
            Utilities.Click(BaseTest.getDriver(), Inventory);

            //Inventory Module Select
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
            Utilities.Click(BaseTest.getDriver(), InventoryModule);

            //Click on Purchase Order
            Utilities.Click(BaseTest.getDriver(), PurchaseOrder);

            //Click on Add Button
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), AddButton);
            Utilities.Click(BaseTest.getDriver(), AddButton);

            //Select PO Type
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), SelectType);

            //Select PO Type form the popup
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.SendKeys(BaseTest.getDriver(), POType, "CP");
            Utilities.Click(BaseTest.getDriver(), FirstColumn);

            //Select To Date
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(BaseTest.getDriver(), ToDate, "12/09/2025");
            String Value = "CCL001";
            //Select Vendor
            Utilities.SendKeys(BaseTest.getDriver(), Search_vendor, "CCL001");

            //Click Enter Items Details
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);

        }
    }


    public void Print(){

        WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
        try {

            try {
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

                WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));

                try {
                    OKBtn.click();
                    System.out.println("OK Button clicked successfully!");
                } catch (Exception e) {
//                    System.out.println("Normal click failed, trying JavaScript click.");
//                    ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].click();", OKBtn);
                }

            } catch (Exception e) {
                //System.out.println("Error: " + e.getMessage());
            }


            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeDlg"));
            Utilities.Click(BaseTest.getDriver(),Print);


            try {
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
                try
                {
                    wait.until(ExpectedConditions.elementToBeClickable(rdFinal));
                    Utilities.ClickCheckBox(rdFinal);
                    Utilities.ClickCheckBox(Sendmail);
                }
                catch (Exception e){

                }

                WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"btnOk\"]")));

                try {
                    OKBtn.click();
                    System.out.println("OK Button clicked successfully!");
                } catch (Exception e) {
                    System.out.println("Normal click failed, trying JavaScript click.");
                    ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].click();", OKBtn);
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

//            DynamicWait.longWait();
//            Robot robot = new Robot();
//            robot.keyPress(KeyEvent.VK_ENTER);
//            DynamicWait.longWait();
//            robot.keyRelease(KeyEvent.VK_ENTER);
//            DynamicWait.longWait();
//
//            String expected = "Approved";





//            try {
//                BaseTest.getDriver().switchTo().defaultContent();
//                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
//
//                WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"btnOk\"]")));
//
//                try {
//                    OKBtn.click();
//                    System.out.println("OK Button clicked successfully!");
//                } catch (Exception e) {
//                    System.out.println("Normal click failed, trying JavaScript click.");
//                    ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].click();", OKBtn);
//                }
//
//            } catch (Exception e) {
//                System.out.println("Error: " + e.getMessage());
//            }


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PODATA", Status.FAIL, "Exception found in Method - AddProduct", true, e);
        }
    }


    public void PO0346() {

        try {

            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(),Duration.ofSeconds(10));
            // Switch to the main content and then to the required frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Wait for Inventory element and click it
            DynamicWait.longWait();
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Inventory);
            Utilities.Click(BaseTest.getDriver(), Inventory);

            //Inventory Module Select
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
            Utilities.Click(BaseTest.getDriver(), InventoryModule);

            //Click on Purchase Order
            Utilities.Click(BaseTest.getDriver(), PurchaseOrder);

            //Click on Add Button
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), AddButton);
            Utilities.Click(BaseTest.getDriver(), AddButton);

            //Select PO Type
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), SelectType);

            //Select PO Type form the popup
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.SendKeys(BaseTest.getDriver(), POType, "CP");
            Utilities.Click(BaseTest.getDriver(), FirstColumn);

            //Select To Date
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(BaseTest.getDriver(), ToDate, "12/09/2025");
            String Value = "CCL001";
            //Select Vendor
            Utilities.SendKeys(BaseTest.getDriver(), Vendor, "%%");
            //Utilities.Click(BaseTest.getDriver(), VendorImg);

            Actions actions = new Actions(BaseTest.getDriver());
            actions.sendKeys(Keys.TAB).perform();

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));


            Random random = new Random();
            System.out.println(VendorList);
            int randomIndex = random.nextInt(VendorList.size());
            // Click on the random department
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Filter);
            String vendorlist = VendorList.get(randomIndex).getText();
            Utilities.SendKeys(BaseTest.getDriver(), Filter, vendorlist);
            DynamicWait.longWait();

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.DoubleClick(Cancel);

            try {
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                Utilities.Click(BaseTest.getDriver(), Close);
            } catch (Exception e) {
                System.out.println("No Close Button");
            }


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(BaseTest.getDriver(), Search_vendor, vendorlist);

            //Click Enter Items Details
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);

            // Enter Rate





        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);

        }
    }
    public void SelectPO(){
        try {
            //Select PO Type
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            Utilities.SendKeys(BaseTest.getDriver(),EnterPOType,"CP");
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));






            //Select To Date
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(BaseTest.getDriver(), ToDate, "12/09/2025");
            String Value = "CCL001";
            //Select Vendor
            Utilities.SendKeys(BaseTest.getDriver(), Search_vendor, "CCL001");

            //Click Enter Items Details
            Utilities.Click(BaseTest.getDriver(), EnterItemDetails);

            // Enter Rate
            for (int i = 0; i < itemRates.size(); i++) {
                Actions actions = new Actions(BaseTest.getDriver());
                //WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeDlg")));
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getItemRate(i));
                getItemRate(i).click();

                String value = getItemRate(i).getAttribute("value").replace(",", ""); // Remove commas
                float ItemRate = Float.parseFloat(value);

                // float ItemRate = Float.parseFloat((getItemRate(i).getAttribute("value")));
                if (ItemRate > 0) {
                    // actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("100").perform();
                } else {
                    actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("125").perform();

                }
            }

            Utilities.Click(BaseTest.getDriver(),SaveBtn);

//            BaseTest.getDriver().switchTo().defaultContent();
//            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(),Duration.ofSeconds(10));
//            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
//            //BaseTest.getDriver().switchTo().frame("iframeGridDialog");
////            wait.until(ExpectedConditions.elementToBeClickable(OKBtn));
////
//
//          //  divMsg
//              Utilities.WaitTillElementDisplayed(BaseTest.getDriver(),ConfirmationDialog);
//              wait.until(ExpectedConditions.presenceOfElementLocated(By.id("divMsg")));
//          //  wait.until(ExpectedConditions.elementToBeClickable(OKBtn));
//
//           Utilities.WaitForPageLoad(BaseTest.getDriver());
//            DynamicWait.mediumWait();
//           // Utilities.Click(BaseTest.getDriver(),OKBtn);
//
//            try {
//
//                ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].click();", OKBtn);
//
//            } catch (Exception e){
//                ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].click();", OKBtn);
//
//            }


            try {
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

                WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));

                try {
                    OKBtn.click();
                    System.out.println("OK Button clicked successfully!");
                } catch (Exception e) {
                    System.out.println("Normal click failed, trying JavaScript click.");
                    ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].click();", OKBtn);
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }


            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeDlg"));
            Utilities.Click(BaseTest.getDriver(),Print);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            // Utilities.Click(BaseTest.getDriver(),EntirePO);
            Utilities.Click(BaseTest.getDriver(),OKBtn);









        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);
        }
    }


    public void AdditionalCharge(){
        try {


            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(),Duration.ofSeconds(15));

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

            WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));

            OKBtn.click();


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            Utilities.Click(SelectItem);


            Utilities.Click(Charge);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.SendKeys(EnterCharge,"DSAA");

            Utilities.Click(value);
            Actions actions = new Actions(BaseTest.getDriver());
            actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("100").perform();

            Utilities.Click(Save);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            try {
                ok.click();

                System.out.println("Inside the Try Block");
            } catch (Exception e) {
                OKBtn.click();
                System.out.println("Inside the Catch Block");
            }

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(Close);



        }catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AdditionalCharge", Status.FAIL, "Exception found in Method - AddProduct", true, e);
        }
    }

    public void AdditionalSRVCharge(){
        try {


            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(),Duration.ofSeconds(15));

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

            WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));

            OKBtn.click();


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            //Utilities.Click(SelectItem);


            Utilities.Click(POCharge);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.SendKeys(EnterCharge,"DSAA");

            Utilities.Click(value);
            Actions actions = new Actions(BaseTest.getDriver());
            actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).sendKeys("100").perform();

            Utilities.Click(Save);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            try {
                ok.click();

                System.out.println("Inside the Try Block");
            } catch (Exception e) {
                OKBtn.click();
                System.out.println("Inside the Catch Block");
            }

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(Close);



        }catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AdditionalCharge", Status.FAIL, "Exception found in Method - AddProduct", true, e);
        }
    }

    public void PrintandApprove(){

        WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
        try {

            try {
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

                WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));

                try {
                    OKBtn.click();
                    System.out.println("OK Button clicked successfully!");
                } catch (Exception e) {
//                    System.out.println("Normal click failed, trying JavaScript click.");
//                    ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].click();", OKBtn);
                }

            } catch (Exception e) {
                //System.out.println("Error: " + e.getMessage());
            }


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.selectBy(BaseTest.getDriver(), "visibletext", Approval, "Approve");

            Utilities.Click(SaveBtn);


            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

            WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));
            OKBtn.click();





            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeDlg"));
            Utilities.Click(BaseTest.getDriver(),Print);


            try {
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
                try
                {
                    wait.until(ExpectedConditions.elementToBeClickable(rdFinal));
                    Utilities.ClickCheckBox(rdFinal);
                    Utilities.ClickCheckBox(Sendmail);
                }
                catch (Exception e){

                }

                WebElement OKBtn1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"btnOk\"]")));

                try {
                    OKBtn1.click();
                    System.out.println("OK Button clicked successfully!");
                } catch (Exception e) {
                    System.out.println("Normal click failed, trying JavaScript click.");
                    ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].click();", OKBtn);
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PODATA", Status.FAIL, "Exception found in Method - AddProduct", true, e);
        }
    }


}




package pages.POPages.WithoutApproval.withRQ;

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

import java.time.Duration;
import java.util.Random;

public class ItemLevelCharges {


    public ItemLevelCharges(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "cphBody_txtDateTo")
    private WebElement ToDate;
    @FindBy(id = "cphBody_txtVendor")
    private WebElement Search_vendor;
    @FindBy(id = "cphBody_btnEnterItemDetails")
    private WebElement EnterItemDetails;
    @FindBy(id = "cphBody_txtType")
    private WebElement EnterPOType;


    public WebElement getItemRate(int itemId) {
        String xpath3 = "//input[@id='item_rate_9_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    @FindBy(xpath = "//input[starts-with(@id, 'item_rate_9_')]")
    private java.util.List<WebElement> itemRates;
    @FindBy(id = "cphDialogHeader_btnSaveClose")
    private WebElement SaveBtn;
    @FindBy(id = "cphBody_txtVendor")
    private WebElement Vendor;
    @FindBy(id = "cphBody_imgVendor")
    private WebElement VendorImg;
    @FindBy(xpath = "//td[@class=\"ControlItemStyle\"][1]")
    private java.util.List<WebElement> VendorList;
    @FindBy(xpath = "//input[@id='col_1']")
    private WebElement Filter;
    @FindBy(id = "btnCancel")
    private WebElement Cancel;
    @FindBy(id = "btnClose")
    private WebElement Close;


    public WebElement getItemSearchElement(int itemId) {
        String xpath = "//input[@id='item_id_1_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath));
    }

    public WebElement getrequestQuantityelement(int itemId) {
        String xpath3 = "//input[@id='qty_ordered_5_" + itemId + "']";
        return BaseTest.getDriver().findElement(By.xpath(xpath3));
    }

    @FindBy(id = "item_id_1_0")
    private WebElement SelectItem;
    @FindBy(id = "cphBody_lblCharges_1")
    private WebElement Charges;
    @FindBy(id = "cphBody_lblCharges")
    private WebElement POLevelCharges;
    @FindBy(id = "img_charge_desc_2_0")
    private WebElement SelectCharges;
    @FindBy(xpath = "//td[@id='td_0_0' and text()='OTAA']")
    private WebElement ClickCharge;
    @FindBy(id = "btnSave")
    private WebElement Save;
    @FindBy(xpath = "//*[@id='btnOK']")
    private WebElement OKBtn;
    @FindBy(xpath = "//*[@id='btnClose']")
    private WebElement CloseBtn;
    @FindBy(xpath = "//*[@id='cphBody_lblInstructions']")
    private WebElement Instructions;
    @FindBy(xpath = "//*[@id='txtCoordinator']")
    private WebElement Cordinator;
    @FindBy(xpath = "//*[@id='txtDespatchInstructions']")
    private WebElement DespatchInstructions;
    @FindBy(xpath = "//*[@id='txtPackingInstructions']")
    private WebElement PackingInstructions;
    @FindBy(xpath = "//*[@id='txtRemarks']")
    private WebElement Remark;
    @FindBy(id = "btnOk")
    private WebElement OK;
    @FindBy(id = "cphBody_lblComments")
    private WebElement Comment;
    @FindBy(id = "txtComments")
    private WebElement AddComment;
    @FindBy(id = "cphBody_lblNotes")
    private WebElement Notes;
    @FindBy(id = "txtNotes")
    private WebElement AddNotes;
    @FindBy(id = "cphBody_lblSchedules_1")
    private WebElement Schedule;




    public void SelectPO() {
        try {
            //Select PO Type
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            Utilities.SendKeys(BaseTest.getDriver(), EnterPOType, "CP");
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));


            //Select To Date
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.SendKeys(BaseTest.getDriver(), ToDate, "05/11/2025");
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
            for (int i = 0; i < itemRates.size(); i++) {
               // Actions actions = new Actions(BaseTest.getDriver());
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

            Utilities.Click(BaseTest.getDriver(), SaveBtn);


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
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);
        }
    }
    public void ItemCharges() {
        try {
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            Utilities.Click(BaseTest.getDriver(), SelectItem);
            Utilities.Click(BaseTest.getDriver(), Charges);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

            Utilities.Click(BaseTest.getDriver(), SelectCharges);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

            Utilities.DoubleClick(ClickCharge);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            Utilities.Click(BaseTest.getDriver(), Save);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            Utilities.Click(BaseTest.getDriver(), OKBtn);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            Utilities.Click(BaseTest.getDriver(), CloseBtn);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("ItemCharges", Status.FAIL, "Exception Found in Applying Item Level Charges", true, e);
        }
    }
    public void POCharges() {
        try {
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            Utilities.Click(BaseTest.getDriver(), SelectItem);
            Utilities.Click(BaseTest.getDriver(), POLevelCharges);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

            Utilities.Click(BaseTest.getDriver(), SelectCharges);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

            Utilities.DoubleClick(ClickCharge);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            Utilities.Click(BaseTest.getDriver(), Save);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            Utilities.Click(BaseTest.getDriver(), OKBtn);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            Utilities.Click(BaseTest.getDriver(), CloseBtn);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("ItemCharges", Status.FAIL, "Exception Found in Applying Item Level Charges", true, e);
        }
    }
    public void Instructions() {
        try {


            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            Utilities.Click(BaseTest.getDriver(), Instructions);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            Utilities.SendKeys(BaseTest.getDriver(), DespatchInstructions, "Handel With Care");
            Utilities.SendKeys(BaseTest.getDriver(), PackingInstructions, "Fragile Items");
            Utilities.SendKeys(BaseTest.getDriver(), Remark, "Test:");
            Utilities.Click(BaseTest.getDriver(), OK);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));


            DynamicWait.mediumWait();
            BaseTest.getDriver().findElement(By.id("btnOK")).click();

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(BaseTest.getDriver(),Close);




        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("ItemCharges", Status.FAIL, "Exception Found in Applying Item Level Charges", true, e);
        }
    }
    public void Comment(){

        try {
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            Utilities.Click(BaseTest.getDriver(),Comment);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.SendKeys(BaseTest.getDriver(),AddComment,"Testing");

            Utilities.Click(BaseTest.getDriver(),OK);


            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));


            DynamicWait.mediumWait();
            BaseTest.getDriver().findElement(By.id("btnOK")).click();

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(BaseTest.getDriver(),Close);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddComment",Status.FAIL,"Exception in Adding Comment",true,e);
        }
    }
    public void Notes(){

        try {
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            Utilities.Click(BaseTest.getDriver(),Notes);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.SendKeys(BaseTest.getDriver(),AddNotes,"Testing");

            Utilities.Click(BaseTest.getDriver(),OK);


            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));


            DynamicWait.mediumWait();
            BaseTest.getDriver().findElement(By.id("btnOK")).click();

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(BaseTest.getDriver(),Close);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddComment",Status.FAIL,"Exception in Adding Comment",true,e);
        }
    }
    public void Schedule(){
        try {

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            Utilities.Click(BaseTest.getDriver(),Schedule);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Schedule",Status.FAIL,"Error in Schedule",true,e);
        }
    }


}

package pages.PO;

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


import java.time.Duration;
import java.util.HashSet;
import java.util.Random;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RQCreate {
    /**
     * Constructor
     */
    public RQCreate(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


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
    @FindBy(xpath = "//input[starts-with(@id,'item_id_1_')]")
    private List<WebElement> Items;
    @FindBy(xpath = "//input[starts-with(@id,'org_req_qty_6_')]")
    private List<WebElement> Quantity;
    @FindBy(xpath = "//input[starts-with(@id,'item_desc_3_')]")
    private List<WebElement> ItemName;
    @FindBy(id = "cphBody_imgIndentDept")
    private WebElement IndentDept;
    @FindBy(id = "cphBody_imgFromDeptStore")
    private WebElement FromDept;
    @FindBy(id = "cphBody_txtunit_id")
    private WebElement Property;
    @FindBy(id = "cphBody_ddlApprovalStatus")
    private WebElement Approval;
    @FindBy(id = "cphDialogHeader_btnSave")
    private WebElement SaveBtn;
    @FindBy(id = "cphHeader_lnkHome")
    private WebElement HomePage;



    // List<WebElement> elements = BaseTest.getDriver().findElements(By.cssSelector("[id^='ischeck_13_']"));


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

    public void AddProduct() {
        try {
            //Getting All resoureces from Testdata excel sheet
            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "TestData.xlsx";

            String Updload_Path1 = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "POData.xlsx";
            String ToDepartment1 = "", ToDepartment2 = "";


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
            String RequestionType = Description.get(6);
            Utilities.SendKeys(BaseTest.getDriver(), Filter, RequestionType);
            DynamicWait.smallWait();
            Utilities.Click(BaseTest.getDriver(), FirstColumn);


            //check whether indent department is disabled or not
            if (Description.get(3).contains("Issue Requisition")) {
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

                WebElement inputField = BaseTest.getDriver().findElement(By.id("cphBody_txtIndentDept"));

                // Verify the input field is disabled
                boolean isDisabled = inputField.getAttribute("readonly") != null && inputField.getAttribute("tabindex").equals("-1") && inputField.getAttribute("class").contains("readonlytextbox");

                if (isDisabled) {
                    System.out.println("The Indent Department is disabled.");
                    ExtentTestManager.createAssertTestStepWithScreenshot("Indent Dept", Status.PASS, "Indent Department is Disabled", true);
                } else {
                    System.out.println("The Indent Department is enabled.");
                    ExtentTestManager.createAssertTestStepWithScreenshot("Indent Dept", Status.PASS, "Indent Department is Enabled", true);
                }
            }


            try {
                // Wait for the element to be clickable (visible and enabled)
                WebDriverWait wait1 = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(2));
                WebElement clickableElement = wait1.until(ExpectedConditions.elementToBeClickable(FromDepartment));
                ExtentTestManager.createAssertTestStepWithScreenshot("From Department", Status.PASS, "From Department is Enabled ", true);

                System.out.println("From Department is clickable");
            } catch (TimeoutException e) {
                System.out.println("From Department is NOT clickable");
                ExtentTestManager.createAssertTestStepWithScreenshot("From Department", Status.WARNING, "From Department is Disabled", true);
            }

            try {
                // Wait for the element to be clickable (visible and enabled)
                WebDriverWait wait1 = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(2));
                WebElement clickableElement = wait1.until(ExpectedConditions.elementToBeClickable(ToDepartment));
                ExtentTestManager.createAssertTestStepWithScreenshot("To Department", Status.PASS, "To Department is Enabled ", true);

                System.out.println("To Department is clickable");
            } catch (TimeoutException e) {
                System.out.println("To Department is NOT clickable");
                ExtentTestManager.createAssertTestStepWithScreenshot("To Department", Status.WARNING, "To Department is Disabled", true);
            }


            //Check whether property is displayed or not


            if (Property.isDisplayed() == false) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Property", Status.PASS, "Property not displayed", true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Property", Status.WARNING, " Department is Enabled", true);

            }


            //Select From Department/Store
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.Click(BaseTest.getDriver(), FromDepartment);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            // Generate a random index within the bounds of the DepartmentList
            Random random = new Random();
            System.out.println(DepartmentList);
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
            } catch (Exception e) {

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

            // ToDepartment1 = DepartmentList.get(randomIndex2).getText();

            //Item Add
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), EnterItemDetail);
            Utilities.Click(BaseTest.getDriver(), EnterItemDetail);


            Set<String> usedTexts = new HashSet<>();
            boolean isPage2Switched = false; // Track if switched to Page 2
            int itemsFromPage1 = 2; // Define the number of items to pick from Page 1

            for (int i = 0; i < 7; i++) {
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
                if (i >= itemsFromPage1) {
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
                    int randomInde = random.nextInt(Item.size() - 2); // Adjust as per available items
                    text = Item.get(randomInde).getText(); // Get a new text if duplicate
                }
                usedTexts.add(text); // Add the unique text to the set
                Utilities.SendKeys(BaseTest.getDriver(), ItemFilter, text); // Use the unique text
                DynamicWait.smallWait();
                Utilities.Click(BaseTest.getDriver(), FirstColumn);

                // Switch back to the main frame and enter quantity
                double j = (i + 1) * 10;
                String a = String.valueOf(j);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), getrequestQuantityelement(i));
                getrequestQuantityelement(i).click();
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(a).perform();
            }


            Utilities.Click(BaseTest.getDriver(), SaveButton);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            if (ProductConfirmation.isDisplayed() && ProductConfirmation.getText().contains("Requisition Number")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Requisition Number Confirmation ", Status.PASS, "Requisition Number successfully generated", true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Requisition Number Confirmation", Status.FAIL, "Issue in Requisition Number", true);
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


            ExcelHandler.loadExcelFile(Updload_Path1, 0);

            // Find the first empty row in the Excel sheet
            int rowCount = ExcelHandler.getRowCount();

            // Set the "Requisition Number" in the next available row
            ExcelHandler.setCellData("RQ Number", String.valueOf(RequisitionNumber), rowCount);

            ExcelHandler.saveExcelFileCommon(Updload_Path1);


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), OkButton);
            Utilities.Click(BaseTest.getDriver(), OkButton);

//            // Select Status of Requisition As Approved
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
//
//            Utilities.selectBy(BaseTest.getDriver(), "value", Approval, "AP");
//
//            Utilities.Click(BaseTest.getDriver(), SaveBtn);
//
//            //
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//
//            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), OkButton);
//            Utilities.Click(BaseTest.getDriver(), OkButton);

            // Go to Home Page
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            // Click on Home Page
            Utilities.Click(BaseTest.getDriver(), HomePage);

            BaseTest.getDriver().switchTo().defaultContent();


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AddProduct", Status.FAIL, "Exception found in Method - AddProduct", true, e);
        }
    }

}
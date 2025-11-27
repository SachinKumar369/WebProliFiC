package pages.AI_Pages;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utilities;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import baselibrary.BaseTest;

public class Requisition {

    private WebDriver driver;

    // 🔹 Locators
    @FindBy(id = "txtChainID") private WebElement chainID;
    @FindBy(id = "txtUnitID") private WebElement unitID;
    @FindBy(id = "txtLogIN") private WebElement username;
    @FindBy(id = "lblLogin_span") private WebElement loginButton;

    @FindBy(id = "cphHeader_ddlPropertyList") private WebElement propertyListDropdown;

    @FindBy(id = "LinkProduct2") private WebElement productLink;
    @FindBy(id = "divmodule_2") private WebElement moduleDiv;
    @FindBy(linkText = "Requisitions") private WebElement requisitionsLink;

    @FindBy(id = "cphBrowserHeader_btnAddNew") private WebElement addNewButton;

    @FindBy(id = "cphBody_imgRequisitionType") private WebElement requisitionType;
    @FindBy(id = "cphBody_imgFromDeptStore") private WebElement fromDeptStore;
    @FindBy(id = "cphBody_imgToDeptStore") private WebElement toDeptStore;

    @FindBy(id = "cphBody_btnEnterItemDetails") private WebElement enterItemDetails;
    @FindBy(id = "img_item_group_id_0_0") private WebElement itemGroup;

    @FindBy(id = "item_id_1_0") private WebElement firstItemId;
    @FindBy(id = "qty_req_7_0") private WebElement firstQty;

    @FindBy(id = "TableData") private WebElement tableData;
    @FindBy(id = "cphBody_btnAddRow_Top") private WebElement addRowTop;
    @FindBy(id = "item_id_1_1") private WebElement secondItemId;
    @FindBy(id = "qty_req_7_1") private WebElement secondQty;

    @FindBy(id = "cphDialogHeader_btnSave") private WebElement saveButton;

    // 🔹 Constructor
    public Requisition(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // 🔹 Login Action
    public void login(String chain, String unit, String user) {
        ExtentTestManager.getTest().log(Status.INFO, "Entering Chain ID");
        Utilities.SendKeys(chainID, chain);

        ExtentTestManager.getTest().log(Status.INFO, "Entering Unit ID");
        Utilities.SendKeys(unitID, unit);

        ExtentTestManager.getTest().log(Status.INFO, "Entering Username");
        Utilities.SendKeys(username, user);

        ExtentTestManager.getTest().log(Status.INFO, "Clicking Login");
        Utilities.Click(loginButton);
    }

    // 🔹 Select Property and handle alert
    public void selectProperty(String property) {
        ExtentTestManager.getTest().log(Status.INFO, "Selecting property: " + property);
        Utilities.selectBy("visibletext", propertyListDropdown, property);
        BaseTest.getDriver().switchTo().alert().accept();
    }

    // 🔹 Navigate to Requisitions section
    public void navigateToRequisitions() {
        ExtentTestManager.getTest().log(Status.INFO, "Navigating to Product Link");
        Utilities.Click(productLink);

        ExtentTestManager.getTest().log(Status.INFO, "Clicking Module");
        Utilities.Click(moduleDiv);

        ExtentTestManager.getTest().log(Status.INFO, "Clicking Requisitions");
        Utilities.Click(requisitionsLink);
    }

    // 🔹 Create Requisition Workflow
    public void createRequisition() {
        ExtentTestManager.getTest().log(Status.INFO, "Clicking Add New Requisition");
        Utilities.Click(addNewButton);

        // 🔄 Switch into main frame for requisition details
        BaseTest.getDriver().switchTo().frame("frameHeader");   // index=0 replacement

        ExtentTestManager.getTest().log(Status.INFO, "Selecting Requisition Type");
        Utilities.Click(requisitionType);

        ExtentTestManager.getTest().log(Status.INFO, "Selecting From Dept Store");
        Utilities.Click(fromDeptStore);

        ExtentTestManager.getTest().log(Status.INFO, "Selecting To Dept Store");
        Utilities.Click(toDeptStore);

        ExtentTestManager.getTest().log(Status.INFO, "Clicking Enter Item Details");
        Utilities.Click(enterItemDetails);

        BaseTest.getDriver().switchTo().defaultContent();

        // 🔄 Enter Item details (index=12 → popup frame)
        BaseTest.getDriver().switchTo().frame("framePopup");
        ExtentTestManager.getTest().log(Status.INFO, "Selecting Item Group");
        Utilities.Click(itemGroup);

        ExtentTestManager.getTest().log(Status.INFO, "Entering First Qty");
        Utilities.SendKeys(firstQty, "10");

        ExtentTestManager.getTest().log(Status.INFO, "Clicking Add Row");
        Utilities.Click(addRowTop);

        ExtentTestManager.getTest().log(Status.INFO, "Entering Second Qty");
        Utilities.SendKeys(secondQty, "10");

        ExtentTestManager.getTest().log(Status.INFO, "Saving Requisition");
        Utilities.Click(saveButton);

        BaseTest.getDriver().switchTo().defaultContent();
    }
}


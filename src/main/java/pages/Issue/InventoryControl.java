package pages.Issue;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import datahandlers.ExcelHandler;
import extentreports.ExtentTestManager;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utilities;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class InventoryControl {

    public InventoryControl(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//select[@id=\"cphHeader_ddlPropertyList\"]")
    private WebElement SwitchTo;
    @FindBy(xpath = "//a[text()='Inventory']")
    private WebElement Inventory;
    @FindBy(xpath = "//a[@id='divmodule_2']")
    private WebElement InventoryModule;
    @FindBy(xpath = "//a[text()='Inventory Control']")
    private WebElement InventoryControl;
    @FindBy(id = "cphBody_chkpo_wo_rq")
    private WebElement CheckBox;
    @FindBy(id = "cphBody_chkreceive_wo_po")
    private WebElement POCheckBox;
    @FindBy(xpath = "//input[@value='Save']")
    private WebElement SaveBtn;
    @FindBy(xpath = "//input[@id='btnOK']")
    private WebElement OKBtn;
    @FindBy(id = "cphHeader_lnkHome")
    private WebElement HomePage;

    public void Switch0529() {
        try {
            //Getting All resoureces from Testdata excel sheet
            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "TestData.xlsx";
            ExcelHandler.loadExcelFile(Updload_Path, 1);
            List<String> Resources = ExcelHandler.getAllColumnData("Resources");
            String resource = Resources.get(4);

            //C0345 - Citrus Classic Bhopal(Madhya Pradesh) Resource select
            try {
                // Wait briefly for the alert and accept it if it appears
                WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(5));
                wait.until(ExpectedConditions.alertIsPresent()).accept(); // Accept the alert if present
                wait.until(ExpectedConditions.alertIsPresent()).dismiss(); // Dismiss the data unsave alert if present
            } catch (TimeoutException e) {
                // If no alert is present within the wait time, continue without doing anything
            }
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
                wait.until(ExpectedConditions.alertIsPresent()).dismiss(); // Dismiss the data unsave alert if present
            } catch (TimeoutException e) {
                // If no alert is present within the wait time, continue without doing anything
            }
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Switch Property", Status.FAIL, "Exception found in Method - Switch Property", true, e);
        }

    }

    public void SwitchProperty() {
        try {
            //Getting All resoureces from Testdata excel sheet
            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "TestData.xlsx";
            ExcelHandler.loadExcelFile(Updload_Path, 1);
            List<String> Resources = ExcelHandler.getAllColumnData("Resources");
            String resource = Resources.get(5);

            //C0345 - Citrus Classic Bhopal(Madhya Pradesh) Resource select
            try {
                // Wait briefly for the alert and accept it if it appears
                WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(5));
                wait.until(ExpectedConditions.alertIsPresent()).accept(); // Accept the alert if present
                wait.until(ExpectedConditions.alertIsPresent()).dismiss(); // Dismiss the data unsave alert if present
            } catch (TimeoutException e) {
                // If no alert is present within the wait time, continue without doing anything
            }
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
                wait.until(ExpectedConditions.alertIsPresent()).dismiss(); // Dismiss the data unsave alert if present
            } catch (TimeoutException e) {
                // If no alert is present within the wait time, continue without doing anything
            }
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Switch Property", Status.FAIL, "Exception found in Method - Switch Property", true, e);
        }

    }

    public void Switch0346() {
        try {
            //Getting All resoureces from Testdata excel sheet
            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "TestData.xlsx";
            ExcelHandler.loadExcelFile(Updload_Path, 1);
            List<String> Resources = ExcelHandler.getAllColumnData("Resources");
            String resource = Resources.get(6);

            //C0345 - Citrus Classic Bhopal(Madhya Pradesh) Resource select
            try {
                // Wait briefly for the alert and accept it if it appears
                WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(5));
                wait.until(ExpectedConditions.alertIsPresent()).accept(); // Accept the alert if present
                wait.until(ExpectedConditions.alertIsPresent()).dismiss(); // Dismiss the data unsave alert if present
            } catch (TimeoutException e) {
                // If no alert is present within the wait time, continue without doing anything
            }
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
                wait.until(ExpectedConditions.alertIsPresent()).dismiss(); // Dismiss the data unsave alert if present
            } catch (TimeoutException e) {
                // If no alert is present within the wait time, continue without doing anything
            }
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Switch Property", Status.FAIL, "Exception found in Method - Switch Property", true, e);
        }

    }

    public void Switch0419() {
        try {
            //Getting All resoureces from Testdata excel sheet
            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "TestData.xlsx";
            ExcelHandler.loadExcelFile(Updload_Path, 1);
            List<String> Resources = ExcelHandler.getAllColumnData("Resources");
            String resource = Resources.get(2);

            //C0345 - Citrus Classic Bhopal(Madhya Pradesh) Resource select
            try {
                // Wait briefly for the alert and accept it if it appears
                WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(5));
                wait.until(ExpectedConditions.alertIsPresent()).accept(); // Accept the alert if present
                wait.until(ExpectedConditions.alertIsPresent()).dismiss(); // Dismiss the data unsave alert if present
            } catch (TimeoutException e) {
                // If no alert is present within the wait time, continue without doing anything
            }
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
                wait.until(ExpectedConditions.alertIsPresent()).dismiss(); // Dismiss the data unsave alert if present
            } catch (TimeoutException e) {
                // If no alert is present within the wait time, continue without doing anything
            }
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Switch Property", Status.FAIL, "Exception found in Method - Switch Property", true, e);
        }

    }

    public void Switch0381() {
        try {
            //Getting All resoureces from Testdata excel sheet
            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "TestData.xlsx";
            ExcelHandler.loadExcelFile(Updload_Path, 1);
            List<String> Resources = ExcelHandler.getAllColumnData("Resources");
            String resource = Resources.get(11);

            //C0345 - Citrus Classic Bhopal(Madhya Pradesh) Resource select
            try {
                // Wait briefly for the alert and accept it if it appears
                WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(5));
                wait.until(ExpectedConditions.alertIsPresent()).accept(); // Accept the alert if present
                wait.until(ExpectedConditions.alertIsPresent()).dismiss(); // Dismiss the data unsave alert if present
            } catch (TimeoutException e) {
                // If no alert is present within the wait time, continue without doing anything
            }
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
                wait.until(ExpectedConditions.alertIsPresent()).dismiss(); // Dismiss the data unsave alert if present
            } catch (TimeoutException e) {
                // If no alert is present within the wait time, continue without doing anything
            }
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Switch Property", Status.FAIL, "Exception found in Method - Switch Property", true, e);
        }

    }

    public void InventoryControl() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Inventory);
            Utilities.Click(BaseTest.getDriver(), Inventory);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
            Utilities.Click(BaseTest.getDriver(), InventoryModule);

            Utilities.Click(BaseTest.getDriver(), InventoryControl);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            boolean isSelected = CheckBox.isSelected();
            if (isSelected) {
                Utilities.Click(BaseTest.getDriver(), CheckBox);
                System.out.println("CheckBox was Selected");
            } else {
                System.out.println("Check box is not selected");
            }
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(BaseTest.getDriver(), SaveBtn);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(BaseTest.getDriver(), OKBtn);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(BaseTest.getDriver(), HomePage);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Inventroy control", Status.FAIL, "Exception found in Inventory Control", true, e);
        }


    }

    public void InventoryControlSRV() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Inventory);
            Utilities.Click(BaseTest.getDriver(), Inventory);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
            Utilities.Click(BaseTest.getDriver(), InventoryModule);

            Utilities.Click(BaseTest.getDriver(), InventoryControl);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            boolean isSelected = POCheckBox.isSelected();
            if (!isSelected) {
                Utilities.Click(BaseTest.getDriver(), POCheckBox);
                System.out.println("CheckBox was Selected");
            } else {
                System.out.println("Check box is not selected");
            }
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(BaseTest.getDriver(), SaveBtn);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(BaseTest.getDriver(), OKBtn);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(BaseTest.getDriver(), HomePage);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Inventroy control", Status.FAIL, "Exception found in Inventory Control", true, e);
        }


    }

    public void InventoryControlPOSRV() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Inventory);
            Utilities.Click(BaseTest.getDriver(), Inventory);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
            Utilities.Click(BaseTest.getDriver(), InventoryModule);

            Utilities.Click(BaseTest.getDriver(), InventoryControl);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            boolean isSelected = POCheckBox.isSelected();
            if (isSelected) {
                Utilities.Click(BaseTest.getDriver(), POCheckBox);
                System.out.println("CheckBox was Selected");
            } else {
                System.out.println("Check box is not selected");
            }
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(BaseTest.getDriver(), SaveBtn);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.Click(BaseTest.getDriver(), OKBtn);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(BaseTest.getDriver(), HomePage);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Inventroy control", Status.FAIL, "Exception found in Inventory Control", true, e);
        }


    }


    public void Switch0570() {
        try {
            //Getting All resoureces from Testdata excel sheet
            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "TestData.xlsx";
            ExcelHandler.loadExcelFile(Updload_Path, 1);
            List<String> Resources = ExcelHandler.getAllColumnData("Resources");
            String resource = Resources.get(12);

            //C0345 - Citrus Classic Bhopal(Madhya Pradesh) Resource select
            try {
                // Wait briefly for the alert and accept it if it appears
                WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(5));
                wait.until(ExpectedConditions.alertIsPresent()).accept(); // Accept the alert if present
                wait.until(ExpectedConditions.alertIsPresent()).dismiss(); // Dismiss the data unsave alert if present
            } catch (TimeoutException e) {
                // If no alert is present within the wait time, continue without doing anything
            }
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
                wait.until(ExpectedConditions.alertIsPresent()).dismiss(); // Dismiss the data unsave alert if present
            } catch (TimeoutException e) {
                // If no alert is present within the wait time, continue without doing anything
            }
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Switch Property", Status.FAIL, "Exception found in Method - Switch Property", true, e);
        }

    }

    public void SwitchAKHL() {
        try {
            //Getting All resoureces from Testdata excel sheet
            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "TestData.xlsx";
            ExcelHandler.loadExcelFile(Updload_Path, 1);
            List<String> Resources = ExcelHandler.getAllColumnData("Resources");
            String resource = Resources.get(13);

            //C0345 - Citrus Classic Bhopal(Madhya Pradesh) Resource select
            try {
                // Wait briefly for the alert and accept it if it appears
                WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(5));
                wait.until(ExpectedConditions.alertIsPresent()).accept(); // Accept the alert if present
                wait.until(ExpectedConditions.alertIsPresent()).dismiss(); // Dismiss the data unsave alert if present
            } catch (TimeoutException e) {
                // If no alert is present within the wait time, continue without doing anything
            }
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
                wait.until(ExpectedConditions.alertIsPresent()).dismiss(); // Dismiss the data unsave alert if present
            } catch (TimeoutException e) {
                // If no alert is present within the wait time, continue without doing anything
            }
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Switch Property", Status.FAIL, "Exception found in Method - Switch Property", true, e);
        }

    }


    public void Switch0470() {
        try {
            //Getting All resoureces from Testdata excel sheet
            String Updload_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "TestData.xlsx";
            ExcelHandler.loadExcelFile(Updload_Path, 1);
            List<String> Resources = ExcelHandler.getAllColumnData("Resources");
            String resource = Resources.get(14);

            //C0345 - Citrus Classic Bhopal(Madhya Pradesh) Resource select
            try {
                // Wait briefly for the alert and accept it if it appears
                WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(5));
                wait.until(ExpectedConditions.alertIsPresent()).accept(); // Accept the alert if present
                wait.until(ExpectedConditions.alertIsPresent()).dismiss(); // Dismiss the data unsave alert if present
            } catch (TimeoutException e) {
                // If no alert is present within the wait time, continue without doing anything
            }
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
                wait.until(ExpectedConditions.alertIsPresent()).dismiss(); // Dismiss the data unsave alert if present
            } catch (TimeoutException e) {
                // If no alert is present within the wait time, continue without doing anything
            }
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Switch Property", Status.FAIL, "Exception found in Method - Switch Property", true, e);
        }

    }

}

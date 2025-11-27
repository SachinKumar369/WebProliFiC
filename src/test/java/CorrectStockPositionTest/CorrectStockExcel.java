package CorrectStockPositionTest;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CorrectStockPosition.StockPositionPage;
import pages.CorrectStockPosition.XMLModifier;
import pages.POPages.WithoutApproval.withRQ.RQCreate;
import pages.POPages.WithoutApproval.withRQ.CPPage;
import pages.POPages.WithoutApproval.withRQ.InventoryControl;
import pages.POPages.WithoutApproval.withRQ.Login;
import pages.POPages.WithoutApproval.withoutRQ.PO_Page;

import java.lang.reflect.Method;


public class CorrectStockExcel extends BaseTest {

    private final ThreadLocal<Login> LoginThread = new ThreadLocal<>();
    private final ThreadLocal<InventoryControl> InventoryControlThread = new ThreadLocal<>();
    private final ThreadLocal<StockPositionPage> CorrectStockThread  = new ThreadLocal<>();
    private final ThreadLocal<XMLModifier> XMLThread = new ThreadLocal<>();



    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        LoginThread.set(new Login(getDriver()));
        InventoryControlThread.set(new InventoryControl(getDriver()));
        CorrectStockThread.set(new StockPositionPage(getDriver()));
        XMLThread.set(new XMLModifier());
    }

    /**
     * @Test_Method_Description : For PO Creation
     * @Modified_By : Sachin Kumar
     * @Modified_Date : 10/12/2024
     */
    @Test(priority = 1)
    public void Login(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " Portal Login");
        try {
            LoginThread.get().login0419(m);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(priority = 2)
    public void SwitchProperty(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " PO Creation");
        try {
            InventoryControlThread.get().Switch0419();
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.PASS, "PO Creation Pass", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(priority = 3)
    public void CPTest(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " PO Creation");

        try {

            CorrectStockThread.get().CorrectStockPositionExcel();
            //CorrectStockThread.get().modifyXMLQtyInStockUnit();
            XMLThread.get().modifyLatestXMLQtyInStockUnit();
            CorrectStockThread.get().UploadXML();

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.FAIL, "Exception found in Login", true, e);
        }
    }
}

package SRVTest.InterStateVendor;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import listeners.RetryAnalyzer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.POPages.WithoutApproval.withRQ.InventoryControl;
import pages.POPages.WithoutApproval.withRQ.Login;
import pages.POPages.WithoutApproval.withoutRQ.PO_Page;
import pages.POPages.WithoutRQ.CommonAction;
import pages.SRV.SRVPage;

import java.lang.reflect.Method;

/**
 * Author: SACHIN KUMAR (replace sachin with your name or a fixed string)
 * Date: 23-01-2025
 */
public class InterStateVendor extends BaseTest {

    private final ThreadLocal<Login> LoginThread = new ThreadLocal<>();
    private final ThreadLocal<InventoryControl> InventoryControlThread = new ThreadLocal<>();
    private final ThreadLocal<PO_Page> POThread = new ThreadLocal<>();
    private final ThreadLocal<CommonAction> CommonActionThread = new ThreadLocal<>();
    private final ThreadLocal<SRVPage> SRVThread = new ThreadLocal<>();

    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        LoginThread.set(new Login(getDriver()));
        InventoryControlThread.set(new InventoryControl(getDriver()));
        POThread.set(new PO_Page(getDriver()));
        CommonActionThread.set(new CommonAction(getDriver()));
        SRVThread.set(new SRVPage(getDriver()));
    }

    /**
     * @Test_Method_Description : For PO Creation
     * @Modified_By : Sachin Kumar
     * @Modified_Date : 10/12/2024
     */
    @Test(priority = 1, retryAnalyzer = RetryAnalyzer.class)
    public void Login(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " Portal Login");
        try {
            LoginThread.get().loginAndLaunchTest(m);
            InventoryControlThread.get().Switch0570();
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(priority = 2)
    public void POCreate(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " PO Creation");
        try {
            POThread.get().RPPOInterState();
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.FAIL, "Exception found in Login", true, e);
        }
    }

//    @Test(priority = 3)
//    public void EnterItems() {
//        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: ", " Enter Items");
//        try {
//            CommonActionThread.get().EnterItemsInterState();
//            CommonActionThread.get().SaveDetailsinExcel();
//
//        } catch (Exception e) {
//            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Items", Status.FAIL, "Exception found in Login", true, e);
//        }
//    }
//
//    @Test(priority = 4)
//    public void SavePONumber() {
//        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: ", " Save PO Number");
//        try {
//            CommonActionThread.get().SaveRQNumber();
//            SRVThread.get().SelectIGST();
//
//        } catch (Exception e) {
//            ExtentTestManager.createAssertTestStepWithScreenshot("SaveRQNumber", Status.FAIL, "Exception found in SaveRQNumber", true, e);
//        }
//    }
//
//    @Test(priority = 5)
//    public void Tax(Method m) {
//        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " PO Creation");
//
//        try {
//
//
//
//
//
//
//            SRVThread.get().SRVIGST();
//          //SRVThread.get().Edit();
////            SRVThread.get().AddRow();
//
//
//            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.PASS, "PO Creation Pass", true);
//
//        } catch (Exception e) {
//            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.FAIL, "Exception found in Login", true, e);
//        }
//    }


}

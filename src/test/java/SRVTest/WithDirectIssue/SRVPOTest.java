package SRVTest.WithDirectIssue;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import loginTest.Login_Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CommonPages.LaunchSite;
import pages.CommonPages.PortalLoginPage;

import pages.POPages.WithRQ.CPPage;
import pages.POPages.WithRQ.RQApprove;
import pages.POPages.WithRQ.RQCreate;
import pages.POPages.WithoutApproval.withRQ.InventoryControl;
import pages.POPages.WithoutApproval.withRQ.Login;
import pages.POPages.WithoutRQ.CommonAction;
import pages.SRV.SRVPage;
import pages.T0419_Pages.SwitchProperty;

import java.lang.reflect.Method;

/**
 * Author: SACHIN KUMAR (replace sachin with your name or a fixed string)
 * Date: 23-01-2025
 */
public class SRVPOTest extends BaseTest {



    private final ThreadLocal<Login> LoginThread = new ThreadLocal<>();
    private final ThreadLocal<InventoryControl> InventoryControlThread = new ThreadLocal<>();

    private final ThreadLocal<SRVPage> SRVThread = new ThreadLocal<>();

    private final ThreadLocal<LaunchSite> launchSite = new ThreadLocal<>();

    private final ThreadLocal<PortalLoginPage> loginPage = new ThreadLocal<>();
    private final ThreadLocal<SwitchProperty> switchproperty = new ThreadLocal<>();
    private final ThreadLocal<Login_Test> login = new ThreadLocal<>();
    private final ThreadLocal<CPPage> CPThread = new ThreadLocal<>();
    private final ThreadLocal<CommonAction> CommonActionThread = new ThreadLocal<>();
    private final ThreadLocal<RQCreate> RQCreateThread = new ThreadLocal<>();
    private final ThreadLocal<RQApprove> RQApproveThread = new ThreadLocal<>();


    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        LoginThread.set(new Login(getDriver()));
        InventoryControlThread.set(new InventoryControl(getDriver()));
        launchSite.set(new LaunchSite(getDriver()));
        loginPage.set(new PortalLoginPage(getDriver()));
        login.set(new Login_Test(getDriver()));
        switchproperty.set(new SwitchProperty(getDriver()));
        CommonActionThread.set(new CommonAction(getDriver()));
        CPThread.set(new CPPage(getDriver()));
        SRVThread.set(new SRVPage(getDriver()));

        RQCreateThread.set(new RQCreate(getDriver()));
        RQApproveThread.set(new RQApprove(getDriver()));
        // logintest.set(new Login_Page(getDriver()));
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
            LoginThread.get().loginSRVDirectIssue(m);
            InventoryControlThread.get().Switch0381();
            RQCreateThread.get().AddProduct();


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login",
                    true, e);
        }
    }

    @Test(priority = 2)
    public void POCreate(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " PO Creation" );

        try {
            CPThread.get().CPPOCreation();
            CPThread.get().SelectPOSRV();
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.PASS, "PO Creation Pass", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.FAIL, "Exception found in Login",
                    true, e);
        }
    }

//    @Test(priority = 3)
//    public void EnterItems(){
//        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " , " Enter Items" );
//        try {
//
    ////            CommonActionThread.get().EnterItems();
//          //  CommonActionThread.get().SaveDetailsinExcel();
//           //  poWithoutReqThreadLocal.get().ExcelSaveTest();
//            ExtentTestManager.createAssertTestStepWithScreenshot("E", Status.PASS, "Enter Items", true);
//
//        } catch (Exception e) {
//            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Items", Status.FAIL, "Exception found in Login",
//                    true, e);
//        }
//    }

    @Test(priority = 4)
    public void SavePONumber(){
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " , " Save PO Number");

        try {

            SRVThread.get().POSRVDirectIssue();
            SRVThread.get().EnterQty();
            ExtentTestManager.createAssertTestStepWithScreenshot("SaveRQNumber", Status.PASS, "PO Number Saved ", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("SaveRQNumber", Status.FAIL, "Exception found in SaveRQNumber",
                    true, e);
        }
    }

//    @AfterMethod(alwaysRun = true)
//    public void closeDriver() {
//        launchSite.remove();
//
//        closeBrowser();
//    }
}

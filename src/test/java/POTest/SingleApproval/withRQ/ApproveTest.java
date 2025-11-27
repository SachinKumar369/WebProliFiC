package POTest.SingleApproval.withRQ;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import loginTest.Login_Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CommonPages.LaunchSite;
import pages.CommonPages.PortalLoginPage;
import pages.POPages.SingleApproval.withRQ.SingleApproval;
import pages.POPages.WithRQ.RQCreate;
import pages.POPages.WithoutApproval.withRQ.Login;
import pages.POPages.WithoutApproval.withoutRQ.PO_Page;
import pages.POPages.WithRQ.CPPage;
import pages.POPages.WithoutRQ.CommonAction;
import pages.POPages.WithoutRQ.RPPOApprovalPage;
import pages.T0419_Pages.SwitchProperty;

import java.lang.reflect.Method;

/**
 * Author: SACHIN KUMAR (replace sachin with your name or a fixed string)
 * Date: 23-01-2025
 */
public class ApproveTest extends BaseTest {

    private final ThreadLocal<Login> LoginThread = new ThreadLocal<>();
    private final ThreadLocal<LaunchSite> launchSite = new ThreadLocal<>();

    private final ThreadLocal<PortalLoginPage> loginPage = new ThreadLocal<>();
    private final ThreadLocal<SwitchProperty> switchproperty = new ThreadLocal<>();
    //private final ThreadLocal<Login_Test> login = new ThreadLocal<>();
    private final ThreadLocal<CPPage> CPPageThread = new ThreadLocal<>();
    //private final ThreadLocal<CPPage> CPPageThread = new ThreadLocal<>();

    private final ThreadLocal<CommonAction> CommonActionThread = new ThreadLocal<>();
    private final ThreadLocal<RPPOApprovalPage> RPPOApprovalPage = new ThreadLocal<>();
    private final ThreadLocal<RQCreate> RQCreateThread = new ThreadLocal<>();
    private final ThreadLocal<PO_Page> POThread = new ThreadLocal<>();
    private final ThreadLocal<SingleApproval> SingleAPThread = new ThreadLocal<>();

    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        launchSite.set(new LaunchSite(getDriver()));
        loginPage.set(new PortalLoginPage(getDriver()));
        //login.set(new Login_Test(getDriver()));
        switchproperty.set(new SwitchProperty(getDriver()));
        CommonActionThread.set(new CommonAction(getDriver()));
        CPPageThread.set(new CPPage(getDriver()));
        RPPOApprovalPage.set(new RPPOApprovalPage(getDriver()));
        RQCreateThread.set(new RQCreate(getDriver()));
        POThread.set(new PO_Page(getDriver()));
        LoginThread.set(new Login(getDriver()));
        SingleAPThread.set(new SingleApproval(getDriver()));
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
            //login.get().LoginCheck(m);

            LoginThread.get().loginAndLaunchTest(m);
            POThread.get().Switch0345();
            //switchproperty.get().SwitchProperty();


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login",
                    true, e);
        }
    }


    @Test(priority = 2)
    public void CPTest(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " PO Creation");

        try {
            RQCreateThread.get().AddProductWithoutApprov();
            SingleAPThread.get().CPPOCreation();
            SingleAPThread.get().SelectRPPO();

//            CPPageThread.get().CPPOCreation();
//            CPPageThread.get().SelectRPPO();
            RPPOApprovalPage.get().Approve();
//            RPPOApprovalPage.get().validate();

            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.PASS, "PO Creation Pass", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.FAIL, "Exception found in Login", true, e);
        }
    }
}












//    @Test(priority = 3)
//    public void POCreate(Method m) {
//        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " PO Creation" );
//
//        try {
//            CPPageThread.get().CPPOCreation();
//            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.PASS, "PO Creation Pass", true);
//
//        } catch (Exception e) {
//            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.FAIL, "Exception found in Login",
//                    true, e);
//        }
//    }
//
//    @Test(priority = 4)
//    public void EnterItems(){
//        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " , " Enter Items" );
//        try {
//
//            CommonActionThread.get().EnterItems();
//            CommonActionThread.get().SaveDetailsinExcel();
//
//
//            // poWithoutReqThreadLocal.get().ExcelSaveTest();  optional
//            ExtentTestManager.createAssertTestStepWithScreenshot("E", Status.PASS, "Enter Items", true);
//
//        } catch (Exception e) {
//            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Items", Status.FAIL, "Exception found in Login",
//                    true, e);
//        }
//    }
//
//    @Test(priority = 5)
//    public void Approve(){
//        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " , " Enter Items" );
//
//        try {
//
//            RPPOApprovalPage.get().Approve();
//            RPPOApprovalPage.get().validate();
//        } catch (Exception e){
//            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Items", Status.FAIL, "Exception found in Login",
//                    true, e);
//        }
//    }


//    @AfterMethod(alwaysRun = true)
//    public void closeDriver() {
//        launchSite.remove();
//
//        closeBrowser();
//    }







//
//    private final ThreadLocal<Login> LoginThread = new ThreadLocal<>();
//    private final ThreadLocal<InventoryControl> InventoryControlThread = new ThreadLocal<>();
//    private final ThreadLocal<RQCreate> RQCreateThread = new ThreadLocal<>();
//    private final ThreadLocal<CPPage> CPPageThread = new ThreadLocal<>();
//    private final ThreadLocal<RPPOApprovalPage> RPPOApprovalPage = new ThreadLocal<>();
//
//
//    @BeforeClass(alwaysRun = true)
//    public void setUpBrowser() {
//        setDriver();
//        LoginThread.set(new Login(getDriver()));
//        InventoryControlThread.set(new InventoryControl(getDriver()));
//
//        CPPageThread.set(new CPPage(getDriver()));
//        RPPOApprovalPage.set(new RPPOApprovalPage(getDriver()));
//
//    }
//
//    /**
//     * @Test_Method_Description : For PO Creation
//     * @Modified_By : Sachin Kumar
//     * @Modified_Date : 10/12/2024
//     */
//    @Test(priority = 1)
//    public void Login(Method m) {
//        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " Portal Login");
//        try {
//            LoginThread.get().loginAndLaunchTest(m);
//        } catch (Exception e) {
//            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", true, e);
//        }
//    }
//
//    @Test(priority = 2)
//    public void SwitchProperty(Method m) {
//        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " PO Creation");
//        try {
//            InventoryControlThread.get().SwitchProperty();
//            InventoryControlThread.get().InventoryControl();
//            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.PASS, "PO Creation Pass", true);
//        } catch (Exception e) {
//            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.FAIL, "Exception found in Login", true, e);
//        }
//    }
//
//    @Test(priority = 3)
//    public void CPTest(Method m) {
//        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " PO Creation");
//
//        try {
//            RQCreateThread.get().AddProduct();
//            CPPageThread.get().CPPOCreation();
//            CPPageThread.get().SelectPO();
//            RPPOApprovalPage.get().Approve();
//            RPPOApprovalPage.get().validate();
//
//            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.PASS, "PO Creation Pass", true);
//
//        } catch (Exception e) {
//            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.FAIL, "Exception found in Login", true, e);
//        }
//    }
//}

package ValidationTest;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import listeners.RetryAnalyzer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.POPages.WithoutApproval.withRQ.InventoryControl;
import pages.POPages.WithoutApproval.withRQ.Login;
import pages.POPages.WithoutApproval.withoutRQ.PO_Page;
import pages.POPages.WithoutRQ.CPPage;
import pages.POPages.WithoutRQ.CommonAction;
import pages.POPages.WithoutRQ.RPPOApprovalPage;
import pages.SRV.SRVPage;
import pages.Validation.POPage;

import java.lang.reflect.Method;

/**
 * Author: SACHIN KUMAR (replace sachin with your name or a fixed string)
 * Date: 23-01-2025
 */
public class POValidation extends BaseTest {

    private final ThreadLocal<Login> LoginThread = new ThreadLocal<>();
    private final ThreadLocal<InventoryControl> InventoryControlThread = new ThreadLocal<>();


    private final ThreadLocal<PO_Page> POThread = new ThreadLocal<>();

    private final ThreadLocal<CPPage> CPThread = new ThreadLocal<>();
    private final ThreadLocal<CommonAction> CommonActionThread = new ThreadLocal<>();
    private final ThreadLocal<SRVPage> SRVThread = new ThreadLocal<>();
    private final ThreadLocal<RPPOApprovalPage> RPPOApprovalPage = new ThreadLocal<>();
    private final ThreadLocal<POPage> POValidationThread = new ThreadLocal<>();



    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        LoginThread.set(new Login(getDriver()));
        InventoryControlThread.set(new InventoryControl(getDriver()));
        POValidationThread.set(new POPage(getDriver()));

        POThread.set(new PO_Page(getDriver()));

        CommonActionThread.set(new CommonAction(getDriver()));
        CPThread.set(new CPPage(getDriver()));
        SRVThread.set(new SRVPage(getDriver()));
        RPPOApprovalPage.set(new RPPOApprovalPage(getDriver()));

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
            LoginThread.get().loginAndLaunchTest346(m);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(priority = 2)
    public void POCreate(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " PO Creation");

        try {

            InventoryControlThread.get().Switch0346();
            // InventoryControlThread.get().InventoryControlSRV();


            POThread.get().RPPO();
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.PASS, "PO Creation Pass", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(priority = 3)
    public void EnterItems() {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: ", " Enter Items");
        try {

            CommonActionThread.get().EnterItemz();
            CommonActionThread.get().SaveDetailsinExcel();

            ExtentTestManager.createAssertTestStepWithScreenshot("E", Status.PASS, "Enter Items", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Items", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(priority = 4)
    public void SavePONumber() {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: ", " Save PO Number");

        try {




            CommonActionThread.get().SaveRQNumber();
            CPThread.get().Print();
            SRVThread.get().POSRV2();
            POValidationThread.get().EnterQty();

            ExtentTestManager.createAssertTestStepWithScreenshot("SaveRQNumber", Status.PASS, "PO Number Saved ", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("SaveRQNumber", Status.FAIL, "Exception found in SaveRQNumber", true, e);
        }
    }

}

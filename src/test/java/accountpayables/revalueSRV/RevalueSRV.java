package accountpayables.revalueSRV;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.POPages.WithoutApproval.withRQ.InventoryControl;
import pages.POPages.WithoutApproval.withRQ.Login;
import pages.POPages.WithoutApproval.withoutRQ.PO_Page;
import pages.SRV.SRVPage;

import java.lang.reflect.Method;

/**
 * Author: SACHIN KUMAR (replace sachin with your name or a fixed string)
 * Date: 26-01-2025
 */
public class RevalueSRV extends BaseTest {

    private final ThreadLocal<Login> LoginThread = new ThreadLocal<>();
    private final ThreadLocal<InventoryControl> InventoryControlThread = new ThreadLocal<>();
    private final ThreadLocal<SRVPage> SRVThread = new ThreadLocal<>();
    private final ThreadLocal<PO_Page> POThread = new ThreadLocal<>();



    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        LoginThread.set(new Login(getDriver()));
        InventoryControlThread.set(new InventoryControl(getDriver()));
        SRVThread.set(new SRVPage(getDriver()));
        POThread.set(new PO_Page(getDriver()));
    }

    /**
     * @Test_Method_Description : For SRV Creation
     * @Modified_By : Sachin Kumar
     * @Modified_Date : 10/12/2024
     */
    @Test(priority = 1)
    public void Login(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " Portal Login");
        try {
            LoginThread.get().loginAndLaunchTestSRV(m);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(priority = 2)
    public void SwitchProperty(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " PO Creation");

        try {

            InventoryControlThread.get().Switch0419();
            //InventoryControlThread.get().InventoryControlSRV();
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.PASS, "PO Creation Pass", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(priority = 3)
    public void SRVTest(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " PO Creation");

        try {

            SRVThread.get().CreateSRV();

            SRVThread.get().EnterItemz();
            //SRVThread.get().SelectDepartment();



            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.PASS, "PO Creation Pass", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.FAIL, "Exception found in Login", true, e);
        }
    }


}

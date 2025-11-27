package PO.WithRQ.WithoutApproval;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.PO.Login346;
import pages.PO.POPage;
import pages.PO.RQCreate;
import pages.PO.SwitchProperty;
import pages.POPages.WithoutApproval.withRQ.CPPage;

import java.lang.reflect.Method;


public class CPTest extends BaseTest {

    private final ThreadLocal<Login346> LoginThread = new ThreadLocal<>();
    private final ThreadLocal<SwitchProperty> SwitchPropertyThread = new ThreadLocal<>();
    private final ThreadLocal<RQCreate> RQCreateThread = new ThreadLocal<>();
    // private final ThreadLocal<CPPage> CPPageThread = new ThreadLocal<>();
    private final ThreadLocal<POPage> POPageThread = new ThreadLocal<>();


    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        LoginThread.set(new Login346(getDriver()));
        SwitchPropertyThread.set(new SwitchProperty(getDriver()));
        RQCreateThread.set(new RQCreate(getDriver()));
        //CPPageThread.set(new CPPage(getDriver()));
        POPageThread.set(new POPage(getDriver()));
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
            LoginThread.get().loginAndLaunchTest(m);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(priority = 2)
    public void SwitchProperty(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " PO Creation");
        try {
            SwitchPropertyThread.get().Switch0346();
            // SwitchPropertyThread.get().InventoryControl();
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.PASS, "PO Creation Pass", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(priority = 3)
    public void CPTest(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " PO Creation");

        try {
            RQCreateThread.get().AddProduct();
            POPageThread.get().EnterRQ();
            POPageThread.get().SelectCP();

            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.PASS, "PO Creation Pass", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.FAIL, "Exception found in Login", true, e);
        }
    }
}

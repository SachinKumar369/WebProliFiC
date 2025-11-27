package VendorSetup.WithApproval;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import listeners.RetryAnalyzer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Login.Login;
import pages.POPages.WithoutApproval.withRQ.InventoryControl;
import pages.VendorCreation.VendorPage;

import java.lang.reflect.Method;

public class VendorCreation extends BaseTest {

    private final ThreadLocal<Login> LoginThread = new ThreadLocal<>();
    private final ThreadLocal<InventoryControl> InventoryControlThread = new ThreadLocal<>();
    private final ThreadLocal<VendorPage> VendorThread = new ThreadLocal<>();


    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        LoginThread.set(new Login(getDriver()));
        InventoryControlThread.set(new InventoryControl(getDriver()));
        VendorThread.set(new VendorPage(getDriver()));

    }


    @Test(priority = 1, retryAnalyzer = RetryAnalyzer.class)
    public void Login(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " Portal Login");
        try {
            LoginThread.get().loginVAT(m);
            //InventoryControlThread.get().Switch0419();


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(priority = 2)
    public void VendorCreate(Method m){
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " VendorCreate");
        try {
            VendorThread.get().VendorCreation();
            ExtentTestManager.createAssertTestStepWithScreenshot("VendorCreate", Status.PASS, "Vendor Creation Pass", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("VendorCreate", Status.FAIL, "Exception found in VendorCreate", true, e);

        }
    }

    @Test(priority = 3)
    public void VendorApprove(Method m){
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " VendorCreate");
        try {
            VendorThread.get().VendorApproval();
            ExtentTestManager.createAssertTestStepWithScreenshot("VendorCreate", Status.PASS, "Vendor Creation Pass", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("VendorCreate", Status.FAIL, "Exception found in VendorCreate", true, e);

        }
    }








}

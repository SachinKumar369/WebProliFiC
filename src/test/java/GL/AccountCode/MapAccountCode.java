package GL.AccountCode;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.GL.GL;
import pages.Login.Login;
import pages.POPages.WithoutApproval.withRQ.InventoryControl;

import java.lang.reflect.Method;

public class MapAccountCode extends BaseTest {

    private final ThreadLocal<Login> LoginThread = new ThreadLocal<>();
    private final ThreadLocal<InventoryControl> InventoryControlThread = new ThreadLocal<>();
    private final ThreadLocal<GL> glThread = new ThreadLocal<>();

    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        LoginThread.set(new Login(getDriver()));
        InventoryControlThread.set(new InventoryControl(getDriver()));
        glThread.set(new GL(getDriver()));
    }

    @Test(priority = 1)
    public void createARAccountTest(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "Create new AR Account test");
        try {
            LoginThread.get().loginVAT(m);
            glThread.get().gl();
            glThread.get().createAccCode("ASSETS", false, false, false);
            glThread.get().mapAccCode();


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AR Account Creation", Status.FAIL, "AR Account creation failed", true, e);
        }
    }
}

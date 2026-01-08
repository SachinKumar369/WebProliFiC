package GL.BankReconciliation;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AccountsReceivables.BankReconcilation;
import pages.GL.GL;
import pages.Login.Login;
import pages.POPages.WithoutApproval.withRQ.InventoryControl;

import java.lang.reflect.Method;

public class DeleteTrasection extends BaseTest {

    private final ThreadLocal<Login> LoginThread = new ThreadLocal<>();
    private final ThreadLocal<InventoryControl> InventoryControlThread = new ThreadLocal<>();
    private final ThreadLocal<GL> glThread = new ThreadLocal<>();
    private final ThreadLocal<BankReconcilation> bankRecon = new ThreadLocal<>();

    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        LoginThread.set(new Login(getDriver()));
        InventoryControlThread.set(new InventoryControl(getDriver()));
        glThread.set(new GL(getDriver()));
        bankRecon.set(new BankReconcilation(getDriver()));
    }

    @Test(priority = 1)
    public void createARAccountTest(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "Create new AR Account test");
        try {
            LoginThread.get().loginVAT(m);
            //InventoryControlThread.get().SwitchBHOTL();
            bankRecon.get().deleteTransection();


            //  ExtentTestManager.createAssertTestStepWithScreenshot("AR Account Creation", Status.PASS, "AR Account created successfully", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AR Account Creation", Status.FAIL, "AR Account creation failed", true, e);
        }
    }
}

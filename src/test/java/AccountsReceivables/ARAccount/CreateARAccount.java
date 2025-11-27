package AccountsReceivables.ARAccount;

import baselibrary.BaseTest;
import extentreports.ExtentTestManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import com.aventstack.extentreports.Status;
import pages.AccountsReceivables.ARPage;
import pages.Login.Login;
import pages.POPages.WithoutApproval.withRQ.InventoryControl;

public class CreateARAccount extends BaseTest {

    private final ThreadLocal<Login> LoginThread = new ThreadLocal<>();
    private final ThreadLocal<InventoryControl> InventoryControlThread = new ThreadLocal<>();
    private final ThreadLocal<ARPage> arAccountPageThread = new ThreadLocal<>();

    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        LoginThread.set(new Login(getDriver()));
        InventoryControlThread.set(new InventoryControl(getDriver()));
        arAccountPageThread.set(new ARPage(getDriver()));
    }

    @Test(priority = 1)
    public void createARAccountTest(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "Create new AR Account test");
        try {
            LoginThread.get().loginAndLaunchTestSRV(m);
            InventoryControlThread.get().Switch0419();

            arAccountPageThread.get().navigateToARAccount();
            arAccountPageThread.get().createARAccount();
            ExtentTestManager.createAssertTestStepWithScreenshot("AR Account Creation", Status.PASS, "AR Account created successfully", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AR Account Creation", Status.FAIL, "AR Account creation failed", true, e);
        }
    }
}

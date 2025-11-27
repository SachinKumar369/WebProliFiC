package AccountsReceivables.TransactionEntry;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AccountsReceivables.ARPage;
import pages.Login.Login;
import pages.POPages.WithoutApproval.withRQ.InventoryControl;

import java.lang.reflect.Method;

public class InvoicePostingWithTax extends BaseTest {

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
            LoginThread.get().loginVAT(m);
            InventoryControlThread.get().SwitchBHOTL();

            //arAccountPageThread.get().navigateToARAccount();
           // arAccountPageThread.get().withTax();



            arAccountPageThread.get().ar();
            arAccountPageThread.get().selectTransactionType("invoice");
            arAccountPageThread.get().transectionDetails(false);
            arAccountPageThread.get().invoice(0);
            arAccountPageThread.get().enterTax(0);
            arAccountPageThread.get().enterGL();
            ExtentTestManager.createAssertTestStepWithScreenshot("AR Account Creation", Status.PASS, "AR Account created successfully", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AR Account Creation", Status.FAIL, "AR Account creation failed", true, e);
        }
    }
}

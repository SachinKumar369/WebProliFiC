package AccountsReceivables.TransactionEntry;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AccountsReceivables.ARPage;
import pages.Login.Login;
import pages.POPages.WithoutApproval.withRQ.InventoryControl;
import pages.T0419_Pages.SwitchProperty;

import java.lang.reflect.Method;

public class TransferTransection extends BaseTest {

    private final ThreadLocal<Login> LoginThread = new ThreadLocal<>();
    private final ThreadLocal<ARPage> arAccountPageThread = new ThreadLocal<>();
    private final ThreadLocal<SwitchProperty> switchProperty = new ThreadLocal<>();

    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        LoginThread.set(new Login(getDriver()));
         arAccountPageThread.set(new ARPage(getDriver()));
        switchProperty.set(new SwitchProperty(getDriver()));
    }

    @Test(priority = 1)
    public void createARAccountTest(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "Create new AR Account test");
        try {
            LoginThread.get().loginVAT(m);
            switchProperty.get().SwitchTEPD();

            //arAccountPageThread.get().navigateToARAccount();
           // arAccountPageThread.get().withTax();



            arAccountPageThread.get().ar();
            arAccountPageThread.get().selectTransactionType("payment");
            arAccountPageThread.get().transectionTEPD(false);
            arAccountPageThread.get().chequeDetail(false);

            arAccountPageThread.get().paymentParticular();
            //arAccountPageThread.get().enterTax(0);
            arAccountPageThread.get().savePayment();
            arAccountPageThread.get().transferTran();
           // ExtentTestManager.createAssertTestStepWithScreenshot("AR Account Creation", Status.PASS, "AR Account created successfully", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AR Account Creation", Status.FAIL, "AR Account creation failed", true, e);
        }
    }
}

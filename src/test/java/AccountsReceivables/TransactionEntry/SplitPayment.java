package AccountsReceivables.TransactionEntry;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AccountsReceivables.ARPage;
import pages.AccountsReceivables.CentralPayment;
import pages.Login.Login;
import pages.POPages.WithoutApproval.withRQ.InventoryControl;

import java.lang.reflect.Method;

import static pages.AccountsReceivables.ARPage.InquiryType.Cheque_Inquiry;

public class SplitPayment extends BaseTest {

    private final ThreadLocal<Login> LoginThread = new ThreadLocal<>();
    private final ThreadLocal<InventoryControl> InventoryControlThread = new ThreadLocal<>();
    private final ThreadLocal<ARPage> arAccountPageThread = new ThreadLocal<>();
    private final ThreadLocal<CentralPayment> centralPayment = new ThreadLocal<>();

    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        LoginThread.set(new Login(getDriver()));
        InventoryControlThread.set(new InventoryControl(getDriver()));
        arAccountPageThread.set(new ARPage(getDriver()));
        centralPayment.set(new CentralPayment(getDriver()));
    }


    @Test(priority = 1)
    public void createARAccountTest(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "Create new AR Account test");
        try {
            LoginThread.get().loginVAT(m);
            InventoryControlThread.get().SwitchBHOTL();
            centralPayment.get().performTransactionEntry();



            ExtentTestManager.createAssertTestStepWithScreenshot("AR Account Creation", Status.PASS, "AR Account created successfully", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("AR Account Creation", Status.FAIL, "AR Account creation failed", true, e);
        }
    }
}

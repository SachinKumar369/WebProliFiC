package accountpayables.centralPayment;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import listeners.RetryAnalyzer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.POPages.WithoutApproval.withRQ.InventoryControl;
import pages.POPages.WithoutApproval.withRQ.Login;
import pages.T0419_Pages.SwitchProperty;
import pages.accountPayables.TransactionReversal.TransactionReversalPage;
import pages.accountPayables.centralPayment.CPPage;
import pages.accountPayables.payadvance.PO;

import java.lang.reflect.Method;

/**
 * Author: SACHIN KUMAR (replace sachin with your name or a fixed string)
 * Date: 23-01-2025
 */
public class CentralPayment extends BaseTest {

    private final ThreadLocal<Login> LoginThread = new ThreadLocal<>();
    private final ThreadLocal<PO> po = new ThreadLocal<>();
    private final ThreadLocal<TransactionReversalPage> reversal = new ThreadLocal<>();
    private final ThreadLocal<SwitchProperty> switchProp = new ThreadLocal<>();
    private final ThreadLocal<InventoryControl> inventrycontrol = new ThreadLocal<>();
    private final ThreadLocal<CPPage> makePJV = new ThreadLocal<>();

    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        LoginThread.set(new Login(getDriver()));
        po.set(new PO(getDriver()));
        reversal.set(new TransactionReversalPage(getDriver()));
        switchProp.set(new SwitchProperty(getDriver()));
        inventrycontrol.set(new InventoryControl(getDriver()));
        makePJV.set(new CPPage(getDriver()));
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
            LoginThread.get().loginVAT(m);
            inventrycontrol.get().SwitchAKHL();


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(priority = 2)
    public void POCreate(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " PO Creation");

        try {



            makePJV.get().accounting();
            makePJV.get().addPJVEntry();
            makePJV.get().performPaymentRequest();
            makePJV.get().processPayment();

            //  ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.PASS, "PO Creation Pass", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.FAIL, "Exception found in Login", true, e);
        }
    }


}

package IssueTest.WithRQ;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Issue.InventoryControl;
import pages.Issue.IssuePage;
import pages.Issue.Login;
import pages.Issue.RQCreate;

import java.lang.reflect.Method;

/**
 * Author: SACHIN KUMAR (replace sachin with your name or a fixed string)
 * Date: 23-01-2025
 */
public class ExpiryItems extends BaseTest {

    private final ThreadLocal<InventoryControl> switchproperty = new ThreadLocal<>();
    private final ThreadLocal<Login> login = new ThreadLocal<>();
    private final ThreadLocal<RQCreate> RQCreateThread = new ThreadLocal<>();
    private final ThreadLocal<IssuePage> IssueThread = new ThreadLocal<>();


    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        login.set(new Login(getDriver()));
        switchproperty.set(new InventoryControl(getDriver()));
        RQCreateThread.set(new RQCreate(getDriver()));
        IssueThread.set(new IssuePage(getDriver()));
    }


    @Test(priority = 1)
    public void Login(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " Portal Login");
        try {
            login.get().login0419(m);
            switchproperty.get().Switch0419();


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(priority = 2)
    public void RQCreate(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " RQ Creation");

        try {

            RQCreateThread.get().RQExpiryItems();
            IssueThread.get().IssueTax();
            ExtentTestManager.createAssertTestStepWithScreenshot("RQ Creation", Status.PASS, "RQ Creation Pass", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("RQ Creation", Status.FAIL, "Exception found in RQ Creation", true, e);
        }
    }

    @Test(priority = 3)
    public void CheckExpiry() {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: ", "Check Expiry");
        try {

            IssueThread.get().CheckExpiry();
            //ExtentTestManager.createAssertTestStepWithScreenshot("E", Status.PASS, "Enter Items", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Check Expiry", Status.FAIL, "Exception found in Method Expiry", true, e);
        }
    }


}

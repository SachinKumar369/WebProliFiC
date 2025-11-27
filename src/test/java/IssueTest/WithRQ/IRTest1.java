package IssueTest.WithRQ;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CommonPages.LaunchSite;
import pages.CommonPages.PortalLoginPage;
import pages.Issue.InventoryControl;
import pages.Issue.IssuePage;
import pages.Issue.Login;
import pages.Issue.RQCreate;
import pages.POPages.WithRQ.RPPage;
import pages.POPages.WithRQ.RQApprove;
import pages.POPages.WithoutRQ.CommonAction;

import java.lang.reflect.Method;

/**
 * Author: SACHIN KUMAR (replace sachin with your name or a fixed string)
 * Date: 23-01-2025
 */
public class IRTest1 extends BaseTest {
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

    /**
     * @Test_Method_Description : For Creating Issue
     * @Modified_By : Sachin Kumar
     * @Modified_Date : 03/04/2025
     */
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
            RQCreateThread.get().AddProduct();
            ExtentTestManager.createAssertTestStepWithScreenshot("RQ Creation", Status.PASS, "RQ Creation Pass", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("RQ Creation", Status.FAIL, "Exception found in Creating RQ", true, e);
        }
    }

    @Test(priority = 3)
    public void Issue() {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: ", " Issue");
        try {
            IssueThread.get().IssueRQ();


            ExtentTestManager.createAssertTestStepWithScreenshot("Issue", Status.PASS, "Issue Creation Pass", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Issue", Status.FAIL, "Exception found while Creating Issue", true, e);
        }
    }

    @Test(priority = 4)
    public void SaveIntoExcel() {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: ", " Save Issue Number");

        try {

            IssueThread.get().SaveDetailsinExcel();

            ExtentTestManager.createAssertTestStepWithScreenshot("SaveIntoExcel", Status.PASS, "Issue Number Saved ", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("SaveIntoExcel", Status.FAIL, "Exception found in Saving Issue Number", true, e);
        }
    }
}

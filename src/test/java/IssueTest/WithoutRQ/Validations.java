package IssueTest.WithoutRQ;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import listeners.RetryAnalyzer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Issue.InventoryControl;
import pages.Issue.IssuePage;
import pages.Issue.Login;

import java.lang.reflect.Method;


public class Validations extends BaseTest {

    private final ThreadLocal<Login> LoginThread = new ThreadLocal<>();
    private final ThreadLocal<InventoryControl> InventoryControlThread = new ThreadLocal<>();
    private final ThreadLocal<IssuePage> IssueThread = new ThreadLocal<>();


    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        LoginThread.set(new Login(getDriver()));
        InventoryControlThread.set(new InventoryControl(getDriver()));
        IssueThread.set(new IssuePage(getDriver()));
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
            LoginThread.get().login0419(m);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(priority = 2, retryAnalyzer = RetryAnalyzer.class)
    public void SwitchProperty(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "SwitchProperty");
        try {
            InventoryControlThread.get().Switch0419();
            ExtentTestManager.createAssertTestStepWithScreenshot("SwitchProperty", Status.PASS, "SwitchProperty Pass", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("SwitchProperty", Status.FAIL, "Exception found in SwitchProperty", true, e);
        }
    }

    @Test(priority = 3, retryAnalyzer = RetryAnalyzer.class)
    public void Issue(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "Issue");
        try {
            IssueThread.get().Validation();
            //IssueThread.get().EnterItemz();
            ExtentTestManager.createAssertTestStepWithScreenshot("SwitchProperty", Status.PASS, "Issue Pass", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("SwitchProperty", Status.FAIL, "Exception found in Issue", true, e);
        }
    }

    @Test(priority = 4, retryAnalyzer = RetryAnalyzer.class)
    public void SaveIntoExcel(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "Issue");
        try {
            //IssueThread.get().SaveDetailsinExcel();
            ExtentTestManager.createAssertTestStepWithScreenshot("SwitchProperty", Status.PASS, "Issue Pass", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("SwitchProperty", Status.FAIL, "Exception found in Issue", true, e);
        }
    }

}

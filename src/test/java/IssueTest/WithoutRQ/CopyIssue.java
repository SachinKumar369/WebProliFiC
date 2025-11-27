package IssueTest.WithoutRQ;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import listeners.RetryAnalyzer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Issue.IssuePage;
import pages.POPages.WithoutApproval.withRQ.InventoryControl;
import pages.POPages.WithoutApproval.withRQ.Login;

import java.lang.reflect.Method;


public class CopyIssue extends BaseTest {

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

    @Test(priority = 2,retryAnalyzer = RetryAnalyzer.class)
    public void SwitchProperty(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "SwitchProperty");
        try {
            InventoryControlThread.get().Switch0419();
            ExtentTestManager.createAssertTestStepWithScreenshot("SwitchProperty", Status.PASS, "SwitchProperty Pass", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("SwitchProperty", Status.FAIL, "Exception found in SwitchProperty", true, e);
        }
    }

    @Test(priority = 3,retryAnalyzer = RetryAnalyzer.class)
    public void Issue(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "Issue");
        try {
            IssueThread.get().Issue();
            IssueThread.get().EnterItemz();
            ExtentTestManager.createAssertTestStepWithScreenshot("Issue", Status.PASS, "Issue Pass", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Issue", Status.FAIL, "Exception found in Issue", true, e);
        }
    }

    @Test(priority = 4,retryAnalyzer = RetryAnalyzer.class)
    public void Copy(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "Issue");
        try {
            IssueThread.get().CopyIssue();
            ExtentTestManager.createAssertTestStepWithScreenshot("Copy", Status.PASS, "Copy Pass", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Copy", Status.FAIL, "Exception found in Copy", true, e);
        }
    }

    @Test(priority = 5,retryAnalyzer = RetryAnalyzer.class)
    public void SaveDetailsinExcel(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "SaveDetailsinExcel");
        try {
            IssueThread.get().SaveDetailsinExcel();
            ExtentTestManager.createAssertTestStepWithScreenshot("SaveDetailsinExcel", Status.PASS, "SaveDetailsinExcel Pass", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("SaveDetailsinExcel", Status.FAIL, "Exception found in SaveDetailsinExcel", true, e);
        }
    }



}

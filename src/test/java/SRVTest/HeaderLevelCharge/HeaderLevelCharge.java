package SRVTest.HeaderLevelCharge;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import listeners.RetryAnalyzer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.POPages.WithoutApproval.withRQ.EditPage;
import pages.POPages.WithoutApproval.withRQ.InventoryControl;
import pages.POPages.WithoutApproval.withRQ.Login;
import pages.POPages.WithoutApproval.withoutRQ.PO_Page;
import pages.POPages.WithoutRQ.CPPage;
import pages.POPages.WithoutRQ.CommonAction;
import pages.SRV.SRVPage;

import java.lang.reflect.Method;

/**
 * Author: SACHIN KUMAR (replace sachin with your name or a fixed string)
 * Date: 23-01-2025
 */
public class HeaderLevelCharge extends BaseTest {

    private final ThreadLocal<Login> LoginThread = new ThreadLocal<>();
    private final ThreadLocal<PO_Page> POThread = new ThreadLocal<>();
    private final ThreadLocal<EditPage> EditThread = new ThreadLocal<>();

    private final ThreadLocal<InventoryControl> InventoryControlThread = new ThreadLocal<>();
    private final ThreadLocal<CPPage> CPThread = new ThreadLocal<>();
    private final ThreadLocal<CommonAction> CommonActionThread = new ThreadLocal<>();
    private final ThreadLocal<pages.POPages.WithoutApproval.withRQ.CPPage> CPPageThread = new ThreadLocal<>();
    private final ThreadLocal<SRVPage> SRVThread = new ThreadLocal<>();


    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        LoginThread.set(new Login(getDriver()));
        POThread.set(new PO_Page(getDriver()));
        EditThread.set(new EditPage(getDriver()));

        InventoryControlThread.set(new InventoryControl(getDriver()));
        CommonActionThread.set(new CommonAction(getDriver()));
        CPThread.set(new CPPage(getDriver()));
        CPPageThread.set(new pages.POPages.WithoutApproval.withRQ.CPPage(getDriver()));
        SRVThread.set(new SRVPage(getDriver()));
    }


    @Test(priority = 1, retryAnalyzer = RetryAnalyzer.class)
    public void Login(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " Portal Login");
        try {
            LoginThread.get().loginVAT(m);
            InventoryControlThread.get().SwitchAKHL();


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(priority = 2)
    public void POCreate(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " PO Creation");

        try {
            POThread.get().RPPO();
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.PASS, "PO Creation Pass", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(priority = 3)
    public void EnterItems() {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: ", " Enter Items");
        try {

            POThread.get().EnterItems();
            POThread.get().HeaderCharge();

            // POThread.get().AddRow();

            ExtentTestManager.createAssertTestStepWithScreenshot("E", Status.PASS, "Enter Items", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Enter Items", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(priority = 4)
    public void SavePONumber() {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: ", " Save PO Number");

        try {



            //SRVThread.get().SaveSRVToExcel();
            CPThread.get().PrintandApprove();
            SRVThread.get().SavePOToExcel();
            SRVThread.get().POSRV2();
            SRVThread.get().CheckItemCharge();

            ExtentTestManager.createAssertTestStepWithScreenshot("SaveRQNumber", Status.PASS, "PO Number Saved ", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("SaveRQNumber", Status.FAIL, "Exception found in SaveRQNumber", true, e);
        }
    }

}

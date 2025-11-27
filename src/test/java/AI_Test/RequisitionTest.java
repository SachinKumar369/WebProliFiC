package AI_Test;



import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import listeners.RetryAnalyzer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AI_Pages.Requisition;


import java.lang.reflect.Method;

public class RequisitionTest extends BaseTest {

    private final ThreadLocal<Requisition> requisitionThread = new ThreadLocal<>();

    @DataProvider(name = "requisitionData")
    public Object[][] provideRequisitionData() {
        return new Object[][] {
                {"CLARD", "CLARD", "SUP", "T0419 - The Claridges(Delhi)", "10.000", "11.000"}
        };
    }

    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        requisitionThread.set(new Requisition(getDriver()));
    }

    @Test(priority = 1, retryAnalyzer = RetryAnalyzer.class, dataProvider = "requisitionData")
    public void createRequisitionTest(Method m, String chainID, String unitID, String username, String property, String qty1, String qty2) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "Requisition Creation Test");
        try {
            requisitionThread.get().login(chainID, unitID, username);
            requisitionThread.get().selectProperty(property);
            requisitionThread.get().navigateToRequisitions();
            requisitionThread.get().createRequisition();
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Requisition Test", Status.FAIL, "Exception during requisition creation", true, e);
        }
    }
}


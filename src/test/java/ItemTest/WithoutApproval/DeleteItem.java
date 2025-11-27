package ItemTest.WithoutApproval;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import listeners.RetryAnalyzer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ItemPage.ItemPage;
import pages.Login.Login;
import pages.POPages.WithoutApproval.withRQ.InventoryControl;

import java.lang.reflect.Method;

public class DeleteItem extends BaseTest {

    private final ThreadLocal<Login> LoginThread = new ThreadLocal<>();
    private final ThreadLocal<InventoryControl> InventoryControlThread = new ThreadLocal<>();
    private final ThreadLocal<ItemPage> ItemThread = new ThreadLocal<>();


    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        LoginThread.set(new Login(getDriver()));
        InventoryControlThread.set(new InventoryControl(getDriver()));
        ItemThread.set(new ItemPage(getDriver()));

    }


    @Test(priority = 1, retryAnalyzer = RetryAnalyzer.class)
    public void Login(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " Portal Login");
        try {
            LoginThread.get().login0419(m);
            InventoryControlThread.get().Switch0419();


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(priority = 2)
    public void  ItemCreate(Method m){
        ExtentTestManager.startTest(this.getClass().getSimpleName()+" :: "+ m.getName(),"Item Creation");
        try {
            ItemThread.get().ItemCreate();
            ItemThread.get().MarketList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 2)
    public void  ItemDelete(Method m){
        ExtentTestManager.startTest(this.getClass().getSimpleName()+" :: "+ m.getName(),"Item Creation");
        try {
            ItemThread.get().DeleteItem();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }






}

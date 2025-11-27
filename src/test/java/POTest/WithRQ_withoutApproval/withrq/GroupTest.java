package POTest.WithRQ_withoutApproval.withrq;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.POPages.WithoutApproval.withRQ.*;

import java.lang.reflect.Method;


public class GroupTest extends BaseTest {

    private final ThreadLocal<Login> LoginThread = new ThreadLocal<>();
    private final ThreadLocal<InventoryControl> InventoryControlThread = new ThreadLocal<>();
    private final ThreadLocal<RQCreate> RQCreateThread = new ThreadLocal<>();
    private final ThreadLocal<RPPage> RPPageThread = new ThreadLocal<>();
    private final ThreadLocal<CPPage> CPPageThread = new ThreadLocal<>();
    private final ThreadLocal<ItemLevelCharges> ItemLevelChargesThread = new ThreadLocal<>();
    private final ThreadLocal<EditPage> EditPageThread = new ThreadLocal<>();


    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        LoginThread.set(new Login(getDriver()));
        InventoryControlThread.set(new InventoryControl(getDriver()));
        RQCreateThread.set(new RQCreate(getDriver()));
        RPPageThread.set(new RPPage(getDriver()));
        CPPageThread.set(new CPPage(getDriver()));
        ItemLevelChargesThread.set(new ItemLevelCharges(getDriver()));
        EditPageThread.set(new EditPage(getDriver()));


    }

    /**
     * @Test_Method_Description : For PO Creation
     * @Modified_By : Sachin Kumar
     * @Modified_Date : 10/12/2024
     */
    @Test(groups = "ScheduleItem", priority = 1)
    public void ScheduleItem(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " Portal Login");
        try {
            LoginThread.get().loginAndLaunchTest(m);
            InventoryControlThread.get().SwitchProperty();
            //InventoryControlThread.get().InventoryControl();
            RQCreateThread.get().AddProduct();
            RPPageThread.get().RPPOCreation();
            RPPageThread.get().SelectPO();
            RPPageThread.get().ScheduleItem();
            RPPageThread.get().Print();
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.PASS, "PO Creation Pass", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(groups = "Comment", priority = 2)
    public void Comment(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " Portal Login");
        try {
            //RPPageThread.get().NavigateHome();
            LoginThread.get().loginAndLaunchTest(m);
            InventoryControlThread.get().SwitchProperty();
            //InventoryControlThread.get().InventoryControl();
            RQCreateThread.get().AddProduct();
            CPPageThread.get().CPPOCreation();
            ItemLevelChargesThread.get().SelectPO();
            ItemLevelChargesThread.get().Instructions();
            ItemLevelChargesThread.get().Comment();
            ItemLevelChargesThread.get().Notes();
           // ItemLevelChargesThread.get().Schedule();
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.PASS, "PO Creation Pass", true);

//            RPPageThread.get().RPPOCreation();
//            RPPageThread.get().SelectPO();
//            RPPageThread.get().PaymentSchedule();

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(groups = "Edit", priority = 3)
    public void Edit(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " Portal Login");
        try {
            //RPPageThread.get().NavigateHome();

            LoginThread.get().loginAndLaunchTest(m);
            InventoryControlThread.get().SwitchProperty();
            //InventoryControlThread.get().InventoryControl();
            RQCreateThread.get().AddProduct();
            CPPageThread.get().CPPOCreation();
            CPPageThread.get().SelectPO();
            EditPageThread.get().EditPO();
            RPPageThread.get().Print();
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.PASS, "PO Creation Pass", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(groups = "InventoryControl", priority = 4)
    public void InventoryControl(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " Portal Login");
        try {
            //RPPageThread.get().NavigateHome();

            LoginThread.get().loginAndLaunchTest(m);
            InventoryControlThread.get().SwitchProperty();
            InventoryControlThread.get().InventoryControl();


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(groups = "ItemLevelCharges",priority = 5)
    public void ItemLevelCharges(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " Portal Login");
        try {
            //RPPageThread.get().NavigateHome();

            LoginThread.get().loginAndLaunchTest(m);
            InventoryControlThread.get().SwitchProperty();
            //InventoryControlThread.get().InventoryControl();
            RQCreateThread.get().AddProduct();
            RPPageThread.get().RPPOCreation();
            RPPageThread.get().SelectPO();
            ItemLevelChargesThread.get().ItemCharges();

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(groups = "PaymentSchedule",priority = 6)
    public void PaymentSchedule(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " Portal Login");
        try {
            //RPPageThread.get().NavigateHome();

            LoginThread.get().loginAndLaunchTest(m);
            InventoryControlThread.get().SwitchProperty();
            //InventoryControlThread.get().InventoryControl();
            RQCreateThread.get().AddProduct();
            RPPageThread.get().RPPOCreation();
            RPPageThread.get().SelectPO();
            RPPageThread.get().PaymentSchedule();
            RPPageThread.get().Print();
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(groups = "POLevelCharges", priority = 7)
    public void POLevelCharges(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " Portal Login");
        try {
            //RPPageThread.get().NavigateHome();

            LoginThread.get().loginAndLaunchTest(m);
            InventoryControlThread.get().SwitchProperty();
            //InventoryControlThread.get().InventoryControl();
            RQCreateThread.get().AddProduct();
            RPPageThread.get().RPPOCreation();
            RPPageThread.get().SelectPO();
            ItemLevelChargesThread.get().POCharges();

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(groups = "SchedulePO", priority = 8)
    public void SchedulePO(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " Portal Login");
        try {
            //RPPageThread.get().NavigateHome();

            LoginThread.get().loginAndLaunchTest(m);
            InventoryControlThread.get().SwitchProperty();
            //InventoryControlThread.get().InventoryControl();
            RQCreateThread.get().AddProduct();
            RPPageThread.get().RPPOCreation();
            RPPageThread.get().SelectPO();
            RPPageThread.get().SchedulePO();

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", true, e);
        }
    }


}

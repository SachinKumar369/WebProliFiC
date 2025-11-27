package PO.WithoutRQ.WithoutApproval;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.PO.Login346;
import pages.PO.POPage;
import pages.PO.SwitchProperty;


import java.lang.reflect.Method;


public class RPTest extends BaseTest {

    private final ThreadLocal<Login346> LoginThread = new ThreadLocal<>();
    private final ThreadLocal<SwitchProperty> SwitchPropertyThread = new ThreadLocal<>();
    private final ThreadLocal<POPage> POPageThread = new ThreadLocal<>();


    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        LoginThread.set(new Login346(getDriver()));
        SwitchPropertyThread.set(new SwitchProperty(getDriver()));
        POPageThread.set(new POPage(getDriver()));
    }

    /**
     * @Test_Method_Description : For PO Creation
     * @Modified_By : Sachin Kumar
     * @Modified_Date : 10/12/2024
     */
    @Test(priority = 1)
    public void Login(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " Portal Login");
        try {
            LoginThread.get().loginAndLaunchTest(m);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(priority = 2)
    public void SwitchProperty(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " SwitchProperty");
        try {
            SwitchPropertyThread.get().Switch0346();
           // SwitchPropertyThread.get().InventoryControl();
            //ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.PASS, "SwitchProperty Pass", true);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.FAIL, "Exception found in SwitchProperty", true, e);
        }
    }

    @Test(priority = 3)
    public void RPTest(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " PO Creation");

        try {
            POPageThread.get().RPPOwithoutRQ();
            POPageThread.get().EnterItems2();



            //ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.PASS, "PO Creation Pass", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.FAIL, "Exception found in PO Creation", true, e);
        }
    }




    @Test(priority = 4)
    public void SaveDetailsinExcel(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " SaveDetailsinExcel");

        try {

            POPageThread.get().SaveDetailsinExcel();


            //ExtentTestManager.createAssertTestStepWithScreenshot("SaveDetailsinExcel", Status.PASS, "SaveDetailsinExcel Pass", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("SaveDetailsinExcel", Status.FAIL, "Exception found in SaveDetailsinExcel", true, e);
        }
    }



    @Test(priority = 5)
    public void FinalPrint(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " FinalPrint");

        try {

            POPageThread.get().Print();


            //ExtentTestManager.createAssertTestStepWithScreenshot("FinalPrint", Status.PASS, "FinalPrint Pass", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("FinalPrint", Status.FAIL, "Exception found in FinalPrint", true, e);
        }
    }
}

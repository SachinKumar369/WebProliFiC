package accountpayables.PJV;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import loginTest.Login_Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CommonPages.LaunchSite;
import pages.CommonPages.PortalLoginPage;
import pages.T0419_Pages.SwitchProperty;
import pages.accountPayables.PJV.MakePJVPage;
import pages.accountPayables.PJV.MultipleInvoiceGLPage;
import pages.accountPayables.PJV.TaxDeductionPage;

import java.lang.reflect.Method;

/**
 * Author: SACHIN KUMAR (replace sachin with your name or a fixed string)
 * Date: 26-01-2025
 */
public class TaxDeductionApprovalTest extends BaseTest {
    private final ThreadLocal<LaunchSite> launchSite = new ThreadLocal<>();

    private final ThreadLocal<PortalLoginPage> loginPage = new ThreadLocal<>();
    private final ThreadLocal<SwitchProperty> switchproperty = new ThreadLocal<>();
    private final ThreadLocal<Login_Test> login = new ThreadLocal<>();

        private final ThreadLocal<MakePJVPage> makePJV = new ThreadLocal<>();
    private final ThreadLocal<TaxDeductionPage> taxDeduction = new ThreadLocal<>();
    private final ThreadLocal<MultipleInvoiceGLPage> multipleInv = new ThreadLocal<>();



    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        launchSite.set(new LaunchSite(getDriver()));
        loginPage.set(new PortalLoginPage(getDriver()));
        login.set(new Login_Test(getDriver()));
        switchproperty.set(new SwitchProperty(getDriver()));
        makePJV.set(new MakePJVPage(getDriver()));
       taxDeduction.set(new TaxDeductionPage(getDriver()));
        multipleInv.set(new MultipleInvoiceGLPage(getDriver()));

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
            login.get().LoginCheck(m);
            switchproperty.get().SwitchProperty();


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login",
                    true, e);
        }
    }

    @Test(priority = 2)
    public void POCreate(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " PO Creation" );

        try {
            makePJV.get().accounting();
            taxDeduction.get().completePJVPickerFlow();
            multipleInv.get().saveandClose();
            multipleInv.get().pjfApproval();

            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.PASS, "PO Creation Pass", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.FAIL, "Exception found in Login",
                    true, e);
        }
    }



}

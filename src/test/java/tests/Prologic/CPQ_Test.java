package tests.Prologic;





import com.aventstack.extentreports.Status;
import loginTest.Login_Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CommonPages.LaunchSite;
import pages.CommonPages.PortalLoginPage;
import pages.T0419_Pages.CPQ_Page;
import pages.T0419_Pages.KSTRequisition_Page;
import pages.T0419_Pages.SwitchProperty;
import baselibrary.BaseTest;
import datahandlers.ConfigProperties;
import datahandlers.ExcelHandler;
import extentreports.ExtentTestManager;

import java.lang.reflect.Method;
import java.util.Map;

public class CPQ_Test extends BaseTest {
    private final ThreadLocal<LaunchSite> launchSite = new ThreadLocal<>();
    
    private final ThreadLocal<PortalLoginPage> loginPage = new ThreadLocal<>();
    private final ThreadLocal<SwitchProperty> switchproperty= new ThreadLocal<>();
    private final ThreadLocal<Login_Test> login = new ThreadLocal<>();
    private final ThreadLocal<CPQ_Page> addProduct = new ThreadLocal<>();


    @BeforeMethod(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        launchSite.set(new LaunchSite(getDriver()));
        loginPage.set(new PortalLoginPage(getDriver()));
        switchproperty.set(new SwitchProperty(getDriver()));
        addProduct.set(new CPQ_Page(getDriver()));
        login.set(new Login_Test(getDriver()));
    }

    /**
     * @Test_Method_Description : 
     * @Modified_By : Sachin Kumar
     * @Modified_Date : 
     */
    @Test 
    public void CPQTEST(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "WebProliFic Login");
        try {

            // Test steps
            Map<String,String> name =login.get().LoginCheck(m);
//            launchSite.get().LaunchPortal(url);
//            loginPage.get().LogInToPortal(ChainID, PropertyID, Username);
            switchproperty.get().SwitchProperty();
            addProduct.get().AddProduct(name.get("user"));
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", false, e);
        }
    }

//    @AfterMethod(alwaysRun = true)
//    public void closeDriver() {
//        launchSite.remove();
//      
//        closeBrowser();
//    }
}


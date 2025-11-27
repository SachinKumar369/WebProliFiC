package loginTest;

import com.aventstack.extentreports.Status;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CommonPages.LaunchSite;
import pages.CommonPages.Login;
import pages.CommonPages.PortalLoginPage;
import pages.T0419_Pages.SwitchProperty;
import baselibrary.BaseTest;
import extentreports.ExtentTestManager;

import java.lang.reflect.Method;
import java.util.Map;

public class SwitchProperty_Test extends BaseTest {
    private final ThreadLocal<LaunchSite> launchSite = new ThreadLocal<>();

    private final ThreadLocal<PortalLoginPage> loginPage = new ThreadLocal<>();
    private final ThreadLocal<SwitchProperty> switchproperty = new ThreadLocal<>();
    private final ThreadLocal<Login_Test> login = new ThreadLocal<>();


    @BeforeMethod(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        launchSite.set(new LaunchSite(getDriver()));
        loginPage.set(new PortalLoginPage(getDriver()));
        login.set(new Login_Test(getDriver()));
        switchproperty.set(new SwitchProperty(getDriver()));
        // logintest.set(new Login_Page(getDriver()));
    }

    /**
     * @Test_Method_Description :
     * @Modified_By :
     * @Modified_Date :
     */
    @Test
    public void SwitchProperty(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " Portal Login");
        try {


            login.get().LoginCheck(m);

            switchproperty.get().SwitchProperty();


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login",
                    true, e);
        }
    }

//    @AfterMethod(alwaysRun = true)
//    public void closeDriver() {
//        launchSite.remove();
//       
//        closeBrowser();
//    }
}

package POTest;

import com.aventstack.extentreports.Status;
import loginTest.Login_Test;
import org.testng.annotations.*;
import pages.CommonPages.LaunchSite;
import pages.CommonPages.PortalLoginPage;
import pages.POPages.RPPOPage;
import pages.T0419_Pages.SwitchProperty;
import baselibrary.BaseTest;
import extentreports.ExtentTestManager;
import java.lang.reflect.Method;

public class RPPOTest extends BaseTest {
    private final ThreadLocal<LaunchSite> launchSite = new ThreadLocal<>();
    private final ThreadLocal<PortalLoginPage> loginPage = new ThreadLocal<>();
    private final ThreadLocal<SwitchProperty> switchproperty = new ThreadLocal<>();
    private final ThreadLocal<Login_Test> login = new ThreadLocal<>();
    private final ThreadLocal<RPPOPage> RPPO = new ThreadLocal<>();

    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        if (BaseTest.getDriver() == null) {
            setDriver();
        }
        launchSite.set(new LaunchSite(getDriver()));
        loginPage.set(new PortalLoginPage(getDriver()));
        login.set(new Login_Test(getDriver()));
        switchproperty.set(new SwitchProperty(getDriver()));
        RPPO.set(new RPPOPage(getDriver()));
    }

    @Test(priority = 1)
    public void Login(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "Portal Login");
        try {
            login.get().LoginCheck(m);
            switchproperty.get().SwitchProperty();
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", true, e);
            e.printStackTrace(); // Added for debugging
        }
    }

    @Test(priority = 2)
    public void EditReq(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "PO Creation");
        try {
            RPPO.get().POCreate("Username");
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("EditReq", Status.FAIL, "Exception found in EditReq", true, e);
            e.printStackTrace(); // Added for debugging
        }
    }

    @AfterClass(alwaysRun = false)
    public void cleanUp() {
        launchSite.remove();
        loginPage.remove();
        login.remove();
        switchproperty.remove();
        RPPO.remove();
        quitBrowser(); // Ensure the browser is closed
    }
}








//package POTest;
//
//import com.aventstack.extentreports.Status;
//import loginTest.Login_Test;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import pages.CommonPages.LaunchSite;
//import pages.CommonPages.PortalLoginPage;
//import pages.POPages.RPPOPage;
//import pages.T0419_Pages.SwitchProperty;
//import baselibrary.BaseTest;
//import extentreports.ExtentTestManager;
//import java.lang.reflect.Method;
//
//public class RPPOTest extends BaseTest {
//    private final ThreadLocal<LaunchSite> launchSite = new ThreadLocal<>();
//
//    private final ThreadLocal<PortalLoginPage> loginPage = new ThreadLocal<>();
//    private final ThreadLocal<SwitchProperty> switchproperty = new ThreadLocal<>();
//    private final ThreadLocal<Login_Test> login = new ThreadLocal<>();
//    private final ThreadLocal<RPPOPage> RPPO = new ThreadLocal<>();
//
//
//
//    @BeforeMethod(alwaysRun = true)
//    public void initializeDriver() {
//        if (BaseTest.getDriver() == null) {
//            BaseTest.setDriver(); // Initializes the WebDriver
//        }
//    }
//
//
//
//    @BeforeClass(alwaysRun = true)
//    public void setUpBrowser() {
//        setDriver();
//        launchSite.set(new LaunchSite(getDriver()));
//        loginPage.set(new PortalLoginPage(getDriver()));
//        login.set(new Login_Test(getDriver()));
//        switchproperty.set(new SwitchProperty(getDriver()));
//
//        RPPO.set(new RPPOPage(getDriver()));
//        // logintest.set(new Login_Page(getDriver()));
//    }
//
//    /**
//     * @Test_Method_Description : For PO Creation
//     * @Modified_By : Sachin Kumar
//     * @Modified_Date : 10/12/2024
//     */
//    @Test(priority = 1)
//    public void Login(Method m) {
//        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " Portal Login");
//
//        try {
//            login.get().LoginCheck(m);
//            switchproperty.get().SwitchProperty();
//
//
//        } catch (Exception e) {
//            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login",
//                    true, e);
//        }
//    }
//
//    @Test(priority = 2)
//    public void EditReq(Method m) {
//        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " PO Creation");
//        try {
//
//            RPPO.get().POCreate("Username");
//
//
//
//
//        } catch (Exception e) {
//            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login",
//                    true, e);
//        }
//    }
//
//
//
////    @AfterMethod(alwaysRun = true)
////    public void closeDriver() {
////        launchSite.remove();
////
////        closeBrowser();
////    }
//}

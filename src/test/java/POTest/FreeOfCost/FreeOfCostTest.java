package POTest.FreeOfCost;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import datahandlers.ConfigProperties;
import datahandlers.ExcelHandler;
import extentreports.ExtentTestManager;
import loginTest.Login_Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CommonPages.LaunchSite;
import pages.CommonPages.PortalLoginPage;
import pages.POPages.ApprovePO.ApprovePage;
import pages.POPages.WithoutRQ.CommonAction;
import pages.POPages.WithoutRQ.RPPage;
import pages.POPages.WithoutRQ.RejectPage;
import pages.T0419_Pages.SwitchProperty;

import java.lang.reflect.Method;

public class FreeOfCostTest extends BaseTest {
    private final ThreadLocal<LaunchSite> launchSite = new ThreadLocal<>();
    private final ThreadLocal<PortalLoginPage> loginPage = new ThreadLocal<>();
    private final ThreadLocal<SwitchProperty> switchproperty = new ThreadLocal<>();
    private final ThreadLocal<Login_Test> login = new ThreadLocal<>();
    private final ThreadLocal<RPPage> RPPageThread = new ThreadLocal<>();
    private final ThreadLocal<CommonAction> CommonActionThread = new ThreadLocal<>();
    private final ThreadLocal<RejectPage>  RejectPageThread = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        launchSite.set(new LaunchSite(getDriver()));
        loginPage.set(new PortalLoginPage(getDriver()));
        switchproperty.set(new SwitchProperty(getDriver()));
        login.set(new Login_Test(getDriver()));
        RPPageThread.set(new RPPage(getDriver()));
        CommonActionThread.set(new CommonAction(getDriver()));
        RejectPageThread.set(new RejectPage(getDriver()));
    }

    @Test(priority = 1)
    public void PORejection(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "WebProliFic Login");
        try {
            String environment = System.getProperty("env", "new");
            String url = ConfigProperties.getURL(environment, "admin");
            String[] credentials = ExcelHandler.getCredentialsForEnvironment(environment);

            if (credentials == null || credentials.length < 3) {
                throw new IllegalArgumentException("Credentials not found for the environment: " + environment);
            }

            String ChainID = credentials[0];
            String PropertyID = credentials[1];
            String Username = credentials[2];
            String Password = credentials.length > 3 ? credentials[3] : "";

            launchSite.get().LaunchPortal(url);
            loginPage.get().LogInToPortal(ChainID, PropertyID, Username);
            switchproperty.get().Switch363();
            RPPageThread.get().POCreation();
            CommonActionThread.get().EnterItemsFreeofCost();
            CommonActionThread.get().SaveDetailsinExcel();
            //CommonActionThread.get().Reject();


            //CommonActionThread.get().SaveRQNumber();

            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation ", Status.PASS, "PO Create Sucessfully", true);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", false, e);
        }
    }



}

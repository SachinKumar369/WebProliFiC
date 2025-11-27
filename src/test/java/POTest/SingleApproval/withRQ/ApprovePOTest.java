package POTest.SingleApproval.withRQ;

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
import pages.POPages.WithRQ.RPPage;
import pages.POPages.WithRQ.RQCreate;
import pages.POPages.WithoutRQ.CommonAction;
import pages.T0419_Pages.SwitchProperty;

import java.lang.reflect.Method;

public class ApprovePOTest extends BaseTest {
    private final ThreadLocal<LaunchSite> launchSite = new ThreadLocal<>();
    private final ThreadLocal<PortalLoginPage> loginPage = new ThreadLocal<>();
    private final ThreadLocal<SwitchProperty> switchproperty = new ThreadLocal<>();
    private final ThreadLocal<RQCreate> RQCreateThread = new ThreadLocal<>();
    private final ThreadLocal<RPPage> RPPageThread = new ThreadLocal<>();
    private final ThreadLocal<Login_Test> login = new ThreadLocal<>();
    private final ThreadLocal<CommonAction> CommonActionThread = new ThreadLocal<>();
    private final ThreadLocal<ApprovePage>  ApprovePoThread = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        launchSite.set(new LaunchSite(getDriver()));
        loginPage.set(new PortalLoginPage(getDriver()));
        switchproperty.set(new SwitchProperty(getDriver()));
        RQCreateThread.set(new RQCreate(getDriver()));
        login.set(new Login_Test(getDriver()));
        RPPageThread.set(new RPPage(getDriver()));
        CommonActionThread.set(new CommonAction(getDriver()));
        ApprovePoThread.set(new ApprovePage(getDriver()));
    }

    @Test(priority = 1)
    public void Requisition(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "WebProliFic Login");
        try {
            String environment = System.getProperty("env", "prod");
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
            switchproperty.get().SwitchProperty();
            RQCreateThread.get().AddProduct();
            RPPageThread.get().CPPOCreation();
            RPPageThread.get().SelectPO();
           // CommonActionThread.get().EnterItems();
            //CommonActionThread.get().SaveDetailsinExcel();
            CommonActionThread.get().SaveRQNumber();

            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation ", Status.PASS, "PO Create Sucessfully", true);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", false, e);
        }
    }

    @Test(priority = 2)
    public void RequestApprove(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "WebProliFic Login");
        try {
            String environment = System.getProperty("env", "Approve");
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
            switchproperty.get().SwitchProperty();
            ApprovePoThread.get().ApprovePOPage();


            ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation ", Status.PASS, "PO Create Sucessfully", true);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", false, e);
        }
    }


}

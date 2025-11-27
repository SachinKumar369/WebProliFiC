package reqApprove_Test;





import com.aventstack.extentreports.Status;

import loginTest.Login_Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CommonPages.LaunchSite;
import pages.CommonPages.PortalLoginPage;
import pages.T0419_Pages.*;
import reqApproval.*;
import baselibrary.BaseTest;
import datahandlers.ConfigProperties;
import datahandlers.ExcelHandler;
import extentreports.ExtentTestManager;

import java.io.IOException;
import java.lang.reflect.Method;

public class ReqRejectTest1 extends BaseTest {
    private final ThreadLocal<LaunchSite> launchSite = new ThreadLocal<>();

    private final ThreadLocal<PortalLoginPage> loginPage = new ThreadLocal<>();
    private final ThreadLocal<SwitchProperty> switchproperty= new ThreadLocal<>();
    private final ThreadLocal<ReqCreate> addProduct = new ThreadLocal<>();
    private final ThreadLocal<ReqRejectPage> Reject = new ThreadLocal<>();
    private final ThreadLocal<Login_Test> login = new ThreadLocal<>();
    private final ThreadLocal<ApprovarLogin> approvarLogin = new ThreadLocal<>();
    private final ThreadLocal<Switch2Property> switch2property= new ThreadLocal<>();



    @BeforeMethod(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        launchSite.set(new LaunchSite(getDriver()));
        loginPage.set(new PortalLoginPage(getDriver()));
        switchproperty.set(new SwitchProperty(getDriver()));
        addProduct.set(new ReqCreate(getDriver()));
        Reject.set(new ReqRejectPage(getDriver()));
        login.set(new Login_Test(getDriver()));
        switch2property.set(new Switch2Property(getDriver()));
        //login.set(new ApprovarLogin(getDriver()));
        approvarLogin.set(new ApprovarLogin(getDriver()));
    }

    /**
     * @Test_Method_Description :
     * @Modified_By : Sachin Kumar
     * @Modified_Date :
     */
    @Test (priority = 1)
    public void Requisition(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "WebProliFic Login");
        try {
            // Load URL based on environment
            String url = ConfigProperties.getURL(environment, "admin");

            String ChainID = null;
            String PropertyID = null;
            String Username = null;
            String Password = null;
            String[] credentials = null;

            // Get environment variable or set default to "prod"
            String environment = System.getProperty("env", "prod");

            // Validate environment and retrieve credentials
            switch (environment.toLowerCase()) {
                case "dev":
                case "test":
                case "prod":
                    credentials = ExcelHandler.getCredentialsForEnvironment(environment);
                    if (credentials != null && credentials.length >= 3) {
                        ChainID = credentials[0];
                        PropertyID = credentials[1];
                        Username = credentials[2];
                        Password = credentials.length > 3 ? credentials[3] : "";  // Optional Password field
                    } else {
                        throw new IllegalArgumentException("Credentials not found for the environment: " + environment);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown environment: " + environment);
            }

            // Now you have ChainID, PropertyID, Username, and Password for the specified environment


            // Test steps
            launchSite.get().LaunchPortal(url);
            loginPage.get().LogInToPortal(ChainID, PropertyID, Username);
            switchproperty.get().SwitchProperty();
            addProduct.get().AddProduct(Username);
            //approvarLogin.get().setUpBrowser();
            //approvarLogin.get().RequestApprove(m);
            //switch2property.get().Switch2Property();
            // approve.get().Approve();

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", false, e);
        }


    }

    @Test(priority = 2)
    public void RequestApprove(Method m) throws IOException {
        // Load URL based on environment
        String url = ConfigProperties.getURL(environment, "admin");

        String ChainID = null;
        String PropertyID = null;
        String Username = null;
        String Password = null;
        String[] credentials = null;

        // Get environment variable or set default to "prod"
        String environment = System.getProperty("env", "Approve");

        // Validate environment and retrieve credentials
        switch (environment.toLowerCase()) {
            case "dev":
            case "test":
            case "prod":
            case "approve":


                credentials = ExcelHandler.getCredentialsForEnvironment(environment);
                if (credentials != null && credentials.length >= 3) {
                    ChainID = credentials[0];
                    PropertyID = credentials[1];
                    Username = credentials[2];
                    Password = credentials.length > 3 ? credentials[3] : "";  // Optional Password field
                } else {
                    throw new IllegalArgumentException("Credentials not found for the environment: " + environment);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown environment: " + environment);
        }

        // Now you have ChainID, PropertyID, Username, and Password for the specified environment


        // Test steps
//        LaunchSite.LaunchPortal(url);
//        PortalLoginPage.LogInToPortal(ChainID, PropertyID, Username);

        //launchSite.get().LaunchPortal(url);
        //loginPage.get().LogInToPortal(ChainID, PropertyID, Username);
        launchSite.get().LaunchPortal(url);
        loginPage.get().LogInToPortal(ChainID, PropertyID, Username);
        switch2property.get().Switch2Property();
//        addProduct.get().AddProduct(Username);
//
//        switchproperty.get().SwitchProperty();
        Reject.get().Approve();
    }


//    @AfterMethod(alwaysRun = true)
//    public void closeDriver() {
//        launchSite.remove();
////
//        closeBrowser();
//    }
}


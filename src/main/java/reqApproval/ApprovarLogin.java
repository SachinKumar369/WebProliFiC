package reqApproval;

import baselibrary.BaseTest;
import datahandlers.ConfigProperties;
import datahandlers.ExcelHandler;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CommonPages.LaunchSite;
import pages.CommonPages.PortalLoginPage;
import pages.T0419_Pages.SwitchProperty;

import java.io.IOException;
import java.lang.reflect.Method;

public class ApprovarLogin extends BaseTest {

    private final ThreadLocal<LaunchSite> launchSite = new ThreadLocal<>();

    private final ThreadLocal<PortalLoginPage> loginPage = new ThreadLocal<>();
    private final ThreadLocal<SwitchProperty> switchproperty= new ThreadLocal<>();
    private final ThreadLocal<ReqCreate> addProduct = new ThreadLocal<>();
    private final ThreadLocal<ReqApprovePage> approve = new ThreadLocal<>();

    public ApprovarLogin(WebDriver driver) {
        super();
    }
    //  private final ThreadLocal<Login_Test> login = new ThreadLocal<>();


   // @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        launchSite.set(new LaunchSite(getDriver()));
        loginPage.set(new PortalLoginPage(getDriver()));
        switchproperty.set(new SwitchProperty(getDriver()));
        addProduct.set(new ReqCreate(getDriver()));
        approve.set(new ReqApprovePage(getDriver()));
        //login.set(new Login_Test(getDriver()));
    }

   // @Test(priority = 2)
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
//        switchproperty.get().SwitchProperty();
//        addProduct.get().AddProduct(Username);
//
//        switchproperty.get().SwitchProperty();
//    	approve.get().Approve();
    }


//    @AfterMethod(alwaysRun = true)
//    public void closeDriver() {
//        launchSite.remove();
//
//        closeBrowser();
//    }
}




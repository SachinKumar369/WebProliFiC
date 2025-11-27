package RQ.WithoutTemplate;


import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import datahandlers.ConfigProperties;
import datahandlers.ExcelHandler;
import extentreports.ExtentTestManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CommonPages.LaunchSite;
import pages.CommonPages.PortalLoginPage;
import pages.T0419_Pages.SQ_Page;
import pages.T0419_Pages.SwitchProperty;

import java.lang.reflect.Method;

public class SQ_Test extends BaseTest {
    private final ThreadLocal<LaunchSite> launchSite = new ThreadLocal<>();
    
    private final ThreadLocal<PortalLoginPage> loginPage = new ThreadLocal<>();
    private final ThreadLocal<SwitchProperty> switchproperty= new ThreadLocal<>();
    private final ThreadLocal<SQ_Page> addProduct = new ThreadLocal<>();


    @BeforeMethod(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        launchSite.set(new LaunchSite(getDriver()));
        loginPage.set(new PortalLoginPage(getDriver()));
        switchproperty.set(new SwitchProperty(getDriver()));
        addProduct.set(new SQ_Page(getDriver()));
    }

    /**
     * @Test_Method_Description : 
     * @Modified_By : Sachin Kumar
     * @Modified_Date : 
     */
    @Test 
    public void D2C(Method m) {
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


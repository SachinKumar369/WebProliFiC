package RQTest.PackItem;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import datahandlers.ConfigProperties;
import datahandlers.ExcelHandler;
import extentreports.ExtentTestManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CommonPages.LaunchSite;
import pages.CommonPages.PortalLoginPage;
import pages.RQ.PackItem;
import pages.RQ.RQ_Page;
import pages.T0419_Pages.SwitchProperty;

import java.lang.reflect.Method;

public class PackItemTest extends BaseTest {
    private final ThreadLocal<LaunchSite> launchSite = new ThreadLocal<>();
    private final ThreadLocal<PortalLoginPage> loginPage = new ThreadLocal<>();
    private final ThreadLocal<SwitchProperty> switchproperty = new ThreadLocal<>();
    private final ThreadLocal<PackItem> addProduct = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        launchSite.set(new LaunchSite(getDriver()));
        loginPage.set(new PortalLoginPage(getDriver()));
        switchproperty.set(new SwitchProperty(getDriver()));
        addProduct.set(new PackItem(getDriver()));
    }

    /**
     * @Test_Method_Description : Test to create a requisition with specified type
     * @Modified_By : Sachin Kumar
     * @Modified_Date : 23/05/25
     */
    @Test
    public void Requisition(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "WebProliFic Login");
        try {
            // Load URL based on environment
            String url = ConfigProperties.getURL(environment, "admin");
            String requisitionType = "PQ"; // Specify the requisition type here

            String ChainID = null;
            String PropertyID = null;
            String Username = null;
            String Password = null;
            String[] credentials = null;

            // Get environment variable or set default to "prod"
            String environment = System.getProperty("env", "vat");

            // Validate environment and retrieve credentials
            switch (environment.toLowerCase()) {
                case "dev":
                case "test":
                case "vat":
                    credentials = ExcelHandler.getCredentialsForEnvironment(environment);
                    if (credentials != null && credentials.length >= 3) {
                        ChainID = credentials[0];
                        PropertyID = credentials[1];
                        Username = credentials[2];
                        Password = credentials.length > 3 ? credentials[3] : "";
                    } else {
                        throw new IllegalArgumentException("Credentials not found for the environment: " + environment);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown environment: " + environment);
            }

            // Test steps
            launchSite.get().LaunchPortal(url);
            loginPage.get().LogInToPortal(ChainID, PropertyID, Username);
            switchproperty.get().SwitchBHOTL();
            addProduct.get().Pack(requisitionType);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", false, e);
        }
    }

//    @AfterMethod(alwaysRun = true)
//    public void closeDriver() {
//        launchSite.remove();
//        loginPage.remove();
//        switchproperty.remove();
//        addProduct.remove();
//        closeBrowser();
//    }
}
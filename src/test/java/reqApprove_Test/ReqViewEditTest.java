package reqApprove_Test;





import com.aventstack.extentreports.Status;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CommonPages.LaunchSite;
import pages.CommonPages.PortalLoginPage;
import pages.T0419_Pages.CPQ_Page;
import pages.T0419_Pages.CQ_Page;
import pages.T0419_Pages.DQ_Page;
import pages.T0419_Pages.IQ_Page;
import pages.T0419_Pages.KSTRequisition_Page;
import pages.T0419_Pages.SwitchProperty;
import reqApproval.PQApproval_Page;
import reqApproval.ReqApprovePage;
import reqApproval.ReqCreate;
import reqApproval.ReqHoldPage;
import reqApproval.ReqRejectPage;
import reqApproval.ReqViewEditPage;
import reqApproval.Req_Aooroval;
import reqApproval.IQApprove;
import baselibrary.BaseTest;
import datahandlers.ConfigProperties;
import datahandlers.ExcelHandler;
import extentreports.ExtentTestManager;

import java.lang.reflect.Method;

public class ReqViewEditTest extends BaseTest {
    private final ThreadLocal<LaunchSite> launchSite = new ThreadLocal<>();
    
    private final ThreadLocal<PortalLoginPage> loginPage = new ThreadLocal<>();
    private final ThreadLocal<SwitchProperty> switchproperty= new ThreadLocal<>();
    private final ThreadLocal<ReqCreate> addProduct = new ThreadLocal<>();
    private final ThreadLocal<ReqViewEditPage> ViewEdit = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        launchSite.set(new LaunchSite(getDriver()));
        loginPage.set(new PortalLoginPage(getDriver()));
        switchproperty.set(new SwitchProperty(getDriver()));
        addProduct.set(new ReqCreate(getDriver()));
        ViewEdit.set(new ReqViewEditPage(getDriver()));
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
            ViewEdit.get().ViewEdit(Username);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", false, e);
        }
        
        
    }
    
//    @Test(priority = 2)
//    public void ClickokBtn() {
//    	approve.get().Approve();
//    }
    

//    @AfterMethod(alwaysRun = true)
//    public void closeDriver() {
//        launchSite.remove();
//      
//        closeBrowser();
//    }
}


package tempReqTest;

import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CommonPages.LaunchSite;
import pages.CommonPages.PortalLoginPage;
import pages.T0419_Pages.AddProduct;

import pages.T0419_Pages.Requisition_Page;
import reqTemplatePage.ReqExcel;
import reqTemplatePage.CPQTempReq_Page;
import reqTemplatePage.KQTempReq_Page;
import baselibrary.BaseTest;
import datahandlers.ConfigProperties;
import datahandlers.ExcelHandler;
import editreq.EditCPQ_Page;
import editreq.EditReq_Page;
import editreq.PQEdit_Page;
import extentreports.ExtentTestManager;
import java.lang.reflect.Method;

public class CPQTempReq_Test extends BaseTest {
    private final ThreadLocal<LaunchSite> launchSite = new ThreadLocal<>();
    
    private final ThreadLocal<PortalLoginPage> loginPage = new ThreadLocal<>();
    private final ThreadLocal<CPQTempReq_Page> templatereq = new ThreadLocal<>();
    //private final ThreadLocal<Requisition_Page> addProduct = new ThreadLocal<>();
   // private final ThreadLocal<PQEdit_Page> editProduct = new ThreadLocal<>();



    @BeforeMethod(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        launchSite.set(new LaunchSite(getDriver()));
        loginPage.set(new PortalLoginPage(getDriver()));
        templatereq.set(new CPQTempReq_Page(getDriver()));
      
       // addProduct.set(new Requisition_Page(getDriver()));
       // editProduct.set(new PQEdit_Page(getDriver()));
    }

    /**
     * @Test_Method_Description : 
     * @Modified_By : 
     * @Modified_Date : 
     */
    @Test
    public void template(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " WebProlific Login");
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
            templatereq.get().AddProductUsingTemplate(Username);
           // addProduct.get().AddProduct();
           // editProduct.get().EditProduct();
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


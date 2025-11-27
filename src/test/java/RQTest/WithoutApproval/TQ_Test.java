package RQTest.WithoutApproval;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import datahandlers.ConfigProperties;
import datahandlers.ExcelHandler;
import extentreports.ExtentTestManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CommonPages.LaunchSite;
import pages.CommonPages.PortalLoginPage;
import pages.RQ.PdfDataValidator;
import pages.RQ.PdfDataValidator1; // Import the PdfDataValidator class
import pages.RQ.RQ_Page;
import pages.T0419_Pages.SwitchProperty;

import java.lang.reflect.Method;

public class TQ_Test extends BaseTest {
    private final ThreadLocal<LaunchSite> launchSite = new ThreadLocal<>();
    private final ThreadLocal<PortalLoginPage> loginPage = new ThreadLocal<>();
    private final ThreadLocal<SwitchProperty> switchproperty = new ThreadLocal<>();
    private final ThreadLocal<RQ_Page> addProduct = new ThreadLocal<>();
    private final ThreadLocal<PdfDataValidator> pdfValidator = new ThreadLocal<>(); // Add PdfDataValidator

    @BeforeMethod(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        launchSite.set(new LaunchSite(getDriver()));
        loginPage.set(new PortalLoginPage(getDriver()));
        switchproperty.set(new SwitchProperty(getDriver()));
        addProduct.set(new RQ_Page(getDriver()));
        pdfValidator.set(new PdfDataValidator(getDriver())); // Initialize PdfDataValidator
    }

    /**
     * @Test_Method_Description : Test to create a requisition with specified type and validate the downloaded PDF
     * @Modified_By : Sachin Kumar
     * @Modified_Date : 24/05/25
     */
    @Test
    public void Requisition(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "WebProliFic Login and PDF Validation");
        try {
            // Load URL based on environment
            String url = ConfigProperties.getURL(environment, "admin");
            String requisitionType = "TQ"; // Specify the requisition type here

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
            switchproperty.get().SwitchProperty();
            addProduct.get().IssueRQ(Username, requisitionType);

            addProduct.get().Print();
            // Wait for PDF to download (adjust wait time as needed)
            Thread.sleep(7000); // Consider using a more robust wait mechanism (e.g., file watcher)

            // Validate the downloaded PDF against the webpage
            ExtentTestManager.getTest().log(Status.INFO, "Validating PDF data against webpage data...");
            pdfValidator.get().validatePdfAgainstWebpage();
            ExtentTestManager.getTest().log(Status.PASS, "PDF data validation successful");

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Requisition and PDF Validation", Status.FAIL, "Exception found: " + e.getMessage(), false, e);
        }
    }

//    @AfterMethod(alwaysRun = true)
//    public void closeDriver() {
//        launchSite.remove();
//        loginPage.remove();
//        switchproperty.remove();
//        addProduct.remove();
//        pdfValidator.remove(); // Clean up PdfDataValidator
//        closeBrowser();
//    }
}
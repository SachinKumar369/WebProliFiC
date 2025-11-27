package loginTest;

import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CommonPages.LaunchSite;
import pages.CommonPages.Login;
import pages.CommonPages.PortalLoginPage;
import pages.T0419_Pages.AddProduct;
//import pages.T0419_Pages.PQEdit_Page;
import pages.T0419_Pages.Requisition_Page;
import baselibrary.BaseTest;
import datahandlers.ConfigProperties;
import datahandlers.ExcelHandler;
import editreq.EditCPQ_Page;
import editreq.EditReq_Page;
import editreq.PQEdit_Page;
import extentreports.ExtentTestManager;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Login_Test extends BaseTest {

    public Login_Test(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    /**
     * @Test_Method_Description :
     * @Modified_By :
     * @Modified_Date :
     */
    @Test
    public Map<String, String> LoginCheck(Method m) {
        Map<String, String> data = new HashMap<>();
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " Portal Login");
        try {
            // Load URL based on environment
            String url = ConfigProperties.getURL(environment, "admin");

            String ChainID = null;
            String PropertyID = null;
            String Username = null;
            String Password = null;
            String[] credentials = null;

            // Get environment variable or set default to "prod"
            String environment = System.getProperty("env", "test");

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
            LaunchSite.LaunchPortal(url);
            PortalLoginPage.LogInToPortal(ChainID, PropertyID, Username);
            data.put("user", Username);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("LoginCheck", Status.FAIL, "Exception found in Login", true, e);
        }
        return data;
    }

}


package pages.Login;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import extentreports.ExtentTestManager;
import utils.DynamicWait;
import utils.Utilities;
import datahandlers.ConfigProperties;
import datahandlers.ExcelHandler;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Login extends BaseTest {

    private WebDriver driver;

    // Constructor
    public Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    @FindAll({
            @FindBy(how = How.XPATH, using = "//input[@id='txtChainID']"),
            @FindBy(how = How.ID, using = "txtChainID")
    })
    private static List<WebElement> ChainID;

    @FindAll({
            @FindBy(how = How.XPATH, using = "//input[@id='txtUnitID']"),
            @FindBy(how = How.ID, using = "txtUnitID")
    })
    private static List<WebElement> PropertyId;

    @FindAll({
            @FindBy(how = How.XPATH, using = "//input[@id='txtLogIN']"),
            @FindBy(how = How.ID, using = "txtLogIN")
    })
    private static List<WebElement> UserID;

    @FindAll({
            @FindBy(how = How.XPATH, using = "//input[@id='lblEnterPassword']"),
            @FindBy(how = How.ID, using = "lblEnterPassword")
    })
    private List<WebElement> Password;

    @FindAll({
            @FindBy(how = How.XPATH, using = "//span[@id='lblLogin_span']"),
            @FindBy(how = How.ID, using = "lblLogin_span")
    })
    private static List<WebElement> Login;

    @FindBy(id = "lblStatusDesc")
    private static WebElement AlreadyLoginPopup;

    @FindBy(id = "btnYes")
    private static WebElement YesLogin;

    @FindBy(id = "btnOK")
    private WebElement OKBtn;

    // Method to launch the portal
    public void launchPortal(String url) {
        try {
            driver.navigate().to(url);
            driver.manage().deleteAllCookies();
            Utilities.WaitForPageLoad(driver);
            ExtentTestManager.createAssertTestStepWithScreenshot("Launch Portal", Status.INFO, "Launched portal - " + url, false);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("LaunchPortal", Status.FAIL, "Exception found in Method - LaunchPortal", true, e);
        }
    }

    // Method to log in to the portal
    public void logInToPortal(String chain, String property, String user) {
        try {
            Utilities.WaitTillElementDisplayed(driver, ChainID.get(0));
            Utilities.SendKeys(driver, ChainID.get(0), chain);
            Utilities.SendKeys(driver, PropertyId.get(0), property);
            Utilities.SendKeys(driver, UserID.get(0), user);

            String currentHandles = driver.getWindowHandle();
            System.out.println(currentHandles);




            Utilities.Click(driver, Login.get(0));
            DynamicWait.smallWait();

            if (isIframePresent("iframeGridDialog")) {
                driver.switchTo().frame("iframeGridDialog");
                if (isElementPresent(AlreadyLoginPopup)) {
                    Utilities.Click(driver, YesLogin);
                    DynamicWait.smallWait();
                }
            }
            // for liceance expire
            try {
                driver.switchTo().frame("iframeGridDialog");
                if (isElementPresent(AlreadyLoginPopup)) {
                    Utilities.Click(driver, OKBtn);
                    DynamicWait.smallWait();
                }
            } catch (Exception e){

            }

            Set<String> handle = driver.getWindowHandles();
            for (String actual : handle) {
                if (!actual.equalsIgnoreCase(currentHandles)) {
                    driver.switchTo().window(actual);
                }
            }

            DynamicWait.smallWait();
            String url = driver.getCurrentUrl();
            if (url.contains("prologic")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Verify navigation", Status.PASS, "Navigation on Product Page working fine", true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Verify navigation", Status.WARNING, "Issue on navigation on Product Page", true);
            }

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("LogInToPortal", Status.FAIL, "Exception found in Method - LogInToPortal", true, e);
        }
    }

    // Helper method to check if an iframe is present
    private boolean isIframePresent(String iframeName) {
        try {
            driver.switchTo().frame(iframeName);
            driver.switchTo().defaultContent(); // Reset back to main content
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Helper method to check if an element is present within the iframe
    private boolean isElementPresent(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }


    public Map<String, String> loginAndLaunchTest(Method m) {
        Map<String, String> data = new HashMap<>();
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "Portal Login and Launch");

        try {
            // Load URL based on environment
            String environment = System.getProperty("env", "new");
            String url = ConfigProperties.getURL(environment, "admin");

            String ChainID = null;
            String PropertyID = null;
            String Username = null;
            String Password = null;
            String[] credentials = null;

            // Retrieve credentials based on environment
            switch (environment.toLowerCase()) {
                case "dev":
                case "test":
                case "prod":
                case "new":
                    credentials = ExcelHandler.getCredentialsForEnvironment(environment);
                    if (credentials != null && credentials.length >= 3) {
                        ChainID = credentials[0];
                        PropertyID = credentials[1];
                        Username = credentials[2];
                        Password = credentials.length > 3 ? credentials[3] : "";
                    } else {
                        throw new IllegalArgumentException("Credentials not found for environment: " + environment);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown environment: " + environment);
            }

            // Launch and log in to the portal
            launchPortal(url);
            logInToPortal(ChainID, PropertyID, Username);
            data.put("user", Username);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("loginAndLaunchTest", Status.FAIL, "Exception found in Login and Launch", true, e);
        }
        return data;
    }


    public Map<String, String> loginAndLaunchTestL2(Method m) {
        Map<String, String> data = new HashMap<>();
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "Portal Login and Launch");

        try {
            // Load URL based on environment
            String environment = System.getProperty("env", "l2");
            String url = ConfigProperties.getURL(environment, "admin");

            String ChainID = null;
            String PropertyID = null;
            String Username = null;
            String Password = null;
            String[] credentials = null;

            // Retrieve credentials based on environment
            switch (environment.toLowerCase()) {
                case "dev":
                case "test":
                case "prod":
                case "l2":
                    credentials = ExcelHandler.getCredentialsForEnvironment(environment);
                    if (credentials != null && credentials.length >= 3) {
                        ChainID = credentials[0];
                        PropertyID = credentials[1];
                        Username = credentials[2];
                        Password = credentials.length > 3 ? credentials[3] : "";
                    } else {
                        throw new IllegalArgumentException("Credentials not found for environment: " + environment);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown environment: " + environment);
            }

            // Launch and log in to the portal
            launchPortal(url);
            logInToPortal(ChainID, PropertyID, Username);
            data.put("user", Username);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("loginAndLaunchTest", Status.FAIL, "Exception found in Login and Launch", true, e);
        }
        return data;
    }

    public Map<String, String> loginAndLaunchTestSRV(Method m) {
        Map<String, String> data = new HashMap<>();
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "Portal Login and Launch");

        try {
            // Load URL based on environment
            String environment = System.getProperty("env", "l2");
            String url = ConfigProperties.getURL(environment, "admin");

            String ChainID = null;
            String PropertyID = null;
            String Username = null;
            String Password = null;
            String[] credentials = null;

            // Retrieve credentials based on environment
            switch (environment.toLowerCase()) {
                case "dev":
                case "test":
                case "prod":
                case "l2":
                    credentials = ExcelHandler.getCredentialsForEnvironment(environment);
                    if (credentials != null && credentials.length >= 3) {
                        ChainID = credentials[0];
                        PropertyID = credentials[1];
                        Username = credentials[2];
                        Password = credentials.length > 3 ? credentials[3] : "";
                    } else {
                        throw new IllegalArgumentException("Credentials not found for environment: " + environment);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown environment: " + environment);
            }

            // Launch and log in to the portal
            launchPortal(url);
            logInToPortal(ChainID, PropertyID, Username);
            data.put("user", Username);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("loginAndLaunchTest", Status.FAIL, "Exception found in Login and Launch", true, e);
        }
        return data;
    }


    public Map<String, String> loginAndLaunchTest346(Method m) {
        Map<String, String> data = new HashMap<>();
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "Portal Login and Launch");

        try {
            // Load URL based on environment
            String environment = System.getProperty("env", "346");
            String url = ConfigProperties.getURL(environment, "admin");

            String ChainID = null;
            String PropertyID = null;
            String Username = null;
            String Password = null;
            String[] credentials = null;

            // Retrieve credentials based on environment
            switch (environment.toLowerCase()) {
                case "dev":
                case "test":
                case "prod":
                case "346":
                    credentials = ExcelHandler.getCredentialsForEnvironment(environment);
                    if (credentials != null && credentials.length >= 3) {
                        ChainID = credentials[0];
                        PropertyID = credentials[1];
                        Username = credentials[2];
                        Password = credentials.length > 3 ? credentials[3] : "";
                    } else {
                        throw new IllegalArgumentException("Credentials not found for environment: " + environment);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown environment: " + environment);
            }

            // Launch and log in to the portal
            launchPortal(url);
            logInToPortal(ChainID, PropertyID, Username);
            data.put("user", Username);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("loginAndLaunchTest", Status.FAIL, "Exception found in Login and Launch", true, e);
        }
        return data;
    }

    public Map<String, String> loginSK(Method m) {
        Map<String, String> data = new HashMap<>();
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "Portal Login and Launch");

        try {
            // Load URL based on environment
            String environment = System.getProperty("env", "approve");
            String url = ConfigProperties.getURL(environment, "admin");

            String ChainID = null;
            String PropertyID = null;
            String Username = null;
            String Password = null;
            String[] credentials = null;

            // Retrieve credentials based on environment
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
                        Password = credentials.length > 3 ? credentials[3] : "";
                    } else {
                        throw new IllegalArgumentException("Credentials not found for environment: " + environment);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown environment: " + environment);
            }

            // Launch and log in to the portal
            launchPortal(url);
            logInToPortal(ChainID, PropertyID, Username);
            data.put("user", Username);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("loginAndLaunchTest", Status.FAIL, "Exception found in Login and Launch", true, e);
        }
        return data;
    }

    public Map<String, String> loginVAT(Method m) {
        Map<String, String> data = new HashMap<>();
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "Portal Login and Launch");

        try {
            // Load URL based on environment
            String environment = System.getProperty("env", "vat");
            String url = ConfigProperties.getURL(environment, "admin");

            String ChainID = null;
            String PropertyID = null;
            String Username = null;
            String Password = null;
            String[] credentials = null;

            // Retrieve credentials based on environment
            switch (environment.toLowerCase()) {
                case "dev":
                case "test":
                case "prod":
                case "vat":
                    credentials = ExcelHandler.getCredentialsForEnvironment(environment);
                    if (credentials != null && credentials.length >= 3) {
                        ChainID = credentials[0];
                        PropertyID = credentials[1];
                        Username = credentials[2];
                        Password = credentials.length > 3 ? credentials[3] : "";
                    } else {
                        throw new IllegalArgumentException("Credentials not found for environment: " + environment);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown environment: " + environment);
            }

            // Launch and log in to the portal
            launchPortal(url);
            logInToPortal(ChainID, PropertyID, Username);
            data.put("user", Username);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("loginAndLaunchTest", Status.FAIL, "Exception found in Login and Launch", true, e);
        }
        return data;
    }

    public Map<String, String> login0419(Method m) {
        Map<String, String> data = new HashMap<>();
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "Portal Login and Launch");

        try {
            // Load URL based on environment
            String environment = System.getProperty("env", "prod");
            String url = ConfigProperties.getURL(environment, "admin");

            String ChainID = null;
            String PropertyID = null;
            String Username = null;
            String Password = null;
            String[] credentials = null;

            // Retrieve credentials based on environment
            switch (environment.toLowerCase()) {
                case "dev":
                case "test":
                case "prod":
                case "vat":
                    credentials = ExcelHandler.getCredentialsForEnvironment(environment);
                    if (credentials != null && credentials.length >= 3) {
                        ChainID = credentials[0];
                        PropertyID = credentials[1];
                        Username = credentials[2];
                        Password = credentials.length > 3 ? credentials[3] : "";
                    } else {
                        throw new IllegalArgumentException("Credentials not found for environment: " + environment);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown environment: " + environment);
            }

            // Launch and log in to the portal
            launchPortal(url);
            logInToPortal(ChainID, PropertyID, Username);
            data.put("user", Username);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("loginAndLaunchTest", Status.FAIL, "Exception found in Login and Launch", true, e);
        }
        return data;
    }

}

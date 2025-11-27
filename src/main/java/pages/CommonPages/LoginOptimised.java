package pages.CommonPages;


import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import datahandlers.ConfigProperties;
import datahandlers.ExcelHandler;
import extentreports.ExtentTestManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DynamicWait;
import utils.Utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LoginOptimised extends BaseTest {

    private static final String IFRAME_DIALOG = "iframeGridDialog";
    // Product URLs to validate against
    private static final String[] PRODUCT_URL_CONTAINS = {
            "http://203.122.16.200:85/WISH/Common/frmMultiPage.aspx?pgName=WishProduct&form_id=frmWishProduct",
            "https://qc2web.prologicfirst.in/WISH/Common/frmMultiPage.aspx?pgName=WishProduct&form_id=frmWishProduct"
    };
    // ---------- Elements ----------
    @FindBy(id = "txtChainID")
    private WebElement chainIdField;
    @FindBy(id = "txtUnitID")
    private WebElement propertyIdField;
    @FindBy(id = "txtLogIN")
    private WebElement userIdField;
    @FindBy(id = "lblEnterPassword")
    private WebElement passwordField; // optional
    @FindBy(id = "lblLogin_span")
    private WebElement loginBtn;
    // Popup/iframe
    @FindBy(id = "lblStatusDesc")
    private WebElement alreadyLoginPopup;
    @FindBy(id = "btnYes")
    private WebElement yesLoginBtn;
    @FindBy(id = "btnOK")
    private WebElement okBtn;

    // ---------- Constructor ----------
    public LoginOptimised(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // ---------- Public Flow ----------
    public Map<String, String> performLogin() {
        Map<String, String> out = new HashMap<>();
        ExtentTestManager.startTest("Portal Login", "Launch & Login to Portal");

        String environment = System.getProperty("env", "test");
        try {
            // Fetch URL and credentials
            String url = ConfigProperties.getURL(environment, "admin");
            String[] creds = ExcelHandler.getCredentialsForEnvironment(environment);

            if (creds == null || creds.length < 3) {
                throw new IllegalArgumentException("No credentials found for env: " + environment);
            }

            String chain = creds[0];
            String property = creds[1];
            String user = creds[2];
            String pwd = creds.length > 3 ? creds[3] : "";

            // Launch portal
            launchPortal(url);

            // Perform login
            String parentHandle = BaseTest.getDriver().getWindowHandle();
            logInToPortal(chain, property, user, pwd);

            // Handle already logged-in popup
            handleAlreadyLoginPopup();

            // Switch to product window
            switchToNewWindow(parentHandle);

            // Verify navigation
            verifyNavigation();

            out.put("user", user);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("PortalLogin", Status.FAIL,
                    "Login failed", true, e);
        }
        return out;
    }

    // ---------- Private Helpers ----------
    private void launchPortal(String url) {
        BaseTest.getDriver().navigate().to(url);
        BaseTest.getDriver().manage().deleteAllCookies();
        Utilities.WaitForPageLoad(BaseTest.getDriver());
        ExtentTestManager.createAssertTestStepWithScreenshot("Launch Portal", Status.INFO,
                "Launched portal - " + url, false);
    }

    private void logInToPortal(String chain, String property, String user, String pwd) throws InterruptedException {
        Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), chainIdField);
        Utilities.SendKeys(BaseTest.getDriver(), chainIdField, chain);
        Utilities.SendKeys(BaseTest.getDriver(), propertyIdField, property);
        Utilities.SendKeys(BaseTest.getDriver(), userIdField, user);

        if (passwordField != null && isDisplayed(passwordField)) {
            Utilities.SendKeys(BaseTest.getDriver(), passwordField, pwd);
        }

        Utilities.Click(BaseTest.getDriver(), loginBtn);
        DynamicWait.smallWait();
    }

    private void handleAlreadyLoginPopup() {
        try {
            BaseTest.getDriver().switchTo().frame(IFRAME_DIALOG);
            if (isDisplayed(alreadyLoginPopup)) {
                if (isDisplayed(yesLoginBtn)) {
                    Utilities.Click(BaseTest.getDriver(), yesLoginBtn);
                } else if (isDisplayed(okBtn)) {
                    Utilities.Click(BaseTest.getDriver(), okBtn);
                }
                DynamicWait.smallWait();
            }
        } catch (NoSuchFrameException | InterruptedException ignored) {
        } finally {
            BaseTest.getDriver().switchTo().defaultContent();
        }
    }

    private void switchToNewWindow(String parentHandle) {
        Set<String> handles = BaseTest.getDriver().getWindowHandles();
        for (String h : handles) {
            if (!h.equals(parentHandle)) {
                BaseTest.getDriver().switchTo().window(h);
                break;
            }
        }
    }

    private void verifyNavigation() {
        DynamicWait.smallWait();
        String url = BaseTest.getDriver().getCurrentUrl();
        boolean matched = false;
        for (String fragment : PRODUCT_URL_CONTAINS) {
            if (url.contains(fragment)) {
                matched = true;
                break;
            }
        }

        if (matched) {
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Verify Navigation", Status.PASS, "Navigation OK: " + url, true);
        } else {
            ExtentTestManager.createAssertTestStepWithScreenshot(
                    "Verify Navigation", Status.WARNING, "Unexpected landing: " + url, true);
        }
    }

    private boolean isDisplayed(WebElement element) {
        try {
            return element != null && element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}



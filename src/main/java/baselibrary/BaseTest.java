package baselibrary;

import com.aventstack.extentreports.Status;
//import com.microsoft.edge.seleniumtools.EdgeDriver;
//import com.microsoft.edge.seleniumtools.EdgeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.text.WordUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.*;
import extentreports.ExtentTestManager;
import logs.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.HashMap;

public class BaseTest {
    protected static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static String screenshotBasePath = System.getProperty("user.dir") + File.separator + "screenshots";
    public static String folderPath;
    private static File outputFile;
    public static String browser = "";
    public static String version = "";
    public static String mode = "";
    public static String environment = "";
    public static String testOS = "";
    public static String buildNumber = "";
    private static String projectName = "";
    private static String baseurl = "";
    private static long implicitwaitduration = 0;
    private static String timeunit = "";


    @BeforeTest(alwaysRun = true)
    @Parameters({"browser", "mode", "environment", "implicitwaitduration", "timeunit", "os", "url"})
    public void initSession(String pbrowser, String pmode, String penvironment, long pimplicitwaitduration, String ptimeunit, String pos, String purl) {
        try {
            Log.info("Current System OS -> " + System.getProperty("os.name"));
            folderPath = screenshot_TimeStamp_Folder();
            browser = WordUtils.capitalize(pbrowser);
            Log.info("Browser=" + browser);
            testOS = System.getProperty("os.name");
            Log.info("OS=" + testOS);
            mode = pmode;
            Log.info("Mode=" + mode);
            environment = penvironment;
            Log.info("Environment=" + environment);
            buildNumber = System.getProperty("jenkinsbuildnum");
            Log.info("buildNumber=" + buildNumber);
            projectName = System.getProperty("jenkinsjobname");
            Log.info("projectName=" + projectName);
            implicitwaitduration = pimplicitwaitduration;
            timeunit = ptimeunit;
            Log.info(System.getProperty("user.dir"));
            baseurl = purl;
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("initSession", Status.FAIL,
                    "Exception found in Method - initSession", false, e);
        }
    }

    public static WebDriver createDriver() throws InterruptedException {
        Log.info("Initializing " + browser + " Driver");
        String downloadFilepath = System.getProperty("user.dir") + File.separator + "DownloadPath";

        WebDriver returnDriver = null;
        switch (browser.toLowerCase()) {
            case "safari":

                SafariOptions options = new SafariOptions();
                options.setUseTechnologyPreview(true);

                returnDriver = new SafariDriver(options);
                setImplicitWait(returnDriver);
                returnDriver.manage().window().maximize();
                version = ((SafariDriver) returnDriver).getCapabilities().getBrowserVersion();
                break;

            case "firefox":

                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("pdfjs.disabled", true);
                profile.setPreference("browser.download.dir", downloadFilepath);
                profile.setPreference("browser.download.folderList", 2);
                profile.setPreference("browser.download.manager.showWhenStarting", false);
                profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");

                FirefoxOptions firefoxOptions = new FirefoxOptions();

                if (mode.equalsIgnoreCase("headless")) {
                    firefoxOptions.addArguments("-headless");
                } else {
                    firefoxOptions.addArguments("-private");
                    firefoxOptions.setCapability("acceptInsecureCerts", true);
                    firefoxOptions.setProfile(profile);
                }

                returnDriver = new FirefoxDriver(firefoxOptions);
                returnDriver.manage().window().maximize();
                returnDriver.manage().deleteAllCookies();
                setImplicitWait(returnDriver);
                version = ((FirefoxDriver) returnDriver).getCapabilities().getBrowserVersion();
                break;

            case "edge":
                HashMap<String, Object> edgePrefs = new HashMap<>();
                edgePrefs.put("profile.default_content_settings.popups", 0);
                edgePrefs.put("profile.default_content_setting_values.notifications", 2);
                edgePrefs.put("profile.default_content_setting_values.automatic_downloads", 1);
                edgePrefs.put("profile.content_settings.pattern_pairs.*,*.multiple-automatic-downloads", 1);
                edgePrefs.put("plugins.always_open_pdf_externally", true);
                edgePrefs.put("download.directory_upgrade", true);
                edgePrefs.put("download.prompt_for_download", false);
                edgePrefs.put("download.default_directory", downloadFilepath);

                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("InPrivate");
                edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                edgeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                edgeOptions.addArguments("--disable-notifications");
                edgeOptions.addArguments("--remote-allow-origins=*");
                edgeOptions.setExperimentalOption("prefs", edgePrefs);

                returnDriver = new EdgeDriver(edgeOptions);
                returnDriver.manage().window().maximize();
                returnDriver.manage().deleteAllCookies();
                setImplicitWait(returnDriver);
                version = ((EdgeDriver) returnDriver).getCapabilities().getBrowserVersion();

                break;

            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                HashMap<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", downloadFilepath);
                chromePrefs.put("download.prompt_for_download", false);
                chromePrefs.put("safebrowsing.enabled", true);
                chromeOptions.setExperimentalOption("prefs", chromePrefs);
                chromeOptions.addArguments("--disable-features=DownloadBubble,DownloadBubbleV2");

                if (mode.equalsIgnoreCase("headless")) {
                    chromeOptions.addArguments("--headless", "--window-size=1920,1200");
                    chromeOptions.addArguments("--disable-notifications");
                    chromeOptions.addArguments("--ignore-certificate-errors");
                    chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                    chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                    chromeOptions.addArguments("--remote-allow-origins=*");
                } else {
//                    chromeOptions.addArguments("incognito");
                    chromeOptions.addArguments("--disable-notifications");
                    chromeOptions.addArguments("--ignore-certificate-errors");
                    chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                    chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    chromeOptions.addArguments("--safebrowsing-disable-download-protection");
                    chromeOptions.addArguments("safebrowsing-disable-extension-blacklist");
                    chromeOptions.addArguments("--disable-features=DownloadBubble,DownloadBubbleV2");

                }

                returnDriver = new ChromeDriver(chromeOptions);
                returnDriver.manage().window().maximize();
                returnDriver.manage().deleteAllCookies();
                setImplicitWait(returnDriver);
                version = ((ChromeDriver) returnDriver).getCapabilities().getBrowserVersion();
                break;
        }
        return returnDriver;
    }

    public static void setDriver() {
        try {
            driver.set(createDriver());
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("setDriver", Status.FAIL,
                    "Exception found in Method - setDriver", false, e);
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void closeBrowser() {
        try {
            if (driver.get() != null)
                driver.get().quit();
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("closeBrowser", Status.FAIL,
                    "Exception found in Method - closeBrowser", true, e);
        }
    }

    public static void quitBrowser() {
        try {
            if (driver.get() != null) {
                driver.get().quit();
            }
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("quitBrowser", Status.FAIL,
                    "Exception found in Method - quitBrowser", true, e);
        }
    }

    private static void setImplicitWait(WebDriver sdriver) {
        try {
            switch (timeunit) {
                case "nanoseconds":
                    sdriver.manage().timeouts().implicitlyWait(Duration.ofNanos(implicitwaitduration));
                    break;
                case "milliseconds":
                    sdriver.manage().timeouts().implicitlyWait(Duration.ofMillis(implicitwaitduration));
                    break;
                case "seconds":
                    sdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitwaitduration));
                    break;
                case "minutes":
                    sdriver.manage().timeouts().implicitlyWait(Duration.ofMinutes(implicitwaitduration));
                    break;
                case "hours":
                    sdriver.manage().timeouts().implicitlyWait(Duration.ofHours(implicitwaitduration));
                    break;
            }
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("setImplicitWait", Status.FAIL,
                    "Exception found in Method - setImplicitWait", true, e);
        }
    }

    public synchronized static String screenshot_TimeStamp_Folder() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        outputFile = new File(screenshotBasePath + File.separator + "screenshot_" + timeStamp);
        outputFile.mkdir();
        return screenshotBasePath + File.separator + "screenshot_" + timeStamp;
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        try {
            ExtentTestManager.flush();
            quitBrowser();
            driver.remove();
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("tearDown", Status.FAIL,
                    "Exception found in Method - tearDown", false, e);
        }
    }
}
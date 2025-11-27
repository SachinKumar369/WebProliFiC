//package extentreports;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.markuputils.MarkupHelper;
//import org.apache.commons.lang3.exception.ExceptionUtils;
//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.LogManager;
//import org.testng.Assert;
//import datahandlers.ConfigProperties;
//import screenshot.ScreenshotService;
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.Base64;
//
/// **
// * ExtentTestManager for parallel execution
// */
//public class ExtentTestManager {
//
//    private static final ThreadLocal<ExtentTest> extentTestThreadLocal = ThreadLocal.withInitial(() -> null);
//    private static final ExtentReports extent = ExtentManager.createExtentReports();
//    private static final Logger Log = LogManager.getLogger(ExtentTestManager.class);
//
//    public static synchronized ExtentTest getTest() {
//        return extentTestThreadLocal.get();
//    }
//
//    public static synchronized ExtentTest startTest(String testName, String desc) {
//        Log.info("Started Test - " + testName + " with description - " + desc);
//        ExtentTest test = extent.createTest(testName, "<b>" + desc + "</b>");
//        extentTestThreadLocal.set(test);
//        return test;
//    }
//
//    /**
//     * Create a test step with screenshot
//     */
//    public static synchronized void createAssertTestStepWithScreenshot(String stepName, Status status, String message, boolean screenshot, Throwable... t) {
//        try {
//            String configFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
//                    + File.separator + "resources" + File.separator + "config" + File.separator + "config.properties";
//            new ConfigProperties(configFilePath);
//
//            ExtentTest test = getTest();
//            if (test == null) {
//                Log.error("ExtentTest instance is null for thread: " + Thread.currentThread().getId());
//                return;
//            }
//
//            switch (status) {
//                case INFO:
//                    Log.info(stepName + " - " + message);
//                    test.createNode(stepName).info(message);
//                    if (screenshot && ConfigProperties.getPropertyValue("info_step_screenshots").equalsIgnoreCase("yes")) {
//                        test.addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
//                    }
//                    break;
//
//                case PASS:
//                    Log.info("PASS - " + stepName + " - " + message);
//                    test.createNode(stepName).pass(message);
//                    if (screenshot && ConfigProperties.getPropertyValue("passed_step_screenshots").equalsIgnoreCase("yes")) {
//                        test.addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
//                    }
//                    break;
//
//                case FAIL:
//                    Log.error(stepName + " - " + message);
//                    if (screenshot && ConfigProperties.getPropertyValue("failed_step_screenshots").equalsIgnoreCase("yes")) {
//                        if (t.length > 0) {
//                            String stackTrace = ExceptionUtils.getStackTrace(t[0]);
//                            test.createNode(stepName).fail(message)
//                                    .info(MarkupHelper.createCodeBlock(stackTrace))
//                                    .addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
//                        } else {
//                            test.createNode(stepName).fail(message)
//                                    .addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
//                        }
//                    } else if (t.length > 0) {
//                        test.createNode(stepName).fail(message)
//                                .info(MarkupHelper.createCodeBlock(ExceptionUtils.getStackTrace(t[0])));
//                    } else {
//                        test.createNode(stepName).fail(message);
//                    }
//                    Assert.fail(message);
//                    break;
//
//                case WARNING:
//                    Log.warn(stepName + " - " + message);
//                    test.createNode(stepName).warning(message);
//                    if (screenshot && ConfigProperties.getPropertyValue("warning_step_screenshots").equalsIgnoreCase("yes")) {
//                        test.addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
//                    }
//                    break;
//
//                case SKIP:
//                    Log.info("SKIP - " + stepName + " - " + message);
//                    test.createNode(stepName).skip(message)
//                            .addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
//                    break;
//
//                default:
//                    Log.warn("Unknown status: " + status);
//                    break;
//            }
//        } catch (Exception e) {
//            Log.error("Error in createAssertTestStepWithScreenshot: " + ExceptionUtils.getStackTrace(e));
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * Ensures ExtentReports is flushed at the end of the test run.
//     */
//    public static synchronized void flush() {
//        extent.flush();
//    }
//
//    /**
//     * Convert a file to Base64 encoded string
//     */
//    public synchronized static String base64Encode(File file) {
//        if (file == null || !file.isFile()) {
//            return null;
//        }
//        try (FileInputStream fileInputStreamReader = new FileInputStream(file)) {
//            byte[] bytes = new byte[(int) file.length()];
//            if (fileInputStreamReader.read(bytes) != -1) {
//                return "data:image/png;base64," + Base64.getEncoder().encodeToString(bytes);
//            }
//            return null;
//        } catch (Exception e) {
//            Log.error("Error encoding file to Base64: " + ExceptionUtils.getStackTrace(e));
//            return null;
//        }
//    }
//}
//
//


//package extentreports;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.markuputils.MarkupHelper;
//import org.apache.commons.lang3.exception.ExceptionUtils;
//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.LogManager;
//import org.testng.Assert;
//import datahandlers.ConfigProperties;
//import screenshot.ScreenshotService;
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.Base64;
//import java.util.HashMap;
//import java.util.Map;
//
/// **
// * extentTestMap holds the information of thread ids and ExtentTest instances.
// * ExtentReports instance created by calling createExtentReports() method from
// * ExtentManager. At startTest() method, an instance of ExtentTest created and
// * put into extentTestMap with current thread id. At getTest() method, return
// * ExtentTest instance in extentTestMap by using current thread id.
// */
//public class ExtentTestManager
//{
//    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
//    static ExtentReports extent = ExtentManager.createExtentReports();
//
//    public static synchronized ExtentTest getTest()
//    {
//        return extentTestMap.get((int) Thread.currentThread().getId());
//    }
//
//    public static Logger Log = (Logger) LogManager.getLogger(logs.Log.class);
//
//    public static synchronized ExtentTest startTest(String testName, String desc)
//    {
//        Log.info("Started Test - " + testName + " with description - " + desc);
//        ExtentTest test = extent.createTest(testName, "<b>" + desc + "</b>");
//        extentTestMap.put((int) Thread.currentThread().getId(), test);
//        return test;
//    }
//
//
//    /**
//     *
//     * @param stepName
//     * @param status
//     * @param message
//     * @param ScreenShot
//     * @param t Throwable t
//     */
//    public static synchronized void createAssertTestStepWithScreenshot(String stepName, Status status, String message, Boolean ScreenShot, Throwable... t) {
//
//        String filepath = System.getProperty("user.dir") + File.separator + "src"  + File.separator + "test"
//                + File.separator + "resources" + File.separator + "config" + File.separator + "config.properties";
//        try
//        {
//            new ConfigProperties(filepath);
//            switch(status.toString().toUpperCase())
//            {
//                case "INFO":
//                    Log.info(stepName + " - " + message);
//
//                    if(ScreenShot && ConfigProperties.getPropertyValue("info_step_screenshots").equalsIgnoreCase("yes"))
//                        extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).info(message)
//                                .addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
//                    else
//                        extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).info(message);
//                    break;
//                case "PASS":
//                    Log.info("PASS - " + stepName + " - " + message);
//
//                    if(ScreenShot && ConfigProperties.getPropertyValue("passed_step_screenshots").equalsIgnoreCase("yes"))
//                        extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).pass(message)
//                                .addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
//                    else
//                        extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).pass(message);
//                    break;
//                case "FAIL":
//                    Log.error(stepName + " - " + message);
//                    if(ScreenShot && ConfigProperties.getPropertyValue("failed_step_screenshots").equalsIgnoreCase("yes"))
//                    {
//                        if(t.length > 0)
//                        {
//                            t[0].printStackTrace();
//                            for(int i = 0; i < t[0].getStackTrace().length; i++)
//                            {
//                                if(t[0].getStackTrace()[i].getClassName().contains("pages"))
//                                {
//                                    getTest().fail(message + "<br>" + t[0].getMessage().split("\\n")[0]
//                                            + "<br>Exception found in class <b>" + t[0].getStackTrace()[i].getClassName()
//                                            + "</b> line number - <b>" + t[0].getStackTrace()[i].getLineNumber() + "</b>");
//                                }
//                            }
//                            extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).fail(message)
//                                    .info(MarkupHelper.createCodeBlock(ExceptionUtils.getStackTrace(t[0])))
//                                    .addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
//                        }
//                        else
//                            extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).fail(message)
//                                    .addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
//                    }
//                    else
//                    if(t.length > 0)
//                        extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).fail(message)
//                                .info(MarkupHelper.createCodeBlock(ExceptionUtils.getStackTrace(t[0])));
//                    else
//                        extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).fail(message);
//
//                    Assert.fail();
//                    break;
//                case "WARNING":
//                    Log.warn(stepName + " - " + message);
//
//                    if(ScreenShot && ConfigProperties.getPropertyValue("warning_step_screenshots").equalsIgnoreCase("yes"))
//                        extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName)
//                                .warning(message).addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
//                    else
//                        extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).warning(message);
//                    break;
//                case "SKIP":
//                    Log.info("SKIP - " + stepName + " - " + message);
//                    extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).skip(message)
//                            .addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
//                    break;
//            }
//        }
//        catch(Exception e)
//        {
//            Log.error(ExceptionUtils.getStackTrace(e));
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    public static synchronized void flush()
//    {
//        extent.flush();
//    }
//
//
//    public synchronized static String base64Encode(File file)
//    {
//        if(file == null || !file.isFile())
//        {
//            return null;
//        }
//        try
//        {
//            @SuppressWarnings("resource") FileInputStream fileInputStreamReader = new FileInputStream(file);
//            byte[] bytes = new byte[(int) file.length()];
//            if(fileInputStreamReader.read(bytes) != -1)
//            {
//                return "data:image/png;base64," + new String(Base64.getEncoder().encode(bytes), "UTF-8");
//            }
//            return null;
//        }
//        catch(Throwable e)
//        {
//            return null;
//        }
//    }
//}


package extentreports;

import baselibrary.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.*;
import org.testng.Assert;
import datahandlers.ConfigProperties;
import screenshot.ScreenshotService;
import utils.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * extentTestMap holds the information of thread ids and ExtentTest instances.
 * ExtentReports instance created by calling createExtentReports() method from
 * ExtentManager. At startTest() method, an instance of ExtentTest created and
 * put into extentTestMap with current thread id. At getTest() method, return
 * ExtentTest instance in extentTestMap by using current thread id.
 */
public class ExtentTestManager {
    public static Logger Log = (Logger) LogManager.getLogger(logs.Log.class);
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static ExtentReports extent = ExtentManager.createExtentReports();

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized ExtentTest startTest(String testName, String desc) {
        Log.info("Started Test - " + testName + " with description - " + desc);
        ExtentTest test = extent.createTest(testName, "<b>" + desc + "</b>");
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }


    public static synchronized void flush() {
        extent.flush();
    }


    public synchronized static String base64Encode(File file) {
        if (file == null || !file.isFile()) {
            return null;
        }
        try {
            @SuppressWarnings("resource") FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            if (fileInputStreamReader.read(bytes) != -1) {
                return "data:image/png;base64," + new String(Base64.getEncoder().encode(bytes), "UTF-8");
            }
            return null;
        } catch (Throwable e) {
            return null;
        }
    }


    public static synchronized void createAssertTestStepWithScreenshot(String stepName, Status status, String message, Boolean ScreenShot, Throwable... t) {
        String filepath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "config" + File.separator + "config.properties";
        try {
            new ConfigProperties(filepath);

            switch (status.toString().toUpperCase()) {
                case "INFO":
                    Log.info(stepName + " - " + message);

                    if (ScreenShot && ConfigProperties.getPropertyValue("info_step_screenshots").equalsIgnoreCase("yes"))
                        extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).info(message).addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
                    else extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).info(message);
                    break;

                case "PASS":
                    Log.info("PASS - " + stepName + " - " + message);

                    if (ScreenShot && ConfigProperties.getPropertyValue("passed_step_screenshots").equalsIgnoreCase("yes"))
                        extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).pass(message).addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
                    else extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).pass(message);
                    break;

                case "FAIL":
                    Log.error(stepName + " - " + message);

                    if (ScreenShot && ConfigProperties.getPropertyValue("failed_step_screenshots").equalsIgnoreCase("yes")) {

                        new ConfigProperties(filepath); // Load properties file
                        String elementName = "UnknownElement";
                        if (t.length > 0) {


                            elementName = String.valueOf(extractElementFromException(t[0])); // Dynamically get element name

                            System.out.println(elementName);
                            Log.info(elementName);
                            t[0].printStackTrace();
                            String customMessage = message;

                            // Handle various exceptions
                            if (t[0] instanceof TimeoutException) {
                                customMessage += "<br><b>Possible issue: The page or element took too long to load or respond. Please check the server or network performance.</b>";
                            } else if (t[0] instanceof NoSuchElementException) {
                                customMessage += "<br><b>Possible issue: The element could not be found on the page. Please check if the element’s locator is correct and exists in the DOM.</b>";
                            } else if (t[0] instanceof ElementNotInteractableException) {
                                customMessage += "<br><b>Possible issue: The element is present in the DOM but cannot be interacted with. Ensure it is visible and clickable.</b>";
                            } else if (t[0] instanceof StaleElementReferenceException) {
                                customMessage += "<br><b>Possible issue: The element is no longer attached to the DOM. Try re-locating or refreshing the element.</b>";
                            } else if (t[0] instanceof WebDriverException) {
                                customMessage += "<br><b>Possible issue: A WebDriver communication error occurred. Ensure the WebDriver is configured correctly and try again.</b>";
                            } else if (t[0] instanceof NoSuchWindowException) {
                                customMessage += "<br><b>Possible issue: The referenced window is no longer available. Check if the window handle is correct.</b>";
                            } else if (t[0] instanceof NoSuchFrameException) {
                                customMessage += "<br><b>Possible issue: The specified frame does not exist. Verify the frame’s index or ID.</b>";
                            } else if (t[0] instanceof InvalidSelectorException) {
                                customMessage += "<br><b>Possible issue: The selector used (CSS/XPath) is invalid. Please verify the correctness of the selector.</b>";
                            } else if (t[0] instanceof JavascriptException) {
                                customMessage += "<br><b>Possible issue: JavaScript execution failed. Check the JavaScript execution context or syntax errors.</b>";
                            } else if (t[0] instanceof InvalidArgumentException) {
                                customMessage += "<br><b>Possible issue: Invalid argument passed to the WebDriver command. Please verify the arguments.</b>";
                            }


                            // Include stack trace and screenshot
                            extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).fail(customMessage).info(MarkupHelper.createCodeBlock(ExceptionUtils.getStackTrace(t[0]))).addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
                        } else {
                            extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).fail(message).addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
                        }
                    } else {
                        String elementName = "UnknownElement";

                        if (t.length > 0) {
                            String stackTrace = ExceptionUtils.getStackTrace(t[0]);
                            String customMessage = message + "<br><b>Error Details:</b><br>" + stackTrace.split("\n")[0];

                            elementName = String.valueOf(extractElementFromException(t[0])); // Dynamically get element name

                            System.out.println(elementName);

                            // Handle various exceptions
                            if (t[0] instanceof TimeoutException) {
                                customMessage += "<br><b>Possible issue: The page or element took too long to load. Please check the network or server response time.</b>";
                            } else if (t[0] instanceof NoSuchElementException) {
                                customMessage += "<br><b>Possible issue: The element could not be found. Check the element’s locator.</b>";
                            } else if (t[0] instanceof ElementNotInteractableException) {
                                customMessage += "<br><b>Possible issue: The element cannot be interacted with. Ensure it's visible and clickable.</b>";
                            } else if (t[0] instanceof StaleElementReferenceException) {
                                customMessage += "<br><b>Possible issue: The element is stale. Re-locate the element.</b>";
                            } else if (t[0] instanceof WebDriverException) {
                                customMessage += "<br><b>Possible issue: WebDriver communication failed. Verify the WebDriver setup.</b>";
                            } else if (t[0] instanceof NoSuchWindowException) {
                                customMessage += "<br><b>Possible issue: The window was not found. Check the window handle.</b>";
                            } else if (t[0] instanceof NoSuchFrameException) {
                                customMessage += "<br><b>Possible issue: The frame was not found. Verify the frame's ID or index.</b>";
                            } else if (t[0] instanceof InvalidSelectorException) {
                                customMessage += "<br><b>Possible issue: Invalid CSS selector or XPath used.</b>";
                            }


                            extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).fail(customMessage).info(MarkupHelper.createCodeBlock(stackTrace));
                        } else {
                            extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).fail(message);
                        }
                    }

                    Assert.fail();
                    break;

                case "WARNING":
                    Log.warn(stepName + " - " + message);

                    if (ScreenShot && ConfigProperties.getPropertyValue("warning_step_screenshots").equalsIgnoreCase("yes"))
                        extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).warning(message).addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
                    else extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).warning(message);
                    break;

                case "SKIP":
                    Log.info("SKIP - " + stepName + " - " + message);
                    extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).skip(message).addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
                    break;
            }
        } catch (Exception e) {
            Log.error("Error occurred in createAssertTestStepWithScreenshot method: " + e.getMessage());
            String stackTrace = ExceptionUtils.getStackTrace(e);
            Log.error(stackTrace);
            throw new RuntimeException("An unexpected error occurred: " + stackTrace, e);
        }
    }


    public static void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }


    /**
     * Extracts WebElement name or locator from NoSuchElementException.
     */
//    public static String extractElementFromException(Throwable exception) {
//        if (exception instanceof NoSuchElementException) {
//            String message = exception.getMessage();
//
//            // Extracting locator type and value from exception message
//            Pattern pattern = Pattern.compile("\\{method\":\"(.*?)\",\"selector\":\"(.*?)\"\\}");
//            Matcher matcher = pattern.matcher(message);
//
//            if (matcher.find()) {
//                return matcher.group(1) + " = " + matcher.group(2); // Example: id = myInput
//            }
//        }
//        return "UnknownElement"; // Default if no match found
//    }
    private static WebElement extractElementFromException(Throwable exception) {
        if (exception instanceof NoSuchElementException || exception instanceof StaleElementReferenceException) {
            String message = exception.getMessage();
            Pattern pattern = Pattern.compile("\\{method\":\"(.*?)\",\"selector\":\"(.*?)\"\\}");
            Matcher matcher = pattern.matcher(message);

            if (matcher.find()) {
                String locatorType = matcher.group(1);  // e.g., "id"
                String locatorValue = matcher.group(2); // e.g., "cphBody_txtPONumber"

                try {
                    switch (locatorType) {
                        case "id":
                            return BaseTest.getDriver().findElement(By.id(locatorValue));
                        case "name":
                            return BaseTest.getDriver().findElement(By.name(locatorValue));
                        case "class name":
                            return BaseTest.getDriver().findElement(By.className(locatorValue));
                        case "tag name":
                            return BaseTest.getDriver().findElement(By.tagName(locatorValue));
                        case "css selector":
                            return BaseTest.getDriver().findElement(By.cssSelector(locatorValue));
                        case "xpath":
                            return BaseTest.getDriver().findElement(By.xpath(locatorValue));
                        default:
                            return null;
                    }
                } catch (Exception e) {
                    return null; // If the element is still not found, return null
                }
            }
        }
        return null;
    }

    private void logExceptionWithElement(Exception e, String context) {
        WebElement failedElement = extractElementFromException(e);
        String elementDetails = (failedElement != null) ? failedElement.toString() : "UnknownElement";

        String message = "Exception in " + context + " | Failed on Element: `" + elementDetails + "`";
        ExtentTestManager.createAssertTestStepWithScreenshot("Enter Items", Status.FAIL, message, true, e);
    }

    // New method to log steps without asserting failure
    public static synchronized void logTestStepWithScreenshot(String stepName, Status status, String message, Boolean takeScreenshot, Throwable... t) {
        String filepath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "config" + File.separator + "config.properties";
        try {
            new ConfigProperties(filepath);

            switch (status.toString().toUpperCase()) {
                case "INFO":
                    Log.info(stepName + " - " + message);

                    if (takeScreenshot && ConfigProperties.getPropertyValue("info_step_screenshots").equalsIgnoreCase("yes"))
                        extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).info(message).addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
                    else extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).info(message);
                    break;

                case "PASS":
                    Log.info("PASS - " + stepName + " - " + message);

                    if (takeScreenshot && ConfigProperties.getPropertyValue("passed_step_screenshots").equalsIgnoreCase("yes"))
                        extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).pass(message).addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
                    else extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).pass(message);
                    break;

                case "FAIL":
                    Log.error(stepName + " - " + message);

                    if (takeScreenshot && ConfigProperties.getPropertyValue("failed_step_screenshots").equalsIgnoreCase("yes")) {

                        new ConfigProperties(filepath); // Load properties file
                        String elementName = "UnknownElement";
                        if (t.length > 0) {

                            elementName = String.valueOf(extractElementFromException(t[0])); // Dynamically get element name

                            System.out.println(elementName);
                            Log.info(elementName);
                            t[0].printStackTrace();
                            String customMessage = message;

                            // Handle various exceptions
                            if (t[0] instanceof TimeoutException) {
                                customMessage += "<br><b>Possible issue: The page or element took too long to load or respond. Please check the server or network performance.</b>";
                            } else if (t[0] instanceof NoSuchElementException) {
                                customMessage += "<br><b>Possible issue: The element could not be found on the page. Please check if the element’s locator is correct and exists in the DOM.</b>";
                            } else if (t[0] instanceof ElementNotInteractableException) {
                                customMessage += "<br><b>Possible issue: The element is present in the DOM but cannot be interacted with. Ensure it is visible and clickable.</b>";
                            } else if (t[0] instanceof StaleElementReferenceException) {
                                customMessage += "<br><b>Possible issue: The element is no longer attached to the DOM. Try re-locating or refreshing the element.</b>";
                            } else if (t[0] instanceof WebDriverException) {
                                customMessage += "<br><b>Possible issue: A WebDriver communication error occurred. Ensure the WebDriver is configured correctly and try again.</b>";
                            } else if (t[0] instanceof NoSuchWindowException) {
                                customMessage += "<br><b>Possible issue: The referenced window is no longer available. Check if the window handle is correct.</b>";
                            } else if (t[0] instanceof NoSuchFrameException) {
                                customMessage += "<br><b>Possible issue: The specified frame does not exist. Verify the frame’s index or ID.</b>";
                            } else if (t[0] instanceof InvalidSelectorException) {
                                customMessage += "<br><b>Possible issue: The selector used (CSS/XPath) is invalid. Please verify the correctness of the selector.</b>";
                            } else if (t[0] instanceof JavascriptException) {
                                customMessage += "<br><b>Possible issue: JavaScript execution failed. Check the JavaScript execution context or syntax errors.</b>";
                            } else if (t[0] instanceof InvalidArgumentException) {
                                customMessage += "<br><b>Possible issue: Invalid argument passed to the WebDriver command. Please verify the arguments.</b>";
                            }

                            // Include stack trace and screenshot
                            extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).fail(customMessage).info(MarkupHelper.createCodeBlock(ExceptionUtils.getStackTrace(t[0]))).addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
                        } else {
                            extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).fail(message).addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
                        }
                    } else {
                        String elementName = "UnknownElement";

                        if (t.length > 0) {
                            String stackTrace = ExceptionUtils.getStackTrace(t[0]);
                            String customMessage = message + "<br><b>Error Details:</b><br>" + stackTrace.split("\n")[0];

                            elementName = String.valueOf(extractElementFromException(t[0])); // Dynamically get element name

                            System.out.println(elementName);

                            // Handle various exceptions
                            if (t[0] instanceof TimeoutException) {
                                customMessage += "<br><b>Possible issue: The page or element took too long to load. Please check the network or server response time.</b>";
                            } else if (t[0] instanceof NoSuchElementException) {
                                customMessage += "<br><b>Possible issue: The element could not be found. Check the element’s locator.</b>";
                            } else if (t[0] instanceof ElementNotInteractableException) {
                                customMessage += "<br><b>Possible issue: The element cannot be interacted with. Ensure it's visible and clickable.</b>";
                            } else if (t[0] instanceof StaleElementReferenceException) {
                                customMessage += "<br><b>Possible issue: The element is stale. Re-locate the element.</b>";
                            } else if (t[0] instanceof WebDriverException) {
                                customMessage += "<br><b>Possible issue: WebDriver communication failed. Verify the WebDriver setup.</b>";
                            } else if (t[0] instanceof NoSuchWindowException) {
                                customMessage += "<br><b>Possible issue: The window was not found. Check the window handle.</b>";
                            } else if (t[0] instanceof NoSuchFrameException) {
                                customMessage += "<br><b>Possible issue: The frame was not found. Verify the frame's ID or index.</b>";
                            } else if (t[0] instanceof InvalidSelectorException) {
                                customMessage += "<br><b>Possible issue: Invalid CSS selector or XPath used.</b>";
                            }

                            extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).fail(customMessage).info(MarkupHelper.createCodeBlock(stackTrace));
                        } else {
                            extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).fail(message);
                        }
                    }
                    break;  // No Assert.fail() here

                case "WARNING":
                    Log.warn(stepName + " - " + message);

                    if (takeScreenshot && ConfigProperties.getPropertyValue("warning_step_screenshots").equalsIgnoreCase("yes"))
                        extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).warning(message).addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
                    else extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).warning(message);
                    break;

                case "SKIP":
                    Log.info("SKIP - " + stepName + " - " + message);
                    extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).skip(message).addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
                    break;
            }
        } catch (Exception e) {
            Log.error("Error occurred in logTestStepWithScreenshot method: " + e.getMessage());
            String stackTrace = ExceptionUtils.getStackTrace(e);
            Log.error(stackTrace);
            throw new RuntimeException("An unexpected error occurred: " + stackTrace, e);
        }
    }

    /**
     * Logs a test step directly to the current ExtentTest with optional screenshot.
     * Ensures steps are logged in chronological order.
     *
     * @param stepName      The name/title of the step
     * @param message       Details or description of the step
     * @param takeScreenshot True to attach screenshot, false otherwise
     */
    public static synchronized void logStep(String stepName, String message, boolean takeScreenshot) {
        try {
            ExtentTest test = getTest(); // Get the current thread's test

            if (takeScreenshot) {
                // Capture screenshot as Base64
                String base64 = ScreenshotService.getScreenshotAsBase64();
                test.info(stepName + " - " + message,
                        com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
            } else {
                test.info(stepName + " - " + message);
            }

            Log.info(stepName + " - " + message); // Log to console / log file

        } catch (Exception e) {
            Log.error("Error while logging step: " + e.getMessage());
            String stackTrace = ExceptionUtils.getStackTrace(e);
            Log.error(stackTrace);
        }
    }

    /**
     * Overloaded method with screenshot defaulting to false
     *
     * @param stepName The name/title of the step
     * @param message  Details or description of the step
     */
    public static synchronized void logStep(String stepName, String message) {
        logStep(stepName, message, false);
    }


}
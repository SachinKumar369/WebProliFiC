package extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import datahandlers.ConfigProperties;
import screenshot.ScreenshotService;
import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * extentTestMap holds the information of thread ids and ExtentTest instances.
 * ExtentReports instance created by calling createExtentReports() method from
 * ExtentManager. At startTest() method, an instance of ExtentTest created and
 * put into extentTestMap with current thread id. At getTest() method, return
 * ExtentTest instance in extentTestMap by using current thread id.
 */
public class ExtentTestManager
{
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static ExtentReports extent = ExtentManager.createExtentReports();

    public static synchronized ExtentTest getTest()
    {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static Logger Log = (Logger) LogManager.getLogger(logs.Log.class);

    public static synchronized ExtentTest startTest(String testName, String desc)
    {
        Log.info("Started Test - " + testName + " with description - " + desc);
        ExtentTest test = extent.createTest(testName, "<b>" + desc + "</b>");
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }


    /**
     *
     * @param stepName
     * @param status
     * @param message
     * @param ScreenShot
     * @param t Throwable t
     */
    public static synchronized void createAssertTestStepWithScreenshot(String stepName, Status status, String message, Boolean ScreenShot, Throwable... t) {

        String filepath = System.getProperty("user.dir") + File.separator + "src"  + File.separator + "test"
                + File.separator + "resources" + File.separator + "config" + File.separator + "config.properties";
        try
        {
            new ConfigProperties(filepath);
            switch(status.toString().toUpperCase())
            {
                case "INFO":
                    Log.info(stepName + " - " + message);

                    if(ScreenShot && ConfigProperties.getPropertyValue("info_step_screenshots").equalsIgnoreCase("yes"))
                        extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).info(message)
                                .addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
                    else
                        extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).info(message);
                    break;
                case "PASS":
                    Log.info("PASS - " + stepName + " - " + message);

                    if(ScreenShot && ConfigProperties.getPropertyValue("passed_step_screenshots").equalsIgnoreCase("yes"))
                        extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).pass(message)
                                .addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
                    else
                        extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).pass(message);
                    break;
                case "FAIL":
                    Log.error(stepName + " - " + message);
                    if(ScreenShot && ConfigProperties.getPropertyValue("failed_step_screenshots").equalsIgnoreCase("yes"))
                    {
                        if(t.length > 0)
                        {
                            t[0].printStackTrace();
                            for(int i = 0; i < t[0].getStackTrace().length; i++)
                            {
                                if(t[0].getStackTrace()[i].getClassName().contains("pages"))
                                {
                                    getTest().fail(message + "<br>" + t[0].getMessage().split("\\n")[0]
                                            + "<br>Exception found in class <b>" + t[0].getStackTrace()[i].getClassName()
                                            + "</b> line number - <b>" + t[0].getStackTrace()[i].getLineNumber() + "</b>");
                                }
                            }
                            extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).fail(message)
                                    .info(MarkupHelper.createCodeBlock(ExceptionUtils.getStackTrace(t[0])))
                                    .addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
                        }
                        else
                            extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).fail(message)
                                    .addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
                    }
                    else
                    if(t.length > 0)
                        extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).fail(message)
                                .info(MarkupHelper.createCodeBlock(ExceptionUtils.getStackTrace(t[0])));
                    else
                        extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).fail(message);

//                    Assert.fail();
                    break;
                case "WARNING":
                    Log.warn(stepName + " - " + message);

                    if(ScreenShot && ConfigProperties.getPropertyValue("warning_step_screenshots").equalsIgnoreCase("yes"))
                        extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName)
                                .warning(message).addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
                    else
                        extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).warning(message);
                    break;
                case "SKIP":
                    Log.info("SKIP - " + stepName + " - " + message);
                    extentTestMap.get((int) Thread.currentThread().getId()).createNode(stepName).skip(message)
                            .addScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64());
                    break;
            }
        }
        catch(Exception e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
            throw new RuntimeException(e);
        }
    }


    public static synchronized void flush()
    {
        extent.flush();
    }


    public synchronized static String base64Encode(File file)
    {
        if(file == null || !file.isFile())
        {
            return null;
        }
        try
        {
            @SuppressWarnings("resource") FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            if(fileInputStreamReader.read(bytes) != -1)
            {
                return "data:image/png;base64," + new String(Base64.getEncoder().encode(bytes), "UTF-8");
            }
            return null;
        }
        catch(Throwable e)
        {
            return null;
        }
    }
}
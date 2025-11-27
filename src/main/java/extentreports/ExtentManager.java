package extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import baselibrary.BaseTest;
import datahandlers.XMLHandler;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports createExtentReports() {
        // Generate a timestamp for unique folder names
        String timeStamp = new SimpleDateFormat("yy-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime());
        String folderPath = "./extent-reports/" ;

        //String filePath = folderPath + "/report.html";
        String filePath = folderPath + "report_" + timeStamp + "" +  ".html"; // Unique filename



        // Create the folder if it doesn't exist
        File reportDir = new File(folderPath);
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        // Create the ExtentSparkReporter with the dynamic file path
        ExtentSparkReporter reporter = new ExtentSparkReporter(filePath);
        reporter.viewConfigurer().viewOrder().as(new ViewName[]{
                ViewName.DASHBOARD,
                ViewName.TEST,
                ViewName.CATEGORY,
                ViewName.AUTHOR,
                ViewName.DEVICE,
                ViewName.EXCEPTION,
                ViewName.LOG
        }).apply();

        reporter.config().setReportName("Extent Report");
        extentReports.attachReporter(reporter);

        // Add system information
        extentReports.setSystemInfo("Environment", BaseTest.environment);
        extentReports.setSystemInfo("OS", BaseTest.testOS);
        extentReports.setSystemInfo("Browser", BaseTest.browser);
        extentReports.setSystemInfo("Version", BaseTest.version);
        extentReports.setSystemInfo("Mode", BaseTest.mode);
        extentReports.setSystemInfo("Author", System.getProperty("user.name"));
        extentReports.setSystemInfo("Build Number", BaseTest.buildNumber);

        return extentReports;
    }

    public synchronized static void MarkRerun() {
        Map<String, Integer> TestMap = new HashMap<>();
        List<Test> TestList = extentReports.getReport().getTestList();

        for (Test test : TestList) {
            if (TestMap.keySet().contains(test.getName()))
                TestMap.put(test.getName(), TestMap.get(test.getName()) + 1);
            else
                TestMap.put(test.getName(), 1);
        }

        String tests = "";
        for (Map.Entry<String, Integer> Entry : TestMap.entrySet()) {
            if (Entry.getValue() > 1) {
                tests = Entry.getKey();
                int count = 0;
                for (int j = 0; j < TestList.size(); j++) {
                    if (TestList.get(j).getName().equals(tests)) {
                        if (count > 0) {
                            TestList.get(j).setName(tests + "_ReRun_" + count);
                        }
                        count++;
                    }
                }
            }
        }
    }

    // Method to launch the report in the default browser
    public static void launchReport(String filePath) {
        try {
            File reportFile = new File(filePath);
            if (reportFile.exists()) {
                Desktop.getDesktop().browse(reportFile.toURI());
                System.out.println("Report launched successfully!");
            } else {
                System.out.println("Report file not found: " + reportFile.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

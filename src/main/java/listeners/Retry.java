package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import datahandlers.ConfigProperties;
import java.io.File;

public class Retry implements IRetryAnalyzer {

    private int count  = 0;
    String filepath = System.getProperty("user.dir") + File.separator + "src"  + File.separator + "test"
            + File.separator + "resources" + File.separator + "config" + File.separator + "config.properties";

    @Override
    public boolean retry(ITestResult iTestResult)
    {
        boolean value = false;
        new ConfigProperties(filepath);
        int maxTry = Integer.parseInt(ConfigProperties.getPropertyValue("retry_count"));

        if(ConfigProperties.getPropertyValue("retry_failed_tests").equals("yes"))
        {
            if (!iTestResult.isSuccess()) {                      //Check if test not succeed
                if (count < maxTry) {                            //Check if maxTry count is reached
                    count++;                                     //Increase the maxTry count by 1
                    iTestResult.setStatus(ITestResult.FAILURE);  //Mark test as failed
                    return true;                                 //Tells TestNG to re-run the test
                }
                else {
                    iTestResult.setStatus(ITestResult.FAILURE);  //If maxCount reached,test marked as failed
                }
            }
            else {
                iTestResult.setStatus(ITestResult.SUCCESS);      //If test passes, TestNG marks it as passed
            }
        }
        return value;
    }


}

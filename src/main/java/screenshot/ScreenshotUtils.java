package screenshot;

import baselibrary.BaseTest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.firefox.FirefoxDriver;
import logs.Log;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ScreenshotUtils
{
    // This class is to handle the change in third party library
    @SneakyThrows
    public static void captureScreenshotAsFile(String testName) throws IOException {
        File source = ((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.FILE);
        String screenshotPath = "";
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        screenshotPath = BaseTest.folderPath + File.separator + timeStamp + ".jpg";

        FileUtils.copyFile(source, new File(screenshotPath));
    }

    public static String captureScreenshotAsBase64()
    {
        try
        {
            if (Objects.nonNull(BaseTest.getDriver()))
            {
                if(BaseTest.getDriver() instanceof FirefoxDriver)
                    return ((FirefoxDriver) BaseTest.getDriver()).getFullPageScreenshotAs(OutputType.BASE64);

                else
                    return ((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.BASE64);
            }
        }
        catch (Exception ex)
        {
            Log.Log.error("No Driver Instance present. Unable to take Screenshot",ex);
        }

        return null;
    }
}

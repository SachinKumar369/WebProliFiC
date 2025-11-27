package pages.POPages.SingleApproval.withoutRQ;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.apache.commons.math3.analysis.function.Sin;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SingleApproval {

    public SingleApproval(WebDriver driver){
        PageFactory.initElements(driver,this);
    }


    WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
    public void Approve(){
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

            WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOK")));

            try {
                OKBtn.click();
                System.out.println("OK Button clicked successfully!");
            } catch (Exception e) {
                System.out.println("Normal click failed, trying JavaScript click.");
                ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].click();", OKBtn);
            }

        }


         catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Approve", Status.FAIL,"Exception in Approval",true,e);
        }
    }
}

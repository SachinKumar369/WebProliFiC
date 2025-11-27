package VendorSetup.WithoutApproval;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import listeners.RetryAnalyzer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ItemPage.ItemPage;
import pages.Login.Login;
import pages.POPages.WithoutApproval.withRQ.InventoryControl;
import pages.VendorCreation.ExcelDownload;
import pages.VendorCreation.ExcelUpload;
import pages.VendorCreation.XMLModifier;
import utils.DynamicWait;

import java.lang.reflect.Method;

public class ExcelDownloadTest extends BaseTest {

    private final ThreadLocal<Login> LoginThread = new ThreadLocal<>();
    private final ThreadLocal<ExcelUpload> ExcelThread = new ThreadLocal<>();
    private final ThreadLocal<ExcelDownload> ExcelThread1 = new ThreadLocal<>();
    private final ThreadLocal<XMLModifier> XMLModifierThread =new ThreadLocal<>();

    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        LoginThread.set(new Login(getDriver()));
        ExcelThread.set(new ExcelUpload(getDriver()));
        ExcelThread1.set(new ExcelDownload(getDriver()));
        XMLModifierThread.set(new XMLModifier(getDriver()));

    }


    @Test(priority = 1, retryAnalyzer = RetryAnalyzer.class)
    public void Login(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " Portal Login");
        try {
            LoginThread.get().login0419(m);



        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(priority = 2)
    public void  ItemCreate(Method m){
        ExtentTestManager.startTest(this.getClass().getSimpleName()+" :: "+ m.getName(),"Item Creation");
        try {
          ExcelThread.get().VendorSetup();
          //ExcelThread.get().ExcelDwonload1();
          ExcelThread1.get().excelDownload();
            //DynamicWait.longWait();
          XMLModifierThread.get().modifyXMLFile();
          ExcelThread1.get().Upload();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 2)
    public void  ItemDelete(Method m){
        ExtentTestManager.startTest(this.getClass().getSimpleName()+" :: "+ m.getName(),"Item Creation");
        try {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }






}

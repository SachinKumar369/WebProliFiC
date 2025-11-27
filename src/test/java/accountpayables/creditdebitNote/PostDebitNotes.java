package accountpayables.creditdebitNote;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import listeners.RetryAnalyzer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.POPages.WithoutApproval.withRQ.Login;
import pages.accountPayables.creditdebitNote.CreditDebitNotes;
import pages.accountPayables.payadvance.PO;

import java.lang.reflect.Method;

/**
 * Author: SACHIN KUMAR (replace sachin with your name or a fixed string)
 * Date: 23-01-2025
 */
public class PostDebitNotes extends BaseTest {

    private final ThreadLocal<Login> LoginThread = new ThreadLocal<>();
    private final ThreadLocal<PO> po = new ThreadLocal<>();
    private final ThreadLocal<CreditDebitNotes> notes = new ThreadLocal<>();

    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        setDriver();
        LoginThread.set(new Login(getDriver()));
        po.set(new PO(getDriver()));
        notes.set(new CreditDebitNotes(getDriver()));

    }



    /**
     * @Test_Method_Description : For PO Creation
     * @Modified_By : Sachin Kumar
     * @Modified_Date : 10/12/2024
     */
    @Test(priority = 1, retryAnalyzer = RetryAnalyzer.class)
    public void Login(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), " Portal Login");
        try {
            LoginThread.get().login0419(m);
            po.get().Switch0419();


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Login", Status.FAIL, "Exception found in Login", true, e);
        }
    }

    @Test(priority = 2)

    public void Post_DebitNote(Method m) {
        ExtentTestManager.startTest(this.getClass().getSimpleName() + " :: " + m.getName(), "Post_DebitNote");

        try {
            String noteType = System.getProperty("noteType", "DEBIT"); // default

            notes.get().accountPayable();
            //notes.get().performAPEntryFlow();
            notes.get().performAPEntryFlow(CreditDebitNotes.NoteType.valueOf(noteType.trim().toUpperCase()));





            //  ExtentTestManager.createAssertTestStepWithScreenshot("PO Creation", Status.PASS, "PO Creation Pass", true);

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Post_DebitNote", Status.FAIL, "Exception found in Post_DebitNote", true, e);
        }
    }


}

package pages.SRV;

import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditSRV {


    // Constructor
    public EditSRV(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    ///  WebElements
    @FindBy(id = "")
    private WebElement Inventory;


    public void RQCreate(){

        try {

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("RQ Create", Status.FAIL,"Error while creating RQ",true,e);
        }

    }



}

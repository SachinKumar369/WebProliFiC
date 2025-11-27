package pages.practice;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utilities;

public class PracticePage {


    // Constructor
    public PracticePage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    ///  WebElements
    @FindBy(id = "LinkProduct2")
    private WebElement Inventory;
    @FindBy(id = "divmodule_2")
    private WebElement Inventory2;
    @FindBy(xpath = "//a[text()='Store Receipt from Vendor']")
    private WebElement SRV;




    public void Inventory(){

        try {

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            Utilities.Click(Inventory);



            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(Inventory2);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("RQ Create", Status.FAIL,"Error while creating RQ",true,e);
        }

    }


    public void RQEdit(){

        try {

        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("RQ Edit", Status.FAIL,"Error while creating RQ",true,e);
        }
    }



}

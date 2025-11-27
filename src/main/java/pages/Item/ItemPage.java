package pages.Item;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utilities;

import java.util.Base64;
import java.util.List;

public class ItemPage {

    /*
    Constructor
     */

    public ItemPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }


    @FindBy(id="LinkProduct2")
    private WebElement Inventory;
    @FindBy(id = "divmodule_2")
    private WebElement InventoryModule;
    @FindBy(xpath = "//a[text()='Items Setup']")
    private WebElement ItemSetup;
    @FindBy(xpath = "//td[@class=\"ControlItemStyle\"][2]")
    private List<WebElement> item;




    /*

     */

    public void ItemCreate(){
        try {


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(Inventory);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Item Create", Status.FAIL,"Item Creation Failed",true,e);
        }
    }


}

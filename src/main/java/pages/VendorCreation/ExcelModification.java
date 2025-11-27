package pages.VendorCreation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ExcelModification {
    public ExcelModification(WebDriver driver){
        PageFactory.initElements(driver,this);
    }


    public void ModifyXML(){
        try {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

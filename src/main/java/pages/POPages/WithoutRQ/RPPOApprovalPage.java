package pages.POPages.WithoutRQ;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import datahandlers.PdfHandler;
import extentreports.ExtentTestManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DynamicWait;
import utils.GetLatestFile;
import utils.Utilities;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.List;

public class RPPOApprovalPage {

    public RPPOApprovalPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "cphBody_ddlApproved")
    private WebElement Approval;
    @FindBy(id = "btnOK")
    private WebElement OKBtn;
    @FindBy(id = "cphDialogHeader_btnSaveClose")
    private WebElement SaveBtn;
    @FindBy(id = "lblErrorDesc")
    private WebElement POConfirmation;
//    @FindBy(id = "cphBody_lblLastApprovedStatus")
//    private WebElement ApprovalStatus;
    @FindBy(xpath = "//*[@id=\"cphBody_lblLastApprovedStatus\"]")
    private WebElement ApprovalStatus;
    @FindBy(id = "cphDialogHeader_btnPrint")
    private WebElement Print;
    @FindBy(id = "rdoFinal")
    private WebElement rdFinal;
    @FindBy(xpath = "//input[starts-with(@id,'po_item_amount_10_')]")
    private List<WebElement> ItemAmount;
    @FindBy(xpath = "//input[starts-with(@id,'item_id_1_')]")
    private List<WebElement> ItemID;
    @FindBy(xpath = "//input[starts-with(@id,'qty_ordered_5_')]")
    private List<WebElement> ItemQuantity;
//    @FindBy(xpath = "//input[starts-with(@id,'po_item_amount_10_')]")
//    private List<WebElement> ItemNames;
//    @FindBy(xpath = "//input[starts-with(@id,'po_item_amount_10_')]")
//    private List<WebElement> ItemNames;

    @FindBy(id = "txtTo")
    private WebElement TOEmail;
    @FindBy(id = "txtMailBody")
    private WebElement Comment;
    @FindBy(id = "btnCancel")
    private WebElement CancelBtn;



    public void Approve(){
    try {

        WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
        BaseTest.getDriver().switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

        Utilities.WaitTillElementDisplayed(BaseTest.getDriver(),OKBtn);
        Utilities.Click(BaseTest.getDriver(),OKBtn);
        //Utilities.selectBy(BaseTest.getDriver(),);

        BaseTest.getDriver().switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeDlg"));
        Utilities.selectBy(BaseTest.getDriver(), "visibletext", Approval, "Approve");

        Utilities.Click(BaseTest.getDriver(),SaveBtn);

        BaseTest.getDriver().switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
        wait.until(ExpectedConditions.visibilityOf(POConfirmation));

        Utilities.Click(BaseTest.getDriver(),OKBtn);


        // Store the Entered Details to for the validations
       BaseTest.getDriver().switchTo().defaultContent();
       wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeDlg"));

        List<String> ratesWeb = ItemAmount.stream().map(e -> e.getAttribute("value")).filter(s -> !s.isEmpty()).toList();
        System.out.println("Rates are: " + ratesWeb);
        List<String> itemIDWeb = ItemID.stream().map(e -> e.getAttribute("value")).filter(s -> !s.isEmpty()).toList();
        System.out.println("ItemID are: " + itemIDWeb);
        List<String> quantitiesWeb = ItemQuantity.stream().map(e -> e.getAttribute("value")).filter(s -> !s.isEmpty()).toList();
        System.out.println("Quantity are: " + quantitiesWeb);







        BaseTest.getDriver().switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeDlg"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cphBody_lblLastApprovedStatus")));
        String getApprovalStatus = ApprovalStatus.getText();

        BaseTest.getDriver().switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeDlg"));
        Utilities.Click(BaseTest.getDriver(),Print);


        try {
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            try
            {
                wait.until(ExpectedConditions.elementToBeClickable(rdFinal));
                Utilities.ClickCheckBox(rdFinal);
            }
            catch (Exception e){

            }

            WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"btnOk\"]")));

            try {
                OKBtn.click();
                System.out.println("OK Button clicked successfully!");
            } catch (Exception e) {
                System.out.println("Normal click failed, trying JavaScript click.");
                ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].click();", OKBtn);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        DynamicWait.longWait();
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        DynamicWait.longWait();

        String expected = "Approved";

        System.out.println(getApprovalStatus);
        if (expected.equals(getApprovalStatus)) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Approval Status", Status.PASS, "Approval Status is :Approved   ", true);
        } else {
            ExtentTestManager.createAssertTestStepWithScreenshot("Approval Status", Status.FAIL, "Error in Approval Status   ", true);

        }


        BaseTest.getDriver().switchTo().defaultContent();
        BaseTest.getDriver().switchTo().frame("iframeGridDialog");

        Utilities.Click(BaseTest.getDriver(),CancelBtn);
















        //validatePDFData(itemIDWeb,quantitiesWeb, ratesWeb,totalAmountWeb);

       // validatePDFData(itemIDWeb,quantitiesWeb, ratesWeb);   uncomment if want to validate pdf


//        Utilities.Click(BaseTest.getDriver(),Approval);
//
//        Actions actions = new Actions(BaseTest.getDriver());
//        actions.sendKeys(Keys.ARROW_DOWN).click().perform();
    } catch (Exception e) {
        ExtentTestManager.createAssertTestStepWithScreenshot("Approval", Status.FAIL, "Exception found in Method - Approval", true, e);

    }
  }

    public void ApproveDO(){
        try {

            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

            Utilities.WaitTillElementDisplayed(BaseTest.getDriver(),OKBtn);
            Utilities.Click(BaseTest.getDriver(),OKBtn);
            //Utilities.selectBy(BaseTest.getDriver(),);

//            BaseTest.getDriver().switchTo().defaultContent();
//            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeDlg"));
//            Utilities.selectBy(BaseTest.getDriver(), "visibletext", Approval, "Approve");
//
//            Utilities.Click(BaseTest.getDriver(),SaveBtn);
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
//            wait.until(ExpectedConditions.visibilityOf(POConfirmation));
//
//            Utilities.Click(BaseTest.getDriver(),OKBtn);


            // Store the Entered Details to for the validations
//            BaseTest.getDriver().switchTo().defaultContent();
//            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeDlg"));
//
//            List<String> ratesWeb = ItemAmount.stream().map(e -> e.getAttribute("value")).filter(s -> !s.isEmpty()).toList();
//            System.out.println("Rates are: " + ratesWeb);
//            List<String> itemIDWeb = ItemID.stream().map(e -> e.getAttribute("value")).filter(s -> !s.isEmpty()).toList();
//            System.out.println("ItemID are: " + itemIDWeb);
//            List<String> quantitiesWeb = ItemQuantity.stream().map(e -> e.getAttribute("value")).filter(s -> !s.isEmpty()).toList();
//            System.out.println("Quantity are: " + quantitiesWeb);
//
//
//
//
//
//
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeDlg"));
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cphBody_lblLastApprovedStatus")));
//            String getApprovalStatus = ApprovalStatus.getText();

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeDlg"));
            Utilities.Click(BaseTest.getDriver(),Print);


            try {
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
                try
                {
                    wait.until(ExpectedConditions.elementToBeClickable(rdFinal));
                    Utilities.ClickCheckBox(rdFinal);
                }
                catch (Exception e){

                }

                WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"btnOk\"]")));

                try {
                    OKBtn.click();
                    System.out.println("OK Button clicked successfully!");
                } catch (Exception e) {
                    System.out.println("Normal click failed, trying JavaScript click.");
                    ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].click();", OKBtn);
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            DynamicWait.longWait();
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            DynamicWait.longWait();

            String expected = "Approved";

//            System.out.println(getApprovalStatus);
//            if (expected.equals(getApprovalStatus)) {
//                ExtentTestManager.createAssertTestStepWithScreenshot("Approval Status", Status.PASS, "Approval Status is :Approved   ", true);
//            } else {
//                ExtentTestManager.createAssertTestStepWithScreenshot("Approval Status", Status.FAIL, "Error in Approval Status   ", true);
//
//            }


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            Utilities.Click(BaseTest.getDriver(),CancelBtn);
















            //validatePDFData(itemIDWeb,quantitiesWeb, ratesWeb,totalAmountWeb);

            // validatePDFData(itemIDWeb,quantitiesWeb, ratesWeb);   uncomment if want to validate pdf


//        Utilities.Click(BaseTest.getDriver(),Approval);
//
//        Actions actions = new Actions(BaseTest.getDriver());
//        actions.sendKeys(Keys.ARROW_DOWN).click().perform();
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Approval", Status.FAIL, "Exception found in Method - Approval", true, e);

        }
    }


    public static void validate() {
        try {
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));

            // 1️⃣ Switch to iframe if needed
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeDlg"));

            // 2️⃣ Ensure the table is visible
            //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[contains(@class, 'tableClassName')]"))); // Adjust XPath

            // 3️⃣ Extract Item Names, Quantities, and Rates (Using Correct XPaths)
            List<WebElement> itemNames = BaseTest.getDriver().findElements(By.xpath("//input[starts-with(@id,'item_id_1_')]"));
            List<WebElement> quantities = BaseTest.getDriver().findElements(By.xpath("//input[starts-with(@id,'item_quantity_')]")); // Adjust XPath
            List<WebElement> rates = BaseTest.getDriver().findElements(By.xpath("//input[starts-with(@id,'item_rate_')]")); // Adjust XPath
          //  WebElement totalAmountElement = BaseTest.getDriver().findElement(By.xpath("//input[@id='totalAmount']")); // Adjust XPath

            // 4️⃣ Convert WebElements to Strings (Use getAttribute("value") for Input Fields)
            List<String> itemNamesWeb = itemNames.stream().map(e -> e.getAttribute("value")).filter(s -> !s.isEmpty()).toList();
            List<String> quantitiesWeb = quantities.stream().map(e -> e.getAttribute("value")).filter(s -> !s.isEmpty()).toList();
            List<String> ratesWeb = rates.stream().map(e -> e.getAttribute("value")).filter(s -> !s.isEmpty()).toList();
           // String totalAmountWeb = totalAmountElement.getAttribute("value").trim();

            String totalAmountWeb = " ";
            // 5️⃣ Debugging: Print extracted values
            System.out.println("✅ Webpage Data:");
            System.out.println("Items: " + itemNamesWeb);
            System.out.println("Quantities: " + quantitiesWeb);
           // System.out.println("Rates: " + ratesWeb);
            System.out.println("Total Amount: " + totalAmountWeb);

            // 6️⃣ Validate against PDF data

           // validatePDFData(itemNamesWeb, quantitiesWeb, ratesWeb, totalAmountWeb);
            validatePDFData(itemNamesWeb,quantitiesWeb,ratesWeb);

        } catch (Exception e) {
            System.out.println("❌ Error Extracting Webpage Data: " + e.getMessage());
        }
    }

   // public void validatePDFData(List<String> itemNamesWeb, List<String> quantitiesWeb, List<String> ratesWeb, String totalAmountWeb) {
   public static void validatePDFData(List<String> itemNamesWeb, List<String> quantitiesWeb, List<String> ratesWeb) {

   // public void validatePDFData(  List<String> ratesWeb) {
        // Get latest downloaded PDF file
      //  String path = "";
        String path = System.getProperty("user.dir") + File.separator + "DownloadPath" ;

        File latestPdf = GetLatestFile.getLatestFileFromDirectory(path);

        if (latestPdf != null) {
            System.out.println("Latest PDF found: " + latestPdf.getAbsolutePath());

            // Extract text from PDF
            String pdfContent = PdfHandler.ReadPdf(latestPdf);

//            System.out.println("\n==== Extracted PDF Content ====");
//            System.out.println(pdfContent);
//            System.out.println("==== PDF Content End ====\n");

            // **Validation**
            boolean allMatched = true;

            // Check if each item name from webpage exists in PDF
            for (String item : itemNamesWeb) {
                if (!pdfContent.contains(item)) {
                    System.out.println("Mismatch Found: Item Name '" + item + "' is NOT in PDF");
                    allMatched = false;
                }
            }

            // Check if each quantity from webpage exists in PDF
            for (String quantity : quantitiesWeb) {
                if (!pdfContent.contains(quantity)) {
                    System.out.println("Mismatch Found: Quantity '" + quantity + "' is NOT in PDF");
                    allMatched = false;
                }
            }

            // Check if each rate from webpage exists in PDF
//            for (String rate : ratesWeb) {
//                if (!pdfContent.contains(rate)) {
//                    System.out.println("Mismatch Found: Rate '" + rate + "' is NOT in PDF");
//                    allMatched = false;
//                }
//            }

            // Check if total amount from webpage exists in PDF
//            if (!pdfContent.contains(totalAmountWeb)) {
//                System.out.println("Mismatch Found: Total Amount '" + totalAmountWeb + "' is NOT in PDF");
//                allMatched = false;
//            }

            // Log final validation result
            if (allMatched) {
                System.out.println("✅ PDF Data Matches Webpage Data!");
                ExtentTestManager.createAssertTestStepWithScreenshot("PDF Validation", Status.PASS, "PDF data matches webpage data", true);
            } else {
                System.out.println("❌ PDF Data DOES NOT Match Webpage Data!");
                ExtentTestManager.createAssertTestStepWithScreenshot("PDF Validation", Status.FAIL, "PDF data mismatch", true);
            }

        } else {
            System.out.println("❌ No PDF file found in directory.");
        }
    }



    public void Approve1(){
        try {

            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));

            try {
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));

                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(),OKBtn);
                Utilities.Click(BaseTest.getDriver(),OKBtn);
            } catch (Exception e) {

            }
            //Utilities.selectBy(BaseTest.getDriver(),);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeDlg"));
            Utilities.selectBy(BaseTest.getDriver(), "visibletext", Approval, "Approve");

            Utilities.Click(BaseTest.getDriver(),SaveBtn);

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
            wait.until(ExpectedConditions.visibilityOf(POConfirmation));

            Utilities.Click(BaseTest.getDriver(),OKBtn);


            // Store the Entered Details to for the validations
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeDlg"));

            List<String> ratesWeb = ItemAmount.stream().map(e -> e.getAttribute("value")).filter(s -> !s.isEmpty()).toList();
            System.out.println("Rates are: " + ratesWeb);
            List<String> itemIDWeb = ItemID.stream().map(e -> e.getAttribute("value")).filter(s -> !s.isEmpty()).toList();
            System.out.println("ItemID are: " + itemIDWeb);
            List<String> quantitiesWeb = ItemQuantity.stream().map(e -> e.getAttribute("value")).filter(s -> !s.isEmpty()).toList();
            System.out.println("Quantity are: " + quantitiesWeb);







            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeDlg"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cphBody_lblLastApprovedStatus")));
            String getApprovalStatus = ApprovalStatus.getText();

            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeDlg"));
            Utilities.Click(BaseTest.getDriver(),Print);


            try {
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeGridDialog"));
                try
                {
                    wait.until(ExpectedConditions.elementToBeClickable(rdFinal));
                    Utilities.ClickCheckBox(rdFinal);
                }
                catch (Exception e){

                }

                WebElement OKBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"btnOk\"]")));

                try {
                    OKBtn.click();
                    System.out.println("OK Button clicked successfully!");
                } catch (Exception e) {
                    System.out.println("Normal click failed, trying JavaScript click.");
                    ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].click();", OKBtn);
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            DynamicWait.longWait();
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            DynamicWait.longWait();

            String expected = "Approved";

            System.out.println(getApprovalStatus);
            if (expected.equals(getApprovalStatus)) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Approval Status", Status.PASS, "Approval Status is :Approved   ", true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Approval Status", Status.FAIL, "Error in Approval Status   ", true);

            }



            //validatePDFData(itemIDWeb,quantitiesWeb, ratesWeb,totalAmountWeb);

            // validatePDFData(itemIDWeb,quantitiesWeb, ratesWeb);   uncomment if want to validate pdf


//        Utilities.Click(BaseTest.getDriver(),Approval);
//
//        Actions actions = new Actions(BaseTest.getDriver());
//        actions.sendKeys(Keys.ARROW_DOWN).click().perform();




        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Approval", Status.FAIL, "Exception found in Method - Approval", true, e);

        }
    }

}

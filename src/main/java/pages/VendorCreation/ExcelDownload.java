package pages.VendorCreation;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DynamicWait;
import utils.Utilities;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;



public class ExcelDownload {


    public ExcelDownload(WebDriver driver){
        PageFactory.initElements(driver,this);
    }


    @FindBy(xpath = "//a[text()='Vendor Setup']")
    private WebElement VendorSetup;
    @FindBy(id = "LinkProduct2")
    private WebElement Inventory;
    @FindBy(id = "divmodule_2")
    private WebElement InventoryModule;
    @FindBy(id = "cphDialogHeader_btnAddRecords")
    private WebElement Add;
    @FindBy(id = "cphBody_lblUploadFromExcel")
    private WebElement UploadExcel;
    @FindBy(xpath = "//*[@id='btnDownLoadTemplate']")
    private WebElement Download;
    @FindBy(id = "lblFileDialogUploadDownload")
    private WebElement Downloaddlg;
    @FindAll({
            @FindBy(xpath = "//input[@id='btnDownLoadTemplate']"),
            @FindBy(xpath = "//input[@name='btnDownLoadTemplate']"),
//            @FindBy(xpath = "//input[@value='Download Template']"),
//            @FindBy(xpath = "//input[@type='submit' and @value='Download Template']"),
            @FindBy(xpath = "//input[@type='submit' and contains(@value, 'Download')]"),

            @FindBy(xpath = "//input[contains(@id, 'btnDownLoadTemplate')]"),
            @FindBy(xpath = "//input[@type='submit' and @name='btnDownLoadTemplate' and @value='Download Template']")
    })
    private WebElement downloadTemplateButton;

    @FindBy(xpath = "//*[@id='cphBody_divFileDialog']")
    private WebElement ConfDlg;

    @FindBy(id = "FileDlg")
    private WebElement ChoseFile;

    @FindBy(id = "cphBody_lblUploadFromExcel")
    private WebElement uploadExcelButton;

    private WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(),Duration.ofSeconds(20));

    public void VendorSetup(){
        try {
            BaseTest.getDriver().manage().window().maximize();
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Step 3: Click Inventory
            Utilities.Click(Inventory);

            // Step 4: Switch to frame again
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Step 5: Click Inventory Module
            Utilities.Click(InventoryModule);

            // Step 6: Click Vendor Setup
            Utilities.Click(VendorSetup);

            // Step 7: Switch to frame again
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            // Step 8: Click Add
            Utilities.Click(Add);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void ExcelDwonload1(){
        try {

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(UploadExcel);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            System.out.println("Get Text :"+ConfDlg.getText());
            BaseTest.getDriver().switchTo().frame("iframeFileDialog");
            System.out.println("Download");

            try {
                Utilities.Click(downloadTemplateButton);
            } catch (NumberFormatException e) {
                downloadTemplateButton.click();
            } catch (RuntimeException e) {
                Utilities.JavaScriptClick(BaseTest.getDriver(),downloadTemplateButton);
            } catch (Exception e) {
                Utilities.ActionClick(BaseTest.getDriver(),downloadTemplateButton);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void excelDownload() {
        try {
            // Initial steps
            switchToIframe("MultiPageiframeBrw");
            //clickUploadExcelButton();

            Utilities.Click(UploadExcel);
            // Wait for dialog with enhanced conditions
            {
             //    waitForDialogAppearance();
            }
            // Try JavaScript search with retry
//            WebElement jsButton = retryFindJavaScriptButton(3);
//            if (jsButton != null) {
//                clickButton(jsButton, "JavaScript found");
//                return;
//            }

            // Search main dialog iframes
            if (searchDialogIframes()) return;

            // Deep search in MultiPageiframeBrw
            switchToIframe("MultiPageiframeBrw");
            if (searchFramesRecursively(3)) return;



            // Final fallback
            throw new NoSuchElementException("Button not found after exhaustive search");

            //DynamicWait.longWait();





        } catch (Exception e) {
            //handleException(e);
        } finally {
            //BaseTest.getDriver().switchTo().defaultContent();
        }
    }



    private boolean isButtonAccessible(WebElement button) {
        try {
            return button != null && button.isDisplayed() && button.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    private void clickButton(WebElement button, String iframeId) {
        wait.until(ExpectedConditions.elementToBeClickable(button));
        ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].scrollIntoView(true);", button);
        try {
            Utilities.Click(button);
        } catch (Exception e) {
            try {
                Utilities.JavaScriptClick(BaseTest.getDriver(), button);
            } catch (Exception e2) {
                Utilities.ActionClick(BaseTest.getDriver(), button);
            }
        }
        System.out.println("Clicked Download Template button in " + iframeId);
    }

    private boolean tryNestedIframes(String parentIframe) {
        List<WebElement> iframes = BaseTest.getDriver().findElements(By.tagName("iframe"));
        System.out.println("Found " + iframes.size() + " nested iframes in " + parentIframe);

        for (WebElement iframe : iframes) {
            String iframeId = iframe.getAttribute("id");
            if (iframeId == null || iframeId.isEmpty()) {
                iframeId = "Unnamed iframe";
            }
            System.out.println("Trying nested iframe: " + iframeId);

            try {
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(parentIframe));
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));

                System.out.println("Source of " + iframeId + ": " + BaseTest.getDriver().getPageSource().substring(0, Math.min(500, BaseTest.getDriver().getPageSource().length())) + "...");

                if (isButtonAccessible(downloadTemplateButton)) {
                    clickButton(downloadTemplateButton, iframeId);
                    return true;
                }

                // Recurse into deeper iframes
                if (tryNestedIframes(iframeId)) {
                    return true;
                }
            } catch (Exception e) {
                System.out.println("Failed to process nested iframe " + iframeId + ": " + e.getMessage());
            } finally {
                BaseTest.getDriver().switchTo().defaultContent();
            }
        }
        return false;
    }

    private boolean tryAllNestedIframes(String parentIframe) {
        List<WebElement> iframes = BaseTest.getDriver().findElements(By.tagName("iframe"));
        System.out.println("Found " + iframes.size() + " nested iframes in " + parentIframe);

        for (WebElement iframe : iframes) {
            String iframeId = iframe.getAttribute("id");
            if (iframeId == null || iframeId.isEmpty()) {
                iframeId = "Unnamed iframe";
            }
            System.out.println("Trying nested iframe: " + iframeId);

            try {
                BaseTest.getDriver().switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(parentIframe));
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));

                System.out.println("Full source of " + iframeId + ": " + BaseTest.getDriver().getPageSource());

                wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
                System.out.println("Body loaded in " + iframeId);

                if (isButtonAccessible(downloadTemplateButton)) {
                    clickButton(downloadTemplateButton, iframeId);
                    return true;
                }

                // Recurse into deeper iframes
                if (tryNestedIframes(iframeId)) {
                    return true;
                }
            } catch (Exception e) {
                System.out.println("Failed to process nested iframe " + iframeId + ": " + e.getMessage());
            } finally {
                BaseTest.getDriver().switchTo().defaultContent();
            }
        }
        return false;
    }



    private boolean switchToIframe(String iframeId) {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeId));
            // Wait for content to load inside the iframe
            wait.until(d -> d.findElement(By.tagName("body")).isDisplayed());
            return true;
        } catch (Exception e) {
            System.out.println("Failed to switch to iframe: " + iframeId + " - " + e.getMessage());
            return false;
        }
    }

    private WebElement findDownloadButtonInCurrentContext() {
        List<By> locators = Arrays.asList(
//                By.id("btnDownLoadTemplate"),
//                By.id("btnDownloadTemplate"),
                By.xpath("//input[@value='Download Template']"),
                By.xpath("//button[contains(text(), 'Download Template')]")
        );

        for (By locator : locators) {
            try {
                WebElement button = BaseTest.getDriver().findElement(locator);
                if (button.isDisplayed() && button.isEnabled()) return button;
            } catch (Exception ignored) {}
        }
        return null;
    }

    private boolean searchFramesRecursively(int depth) {
        if (depth <= 0) return false;

        List<WebElement> iframes = BaseTest.getDriver().findElements(By.tagName("iframe"));
        for (WebElement iframe : iframes) {
            String iframeId = iframe.getAttribute("id");
            try {
                BaseTest.getDriver().switchTo().frame(iframe);
                WebElement button = findDownloadButtonInCurrentContext();
                if (button != null) {
                    clickButton(button, "Nested iframe: " + iframeId);
                    return true;
                }
                // Recursive call for nested iframes
                if (searchFramesRecursively(depth - 1)) return true;
            } catch (Exception e) {
                System.out.println("Error in iframe " + iframeId + ": " + e.getMessage());
            } finally {
                BaseTest.getDriver().switchTo().parentFrame();
            }
        }
        return false;
    }

    private WebElement findButtonWithJavaScript() {
        String script =
                "function search() {" +
                        "   let selectors = ['#btnDownLoadTemplate', '#btnDownloadTemplate', " +
                        "       'input[value=\"Download Template\"], button:contains(\"Download Template\")'];" +
                        "   for (let s of selectors) {" +
                        "       let elem = document.querySelector(s);" +
                        "       if (elem) return elem;" +
                        "   }" +
                        "   for (let frame of document.getElementsByTagName('iframe')) {" +
                        "       try {" +
                        "           let doc = frame.contentDocument;" +
                        "           for (let s of selectors) {" +
                        "               let elem = doc.querySelector(s);" +
                        "               if (elem) return elem;" +
                        "           }" +
                        "       } catch(e) {}" +
                        "   }" +
                        "   return null;" +
                        "}" +
                        "return search();";

        try {
            return (WebElement) ((JavascriptExecutor) BaseTest.getDriver()).executeScript(script);
        } catch (Exception e) {
            System.out.println("JS search failed: " + e.getMessage());
            return null;
        }
    }





    // Add this method to your ExcelUpload class
    private boolean searchDialogIframes() {
        String[] dialogIframes1 = {"MultiPageiframeDlg", "MultiPageiframeSubDlg",
                "MultiPageiframeDialog1", "MultiPageiframeDialog2"};
        String[] dialogIframes = {"MultiPageiframeDialog2"};

        for (String iframeId : dialogIframes) {
            try {
                if (!switchToIframe(iframeId)) continue;

                System.out.println("Searching in dialog iframe: " + iframeId);
                WebElement button = findDownloadButtonInCurrentContext();

                if (button != null) {
                    clickButton(button, iframeId);
                    return true;
                }

                // Check nested iframes
                if (searchFramesRecursively(2)) return true;

            } catch (Exception e) {
                System.out.println("Error in dialog iframe " + iframeId + ": " + e.getMessage());
            } finally {
                BaseTest.getDriver().switchTo().defaultContent();
            }
        }
        return false;
    }

    // Add this helper method for retrying JavaScript search
    private WebElement retryFindJavaScriptButton(int maxAttempts) {
        for (int i = 0; i < maxAttempts; i++) {
            WebElement button = findButtonWithJavaScript();
            if (button != null) return button;
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        }
        return null;
    }

    // Add this method for clicking upload button
    private void clickUploadExcelButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(uploadExcelButton));
            Utilities.Click(uploadExcelButton);
            System.out.println("Clicked UploadExcel successfully");
        } catch (Exception e) {
            throw new RuntimeException("Failed to click UploadExcel button", e);
        }
    }

    private void waitForDialogAppearance() {
        try {
            new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
//                    .until(ExpectedConditions.or(
//                            ExpectedConditions.visibilityOfElementLocated(By.id("MultiPagedivDlg")),
//                            ExpectedConditions.visibilityOfElementLocated(By.id("MultiPagedivSubDlg")),
//                            ExpectedConditions.visibilityOfElementLocated(By.id("MultiPagedivDialog1")),
//                            ExpectedConditions.visibilityOfElementLocated(By.id("MultiPagedivDialog2")))
//                    );
        } catch (TimeoutException e) {
            System.out.println("Dialog containers not found within timeout");
        }
    }

    private void handleException(Exception e) {
        System.out.println("Error during execution: " + e.getMessage());
        ExtentTestManager.createAssertTestStepWithScreenshot("Excel Download", Status.FAIL,
                "Failed to perform operation", true, e);
        throw new RuntimeException(e);
    }

    public void Upload(){
        try {

            Utilities.Click(UploadExcel);
            Utilities.Click(UploadExcel);
            BaseTest.getDriver().switchTo().defaultContent();

            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            BaseTest.getDriver().switchTo().frame("iframeFileDialog");
            Utilities.Click(ChoseFile);

            System.out.println("Clicked on Chose File");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}

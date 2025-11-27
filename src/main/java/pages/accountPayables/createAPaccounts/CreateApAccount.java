package pages.accountPayables.createAPaccounts;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class CreateApAccount extends Utilities {
    @FindBy(xpath = "//a[text()='Accounting']")
    private WebElement accounting;
    @FindBy(id = "divmodule_1")
    private WebElement accountPayble;
    @FindBy(xpath = "//a[text()='Create/Modify AP Account']")
    private WebElement createAp;
    @FindBy(id = "cphDialogHeader_btnAddRecords")
    private WebElement Add;
    @FindBy(id = "cphBody_imgPopupap_type")
    private WebElement AccountGroup;
    @FindBy(id = "td_0_0")
    private WebElement SelectAccGroup;
    @FindBy(id = "cphBody_txtap_id")
    private WebElement AccountID;
    @FindBy(id = "cphBody_txtap_name")
    private WebElement AccountName;
    @FindBy(id = "cphBody_txtap_address_1")
    private WebElement Address;
    @FindBy(id = "cphBody_txtap_city")
    private WebElement City;
    @FindBy(id = "cphBody_txtap_state")
    private WebElement State;
    @FindBy(id = "cphBody_txtap_zip")
    private WebElement Zip;
    @FindBy(id = "cphBody_txtap_country")
    private WebElement Country;
    @FindBy(id = "cphBody_ddlstate")
    private WebElement GstState;
    @FindBy(id = "cphDialogHeader_btnSave")
    private WebElement Save;
    @FindBy(id = "lblErrorDesc")
    private WebElement ConfDlg;
    @FindBy(id = "cphBody_imgfx_code")
    private WebElement CurrencyDropdown;
    @FindBy(id = "td_4_0")
    private WebElement SelectCurrency;
    @FindBy(id = "btnOK")
    private WebElement OkBtn;


    String uniqueText;
    List<String> uniqueTextList = new ArrayList<>();


    @FindBy(id = "col_1")
    private WebElement AccountID1;
    @FindBy(xpath = "(//*[contains(@src,'/WISH/WebResource.axd?d=HsZ')])[1]")
    private WebElement Search;
    @FindBy(id = "cphBody_btnApproval")
    private WebElement Approval;
    @FindBy(id = "cphBody_ddlApprovalStatus")
    private WebElement ApprovalStatus;
    @FindBy(id = "chkFreeze")
    private WebElement Freeze;
    @FindBy(id = "cphDialogHeader_btnDelete_Dlg")
    private WebElement Delete;
    @FindBy(id = "td_2_0")
    private WebElement AccountGr1;
    @FindBy(id = "cphBody_txtap_type")
    private WebElement AccountGrText;
    @FindBy(id = "cphBody_lblUploadFromExcel")
    private WebElement UploadExcel;
    @FindBy(id = "btnDownLoadTemplate")
    private WebElement DownloadTemplate;
    @FindBy(id = "btnUpLoadData")
    private WebElement UploadData;

    @FindBy(xpath = "//input[starts-with(@id, 'btnDownLoad')]")
    private WebElement downloadButton;


    public CreateApAccount(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    private static WebElement findButtonInNestedIframes(WebDriver driver, WebDriverWait wait) {
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        System.out.println("Found " + iframes.size() + " nested iframes in current context");

        for (WebElement iframe : iframes) {
            String iframeId = iframe.getAttribute("id");
            if (iframeId == null || iframeId.isEmpty()) {
                iframeId = "Unnamed iframe";
            }
            System.out.println("Trying nested iframe: " + iframeId);

            try {
                driver.switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeBrw"));
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));

                System.out.println("Source of " + iframeId + ": " + driver.getPageSource().substring(0, Math.min(500, driver.getPageSource().length())) + "...");

                WebElement button = tryLocateButton(driver, wait);
                if (button != null) {
                    System.out.println("Button found in nested iframe: " + iframeId);
                    return button;
                }

                // Recurse into deeper iframes
                button = findButtonInNestedIframes(driver, wait);
                if (button != null) {
                    return button;
                }
            } catch (Exception e) {
                System.out.println("Failed to process nested iframe " + iframeId + ": " + e.getMessage());
            } finally {
                driver.switchTo().defaultContent();
            }
        }
        return null;
    }

    private static WebElement tryLocateButton(WebDriver driver, WebDriverWait wait) {
        String[] locators = {"By.id: btnDownLoadTemplate", "By.id: btnDownloadTemplate", // Handle case sensitivity
                "By.xpath: //input[@value='Download Template']", "By.xpath: //input[contains(@value, 'Download Template')]", "By.xpath: //input[@type='submit' and contains(@value, 'Download')]", "By.xpath: //input[contains(@id, 'btnDownLoadTemplate')]", "By.xpath: //input[contains(@id, 'btnDownloadTemplate')]"};

        for (String locator : locators) {
            try {
                By by;
                if (locator.startsWith("By.id")) {
                    by = By.id(locator.split(": ")[1]);
                } else {
                    by = By.xpath(locator.split(": ")[1]);
                }

                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
                System.out.println("Found button using " + locator);
                return element;
            } catch (TimeoutException e) {
                System.out.println("Locator failed: " + locator);
            }
        }
        return null;
    }

    private static WebElement findButtonInIframes(WebDriver driver, WebDriverWait wait) {
        WebElement downloadButton = null;
        try {
            // Try main content first
            downloadButton = tryLocateButton(driver, wait);
            if (downloadButton != null) {
                System.out.println("Button found in main content");
                return downloadButton;
            }

            // Get all iframes
            List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
            System.out.println("Found " + iframes.size() + " iframes");

            for (WebElement iframe : iframes) {
                String iframeId = iframe.getAttribute("id");
                if (iframeId == null || iframeId.isEmpty()) {
                    iframeId = "Unnamed iframe";
                }
                System.out.println("Trying iframe: " + iframeId);

                try {
                    // Switch to iframe
                    driver.switchTo().defaultContent();
                    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));

                    // Try to find button in this iframe
                    downloadButton = tryLocateButton(driver, wait);
                    if (downloadButton != null) {
                        System.out.println("Button found in iframe: " + iframeId);
                        return downloadButton;
                    }

                    // Recursively search nested iframes
                    downloadButton = findButtonInIframes(driver, wait);
                    if (downloadButton != null) {
                        return downloadButton;
                    }

                } catch (Exception e) {
                    System.out.println("Failed to process iframe " + iframeId + ": " + e.getMessage());
                } finally {
                    driver.switchTo().defaultContent();
                }
            }
        } catch (Exception e) {
            System.out.println("Error searching iframes: " + e.getMessage());
        }
        return downloadButton;
    }

    @FindAll({@FindBy(id = "btnDownLoadTemplate"), @FindBy(name = "btnDownLoadTemplate"), @FindBy(xpath = "//input[@value='Download Template']"), @FindBy(xpath = "//input[@type='submit' and @class='Button' and contains(@value, 'Download')]"), @FindBy(xpath = "//tr[@id='tr_Dwn']//input[@type='submit']")})
    public WebElement downloadTemplateButton;
    @FindBy(xpath = "//*[@name=\"btnDownLoadTemplate\"]")
    private WebElement downloadtemp;
    @FindBy(xpath = "//*[@id=\"td2\"]/img")
    private WebElement close;


    public void CreateAccount() {
        try {
            BaseTest.getDriver().manage().window().maximize();
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Click(accounting);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Click(accountPayble);
            Click(createAp);


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Create AP Account", Status.FAIL, "Issue while creating AP Account", true, e);
        }
    }

    public void VendorCreation(){
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(Add);
            ExtentTestManager.getTest().log(Status.INFO,"Click On Add Button");
            Utilities.Click(AccountGroup);
            ExtentTestManager.getTest().log(Status.INFO,"Click on Account Group Dropdown");

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.DoubleClick(SelectAccGroup);
            ExtentTestManager.getTest().log(Status.INFO,"Select the Account Group");
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");


            uniqueText = new SimpleDateFormat("ddyyHHmm").format(new Date());






            Utilities.SendKeys(AccountID,uniqueText);
            ExtentTestManager.getTest().log(Status.INFO,"Enter Account ID");
            Utilities.SendKeys(AccountName,"Testing");
            ExtentTestManager.getTest().log(Status.INFO,"Enter Account Name");
            Utilities.SendKeys(Address,"Phase V");
            ExtentTestManager.getTest().log(Status.INFO,"Enter Address");
            Utilities.SendKeys(City,"Meerut");
            ExtentTestManager.getTest().log(Status.INFO,"Enter City");
            Utilities.SendKeys(State,"UP");
            ExtentTestManager.getTest().log(Status.INFO,"Enter State");
            Utilities.SendKeys(Zip,"250002");
            ExtentTestManager.getTest().log(Status.INFO,"Enter Pin Code");
            Utilities.selectBy("value",GstState,"09");
            ExtentTestManager.getTest().log(Status.INFO,"Enter GST State");
            Utilities.SendKeys(Country,"India");
            ExtentTestManager.getTest().log(Status.INFO,"Enter Country");


            try {
                Utilities.Click(CurrencyDropdown);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                Utilities.DoubleClick(SelectCurrency);
            }catch (Exception e){

            }


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(Save);

            ExtentTestManager.getTest().log(Status.INFO,"Click Save");

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            if(ConfDlg.isDisplayed()&&ConfDlg.getText().contains("[238] - Details Created/Updated")){
                ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Creation",Status.PASS,"Vendor Creation Passed",true);
            }else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Creation", Status.FAIL,"Vendor Creation Failed",true);

            }


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Creation", Status.FAIL,"Vendor Creation Failed",true,e);
        }
    }

    public void Excel() {
        try {




            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(UploadExcel);

            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            //BaseTest.getDriver().switchTo().frame("iframeFileDialog");
            Utilities.Click(close);
            Utilities.Click(UploadExcel);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            BaseTest.getDriver().switchTo().frame("iframeFileDialog");
           // Utilities.Click(downloadtemp);

            try {
                downloadtemp.click();
            } catch (Exception e) {

            }

            BaseTest.getDriver().switchTo().defaultContent();




            // 1) Wait for the outer loader to be hidden (just in case)
            By loader = By.id("divLoading");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));

// 2) Switch into the main browsing iframe
            wait.until(ExpectedConditions
                    .frameToBeAvailableAndSwitchToIt(By.id("MultiPageiframeBrw")));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeFileDialog"));

// 3) Click “Download Template” (try common patterns)
            By[] candidates = new By[]{
                    By.id("btnDownloadTemplate"),
                    By.xpath("//a[normalize-space()='Download Template']"),
                    By.xpath("//button[normalize-space()='Download Template']"),
                    By.xpath("//*[contains(normalize-space(.), 'Download Template') and (self::a or self::button)]")
            };

// try each locator until one is clickable
            WebElement target = null;
            for (By by : candidates) {
                try {
                    target = wait.until(ExpectedConditions.elementToBeClickable(by));
                    break;
                } catch (TimeoutException ignored) { }
            }

            if (target == null) {
                throw new NoSuchElementException("Could not find 'Download Template' inside #MultiPageiframeBrw");
            }

// Prefer your Utilities click if you have one

            Utilities.Click(target);







        } catch (Exception e) {

            ExtentTestManager.createAssertTestStepWithScreenshot("Excel Upload",Status.FAIL," Error while uploading the excel file",true,e);
        }
    }

    public void Excel1() {
        try {




            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(UploadExcel);

            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(30));


            try {
                // Step 1: Wait for loading indicator to disappear
                try {
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("divLoading")));
                    System.out.println("Loading indicator disappeared");
                } catch (TimeoutException e) {
                    System.out.println("No loading indicator or it didn't disappear");
                }

                // Step 2: Check dialog iframes first (MultiPageiframeDlg, MultiPageiframeSubDlg)
                String[] dialogIframes = {"MultiPageiframeDlg", "MultiPageiframeSubDlg"};
                for (String iframeId : dialogIframes) {
                    try {
                        // Wait for iframe to be visible (dialog opened by UploadFromExcel)
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(iframeId.replace("iframe", "div"))));
                        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeId));
                        System.out.println("Switched to " + iframeId);

                        // Log iframe source
                        System.out.println("Source of " + iframeId + ": " + BaseTest.getDriver().getPageSource().substring(0, Math.min(500, BaseTest.getDriver().getPageSource().length())) + "...");

                        // Try to find button
                        downloadButton = tryLocateButton(BaseTest.getDriver(), wait);
                        if (downloadButton != null) {
                            System.out.println("Button found in " + iframeId);
                            break;
                        }
                        BaseTest.getDriver().switchTo().defaultContent();
                    } catch (Exception e) {
                        System.out.println("Failed to process " + iframeId + ": " + e.getMessage());
                        BaseTest.getDriver().switchTo().defaultContent();
                    }
                }

                // Step 3: If not found, check MultiPageiframeBrw and nested iframes
                if (downloadButton == null) {
                    try {
                        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeBrw"));
                        System.out.println("Switched to MultiPageiframeBrw");
                        System.out.println("MultiPageiframeBrw source: " + BaseTest.getDriver().getPageSource().substring(0, Math.min(500, BaseTest.getDriver().getPageSource().length())) + "...");

                        // Try button in MultiPageiframeBrw
                        downloadButton = tryLocateButton(BaseTest.getDriver(), wait);
                        if (downloadButton != null) {
                            System.out.println("Button found in MultiPageiframeBrw");
                        } else {
                            // Check nested iframes
                            downloadButton = findButtonInNestedIframes(BaseTest.getDriver(), wait);
                        }
                    } catch (Exception e) {
                        System.out.println("Failed to process MultiPageiframeBrw: " + e.getMessage());
                    } finally {
                        BaseTest.getDriver().switchTo().defaultContent();
                    }
                }

                // Step 4: Click the button if found
                if (downloadButton != null) {
                    ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].scrollIntoView(true);", downloadButton);
                    try {
                        downloadButton.click();
                    } catch (ElementClickInterceptedException e) {
                        System.out.println("Regular click failed, trying JavaScript click");
                        ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].click();", downloadButton);
                    }
                    System.out.println("Clicked Download Template button");
                } else {
                    System.out.println("Download Template button not found in any iframe");
                    throw new NoSuchElementException("Download Template button not found");
                }

            } catch (Exception e) {
                System.out.println("Failed to click button: " + e.getMessage());
                // Log with Extent Reports
                // ExtentTestManager.createAssertTestStepWithScreenshot("Download Template", Status.FAIL, "Failed to click Download Template", true, e);
            } finally {
                BaseTest.getDriver().switchTo().defaultContent();
                System.out.println("Switched back to default content");
            }


            try {
                // Step 1: Wait for loading indicator to disappear
                try {
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("divLoading")));
                    System.out.println("Loading indicator disappeared");
                } catch (TimeoutException e) {
                    System.out.println("No loading indicator or it didn't disappear in time");
                }

                // Step 2: Recursively search for the button in all iframes
                downloadButton = findButtonInIframes(BaseTest.getDriver(), wait);

                // Step 3: Click the button if found
                if (downloadButton != null) {
                    // Scroll to button
                    ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].scrollIntoView(true);", downloadButton);
                    // Try regular click, fallback to JavaScript click
                    try {
                        downloadButton.click();
                    } catch (ElementClickInterceptedException e) {
                        System.out.println("Regular click failed, trying JavaScript click");
                        ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].click();", downloadButton);
                    }
                    System.out.println("Clicked Download Template button");
                } else {
                    System.out.println("Download Template button not found in any iframe");
                    // Print page source for debugging
                    System.out.println("Main page source: " + BaseTest.getDriver().getPageSource());
                    throw new NoSuchElementException("Download Template button not found");
                }

            } catch (Exception e) {
                System.out.println("Failed to click button: " + e.getMessage());
                // Log with Extent Reports (based on your setup)
                // ExtentTestManager.createAssertTestStepWithScreenshot("Download Template", Status.FAIL, "Failed to click Download Template", true, e);
            } finally {
                // Step 4: Switch back to default content
                BaseTest.getDriver().switchTo().defaultContent();
                System.out.println("Switched back to default content");
            }


            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("divLoading")));
            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeBrw"));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeFileDialog"));
            // WebElement trrun = BaseTest.getDriver().findElement(By.id("tr_Dwn"));

            // WebElement downloadButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnDownLoadTemplate")));
            // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", downloadButton);

            wait.until(ExpectedConditions.visibilityOf(downloadTemplateButton));
            try {
                downloadTemplateButton.click();

            } catch (Exception e) {
                Utilities.Click(downloadTemplateButton);
            }


            System.out.println("Clicked Download Template button");


            // WebElement downloadButton = null;
            try {
                // Step 1: Wait for loading indicator to disappear (if present)
                try {
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("divLoading")));
                    System.out.println("Loading indicator disappeared");
                } catch (TimeoutException e) {
                    System.out.println("No loading indicator or it didn't disappear in time");
                }

                // Step 2: Switch to the main iframe (MultiPageiframeBrw)
                try {
                    BaseTest.getDriver().switchTo().defaultContent();
                    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeBrw"));
                    System.out.println("Switched to MultiPageiframeBrw");
                } catch (TimeoutException e) {
                    System.out.println("Failed to switch to MultiPageiframeBrw: " + e.getMessage());
                    return;
                }

                // Step 3: Try to find the button in MultiPageiframeBrw
                try {
                    downloadButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnDownLoadTemplate")));
                    System.out.println("Button found in MultiPageiframeBrw");
                } catch (TimeoutException e) {
                    System.out.println("Button not found in MultiPageiframeBrw, checking other iframes");
                }

                // Step 4: If button not found, check for nested iframes
                if (downloadButton == null) {
                    BaseTest.getDriver().switchTo().defaultContent(); // Reset context
                    List<WebElement> iframes = BaseTest.getDriver().findElements(By.tagName("iframe"));
                    System.out.println("Found " + iframes.size() + " iframes");

                    for (WebElement iframe : iframes) {
                        String iframeId = iframe.getAttribute("id");
                        System.out.println("Trying iframe: " + iframeId);
                        try {
                            //BaseTest.getDriver().switchTo().defaultContent();
                            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
                            downloadButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnDownLoadTemplate")));
                            System.out.println("Button found in iframe: " + iframeId);
                            break; // Exit loop once button is found
                        } catch (Exception e) {
                            System.out.println("Button not found in iframe " + iframeId + ": " + e.getMessage());
                        }
                    }
                }

                // Step 5: Fallback locator if button still not found
                if (downloadButton == null) {
                    System.out.println("Trying fallback locator");
                    try {
                        downloadButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Download Template']")));
                        System.out.println("Button found with fallback locator");
                    } catch (TimeoutException e) {
                        System.out.println("Fallback locator failed: " + e.getMessage());
                    }
                }

                // Step 6: Click the button if found
                if (downloadButton != null) {
                    // Scroll to button
                    ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].scrollIntoView(true);", downloadButton);
                    downloadButton.click();
                    System.out.println("Clicked Download Template button");
                } else {
                    System.out.println("Download Template button not found in any iframe");
                    throw new NoSuchElementException("Download Template button not found");
                }

            } catch (Exception e) {
                System.out.println("Failed to click button: " + e.getMessage());
                // Optional: Log with Extent Reports
                // ExtentTestManager.createAssertTestStepWithScreenshot("Download Template", Status.FAIL, "Failed to click Download Template", true, e);
            } finally {
                // Step 7: Switch back to default content
                BaseTest.getDriver().switchTo().defaultContent();
                System.out.println("Switched back to default content");
            }


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            //BaseTest.getDriver().switchTo().frame("iframeFileDialog");

            //WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(),Duration.ofSeconds(10));
            // wait.until(ExpectedConditions.elementToBeClickable(DownloadTemplate));

            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeFileDialog"));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("divLoading")));

            Utilities.Click(DownloadTemplate);


//                Utilities.Click(UploadFromExcel);
//                BaseTest.getDriver().switchTo().defaultContent();
//                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
//                BaseTest.getDriver().switchTo().frame("iframeFileDialog");
//                Utilities.Click(DownloadTemplate);
//
//                DynamicWait.mediumWait();
//
//                File filePath = getLatestFile(downloadPath);
//
//                modifyXMLFile(String.valueOf(filePath));


//                BaseTest.getDriver().switchTo().defaultContent();
//                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//
//                if (ConfDlg.getText().contains("Details Created/Updated")){
//                    ExtentTestManager.createAssertTestStepWithScreenshot("Item Creation",Status.PASS,"Item Created Sucessfully",true);
//                    System.out.println("Item Created Sucessfully");
//                } else {
//                    ExtentTestManager.createAssertTestStepWithScreenshot("Item Creation",Status.FAIL,"Item Creation Failed",true);
//
//                }


        } catch (Exception e) {

        }
    }

    public void Modify() {
        try {
            Utilities.logerror("Modify Vendor", logMessages -> {
                Utilities.Click(OkBtn);
                logMessages.add("Click on OK Button");
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

                Utilities.SendKeys(AccountID1, uniqueText);
                Utilities.Click(Search);

                Utilities.clearText(BaseTest.getDriver(), AccountGrText);
                Utilities.Click(AccountGroup);

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                Utilities.DoubleClick(AccountGr1);

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");


                Utilities.SendKeys(AccountName, "Modify");
                Utilities.SendKeys(City, "Delhi");

                // Step 23: Click Save
                Utilities.Click(Save);
                logMessages.add("Clicked Save");

                // Step 24: Check confirmation dialog
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                logMessages.add("Switched to iframeGridDialog frame");
                if (ConfDlg.isDisplayed() && ConfDlg.getText().contains("[238] - Details Created/Updated")) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Creation", Status.PASS, "Vendor Creation Passed", true);
                } else {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Creation", Status.FAIL, "Vendor Creation Failed", true);
                }
            });
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Delete", Status.FAIL, "Vendor Deletion Failed", true, e);
        }
    }




}

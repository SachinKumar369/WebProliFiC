package pages.VendorCreation;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.apache.commons.math3.analysis.function.Add;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.T0419_Pages.IQ_Page;
import utils.DynamicWait;
import utils.Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class VendorPage extends ExtentTestManager {

    @FindAll({@FindBy(id = "btnDownLoadTemplate"), @FindBy(name = "btnDownLoadTemplate"), @FindBy(xpath = "//input[@value='Download Template']"), @FindBy(xpath = "//input[@type='submit' and @class='Button' and contains(@value, 'Download')]"), @FindBy(xpath = "//tr[@id='tr_Dwn']//input[@type='submit']")})
    public WebElement downloadTemplateButton;
    String uniqueText;
    List<String> uniqueTextList = new ArrayList<>();
    @FindBy(xpath = "//a[text()='Vendor Setup']")
    private WebElement VendorSetup;
    @FindBy(id = "LinkProduct2")
    private WebElement Inventory;
    @FindBy(id = "divmodule_2")
    private WebElement InventoryModule;
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


    public VendorPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    private static void modifyXMLFile(String filePath) {
        try {
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));

            // Verify file exists
            File xmlFile = new File(filePath);
            if (!xmlFile.exists()) {
                throw new RuntimeException("XML file not found at: " + filePath);
            }
            if (xmlFile.length() == 0) {
                throw new RuntimeException("XML file is empty at: " + filePath);
            }

            // Debug: Print first few lines
            try (BufferedReader reader = new BufferedReader(new FileReader(xmlFile))) {
                System.out.println("First 5 lines of XML file:");
                for (int i = 0; i < 5 && reader.ready(); i++) {
                    System.out.println(reader.readLine());
                }
            }

            // Parse the XML file
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(xmlFile);

            // Debug: Print root and worksheets
            Element root = document.getRootElement();
            System.out.println("Root element: " + root.getName());
            System.out.println("Available worksheets:");

            // Use the default namespace for Worksheet elements
            Namespace defaultNs = root.getNamespace(); // urn:schemas-microsoft-com:office:spreadsheet
            Namespace ssNs = root.getNamespace("ss"); // urn:schemas-microsoft-com:office:spreadsheet for ss prefix

            List<Element> worksheets = root.getChildren("Worksheet", defaultNs);
            if (worksheets.isEmpty()) {
                System.out.println("No Worksheet elements found!");
                System.out.println("All child elements of root:");
                root.getChildren().forEach(child -> System.out.println(child.getName() + " with attributes: " + child.getAttributes()));
                throw new RuntimeException("No Worksheet elements found in XML");
            } else {
                worksheets.forEach(ws -> System.out.println(ws.getAttributeValue("Name", ssNs)));
            }

            // Find Data worksheet
            Element worksheet = worksheets.stream().filter(ws -> "Data".equalsIgnoreCase(ws.getAttributeValue("Name", ssNs))).findFirst().orElseThrow(() -> new RuntimeException("Data worksheet not found"));

            // Navigate to Table
            Element table = worksheet.getChild("Table", ssNs);
            if (table == null) {
                throw new RuntimeException("Table element not found in Data worksheet");
            }

            // Get second row
            List<Element> rows = table.getChildren("Row", defaultNs);
            if (rows.size() < 2) {
                throw new RuntimeException("Second row not found in Table");
            }
            Element secondRow = rows.get(1);

            // Clear existing cells
            secondRow.removeChildren("Cell", defaultNs);

            String timeStamp = new SimpleDateFormat("ddMMyyHHmm").format(new Date());
            // Data to insert
            String[] data = {"", "Sample Item" + timeStamp, "NOS", "GS", "88", "COAL", "NOS", "NOS", "", "NOS"};

            // Insert new cells
            for (int i = 0; i < data.length; i++) {
                Element cell = new Element("Cell", defaultNs);
                if (i != 0) {
                    cell.setAttribute("Index", String.valueOf(i + 1), ssNs);
                }
                Element dataElement = new Element("Data", defaultNs);
                dataElement.setAttribute("Type", "String", ssNs);
                dataElement.setText(data[i]);
                cell.addContent(dataElement);
                secondRow.addContent(cell);
            }

            // Save the modified file
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            try (FileOutputStream fos = new FileOutputStream(xmlFile)) {
                xmlOutputter.output(document, fos);
            }

            System.out.println("XML file updated successfully at: " + filePath);


            String Uploaded_File = System.getProperty("user.dir") + File.separator + "DownloadPath";
            File file2 = Utilities.getLastModified(Uploaded_File);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            BaseTest.getDriver().switchTo().frame("iframeFileDialog");


//            ChooseFile.sendKeys(file2.getAbsolutePath());
//            Utilities.Click(BaseTest.getDriver(), UploadData);
            wait.until(ExpectedConditions.alertIsPresent()).accept();


            BaseTest.getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeBrw"));
            // Utilities.Click(Save);


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to modify XML file", e);
        }
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

    public void VendorSetup() {
        try {
            String downloadPath = System.getProperty("user.dir") + File.separator + "DownloadPath";

            BaseTest.getDriver().manage().window().maximize();

            // Step 2: Switch to frame
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


//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
//            Utilities.Click(UploadExcel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void ExcelDownload() {
        try {
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(Add);
            Utilities.Click(UploadExcel);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            BaseTest.getDriver().switchTo().frame("iframeFileDialog");

            WebElement Uplod = BaseTest.getDriver().findElement(By.xpath("//input[@value='Download Template']"));
            Uplod.click();


//            Utilities.Click(UploadData);
//            Utilities.Click(DownloadTemplate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Excel() {
        try {


            String downloadPath = System.getProperty("user.dir") + File.separator + "DownloadPath";

            BaseTest.getDriver().manage().window().maximize();

            // Step 2: Switch to frame
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

    public void Freeze1() {
        try {
            Utilities.Click(OkBtn);
            ExtentTestManager.getTest().log(Status.INFO, "Click ok");

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

            if (!Freeze.isSelected()) {
                Utilities.ClickCheckBox(Freeze);
            }

            Utilities.Click(Save);

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(Approval);
            ExtentTestManager.getTest().log(Status.INFO, "Click on Approval");
            Utilities.selectBy("value", ApprovalStatus, "UP");
            ExtentTestManager.getTest().log(Status.INFO, "Select Approval form the Dropdown");


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("", Status.FAIL, "", true, e);
        }
    }

    public void VendorCreation99() {
        try {
            BaseTest.getDriver().manage().window().maximize();
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(Inventory);
            ExtentTestManager.getTest().log(Status.INFO, "Click On Inventory");
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(InventoryModule);
            ExtentTestManager.getTest().log(Status.INFO, "Click On Inventory Module");
            Utilities.Click(VendorSetup);
            ExtentTestManager.getTest().log(Status.INFO, "Click On VendorSetup");
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(Add);
            ExtentTestManager.getTest().log(Status.INFO, "Click On Add Button");
            Utilities.Click(AccountGroup);
            ExtentTestManager.getTest().log(Status.INFO, "Click on Account Group Dropdown");

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            Utilities.DoubleClick(SelectAccGroup);
            ExtentTestManager.getTest().log(Status.INFO, "Select the Account Group");
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");


            uniqueText = new SimpleDateFormat("ddyyHHmm").format(new Date());


            Utilities.SendKeys(AccountID, uniqueText);
            ExtentTestManager.getTest().log(Status.INFO, "Enter Account ID");
            Utilities.SendKeys(AccountName, "Testing");
            ExtentTestManager.getTest().log(Status.INFO, "Enter Account Name");
            Utilities.SendKeys(Address, "Phase V");
            ExtentTestManager.getTest().log(Status.INFO, "Enter Address");
            Utilities.SendKeys(City, "Meerut");
            ExtentTestManager.getTest().log(Status.INFO, "Enter City");
            Utilities.SendKeys(State, "UP");
            ExtentTestManager.getTest().log(Status.INFO, "Enter State");
            Utilities.SendKeys(Zip, "250002");
            ExtentTestManager.getTest().log(Status.INFO, "Enter Pin Code");
            Utilities.selectBy("value", GstState, "09");
            ExtentTestManager.getTest().log(Status.INFO, "Enter GST State");
            Utilities.SendKeys(Country, "India");
            ExtentTestManager.getTest().log(Status.INFO, "Enter Country");


            try {
                Utilities.Click(CurrencyDropdown);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                Utilities.DoubleClick(SelectCurrency);
            } catch (Exception e) {

            }


            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            Utilities.Click(Save);

            ExtentTestManager.getTest().log(Status.INFO, "Click Save");

            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            if (ConfDlg.isDisplayed() && ConfDlg.getText().contains("[238] - Details Created/Updated")) {
                ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Creation", Status.PASS, "Vendor Creation Passed", true);
            } else {
                ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Creation", Status.FAIL, "Vendor Creation Failed", true);

            }


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Creation", Status.FAIL, "Vendor Creation Failed", true, e);
        }
    }

    public void VendorApproval() {

        Utilities.Click(OkBtn);
        ExtentTestManager.getTest().log(Status.INFO, "Click ok");
        System.out.println(uniqueText);

        BaseTest.getDriver().switchTo().defaultContent();
        BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
        Utilities.SendKeys(AccountID1, uniqueText);
        ExtentTestManager.getTest().log(Status.INFO, "Enter Account ID");

        Utilities.Click(Search);
        ExtentTestManager.getTest().log(Status.INFO, "Click ON Search");

        BaseTest.getDriver().switchTo().defaultContent();
        BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
        Utilities.Click(Approval);
        ExtentTestManager.getTest().log(Status.INFO, "Click on Approval");
        Utilities.selectBy("value", ApprovalStatus, "AP");
        ExtentTestManager.getTest().log(Status.INFO, "Select Approval form the Dropdown");
        Utilities.Click(Save);
        ExtentTestManager.getTest().log(Status.INFO, "Click Save");

        BaseTest.getDriver().switchTo().defaultContent();
        BaseTest.getDriver().switchTo().frame("iframeGridDialog");
        if (ConfDlg.isDisplayed() && ConfDlg.getText().contains("[238] - Details Created/Updated")) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Approval", Status.PASS, "Vendor Approval Passed", true);
        } else {
            ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Approval", Status.FAIL, "Vendor Approval Failed", true);

        }


    }


//    public void Freeze3() {
//        try {
//            Utilities.logStep("Click OK Button");
//            Utilities.Click(OkBtn);
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
//
//            Utilities.logStep("Check Freeze checkbox");
//            if (!Freeze.isSelected()) {
//                Utilities.ClickCheckBox(Freeze);
//            }
//
//
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
//
//            Utilities.logStep("Click on Approval Button");
//            Utilities.Click(Approval);
//
//            Utilities.logStep("Select 'UP' from ApprovalStatus Dropdown");
//            Utilities.selectBy("value", ApprovalStatus, "UP");
//
//            Utilities.logStep("Click Save Button");
//            Utilities.Click(Save);
//
//        } catch (Exception e) {
//            Utilities.logFailure( e);
//
//            ExtentTestManager.createAssertTestStepWithScreenshot("testing", Status.FAIL, "", true, e);
//
//        }
//    }
//
//    public void Freeze() {
//        String step = "";
//
//        try {
//            step = "Click OK Button";
//            Utilities.Click(OkBtn); // no logStep()
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
//
//            step = "Check Freeze checkbox if not already selected";
//            if (!Freeze.isSelected()) {
//                Utilities.ClickCheckBox(Freeze);
//            }
//
//            step = "Click Save Button";
//            Utilities.Click(Save);
//
//            BaseTest.getDriver().switchTo().defaultContent();
//            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
//
//            step = "Click on Approval Button";
//            Utilities.Click(Approval);
//
//            step = "Select 'UP' from ApprovalStatus Dropdown";
//            Utilities.selectBy("value", ApprovalStatus, "UP");
//
//        } catch (Exception e) {
//            Utilities.logFailure(e);  // Only logs the failing step
//            ExtentTestManager.createAssertTestStepWithScreenshot("testing", Status.FAIL, "", true, e);
//
//        }
//    }

    public void VendorCreation1() {
        try {
            for (int i = 1; i <= 3; i++) {
                BaseTest.getDriver().manage().window().maximize();
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                Utilities.Click(Inventory);
                ExtentTestManager.getTest().log(Status.INFO, "Click On Inventory");
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                Utilities.Click(InventoryModule);
                ExtentTestManager.getTest().log(Status.INFO, "Click On Inventory Module");
                Utilities.Click(VendorSetup);
                ExtentTestManager.getTest().log(Status.INFO, "Click On VendorSetup");
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                Utilities.Click(Add);
                ExtentTestManager.getTest().log(Status.INFO, "Click On Add Button");
                Utilities.Click(AccountGroup);
                ExtentTestManager.getTest().log(Status.INFO, "Click on Account Group Dropdown");

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                Utilities.DoubleClick(SelectAccGroup);
                ExtentTestManager.getTest().log(Status.INFO, "Select the Account Group");
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");


                uniqueText = new SimpleDateFormat("ddyyHHmm").format(new Date());
                uniqueTextList.add(uniqueText);


                Utilities.SendKeys(AccountID, uniqueText);
                ExtentTestManager.getTest().log(Status.INFO, "Enter Account ID");
                Utilities.SendKeys(AccountName, "Testing");
                ExtentTestManager.getTest().log(Status.INFO, "Enter Account Name");
                Utilities.SendKeys(Address, "Phase V");
                ExtentTestManager.getTest().log(Status.INFO, "Enter Address");
                Utilities.SendKeys(City, "Meerut");
                ExtentTestManager.getTest().log(Status.INFO, "Enter City");
                Utilities.SendKeys(State, "UP");
                ExtentTestManager.getTest().log(Status.INFO, "Enter State");
                Utilities.SendKeys(Zip, "250002");
                ExtentTestManager.getTest().log(Status.INFO, "Enter Pin Code");
                Utilities.selectBy("value", GstState, "09");
                ExtentTestManager.getTest().log(Status.INFO, "Enter GST State");
                Utilities.SendKeys(Country, "India");
                ExtentTestManager.getTest().log(Status.INFO, "Enter Country");


                try {
                    Utilities.Click(CurrencyDropdown);
                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                    Utilities.DoubleClick(SelectCurrency);
                } catch (Exception e) {

                }


                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                Utilities.Click(Save);

                ExtentTestManager.getTest().log(Status.INFO, "Click Save");

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                if (ConfDlg.isDisplayed() && ConfDlg.getText().contains("[238] - Details Created/Updated")) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Creation", Status.PASS, "Vendor Creation Passed", true);
                } else {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Creation", Status.FAIL, "Vendor Creation Failed", true);

                }
            }


        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Creation", Status.FAIL, "Vendor Creation Failed", true, e);
        }
    }

    public void VendorApproval1() {

        Utilities.Click(OkBtn);
        ExtentTestManager.getTest().log(Status.INFO, "Click ok");
        System.out.println(uniqueText);

        BaseTest.getDriver().switchTo().defaultContent();
        BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
        Utilities.SendKeys(AccountID1, uniqueText);
        ExtentTestManager.getTest().log(Status.INFO, "Enter Account ID");

        Utilities.Click(Search);
        ExtentTestManager.getTest().log(Status.INFO, "Click ON Search");

        BaseTest.getDriver().switchTo().defaultContent();
        BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
        Utilities.Click(Approval);
        ExtentTestManager.getTest().log(Status.INFO, "Click on Approval");
        Utilities.selectBy("value", ApprovalStatus, "AP");
        ExtentTestManager.getTest().log(Status.INFO, "Select Approval form the Dropdown");
        Utilities.Click(Save);
        ExtentTestManager.getTest().log(Status.INFO, "Click Save");

        BaseTest.getDriver().switchTo().defaultContent();
        BaseTest.getDriver().switchTo().frame("iframeGridDialog");
        if (ConfDlg.isDisplayed() && ConfDlg.getText().contains("[238] - Details Created/Updated")) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Approval", Status.PASS, "Vendor Approval Passed", true);
        } else {
            ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Approval", Status.FAIL, "Vendor Approval Failed", true);

        }


    }

    public void Freeze() {
        try {
            Utilities.logerror("VendorCreation", logMessages -> {
                Utilities.Click(OkBtn);
                logMessages.add("Click ok");

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

                if (!Freeze.isSelected()) {
                    Utilities.ClickCheckBox(Freeze);
                    logMessages.add("Clicked Freeze checkbox");
                }

//                Utilities.Click(Save);
//                logMessages.add("Clicked Save");

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");


                Utilities.Click(Approval);
                //Utilities.Click(Freeze);

                logMessages.add("Click on Approval");

                Utilities.selectBy("value", ApprovalStatus, "UP");
                logMessages.add("Select Approval form the Dropdown");

                Utilities.Click(Save);
                logMessages.add("Click on Save");


                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                if (ConfDlg.isDisplayed() && ConfDlg.getText().contains("[238] - Details Created/Updated")) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Item Freeze", Status.PASS, "Item Freeze Sucessfully", true);
                } else {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Item Freeze", Status.FAIL, "Error while Freezing Item", true);
                }
            });

        } catch (Exception e) {
            // Log all accumulated messages only on failure

            ExtentTestManager.createAssertTestStepWithScreenshot("testing", Status.FAIL, "", true, e);
        }
    }

    public void Freeze99() {
        List<String> logMessages = new ArrayList<>();
        try {
            // Step 1: Click OK button
            Utilities.Click(OkBtn);
            logMessages.add("Clicked OK button");

            // Step 2: Switch to default content and then to frame
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            logMessages.add("Switched to MultiPageiframeBrw frame");

            // Step 3: Check and click Freeze checkbox if not selected
            if (!Freeze.isSelected()) {
                Utilities.ClickCheckBox(Freeze);
                logMessages.add("Clicked Freeze checkbox");
            } else {
                logMessages.add("Freeze checkbox already selected");
            }

            // Step 4: Click Save
            Utilities.Click(Save);
            logMessages.add("Clicked Save button");

            // Step 5: Switch to default content and then to frame again
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            logMessages.add("Switched to MultiPageiframeBrw frame for Approval");

            // Step 6: Click Approval
            Utilities.Click(Approval);
            logMessages.add("Clicked Approval button");

            // Step 7: Select Approval status
            Utilities.selectBy("value", ApprovalStatus, "UP");
            logMessages.add("Selected 'UP' from Approval dropdown");

        } catch (Exception e) {
            // Log all steps executed so far only on failure
            for (String message : logMessages) {
                ExtentTestManager.getTest().log(Status.INFO, message);
            }
            // Log failure with screenshot and exception details
            ExtentTestManager.createAssertTestStepWithScreenshot("Freeze5 execution failed", Status.FAIL, "Exception occurred: " + e.getMessage(), true, e);
        }
    }

    public void VendorCreation2() {
        try {


                BaseTest.getDriver().manage().window().maximize();

                // Step 2: Switch to frame
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

                // Step 9: Click Account Group Dropdown
                Utilities.Click(BaseTest.getDriver(), AccountGroup);

                // Step 10: Select Account Group
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                Utilities.DoubleClick(SelectAccGroup);

                // Step 11: Switch back to main frame
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

                // Step 12: Generate unique text for Account ID
                uniqueText = new SimpleDateFormat("ddyyHHmm").format(new Date());

                // Step 13: Enter Account ID
                Utilities.SendKeys(AccountID, uniqueText);

                // Step 14: Enter Account Name
                Utilities.SendKeys(AccountName, "Testing");

                // Step 15: Enter Address
                Utilities.SendKeys(Address, "Phase V");

                // Step 16: Enter City
                Utilities.SendKeys(City, "Meerut");

                // Step 17: Enter State
                Utilities.SendKeys(State, "UP");

                // Step 18: Enter Zip
                Utilities.SendKeys(Zip, "250002");

                // Step 19: Select GST State
                Utilities.selectBy("value", GstState, "09");

                // Step 20: Enter Country
                Utilities.SendKeys(Country, "India");

                // Step 21: Select Currency
                try {

                    Utilities.Click(CurrencyDropdown);
                    BaseTest.getDriver().switchTo().defaultContent();
                    BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                    Utilities.DoubleClick(SelectCurrency);
                } catch (Exception e) {

                }
                // Step 22: Switch back to main frame
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

                // Step 23: Click Save
                Utilities.Click(Save);

                // Step 24: Check confirmation dialog
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                if (ConfDlg.isDisplayed() && ConfDlg.getText().contains("[238] - Details Created/Updated")) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Creation", Status.PASS, "Vendor Creation Passed", true);
                } else {
                   // ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Creation", Status.FAIL, "Vendor Creation Failed", true);

                    appProgError();

                }
           // });
        } catch (Exception e) {
           // ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Creation9", Status.FAIL, "Vendor Creation Failed", true);


            appProgError();

        }
    }


    public void VendorCreation() {
        try {

            ExtentTestManager.logStep("Vendor Creation", "Test started");

            BaseTest.getDriver().manage().window().maximize();
            ExtentTestManager.logStep("Browser", "Window maximized");

            BaseTest.getDriver().switchTo().defaultContent();
            ExtentTestManager.logStep("Frame Switch", "Switched to default content");

            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            ExtentTestManager.logStep("Frame Switch", "Switched to frame: MultiPageiframeBrw");

            ExtentTestManager.logStep("Click", "Clicking Inventory");
            Utilities.Click(Inventory);
            ExtentTestManager.logStep("Click", "Clicked Inventory");

            BaseTest.getDriver().switchTo().defaultContent();
            ExtentTestManager.logStep("Frame Switch", "Switched to default content");

            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            ExtentTestManager.logStep("Frame Switch", "Switched to frame: MultiPageiframeBrw");

            ExtentTestManager.logStep("Click", "Clicking Inventory Module");
            Utilities.Click(InventoryModule);
            ExtentTestManager.logStep("Click", "Clicked Inventory Module");

            ExtentTestManager.logStep("Click", "Clicking Vendor Setup");
            Utilities.Click(VendorSetup);
            ExtentTestManager.logStep("Click", "Clicked Vendor Setup");

            BaseTest.getDriver().switchTo().defaultContent();
            ExtentTestManager.logStep("Frame Switch", "Switched to default content");

            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            ExtentTestManager.logStep("Frame Switch", "Switched to frame: MultiPageiframeBrw");

            ExtentTestManager.logStep("Click", "Clicking Add");
            Utilities.Click(Add);
            ExtentTestManager.logStep("Click", "Clicked Add");

            ExtentTestManager.logStep("Click", "Clicking Account Group dropdown");
            Utilities.Click(BaseTest.getDriver(), AccountGroup);
            ExtentTestManager.logStep("Click", "Clicked Account Group dropdown");

            BaseTest.getDriver().switchTo().defaultContent();
            ExtentTestManager.logStep("Frame Switch", "Switched to default content");

            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            ExtentTestManager.logStep("Frame Switch", "Switched to frame: iframeGridDialog");

            ExtentTestManager.logStep("Double Click", "Selecting Account Group");
            Utilities.DoubleClick(SelectAccGroup);
            ExtentTestManager.logStep("Double Click", "Selected Account Group");

            BaseTest.getDriver().switchTo().defaultContent();
            ExtentTestManager.logStep("Frame Switch", "Switched to default content");

            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            ExtentTestManager.logStep("Frame Switch", "Switched to frame: MultiPageiframeBrw");

            uniqueText = new SimpleDateFormat("ddyyHHmm").format(new Date());
            ExtentTestManager.logStep("Test Data", "Generated unique Account ID: " + uniqueText);

            ExtentTestManager.logStep("Input", "Entering Account ID: " + uniqueText);
            Utilities.SendKeys(AccountID, uniqueText);

            ExtentTestManager.logStep("Input", "Entering Account Name: Testing");
            Utilities.SendKeys(AccountName, "Testing");

            ExtentTestManager.logStep("Input", "Entering Address: Phase V");
            Utilities.SendKeys(Address, "Phase V");

            ExtentTestManager.logStep("Input", "Entering City: Meerut");
            Utilities.SendKeys(City, "Meerut");

            ExtentTestManager.logStep("Input", "Entering State: UP");
            Utilities.SendKeys(State, "UP");

            ExtentTestManager.logStep("Input", "Entering Zip: 250002");
            Utilities.SendKeys(Zip, "250002");

            ExtentTestManager.logStep("Dropdown", "Selecting GST State value: 09");
            Utilities.selectBy("value", GstState, "09");

            ExtentTestManager.logStep("Input", "Entering Country: India");
            Utilities.SendKeys(Country, "India");

            try {
                ExtentTestManager.logStep("Click", "Clicking Currency dropdown");
                Utilities.Click(CurrencyDropdown);
                ExtentTestManager.logStep("Click", "Clicked Currency dropdown");

                BaseTest.getDriver().switchTo().defaultContent();
                ExtentTestManager.logStep("Frame Switch", "Switched to default content");

                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                ExtentTestManager.logStep("Frame Switch", "Switched to frame: iframeGridDialog");

                ExtentTestManager.logStep("Double Click", "Selecting Currency");
                Utilities.DoubleClick(SelectCurrency);
                ExtentTestManager.logStep("Double Click", "Selected Currency");

            } catch (Exception e) {
                ExtentTestManager.logStep("Currency", "Currency selection skipped/failed: " + e.getMessage());
            }

            BaseTest.getDriver().switchTo().defaultContent();
            ExtentTestManager.logStep("Frame Switch", "Switched to default content");

            BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
            ExtentTestManager.logStep("Frame Switch", "Switched to frame: MultiPageiframeBrw");

            ExtentTestManager.logStep("Click", "Clicking Save");
            Utilities.Click(Save);
            ExtentTestManager.logStep("Click", "Clicked Save");

            BaseTest.getDriver().switchTo().defaultContent();
            ExtentTestManager.logStep("Frame Switch", "Switched to default content");

            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            ExtentTestManager.logStep("Frame Switch", "Switched to frame: iframeGridDialog");

            ExtentTestManager.logStep("Validation", "Validating confirmation dialog message");

            if (ConfDlg.isDisplayed() && ConfDlg.getText().contains("[238] - Details Created/Updated")) {
                ExtentTestManager.createAssertTestStepWithScreenshot(
                        "Vendor Creation",
                        Status.PASS,
                        "Vendor Creation Passed - Confirmation: " + ConfDlg.getText(),
                        true
                );
            } else {
                appProgError();
//                ExtentTestManager.createAssertTestStepWithScreenshot(
//                        "Vendor Creation",
//                        Status.FAIL,
//                        "Vendor Creation Failed - Confirmation not matched. Found: " + ConfDlg.getText(),
//                        true
//                );
            }

        } catch (Exception e) {
            appProgError();
//            ExtentTestManager.createAssertTestStepWithScreenshot(
//                    "Vendor Creation",
//                    Status.FAIL,
//                    "Vendor Creation Failed due to exception: " + e.getMessage(),
//                    true,
//                    e
//            );
        }
    }


    public void Delete() {
        try {
            Utilities.logerror("Delete Vendor", logMessages -> {

                Utilities.Click(OkBtn);
                logMessages.add("Click on OK Button");
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                Utilities.Click(Delete);
                logMessages.add("Click on Delete Button");
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                logMessages.add("Switched to iframeGridDialog frame");
                if (ConfDlg.isDisplayed() && ConfDlg.getText().contains("[276] - Details Deleted")) {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Delete", Status.PASS, "Vendor Deleted Successfully", true);
                } else {
                    ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Delete", Status.FAIL, "Vendor Deletion Failed", false);
                }
            });
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Delete", Status.FAIL, "Vendor Deletion Failed", true, e);

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

    public void usd() {
        try {


            Utilities.logerror("VendorCreation", logMessages -> {
                // Step 1: Maximize window
                BaseTest.getDriver().manage().window().maximize();
                logMessages.add("Maximized browser window");

                // Step 2: Switch to frame
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                logMessages.add("Switched to MultiPageiframeBrw frame");

                // Step 3: Click Inventory
                Utilities.Click(Inventory);
                logMessages.add("Clicked on Inventory");

                // Step 4: Switch to frame again
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                logMessages.add("Switched to MultiPageiframeBrw frame");

                // Step 5: Click Inventory Module
                Utilities.Click(InventoryModule);
                logMessages.add("Clicked on Inventory Module");

                // Step 6: Click Vendor Setup
                Utilities.Click(VendorSetup);
                logMessages.add("Clicked on VendorSetup");

                // Step 7: Switch to frame again
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                logMessages.add("Switched to MultiPageiframeBrw frame");

                // Step 8: Click Add
                Utilities.Click(Add);
                logMessages.add("Clicked on Add Button");

                // Step 9: Click Account Group Dropdown
                Utilities.Click(BaseTest.getDriver(), AccountGroup);
                logMessages.add("Clicked on Account Group Dropdown");

                // Step 10: Select Account Group
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                Utilities.DoubleClick(SelectAccGroup);
                logMessages.add("Selected the Account Group");

                // Step 11: Switch back to main frame
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                logMessages.add("Switched to MultiPageiframeBrw frame");

                // Step 12: Generate unique text for Account ID
                uniqueText = new SimpleDateFormat("ddyyHHmm").format(new Date());
                logMessages.add("Generated unique text for Account ID: " + uniqueText);

                // Step 13: Enter Account ID
                Utilities.SendKeys(AccountID, uniqueText);
                logMessages.add("Entered Account ID");

                // Step 14: Enter Account Name
                Utilities.SendKeys(AccountName, "Testing");
                logMessages.add("Entered Account Name");

                // Step 15: Enter Address
                Utilities.SendKeys(Address, "Phase V");
                logMessages.add("Entered Address");

                // Step 16: Enter City
                Utilities.SendKeys(City, "Meerut");
                logMessages.add("Entered City");

                // Step 17: Enter State
                Utilities.SendKeys(State, "UP");
                logMessages.add("Entered State");

                // Step 18: Enter Zip
                Utilities.SendKeys(Zip, "250002");
                logMessages.add("Entered Pin Code");

//                // Step 19: Select GST State
//                Utilities.selectBy("value", GstState, "09");
//                logMessages.add("Entered GST State");

                // Step 20: Enter Country
                Utilities.SendKeys(Country, "India");
                logMessages.add("Entered Country");

                // Step 21: Select Currency
                try {

                    Utilities.SendKeys(currency,"USD");
//                    Utilities.Click(CurrencyDropdown);
//                    logMessages.add("Clicked on Currency Dropdown");
//                    BaseTest.getDriver().switchTo().defaultContent();
//                    BaseTest.getDriver().switchTo().frame("iframeGridDialog");
//                    logMessages.add("Switched to iframeGridDialog frame");
//                    Utilities.DoubleClick(SelectCurrency);
//                    logMessages.add("Selected Currency");
                } catch (Exception e) {

                }
                // Step 22: Switch back to main frame
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                logMessages.add("Switched to MultiPageiframeBrw frame");

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
            ExtentTestManager.createAssertTestStepWithScreenshot("Vendor Creation9", Status.FAIL, "Vendor Creation Failed", true);

        }
    }

    @FindBy(id = "cphBody_txtfx_code")
    private WebElement currency;

    public void appProgError(){
        try {
            // Error handling with proper logging
            ExtentTestManager.createAssertTestStepWithScreenshot("Error Handling", Status.INFO,
                    "Checking for application errors after exception", false);
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("iframeGridDialog");
            //BaseTest.getDriver().switchTo().frame("iframeGridDialog");

            if (ConfDlg.isDisplayed() && ConfDlg.getText().contains("Application program error")) {
                IQ_Page ob = new IQ_Page(BaseTest.getDriver());
                ob.ApplicationProgError();
                ExtentTestManager.createAssertTestStepWithScreenshot("Application Error", Status.FAIL,
                        "Application error occurred during execution", true);
                return;
            }
        } catch (Exception innerEx) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Error Handling", Status.FAIL,
                    "Error while handling application error", true, innerEx);
        }
    }

}

package pages.VendorCreation;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import extentreports.ExtentTestManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DynamicWait;
import utils.Utilities;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static datahandlers.XMLHandler.filePath;

public class Testing {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // WebElements
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
    @FindBy(id = "FileDlg")
    private WebElement FileDlgButton;
    @FindBy(id = "btnUpLoadData")
    private WebElement UploadData;

    // Existing download button locators
    @FindAll({
            @FindBy(xpath = "//input[@id='btnDownLoadTemplate']"),
            @FindBy(xpath = "//input[@name='btnDownLoadTemplate']"),
            @FindBy(xpath = "//input[@type='submit' and contains(@value, 'Download')]"),
            @FindBy(xpath = "//input[contains(@id, 'btnDownLoadTemplate')]"),
            @FindBy(xpath = "//input[@type='submit' and @name='btnDownLoadTemplate' and @value='Download Template']")
    })
    private WebElement downloadTemplateButton;

    public Testing(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public void excelDownload() {
        try {
            switchToIframe("MultiPageiframeBrw");
            Utilities.Click(UploadExcel);

            if (searchDialogIframes()) return;

            switchToIframe("MultiPageiframeBrw");
            if (searchFramesRecursively(3)) return;

            throw new NoSuchElementException("Button not found after exhaustive search");

        } catch (Exception e) {
            handleException(e);
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    private boolean searchDialogIframes() {
        String[] dialogIframes = {"MultiPageiframeDialog2"};

        for (String iframeId : dialogIframes) {
            try {
                if (!switchToIframe(iframeId)) continue;

                WebElement button = findDownloadButtonInCurrentContext();
                if (button != null) {
                    clickDownloadAndFileDlg(button, iframeId);
                    return true;
                }

                if (searchFramesRecursively(2)) return true;

            } catch (Exception e) {
                System.out.println("Error in dialog iframe " + iframeId + ": " + e.getMessage());
            } finally {
                driver.switchTo().defaultContent();
            }
        }
        return false;
    }

    private boolean searchFramesRecursively(int depth) {
        if (depth <= 0) return false;

        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        for (WebElement iframe : iframes) {
            String iframeId = iframe.getAttribute("id");
            try {
                driver.switchTo().frame(iframe);
                WebElement button = findDownloadButtonInCurrentContext();
                if (button != null) {
                    clickDownloadAndFileDlg(button, "Nested iframe: " + iframeId);
                    return true;
                }
                if (searchFramesRecursively(depth - 1)) return true;
            } catch (Exception e) {
                System.out.println("Error in iframe " + iframeId + ": " + e.getMessage());
            } finally {
                driver.switchTo().parentFrame();
            }
        }
        return false;
    }

    private void clickDownloadAndFileDlg(WebElement downloadButton, String context) {
        try {
            // Click Download Template button
            clickButton(downloadButton, context);

            // Click FileDlg button in same context
            clickFileDlgButton(context);
        } catch (Exception e) {
            throw new RuntimeException("Failed to complete both clicks in " + context, e);
        }
    }

    private void clickFileDlgButton1(String context) {
        try {
             //final String DOWNLOAD_PATH = System.getProperty("user.dir") + File.separator + "DownloadPath";
            WebElement fileDlg = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("FileDlg")
            ));
            Path absolutePath = Paths.get(DOWNLOAD_PATH).toAbsolutePath();

            fileDlg.sendKeys(absolutePath.toString());
            System.out.println("Send file");

            //clickButton(fileDlg, context + " -> FileDlg");
        } catch (Exception e) {
            throw new RuntimeException("FileDlg button not found in " + context, e);
        }
    }


    private void clickFileDlgButton(String context) {
        try {

            XMLModifier.modifyXMLFile();
            final String DOWNLOAD_PATH = "E:\\Automation Project\\WebProLiFiC 3.O\\DownloadPath";

            // Get the latest file from download directory
            Path dirPath = Paths.get(DOWNLOAD_PATH);

            if (!Files.exists(dirPath)) {
                throw new RuntimeException("Download directory not found: " + DOWNLOAD_PATH);
            }

            // Find latest XML file
            Path latestFile = Files.list(dirPath)
                    .filter(path -> path.toString().endsWith(".xml"))
                    .max(Comparator.comparing(path -> {
                        try {
                            return Files.getLastModifiedTime(path);
                        } catch (IOException e) {
                            return FileTime.from(Instant.EPOCH);
                        }
                    }))
                    .orElseThrow(() -> new RuntimeException("No XML files found in directory"));

            // Get absolute path of the file
            String absolutePath = latestFile.toAbsolutePath().toString();
            System.out.println("Latest file to upload: " + absolutePath);

            // Find and interact with the file input
            WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("FileDlg")));
            DynamicWait.longWait();

            Robot robot = new Robot();
            robot.delay(1000); // Optional: Wait 1 second
            robot.keyPress(KeyEvent.VK_ESCAPE);
            fileInput.sendKeys(absolutePath);
            robot.keyPress(KeyEvent.VK_ESCAPE);




            System.out.println("Successfully uploaded latest XML file from: " + DOWNLOAD_PATH);
            Utilities.Click(UploadData);

        } catch (Exception e) {
            throw new RuntimeException("File upload failed in " + context + ". Reason: " + e.getMessage(), e);
        }
    }






    private void clickButton(WebElement button, String context) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(button));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
            Utilities.JavaScriptClick(driver, button);
            System.out.println("Successfully clicked button in context: " + context);
        } catch (Exception e) {
            System.out.println("Standard click failed, trying alternative methods...");
            Utilities.ActionClick(driver, button);
        }
    }

    private WebElement findDownloadButtonInCurrentContext() {
        List<By> locators = Arrays.asList(
                By.xpath("//input[@value='Download Template']"),
                By.xpath("//button[contains(text(), 'Download Template')]")
        );

        for (By locator : locators) {
            List<WebElement> elements = driver.findElements(locator);
            if (!elements.isEmpty() && elements.get(0).isEnabled()) {
                return elements.get(0);
            }
        }
        return null;
    }

    private boolean switchToIframe(String iframeId) {
        try {
            driver.switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeId));
            wait.until(d -> d.findElement(By.tagName("body")).isDisplayed());
            return true;
        } catch (Exception e) {
            System.out.println("Frame switch failed: " + iframeId + " - " + e.getMessage());
            return false;
        }
    }

    private void handleException(Exception e) {
        System.out.println("Error during execution: " + e.getMessage());
        ExtentTestManager.createAssertTestStepWithScreenshot("Excel Download", Status.FAIL,
                "Failed to perform operation", true, e);
        throw new RuntimeException(e);
    }

    // Existing VendorSetup method remains unchanged
    public void VendorSetup() {
        // ... existing implementation ...
    }

    private static final String DOWNLOAD_PATH = System.getProperty("user.dir") + File.separator + "DownloadPath";

    private String getLatestXMLFile() {


        File downloadDir = new File(DOWNLOAD_PATH);

        // Verify directory exists
        if (!downloadDir.exists() || !downloadDir.isDirectory()) {
            throw new RuntimeException("Download directory not found: " + DOWNLOAD_PATH);
        }

        // Get latest modified XML file
        File[] files = downloadDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".xml"));

        if (files == null || files.length == 0) {
            throw new RuntimeException("No XML files found in directory: " + DOWNLOAD_PATH);
        }

        // Sort by last modified date
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

        return files[0].getAbsolutePath();
    }
}
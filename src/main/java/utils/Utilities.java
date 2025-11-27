package utils;

import baselibrary.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import extentreports.ExtentTestManager;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.NoSuchFileException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utilities {
    public static final long WAIT = 9;
    private static Logger Log = LogManager.getLogger(logs.Log.class);

    private static String currentStep = "";

    public static synchronized void WaitForPageLoad(WebDriver driver) {
        try {
            WebDriverWait cwait = new WebDriverWait(driver, Duration.ofSeconds(30));
            ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("loaded") || ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                }
            };
            cwait.until(pageLoadCondition);
        } catch (Exception e) {
            Log.error("Issue with page load time, tried waiting 30 seconds for the page to load");
            Assert.fail("Issue with page load time, tried waiting 30 seconds for the page to load");
        }
    }

    public static synchronized void WaitTillElementDisplayed(WebDriver driver, WebElement element) {
        WaitForPageLoad(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT));
        wait.until(ExpectedConditions.visibilityOf(element));
        if (!element.isDisplayed() || !element.isEnabled()) {
            ExtentTestManager.createAssertTestStepWithScreenshot("WaitTillElementDisplayed", Status.FAIL, "Element is not visible" + element.getAccessibleName(), false);
        }
    }


    public static synchronized void WaitTillElementRefreshed(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(toByVal(element))));
        driver.findElement(toByVal(element));
    }

    public static synchronized void WaitForNumberOfElementsToBeMoreThan(WebDriver driver, WebElement element, Integer numberOfElementsToBeMoreThan) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(toByVal(element), numberOfElementsToBeMoreThan));
        if (!(driver.findElements(toByVal(element)).size() > numberOfElementsToBeMoreThan)) {
            ExtentTestManager.createAssertTestStepWithScreenshot("WaitTillElementDisplayed", Status.FAIL, "Element is not visible", false);
        }
    }

    public static synchronized void WaitForNumberOfElementsToBe(WebDriver driver, WebElement element, Integer NumberOfElements) {
        WaitForPageLoad(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT));
        wait.until(ExpectedConditions.numberOfElementsToBe(toByVal(element), NumberOfElements));
        if (driver.findElements(toByVal(element)).size() != NumberOfElements) {
            ExtentTestManager.createAssertTestStepWithScreenshot("WaitTillElementDisplayed", Status.FAIL, "Element is not visible", false);
        }
    }


    public static synchronized void WaitTillVisibilityOfAllElementsLocatedBy(WebDriver driver, WebElement element) {
        WaitForPageLoad(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(toByVal(element)));
        if (!element.isDisplayed() || !element.isEnabled()) {
            ExtentTestManager.createAssertTestStepWithScreenshot("WaitTillElementDisplayed", Status.FAIL, "Element is not visible", false);
        }
    }


    public static synchronized void WaitTillElementNotDisplayed(WebDriver driver, WebElement element) {
        WaitForPageLoad(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT));
        wait.until(ExpectedConditions.invisibilityOf(element));
        if (element.isDisplayed() || element.isEnabled()) {
            ExtentTestManager.createAssertTestStepWithScreenshot("WaitTillElementDisplayed", Status.FAIL, element + " is still visible", false);
        }
    }

    public static synchronized void WaitTillElementIsClickable(WebDriver driver, WebElement element) {
        WaitForPageLoad(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        if (!element.isEnabled()) {
            ExtentTestManager.createAssertTestStepWithScreenshot("WaitTillElementIsClickable", Status.FAIL, "Element is not clickable", false);
        }
    }

    public static synchronized void WaitTillAttributeValueToBe(WebDriver driver, WebElement element, String attribute, String AttributeValue) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT));
            wait.until(ExpectedConditions.attributeToBe(element, attribute, AttributeValue));
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("WaitTillAttributeValueToBe", Status.FAIL, attribute + "Attribute doesn't contain " + AttributeValue, false);
        }
    }

    public static synchronized void SendKeys(WebDriver driver, WebElement element, String text) throws InterruptedException {
        WaitForPageLoad(driver);
        scrollIntoCenterWithinDiv(driver, element);
        WaitTillElementDisplayed(driver, element);
        HighlightElement(driver, element);

        if (element.isDisplayed() || element.isEnabled()) {
            element.clear();
            Thread.sleep(100);
            element.sendKeys(text);
            Log.info("Value :" + text + " has been entered");
        }
    }

    public static synchronized void clearText(WebDriver driver, WebElement element) throws InterruptedException {
        scrollIntoCenterWithinDiv(driver, element);
        WaitTillElementDisplayed(driver, element);
        if (element.isDisplayed() || element.isEnabled()) {
            element.clear();
        }
    }

    public static synchronized void SendKeysForFileUpload(WebDriver driver, WebElement element, String FileName) {
        try {
            File file = new File(FileName);
            if (file.exists() && file.isFile()) {
                WaitForPageLoad(driver);
                element.sendKeys(FileName);
                Thread.sleep(200);
                Log.info("File with filename: " + FileName + " has been uploaded");
            } else {
                Log.error("File Not Found. Check the FilePath. Given Filepath: " + FileName);
                throw new NoSuchFileException(FileName);
            }
        } catch (Exception e) {
            Log.error(ExceptionUtils.getStackTrace(e));
        }
    }

    //    public static synchronized void Click(WebDriver driver, WebElement element) throws InterruptedException {
//        WaitForPageLoad(driver);
//        scrollIntoCenterWithinDiv(driver, element);
//        WaitTillElementDisplayed(driver, element);
//       // if(element.isDisplayed() || element.isEnabled())
//        {
//            HighlightElement(driver, element);
//            element.click();
//            if(driver.findElements(toByVal(element)).size() > 0)
//                UnHighlightElement(driver, element);
//        }
//    }
    public static synchronized void selectBy(WebDriver driver, String type, WebElement element, String value) {
        WaitForPageLoad(driver);
        scrollIntoCenterWithinDiv(driver, element);
//        element.sendKeys(Keys.ARROW_UP);
//        element.sendKeys(Keys.ARROW_UP);
        WaitTillElementDisplayed(driver, element);
        if (element.isDisplayed() || element.isEnabled()) {
            Select dropDownElement = new Select(element);
            switch (type.trim().toLowerCase()) {
                case "value":
                    dropDownElement.selectByValue(value);
                    break;
                case "index":
                    if (!value.isEmpty()) {
                        dropDownElement.selectByIndex(Integer.parseInt(value));
                    } else {
                        ExtentTestManager.createAssertTestStepWithScreenshot("selectBy", Status.FAIL, "Value is either null or empty", false);
                    }
                    break;
                case "visibletext":
                    dropDownElement.selectByVisibleText(value);
                    break;
            }
        }
    }

    public static synchronized void selectByContainsText(WebDriver driver, WebElement element, String value) {
        WaitForPageLoad(driver);
        scrollIntoView(driver, element);
        WaitTillElementDisplayed(driver, element);
        if (element.isDisplayed() || element.isEnabled()) element.click();
        {
            Select dropDownElement = new Select(element);
            List<WebElement> selectedOptions = dropDownElement.getAllSelectedOptions();
            for (int i = 0; i < selectedOptions.size(); i++) {
                String selectedOptionsText = selectedOptions.get(i).getText();
                if (selectedOptionsText.trim().toLowerCase().contains(value.trim().toLowerCase())) {
                    String optionIndex = selectedOptions.get(i).getAttribute("index");
                    dropDownElement.selectByIndex(Integer.parseInt(optionIndex));
                }
            }
        }
    }

    public static synchronized void scrollIntoView(WebDriver driver, WebElement element) {
        WaitForPageLoad(driver);
        JavascriptExecutor je = (JavascriptExecutor) driver;
        //je.executeScript("window.scrollTo(0,0)");
        je.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static synchronized void scrollIntoCenterWithinDiv(WebDriver driver, WebElement element) {
        try {

            WaitForPageLoad(driver);
            JavascriptExecutor je = (JavascriptExecutor) driver;
            je.executeScript("arguments[0].scrollIntoView({behavior: \"auto\", block: \"center\", inline: \"center\"});", element);
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Element not Found", Status.FAIL, element.getAccessibleName() + " is either not visible or inside a Frame", true, e);
            System.out.println(element.getAccessibleName() + "is insede a frame of not visible");
        }
    }


    @BeforeMethod
    public static synchronized String randomGroupName() {

        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String generatedString = salt.toString();
        return generatedString;
    }

    @BeforeMethod
    public static synchronized String randomGroupNameWithoutNumber() {

        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String generatedString = salt.toString();
        return generatedString;
    }


    public static synchronized String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999999);
        // this will convert any number sequence into 6 character.
        return String.format("%09d", number);
    }

    public static synchronized String getRandomNumber() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(99999);
        // this will convert any number sequence into 6 character.
        return String.format("%04d", number);
    }

    public static synchronized void scrollToTop(WebDriver driver) {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("window.scrollTo(0,0)");
    }

    public static synchronized By toByVal(WebElement we) {
        // By format = "[foundFrom] -> locator: term"
        // see RemoteWebElement toString() implementation
        String[] data = new String[6];
        try {
            data = we.toString().split("'By.")[1].replace("]\'", "]").split(": ");
        } catch (Exception e) {
            data = we.toString().split(" -> ")[1].replace("]]", "]").split(": ");
        }
        String locator = data[0];
        String term = "";

        if (StringUtils.countMatches(data[1], "]") > StringUtils.countMatches(data[1], "["))
            term = data[1].substring(0, data[1].length() - 1);
        else term = data[1];

        switch (locator) {
            case "xpath":
                return By.xpath(term);
            case "css selector":
                return By.cssSelector(term);
            case "id":
                return By.id(term);
            case "tag name":
                return By.tagName(term);
            case "name":
                return By.name(term);
            case "link text":
                return By.linkText(term);
            case "class name":
                return By.className(term);
        }
        return (By) we;
    }

    public static synchronized void MouseOver(WebDriver driver, WebElement element) {
        WaitForPageLoad(driver);
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    /**
     * @param element
     * @return
     */
    public static synchronized String getLocator(WebElement element) {
        try {
            Object proxyOrigin = FieldUtils.readField(element, "h", true);
            Object locator = FieldUtils.readField(proxyOrigin, "locator", true);
            Object findBy = FieldUtils.readField(locator, "by", true);
            Object xpathExpression = FieldUtils.readField(findBy, "xpathExpression", true);
            if (xpathExpression != null) {
                return xpathExpression.toString();
            }
        } catch (IllegalAccessException ignored) {
            ignored.printStackTrace();
        }
        return "[unknown]";
    }


    public static synchronized void JavaScriptClick(WebDriver driver, WebElement element) throws InterruptedException {
        WaitForPageLoad(driver);
        HighlightElement(driver, element);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", element);
        if (driver.findElements(By.xpath(getLocator(element))).size() > 0) UnHighlightElement(driver, element);
    }


    public static synchronized void scrollToDown(WebDriver driver) {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("window.scrollBy(0,-350)", "");
    }


    public static synchronized void sendFilePath(String path) throws AWTException {
        Robot robot = new Robot();
        robot.setAutoDelay(2000);
        StringSelection stringselection = new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringselection, null);
        robot.setAutoDelay(2000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        //robot.setAutoDelay(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public static synchronized boolean isFileDownloaded(String downloadPath, String fileName, Boolean DeleteFileAfterVerification, Integer TimeInSeconds) {
        try {
            File dir = new File(downloadPath);
            File[] dirContents;

            for (int j = 0; j < TimeInSeconds; j++) {
                dirContents = dir.listFiles();
                Thread.sleep(1000);

                for (int i = 0; i < dirContents.length; i++) {
                    if (dirContents[i].getName().contains("crdownload")) {
                        return false;
                    }
                    if (CheckIfStringMatchesRegex(fileName, dirContents[i].getName())) {
                        if (DeleteFileAfterVerification) {
                            //File has been found, it can now be deleted:
                            dirContents[i].delete();
                        }
                        return true;
                    }
                }
            }

            dir = getLastModified(downloadPath);
            if (CheckIfStringMatchesRegex(fileName.split("\\.")[0] + ".+[0-9a-zA-Z_\\-.()]", dir.getName()))
                return true;
        } catch (NumberFormatException | InterruptedException e) {
            Log.error(ExceptionUtils.getStackTrace(e));
        }
        return false;
    }

    public static synchronized boolean isFileDownloadedWithCustomName(String downloadPath, String fileNameStartsWith, Boolean DeleteFileAfterVerification, Integer TimeInSeconds) {
        try {
            File dir = new File(downloadPath);
            File[] dirContents;

            for (int j = 0; j < TimeInSeconds; j++) {
                dirContents = dir.listFiles();
                Thread.sleep(1000);

                for (int i = 0; i < dirContents.length; i++) {
                    if (dirContents[i].getName().contains("crdownload")) {
                        return false;
                    }
                    if (dirContents[i].getName().startsWith(fileNameStartsWith)) {
                        if (DeleteFileAfterVerification) {
                            //File has been found, it can now be deleted:
                            dirContents[i].delete();
                        }
                        return true;
                    }
                }
            }

            return true;
        } catch (NumberFormatException | InterruptedException e) {
            Log.error(ExceptionUtils.getStackTrace(e));
        }
        return false;
    }


    public static synchronized boolean StringMatch(String PatternToBeCompiled, String StringToMatch) {
        boolean found = false;
        while (true) {
            Pattern pattern = Pattern.compile(PatternToBeCompiled);
            Matcher matcher = pattern.matcher(StringToMatch);
            while (matcher.find()) {
                Log.info(StringToMatch + " matches the pattern " + PatternToBeCompiled);
                found = true;
            }
            return found;
        }
    }


    private static synchronized boolean CheckIfStringMatchesRegex(String PatternToBeCompiled, String StringToMatch) {
        boolean found = false;
        while (true) if (StringToMatch.contains(PatternToBeCompiled)) {

            Log.info(StringToMatch + " matches the pattern " + PatternToBeCompiled);
            found = true;
//        {
//            if(PatternToBeCompiled.contains("("))
//            {
//                PatternToBeCompiled = PatternToBeCompiled.replace("(","\\(");
//                PatternToBeCompiled = PatternToBeCompiled.replace(")","\\)");
//            }
//            Pattern pattern = Pattern.compile(PatternToBeCompiled);
//            Matcher matcher = pattern.matcher(StringToMatch);
//            while(matcher.find())
//            {
//                Log.info(StringToMatch + " matches the pattern " + PatternToBeCompiled);
//                found = true;
//            }
            return found;
        }
    }

    public static synchronized void HighlightElement(WebDriver driver, WebElement element) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='2px solid red'", element);
        Thread.sleep(10);
    }

    public static synchronized void UnHighlightElement(WebDriver driver, WebElement element) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='0px solid red'", element);
        Thread.sleep(100);
    }

    public static synchronized void ActionClick(WebDriver driver, WebElement element) {
        Actions ac = new Actions(driver);
        ac.click(element).build().perform();
    }

    public static synchronized void DoubleClick(WebElement element) {
        Actions ac = new Actions(BaseTest.getDriver());
        ac.doubleClick(element).build().perform();
    }


    public static synchronized File getLastModified(String directoryFilePath) {
        File directory = new File(directoryFilePath);
        File[] files = directory.listFiles(File::isFile);
        long lastModifiedTime = Long.MIN_VALUE;
        File chosenFile = null;

        if (files != null) {
            for (File file : files) {
                if (file.lastModified() > lastModifiedTime) {
                    chosenFile = file;
                    lastModifiedTime = file.lastModified();
                }
            }
        }
        Log.info("File: " + chosenFile.getAbsolutePath());
        return chosenFile;
    }

    public static synchronized String getSelectedValue(WebDriver driver, WebElement element) {
        String selectedoption = null;
        WaitForPageLoad(driver);
        scrollIntoView(driver, element);
        WaitTillElementDisplayed(driver, element);
        if (element.isDisplayed() || element.isEnabled()) {
            Select select = new Select(element);
            WebElement option = select.getFirstSelectedOption();
            selectedoption = option.getText();
        }
        return selectedoption;
    }

    public static synchronized boolean getDropDownStatusValue(WebDriver driver, List<WebElement> element, List expected_value) {
        WaitForPageLoad(driver);
        Boolean flag = false;
        List lt = new ArrayList();
        for (int l = 0; l < 4; l++) {
            lt.add(element.get(l).getText());
        }
        if (expected_value.equals(lt)) {
            flag = true;
        }
        return flag;
    }


    public static synchronized String FileDownloadedName(String downloadPath, String... TimeinSeconds) throws InterruptedException {
        File dir = new File(downloadPath);
        File[] dirContents;
        int iteration = 10;
        String filename = null;
        if (TimeinSeconds.length > 0) {
            iteration = Integer.parseInt(TimeinSeconds[0]) * 2;
        }

        for (int j = 0; j < iteration; j++) {
            dirContents = dir.listFiles();
            Thread.sleep(500);

            for (int i = 0; i < dirContents.length; i++) {
                filename = dirContents[i].getName();
            }
        }
        return filename;
    }

    public static synchronized void openNewTabInChrome(WebDriver driver) {
        driver.switchTo().newWindow(WindowType.TAB);
    }


    public static synchronized void performSignature(WebDriver driver, WebElement SignatureCanvas) {
        try {
            Actions actions = new Actions(driver);
            actions.contextClick(SignatureCanvas).clickAndHold().moveToElement(SignatureCanvas, 20, -50).moveByOffset(50, 50).moveByOffset(80, -50).moveByOffset(100, 50).release(SignatureCanvas).build().perform();

            Log.info("Signature done successfully");
        } catch (NoSuchElementException e) {
            Log.error("Check locator for Signature Canvas");
            ExtentTestManager.createAssertTestStepWithScreenshot("performSignature", Status.FAIL, "Check locator for Signature Canvas, Signature Canvas not accessible", true);
        }

    }

    public static String StateRendomCode(String fixedPrefix, String excludeValue) {
        Random random = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // Allowed characters
        int length = excludeValue.length() - fixedPrefix.length(); // Calculate the variable length

        String result;
        do {
            StringBuilder sb = new StringBuilder(fixedPrefix); // Start with the fixed prefix
            for (int i = 0; i < length; i++) {
                sb.append(characters.charAt(random.nextInt(characters.length())));
            }
            result = sb.toString();
        } while (result.equals(excludeValue)); // Ensure it is not equal to the excluded value

        return result;
    }

    public static void WaitForPageLoad() {
        try {
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(30));
            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        } catch (Exception e) {
            System.err.println("Issue with page load time, tried waiting 30 seconds");
        }
    }

    public static void WaitTillElementDisplayed(WebElement element) {
        WaitForPageLoad();
        WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(WAIT));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void WaitTillElementClickable(WebElement element) {
        WaitForPageLoad();
        WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(WAIT));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void HighlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) BaseTest.getDriver();
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }


    public static void SendKeys(WebElement element, String text) {
        HighlightElement(element);
        WaitForPageLoad();
        WaitTillElementDisplayed(element);
        element.clear();
        element.sendKeys(text);
    }

    public static void selectBy(String type, WebElement element, String value) {
        WaitForPageLoad();
        WaitTillElementDisplayed(element);
        Select dropDownElement = new Select(element);
        switch (type.toLowerCase()) {
            case "value":
                dropDownElement.selectByValue(value);
                break;
            case "index":
                dropDownElement.selectByIndex(Integer.parseInt(value));
                break;
            case "visibletext":
                dropDownElement.selectByVisibleText(value);
                break;
        }
    }

    public static void scrollIntoView(WebElement element) {
        WaitForPageLoad();
        ((JavascriptExecutor) BaseTest.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollToTop() {
        ((JavascriptExecutor) BaseTest.getDriver()).executeScript("window.scrollTo(0,0)");
    }

    public static void ClickCheckBox(WebElement element) {

        scrollIntoCenterWithinDiv(BaseTest.getDriver(), element);
        WaitForPageLoad();
        WaitTillElementClickable(element);
        HighlightElement(element);
        element.click();


    }


    public static void Click(WebElement element) {
        scrollIntoCenterWithinDiv(BaseTest.getDriver(), element);
        SoftAssert softAssert = new SoftAssert();
        WaitForPageLoad();
        WaitTillElementClickable(element);
        softAssert.assertEquals(element.isEnabled(), true, element.getAccessibleName() + " is not enabled");
        softAssert.assertEquals(element.isDisplayed(), true, element.getAccessibleName() + " is not displayed");
        softAssert.assertEquals(!element.isSelected(), true, element.getAccessibleName() + " is already selected");
        boolean assertionsFailed = false;
        try {
            softAssert.assertAll();
        } catch (AssertionError e) {
            assertionsFailed = true;
        }

        if (!assertionsFailed && element.isDisplayed() && element.isEnabled()) {
            // Optionally highlight the element before clicking
           // HighlightElement(element);
            element.click();

        }
        //else {
//            ExtentTestManager.createAssertTestStepWithScreenshot("Click", Status.FAIL, element.getAccessibleName() + " is not enabled", true);
//        }
    }

    public static synchronized void Click(WebDriver driver, WebElement element) throws InterruptedException {

        WaitForPageLoad(driver);
        scrollIntoCenterWithinDiv(driver, element);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(element.isEnabled(), true, element.getAccessibleName() + " is not enabled");
        softAssert.assertEquals(element.isDisplayed(), true, element.getAccessibleName() + " is not displayed");
        softAssert.assertEquals(!element.isSelected(), true, element.getAccessibleName() + " is already selected");
        boolean assertionsFailed = false;

        try {
            softAssert.assertAll();
        } catch (AssertionError e) {
            assertionsFailed = true;
        }

        // If assertions failed, skip clicking and go to the else block
        if (!assertionsFailed && element.isDisplayed() && element.isEnabled()) {
            // Optionally highlight the element before clicking
            HighlightElement(driver, element);
            element.click();

        } else {
            ExtentTestManager.createAssertTestStepWithScreenshot("Click", Status.FAIL, element.getAccessibleName() + " is not enabled", true);
        }
    }


//    public static String getStep() {
//        return currentStep;
//    }
//
//
//
//
//
//        public static void logStep(String stepDescription) {
//           // ExtentTestManager.getTest().log(Status.INFO, stepDescription);
//        }
//
//        public static void logFailure(String stepDescription, Exception e) {
//            ExtentTestManager.getTest().log(Status.INFO, "Step failed: " + stepDescription);
//            ExtentTestManager.createAssertTestStepWithScreenshot(stepDescription, Status.FAIL, "", true, e);
//        }


    public static void logerror(String operationName, StepExecutor executor) {
        List<String> logMessages = new ArrayList<>();
        try {
            executor.execute(logMessages);
        } catch (Exception e) {
            // Log all steps executed so far only on failure
            for (String message : logMessages) {
                ExtentTestManager.getTest().log(Status.INFO, message);
            }
            // Log failure with screenshot and exception details
            ExtentTestManager.createAssertTestStepWithScreenshot(operationName + " execution failed", Status.FAIL, "Exception occurred: " + e.getMessage(), true, e);
        }
    }

    // Interface to define steps and log messages
    @FunctionalInterface
    public interface StepExecutor {
        void execute(List<String> logMessages) throws Exception;
    }

    public static String generateUniqueCode(String prefix, int totalLength) {
        // Base time (milliseconds since epoch)
        long currentTimeMillis = System.currentTimeMillis();

        // Convert to base36 (0-9 + A-Z) to shorten
        String encodedTime = Long.toString(currentTimeMillis, 36).toUpperCase();

        // Combine with prefix
        String code = prefix + encodedTime;

        // If longer than max allowed, take the last characters (unique part)
        if (code.length() > totalLength) {
            code = code.substring(code.length() - totalLength);
        }

        return code;
    }



}
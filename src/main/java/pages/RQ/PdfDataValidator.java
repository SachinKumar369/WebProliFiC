package pages.RQ;

import baselibrary.BaseTest;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.Utilities;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PdfDataValidator {
    private final String downloadPath = "E:\\Automation Project\\WebProLiFiC 3.O\\DownloadPath";

    public PdfDataValidator(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "btnOK")
    private WebElement OKBtn;
    @FindBy(id = "cphDialogHeader_btnPrint")
    private WebElement Print;



    public void validatePdfAgainstWebpage() throws Exception {
        // Step 1: Get the latest downloaded PDF file
        File latestPdfFile = getLatestPdfFile();
        if (latestPdfFile == null) {
            throw new RuntimeException("No PDF file found in the download directory: " + downloadPath);
        }

        // Step 2: Extract item codes from the webpage
        List<String> webItemCodes = extractItemCodesFromWebpage();

        // Step 3: Check if webpage item codes are present in the PDF
        List<String> missingItemCodes = extractItemCodesFromPdf(latestPdfFile, webItemCodes);

        // Step 4: Check if any webpage item codes are missing in the PDF
        compareItemCodes(webItemCodes, missingItemCodes);

        List<String> webItemNames = extractItemNamesFromWebpage();
        List<String> missingItemNames = extractItemNameFromPdf(latestPdfFile,webItemNames);

        compareItemName(webItemNames,missingItemNames);
    }

    private File getLatestPdfFile() throws Exception {
        Path downloadDir = Paths.get(downloadPath);
        File latestPdfFile = Files.list(downloadDir)
                .filter(path -> path.toString().endsWith(".pdf"))
                .max(Comparator.comparingLong(path -> path.toFile().lastModified()))
                .map(Path::toFile)
                .orElse(null);
        if (latestPdfFile != null) {
            System.out.println("Latest PDF File: " + latestPdfFile.getAbsolutePath());
            System.out.println("Last Modified: " + new java.util.Date(latestPdfFile.lastModified()));
        }
        return latestPdfFile;
    }


    private List<String> extractItemCodesFromWebpage() {
        List<String> itemCodes = new ArrayList<>();

        try {
            // Switch to the iframe containing the requisition data
            System.out.println("Switching to iframe 'MultiPageiframeDlg'...");
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            // Debug: Print the iframe's HTML to confirm we’re in the right context
            String iframeHtml = BaseTest.getDriver().getPageSource();
            System.out.println("Iframe HTML: " + iframeHtml.substring(0, Math.min(iframeHtml.length(), 500)) + "...");

            // Locate elements with IDs starting with "item_id_1_"
            List<WebElement> itemElements = BaseTest.getDriver().findElements(By.xpath("//*[@id[starts-with(., 'item_id_1_')]]"));

            // Debug: Print the number of elements found
            System.out.println("Found " + itemElements.size() + " elements with IDs starting with 'item_id_1_'");

            if (itemElements.isEmpty()) {
                throw new RuntimeException("No elements found with IDs starting with 'item_id_1_'. Check the XPath or page content.");
            }

            for (WebElement element : itemElements) {
                // Debug: Print the element's ID and outer HTML to inspect its structure
                String elementId = element.getAttribute("id");
                System.out.println("Element ID: " + elementId);
                System.out.println("Element HTML: " + element.getAttribute("outerHTML"));

                // Extract the value or text of the element
                String itemCode = element.getAttribute("value");
                if (itemCode == null || itemCode.trim().isEmpty()) {
                    itemCode = element.getText().trim();
                }

                if (!itemCode.isEmpty()) {
                    itemCodes.add(itemCode);
                    System.out.println("Extracted Item Code: " + itemCode);
                } else {
                    System.out.println("No item code found in element with ID: " + elementId);
                }
            }

            // Switch back to the default content
            BaseTest.getDriver().switchTo().defaultContent();

            if (itemCodes.isEmpty()) {
                throw new RuntimeException("No item codes extracted from elements with IDs starting with 'item_id_1_'. Check the element content.");
            }

        } catch (Exception e) {
            System.err.println("Error extracting item codes from webpage: " + e.getMessage());
            throw new RuntimeException("Failed to extract item codes from webpage", e);
        }

        // Debug: Print all extracted item codes
        System.out.println("Web Item Codes: " + itemCodes);

        return itemCodes;
    }

    private List<String> extractItemCodesFromPdf(File pdfFile, List<String> webItemCodes) throws Exception {
        List<String> missingItemCodes = new ArrayList<>(webItemCodes);

        try (PDDocument document = Loader.loadPDF(pdfFile)) {
            PDFTextStripper stripper = new PDFTextStripper();
            String pdfText = stripper.getText(document);

            // Debug: Print the raw PDF text to confirm structure
            System.out.println("PDF Text: " + pdfText);

            // Split PDF text into lines
            String[] lines = pdfText.split("\n");

            // Check for each webpage item code in the PDF
            for (String itemCode : webItemCodes) {
                // Escape special characters in itemCode for regex
                String escapedItemCode = Pattern.quote(itemCode);
                Pattern itemCodePattern = Pattern.compile(escapedItemCode);

                boolean found = false;
                for (String line : lines) {
                    // Debug: Print each line being checked
                    System.out.println("Checking line for item code " + itemCode + ": " + line);

                    Matcher matcher = itemCodePattern.matcher(line);
                    if (matcher.find()) {
                        System.out.println("Found item code in PDF: " + itemCode);
                        missingItemCodes.remove(itemCode);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Item code not found in PDF: " + itemCode);
                }
            }
        }

        // Debug: Print all missing item codes
        System.out.println("Missing Item Codes in PDF: " + missingItemCodes);

        return missingItemCodes;
    }

    private void compareItemCodes(List<String> webItemCodes, List<String> missingItemCodes) {
        // Check if any webpage item codes are missing in the PDF
        if (!missingItemCodes.isEmpty()) {
            Assert.fail("The following item codes from the webpage were not found in the PDF: " + missingItemCodes);
        }

        System.out.println("All webpage item codes were found in the PDF. Validation successful!");
    }

    private List<String> extractItemNamesFromWebpage() {
        List<String> itemNames = new ArrayList<>();

        try {
            // Switch to the iframe containing the requisition data
            BaseTest.getDriver().switchTo().defaultContent();
            System.out.println("Switching to iframe 'MultiPageiframeDlg'...");
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            // Debug: Print the iframe's HTML to confirm we’re in the right context
            String iframeHtml = BaseTest.getDriver().getPageSource();
            System.out.println("Iframe HTML: " + iframeHtml.substring(0, Math.min(iframeHtml.length(), 500)) + "...");

            // Locate elements with IDs starting with "item_id_1_"
            List<WebElement> itemElements = BaseTest.getDriver().findElements(By.xpath("//*[@id[starts-with(., 'org_req_qty_6_')]]"));

            // Debug: Print the number of elements found
            System.out.println("Found " + itemElements.size() + " elements with IDs starting with 'org_req_qty_6_'");

            if (itemElements.isEmpty()) {
                throw new RuntimeException("No elements found with IDs starting with 'org_req_qty_6_'. Check the XPath or page content.");
            }

            for (WebElement element : itemElements) {
                // Debug: Print the element's ID and outer HTML to inspect its structure
                String elementId = element.getAttribute("id");
                System.out.println("Element ID: " + elementId);
                System.out.println("Element HTML: " + element.getAttribute("outerHTML"));

                // Extract the value or text of the element
                String itemCode = element.getAttribute("value");
                if (itemCode == null || itemCode.trim().isEmpty()) {
                    itemCode = element.getText().trim();
                }

                if (!itemCode.isEmpty()) {
                    itemNames.add(itemCode);
                    System.out.println("Extracted Item Code: " + itemCode);
                } else {
                    System.out.println("No item code found in element with ID: " + elementId);
                }
            }

            // Switch back to the default content
            BaseTest.getDriver().switchTo().defaultContent();

            if (itemNames.isEmpty()) {
                throw new RuntimeException("No item codes extracted from elements with IDs starting with 'item_id_1_'. Check the element content.");
            }

        } catch (Exception e) {
            System.err.println("Error extracting item codes from webpage: " + e.getMessage());
            throw new RuntimeException("Failed to extract item codes from webpage", e);
        }

        // Debug: Print all extracted item codes
        System.out.println("Web Item Codes: " + itemNames);

        return itemNames;
    }

    private List<String> extractItemNameFromPdf(File pdfFile, List<String> webItemNames) throws Exception {
        List<String> missingItemNames = new ArrayList<>(webItemNames);

        try (PDDocument document = Loader.loadPDF(pdfFile)) {
            PDFTextStripper stripper = new PDFTextStripper();
            String pdfText = stripper.getText(document);

            // Debug: Print the raw PDF text to confirm structure
            System.out.println("PDF Text: " + pdfText);

            // Split PDF text into lines
            String[] lines = pdfText.split("\n");

            // Check for each webpage item code in the PDF
            for (String webItem : webItemNames) {
                boolean found = false;
                for (String line : lines) {
                    String[] tokens = line.trim().split("\\s+");
                    for (String token : tokens) {
                        try {
                            BigDecimal webValue = new BigDecimal(webItem);
                            BigDecimal pdfValue = new BigDecimal(token);

                            if (webValue.compareTo(pdfValue) == 0) {
                                System.out.println("Match found for item code: " + webItem + " == " + token);
                                missingItemNames.remove(webItem);
                                found = true;
                                break;
                            }
                        } catch (NumberFormatException e) {
                            // not a number, skip this token
                        }
                    }
                    if (found) break;
                }
                if (!found) {
                    System.out.println("Item code not found in PDF: " + webItem);
                }
            }


        }

        // Debug: Print all missing item codes
        System.out.println("Missing Item Codes in PDF: " + missingItemNames);

        return missingItemNames;
    }

    private void compareItemName(List<String> webItemCodes, List<String> missingNamesCodes) {
        // Check if any webpage item codes are missing in the PDF
        if (!missingNamesCodes.isEmpty()) {
            Assert.fail("The following item codes from the webpage were not found in the PDF: " + missingNamesCodes);
        }

        System.out.println("All webpage item codes were found in the PDF. Validation successful!");
    }


}
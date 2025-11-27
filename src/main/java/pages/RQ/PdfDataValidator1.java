package pages.RQ;

import baselibrary.BaseTest;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.Loader; // New import for PDFBox 3.x
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.Utilities;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PdfDataValidator1 {
    private final String downloadPath = "E:\\Automation Project\\WebProLiFiC 3.O\\DownloadPath";

    static class ItemData {
        String itemCode;
        String description;
        String unit;
        double itemRate;
        double requestedQuantity;
        double totalValue;

        ItemData(String itemCode, String description, String unit, double itemRate, double requestedQuantity, double totalValue) {
            this.itemCode = itemCode;
            this.description = description;
            this.unit = unit;
            this.itemRate = itemRate;
            this.requestedQuantity = requestedQuantity;
            this.totalValue = totalValue;
        }
    }

    public PdfDataValidator1(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "btnOK")
    private WebElement OKBtn;
    @FindBy(id = "cphDialogHeader_btnPrint")
    private WebElement Print;

    public void validatePdfAgainstWebpage() throws Exception {
        BaseTest.getDriver().switchTo().defaultContent();
        BaseTest.getDriver().switchTo().frame("iframeGridDialog");
        Utilities.Click(OKBtn);

        BaseTest.getDriver().switchTo().defaultContent();
        BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");
        Utilities.Click(Print);



        File latestPdfFile = getLatestPdfFile();
        if (latestPdfFile == null) {
            throw new RuntimeException("No PDF file found in the download directory: " + downloadPath);
        }

        //List<ItemData> webData = extractDataFromWebpage();
        List<String> webItemCodes = extractItemCodesFromWebpage();

        List<ItemData> pdfData = extractDataFromPdf(latestPdfFile);
        List<String> pdfItemCodes = extractItemCodesFromPdf(latestPdfFile);
        compareItemCodes(webItemCodes, pdfItemCodes);
    }

    private File getLatestPdfFile() throws Exception {
        Path downloadDir = Paths.get(downloadPath);
        return Files.list(downloadDir)
                .filter(path -> path.toString().endsWith(".pdf"))
                .max(Comparator.comparingLong(path -> path.toFile().lastModified()))
                .map(Path::toFile)
                .orElse(null);
    }

    private List<String> extractItemCodesFromWebpage() {
        List<String> itemCodes = new ArrayList<>();

        try {
            // Switch to the iframe containing the requisition data
            System.out.println("Switching to iframe 'MultiPageiframeDlg'...");
            BaseTest.getDriver().switchTo().defaultContent();
            BaseTest.getDriver().switchTo().frame("MultiPageiframeDlg");

            // Debug: Print the iframe's HTML to confirm we’re in the right context
            String iframeHtml = BaseTest.getDriver().getPageSource();
            System.out.println("Iframe HTML: " + iframeHtml.substring(0, Math.min(iframeHtml.length(), 500)) + "...");

            // Locate elements with IDs starting with "item_id_1_"
            // Using XPath: //*[@id[starts-with(., 'item_id_1_')]]
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
                // If it’s an input element, use getAttribute("value"); otherwise, use getText()
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
    private List<ItemData> extractDataFromPdf(File pdfFile) throws Exception {
        List<ItemData> pdfData = new ArrayList<>();
        try (PDDocument document = Loader.loadPDF(pdfFile)) { // Updated for PDFBox 3.x
            PDFTextStripper stripper = new PDFTextStripper();
            String pdfText = stripper.getText(document);

            String[] lines = pdfText.split("\n");
            boolean tableStarted = false;
            for (String line : lines) {
                if (line.contains("Item Pack Description")) {
                    tableStarted = true;
                    continue;
                }
                if (tableStarted && line.contains("Grand Total")) {
                    break;
                }
                if (tableStarted && !line.trim().isEmpty()) {
                    String[] columns = line.trim().split("\\s+");
                    String itemCode = columns[0];
                    String description = String.join(" ", java.util.Arrays.copyOfRange(columns, 1, columns.length - 5));
                    String unit = columns[columns.length - 5];
                    String itemRateStr = columns[columns.length - 4];
                    String requestedQuantityStr = columns[columns.length - 2];
                    String totalValueStr = columns[columns.length - 1].replace(",", "");

                    double itemRate = itemRateStr.isEmpty() ? 0.0 : Double.parseDouble(itemRateStr);
                    double requestedQuantity = requestedQuantityStr.isEmpty() ? 0.0 : Double.parseDouble(requestedQuantityStr);
                    double totalValue = totalValueStr.isEmpty() || totalValueStr.equals("0.00") ? 0.0 : Double.parseDouble(totalValueStr);

                    pdfData.add(new ItemData(itemCode, description, unit, itemRate, requestedQuantity, totalValue));
                }
            }
        }
        return pdfData;
    }
    private void compareData(List<ItemData> webData, List<ItemData> pdfData) {
        Assert.assertEquals(webData.size(), pdfData.size(), "Number of items mismatch between webpage and PDF");

        for (int i = 0; i < webData.size(); i++) {
            ItemData webItem = webData.get(i);
            ItemData pdfItem = pdfData.get(i);

            Assert.assertEquals(webItem.itemCode, pdfItem.itemCode, "Item code mismatch for item " + (i + 1));
            Assert.assertEquals(webItem.description, pdfItem.description, "Description mismatch for item " + (i + 1));
            Assert.assertEquals(webItem.unit, pdfItem.unit, "Unit mismatch for item " + (i + 1));
            Assert.assertEquals(webItem.itemRate, pdfItem.itemRate, 0.01, "Item rate mismatch for item " + (i + 1));
            Assert.assertEquals(webItem.requestedQuantity, pdfItem.requestedQuantity, 0.01, "Requested quantity mismatch for item " + (i + 1));
            Assert.assertEquals(webItem.totalValue, pdfItem.totalValue, 0.01, "Total value mismatch for item " + (i + 1));
        }

        double webGrandTotal = webData.stream().mapToDouble(item -> item.totalValue).sum();
        double pdfGrandTotal = pdfData.stream().mapToDouble(item -> item.totalValue).sum();
        Assert.assertEquals(webGrandTotal, pdfGrandTotal, 0.01, "Grand total mismatch");
    }

    private void compareItemCodes(List<String> webItemCodes, List<String> pdfItemCodes) {
        // Validate the number of items
        Assert.assertEquals(webItemCodes.size(), pdfItemCodes.size(), "Number of items mismatch between webpage and PDF");

        // Compare item codes
        for (int i = 0; i < webItemCodes.size(); i++) {
            String webItemCode = webItemCodes.get(i);
            String pdfItemCode = pdfItemCodes.get(i);

            // Validate item code
            Assert.assertEquals(webItemCode, pdfItemCode, "Item code mismatch at position " + (i + 1) + ": Web=" + webItemCode + ", PDF=" + pdfItemCode);
        }

        System.out.println("Item code validation successful!");
    }


    private List<String> extractItemCodesFromPdf(File pdfFile) throws Exception {
        List<String> itemCodes = new ArrayList<>();

        try (PDDocument document = Loader.loadPDF(pdfFile)) {
            PDFTextStripper stripper = new PDFTextStripper();
            String pdfText = stripper.getText(document);

            // Debug: Print the raw PDF text to confirm structure
            System.out.println("PDF Text: " + pdfText);

            // Split PDF text into lines
            String[] lines = pdfText.split("\n");

            // Parse table data
            boolean tableStarted = false;
            for (String line : lines) {
                if (line.contains("Item Pack Description")) {
                    tableStarted = true; // Start of table
                    continue;
                }
                if (tableStarted && line.contains("Grand Total")) {
                    break; // End of table
                }
                if (tableStarted && !line.trim().isEmpty()) {
                    // Split line into columns (assuming space-separated)
                    String[] columns = line.trim().split("\\s+");

                    // Extract the item code (first column)
                    String itemCode = columns[0];

                    // Debug: Print the extracted item code from PDF
                    System.out.println("Extracted PDF Item Code: " + itemCode);

                    itemCodes.add(itemCode);
                }
            }
        }

        // Debug: Print all extracted PDF item codes
        System.out.println("PDF Item Codes: " + itemCodes);

        if (itemCodes.isEmpty()) {
            throw new RuntimeException("No item codes found in the PDF. Check the PDF content or parsing logic.");
        }

        return itemCodes;
    }
}
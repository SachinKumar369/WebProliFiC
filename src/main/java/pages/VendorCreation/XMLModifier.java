package pages.VendorCreation;

import baselibrary.BaseTest;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.jdom2.output.Format;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.time.Duration;

public class XMLModifier {

    public XMLModifier(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public static String modifyXMLFile() {
        try {
            WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));

            // Define download directory
            String downloadPath = System.getProperty("user.dir") + File.separator + "DownloadPath";
            File downloadDir = new File(downloadPath);

            // Debug: Verify directory path and existence
            System.out.println("Checking download directory: " + downloadPath);
            System.out.println("Canonical path: " + downloadDir.getCanonicalPath());
            if (!downloadDir.exists()) {
                throw new RuntimeException("Download directory does not exist: " + downloadPath);
            }
            if (!downloadDir.isDirectory()) {
                throw new RuntimeException("Path is not a directory: " + downloadPath);
            }
            if (!downloadDir.canRead()) {
                throw new RuntimeException("Cannot read directory: " + downloadPath);
            }

            // Debug: List all files in the directory
            File[] files = downloadDir.listFiles();
            System.out.println("Files in directory:");
            if (files == null || files.length == 0) {
                System.out.println("No files found in directory.");
                throw new RuntimeException("No files found in download directory: " + downloadPath);
            } else {
                for (File file : files) {
                    System.out.println(" - " + file.getName() + " (Size: " + file.length() + " bytes, Last Modified: " + new Date(file.lastModified()) + ")");
                }
            }

            // Get the latest downloaded XML file
            File xmlFile = getLastModified(downloadPath);
            if (xmlFile == null || !xmlFile.exists()) {
                throw new RuntimeException("No XML files found in download directory: " + downloadPath);
            }
            if (xmlFile.length() == 0) {
                throw new RuntimeException("XML file is empty: " + xmlFile.getAbsolutePath());
            }

            // Debug: Print file details
            System.out.println("Selected XML file: " + xmlFile.getAbsolutePath());
            System.out.println("File size: " + xmlFile.length() + " bytes");
            System.out.println("Last modified: " + new Date(xmlFile.lastModified()));

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
                root.getChildren().forEach(child ->
                        System.out.println(child.getName() + " with attributes: " + child.getAttributes())
                );
                throw new RuntimeException("No Worksheet elements found in XML");
            } else {
                worksheets.forEach(ws ->
                        System.out.println(ws.getAttributeValue("Name", ssNs))
                );
            }

            // Find Data worksheet
            Element worksheet = worksheets.stream()
                    .filter(ws -> "Data".equalsIgnoreCase(ws.getAttributeValue("Name", ssNs)))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Data worksheet not found"));

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
            Element secondRow = rows.get(1); // Second row (0-based index)

            // Clear existing cells in the second row
            secondRow.removeChildren("Cell", defaultNs);
            System.out.println("Cleared existing cells in second row.");

            // Data to insert (aligned with column headers)
            String timeStamp = new SimpleDateFormat("MMyyHHmm").format(new Date());
            String[] data = new String[48]; // Match the 48 columns
            // Initialize with empty strings
            for (int i = 0; i < data.length; i++) {
                data[i] = "";
            }
            // Populate specific columns
            data[0] = "CORP"; // Account Group
            //data[1] = "CASH002" + timeStamp; // AP Account
            data[1] = timeStamp;
            data[2] = "Testing"; // AP Account Name
            data[3] = "Meerut"; // Address 1
            data[16] = "BO"; // Applies To (AP or BO)
            data[17] = "INR";
            data[32] = "C"; // Type of Account Holder (C or I)
            data[33] = "P"; // Type of Account (P or T)
            data[34] = "30"; // Credit Days
            data[38] = "A";

            // Insert new cells
            for (int i = 0; i < data.length; i++) {
                if (data[i] != null && !data[i].isEmpty()) {
                    Element cell = new Element("Cell", defaultNs);
                    cell.setAttribute("StyleID", "s73", ssNs); // Match style of original second row
                    if (i != 0) {
                        cell.setAttribute("Index", String.valueOf(i + 1), ssNs);
                    }
                    Element dataElement = new Element("Data", defaultNs);
                    dataElement.setAttribute("Type", "String", ssNs);
                    dataElement.setText(data[i]);
                    cell.addContent(dataElement);
                    secondRow.addContent(cell);
                }
            }

            // Save the modified file
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            try (FileOutputStream fos = new FileOutputStream(xmlFile)) {
                xmlOutputter.output(document, fos);
            }

            System.out.println("XML file updated successfully: " + xmlFile.getAbsolutePath());

            // Selenium: Upload the modified file
//            driver.switchTo().defaultContent();
//            driver.switchTo().frame("MultiPageiframeBrw");
//            driver.switchTo().frame("iframeFileDialog");
//            ChooseFile.sendKeys(xmlFile.getAbsolutePath());
//            Utilities.Click(driver, UploadData);
//            wait.until(ExpectedConditions.alertIsPresent()).accept();
//
//            driver.switchTo().defaultContent();
//            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MultiPageiframeBrw"));
//            Utilities.Click(Save);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to modify XML file", e);
        }
        return null;
    }

    // Custom method to get the latest modified XML file
    private static File getLastModified(String directoryPath) {
        File dir = new File(directoryPath);
        // Filter for XML files
        File[] xmlFiles = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".xml"));
        if (xmlFiles == null || xmlFiles.length == 0) {
            System.out.println("No XML files found in directory: " + directoryPath);
            return null;
        }

        // Find the most recently modified file
        File lastModified = xmlFiles[0];
        for (File file : xmlFiles) {
            if (file.lastModified() > lastModified.lastModified()) {
                lastModified = file;
            }
        }
        return lastModified;
    }
}
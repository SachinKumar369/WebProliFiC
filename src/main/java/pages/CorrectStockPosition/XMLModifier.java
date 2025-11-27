package pages.CorrectStockPosition;

import baselibrary.BaseTest;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utils.Utilities;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class XMLModifier {

    // Define currentDir and DOWNLOAD_PATH in the same format as testDataExcelPath
    public static final String currentDir = System.getProperty("user.dir");
    public static final String DOWNLOAD_PATH = currentDir + File.separator + "DownloadPath";
    public static final String UPLOAD_PATH = currentDir + File.separator + "UploadedFiles";



    public void modifyLatestXMLQtyInStockUnit() {
        try {
            // Find the latest XML file in DownloadPath
            File downloadDir = new File(DOWNLOAD_PATH);
            if (!downloadDir.exists() || !downloadDir.isDirectory()) {
                System.out.println("Download directory not found: " + DOWNLOAD_PATH);
                return;
            }

            File[] xmlFiles = downloadDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".xml"));
            if (xmlFiles == null || xmlFiles.length == 0) {
                System.out.println("No XML files found in: " + DOWNLOAD_PATH);
                return;
            }

            // Get the latest file based on last modified time
            File latestFile = Arrays.stream(xmlFiles)
                    .max(Comparator.comparingLong(File::lastModified))
                    .orElse(null);
            if (latestFile == null) {
                System.out.println("Could not determine the latest XML file.");
                return;
            }

            String xmlFilePath = latestFile.getAbsolutePath();
            System.out.println("Processing latest XML file: " + xmlFilePath);

            // Initialize XML parser
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setNamespaceAware(true); // Enable namespace awareness for ss: prefix
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(latestFile);
            doc.getDocumentElement().normalize();

            // Verify root element
            if (!"Workbook".equals(doc.getDocumentElement().getNodeName())) {
                System.out.println("Root element is not 'Workbook'. Found: " + doc.getDocumentElement().getNodeName());
                return;
            }

            // Find the Worksheet and Table
            NodeList worksheetList = doc.getElementsByTagName("Worksheet");
            if (worksheetList.getLength() == 0) {
                System.out.println("No Worksheet found in XML.");
                return;
            }

            Element worksheet = (Element) worksheetList.item(0);
            NodeList tableList = worksheet.getElementsByTagName("Table");
            if (tableList.getLength() == 0) {
                System.out.println("No Table found in Worksheet.");
                return;
            }

            Element table = (Element) tableList.item(0);
            NodeList rowList = table.getElementsByTagName("Row");
            if (rowList.getLength() < 2) {
                System.out.println("Not enough rows found in Table.");
                return;
            }

            // Find the column index for "Qty In Stock Unit" in the header row
            Element headerRow = (Element) rowList.item(0);
            NodeList headerCells = headerRow.getElementsByTagName("Cell");
            int qtyColumnIndex = -1;
            for (int i = 0; i < headerCells.getLength(); i++) {
                Element cell = (Element) headerCells.item(i);
                NodeList dataNodes = cell.getElementsByTagName("Data");
                if (dataNodes.getLength() > 0) {
                    String cellValue = dataNodes.item(0).getTextContent().trim();
                    if ("Qty In Stock Unit".equalsIgnoreCase(cellValue)) {
                        qtyColumnIndex = i;
                        break;
                    }
                }
            }

            if (qtyColumnIndex == -1) {
                System.out.println("Column 'Qty In Stock Unit' not found in header row. Header cells:");
                for (int i = 0; i < headerCells.getLength(); i++) {
                    Element cell = (Element) headerCells.item(i);
                    NodeList dataNodes = cell.getElementsByTagName("Data");
                    if (dataNodes.getLength() > 0) {
                        System.out.println(" - Cell " + i + ": " + dataNodes.item(0).getTextContent());
                    }
                }
                return;
            }

            int randomNumber = ThreadLocalRandom.current().nextInt(10, 101); // 101 is exclusive

            // Update the first 10 data rows (rows 1 to 10, assuming row 0 is header)
            int rowsToUpdate = Math.min(10, rowList.getLength() - 1);
            System.out.println("Found " + (rowList.getLength() - 1) + " data rows, updating " + rowsToUpdate);
            for (int i = 1; i <= rowsToUpdate; i++) {
                Element dataRow = (Element) rowList.item(i);
                NodeList dataCells = dataRow.getElementsByTagName("Cell");

                // Ensure the cell at qtyColumnIndex exists
                if (qtyColumnIndex < dataCells.getLength()) {
                    Element cell = (Element) dataCells.item(qtyColumnIndex);
                    NodeList dataNodes = cell.getElementsByTagName("Data");
                    if (dataNodes.getLength() > 0) {
                        Element data = (Element) dataNodes.item(0);
                        data.setAttribute("ss:Type", "Number");
                        data.setTextContent(String.valueOf(randomNumber));
                    } else {
                        // Create a new Data element
                        Element newData = doc.createElement("Data");
                        newData.setAttribute("ss:Type", "Number");
                        newData.setTextContent(String.valueOf(randomNumber));
                        cell.appendChild(newData);
                    }
                } else {
                    // Create a new Cell
                    Element newCell = doc.createElement("Cell");
                    Element newData = doc.createElement("Data");
                    newData.setAttribute("ss:Type", "Number");
                    newData.setTextContent(String.valueOf(randomNumber));
                    newCell.appendChild(newData);
                    dataRow.appendChild(newCell);
                }
            }

            // Save the modified XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(latestFile);
            transformer.transform(source, result);

            System.out.println("XML file updated successfully: " + xmlFilePath);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error updating XML file: " + e.getMessage());
        }
    }


    private void uploadModifiedFile(File modifiedFile) {
        try {
            // Ensure UploadPath exists
            File uploadDir = new File(UPLOAD_PATH);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
                System.out.println("Created upload directory: " + UPLOAD_PATH);
            }

            // Create a timestamped filename to avoid conflicts
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String originalFileName = modifiedFile.getName();
            String uploadedFileName = originalFileName.replace(".xml", "_" + timestamp + ".xml");
            File uploadedFile = new File(UPLOAD_PATH + File.separator + uploadedFileName);

            // Copy the modified file to UploadPath
            Files.copy(modifiedFile.toPath(), uploadedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Uploaded modified file to: " + uploadedFile.getAbsolutePath());

            // Placeholder for custom upload logic (e.g., FTP, HTTP POST)
            // Uncomment and customize as needed
            /*
            // Example: Upload via HTTP POST
            String uploadUrl = "http://your-server/upload";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uploadUrl))
                    .header("Content-Type", "multipart/form-data")
                    .POST(HttpRequest.BodyPublishers.ofFile(modifiedFile.toPath()))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Upload response: " + response.body());
            */

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error uploading file: " + e.getMessage());
        }
    }

}
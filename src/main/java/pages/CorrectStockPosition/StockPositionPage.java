package pages.CorrectStockPosition;

import baselibrary.BaseTest;
import com.aventstack.extentreports.Status;
import datahandlers.ExcelHandler;
import extentreports.ExtentTestManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utils.DynamicWait;
import utils.Utilities;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;


public class StockPositionPage {

    public StockPositionPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@id='LinkProduct2']")
    private WebElement Inventory;
    @FindBy(id = "divmodule_2")
    private WebElement InventoryModule;
    @FindBy(id = "cphBrowserHeader_btnAddNew")
    private WebElement AddButton;
    @FindBy(xpath = "//*[@title='INV26 Correct Stock Position']")
    private WebElement CorrectStockPosition;
    @FindBy(id = "cphBody_txtStoreType")
    private WebElement StoreType;
    @FindBy(id = "cphBody_txtStore")
    private WebElement Store;
    @FindBy(id = "cphBody_txtUserDept")
    private WebElement Department;
    @FindBy(id = "cphBody_btnSearch")
    private WebElement Search;

    @FindBy(xpath = "//*[starts-with(@id, 'qty_in_stock_unit_7_')]")
    private List<WebElement> qtyInputs;
    @FindBy(id = "cphBody_btnDownLoadTemplate")
    private WebElement DownloadTemplate;
    @FindBy(id = "cphBody_btnUploadFromExcel")
    private WebElement UploadExcel;
    @FindBy(xpath = "//*[@id=\"FileDlg\"]")
    private WebElement ChooseFile;

    public static final String currentDir = System.getProperty("user.dir");
    public static final String DOWNLOAD_PATH = currentDir + File.separator + "DownloadPath";

    @FindBy(id = "btnUpLoadData")
    private WebElement UploadData;
    @FindBy(id = "btnOK")
    private WebElement OKBtn;
    @FindBy(id = "lblErrorDesc")
    private WebElement ConfDlg;
    @FindBy(id = "cphDialogHeader_btnSave")
    private WebElement Save;
    @FindBy(id = "cphDialogHeader_btnSaveNew")
    private WebElement PostAdjustment;

    public void UploadXML(){
        try {
            Utilities.logerror("Upload XML",logMessages -> {
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                Utilities.Click(UploadExcel);
                BaseTest.getDriver().switchTo().frame("iframeFileDialog");
                File latestfile = getLatestFile(DOWNLOAD_PATH);
                Utilities.SendKeys(ChooseFile, String.valueOf(latestfile));
                Utilities.Click(UploadData);

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                if (ConfDlg.getText().contains("[238] - Details Created/Updated")){
                    ExtentTestManager.createAssertTestStepWithScreenshot("File Upload",Status.PASS,"File Uploaded Sucessfully",true);
                }
                Utilities.Click(OKBtn);

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                Utilities.Click(PostAdjustment);
                BaseTest.getDriver().switchTo().alert().accept();

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("iframeGridDialog");
                if(ConfDlg.getText().contains("[1147] - Adjustment Transactions Posting Completed.")){
                    ExtentTestManager.createAssertTestStepWithScreenshot("Correct Stock Position",Status.PASS,"Correct Stock Position Passed",true);
                }


            });
        } catch (Exception e) {
            ExtentTestManager.createAssertTestStepWithScreenshot("Correct Stock Position",Status.FAIL,"Correct Stock Position Passed",true);
        }
    }


    public void CorrectStockPosition() {
        try {
            Utilities.logerror("Correct Stock Position", logMessages -> {

                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

                // Wait for Inventory element and click it
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Inventory);
                Utilities.Click(BaseTest.getDriver(), Inventory);

                //Inventory Module Select
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
                Utilities.Click(BaseTest.getDriver(), InventoryModule);


                Utilities.Click(CorrectStockPosition);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                Utilities.SendKeys(StoreType,"GS");
                Utilities.SendKeys(Store,"89");
                Utilities.SendKeys(Department,"1134");
                Utilities.Click(Search);

                Actions actions = new Actions(BaseTest.getDriver());
                for (WebElement input : qtyInputs) {

                    Utilities.Click(input);
                    actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("300").perform();

                }



            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void CorrectStockPositionExcel(){
        try {
            Utilities.logerror("Correct Stock Position Excel",logMessages -> {
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");

                // Wait for Inventory element and click it
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), Inventory);
                Utilities.Click(BaseTest.getDriver(), Inventory);

                //Inventory Module Select
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                Utilities.WaitTillElementDisplayed(BaseTest.getDriver(), InventoryModule);
                Utilities.Click(BaseTest.getDriver(), InventoryModule);


                Utilities.Click(CorrectStockPosition);
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                BaseTest.getDriver().switchTo().defaultContent();
                BaseTest.getDriver().switchTo().frame("MultiPageiframeBrw");
                Utilities.SendKeys(StoreType,"GS");
                Utilities.SendKeys(Store,"89");
                Utilities.SendKeys(Department,"1134");
                Utilities.Click(DownloadTemplate);
                DynamicWait.longWait();
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void modifyXMLQtyInStockUnit() {
        // Define the path to the XML file
        String xmlFilePath = "E:\\Automation Project\\WebProLiFiC 3.O\\DownloadPath\\INV26_SUP_T0419.xml";

        try {
            // Initialize XML parser
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setNamespaceAware(true); // Enable namespace awareness for ss: prefix
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File(xmlFilePath));
            doc.getDocumentElement().normalize();

            // Find the Worksheet and Table
            NodeList worksheetList = doc.getElementsByTagName("Worksheet");
            if (worksheetList.getLength() == 0) {
                System.out.println("No Worksheet found in XML. Please verify the structure.");
                return;
            }

            Element worksheet = (Element) worksheetList.item(0);
            NodeList tableList = worksheet.getElementsByTagName("Table");
            if (tableList.getLength() == 0) {
                System.out.println("No Table found in Worksheet. Please verify the structure.");
                return;
            }

            Element table = (Element) tableList.item(0);
            NodeList rowList = table.getElementsByTagName("Row");

            if (rowList.getLength() < 2) {
                System.out.println("Not enough rows found in Table. Please verify the structure.");
                return;
            }

            // Find the column index for "Qty In Stock Unit" in the header row
            Element headerRow = (Element) rowList.item(0); // First row is the header
            NodeList headerCells = headerRow.getElementsByTagName("Cell");
            int qtyColumnIndex = -1;
            for (int i = 0; i < headerCells.getLength(); i++) {
                Element cell = (Element) headerCells.item(i);
                NodeList dataNodes = cell.getElementsByTagName("Data");
                if (dataNodes.getLength() > 0) {
                    String cellValue = dataNodes.item(0).getTextContent();
                    if ("Qty In Stock Unit".equalsIgnoreCase(cellValue.trim())) {
                        qtyColumnIndex = i;
                        break;
                    }
                }
            }

            if (qtyColumnIndex == -1) {
                System.out.println("Column 'Qty In Stock Unit' not found in header row.");
                return;
            }

            // Update the first 10 data rows (rows 1 to 10, assuming row 0 is header)
            int rowsToUpdate = Math.min(10, rowList.getLength() - 1); // Exclude header row
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
                        data.setAttribute("ss:Type", "Number"); // Ensure data type is Number
                        data.setTextContent("100");
                    } else {
                        // Create a new Data element if it doesn't exist
                        Element newData = doc.createElement("Data");
                        newData.setAttribute("ss:Type", "Number");
                        newData.setTextContent("100");
                        cell.appendChild(newData);
                    }
                } else {
                    // Create a new Cell if it doesn't exist
                    Element newCell = doc.createElement("Cell");
                    Element newData = doc.createElement("Data");
                    newData.setAttribute("ss:Type", "Number");
                    newData.setTextContent("100");
                    newCell.appendChild(newData);
                    dataRow.appendChild(newCell);
                }
            }

            // Save the modified XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(xmlFilePath));
            transformer.transform(source, result);

            System.out.println("XML file updated successfully: " + xmlFilePath);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error updating XML file: " + e.getMessage());
        }
    }


    private File getLatestFile(String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".xml"));
        if (files == null || files.length == 0) return null;

        File lastModified = files[0];
        for (File f : files) {
            if (f.lastModified() > lastModified.lastModified()) {
                lastModified = f;
            }
        }
        return lastModified;
    }



}



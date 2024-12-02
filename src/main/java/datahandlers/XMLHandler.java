package datahandlers;

import com.aventstack.extentreports.Status;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.text.WordUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import baselibrary.BaseTest;
import extentreports.ExtentTestManager;
import logs.Log;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class XMLHandler {
    public static String filePath = "";
    private static File xmlFile;
    private static DocumentBuilderFactory dbFactory;
    private static DocumentBuilder builder;
    private static Document doc;
    private static Map<String, String> NodeValueMap = new HashMap<>();

    public XMLHandler(String FilePath) {
        try {
            filePath = FilePath;
            xmlFile = new File(filePath);
            Boolean kk = xmlFile.exists();
            dbFactory = DocumentBuilderFactory.newInstance();
            builder = dbFactory.newDocumentBuilder();
            doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            Log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
    }

    public synchronized static String readTestNGXMLData() {
        String returnText = "";
        try {
            NodeList nodeList;

            if (doc.getElementsByTagName("package").getLength() == 1)
                nodeList = doc.getElementsByTagName("package");
            else
                nodeList = doc.getElementsByTagName("class");

            if (nodeList.getLength() == 1) {
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        String attributeVal = node.getAttributes().getNamedItem("name").getNodeValue();
                        returnText = attributeVal.split("\\.")[attributeVal.split("\\.").length - 1];
                    }
                }
            }
        } catch (Exception e) {
            Log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return returnText;
    }

    public synchronized static Map getNodevalues(String Tagname, String Node) {
        try {
            filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "testdata.xml";
            xmlFile = new File(filePath);
            dbFactory = DocumentBuilderFactory.newInstance();
            builder = dbFactory.newDocumentBuilder();
            doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName(Tagname);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    String attributeVal = node.getAttributes().getNamedItem("name").getNodeValue();
                    if (attributeVal.equalsIgnoreCase(Node)) {
                        Element elem = (Element) node;
                        NodeList kk = elem.getChildNodes();
                        for (int j = 0; j < elem.getChildNodes().getLength(); j++) {
                            if (!kk.item(j).getTextContent().trim().isEmpty()) {
                                String Key = kk.item(j).getChildNodes().toString().split(":")[0].replace("[", "");
                                NodeValueMap.put(Key, kk.item(j).getTextContent());
                            }
                        }
                    }
                }
            }
            return NodeValueMap;
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }


    public synchronized static String readXMLData(String nodeVal, String fieldName) {
        String returnText = "";
        try {
            filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData" + File.separator + "testdata.xml";
            xmlFile = new File(filePath);
            dbFactory = DocumentBuilderFactory.newInstance();
            builder = dbFactory.newDocumentBuilder();
            doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("testcase");
            boolean tcfound = false;
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    String attributeVal = node.getAttributes().getNamedItem("name").getNodeValue();
                    if (nodeVal.equalsIgnoreCase("_Env")) {
                        if (attributeVal.equalsIgnoreCase(WordUtils.capitalize(BaseTest.environment) + nodeVal)) {
                            tcfound = true;
                            Element elem = (Element) node;
                            if (elem.getElementsByTagName(fieldName).getLength() <= 0) {
                                String failText = "readXMLData - Could not find field '" + fieldName + "' in the test data of test case '" + nodeVal + "'. Returning empty text";
                                System.out.println(failText);
                                ExtentTestManager.createAssertTestStepWithScreenshot("readXMLData", Status.INFO, failText, true);
                                returnText = "";
                                break;
                            } else {
                                returnText = elem.getElementsByTagName(fieldName).item(0).getTextContent();
                                break;
                            }
                        }
                    } else {
                        if (attributeVal.equalsIgnoreCase(nodeVal)) {
                            tcfound = true;
                            Element elem = (Element) node;
                            if (elem.getElementsByTagName(fieldName).getLength() <= 0) {
                                String failText = "readXMLData - Could not find field '" + fieldName + "' in the test data of test case '" + nodeVal + "'. Returning empty text";
                                System.out.println(failText);
                                ExtentTestManager.createAssertTestStepWithScreenshot("readXMLData", Status.INFO, failText, false);
                                returnText = "";
                                break;
                            } else {
                                returnText = elem.getElementsByTagName(fieldName).item(0).getTextContent();
                                break;
                            }
                        }
                    }
                }
            }
            if (!tcfound) {
                String failText = "readXMLData - Could not find test case '" + nodeVal + "' in the test data. Returning empty text";
                System.out.println(failText);
                ExtentTestManager.createAssertTestStepWithScreenshot("readXMLData", Status.INFO, failText, false);
                returnText = "";
            }

        } catch (Exception e) {
            Log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return returnText;
    }

    public static synchronized void writeXMLData(String nodeVal, String fieldName, String val) {
        try {
            File xmlFile = new File(filePath);
            xmlFile.canWrite();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbFactory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("testcase");
            boolean tcfound = false;
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    String attributeVal = node.getAttributes().getNamedItem("name").getNodeValue();
                    if (attributeVal.equalsIgnoreCase(nodeVal)) {
                        tcfound = true;
                        Element elem = (Element) node;
                        if (elem.getElementsByTagName(fieldName).getLength() <= 0) {
                            String failText = "writeXMLData - Could not find empty tags with name '" + fieldName + "' in the test data for the test case '" + nodeVal + "'";
                            System.out.println(failText);
                            ExtentTestManager.createAssertTestStepWithScreenshot("writeXMLData", Status.INFO, failText, true);
                            break;
                        } else {
                            elem.getElementsByTagName(fieldName).item(0).setTextContent(val);
                            TransformerFactory factory = TransformerFactory.newInstance();
                            Transformer transformer = factory.newTransformer();
                            DOMSource domSource = new DOMSource(doc);
                            StreamResult streamResult = new StreamResult(xmlFile);
                            transformer.transform(domSource, streamResult);
                            break;
                        }
                    }
                }
            }
            if (!tcfound) {
                String failText = "writeXMLData - Could not find test case '" + nodeVal + "' in the test data";
                System.out.println(failText);
                ExtentTestManager.createAssertTestStepWithScreenshot("writeXMLData", Status.INFO, failText, true);
            }
        } catch (Exception e) {
            Log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
    }
    public synchronized static void updateCellsWithRandomValues(String file) {
        try {
            File xmlFile = new File(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbFactory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Find all <Cell ss:StyleID="s71">
            NodeList cellNodes = doc.getElementsByTagName("Cell");

            // Generate 6 unique random values between 10 and 20
            Set<Integer> randomValues = generateUniqueRandomValues(6, 10, 20);
            Integer[] uniqueRandomValues = randomValues.toArray(new Integer[0]);
            int randomIndex = 0;

            for (int i = 0; i < cellNodes.getLength(); i++) {
                Node cellNode = cellNodes.item(i);
                if (cellNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element cellElement = (Element) cellNode;
                    // Check for attribute ss:StyleID="s71"
                    if ("s71".equals(cellElement.getAttribute("ss:StyleID"))) {
                        NodeList dataNodes = cellElement.getElementsByTagName("Data");

                        for (int j = 0; j < dataNodes.getLength(); j++) {
                            Element dataElement = (Element) dataNodes.item(j);
                            if ("Number".equals(dataElement.getAttribute("ss:Type")) && randomIndex < uniqueRandomValues.length) {
                                // Update with random value
                                dataElement.setTextContent(String.valueOf(uniqueRandomValues[randomIndex++]));
                            }
                        }
                        // Stop if all six nodes have been updated
                        if (randomIndex >= uniqueRandomValues.length) break;
                    }
                }
            }

            // Save the changes to the XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);
            System.out.println("XML updated successfully with random values.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Set<Integer> generateUniqueRandomValues(int count, int min, int max) {
        Set<Integer> values = new HashSet<>();
        Random random = new Random();
        while (values.size() < count) {
            int randomValue = random.nextInt(max - min + 1) + min;
            values.add(randomValue);
        }
        return values;
    }
}

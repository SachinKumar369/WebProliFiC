package datahandlers;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.testng.annotations.Test;
import logs.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class ExcelHandler {
    public static final String currentDir = System.getProperty("user.dir");

    //Location of Test data excel file
    public static String testDataExcelPath = currentDir + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData";
    public static String testDataExcelFileName = "TestData.xlsx";
    private static XSSFWorkbook excelWBook = null; //Excel WorkBook
    private static XSSFSheet excelWSheet = null;//Excel Sheet
    private static XSSFCell cell = null;//Excel cell
    private static XSSFRow row = null;//Excel row
    public static int rowNumber; //Row Number
    public static int columnNumber;//Column Number
    private static FormulaEvaluator formulaEvaluator;

    @Test
    public void ExcelTest() {
        int rowNum = getRowNum("TC02");
        int colNum = getColumnNum("Password");
        System.out.println(rowNum);
        System.out.println(colNum);
        System.out.println(getCellData("TC02", "Username"));
        System.out.println(getCellData("TC02", "Password"));
        setData("TC02", "Username", "Username");
        setData("TC02", "Password", "Password");
        System.out.println(getCellData("TC02", "Username"));
        System.out.println(getCellData("TC02", "Password"));
    }

    /**
     * @Test_Method_Description : To Load Excel File
     * @Method_Name : loadExcelFile
     * @Input_Parameters : String
     * @Output_Parameters : Na
     **/
    public static synchronized void loadExcelFile(String FilePath, int SheetNumber) {

        try (FileInputStream ExcelFile = new FileInputStream(FilePath)) {
            excelWBook = new XSSFWorkbook(ExcelFile);
            XSSFFormulaEvaluator.evaluateAllFormulaCells(excelWBook);
            formulaEvaluator = excelWBook.getCreationHelper().createFormulaEvaluator();
            formulaEvaluator.evaluateAll();
            excelWSheet = excelWBook.getSheetAt(SheetNumber);
        } catch (IOException e) {
            Log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
    }

    /**
     * @Test_Method_Description : To get Row Count
     * @Method_Name : getRowCount
     * @Input_Parameters : Na
     * @Output_Parameters : Na
     **/
    public static synchronized int getRowCount() {
        int rowCount = excelWSheet.getLastRowNum() + 1;
        return rowCount;
    }

    /**
     * @Test_Method_Description : To get Coloumn Count
     * @Method_Name : getColumnCount
     * @Input_Parameters : Na
     * @Output_Parameters : Na
     **/
    public static int getColumnCount() {
        int colCount = excelWSheet.getRow(0).getLastCellNum() + 1;
        return colCount;
    }

    public static synchronized int getRowNum(String rowIdentifier) {
        int rowNum = 0;
        int i = 0;
        try {
            int rowCount = getRowCount();
            boolean foundFlag = false;
            for (i = 0; i < rowCount; i++) {
                cell = excelWSheet.getRow(i).getCell(0);
                DataFormatter formatter = new DataFormatter();
                String cellValue = formatter.formatCellValue(cell);
                if (cellValue.trim().equalsIgnoreCase(rowIdentifier.trim())) {
                    foundFlag = true;
                    break;
                }
            }
            if (!foundFlag) {
                rowNum = -1;
            } else {
                rowNum = i;
            }
        } catch (Exception e) {
            Log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return rowNum;
    }

    public static synchronized int getColumnNum(String colIdentifier) {
        int colNum = 0;
        int i = 0;
        try {
            int colCount = getColumnCount();
            boolean foundFlag = false;
            for (i = 0; i < colCount; i++) {
                cell = excelWSheet.getRow(0).getCell(i);
                DataFormatter formatter = new DataFormatter();
                String cellValue = formatter.formatCellValue(cell);
                if (cellValue.trim().equalsIgnoreCase(colIdentifier.trim())) {
                    foundFlag = true;
                    break;
                }
            }
            if (!foundFlag) {
                colNum = -1;
            } else {
                colNum = i;
            }
        } catch (Exception e) {
            Log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return colNum;
    }

    public static synchronized String getColumnIdentifier(int i) {
        String cellValue = "";
        try {
            cell = excelWSheet.getRow(0).getCell(i);
            DataFormatter formatter = new DataFormatter();
            cellValue = formatter.formatCellValue(cell);
        } catch (Exception e) {
            Log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return cellValue;
    }

    public static synchronized String getCellData(String rowIdentifier, String colIdentifier) {
        String cellValue = "";
        if (excelWBook != null) {
            try {
                int rowNum = getRowNum(rowIdentifier);
                int colNum = getColumnNum(colIdentifier);
                if (rowNum < 0) {
                    System.out.println("The Row Identifier is either invalid or not found");
                } else if (colNum < 0) {
                    System.out.println("The Column Identifier is either invalid or not found");
                } else {
                    XSSFFormulaEvaluator.evaluateAllFormulaCells(excelWBook);
                    cell = excelWSheet.getRow(rowNum).getCell(colNum);
                    //		        CellValue c=formulaEvaluator.evaluate(cell);
                    //		        if(c!=null)
                    //		        	cellValue=c.getStringValue();
                    DataFormatter formatter = new DataFormatter();
                    cellValue = formatter.formatCellValue(cell, formulaEvaluator);
                }
            } catch (Exception e) {
                Log.error(ExceptionUtils.getStackTrace(e));
                e.printStackTrace();
            }
        }
        return cellValue;
    }

    /**
     * @Test_Method_Description : To Get Row Data
     * @Method_Name : getAllRowData
     * @Input_Parameters : String
     * @Output_Parameters :  Map
     **/
    public static synchronized Map<String, String> getAllRowData(String rowIdentifier) {
        Map<String, String> allColData = new HashMap<>();
        try {
            int colCount = getColumnCount();
            for (int i = 1; i < colCount; i++) {
                String colIdentifier = getColumnIdentifier(i);
                if (!colIdentifier.isEmpty()) {
                    String cellData = getCellData(rowIdentifier, colIdentifier);
                    if (!cellData.isEmpty()) allColData.put(colIdentifier, cellData);
                }
            }
        } catch (Exception e) {
            Log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return allColData;
    }

    /**
     * @Test_Method_Description : To Get Column Data
     * @Method_Name : getAllColumnData
     * @Input_Parameters : String
     * @Output_Parameters : List
     **/
    public static synchronized List<String> getAllColumnData(String colIdentifier) {
        List<String> allColData = new ArrayList<>();
        try {
            int RowCount = getRowCount();
            int colNumber = getColumnNum(colIdentifier);

            for (int i = 1; i < RowCount; i++) {
                String cellValue = "";

                cell = excelWSheet.getRow(i).getCell(colNumber);
                DataFormatter formatter = new DataFormatter();
                cellValue = formatter.formatCellValue(cell);

                if (!colIdentifier.isEmpty()) {
                    if (!cellValue.isEmpty())
                        allColData.add(cellValue);
                }
            }
        } catch (Exception e) {
            Log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return allColData;
    }

    /**
     * @Test_Method_Description : To Set Column Data
     * @Method_Name : setCellData
     * @Input_Parameters : String,String,String
     * @Output_Parameters : Na
     **/
    public static synchronized void setData(String rowIdentifier, String colIdentifier, String cellValue) {
        try {
            int rowNum = getRowNum(rowIdentifier);
            int colNum = getColumnNum(colIdentifier);
            if (rowNum < 0) {
                System.out.println("The Row Identifier is either invalid or not found");
            } else if (colNum < 0) {
                System.out.println("The Column Identifier is either invalid or not found");
            } else {
                row = excelWSheet.getRow(rowNum);
                cell = row.getCell(colNum);
                if (cell == null) {
                    cell = row.createCell(colNum);
                    cell.setCellValue(cellValue);
                } else {
                    cell.setCellValue(cellValue);
                }
                saveExcelFile();
            }
        } catch (Exception e) {
            Log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
    }

    /**
     * @Test_Method_Description : To Set Column Data
     * @Method_Name : setCellData
     * @Input_Parameters : String,String,String
     * @Output_Parameters : Na
     **/
    public static synchronized void setCellData(String colIdentifier, String cellValue, int rowNum) {
        try {
            int colNum = getColumnNum(colIdentifier);
            if (colNum < 0) {
                System.out.println("The Column Identifier is either invalid or not found");
            } else {
                // Ensure row exists or create it
                Row row = excelWSheet.getRow(rowNum);
                if (row == null) {
                    row = excelWSheet.createRow(rowNum);
                }
                Cell cell = row.getCell(colNum);
                if (cell == null) {
                    cell = row.createCell(colNum);
                }
                cell.setCellValue(cellValue);

                saveExcelFile();  // Save the Excel file after modification
            }
        } catch (Exception e) {
            Log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
    }


    /**
     * @Test_Method_Description : To Save Excel File
     * @Method_Name : saveExcelFile
     * @Input_Parameters : String,String,String
     * @Output_Parameters : Na
     **/
    public static synchronized void saveExcelFile() {
        if (excelWBook != null) {
            try (FileOutputStream fileOut = new FileOutputStream(testDataExcelPath + File.separator + testDataExcelFileName)) {
                excelWBook.write(fileOut);
                fileOut.flush();
            } catch (Exception e) {
                Log.error(ExceptionUtils.getStackTrace(e));
                e.printStackTrace();
            }
        }
    }

    /**
     * @Test_Method_Description : To Save and Close Excel File
     * @Method_Name : saveCloseExcelFile
     * @Input_Parameters : String,String,String
     * @Output_Parameters : Na
     **/
    public static synchronized void saveCloseExcelFile() {

        try (FileOutputStream fileOut = new FileOutputStream(testDataExcelPath + File.separator + testDataExcelFileName)) {
            excelWBook.write(fileOut);
            excelWBook.close();
            fileOut.close();
        } catch (Exception e) {
            Log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
    }

    public static String[] getCredentialsForEnvironment(String env) throws IOException {
        FileInputStream file = new FileInputStream(testDataExcelPath + File.separator + testDataExcelFileName);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet("Sheet1");
        String[] credentials = new String[4]; // To store ChainID, PropertyID, Username, Password

        // Iterate over rows to find the environment match
        for (Row row : sheet) {
            Cell envCell = row.getCell(2); // Assuming "Environment" is column 3
            if (envCell != null && envCell.getStringCellValue().equalsIgnoreCase(env)) {
                credentials[0] = row.getCell(0).getStringCellValue(); // ChainID
                credentials[1] = row.getCell(1).getStringCellValue(); // PropertyID
                credentials[2] = row.getCell(3).getStringCellValue(); // Username
                credentials[3] = row.getCell(4).getStringCellValue(); // Password
                break;
            }
        }
        workbook.close();
        return credentials;
    }

    public static synchronized void setCellData(String colIdentifier, String cellValue) {
        try {
            int colNum = getColumnNum(colIdentifier);
            if (colNum < 0) {
                System.out.println("The Column Identifier is either invalid or not found");
            } else {
                int rowNum = getNextEmptyRow(colNum);  // Find the next empty row in the column
                if (rowNum < 0) {
                    System.out.println("No empty row available in the column: " + colIdentifier);
                    return;
                }
                row = excelWSheet.getRow(rowNum);
                if (row == null) {
                    row = excelWSheet.createRow(rowNum);  // Create new row if it doesn’t exist
                }
                cell = row.createCell(colNum);
                cell.setCellValue(cellValue);

                saveExcelFile(); // Save the Excel file after modification
            }
        } catch (Exception e) {
            Log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
    }

    public static int getNextEmptyRow(int colNum) {
        int lastRowNum = excelWSheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum + 1; i++) { // Start from row 1 (assuming row 0 is the header)
            Row row = excelWSheet.getRow(i);
            if (row == null || row.getCell(colNum) == null || row.getCell(colNum).getCellType() == CellType.BLANK) {
                return i;  // Return the first empty row
            }
        }
        return lastRowNum + 1; // If no empty cell is found, return the next row after the last row
    }

}

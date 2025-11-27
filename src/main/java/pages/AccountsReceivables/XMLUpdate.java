package pages.AccountsReceivables;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import utils.Utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLUpdate {

    private static class CellData {
        final String value;
        final String ssType;

        CellData(String value, String ssType) {
            this.value = value;
            this.ssType = ssType;
        }
    }

    public static void main(String[] args) {
        String filePath = "E:\\Automation Project\\WebProLiFiC 3.O\\DownloadPath\\AR01_ACCOUNT.xml";

        String arAcc = Utilities.generateUniqueCode("PF", 8);

        // --------------------------------------------------------------
        // 1. Define ONLY the columns you want to change (0-based index)
        // --------------------------------------------------------------
        Map<Integer, CellData> updates = new HashMap<>();
        updates.put(0,  new CellData("BANK", "String"));          // Account Group
        updates.put(1,  new CellData(arAcc, "String"));           // AR Account
        updates.put(2,  new CellData("Test Customer Ltd", "String"));
        updates.put(22, new CellData("750000.00", "Number"));     // Credit Limit
        updates.put(23, new CellData("45", "Number"));            // Credit Days
        updates.put(24, new CellData("Y", "String"));             // Reminder Generate

        // --------------------------------------------------------------
        // 2. Parse the XML and update the second row
        // --------------------------------------------------------------
        try {
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(new File(filePath));

            Namespace ss = Namespace.getNamespace("ss",
                    "urn:schemas-microsoft-com:office:spreadsheet");

            Element table = doc.getRootElement()
                    .getChild("Worksheet", null)
                    .getChild("Table", ss);

            List<Element> rows = table.getChildren("Row", ss);
            if (rows.size() < 2) {
                System.out.println("Not enough rows in the table!");
                return;
            }

            /* ---------- UPDATE SECOND ROW (index 1) ---------- */
            Element secondRow = rows.get(1);
            int maxCol = updates.keySet().stream().mapToInt(Integer::intValue).max().orElse(-1);
            while (secondRow.getChildren("Cell", ss).size() <= maxCol) {
                secondRow.addContent(new Element("Cell", ss));
            }

            List<Element> cells = secondRow.getChildren("Cell", ss);
            for (Map.Entry<Integer, CellData> entry : updates.entrySet()) {
                int colIdx = entry.getKey();
                CellData cd = entry.getValue();

                Element cell = cells.get(colIdx);
                Element data = cell.getChild("Data", ss);
                if (data == null) {
                    data = new Element("Data", ss);
                    cell.addContent(data);
                }

                data.setAttribute("Type", cd.ssType, ss);
                data.setText(cd.value);

                if (cell.getAttribute("StyleID", ss) == null) {
                    cell.setAttribute("StyleID", "s78", ss);
                }
            }

            /* ---------- CLEAR NEXT 10 ROWS (indexes 2-11) ---------- */
            int rowsToClear = Math.min(10, rows.size() - 2);   // safety
            for (int i = 0; i < rowsToClear; i++) {
                Element row = rows.get(2 + i);                 // start at index 2
                List<Element> rowCells = row.getChildren("Cell", ss);
                for (Element cell : rowCells) {
                    Element data = cell.getChild("Data", ss);
                    if (data != null) {
                        data.setText("");                     // empty the value
                        // keep the original Type attribute (String/Number)
                    }
                }
            }

            // Optional: keep Excel’s row-count accurate
            table.setAttribute("ExpandedRowCount", String.valueOf(rows.size()));

            // --------------------------------------------------------------
            // 3. Write the file back (pretty printed)
            // --------------------------------------------------------------
            XMLOutputter out = new XMLOutputter();
            out.setFormat(Format.getPrettyFormat());
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                out.output(doc, fos);
            }

            System.out.println("Second row updated & next 10 rows cleared successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
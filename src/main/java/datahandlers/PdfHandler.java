package datahandlers;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.testng.Assert;
import logs.Log;
import java.io.File;
import java.io.FileInputStream;

/**
 * This class contains methods to Read,verify PDF data
 */
public class PdfHandler
{

    /**
     * @Method_Description This method takes pdf file & TextToBeVerified as input & verifies if pdf fil contains the said text
     * @param file
     * @param TextToBeVerified
     * @return PDF data as String
     */
    public synchronized static String VerifyPdfContainsText(File file, String TextToBeVerified)
    {
        String PdfData = null;
        try
        {
            PdfData = "";
            if(file.exists())
            {
                FileInputStream fis = new FileInputStream(file);
                PDFParser parser = new PDFParser(fis);
                parser.parse();
                COSDocument document = parser.getDocument();
                PDDocument pdDoc = new PDDocument(document);
                PDFTextStripper stripper = new PDFTextStripper();
                PdfData = stripper.getText(pdDoc);
                Assert.assertTrue(PdfData.contains(TextToBeVerified),file.getName() + " contains " + TextToBeVerified);
                System.out.println(PdfData);
                return PdfData;
            }
            else
                Log.error("File does not exist at " + file.getAbsolutePath());
        }
        catch(Exception e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return PdfData;
    }


    /**
     * @Method_Description This method accepts file as input & returns pdf as String
     * @param file
     * @return Pdf file as String
     */
    public synchronized static String ReadPdf(File file)
    {
        String PdfData = null;
        try
        {
            PdfData = "";
            if(file.exists())
            {
                FileInputStream fis = new FileInputStream(file);
                PDFParser parser = new PDFParser(fis);
                parser.parse();
                COSDocument document = parser.getDocument();
                PDDocument pdDoc = new PDDocument(document);
                PDFTextStripper stripper = new PDFTextStripper();
                PdfData = stripper.getText(pdDoc);
                return PdfData;
            }
            else
                Log.error("File does not exist at " + file.getAbsolutePath());
        }
        catch(Exception e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return PdfData;
    }

    /**
     * @Method_Description This method accepts filepath as input & returns pdf as String
     * @param FilePath
     * @return Pdf file as String
     */
    public synchronized static String ReadPdf(String FilePath)
    {
        String PdfData = null;
        try
        {
            PdfData = "";
            File file = new File(FilePath);
            if(file.exists())
            {
                FileInputStream fis = new FileInputStream(file);
                PDFParser parser = new PDFParser(fis);
                parser.parse();
                COSDocument document = parser.getDocument();
                PDDocument pdDoc = new PDDocument(document);
                PDFTextStripper stripper = new PDFTextStripper();
                PdfData = stripper.getText(pdDoc);
                System.out.println(PdfData);
                return PdfData;
            }
            else
                Log.error("File does not exist at " + file.getAbsolutePath());
        }
        catch(Exception e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return PdfData;
    }
}

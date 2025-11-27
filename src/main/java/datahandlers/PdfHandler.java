package datahandlers;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.testng.Assert;
import logs.Log;

import java.io.File;
import java.io.IOException;

public class PdfHandler {

    /**
     * Verifies if PDF file contains specified text
     * @param file PDF file to verify
     * @param textToBeVerified Text to search for in PDF
     * @return PDF content as String
     */
    public synchronized static String verifyPdfContainsText(File file, String textToBeVerified) {
        if (!validateFile(file)) return null;

        try (PDDocument doc = Loader.loadPDF(file)) {
            String pdfData = extractPdfText(doc);
            Assert.assertTrue(pdfData.contains(textToBeVerified),
                    file.getName() + " contains '" + textToBeVerified + "'");
            return pdfData;
        } catch (IOException e) {
            handlePdfException(file, e);
            return null;
        }
    }

    /**
     * Reads PDF file content
     * @param file PDF file to read
     * @return PDF content as String
     */
    public synchronized static String ReadPdf(File file) {
        if (!validateFile(file)) return null;

        try (PDDocument doc = Loader.loadPDF(file)) {
            return extractPdfText(doc);
        } catch (IOException e) {
            handlePdfException(file, e);
            return null;
        }
    }

    /**
     * Reads PDF file content from file path
     * @param filePath Path to PDF file
     * @return PDF content as String
     */
    public synchronized static String ReadPdf(String filePath) {
        return ReadPdf(new File(filePath));
    }

    // ==================== PRIVATE HELPER METHODS ====================

    private static String extractPdfText(PDDocument doc) throws IOException {
        PDFTextStripper stripper = new PDFTextStripper();
        stripper.setSortByPosition(true);
        stripper.setLineSeparator("\n");
        return stripper.getText(doc);
    }

    private static boolean validateFile(File file) {
        if (file == null) {
            Log.error("PDF file is null");
            return false;
        }
        if (!file.exists()) {
            Log.error("File does not exist at " + file.getAbsolutePath());
            return false;
        }
        if (!file.getName().toLowerCase().endsWith(".pdf")) {
            Log.error("Not a PDF file: " + file.getAbsolutePath());
            return false;
        }
        return true;
    }

    private static void handlePdfException(File file, Exception e) {
        String errorMsg = "Error processing PDF: " + file.getName() +
                " - " + e.getClass().getSimpleName() +
                ": " + e.getMessage();
        Log.error(errorMsg);
        Log.error(ExceptionUtils.getStackTrace(e));
    }
}








//
//
//
//
//package datahandlers;
//
//import org.apache.commons.lang3.exception.ExceptionUtils;
//import org.apache.pdfbox.Loader;
//import org.apache.pdfbox.cos.COSDocument;
//import org.apache.pdfbox.io.RandomAccessReadBuffer;
//import org.apache.pdfbox.pdfparser.PDFParser;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.text.PDFTextStripper;
//import org.testng.Assert;
//import logs.Log;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//
///**
// * This class contains methods to Read,verify PDF data
// */
//public class PdfHandler
//{
//
//    /**
//     * @Method_Description This method takes pdf file & TextToBeVerified as input & verifies if pdf fil contains the said text
//     * @param file
//     * @param TextToBeVerified
//     * @return PDF data as String
//     */
////    public synchronized static String VerifyPdfContainsText(File file, String TextToBeVerified)    {
////        String PdfData = null;
////
////
////
////
////
////
////
////
////
////
////
////        try
////        {
////            PdfData = "";
////            if(file.exists())
////            {
////                FileInputStream fis = new FileInputStream(file);
////                PDFParser parser = new PDFParser(new RandomAccessReadBuffer(fis));
////                parser.parse();
////                PDDocument doc = Loader.loadPDF(file);
////                PDDocument pdDoc = new PDDocument(document);
////                PDFTextStripper stripper = new PDFTextStripper();
////                PdfData = stripper.getText(fis);
////                Assert.assertTrue(PdfData.contains(TextToBeVerified),file.getName() + " contains " + TextToBeVerified);
////                System.out.println(PdfData);
////                return PdfData;
////            }
////            else
////                Log.error("File does not exist at " + file.getAbsolutePath());
////        }
////        catch(Exception e)
////        {
////            Log.error(ExceptionUtils.getStackTrace(e));
////            e.printStackTrace();
////        }
////        return PdfData;
////    }
//
//
//    public synchronized static String verifyPdfContainsText(File file, String TextToBeVerified) {
//       // if (!validateFile(file)) return null;
//
//        try (PDDocument doc = Loader.loadPDF(file)) {
//            String pdfData = extractPdfText(doc);
//            Assert.assertTrue(pdfData.contains(TextToBeVerified),
//                    file.getName() + " contains '" + TextToBeVerified + "'");
//            return pdfData;
//        } catch ( IOException e) {
//            handlePdfException(file, e);
//            return null;
//        }
//    }
//
//
//    private static String extractPdfText(PDDocument doc) throws IOException {
//        PDFTextStripper stripper = new PDFTextStripper();
//        // Configure text extraction
//        stripper.setSortByPosition(true);
//        stripper.setLineSeparator("\n");
//        return stripper.getText(doc);
//    }
//
//
//
//    /**
//     * @Method_Description This method accepts file as input & returns pdf as String
//     * @param file
//     * @return Pdf file as String
//     */
////    public synchronized static String ReadPdf(File file)
////    {
////        String PdfData = null;
////        try
////        {
////            PdfData = "";
////            if(file.exists())
////            {
////                FileInputStream fis = new FileInputStream(file);
////                PDFParser parser = new PDFParser(fis);
////                parser.parse();
////                COSDocument document = parser.getDocument();
////                PDDocument pdDoc = new PDDocument(document);
////                PDFTextStripper stripper = new PDFTextStripper();
////                PdfData = stripper.getText(pdDoc);
////                return PdfData;
////            }
////            else
////                Log.error("File does not exist at " + file.getAbsolutePath());
////        }
////        catch(Exception e)
////        {
////            Log.error(ExceptionUtils.getStackTrace(e));
////            e.printStackTrace();
////        }
////        return PdfData;
////    }
//
//    public synchronized static String readPdf(File file) {
//       // if (!validateFile(file)) return null;
//
//        try (PDDocument doc = Loader.loadPDF(file)) {
//            return extractPdfText(doc);
//        } catch (IOException e) {
//            handlePdfException(file, e);
//            return null;
//        }
//    }
//
//    /**
//     * @Method_Description This method accepts filepath as input & returns pdf as String
//     * @param FilePath
//     * @return Pdf file as String
//     */
////    public synchronized static String ReadPdf(String FilePath)    {
////        String PdfData = null;
////        try
////        {
////            PdfData = "";
////            File file = new File(FilePath);
////            if(file.exists())
////
////
////
////
////            {
////                FileInputStream fis = new FileInputStream(file);
////                PDFParser parser = new PDFParser(new RandomAccessReadBuffer(fis));
////                parser.parse();
////                COSDocument document = parser.getDocument();
////                PDDocument pdDoc = new PDDocument(document);
////                PDFTextStripper stripper = new PDFTextStripper();
////                PdfData = stripper.getText(pdDoc);
////                System.out.println(PdfData);
////                return PdfData;
////            }
////            else
////                Log.error("File does not exist at " + file.getAbsolutePath());
////        }
////        catch(Exception e)
////        {
////            Log.error(ExceptionUtils.getStackTrace(e));
////            e.printStackTrace();
////        }
////        return PdfData;
////    }
//
//    public synchronized static String ReadPdf(String FilePath) {
//        File file = new File(FilePath);
//        return readPdf(file);
//    }
//
//    public synchronized static String ReadPdf(File file) {
//       // if (!validateFile(file)) return null;
//
//        try (PDDocument doc = Loader.loadPDF(file)) {
//            return extractPdfText(doc);
//        } catch (IOException e) {
//            handlePdfException(file, e);
//            return null;
//        }
//    }
//
//    private static void handlePdfException(File file, Exception e) {
//        String errorMsg = "Error processing PDF: " + file.getName() +
//                " - " + e.getClass().getSimpleName() +
//                ": " + e.getMessage();
//        Log.error(errorMsg);
//        Log.error(ExceptionUtils.getStackTrace(e));
//    }
//}

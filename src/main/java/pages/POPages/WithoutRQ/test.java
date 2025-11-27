package pages.POPages.WithoutRQ;

import datahandlers.PdfHandler;
import utils.GetLatestFile;

import java.io.File;

public class test {

    public static void main(String[] args) {
        String downloadFolderPath = System.getProperty("user.dir") + File.separator + "DownloadPath";

        File latestPdf = GetLatestFile.getLatestFileFromDirectory(downloadFolderPath);
        if (latestPdf != null) {
            System.out.println("Latest PDF found: " + latestPdf.getAbsolutePath());
            String pdfContent = PdfHandler.ReadPdf(latestPdf);
            System.out.println(pdfContent);
        } else {
            System.out.println("No PDF files found in directory.");
        }

    }
}

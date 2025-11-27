package utils;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class GetLatestFile {
    public static File getLatestFileFromDirectory(String directoryPath) {
        File dir = new File(directoryPath);
        if (dir.exists() && dir.isDirectory()) {
            return Arrays.stream(dir.listFiles((d, name) -> name.toLowerCase().endsWith(".pdf")))
                    .max(Comparator.comparingLong(File::lastModified))
                    .orElse(null);
        }
        return null;
    }
}

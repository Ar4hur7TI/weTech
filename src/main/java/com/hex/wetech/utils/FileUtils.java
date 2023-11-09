package com.hex.wetech.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * FileUtils
 *
 * @author Guofeng Lin
 * @since 2023/10/26
 */
public class FileUtils {
    private FileUtils() {
    }

    private static final String BASE_FILE_PATH = "src/main/webapp";
    private static final String EVENT_FILE_DIR = "/event";

    private static final char SEPARATOR = '/';
    private static final String JSP_NAME = "index.jsp";
    private static final String ORIGINAL_FILE = "WEB-INF/jsp" + SEPARATOR + JSP_NAME;

    public static String initEventFile(String user, String eventId) {
        File file = createPathIfNotExist(user, eventId);
        if (file != null) {
            String src = BASE_FILE_PATH + SEPARATOR + ORIGINAL_FILE;
            if (copyFile2Dir(new File(src), file))
                return EVENT_FILE_DIR + SEPARATOR + user + SEPARATOR + eventId + SEPARATOR + JSP_NAME;
        }
        return null;
    }

    private static File createPathIfNotExist(String user, String eventId) {
        File file = new File(calcTmpFilePath(user, eventId));
        if (file.exists())
            return file;
        return file.mkdirs() ? file : null;
    }

    private static String calcTmpFilePath(String user, String eventId) {
        return BASE_FILE_PATH + EVENT_FILE_DIR + SEPARATOR + user + SEPARATOR + eventId;
    }

    private static boolean copyFile2Dir(File srcFile, File destFile) {
        if (!srcFile.exists())
            return false;
        try (FileReader fr = new FileReader(srcFile); FileWriter fw = new FileWriter(new File(destFile, "index.jsp"))) {
            char[] buffer = new char[1024];
            int len;
            while ((len = fr.read(buffer)) != -1) {
                fw.write(buffer, 0, len);
            }
        } catch (IOException e) {
            destFile.delete();
            return false;
        }
        return true;
    }
}

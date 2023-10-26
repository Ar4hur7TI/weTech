package com.hex.wetech.utils;

import java.io.File;

/**
 * FileUtils
 *
 * @author Guofeng Lin
 * @since 2023/10/26
 */
public class FileUtils {
    private FileUtils() {
    }

    private static final String identifier = "EventFiles";
    private static final char separator = '/';

    public static boolean createPathIfNotExist(String user, String eventName) {
        File file = new File(calcTmpFilePath(user, eventName));
        if (file.exists())
            return false;
        return file.mkdirs();
    }

    public static String calcTmpFilePath(String user, String eventName) {
        return identifier + separator + user + separator + eventName;
    }
}

package com.tc.dm.core.util;

import java.io.File;

public class CommonUtil {

    public static boolean isNullOrEmpty(Object object) {
        if(null == object) return true;
        if(object instanceof String && "".equals(object)) return true;
        return false;
    }

    public static String extractFileName(String path) {
        return path.substring(path.lastIndexOf(File.separator) + 1);
    }
}

package com.tc.dm.core.util;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

    public static boolean isNullOrEmpty(Object object) {
        if(null == object) return true;
        if(object instanceof String && "".equals(object)) return true;
        return false;
    }

    public static String extractFileName(String path) {
        return path.substring(path.lastIndexOf(File.separator) + 1);
    }

    public static Date toDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.parse(dateStr);
    }
}

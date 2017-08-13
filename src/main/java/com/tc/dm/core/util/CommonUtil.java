package com.tc.dm.core.util;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class CommonUtil {

    public static boolean isNullOrEmpty(Object object) {
        if(null == object) return true;
        if(object instanceof String && "".equals(object)) return true;
        if(object instanceof Collection) return ((Collection)object).isEmpty();
        return false;
    }

    public static String extractFileName(String path) {
        return null==path?"":path.substring(path.lastIndexOf(File.separator) + 1);
    }

    public static String extractFileExt(String path) {
        if(path.lastIndexOf(".")<0 || path.lastIndexOf(".") == path.length()) return "unknown";
        return path.substring(path.lastIndexOf(".") + 1);
    }

    public static Date toDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.parse(dateStr);
    }
}

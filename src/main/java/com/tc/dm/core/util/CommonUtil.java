package com.tc.dm.core.util;

public class CommonUtil {

    public static boolean isNullOrEmpty(Object object) {
        if(null == object) return true;
        if(object instanceof String && "".equals(object)) return true;
        return false;
    }
}

package com.ligstd.homework.utils;

public class Utils {

    public static String RemoveSpaces(String s) {
        return s.replaceAll("\\s", "");
    }

    public static String PreProcessMinus(String s) {
        String result = s.replaceAll("-", "+-");
        if (result.startsWith("+-")) result = result.substring(1);
        return result;
    }

    public static String PostProcessMinus(String s) {
        return s.replaceAll("\\+-", "-");
    }

    public static String RemoveZeros(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("\\.?0+?$", "");
        }
        return s;
    }
}

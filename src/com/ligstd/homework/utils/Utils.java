package com.ligstd.homework.utils;

/**
 * Created by tt030 on 2016/9/10.
 */
public class Utils {

    public static String RemoveSpaces(String s) {
        return s.replaceAll("\\s", "");
    }

    public static String PreProcessMinus(String s) {
        return s.replaceAll("-", "+-");
    }
}

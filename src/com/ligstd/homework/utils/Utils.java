package com.ligstd.homework.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ligstd.homework.merging.CanMerge;
import com.ligstd.homework.models.SubItem;


/**
 * @author xieaoran
 *         Utility Class
 */
public class Utils extends CanMerge {
    /**
     * Removes spaces from an input string
     *
     * @param s Input string,
     * @return String without spaces,
     */
    public static String RemoveSpaces(final String s) {
        return s.replaceAll("\\s", "");
    }

    /**
     * PreProcessing SubItem strings starting with minus
     *
     * @param s Input SubItem string,
     * @return PreProcessed result,
     */
    public static String PreProcessMinus(String s) {
        String result = s.replaceAll("-", "+-");
        if (result.startsWith("+-")) {
            result = result.substring(1);
        }
        return result;
    }

    /**
     * PostProcessing SubItem strings starting with minus
     *
     * @param s Input SubItem string,
     * @return PostProcessed result,
     */
    public static String PostProcessMinus(String s) {
        return s.replaceAll("\\+-", "-");
    }

    /**
     * Remove Zeros from decimal strings
     *
     * @param s Input decimal string,
     * @return Decimal string without zeros,
     */
    public static String RemoveZeros(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("\\.?0+?$", "");
        }
        return s;
    }

/*
    public static List<SubItem> 
    Multiply(final List<SubItem> expression1, final List<SubItem> expression2) {
        List<SubItem> result = new ArrayList<>();
        for (SubItem subItem1 : expression1) {
            for (SubItem subItem2 : expression2) {
                Double newCoefficient 
                    = subItem1.getCoefficient() * subItem2.getCoefficient();

                Map<String, Double> newVariables = new HashMap<>();
                newVariables.putAll(subItem1.getVariables());

                for (String variableName2 : subItem2.getVariables().keySet()) {
                    Double power2 = subItem2.getVariables().get(variableName2);

                    if (newVariables.containsKey(variableName2)) {
                        Double power1 = newVariables.get(variableName2);
                        newVariables.put(variableName2, power1 + power2);
                    } else {
                        newVariables.put(variableName2, power2);
                    }
                }

                result.add(new SubItem(newCoefficient, newVariables));
            }
        }
        Merge(result);
        return result;
    }
*/

}

package com.ligstd.homework.parsers;

import com.ligstd.homework.utils.Utils;
import com.ligstd.homework.models.SubItem;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tt030 on 2016/9/10.
 */
public class ExpressionParser {
    private static final Pattern subExpressionPattern = Pattern.compile("^(-?\\d*)\\**(.*)$");
    private static final Pattern variablePattern = Pattern.compile("^(\\d*\\.?\\d*)([a-zA-Z]*)((\\^\\d+)?)$");

    private List<SubItem> result;
    private String input;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        getResult().clear();
        this.input = input;
    }

    public List<SubItem> getResult() {
        return result;
    }

    public void setResult(List<SubItem> result) {
        this.result = result;
    }

    public ExpressionParser() {
        setResult(new ArrayList<>());
    }

    public void Parse() {
        input = Utils.PreProcessMinus(Utils.RemoveSpaces(input));
        String[] subItemStrings = input.split("\\+");
        for (String subItemString : subItemStrings) {
            try{
                SubItem subItem = ParseSubItem(subItemString);
                getResult().add(subItem);
            }
            catch (ArithmeticException exception){
                getResult().clear();
                throw exception;
            }

        }
    }

    @Nullable
    private SubItem ParseSubItem(String inputString) {
        Double coefficient;
        Map<String, Double> variablesTemp = null;

        Matcher subExpressionMatcher = subExpressionPattern.matcher(inputString);

        if (!subExpressionMatcher.find()) throw new ArithmeticException();

        String coefficientString = subExpressionMatcher.group(1);
        String subExpression = subExpressionMatcher.group(2);

        if (coefficientString.isEmpty()) {
            coefficient = 1.0;
        } else {
            coefficient = Double.parseDouble(coefficientString);
        }

        if (!subExpression.isEmpty()) {
            variablesTemp = new HashMap<>();

            String[] variables = subExpression.split("\\*");
            for (String variable : variables) {
                Matcher variableMatcher = variablePattern.matcher(variable);

                if (!variableMatcher.find()) throw new ArithmeticException();

                String subCoefficientString = variableMatcher.group(1);
                String variableName = variableMatcher.group(2);
                String powerString = variableMatcher.group(3);


                if (!subCoefficientString.isEmpty()) {
                    Double subCoefficient = Double.parseDouble(subCoefficientString);
                    coefficient *= subCoefficient;
                }

                if (!variableName.isEmpty()) {
                    Double power;
                    if (powerString.isEmpty()) {
                        power = 1.0;
                    } else {
                        power = Double.parseDouble(powerString.substring(1));
                    }

                    Double currentPower = variablesTemp.getOrDefault(variableName, 0.0);
                    variablesTemp.put(variableName, currentPower + power);
                }
            }
        }
        return new SubItem(coefficient, variablesTemp);
    }


}

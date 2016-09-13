package com.ligstd.homework.models;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tt030 on 2016/9/10.
 */
public class SubItem {

    private Double coefficient;
    private Map<String, Double> variables;

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    public Map<String, Double> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Double> variables) {
        this.variables = variables;
    }

    public SubItem(Double coefficient, Map<String, Double> variables) {
        setCoefficient(coefficient);
        setVariables(variables);
    }

}

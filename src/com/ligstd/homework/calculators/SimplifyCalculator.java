package com.ligstd.homework.calculators;

import com.ligstd.homework.models.SubItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xieaoran
 * Calculator Class for simplification
 */
public class SimplifyCalculator extends CalculatorBase {

    /**
     * Entry method for calculating derivation
     */
    @Override
    public final void Calculate() {
        Simplify();
        Merge(getNewExpression());
    }

    /**
     * Simplifies List<SubItem> "expression"
     * Stores the result in List<SubItem> "newExpression"
     */
    private void Simplify() {
        setNewExpression(new ArrayList<>());
        Map<String, Double> expressions = getCommand().getExpressions();
        for (SubItem subItem : getExpression()) {
            Map<String, Double> variables = subItem.getVariables();
            Double newCoefficient = subItem.getCoefficient();
            Map<String, Double> newVariables = null;
            if (null != variables) {
                newVariables = new HashMap<>();
                for (String variableName : variables.keySet()) {
                    Double power = variables.get(variableName);
                    if (expressions.containsKey(variableName)) {
                        Double value = expressions.get(variableName);
                        newCoefficient *= Math.pow(value, power);
                    } else {
                        newVariables.put(variableName, power);
                    }
                }
                if (newVariables.isEmpty()) {
                    newVariables = null;
                }
            }
            getNewExpression().add(new SubItem(newCoefficient, newVariables));
        }
    }
}

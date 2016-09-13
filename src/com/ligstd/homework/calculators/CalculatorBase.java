package com.ligstd.homework.calculators;

import com.ligstd.homework.models.Command;
import com.ligstd.homework.models.SubItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by xieaoran on 9/12/2016.
 */
public abstract class CalculatorBase {
    private List<SubItem> expression;

    private Command command;

    private List<SubItem> newExpression;

    public List<SubItem> getExpression() {
        return expression;
    }

    public void setExpression(List<SubItem> expression) {
        this.expression = expression;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public List<SubItem> getNewExpression() {
        return newExpression;
    }

    public void setNewExpression(List<SubItem> newExpression) {
        this.newExpression = newExpression;
    }

    public CalculatorBase() {
        setNewExpression(new ArrayList<>());
    }

    public abstract void Calculate();

    protected void Merge() {
        Integer expressionSize = getNewExpression().size();
        for (Integer subItemIndex1 = 0; subItemIndex1 < expressionSize; subItemIndex1++) {
            SubItem subItem1 = getNewExpression().get(subItemIndex1);
            for (Integer subItemIndex2 = subItemIndex1 + 1; subItemIndex2 < expressionSize; subItemIndex2++) {
                SubItem subItem2 = getNewExpression().get(subItemIndex2);
                Boolean canMerge = true;
                for (String subItem1VariableName : subItem1.getVariables().keySet()) {
                    if (!subItem2.getVariables().containsKey(subItem1VariableName)) canMerge = false;
                    else {
                        Double power1 = subItem1.getVariables().get(subItem1VariableName);
                        Double power2 = subItem2.getVariables().get(subItem1VariableName);
                        if (!Objects.equals(power1, power2)) canMerge = false;
                    }
                }
                if (canMerge) {
                    subItem1.setCoefficient(subItem1.getCoefficient() + subItem2.getCoefficient());
                    getNewExpression().remove(subItem2);
                    expressionSize--;
                }
            }
        }
    }
}

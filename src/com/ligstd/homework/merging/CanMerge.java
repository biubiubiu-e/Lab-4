package com.ligstd.homework.merging;

import com.ligstd.homework.models.SubItem;

import java.util.List;

public abstract class CanMerge {
    protected void Merge(List<SubItem> expression) {
        Integer expressionSize = expression.size();
        for (Integer subItemIndex1 = 0; subItemIndex1 < expressionSize; subItemIndex1++) {
            SubItem subItem1 = expression.get(subItemIndex1);
            for (Integer subItemIndex2 = subItemIndex1 + 1; subItemIndex2 < expressionSize; subItemIndex2++) {
                SubItem subItem2 = expression.get(subItemIndex2);
                Boolean canMerge = true;
                for (String subItem1VariableName : subItem1.getVariables().keySet()) {
                    if (!subItem2.getVariables().containsKey(subItem1VariableName)) canMerge = false;
                    else {
                        Double power1 = subItem1.getVariables().get(subItem1VariableName);
                        Double power2 = subItem2.getVariables().get(subItem1VariableName);
                        if (!power1.equals(power2)) canMerge = false;
                    }
                }
                if (canMerge) {
                    subItem1.setCoefficient(subItem1.getCoefficient() + subItem2.getCoefficient());
                    expression.remove(subItem2);
                    expressionSize--;
                }
            }
        }
    }
}

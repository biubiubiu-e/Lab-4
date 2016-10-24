package com.ligstd.homework.calculators;

import com.ligstd.homework.merging.CanMerge;
import com.ligstd.homework.models.Command;
import com.ligstd.homework.models.SubItem;

import java.util.ArrayList;
import java.util.List;
/**
 * @author xieaoran
 * Abstact Class defining calculators
 */
public abstract class CalculatorBase extends CanMerge {

    private List<SubItem> expression;

    private Command command;

    private List<SubItem> newExpression;

    public final List<SubItem> getExpression() {
        return expression;
    }

    public final void setExpression(final List<SubItem> expression) {
        this.expression = expression;
    }

    public final Command getCommand() {
        return command;
    }

    public final void setCommand(final Command command) {
        this.command = command;
    }

    public final List<SubItem> getNewExpression() {
        return newExpression;
    }

    public final void setNewExpression(final List<SubItem> newExpression) {
        this.newExpression = newExpression;
    }

    public CalculatorBase() {
        setNewExpression(new ArrayList<>());
    }

    public abstract void Calculate();
}

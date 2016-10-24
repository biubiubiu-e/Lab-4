package com.ligstd.homework.calculators;

import com.ligstd.homework.merging.CanMerge;
import com.ligstd.homework.models.Command;
import com.ligstd.homework.models.SubItem;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author who
 *
 */
public abstract class CalculatorBase extends CanMerge {
    /**
     *
     */
    private List<SubItem> expression;
    /**
     *
     */
    private Command command;
    /**
     *
     */
    private List<SubItem> newExpression;
    /**
     *
     * @return expression
     */
    public final List<SubItem> getExpression() {
        return expression;
    }
    /**
     *
     * @param expression ??
     */
    public final void setExpression(final List<SubItem> expression) {
        this.expression = expression;
    }
    /**
     *
     * @return command
     */
    public final Command getCommand() {
        return command;
    }
    /**
     *
     * @param command ?
     */
    public final void setCommand(final Command command) {
        this.command = command;
    }
    /**
     *
     * @return new Expression
     */
    public final List<SubItem> getNewExpression() {
        return newExpression;
    }
    /**
     *
     * @param newExpression ?
     */
    public final void setNewExpression(final List<SubItem> newExpression) {
        this.newExpression = newExpression;
    }
    /**
     *
     */
    public CalculatorBase() {
        setNewExpression(new ArrayList<>());
    }
    /**
     *
     */
    public abstract void Calculate();
}

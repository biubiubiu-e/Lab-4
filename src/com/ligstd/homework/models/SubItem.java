package com.ligstd.homework.models;

import java.util.Map;
/**
 * @author blackgreymon
 * Class that defines sub items
 */
public class SubItem {
	/**
	 * Coefficient of sub item
	 */
    private Double coefficient;
    /**
     * Variable list of sub item
     */
    private Map<String, Double> variables;

    public final Double getCoefficient() {
        return coefficient;
    }

    public final void setCoefficient(final Double coefficient) {
        this.coefficient = coefficient;
    }

    public final Map<String, Double> getVariables() {
        return variables;
    }

    public final void setVariables(final Map<String, Double> variables) {
        this.variables = variables;
    }

    /**
     * Constructor for sub item
     * @param coefficient Coefficient of sub item.
     * @param variables Variable list of sub item.
     */
    public 
    SubItem(final Double coefficient, final Map<String, Double> variables) {
        setCoefficient(coefficient);
        setVariables(variables);
    }

    /**
     * Destructor for sub item
     * @throws Throwable Undestructed sub item
     */
    @Override
    protected final void finalize() throws Throwable {
        if (null != getVariables()) {
        	getVariables().clear();
        }
        super.finalize();
    }

}

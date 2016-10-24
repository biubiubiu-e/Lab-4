package com.ligstd.homework.models;

import java.util.Map;
/**
 * 
 * @author z
 *
 */
public class SubItem {
	/**
	 * 
	 */
    private Double coefficient;
    /**
     * 
     */
    private Map<String, Double> variables;
    /**
     * 
     * @return ,
     */
    public final Double getCoefficient() {
        return coefficient;
    }
    /**
     * 
     * @param coefficient ,
     */
    public final void setCoefficient(final Double coefficient) {
        this.coefficient = coefficient;
    }
    /**
     * 
     * @return ,
     */
    public final Map<String, Double> getVariables() {
        return variables;
    }
    /**
     * 
     * @param variables
     */
    public final void setVariables(final Map<String, Double> variables) {
        this.variables = variables;
    }
    /**
     * 
     * @param coefficient .
     * @param variables .
     */
    public 
    SubItem(final Double coefficient, final Map<String, Double> variables) {
        setCoefficient(coefficient);
        setVariables(variables);
    }

    @Override
    protected final void finalize() throws Throwable {
        if (null != getVariables()) {
        	getVariables().clear();
        }
        super.finalize();
    }

}

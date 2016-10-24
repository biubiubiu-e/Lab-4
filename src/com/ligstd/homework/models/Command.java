package com.ligstd.homework.models;

import com.ligstd.homework.enums.CommandEnum;

import java.util.Map;
/**
 * 
 * @author z
 *
 */
public class Command {
	/**
	 * 
	 */
    private CommandEnum type;
    /**
     * 
     */
    private Map<String, Double> expressions;
    /**
     * 
     * @return ,
     */
    public final CommandEnum getType() {
        return type;
    }
    /**
     * 
     * @param type
     */
    public final void setType(final CommandEnum type) {
        this.type = type;
    }
    /**
     * 
     * @return ,
     */
    public final Map<String, Double> getExpressions() {
        return expressions;
    }
    /**
     * 
     * @param expressions ,
     */
    public final void setExpressions(final Map<String, Double> expressions) {
        this.expressions = expressions;
    }
    /**
     * 
     * @param type , 
     * @param expressions ,
     */
    public 
        Command(final CommandEnum type, final Map<String, Double> expressions) {
        setType(type);
        setExpressions(expressions);
    }

    @Override
    protected final void finalize() throws Throwable {
        if (null != getExpressions()) {
        	getExpressions().clear();
        }
        super.finalize();
    }
}

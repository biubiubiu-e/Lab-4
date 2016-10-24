package com.ligstd.homework.models;

import com.ligstd.homework.enums.CommandEnum;

import java.util.Map;
/**
 * @author blackgreymon
 * Class that defines commands
 */
public class Command {
	/**
	 * Command Type
	 */
    private CommandEnum type;
    /**
     * Command expressions stored in Key - variable name, Value - power
     */
    private Map<String, Double> expressions;

    public final CommandEnum getType() {
        return type;
    }

    public final void setType(final CommandEnum type) {
        this.type = type;
    }

    public final Map<String, Double> getExpressions() {
        return expressions;
    }

    public final void setExpressions(final Map<String, Double> expressions) {
        this.expressions = expressions;
    }

    /**
     * Constructor for Command
     * @param type Command Type,
     * @param expressions Command expressions,
     */
    public 
        Command(final CommandEnum type, final Map<String, Double> expressions) {
        setType(type);
        setExpressions(expressions);
    }

    /**
     * Destructor for Command
     * @throws Throwable Undestructed Command
     */
    @Override
    protected final void finalize() throws Throwable {
        if (null != getExpressions()) {
        	getExpressions().clear();
        }
        super.finalize();
    }
}

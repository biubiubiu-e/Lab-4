package com.ligstd.homework.parsers;

import com.ligstd.homework.enums.CommandEnum;
import com.ligstd.homework.models.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author xieaoran
 * Parser that parses commands
 */
public class CommandParser {
	/**
	 * Pattern matching commands
	 */
    private static final 
        Pattern commandPattern = Pattern.compile("^!(simplify|d/d)\\s?(.*)$");
    /**
     * Parsing result
     */
    private Command result;
    /**
     * Parser input
     */
    private String input;

    public final String getInput() {
        return input;
    }

    public final void setInput(final String input) {
        setResult(null);
        this.input = input;
    }
    public final Command getResult() {
        return result;
    }

    public final void setResult(final Command result) {
        this.result = result;
    }

    /**
     * Entry function for Parsing
     */
    public final void Parse() {
        setInput(getInput().trim());
        try {
            Command command = ParseCommand(getInput());
            setResult(command);
        } catch (ArithmeticException exception){
            setResult(null);
            throw exception;
        }
    }
    /**
     * Function that does actual parsing
     * @param inputString Parser input,
     * @return Parsing result,
     */
    private Command ParseCommand(final String inputString) {
        Matcher commandMatcher = commandPattern.matcher(inputString);
        CommandEnum type;
        Map<String, Double> expressions = new HashMap<>();

        if (!commandMatcher.find()) {
        	throw new ArithmeticException("Error, Illegal Command Format.");
        }
        String commandString = commandMatcher.group(1);
        String expressionsString = commandMatcher.group(2);

        switch (commandString) {
            case "simplify":
                type = CommandEnum.Simplify;
                break;
            case "d/d":
                type = CommandEnum.Derivation;
                break;
            default:
                throw new ArithmeticException("Error, Command Undefined.");
        }

        if (type == CommandEnum.Simplify) {
            String[] expressionsArray = expressionsString.split("\\s");
            for (String expression : expressionsArray) {
                if(expression.isEmpty()) {
                	continue;
                }
                String[] args = expression.split("=");
                String variableName = args[0];
                String value = args[1];
                expressions.put(variableName, Double.parseDouble(value));
            }
        } else {
            expressions.put(expressionsString, 0.0);
        }
        return new Command(type, expressions);
    }
}

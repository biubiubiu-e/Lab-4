package com.ligstd.homework.parsers;

import com.ligstd.homework.enums.CommandEnum;
import com.ligstd.homework.models.Command;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tt030 on 2016/9/11.
 */
public class CommandParser {
    private static final Pattern commandPattern = Pattern.compile("^!(simplify|d/d)\\s?(.+)$");

    private Command result;
    private String input;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        setResult(null);
        this.input = input;
    }

    public Command getResult() {
        return result;
    }

    public void setResult(Command result) {
        this.result = result;
    }

    public void Parse() {
        try{
            Command command = ParseCommand(getInput());
            setResult(command);
        }
        catch (ArithmeticException exception){
            setResult(null);
            throw exception;
        }
    }

    @Nullable
    private Command ParseCommand(String inputString) {
        Matcher commandMatcher = commandPattern.matcher(inputString);
        CommandEnum type;
        Map<String, Double> expressions = new HashMap<>();

        if (!commandMatcher.find()) throw new ArithmeticException();
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
                throw new ArithmeticException();
        }

        if (type == CommandEnum.Simplify) {
            String[] expressionsArray = expressionsString.split("\\s");
            for (String expression : expressionsArray) {
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

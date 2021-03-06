package com.ligstd.homework.controllers;

import com.ligstd.homework.calculators.DerivationCalculator;
import com.ligstd.homework.calculators.SimplifyCalculator;
import com.ligstd.homework.enums.CommandEnum;
import com.ligstd.homework.models.Command;
import com.ligstd.homework.models.SubItem;
import com.ligstd.homework.parsers.CommandParser;
import com.ligstd.homework.parsers.ExpressionParser;
import com.ligstd.homework.utils.Utils;

import java.io.*;
import java.util.List;
import java.util.Map;
/**
 *
 * @author me
 *
 */
public class MainController {
    /**
     * Parser for commands
     */
    private static final CommandParser
        commandParser = new CommandParser();
    /**
     * Parser for expressions
     */
    private static final ExpressionParser
        expressionParser = new ExpressionParser();
    /**
     * Calculator for simplification
     */
    private static final SimplifyCalculator
        simplifyCalculator = new SimplifyCalculator();
    /**
     * Calculator for derivation
     */
    private static final DerivationCalculator
        derivationCalculator = new DerivationCalculator();
    /**
     * Reader that reads user's input
     */
    private BufferedReader inputReader;
    /**
     * Stream that outputs results
     */
    private PrintStream outputStream;
    /**
     * Currently stored expression
     */
    private List<SubItem> currentExpression;
    /**
     * Currently stored command
     */
    private Command currentCommand;

    public static CommandParser getCommandParser() {
        return commandParser;
    }

    public static ExpressionParser getExpressionParser() {
        return expressionParser;
    }

    public static SimplifyCalculator getSimplifyCalculator() {
        return simplifyCalculator;
    }

    public static DerivationCalculator getDerivationCalculator() {
        return derivationCalculator;
    }

    public final BufferedReader getInputReader() {
        return inputReader;
    }

    public final void setInputReader(BufferedReader inputReader) {
        this.inputReader = inputReader;
    }

    public PrintStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    public List<SubItem> getCurrentExpression() {
        return currentExpression;
    }

    public void setCurrentExpression(List<SubItem> currentExpression) {
        this.currentExpression = currentExpression;
    }

    public Command getCurrentCommand() {
        return currentCommand;
    }

    public void setCurrentCommand(Command currentCommand) {
        this.currentCommand = currentCommand;
    }

    /**
     * Constructor for MainController
     * @param inputStream Stream that accepts user's input
     * @param outputStream Stream that outputs results
     */
    public MainController(InputStream inputStream, PrintStream outputStream) {
        setInputReader(new BufferedReader(new InputStreamReader(inputStream)));
        setOutputStream(outputStream);
    }
    /**
     * Function that acquires user's input
     * @throws IOException Parsing Exception
     */
    public void AcquireInput() throws IOException {
        getOutputStream().print('>');
        String currentInput = getInputReader().readLine();
        if (currentInput != null) {
            ParseInput(currentInput);
        }
    }
    /**
     * Function that parses user's input
     * @param input User's Input
     */
    private void ParseInput(String input) {
        if (input.startsWith("!")) {
            getCommandParser().setInput(input);
            getCommandParser().Parse();
            setCurrentCommand(getCommandParser().getResult());
            Calculate();
        } else {
            getExpressionParser().setInput(input);
            getExpressionParser().Parse();
            if (null != getCurrentExpression()) getCurrentExpression().clear();
            setCurrentExpression(getExpressionParser().getResult());
            Feedback(getCurrentExpression());
        }
    }
    /**
     * Function that do actual calculation
     */
    private void Calculate() {
        if (getCurrentExpression() == null) throw new ArithmeticException("Error, Expression Undefined.");
        if (getCurrentCommand().getType() == CommandEnum.Simplify) {
            getSimplifyCalculator().setCommand(getCurrentCommand());
            getSimplifyCalculator().setExpression(getCurrentExpression());
            getSimplifyCalculator().Calculate();
            Feedback(getSimplifyCalculator().getNewExpression());
            getSimplifyCalculator().getNewExpression().clear();
        } else {
            getDerivationCalculator().setCommand(getCurrentCommand());
            getDerivationCalculator().setExpression(getCurrentExpression());
            getDerivationCalculator().Calculate();
            Feedback(getDerivationCalculator().getNewExpression());
            getDerivationCalculator().getNewExpression().clear();
        }
        System.gc();
    }
    /**
     * Result output
     * @param resultExpression Calculation result
     */
    private void Feedback(List<SubItem> resultExpression) {
        Integer expressionSize = resultExpression.size();
        String[] subItemStrings = new String[expressionSize];
        for (Integer subItemIndex = 0; subItemIndex < expressionSize; subItemIndex++) {
            SubItem currentSubItem = resultExpression.get(subItemIndex);
            Map<String, Double> variables = currentSubItem.getVariables();
            Double coefficient = currentSubItem.getCoefficient();
            if (null == variables) {
                subItemStrings[subItemIndex] = Utils.RemoveZeros(String.format("+%f", currentSubItem.getCoefficient()));
            } else {
                if (coefficient == 1) {
                    subItemStrings[subItemIndex] = "+";
                } else if (coefficient == -1) {
                    subItemStrings[subItemIndex] = "-";
                }
                else{
                    subItemStrings[subItemIndex] = Utils.RemoveZeros(String.format("+%f", currentSubItem.getCoefficient())) + "*";
                }
                Integer variablesSize = variables.size();
                String[] variableStrings = new String[variablesSize];
                Integer currentIndex = 0;
                for (String variableName : variables.keySet()) {
                    Double power = variables.get(variableName);
                    if (power == 1) {
                        variableStrings[currentIndex] = variableName;
                    } else {
                        String powerString = Utils.RemoveZeros(power.toString());
                        variableStrings[currentIndex] = String.format("%s^%s", variableName, powerString);
                    }
                    currentIndex++;
                }
                subItemStrings[subItemIndex] += String.join("*", (CharSequence[]) variableStrings);
            }
        }
        String resultString = Utils.PostProcessMinus(String.join("", (CharSequence[]) subItemStrings));
        if (resultString.startsWith("+*")) resultString = resultString.substring(2);
        else if (resultString.startsWith("+")) resultString = resultString.substring(1);
        outputStream.println(resultString);
    }
}

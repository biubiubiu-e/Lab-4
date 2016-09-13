package com.ligstd.homework.controllers;

import com.ligstd.homework.calculators.SimplifyCalculator;
import com.ligstd.homework.enums.CommandEnum;
import com.ligstd.homework.models.Command;
import com.ligstd.homework.models.SubItem;
import com.ligstd.homework.parsers.CommandParser;
import com.ligstd.homework.parsers.ExpressionParser;

import java.io.*;
import java.util.List;

/**
 * Created by tt030 on 2016/9/12.
 */
public class MainController {
    private static final CommandParser commandParser = new CommandParser();
    private static final ExpressionParser expressionParser = new ExpressionParser();
    private static final SimplifyCalculator simplifyCalculator = new SimplifyCalculator();

    private BufferedReader inputReader;
    private PrintStream outputStream;

    private List<SubItem> currentExpression;
    private Command currentCommand;

    public MainController(InputStream inputStream, PrintStream outputStream) {
        this.inputReader = new BufferedReader(new InputStreamReader(inputStream));
        this.outputStream = outputStream;
    }

    public void AcquireInput() throws IOException {
        outputStream.print('>');
        String currentInput = inputReader.readLine();
        ParseInput(currentInput);
    }

    private void ParseInput(String input) {
        if (input.startsWith("!")) {
            commandParser.setInput(input);
            commandParser.Parse();
            currentCommand = commandParser.getResult();
            Calculate();
        } else {
            expressionParser.setInput(input);
            expressionParser.Parse();
            currentExpression = expressionParser.getResult();
        }
    }

    private void Calculate() {
        if (currentExpression == null) throw new ArithmeticException();
        if (currentCommand.getType() == CommandEnum.Simplify) {
            simplifyCalculator.setCommand(currentCommand);
            simplifyCalculator.setExpression(currentExpression);
            simplifyCalculator.Calculate();
        }
    }
}

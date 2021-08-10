package ru.nsu.sergomyaso.calculator;

import java.util.regex.Pattern;

public class PushCommand implements BaseCommand {
    private CommandContext context;
    static final int INDEX_FIRST_ARG = 0;
    private static final String VALID_PATTERN_VALUE_VARIATION = "^[0-9]*[.]?[0-9]+$";

    public PushCommand(CommandContext context) {
        this.context = context;

    }

    private double getArg() throws CalculatorException {
        if (context.getArgs().size() == 0) {
            throw new CalculatorException(new ArgumentCommandException("Not arguments"));
        }
        var stringArg = context.getArgs().get(INDEX_FIRST_ARG);
        var arg = this.context.getVariations().get(stringArg);
        if (arg != null)
            return arg;
        if (!Pattern.compile(VALID_PATTERN_VALUE_VARIATION).matcher(stringArg).find())
            throw new CalculatorException(new ArgumentCommandException("Argument error"));
        arg = Double.parseDouble(context.getArgs().get(INDEX_FIRST_ARG));
        return arg;

    }

    @Override
    public void execute() throws CalculatorException {
        var stack = this.context.getStack();
        stack.push(this.getArg());
    }
}

package ru.nsu.sergomyaso.calculator;

public class DivCommand implements BaseCommand {
    private CommandContext context;

    public DivCommand(CommandContext context) {
        this.context = context;
    }

    @Override
    public void execute() throws CalculatorException {
        var stack = context.getStack();
        if (stack.size() < 2)
            throw new CalculatorException(new StackEmptyCommandException("Stack empty"));
        var arg1 = (double) stack.pop();
        var arg2 = (double) stack.pop();
        if (arg2 == 0)
            throw new CalculatorException(new ZeroDivisionException("Division by zero"));
        stack.push(arg1 / arg2);
    }
}

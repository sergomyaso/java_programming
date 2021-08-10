package ru.nsu.sergomyaso.calculator;


public class SqrtCommand implements BaseCommand {
    private CommandContext context;

    public SqrtCommand(CommandContext context) {
        this.context = context;
    }

    @Override
    public void execute() throws CalculatorException {
        var stack = this.context.getStack();
        if (stack.size() < 1)
            throw new CalculatorException(new StackEmptyCommandException("Stack empty"));
        var arg = (double) stack.pop();
        if (arg < 0)
            throw new CalculatorException(new ArgumentCommandException(String.format("Argument %f is negative", arg)));
        stack.push(Math.sqrt(arg));
    }
}

package ru.nsu.sergomyaso.calculator;

public class AddCommand implements BaseCommand {

    private CommandContext context;

    public AddCommand(CommandContext context) {
        this.context = context;
    }

    @Override
    public void execute() throws CalculatorException {
        var stack = context.getStack();
        if (stack.size() < 2)
            throw new CalculatorException(new StackEmptyCommandException("Stack empty"));
        stack.push((double) stack.pop() + (double) stack.pop());
    }
}

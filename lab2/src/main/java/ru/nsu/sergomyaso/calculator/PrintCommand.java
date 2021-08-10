package ru.nsu.sergomyaso.calculator;

public class PrintCommand implements BaseCommand {

    private CommandContext context;
    private static final int INDEX_OF_FIRST = 0;

    public PrintCommand(CommandContext context) {
        this.context = context;
    }

    @Override
    public void execute() throws CalculatorException {
        var stack = this.context.getStack();
        if (stack.size() == 0)
            throw new CalculatorException(new StackEmptyCommandException("Stack empty"));
        System.out.println(stack.get(INDEX_OF_FIRST));
    }
}

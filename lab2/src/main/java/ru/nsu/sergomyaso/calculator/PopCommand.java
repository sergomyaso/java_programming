package ru.nsu.sergomyaso.calculator;

import java.util.EmptyStackException;

public class PopCommand implements BaseCommand {

    private CommandContext context;

    public PopCommand(CommandContext context) {
        this.context = context;
    }

    @Override
    public void execute() throws CalculatorException {
        try {
            var stack = context.getStack();
            stack.pop();
        } catch (EmptyStackException exp) {
            throw new CalculatorException(new StackEmptyCommandException("Stack empty"));
        }
    }
}

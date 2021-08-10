package ru.nsu.sergomyaso.calculator;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandParser implements BaseParser {
    private static final String SEPARATOR = " ";
    private static final int MAX_COUNT_ITEMS = 3;
    private static final int TITLE_COMMAND_INDEX = 0;

    public boolean isValidItems(ArrayList<String> items) {
        if (items.size() > MAX_COUNT_ITEMS)
            return false;
        return true;
    }

    @Override
    public ModelDataCommand parseCommand(String command) throws CalculatorException {
        var items = new ArrayList<>(Arrays.asList(command.split(SEPARATOR)));
        if (!isValidItems(items))
            throw new CalculatorException(new ParserCommandException("Invalid syntax"));
        String titleCommand = items.get(TITLE_COMMAND_INDEX);
        items.remove(TITLE_COMMAND_INDEX);
        return new ModelDataCommand(titleCommand, items);
    }
}

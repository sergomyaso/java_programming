package ru.nsu.sergomyaso.calculator;

import java.util.regex.Pattern;

public class DefineCommand implements BaseCommand {

    private CommandContext context;
    private static final int INDEX_TITLE_VARIATION = 0;
    private static final int INDEX_VALUE_VARIATION = 1;
    private static final String VALID_PATTERN_TITLE_VARIATION = "[A-Za-z]+";
    private static final String VALID_PATTERN_VALUE_VARIATION = "^[0-9]*[.]?[0-9]+$";

    public DefineCommand(CommandContext context) {
        this.context = context;
    }

    private boolean isValidArguments() {
        var args = context.getArgs();
        if (args.size() < 2)
            return false;
        if (!Pattern.compile(VALID_PATTERN_TITLE_VARIATION).matcher(args.get(INDEX_TITLE_VARIATION)).find())
            return false;
        if (!Pattern.compile(VALID_PATTERN_VALUE_VARIATION).matcher(args.get(INDEX_VALUE_VARIATION)).find())
            return false;
        return true;
    }

    @Override
    public void execute() throws CalculatorException {
        if (!this.isValidArguments())
            throw new CalculatorException(new ArgumentCommandException("Incorrect arguments"));
        var variations = this.context.getVariations();
        var args = this.context.getArgs();
        variations.put(args.get(INDEX_TITLE_VARIATION),
                Double.parseDouble(args.get(INDEX_VALUE_VARIATION)));
    }
}

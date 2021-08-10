package ru.nsu.sergomyaso.calculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class StackCalculator implements BaseCalculator {
    private static final String PATH_TO_PROPERTY = "../lab2/src/main/resources/property.properties";
    private BaseParser parser;
    private BaseLoader loader;
    private HashMap<String, BaseCommand> commandInstanceMap;
    private CommandContext context;
    private static final Logger logger = LogManager.getLogger(StackCalculator.class);
    private String errno;

    public StackCalculator() {
        this.commandInstanceMap = new HashMap<>();
        this.loader = new CommandLoader();
        this.parser = new CommandParser();
        this.context = new CommandContext();
        this.errno = "";
    }

    private void executeCommands(ArrayList<String> stringCommands) {
        CommandCreator commandCreator = new CommandCreator(this.context, PATH_TO_PROPERTY);
        for (var stringCommand : stringCommands) {
            try {
                var command = this.parser.parseCommand(stringCommand);
                var instanceCommand = this.commandInstanceMap.get(command.getTitle());
                if (instanceCommand == null) {
                    instanceCommand = commandCreator.createCommand(command.getTitle());
                    this.commandInstanceMap.put(command.getTitle(), instanceCommand);
                }
                this.context.getArgs().clear();
                this.context.getArgs().addAll(command.getArgs());
                instanceCommand.execute();
            } catch (CalculatorException exception) {

                this.errno = exception.getMessage();
                logger.error(exception);
            }
        }

    }

    void clearContext() {
        this.context.getArgs().clear();
        this.context.getVariations().clear();
        this.context.getStack().clear();
    }

    @Override
    public String getErrno() {
        return this.errno;
    }

    @Override
    public void calculate(String filePath) throws IOException {
        var commands = this.loader.loadCommands(filePath);
        if (commands.size() == 0)
            return;
        this.executeCommands(commands);
        this.clearContext();
    }

    @Override
    public void calculate() throws IOException {
        var commands = this.loader.loadCommands();
        if (commands.size() == 0)
            return;
        this.executeCommands(commands);
        this.clearContext();
    }
}

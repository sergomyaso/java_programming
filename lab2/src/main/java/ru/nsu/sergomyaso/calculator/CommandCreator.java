package ru.nsu.sergomyaso.calculator;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CommandCreator {
    private static final String SEPARATOR = "=";
    private static final int INDEX_TITTLE_COMMAND = 0;
    private static final int INDEX_PATH_COMMAND = 1;
    private HashMap<String, String> commandPathMap;
    private CommandContext context;
    private String pathToProperty;

    private void generateCommandPathMap() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.pathToProperty))) {
            ArrayList<String> commands = new ArrayList<>();
            String line = null;
            while ((line = reader.readLine()) != null) {
                var items = line.split(SEPARATOR);
                this.commandPathMap.put(items[INDEX_TITTLE_COMMAND], items[INDEX_PATH_COMMAND]);
            }
        } catch (IOException exception) {
            throw exception;
        }
    }

    private void initDefaultCommandPathMap() {
        commandPathMap.put("POP", "ru.nsu.sergomyaso.calculator.PopCommand");
        commandPathMap.put("PUSH", "ru.nsu.sergomyaso.calculator.PushCommand");
        commandPathMap.put("+", "ru.nsu.sergomyaso.calculator.AddCommand");
        commandPathMap.put("-", "ru.nsu.sergomyaso.calculator.SubCommand");
        commandPathMap.put("*", "ru.nsu.sergomyaso.calculator.MulCommand");
        commandPathMap.put("/", "ru.nsu.sergomyaso.calculator.DivCommand");
        commandPathMap.put("DEFINE", "ru.nsu.sergomyaso.calculator.DefineCommand");
        commandPathMap.put("SQRT", "ru.nsu.sergomyaso.calculator.SqrtCommand");
        commandPathMap.put("PRINT", "ru.nsu.sergomyaso.calculator.PrintCommand");

    }

    CommandCreator(CommandContext context, String pathToProperty) {
        this.commandPathMap = new HashMap<>();
        this.context = context;
        this.pathToProperty = pathToProperty;
        try {
            this.generateCommandPathMap();
        } catch (IOException exception) {
            initDefaultCommandPathMap();
        }
    }

    BaseCommand createCommand(String titleCommand) {
        var pathCommand = this.commandPathMap.get(titleCommand);
        if (pathCommand == null)
            return null;
        try {
            Class classCommand = Class.forName(pathCommand);
            var constructor = classCommand.getConstructor(new Class[]{CommandContext.class});
            return (BaseCommand) constructor.newInstance(this.context);
        } catch (ReflectiveOperationException e) {
            return null;
        }
    }
}

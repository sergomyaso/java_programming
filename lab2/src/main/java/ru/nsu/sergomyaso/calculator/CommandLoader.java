package ru.nsu.sergomyaso.calculator;

import java.io.*;
import java.util.ArrayList;

public class CommandLoader implements BaseLoader {

    private ArrayList<String> getData(Reader stream) throws IOException {
        try (BufferedReader reader = new BufferedReader(stream)) {
            ArrayList<String> commands = new ArrayList<>();
            String line = null;
            while ((line = reader.readLine()) != null) {
                commands.add(line);
            }
            return commands;
        }
    }

    @Override
    public ArrayList<String> loadCommands(String filePath) throws IOException {
        try (var fileStreamReader = new FileReader(filePath)) {
            var data = getData(fileStreamReader);
            return data;
        } catch (IOException exception) {
            throw exception;
        }
    }

    @Override
    public ArrayList<String> loadCommands() throws IOException {
        try (var streamReader = new InputStreamReader(System.in)) {
            var data = getData(streamReader);
            return data;
        }
    }
}

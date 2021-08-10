package ru.nsu.sergomyaso.calculator;

import java.io.IOException;
import java.util.ArrayList;

public interface BaseLoader {
    ArrayList<String> loadCommands(String filePath) throws IOException;
    ArrayList<String> loadCommands() throws IOException;
}

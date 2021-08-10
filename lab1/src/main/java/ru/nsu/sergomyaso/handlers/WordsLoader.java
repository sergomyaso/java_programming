package ru.nsu.sergomyaso.handlers;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class WordsLoader implements Loader {
    private final Parser parser;

    public WordsLoader(Parser parser) {
        this.parser = parser;
    }

    public WordsLoader() {
        this.parser = new RegularParser();
    }

    @Override
    public ArrayList<String> getData(String filePath) throws IOException {
        ArrayList<String> wordsList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line = reader.readLine();
            while (line != null) {
                wordsList.addAll(this.parser.parse(line));
                line = reader.readLine();
            }
        }
        return wordsList;

    }
}

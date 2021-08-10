package ru.nsu.sergomyaso.handlers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

class TextHandlerTest {
    private final Handler handler = new TextHandler(new WordsLoader(new RegularParser()), new CountAnalyzer(), new TableExporter());
    private static final String TEMPLATE_TITLE_IN_TEST = "in_test%d.txt";
    private static final String TEMPLATE_TITLE_OUT_TEST = "out_test%d.csv";
    private static final String DEFAULT_OUT_PATH = "out.csv";
    private static final String PATH_TO_TEMPLATE = "../lab1/src/test/TextHandlersTestCases/";

    static private int getCountTestCases() throws IOException {
        int countTestCases = 0;
        try {
            var listOfFiles = Files.list(Path.of(PATH_TO_TEMPLATE)).collect(Collectors.toList());
            countTestCases = listOfFiles.size() / 2;
        } catch (IOException exception) {
            exception.printStackTrace();
            throw exception;
        }
        return countTestCases;
    }

    static private String getContentFile(String filePath) throws IOException {
        String text = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            text = "";
            String line = reader.readLine();
            while (line != null) {
                text += line;
                line = reader.readLine();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
            throw exception;
        }
        return text;

    }

    @Test
    public void checkTestCases() throws IOException {
        var countCases = getCountTestCases();
        for (int i = 1; i <= countCases; i++) {
            handler.handle(PATH_TO_TEMPLATE + String.format(TEMPLATE_TITLE_IN_TEST, i), DEFAULT_OUT_PATH);
            String programAnswer = getContentFile(DEFAULT_OUT_PATH);
            String realAnswer = getContentFile(PATH_TO_TEMPLATE + String.format(TEMPLATE_TITLE_OUT_TEST, i));
            Assertions.assertEquals(programAnswer, realAnswer);
        }
    }
}
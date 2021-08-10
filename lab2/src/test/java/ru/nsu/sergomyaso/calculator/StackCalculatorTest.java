package ru.nsu.sergomyaso.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class StackCalculatorTest {
    private static final String DEFAULT_OUT_PATH = "out.txt";
    private static final String DEFAULT_LOGGER_PATH = "../lab2/src/main/resources/calculator.log";
    private static final String TEMPLATE_TITLE_IN_TEST = "in_test%d.txt";
    private static final String TEMPLATE_TITLE_OUT_TEST = "out_test%d.txt";
    private static final String PATH_TO_TEMPLATE_RESULTS = "../lab2/src/test/resources/StackCalculatorTestCase/ResultsCases/";
    private static final String PATH_TO_TEMPLATE_EXCEPTION = "../lab2/src/test/resources/StackCalculatorTestCase/ExceptionCases/";
    private static final StackCalculator calculator = new StackCalculator();

    static private int getCountTestCases(String filePath) throws IOException {
        int countTestCases = 0;
        try {
            var listOfFiles = Files.list(Path.of(filePath)).collect(Collectors.toList());
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
    public void checkTestResultsCases() throws IOException {

        var countCases = getCountTestCases(PATH_TO_TEMPLATE_RESULTS);
        for (int i = 1; i <= countCases; i++) {
            System.setOut( new PrintStream(new FileOutputStream(DEFAULT_OUT_PATH)));
            calculator.calculate(PATH_TO_TEMPLATE_RESULTS + String.format(TEMPLATE_TITLE_IN_TEST, i));
            String programAnswer = getContentFile(DEFAULT_OUT_PATH);
            String realAnswer = getContentFile(PATH_TO_TEMPLATE_RESULTS + String.format(TEMPLATE_TITLE_OUT_TEST, i));
            Assertions.assertEquals(programAnswer, realAnswer);
        }

    }

    @Test
    public void checkTestExceptionCases() throws IOException {
        System.setOut(new PrintStream(new FileOutputStream(DEFAULT_OUT_PATH)));
        var countCases = getCountTestCases(PATH_TO_TEMPLATE_EXCEPTION);
        for (int i = 1; i <= countCases; i++) {
            calculator.calculate(PATH_TO_TEMPLATE_EXCEPTION + String.format(TEMPLATE_TITLE_IN_TEST, i));
            String programAnswer = calculator.getErrno();;
            String realAnswer = getContentFile(PATH_TO_TEMPLATE_EXCEPTION + String.format(TEMPLATE_TITLE_OUT_TEST, i));
            Assertions.assertEquals(programAnswer, realAnswer);
        }
    }

}

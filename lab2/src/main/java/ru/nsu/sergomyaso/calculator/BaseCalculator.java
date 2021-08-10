package ru.nsu.sergomyaso.calculator;

import java.io.IOException;

public interface BaseCalculator {
    void calculate(String filePath) throws CalculatorException, IOException;
    void calculate() throws CalculatorException, IOException;
    String getErrno();
}

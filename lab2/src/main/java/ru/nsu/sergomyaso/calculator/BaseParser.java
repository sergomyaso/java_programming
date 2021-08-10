package ru.nsu.sergomyaso.calculator;

public interface BaseParser {
    ModelDataCommand parseCommand(String command) throws CalculatorException;
}

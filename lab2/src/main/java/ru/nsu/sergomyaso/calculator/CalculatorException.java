package ru.nsu.sergomyaso.calculator;

public class CalculatorException extends Exception {
    CalculatorException(Exception subException){
        super(subException);
    }
}


package ru.nsu.sergomyaso.calculator;

public class StackEmptyCommandException extends Exception {
    StackEmptyCommandException(String errMessage) {
        super(errMessage);
    }
}

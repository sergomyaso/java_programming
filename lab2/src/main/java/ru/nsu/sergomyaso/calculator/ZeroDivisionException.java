package ru.nsu.sergomyaso.calculator;

public class ZeroDivisionException extends Exception {
    ZeroDivisionException(String errMessage) {
        super(errMessage);
    }
}

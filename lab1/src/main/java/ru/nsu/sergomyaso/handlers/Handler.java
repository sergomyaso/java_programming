package ru.nsu.sergomyaso.handlers;

import java.io.IOException;

public interface Handler {
    void handle(String inputFilePath, String outputFilePath) throws IOException;
    void handle() throws IOException;

}

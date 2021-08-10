package ru.nsu.sergomyaso.handlers;

import java.io.IOException;

public class TextHandler implements Handler {
    private static final String DEFAULT_INPUT_FILE_PATH = "input.txt";
    private static final String DEFAULT_OUTPUT_FILE_PATH = "output.csv";

    private final Loader loader;
    private final Analyzer analyzer;
    private final Exporter exporter;

    public TextHandler(Loader loader, Analyzer analyzer, Exporter exporter) {
        this.loader = loader;
        this.analyzer = analyzer;
        this.exporter = exporter;
    }

    @Override
    public void handle(String inputFilePath, String outputFilePath) throws IOException {
        try {
            var analyzedData = analyzer.getAnalyzedData(loader.getData(inputFilePath));
            exporter.export(analyzedData, outputFilePath);
        } catch (IOException exception) {
            throw exception;
        }
    }

    @Override
    public void handle() throws IOException {
        handle(DEFAULT_INPUT_FILE_PATH, DEFAULT_OUTPUT_FILE_PATH);
    }
}

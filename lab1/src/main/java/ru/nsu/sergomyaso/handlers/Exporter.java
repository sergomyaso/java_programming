package ru.nsu.sergomyaso.handlers;


import java.io.IOException;
import java.util.List;

public interface Exporter {
    void export(List<Pair<String, Integer>> data, String filePath) throws IOException;

}

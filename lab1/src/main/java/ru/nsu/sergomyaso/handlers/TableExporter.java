package ru.nsu.sergomyaso.handlers;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TableExporter implements Exporter {

    private static final String PATTERN_TABLE_TITLES = "%s;%d;%f;";

    private static int getCountWords(List<Pair<String, Integer>> data) {
        int count = 0;
        for (var pair : data) {
            count += pair.getSecond();
        }
        return count;
    }

    @Override
    public void export(List<Pair<String, Integer>> data, String filePath) throws IOException {
        var countWords = getCountWords(data);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, StandardCharsets.UTF_8))) {
            for (var pair : data) {
                float percentInclusion = (float) pair.getSecond() / countWords * 100;
                writer.write(String.format(PATTERN_TABLE_TITLES, pair.getFirst(), pair.getSecond(), percentInclusion));
                writer.newLine();
            }
        }
    }
}

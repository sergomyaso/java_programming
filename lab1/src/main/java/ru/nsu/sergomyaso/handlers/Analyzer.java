package ru.nsu.sergomyaso.handlers;

import java.util.ArrayList;
import java.util.List;

public interface Analyzer {
    List<Pair<String, Integer>> getAnalyzedData(ArrayList<String> dataList);
}

package ru.nsu.sergomyaso.handlers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CountAnalyzer implements Analyzer {

    private Pair<String, Integer> findPairInList(List<Pair<String, Integer>> list, Pair<String, Integer> searchable) {
        for (var pair : list) {
            if (pair.getFirst().equals(searchable.getFirst()))
                return pair;
        }
        return null;
    }

    @Override
    public List<Pair<String, Integer>> getAnalyzedData(ArrayList<String> dataList) {
        List<Pair<String, Integer>> analyzedData = new ArrayList<>();
        Pair<String, Integer> searchableElement = new Pair<>();
        for (var str : dataList) {
            searchableElement.setFirst(str);
            var element = findPairInList(analyzedData, searchableElement);
            if (element == null) {
                element = new Pair<String, Integer>(str, 0);
                analyzedData.add(element);
            }
            element.setSecond(element.getSecond() + 1);
        }
        analyzedData.sort(new Comparator<Pair<String, Integer>>() {
            @Override
            public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
                return o2.getSecond() - o1.getSecond();
            }
        });
        return analyzedData;
    }
}

package ru.nsu.sergomyaso.handlers;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularParser implements Parser {
    private static final String DEFAULT_STRING_PATTERN = "[a-zA-ZА-Яа-я\\d]+";

    private final Pattern pattern;

    public RegularParser(String stringPattern) {
        this.pattern = Pattern.compile(stringPattern);
    }

    public RegularParser() {
        this.pattern = Pattern.compile(DEFAULT_STRING_PATTERN);
    }

    @Override
    public ArrayList<String> parse(String data) {
        ArrayList<String> parsedData = new ArrayList<String>();
        Matcher matcher = pattern.matcher(data);
        while (matcher.find()) {
            parsedData.add(data.substring(matcher.start(), matcher.end()));
        }
        return parsedData;
    }

}

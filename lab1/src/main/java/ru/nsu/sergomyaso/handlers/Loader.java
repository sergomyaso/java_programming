package ru.nsu.sergomyaso.handlers;

import java.io.IOException;
import java.util.ArrayList;

public interface Loader {
    ArrayList<String> getData(String filePath) throws IOException;
}

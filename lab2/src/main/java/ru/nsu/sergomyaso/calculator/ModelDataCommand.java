package ru.nsu.sergomyaso.calculator;

import java.util.ArrayList;

public class ModelDataCommand {
    private String title;
    private ArrayList<String> args;

    ModelDataCommand(String title, ArrayList<String> args) {
        this.title = title;
        this.args = args;
    }

    public String getTitle() {
        return this.title;
    }

    public ArrayList<String> getArgs() {
        return this.args;
    }
}

package ru.nsu.sergomyaso.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class CommandContext {
    private Stack<Double> stack;
    private ArrayList<String> args;
    private HashMap<String, Double> variations;

    public CommandContext() {
        this.stack = new Stack<>();
        this.args = new ArrayList<>();
        this.variations = new HashMap<>();
    }

    public Stack<Double> getStack() {
        return this.stack;
    }

    public ArrayList<String> getArgs() {
        return this.args;
    }

    public HashMap<String, Double> getVariations() {
        return this.variations;
    }
}

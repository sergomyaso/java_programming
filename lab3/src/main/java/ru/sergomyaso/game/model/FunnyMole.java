package ru.sergomyaso.game.model;

public class FunnyMole extends Character {
    private static final int SCORE_COUNT = 15;
    private static final int TIME_SECONDS_LIVE = 1;
    private static final String TITLE = "FunnyMole";

    public FunnyMole() {
        super();
    }

    @Override
    public String getStringTitle() {
        return TITLE;
    }

    @Override
    public int getScoreCount() {
        return SCORE_COUNT;
    }

    @Override
    public void restore() {
        this.timeLive = TIME_SECONDS_LIVE;
        this.isAlive = true;
    }
}
package ru.sergomyaso.game.model;

public class ChewedMole extends Character {
    private static final int SCORE_COUNT = 10;
    private static final int TIME_SECONDS_LIVE = 3;
    private static final String TITLE = "ChewedMole";

    public ChewedMole() {
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

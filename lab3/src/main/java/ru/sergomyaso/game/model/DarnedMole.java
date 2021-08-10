package ru.sergomyaso.game.model;

public class DarnedMole extends Character {
    private static final int SCORE_COUNT = 15;
    private static final int TIME_SECONDS_LIVE = 2;
    private static final String TITLE = "DarnedMole";

    public DarnedMole() {
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

package ru.sergomyaso.game.model;

public class BombMole extends Character {
    private static final int SCORE_COUNT = -20;
    private static final int TIME_SECONDS_LIVE = 5;
    private static final String TITLE = "BombMole";

    public BombMole() {
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
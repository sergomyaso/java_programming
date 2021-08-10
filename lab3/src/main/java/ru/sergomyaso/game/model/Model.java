package ru.sergomyaso.game.model;

public interface Model {

    boolean isGameEnd();

    void initMapParams();

    Character getFreeCharacter();

    void update();

    void killCharacter(Character character);

    int getUserScoreCount();

    void increaseGameTime();

    void startGame();
}

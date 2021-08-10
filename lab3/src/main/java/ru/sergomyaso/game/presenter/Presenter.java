package ru.sergomyaso.game.presenter;

import ru.sergomyaso.game.model.Character;

public interface Presenter {

    void update();

    void killCharacter(Character character);

    int getUserScoreCount();

    void gameStart();

    boolean isGameEnd();

    Character getFreeCharacter();

    boolean isCharacterAlive(Character character);

    boolean isCharacterNULL(Character character);
}

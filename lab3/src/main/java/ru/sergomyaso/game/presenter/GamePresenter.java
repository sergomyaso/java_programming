package ru.sergomyaso.game.presenter;

import ru.sergomyaso.game.model.GameModel;
import ru.sergomyaso.game.model.Character;
import ru.sergomyaso.game.model.Model;
import ru.sergomyaso.game.view.View;
import ru.sergomyaso.game.view.javafxview.CharacterView;


public class GamePresenter implements Presenter {
    private final View gameView;
    private final Model gameModel;

    public GamePresenter(View view) {
        this.gameView = view;
        this.gameModel = new GameModel();
    }

    @Override
    public void update() {
        if (this.gameView == null)
            return;
        gameModel.update();
        gameView.updateView();
    }

    @Override
    public void killCharacter(Character character) {
        this.gameModel.killCharacter(character);
    }

    @Override
    public int getUserScoreCount() {
        return this.gameModel.getUserScoreCount();
    }

    @Override
    public void gameStart() {
        this.gameModel.startGame();
    }

    @Override
    public boolean isGameEnd() {
        return gameModel.isGameEnd();
    }

    @Override
    public Character getFreeCharacter() {
        return this.gameModel.getFreeCharacter();
    }

    @Override
    public boolean isCharacterAlive(Character character) {
        return character.isAlive();
    }

    @Override
    public boolean isCharacterNULL(Character character) {
        return character == null;
    }

}

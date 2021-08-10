package ru.sergomyaso.game.view.javafxview;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class ResultChecker {
    private static final int TIME_UPDATE = 1; // in seconds
    private static final int TIME_WAIT = 3; // in seconds
    private App app;
    private Timeline timer;
    private int timeCounter;

    public ResultChecker(App app) {
        this.app = app;
        this.timeCounter = 0;
        initTimer();
    }

    private void initTimer() {
        timer = new Timeline(new KeyFrame(Duration.seconds(TIME_UPDATE), lambda -> {
            this.resultTimerAdapter();
        }));
        timer.setCycleCount(Animation.INDEFINITE);
    }

    public void checkResult() {
        timer.play();
    }

    private void runGame() {
        this.timer.stop();
        this.timeCounter = 0;
        this.app.getGameView().getPresenter().gameStart();
        this.app.runGameView();
    }

    private void resultTimerAdapter() {
        if (isTimerOver())
            runGame();
        this.timeCounter++;
    }

    private boolean isTimerOver() {
        return timeCounter >= TIME_WAIT;
    }

}

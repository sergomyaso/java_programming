package ru.sergomyaso.game.view.consoleview;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class ViewUpdater {

    private static final int INDEX_USER_X = 0;
    private static final int INDEX_USER_Y = 0;
    private static final int INTEGER_CONVERT = 10;
    private static final int TIME_UPDATE = 100; // milliseconds
    private static final String STRING_RESULT_PATTERN = "Your score %d, bro";
    private Timer gameTimer;
    private GameView gameView;
    private Scanner scanner;

    public ViewUpdater() {
        gameView = new GameView();
        scanner = new Scanner(System.in);
        initGameTimer();
    }

    private void initGameTimer() {
        this.gameTimer = new Timer();
        this.gameTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                gameTimerAdapter();
            }
        }, 0, TIME_UPDATE);
    }

    private void gameTimerAdapter() {
        if (gameView.getPresenter().isGameEnd()) {
            this.gameTimer.cancel();
            showEndGame();
            return;
        }
        this.gameView.getPresenter().update();
        listenUserCoordinates();
    }

    void listenUserCoordinates() {
        var userString = scanner.nextLine();
        if (userString.length() != 2)
            return;
        var userPoint = new ConsolePoint(Character.digit(userString.charAt(INDEX_USER_X), INTEGER_CONVERT) , Character.digit(userString.charAt(INDEX_USER_Y), INTEGER_CONVERT) + 1);
        gameView.userActionHandler(userPoint);
    }

    private void startGame() {
        gameView.getPresenter().gameStart();
        initGameTimer();
    }

    private void askUserAboutGame() {
        while (true) {
            System.out.println("Else game, bro? y/n");
            var answer = scanner.nextLine();
            if (answer.equals("y")) {
                startGame();
                break;
            }
            if (answer.equals("n")) {
                System.out.println("Goodbye, bro");
                return;
            }
            System.out.println("Kosachish, bro");
        }
    }

    private void showEndGame() {
        System.out.println(String.format(STRING_RESULT_PATTERN, gameView.getPresenter().getUserScoreCount()));
        askUserAboutGame();
    }

    public static void main(String[] args) {

    }
}

package ru.sergomyaso.game.view.javafxview;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {

    private static final double TIME_UPDATE = 0.1; // in seconds
    private static final String PATH_STYLE = "file:src/main/resources/styles/style.css";
    private static final String STRING_RESULT_PATTERN = "Your score:%d";
    private static final String ID_GAME_SCENE = "main-window";
    private static final String ID_END_GAME_SCENE = "end-window";
    private static final String ID_END_TEXT_RESULTS = "text-score";
    private static final double WIDTH_WINDOW = 600;
    private static final double HEIGHT_WINDOW = 400;

    private GameView gameView;
    private Stage stage;
    private ResultChecker resultChecker;
    private Scene endScene;
    private Scene gameScene;
    private Text textResults;
    private Timeline timer;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        gameView = new GameView();
        resultChecker = new ResultChecker(this);
        initGameScene();
        initEndScene();
        stage.setResizable(false);
        stage.setScene(gameScene);
        stage.show();
        initTimeLine();
    }

    private void initGameScene() {
        StackPane root = new StackPane();
        root.setId(ID_GAME_SCENE);
        root.getChildren().add(gameView.getCanvas());
        gameScene = new Scene(root, WIDTH_WINDOW, HEIGHT_WINDOW);
        gameScene.setUserAgentStylesheet(PATH_STYLE);
        gameScene.setOnMousePressed(e -> this.gameView.mouseClickHandler(e.getX(), e.getY()));
    }

    private void initEndScene() {
        StackPane root = new StackPane();
        root.setId(ID_END_GAME_SCENE);
        textResults = new Text();
        textResults.setId(ID_END_TEXT_RESULTS);
        textResults.setTextAlignment(TextAlignment.CENTER);
        root.getChildren().addAll(textResults);
        StackPane.setAlignment(textResults, Pos.CENTER);
        endScene = new Scene(root, WIDTH_WINDOW, HEIGHT_WINDOW);
        endScene.setUserAgentStylesheet(PATH_STYLE);
    }

    public void showEndGame() {
        gameView.clearCanvas();
        showResults();
        resultChecker.checkResult();
    }

    public void initTimeLine() {
        timer = new Timeline(new KeyFrame(Duration.seconds(TIME_UPDATE), lambda -> gameLoopTimerAdapter()));
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();
    }

    private void gameLoopTimerAdapter() {
        if (gameView.getPresenter().isGameEnd()) {
            timer.stop();
            showEndGame();
            return;
        }
        gameView.getPresenter().update();
    }

    public void runGameView() {
        stage.setScene(gameScene);
        timer.play();
    }

    public static void main(String[] args) {
        launch();
    }

    public GameView getGameView() {
        return gameView;
    }
    public void showResults() {
        textResults.setText(String.format(STRING_RESULT_PATTERN, gameView.getPresenter().getUserScoreCount()));
        stage.setScene(endScene);
    }

}

package ru.sergomyaso.game.view.javafxview;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import ru.sergomyaso.game.presenter.GamePresenter;
import ru.sergomyaso.game.presenter.Presenter;
import ru.sergomyaso.game.view.View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.IntStream;

public class GameView implements View {
    private static final int BEGIN_X_COORDINATE = 120;
    private static final int BEGIN_Y_COORDINATE = 65;
    private static final int OFFSET_X_COORDINATE = 130;
    private static final int OFFSET_Y_COORDINATE = 105;
    private static final int COUNT_COORDINATES_IN_ROW = 3;
    private static final int COUNT_COORDINATES_IN_COLUMN = 3;
    private static final int COUNT_WORK_CHARACTERS = 5;
    private static final int DEFAULT_X = 0;
    private static final int DEFAULT_Y = 0;
    private static final double WIDTH_WINDOW = 600;
    private static final double HEIGHT_WINDOW = 400;

    private final Presenter presenter;
    private final Canvas canvas;
    private final ArrayList<CharacterView> charactersViews;
    private final Queue<Point2D> coordinates;

    public GameView() {
        this.presenter = new GamePresenter(this);
        this.canvas = new Canvas(WIDTH_WINDOW, HEIGHT_WINDOW);
        this.coordinates = new LinkedList<>();
        this.charactersViews = new ArrayList<>();
        initCoordinates();
        initCharactersViews();
    }

    private void initCoordinates() {
        int coordinateX = BEGIN_X_COORDINATE;
        for (int i = 0; i < COUNT_COORDINATES_IN_ROW; i++) {
            int coordinateY = BEGIN_Y_COORDINATE;
            for (int j = 0; j < COUNT_COORDINATES_IN_COLUMN; j++) {
                this.coordinates.add(new Point2D(coordinateX, coordinateY));
                coordinateY += OFFSET_Y_COORDINATE;
            }
            coordinateX += OFFSET_X_COORDINATE;
        }
    }

    private void initCharactersViews() {
        IntStream.range(0, COUNT_WORK_CHARACTERS - 1)
                .forEach(i -> this.charactersViews.add(new CharacterView(this.canvas, Objects.requireNonNull(this.coordinates.poll()))));
    }

    private void updateCharacterViews() {
        IntStream.range(0, COUNT_WORK_CHARACTERS - 1)
                .forEach(i -> {
                    var characterView = this.charactersViews.get(i);
                    var currentCharacter = characterView.getCharacter();
                    if (!presenter.isCharacterNULL(currentCharacter) && presenter.isCharacterAlive(currentCharacter))
                        return;
                    characterView.setCharacter(this.presenter.getFreeCharacter());
                    this.coordinates.add(characterView.getPoint());
                    characterView.setPoint(Objects.requireNonNull(coordinates.poll()));
                });
    }

    private void drawCharacters() {
        this.charactersViews.forEach(BaseItemView::draw);
    }

    public void clearCanvas() {
        var context = canvas.getGraphicsContext2D();
        context.clearRect(DEFAULT_X, DEFAULT_Y, WIDTH_WINDOW, HEIGHT_WINDOW);
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public void mouseClickHandler(double x, double y) {
        this.charactersViews.forEach(item -> {
            if (item.isPointInside(new Point2D(x, y)))
                this.presenter.killCharacter(item.getCharacter());
        });
    }

    @Override
    public void updateView() {
        updateCharacterViews();
        clearCanvas();
        drawCharacters();
    }

    @Override
    public Presenter getPresenter() {
        return this.presenter;
    }

}

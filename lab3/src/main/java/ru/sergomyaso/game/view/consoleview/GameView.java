package ru.sergomyaso.game.view.consoleview;


import ru.sergomyaso.game.presenter.GamePresenter;
import ru.sergomyaso.game.presenter.Presenter;
import ru.sergomyaso.game.view.View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.IntStream;

public class GameView implements View {

    private static final int COUNT_COORDINATES_IN_ROW = 3;
    private static final int COUNT_COORDINATES_IN_COLUMN = 3;
    private static final int COUNT_WORK_CHARACTERS = 5;
    private static final int COUNT_END_LINES = 15;

    private final Presenter presenter;
    private final Queue<ConsolePoint> coordinates;
    private final ArrayList<CharacterView> charactersViews;
    private int[] gameMapPattern;
    private int[] gameMap;


    public GameView() {
        this.presenter = new GamePresenter(this);
        coordinates = new LinkedList<>();
        charactersViews = new ArrayList<>();
        gameMap = new int[COUNT_COORDINATES_IN_COLUMN * COUNT_COORDINATES_IN_ROW];
        gameMapPattern = new int[COUNT_COORDINATES_IN_COLUMN * COUNT_COORDINATES_IN_ROW];
        initCoordinates();
        initCharactersViews();
    }

    private void initCoordinates() {
        for (int i = 0; i < COUNT_COORDINATES_IN_ROW; i++) {
            for (int j = 0; j < COUNT_COORDINATES_IN_COLUMN; j++) {
                this.coordinates.add(new ConsolePoint(i, j));
            }
        }
    }

    private void initCharactersViews() {
        IntStream.range(0, COUNT_WORK_CHARACTERS - 1)
                .forEach(i -> this.charactersViews.add(new CharacterView(Objects.requireNonNull(this.coordinates.poll()))));
    }

    private void updateCharacterViews() {
        IntStream.range(0, COUNT_WORK_CHARACTERS - 1)
                .forEach(i -> {
                    var characterView = this.charactersViews.get(i);
                    var currentCharacter = characterView.getCharacter();
                    if (currentCharacter != null && currentCharacter.isAlive())
                        return;
                    characterView.setCharacter(this.presenter.getFreeCharacter());
                    this.coordinates.add(characterView.getPoint());
                    characterView.setPoint(Objects.requireNonNull(coordinates.poll()));
                });
    }

    void clearMap() {
        for (int i = 0; i < COUNT_COORDINATES_IN_ROW * COUNT_COORDINATES_IN_COLUMN; i++) {
           this.gameMap[i] = 0;
        }
    }

    private void clearArea() {
        IntStream.range(0, COUNT_END_LINES).forEach(index -> System.out.println(""));
    }

    private void drawMap() {
        for (int i = 0; i < COUNT_COORDINATES_IN_ROW; i++) {
            for (int j = 0; j < COUNT_COORDINATES_IN_COLUMN; j++) {
                System.out.print(this.gameMap[i * COUNT_COORDINATES_IN_ROW + j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }

    private void drawCharacters() {
        clearMap();
        clearArea();
        this.charactersViews.forEach(characterView -> {
            var characterPoint = characterView.getPoint();
            this.gameMap[characterPoint.getX() * COUNT_COORDINATES_IN_ROW + characterPoint.getY()] = characterView.getLogo();
        });
        drawMap();
    }

    public void userActionHandler(ConsolePoint point) {
        this.charactersViews.forEach(characterView -> {
            if (characterView.isUserHit(point)) {
                presenter.killCharacter(characterView.getCharacter());
            }
        });
    }

    @Override
    public void updateView() {
        updateCharacterViews();
        drawCharacters();
    }

    @Override
    public Presenter getPresenter() {
        return this.presenter;
    }
}

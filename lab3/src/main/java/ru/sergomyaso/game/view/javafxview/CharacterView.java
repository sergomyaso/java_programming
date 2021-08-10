package ru.sergomyaso.game.view.javafxview;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import ru.sergomyaso.game.model.Character;


import java.util.HashMap;

public class CharacterView extends BaseItemView {
    private static final double DEFAULT_WIDTH = 100;
    private static final double DEFAULT_HEIGHT = 60;

    private final HashMap<String, String> logosMap;
    private Character character;
    private Point2D point;

    public CharacterView(Canvas canvas, Point2D point) {
        super(point.getX(), point.getY(), DEFAULT_WIDTH, DEFAULT_HEIGHT, canvas);
        this.point = point;
        this.character = null;
        this.logosMap = new HashMap<>();
        this.initLogosMap();
    }

    public void setPoint(Point2D point) {
        this.point = point;
        this.setX(point.getX());
        this.setY(point.getY());
    }

    public Point2D getPoint() {
        return this.point;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Character getCharacter() {
        return this.character;
    }

    private void initLogosMap() {
        this.logosMap.put("ChewedMole", "file:src/main/resources/images/ChewedMole.png");
        this.logosMap.put("DarnedMole", "file:src/main/resources/images/DarnedMole.png");
        this.logosMap.put("BombMole", "file:src/main/resources/images/BombMole.png");
        this.logosMap.put("FunnyMole", "file:src/main/resources/images/FunnyMole.png");
    }

    private String getLogo() {
        return logosMap.get(this.character.getStringTitle());
    }

    @Override
    public ImagePattern getImagePattern() {
        return new ImagePattern(new Image(this.getLogo()));
    }
}

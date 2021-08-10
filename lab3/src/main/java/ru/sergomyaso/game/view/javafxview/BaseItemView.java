package ru.sergomyaso.game.view.javafxview;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class BaseItemView extends Rectangle {
    private static final String DEFAULT_LOGO = "file:gog.png";
    private final Canvas canvas;

    protected ImagePattern getImagePattern() {
        return new ImagePattern(new Image(DEFAULT_LOGO));
    }

    public boolean isPointInside(Point2D point) {
        Point2D firstBorder = new Point2D(getX(), getY());
        Point2D secondBorder = new Point2D(getX() + getWidth(), getY() + getHeight());

        return point.getX() >= firstBorder.getX() && point.getX() <= secondBorder.getX() &&
                point.getY() >= firstBorder.getY() && point.getY() <= secondBorder.getY();
    }

    public BaseItemView(double x, double y, double width, double height, Canvas canvas) {
        super(x, y, width, height);
        this.canvas = canvas;
    }

    Canvas getCanvas() {
        return this.canvas;
    }

    public void draw() {
        Canvas canvas = getCanvas();
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.setFill(getImagePattern());
        context.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}
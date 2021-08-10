package ru.sergomyaso.game.view.consoleview;

import ru.sergomyaso.game.model.Character;

import java.util.HashMap;

public class CharacterView {
    private Character character;
    private ConsolePoint point;
    private final HashMap<String, Integer> logosMap;


    public CharacterView(ConsolePoint point) {
        this.point = point;
        this.character = null;
        this.logosMap = new HashMap<>();
        this.initLogosMap();
    }

    private void initLogosMap() {
        this.logosMap.put("ChewedMole", 1);
        this.logosMap.put("DarnedMole", 1);
        this.logosMap.put("BombMole", 5);
        this.logosMap.put("FunnyMole", 1);
    }

    public void setPoint(ConsolePoint point) {
        this.point = point;
    }

    public ConsolePoint getPoint() {
        return point;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public boolean isUserHit(ConsolePoint point) {
        return this.point.getX() == point.getX() && this.point.getY() == point.getY();
    }

    public int getLogo() {
        return logosMap.get(character.getStringTitle());
    }
}

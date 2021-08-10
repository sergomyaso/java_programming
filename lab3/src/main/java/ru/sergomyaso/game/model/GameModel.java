package ru.sergomyaso.game.model;

import java.util.ArrayList;
import java.util.Timer;
import java.util.Queue;
import java.util.LinkedList;
import java.util.TimerTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameModel implements Model {

    private static final int TIME_ROUND = 30; // seconds
    private static final int UPDATE_TIME = 1000; // milliseconds
    private static final int COUNT_WORKERS = 5;

    private final ArrayList<Character> workCharacters;
    private final Queue<Character> freeWorkCharacters;
    private final Queue<Character> sleepCharacters;
    private int userScoreCount;
    private int gameTime;
    private Timer timer;

    public GameModel() {
        this.sleepCharacters = new LinkedList<>();
        this.workCharacters = new ArrayList<>();
        this.freeWorkCharacters = new LinkedList<>();
        this.generateCharacters();
        initMapParams();
        this.addFirstWorkers();
        initTimer();
    }

    void finish() {
        timer.cancel();
    }

    private void initTimer() {
        this.timer = new Timer();
        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timerAdapter();
            }
        }, 0, UPDATE_TIME);
    }

    @Override
    public boolean isGameEnd() {
        return this.gameTime == TIME_ROUND;
    }

    private void timerAdapter() {
        this.workCharacters.forEach(Character::lessTimeLive);
        increaseGameTime();
        if (isGameEnd())
            finish();
    }

    @Override
    public void initMapParams() {
        this.gameTime = 0;
        this.userScoreCount = 0;
    }

    private void addFirstWorkers() {
        IntStream.range(0, COUNT_WORKERS - 1).forEach(i -> addCharacter());
    }

    private void generateCharacters() {
        sleepCharacters.add(new ChewedMole());
        sleepCharacters.add(new FunnyMole());
        sleepCharacters.add(new BombMole());
        sleepCharacters.add(new DarnedMole());
        sleepCharacters.add(new DarnedMole());
        sleepCharacters.add(new BombMole());
        sleepCharacters.add(new ChewedMole());
        sleepCharacters.add(new FunnyMole());
        sleepCharacters.add(new ChewedMole());
        sleepCharacters.add(new DarnedMole());
        sleepCharacters.add(new BombMole());
    }

    private void addCharacter() {
        var character = this.sleepCharacters.poll();
        this.freeWorkCharacters.add(character);
        this.workCharacters.add(character);
        character.restore();
    }

    private void removeCharacter(Character character) {
        this.workCharacters.remove(character);
        this.sleepCharacters.add(character);
        addCharacter();
    }

    @Override
    public Character getFreeCharacter() {
        return this.freeWorkCharacters.poll();
    }

    @Override
    public void update() {
        var deadCharacter = workCharacters.stream().filter(item -> !item.isAlive()).collect(Collectors.toList());
        if (deadCharacter.size() != 0)
            deadCharacter.forEach(this::removeCharacter);
    }

    @Override
    public void killCharacter(Character character) {
        this.userScoreCount += character.getScoreCount();
        character.kill();
    }

    @Override
    public int getUserScoreCount() {
        return userScoreCount;
    }

    @Override
    public void increaseGameTime() {
        this.gameTime += 1;
    }

    @Override
    public void startGame() {
        initMapParams();
        initTimer();
    }
}

package ru.sergomyaso.game.model;

public abstract class Character {
    protected int timeLive;
    protected boolean isAlive;

    public Character(){
        timeLive = 0;
        isAlive = false;
    }

    public abstract int getScoreCount();

    public abstract void restore();

    public abstract String getStringTitle();

    public void lessTimeLive() {
        this.timeLive -= 1;
    }

    public void kill() {
        this.isAlive = false;
        this.timeLive = 0;
    }

    public boolean isAlive() {
        return isAlive && timeLive > 0;
    }

}
